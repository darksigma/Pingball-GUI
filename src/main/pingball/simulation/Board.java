package pingball.simulation;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import physics.Vect;
import pingball.util.Pair;
import pingball.parser.BoardGrammarCentral;
import pingball.simulation.Wall;
import pingball.simulation.gadget.*;
import pingball.simulation.collidable.Collidable;

/**
 * A pingball board.
 *
 * This keeps track of all objects on a board, including balls, walls, and
 * gadgets.
 * 
 * Rep Invariant:
 *  None. All the game-objects on the board have a rep-invariant for themselves which checks 
 *  if they are inside the board, working correctly etc. Calling the board's checkRep is equivalent
 *  to calling all these checkReps(), which are neways being called after any method. So no need to
 *  have a separate checkRep() in board.
 *  
 */
public class Board {

    private boolean named = false;

    private String name;

    private final Set<Ball> balls = new HashSet<>();
    
    private final Set<Portal> portals = new HashSet<>();
    
 //   private final Map<String,Portal> portalNameMap = new HashMap<>();
    
    private final Set<GameObject> gameObjects = new HashSet<>();

    private final Map<String,List<Gadget>> keyUpMap = new HashMap<>(); 

    private final Map<String,List<Gadget>> keyDownMap = new HashMap<>(); 
    
    private final BlockingQueue<String> sendQueue;

    private final Wall topWall, bottomWall, leftWall, rightWall;

    private double gravity = Constants.DEFAULT_GRAVITY;

    private double mu1 = Constants.DEFAULT_FRICTION_MU1, mu2 = Constants.DEFAULT_FRICTION_MU2;

    /**
     * Make a Board.
     *
     * @param sendQueue The queue that takes teleported balls.
     *
     * @param file The board file to read from.
     */
    public Board(BlockingQueue<String> sendQueue, File file) throws IOException {
        this.sendQueue = sendQueue;
        
        topWall = new Wall(this, Wall.Side.TOP, sendQueue);
        bottomWall = new Wall(this, Wall.Side.BOTTOM, sendQueue);
        leftWall = new Wall(this, Wall.Side.LEFT, sendQueue);
        rightWall = new Wall(this, Wall.Side.RIGHT, sendQueue);
        
        addGameObject(topWall);
        addGameObject(bottomWall);
        addGameObject(leftWall);
        addGameObject(rightWall);
        
        BoardGrammarCentral bgc = new BoardGrammarCentral();
        try {
            bgc.parse(file,this);
            this.gravity = bgc.getGravity();
            this.mu1 = bgc.getFriction1();
            this.mu2 = bgc.getFriction2();
            this.name = bgc.getName();
            if (!this.name.equals("")) this.named = true; //TODO Check this
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Portal portal: portals){
            portal.find(portals);
            portal.setQueue(sendQueue);
        }
    }
    
    public void portalMsg() {
    	if (!named) {
            throw new RuntimeException("cannot connect to server with unnamed board");
        }
        assert name != null;
        try {
        	String myportalmsg = "myportals "+this.name;
        	for(Portal portal: portals){
        		myportalmsg = myportalmsg + " "+ portal.getName();
        	}
            sendQueue.put(myportalmsg);
            System.out.println(myportalmsg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	/**
     * Send a hello message to the server.
     */
    public void hello() {
        if (!named) {
            throw new RuntimeException("cannot connect to server with unnamed board");
        }
        assert name != null;
        try {
            sendQueue.put(String.format("hello %s", name));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    

    /**
     * Converts the short form regex into a true regex for board.
     *
     * @param regex The short form regex.
     *
     * @return The full regex.
     */
    private static String regexify(String regex) {
        String WHITESPACE = "\\s+";
        String EQ = "\\s*=\\s*";
        String INTEGER = "[0-9]+";
        String FLOAT = "-?(?:[0-9]+\\.[0-9]*|\\.?[0-9]+)";
        String NAME = "[A-Za-z_][A-Za-z_0-9]*";
        String ORIENTATION = "0|90|180|270";
        String inner = regex
            .replace(" ", WHITESPACE)
            .replace("=", EQ)
            .replace("INTEGER", INTEGER)
            .replace("FLOAT", FLOAT)
            .replace("NAME", NAME)
            .replace("ORIENTATION", ORIENTATION);
        return String.format("^\\s*%s\\s*$", inner);
    }

    /**
     * Finds the matches of a regex pattern in an input.
     *
     * @param input The input string.
     *
     * @param regex The regular expression to match.
     *
     * @return The matches.
     */
    private static List<String> regexMatches(String input, String regex) {
        List<String> matches = new ArrayList<>();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                matches.add(matcher.group(i));
            }
        }
        return matches;
    }
    
    /**
     * Process a message to alter the board.
     *
     * Performs the action corresponding to a received message.
     *
     * @param message The message.
     */
    public void processMessage(String message) {
        String[] split = message.split(" ");

        String connectmsg = "connect (left|right|top|bottom) [A-Za-z_][A-Za-z_0-9]*";
        String disconnectmsg = "disconnect (left|right|top|bottom)";
        String ballmsg = "^ball [A-Za-z_][A-Za-z_0-9]* (left|right|top|bottom)( -?(?:[0-9]+\\.[0-9]*|\\.?[0-9]+)){4}$";
        String keyUpmsg = "^keyup [a-z0-9]+";
        String keyDownmsg = "^keydown [a-z0-9]+";
        String myportalmsg = "^myportals( [A-Za-z_][A-Za-z_0-9]*)+";
        String boardNotOnServermsg = "^notonboard [A-Za-z_][A-Za-z_0-9]*";
        String portalBallmsg = "^portalball [A-Za-z_][A-Za-z_0-9]* [A-Za-z_][A-Za-z_0-9]* [A-Za-z_][A-Za-z_0-9]*( -?(?:[0-9]+\\.[0-9]*|\\.?[0-9]+)){4}$";
        String portalSelfOnlymsg = "^portalSelfOnly";
        if (message.matches(connectmsg)) {
            connectWall(Wall.Side.fromString(split[1]), split[2]);
        } else if (message.matches(disconnectmsg)) {
            disconnectWall(Wall.Side.fromString(split[1]));
        } else if (message.matches(ballmsg)) {
            String ballName = split[1];
            Wall.Side side = Wall.Side.fromString(split[2]);
            double x = parseDouble(split[3]), y = parseDouble(split[4]),
                vx = parseDouble(split[5]), vy = parseDouble(split[6]);
            // fix location
            switch (side) {
                case LEFT:
                case RIGHT:
                    x = getWidth() - x;
                    break;
                case TOP:
                case BOTTOM:
                    y = getHeight() - y;
                    break;
            }
            addBall(new Ball(new Vect(x, y), new Vect(vx, vy), ballName, gravity, mu1, mu2));
        } else if (message.matches(keyUpmsg)){
          String key = split[1];
          triggerKeyUp(key);
        } else if (message.matches(keyDownmsg)){
          String key = split[1];
          triggerKeyDown(key);  
        } else if (message.matches(myportalmsg)){
            System.out.println(message);
            String boardName = split[1];
            if(split.length>2){
                List<String> otherPortals = Arrays.asList(split).subList(2, split.length);
                for(Portal p: portals){
                    if(boardName.equals(p.getOtherBoard())){
                    	if(otherPortals.contains(p.getOtherPortal())) {
                    		p.activate();
                    	}
                    }
                }
                	
            }
        } else if (message.matches(boardNotOnServermsg)) {
            System.out.println(message);
            String boardName = split[1];
            for(Portal p:portals){
                if(boardName.equals(p.getOtherBoard())){
                    p.deactivate();
                }
            }
        } else if (message.matches(portalBallmsg)){
          String ballName = split[1];
          double x = parseDouble(split[4]), y = parseDouble(split[5]),
                  vx = parseDouble(split[6]), vy = parseDouble(split[7]);
          String portalname = split[3];
          System.out.println(message);
          for(Portal portal: portals){
              if(portal.getName().equals(portalname)){
                  Vect transferLoc = new Vect(portal.getLocation().x()+0.5, portal.getLocation().y()+0.5);
                  addBall(new Ball(transferLoc, new Vect(vx, vy), ballName, gravity, mu1, mu2));
              }
          }
          
        } else if (message.matches(portalSelfOnlymsg)) {
            for(Portal p: portals){
                p.selfOnly();
            }
        }else {
            System.err.println("ignoring invalid message from server");
        }
    }

    /**
     * Simulate the board for a specified time.
     *
     * Evolve the contents of the board for a specified amount of time,
     * simulating gravity and collisions.
     *
     * @param time The amount of time (in seconds) to simulate for.
     */
    public void evolve(double time) {
        while (time > 0) {
            double simTime = Math.min(time, Constants.SIMULATION_TIMESTEP);
            boolean collision = false;
            Ball collideBall = null;
            GameObject collideGameObject = null;
            Collidable collideCollidable = null;
            
            for(Ball ball: balls) {
                for(GameObject gameObject: gameObjects) {
                    if( ball != gameObject ) {
                        Pair<Double, Collidable> collide = gameObject.timeUntilCollision(ball);
                        if(collide.getFirst() < simTime) {
                            collision = true;
                            collideBall = ball;
                            collideGameObject = gameObject;
                            collideCollidable = collide.getSecond();
                            simTime = collide.getFirst(); 
                        }
                    }
                }
            }
            
            simulate(simTime);
            if (collision) {
                collideGameObject.collide(collideBall, collideCollidable);
            } 
            time -= simTime;
        }
    }

    /**
     * Returns the name of this board.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the width of this board.
     *
     * @return The width.
     */
    public int getWidth() {
        return Constants.BOARD_WIDTH;
    }
    
    /**
     * Returns the height of this board.
     *
     * @return The height.
     */
    public int getHeight() {
        return Constants.BOARD_HEIGHT;
    }

    public Set<GameObject> getGameObjects(){
        return this.gameObjects;
    }
    
    /**
     * Returns a grid representation of this board.
     *
     * Returns a grid representation as a 22x22 grid of characters
     * corresponding to different game objects on the board.
     *
     * @return The grid representation.
     */
    public List<String> gridRepresentation() {
        char[][] representation = new char[1+getHeight()+1][1+getHeight()+1];
        for(int row = 0; row < representation.length; row ++ ) {
            for(int col = 0; col < representation[row].length; col ++ ) {
                representation[row][col] = ' ';
            }
        }
        for(GameObject gameObject: gameObjects) {
            GridLocation locationObject = gameObject.getLocation();
            List<String> representationObject = gameObject.gridRepresentation();
            
            int rowIndex = locationObject.y() + 1, colIndex = locationObject.x() + 1;
            for(int row = 0; row < representationObject.size(); row ++ ) {
                String line = representationObject.get(row);
                for(int col = 0; col < line.length(); col ++ ) {
                    char currentSymbol = representation[rowIndex+row][colIndex+col];
                    if( line.charAt(col) != ' ' && (currentSymbol == ' ' || currentSymbol == '*') ) {
                        representation[rowIndex+row][colIndex+col] = line.charAt(col);
                    }
                }
            }
        }
        List<String> gridRepresentation = new ArrayList<>();
        for(int line = 0; line < representation.length; line ++ ) {
            gridRepresentation.add(new String(representation[line]));
        }
        return gridRepresentation;
    }
    
    
    public void addKeyUpEvent(String key,Gadget gadget) {
        if (keyUpMap.containsKey(key)) {
            keyUpMap.get(key).add(gadget);
        }
        else {
            keyUpMap.put(key, new ArrayList<Gadget>(Arrays.asList(gadget)));
        }
    }
    
    public void addKeyDownEvent(String key,Gadget gadget){
        if (keyDownMap.containsKey(key)) {
            keyDownMap.get(key).add(gadget);
        }
        else {
            keyDownMap.put(key, new ArrayList<Gadget>(Arrays.asList(gadget)));
        }
    }
    
    private void triggerKeyUp(String key){
        //Check for left/right shift, cntrl, alt?
        //Check key name documentation - CHECKED NO LEFT/RIGHT
        if(keyUpMap.containsKey(key)){
            for (Gadget gadget:keyUpMap.get(key)){
                gadget.action();
            }
        }
    }

    private void triggerKeyDown(String key) {
        if(keyDownMap.containsKey(key)){
            for(Gadget gadget:keyDownMap.get(key)){
                gadget.action();
            }
        }
    }

    /**
     * Adds a ball to the board.
     *
     * @param ball The ball to add.
     *
     * @return Whether this board did not already contain the ball.
     */
    public boolean addBall(Ball ball) {
        return balls.add(ball) && addGameObject(ball);
    }

    public boolean addPortal(Portal portal) {
        return portals.add(portal) && addGameObject(portal);
    }

    /**
     * Removes a ball from the board.
     *
     * @param ball The ball to remove.
     *
     * @return Whether this board contained the ball.
     */
    public boolean removeBall(Ball ball) {
        return balls.remove(ball) && removeGameObject(ball);
    }

    /**
     * Connect a wall to the server.
     *
     * @param side The side of the board to connect.
     *
     * @param name The name of the board that this wall is being connected to.
     */
    private void connectWall(Wall.Side side, String name) {
        switch (side) {
            case TOP: topWall.connect(name); break;
            case BOTTOM: bottomWall.connect(name); break;
            case LEFT: leftWall.connect(name); break;
            case RIGHT: rightWall.connect(name); break;
        }
    }

    /**
     * Disconnect a wall from the server.
     *
     * @param side The side of the board to disconnect.
     */
    private void disconnectWall(Wall.Side side) {
        switch (side) {
            case TOP: topWall.disconnect(); break;
            case BOTTOM: bottomWall.disconnect(); break;
            case LEFT: leftWall.disconnect(); break;
            case RIGHT: rightWall.disconnect(); break;
        }
    }

    /**
     * Adds a game object to this board.
     *
     * @param gameObject The object to add.
     *
     * @return Whether this board did not contain the game object.
     */
    public boolean addGameObject(GameObject gameObject) {
        return gameObjects.add(gameObject);
    }

    /**
     * Removes a game object from this board.
     *
     * @param gameObject The object to remove.
     *
     * @return Whether this board contained the game object.
     */
    public boolean removeGameObject(GameObject gameObject) {
        return gameObjects.remove(gameObject);
    }
    
    /**
     * Simulate motions of objects for a specified time.
     *
     * This does not take collisions into account.
     *
     * @param time The time to simulate for.
     */
    private void simulate(double time) {
        for(GameObject gameObject: gameObjects) {
            gameObject.evolve(time);
        }
    }

    public double getFriction1() {
        return this.mu1;
    }

    public double getFriction2() {
        return this.mu2;
    }

    public double getGravity() {
        return this.gravity;
    }
    

    public void deTrigger() {
      for(GameObject gameObject: gameObjects){
          gameObject.deTrigger();
      }
    }
  
    //FOR TESTING PURPOSES ONLY-- no rep exposure, only PingballModel can call these methods
    public Map<String, List<Gadget>> getUpMap(){
    	return this.keyUpMap;
    }
    
    public Map<String, List<Gadget>> getDownMap(){
    	return this.keyDownMap;
    }
    
    public Set<Ball>getBalls(){
    	return this.balls;
    }
}
