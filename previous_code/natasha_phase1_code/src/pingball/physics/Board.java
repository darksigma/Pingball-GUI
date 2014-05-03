package pingball.physics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import physics.Geometry;
import physics.Geometry.VectPair;
import physics.Vect;
import pingball.datastructures.Pair;
import pingball.datastructures.Side;

/**
 * A board represents the playing area for a game of Pingball. Boards are
 * defined to have sizes of 20 x 20 for the playing area with an outer wall
 * surrounding this 20 x 20 area. By default, a board has gravity set to
 * 25 L/sec^2 and two friction constants set to .025 1/sec and .025 1/L.
 * However, the gravity of the board may be changed. By definition, due to the
 * standards of the graphics community, the upper left corner of a board is
 * location (0, 0) while the bottom right corner of the board is location (20,
 * 20).
 * <br> **************************THREAD SAFETY ARGUMENT***************************<br>
 *  All gadgets and balls that are used in a board are internally contained.
 *  To add a gadget or ball to a board, the parameters that would be needed
 *  for construction are passed in and then an internal variant of said
 *  objects are created for usage. This ensures that references to any internal
 *  data types are completely contained within an instance of board. Moreover,
 *  all publicly accessible methods that are sensitive to mutations are
 *  synchronized about the instance of the board. This ensures that all mutator
 *  methods obtain a lock on the instance and perform all of their operations
 *  atomically before allowing anything else to happen to the board. This allows
 *  multiple threads to handle a board instance whether the threads are issuing
 *  observing, mutating or both types of commands. The main argument is
 *  containment and synchronization. The only data type that this doesn't apply
 *  to is the linked blocking queue that is used to tell the server that a
 *  ball needs to get sent to another board. In this case, the queue blocks
 *  safely, so that the listening end will not do anything until the board
 *  sends the ball out. Furthermore, once the ball gets sent out, it is deleted
 *  from the board entirely, and hence doesn't pose a potential leak in memory.
 *  If you look at the client code which contains this board, you'll notice that
 *  the ball itself isn't sent out and only a text version that contains only
 *  necessary data for ball construction is sent across threads, this allows
 *  the ball to be ultimately contained within the thread anyways.
 *
 * @author enguyen, nconsul, spefley
 *
 */
public class Board {

    /* REP Invariant: Boards all have internal names. However "Named" boards
     * that can be connected to one another must have a name that is a letter
     * or underscore followed by optional letters, numbers, and underscores.
     * Unnamed boards have an empty string as their name. The gravity and '
     * friction values must also be non-negative for the board to simulate real
     * world physics. Lastly no internal gadgets can have their bounding boxes
     * overlap.
     *
     * 1) name matches [A-Za-z_][A-Za-z_0-9]* | ""
     * 2) gravity, mu, mu2 >=0
     * 3) non-overlapping bounding boxes
     */

    /**
     * The default value of the gravity for this board in units of L/sec^2
     */
    public static final double DEFAULT_GRAVITY;

    /**
     * Default value of the first friction constant in units of 1/sec
     */
    public static final double DEFAULT_MU;

    /**
     * Default value of the second friction constant in units of 1/L
     */
    public static final double DEFAULT_MU2;

    /**
     * The playing field of a board must be 20 L wide
     */
    public static final int PLAYING_FIELD_WIDTH;

    /**
     * The playing field of a board must be 20 L high
     */
    public static final int PLAYING_FIELD_HEIGHT;

    /**
     * The default speed at which a ball is shot out of an absorber in
     * L/sec
     */
    public static final Vect DEFAULT_ABSORBER_EJECTION_VELOCITY;

    /**
     * The actual height of the board including external walls
     */
    private static final int BUFFERED_FIELD_HEIGHT;

    /**
     * The actual width of the board including external walls
     */
    private static final int BUFFERED_FIELD_WIDTH;

    //Initialize the class constants
    static {
        DEFAULT_GRAVITY = 25;           // in units of L/sec^2
        DEFAULT_MU = 0.025;             // in units of 1/sec
        DEFAULT_MU2 = 0.025;            // in units of 1/L
        PLAYING_FIELD_WIDTH = 20;       // in units of L
        PLAYING_FIELD_HEIGHT = 20;      // in units of L
        BUFFERED_FIELD_HEIGHT = 22;     // in units of L
        BUFFERED_FIELD_WIDTH = 22;      // in units of L
        DEFAULT_ABSORBER_EJECTION_VELOCITY = new Vect(0, -50); // in units of L/sec
    }

    /**
     * Stores the names of the gadgets mapped to the actual gadget
     */
    private final Map<String, Gadget> gadgets;

    /**
     * The set of balls bouncing around in this board
     */
    private final Map<String, Ball> balls;

    /**
     * Stores a list of the gadgets that are currently active and performing
     * an action
     */
    private final List<Gadget> activeGadgets;

    /**
     * Stores the list of deactivated gadgets
     */
    private final List<Gadget> deactivatedGadgets;

    /**
     * The queue that will be used to send balls to the server when a ball
     * should move to another board
     */
    private final LinkedBlockingQueue<Pair<Ball, Pair<String, Side>>> outGoingBalls;

    /**
     * The actual gravity of this board
     */
    private final double gravity;

    /**
     * The actual value of the first friction constant in units of 1/sec
     */
    private final double mu;

    /**
     * The actual value of the second friction constant in units of 1/L
     */
    private final double mu2;

    /**
     * Stores the name of this board if it has one
     */
    private final String name;

    /**
     * The outer walls of this board
     */
    private final OuterWalls walls;

    /**
     * Internal constructor that builds the board object based on desired
     * parameters. The board is empty and will contain the desired gravity
     * and friction constants.
     * @param name the name of this board, valid iff {@code named} is true
     * @param gravity the desired gravitational acceleration in the board
     * @param mu the desired friction constant in units of 1/sec
     * @param mu2 the desired friction constant in units of 1/L
     * @param out a queue to the server that will be used to send balls that
     *  need to move to another board over.
     */
    public Board(String name, double gravity, double mu, double mu2,
                 LinkedBlockingQueue<Pair<Ball, Pair<String, Side>>> out) {
        //setup the board constants
        this.name = name;
        this.gravity = gravity;
        this.mu = mu;
        this.mu2 = mu2;

        //setup the data structures
        this.outGoingBalls = out;
        this.gadgets = new HashMap<>();
        this.balls = new HashMap<>();
        this.activeGadgets = new ArrayList<>();
        this.deactivatedGadgets = new ArrayList<>();

        //Create a wall with an "illegal" name so that no other gadgets can have
        //name collisions with this one
        this.walls = new OuterWalls(this, "*wall*");
        this.gadgets.put(this.walls.getName(), this.walls);

        checkRep();
    }

    /**
     * Returns the name of this board. If unnamed, an empty string is returned
     * @return the name of the board
     *
     */
    public String getUsername() {
        return name;
    }

    /**
     * Creates and ads a square shaped bumper to the board with the given
     * parameters and with a reflection coefficient of 1.0.
     * @param name the name of the bumper, must be unique among all named objects
     *       in the board
     * @param x the x coordinate of the upper left corner of this bumper,
     *      0 <= x < 20 in units of L
     * @param y the y coordinate of the upper left corner of this bumper,
     *      0 <= y < 20 in units of L
     */
    public synchronized void addSquareBumper(String name, int x, int y) {
        Gadget gadget = new SquareBumper(this, name, x, y);
        gadgets.put(name, gadget);
        checkRep();

    }

    /**
     * Creates and adds a new triangle shaped bumper to the board based with the
     * given parameters and with coefficient of reflection equal to 1.0.
     * @param name the name of the bumper, must be unique among all named objects
     *      in the board
     * @param x the x coordinate of the upper left corner of this bumper,
     *      0 <= x < 20 in units of L
     * @param y the y coordinate of the upper left corner of this bumper,
     *      0 <= y < 20 in units of L
     * @param orientation the orientation clockwise in degrees from the
     *      horizontal that this bumper is initialized in.
     */
    public synchronized void addTriangularBumper(String name, int x, int y, Orientation orientation) {
        Gadget gadget = new TriangularBumper(this, name, x, y, orientation);
        gadgets.put(name, gadget);
        checkRep();

    }

    /**
     * Creates and adds a new circle shaped bumper to the board based with the
     * given parameters and with coefficient of reflection equal to 1.0.
     * @param name the name of the bumper, must be unique among all named objects
     *      in the board
     * @param x the x coordinate of the upper left corner of this bumper,
     *      0 <= x < 20 in units of L
     * @param y the y coordinate of the upper left corner of this bumper,
     *      0 <= y < 20 in units of L
     */
    public synchronized void addCircleBumper(String name, int x, int y) {
        Gadget gadget = new CircleBumper(this, name, x, y);
        gadgets.put(name, gadget);
        checkRep();

    }

    /**
     * Creates and adds a new absorber to the board based with the given
     * parameters and with coefficient of reflection equal to 1.0.
     * @param name the name of the absorber, must be unique among all named
     *      objects in the board
     * @param x the x coordinate of the upper left corner of this absorber,
     *      0 <= x + width <= 20 in units of L
     * @param y the y coordinate of the upper left corner of this absorber,
     *      0 <= y + width <= 20 in units of L
     * @param width the desired width of this absorber, 1 <= width in units of L
     * @param height the desired height of this absorber, 1 <= height in units
     *      of L
     */
    public synchronized void addAbsorber(String name, int x, int y, int width, int height) {
        Gadget gadget = new Absorber(this, name, x, y, width, height);
        gadgets.put(name, gadget);
        checkRep();

    }

    /**
     * Creates and adds a new left flipper to the board based with the given
     * parameters and with coefficient of reflection equal to 1.0. The '2'
     * in the requirements below takes into account the size 2L x 2L bounding
     * box for flippers
     * @param name the name of the flipper, must be unique among all named
     *      objects in the board
     * @param x the x coordinate of the upper left corner of this flipper,
     *      0 <= x + 2 <= 20 in units of L
     * @param y the y coordinate of the upper left corner of this flipper,
     *      0 <= y + 2 <= 20 in units of L
     * @param orientation the orientation clockwise in degrees from the
     *      horizontal that this flipper is initialized in.
     */
    public synchronized void addLeftFlipper(String name, int x, int y, Orientation orientation) {
        Gadget gadget = new Flipper(this, name, x, y, orientation, true);
        gadgets.put(name, gadget);
        checkRep();

    }

    /**
     * Creates and adds a new right flipper to the board based with the given
     * parameters and with coefficient of reflection equal to 1.0. The '2'
     * in the requirements below takes into account the size 2L x 2L bounding
     * box for flippers
     * @param name the name of the flipper, must be unique among all named
     *      objects in the board
     * @param x the x coordinate of the upper left corner of this flipper,
     *      0 <= x + 2 <= 20 in units of L
     * @param y the y coordinate of the upper left corner of this flipper,
     *      0 <= y + 2 <= 20 in units of L
     * @param orientation the orientation clockwise in degrees from the
     *      horizontal that this flipper is initialized in.
     */
    public synchronized void addRightFlipper(String name, int x, int y, Orientation orientation) {
        Gadget gadget = new Flipper(this, name, x, y, orientation, false);
        gadgets.put(name, gadget);
        checkRep();

    }

    /**
     * Connect two gadgets together such that the events produced by the gadget
     * of {@code triggerName} are consumed by the gadget of {@code targetName}
     * @param triggerName the name of the gadget that will produce the events
     * @param targetName the name of the gadget that will consume the events
     */
    public synchronized void connect(String triggerName, String targetName) {
        Gadget trigger = gadgets.get(triggerName);
        Gadget target = gadgets.get(targetName);

        trigger.connect(target);
        checkRep();
    }

    /**
     * Adds a ball to this board with the specified parameters and with a
     * default diameter of 0.5 L. The .25 below refers to the radius of the ball
     * @param name the name of the ball, must be unique across all named objects
     *      in the board
     * @param x the x coordinate of the upper left corner of this ball,
     *      0 <= x + .25 <= 20 in units of L
     * @param y the y coordinate of the upper left corner of this ball,
     *      0 <= y + .25 <= 20 in units of L
     * @param vX the horizontal velocity of the ball in units of L/sec
     * @param vY the vertical velocity of the ball in units of L/sec
     */
    public synchronized void addBall(String name, double x, double y, double vX, double vY) {
        //force the x, y to fit into the board nicely
        double bX = x, bY = y;
        while(bX < -Ball.DEFAULT_DIAMETER / 2) {
            bX += 20;
        }
        while(bX > 20 - Ball.DEFAULT_DIAMETER / 2) {
            bX -= 20;
        }
        while(bY < -Ball.DEFAULT_DIAMETER / 2) {
            bY += 20;
        }
        while(bY > 20 - Ball.DEFAULT_DIAMETER / 2) {
            bY -= 20;
        }
        Ball ball = new Ball(name, vX, vY, bX, bY, gravity, mu, mu2);
        balls.put(name, ball);
        checkRep();
    }

    /**
     * Adds a ball to this board with the specified tokens. Tokens must be
     * in the form ["BALL", vx, vy, x, y, name]
     * @param tokens the strings that describe the ball in the above format
     */
    public synchronized void addBall(String[] tokens) {
        double vx = Double.parseDouble(tokens[1]);
        double vy = Double.parseDouble(tokens[2]);
        double x = Double.parseDouble(tokens[3]);
        double y = Double.parseDouble(tokens[4]);
        String name = tokens[5];
        addBall(name, x, y, vx, vy);
        checkRep();
    }

    /**
     * Connects a board to this board on the designated side.
     * @param boardName the name of th board to be connected to this
     * @param side the side on which the board should be connected
     * @return the name of the board previously connected on the specified side,
     *      empty if no board was there
     */
    public synchronized String connectBoard(String boardName, Side side) {
        String out = walls.connectBoard(boardName, side);
        checkRep();
        return out;
    }

    /**
     * Removes the board connected to the specified side
     * @param boardName the name of the board that needs to be disconnected
     *      from this board
     * @param side the side that the designated board is supposedly on to be
     *      disconnected
     * @return the name of the disconnected board, or an empty string if no board
     *      was disconnected
     */
    public synchronized String disconnectBoard(String boardName) {
        String out = walls.disconnectBoard(boardName);
        checkRep();
        return out;
    }

    /**
     * the depth of the recursive stack from update
     */
    private int recursiveDepth;

    /**
     * Updates the board by a time dt
     * @param dt the time that the board should be updated by
     * @return a String representation of the board after the update
     */
    public synchronized String update(double dt) {
        if(recursiveDepth > 50) { //set a hard limit on how deep we can go

            return toString();
        }

        //There aren't any balls in here, so there can't be any collisions
        if(balls.size() == 0) {
            return toString();
        }

        //calculate the earliest collisions.
        Pair<Double, Pair<Gadget, Ball>> earliestGadgetCollision
            = findEarliestGadgetCollision();
        Pair<Double, Pair<Ball, Ball>> earliestBallCollision
            = findEarliestBallBallCollision();

        //find the actual min time
        double minCollisionTime = Math.min(earliestGadgetCollision.first(),
                                           earliestBallCollision.first());
        double minTime = Math.min(dt, minCollisionTime);

        // decrease the time by a miniscule fraction to try to prevent the ball
        // from jamming into it's target. If the minTime is the total update
        // time, then it doesn't hit anything
        if(minTime > 0 && minTime< dt) {
            minTime -= Math.ulp(minTime);
        }


        //perform whatever action is needed to update the ball
        for(String ballName : balls.keySet()) {
            Ball ball = balls.get(ballName);
            // all captured balls will be handled by the capturing absorber
            if(!ball.isCaptured()) {
                balls.get(ballName).update(minTime);
            }
        }

        //perform whatever action is needed
        for(Gadget gadget : activeGadgets) {
            gadget.action(minTime);
        }

        //clear the deactivated gadgets
        clearDeactiveList();

        //collision didn't happen in this frame
        if(dt < earliestGadgetCollision.first() && dt < earliestBallCollision.first()) {
            return toString();
        }

        double collisionTime;
        if(earliestGadgetCollision.first() < earliestBallCollision.first()) { //ball - gadget collisions happen first
            //extract data
            collisionTime = earliestGadgetCollision.first();
            Gadget gadget = earliestGadgetCollision.second().first();
            Ball ball = earliestGadgetCollision.second().second();

            //deat with whatever updates are necessary
            handleGadgetCollision(gadget, ball);
        } else {// ball-ball collision happens first or at same time as gadget collision event
            //extract data
            collisionTime = earliestBallCollision.first();
            Ball ball1 = earliestBallCollision.second().first();
            Ball ball2 = earliestBallCollision.second().second();

            //deal with whatever updates are necessary
            handleBallBallCollision(ball1, ball2);
        }

        // keep updating the system
        recursiveDepth++;
        String out = update(dt - collisionTime);
        recursiveDepth--;
        //check rep at highest level
        if(recursiveDepth == 0) {
            checkRep();
        }
        return out;
    }

    /**
     * Deals with reflecting the balls after impact.
     * @param ball1 a ball in the collision
     * @param ball2 another ball in the collision
     */
    private void handleBallBallCollision(Ball ball1, Ball ball2) {
        VectPair newVelocities = Geometry.reflectBalls(
                                     ball1.getCenterAsVect(), 1, ball1.getVelocityVector(),
                                     ball2.getCenterAsVect(), 1, ball2.getVelocityVector());

        ball1.setVelocity(newVelocities.v1);
        ball2.setVelocity(newVelocities.v2);
        checkRep();
    }

    /**
     * Deals with reflecting a ball after hitting a gadget and telling the
     * gadget that it's been hit.
     * @param gadget
     * @param ball
     */
    private void handleGadgetCollision(Gadget gadget, Ball ball) {
        gadget.handleCollision(ball);
        checkRep();
    }

    /**
     * Removes the specified ball from the board and sends it to the server
     * @param b the ball to be sent to the server
     */
    void sendBallToServer(Ball b, Side side) {
        balls.remove(b.getName());
        try {
            outGoingBalls.put(new Pair<>(b, new Pair<String, Side>(name, side)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        checkRep();
    }

    /**
     * Finds the earliest time where a ball and a gadget will collide and
     * returns a struct containing that earliest time along with the gadget
     * and ball in the collision
     * @return a struct containing all necessary data about the mintime collision
     */
    private Pair<Double, Pair<Gadget, Ball>> findEarliestGadgetCollision() {
        String[] gadgetNames = gadgets.keySet().toArray(new String[gadgets.size()]);
        String[] ballNames = balls.keySet().toArray(new String[balls.size()]);
        //Default min time is positive infinity
        Double minTime = Double.POSITIVE_INFINITY;
        //default return value is infinity with nonexistent balls
        Pair<Double, Pair<Gadget, Ball>> ret = new Pair<>(minTime,
                new Pair<Gadget, Ball>(null, null));

        //for every ball, calculate the collision with every gadget
        for(int i = 0; i < ballNames.length; i++) {

            Ball ball = balls.get(ballNames[i]);
            if(ball.isCaptured()) { //skip balls absorbed by absorbers
                continue;
            }

            for(int j = 0; j < gadgetNames.length; j++) {
                Gadget gadget = gadgets.get(gadgetNames[j]);
                double time = gadget.calculateCollisionTimeWithBall(ball);
                if(!gadget.equals(walls) && time <= 1E-6) {
                    continue;
                }
                //every time we find an earlier collision time, update
                if(time < minTime) {
                    minTime = time;
                    ret = new Pair<>(minTime, new Pair<Gadget, Ball>(gadget, ball));
                }
            }
        }

        return ret;
    }

    /**
     * Finds the earliest time where two balls would collide. This is of course
     * assuming that the board is empty. Calculating collisions with gadgets
     * on the board happen elsewhere.
     * @return struct holding the time of the collision along with the two balls
     *  partaking in the collision.
     */
    private Pair<Double, Pair<Ball, Ball>> findEarliestBallBallCollision() {
        String[] allBallNames = balls.keySet().toArray(new String[balls.size()]);

        //Default min time is positive infinity
        Double minTime = Double.POSITIVE_INFINITY;
        //default return value is infinity with nonexistent balls
        Pair<Double, Pair<Ball, Ball>> ret = new Pair<>(minTime,
                new Pair<Ball, Ball>(null, null));

        //for every pair of balls, note if less than 2 balls exist, loop never
        //happens
        for(int i = 0; i < allBallNames.length; i++) {
            Ball ball1 = balls.get(allBallNames[i]);
            if(ball1.isCaptured()) { //skip balls that are captured
                continue;
            }

            for(int j = i + 1; j < allBallNames.length; j++) {
                Ball ball2 = balls.get(allBallNames[j]);
                if(ball2.isCaptured()) { //skip balls that are captured
                    continue;
                }

                double time = Geometry.timeUntilBallBallCollision(
                                  ball1.asCircle(), ball1.getVelocityVector(),
                                  ball2.asCircle(), ball2.getVelocityVector());
                //every time we find an earlier collision time, update
                if(time < minTime) {
                    minTime = time;
                    ret = new Pair<>(minTime, new Pair<Ball, Ball>(ball1, ball2));
                }
            }
        }

        return ret;
    }

    /**
     * Add a gadget to this board's active gadgets list.
     * @param gadget the gadget that should be added to the active list
     */
    void addToActiveList(Gadget gadget) {
        if(!activeGadgets.contains(gadget))
            activeGadgets.add(gadget);
        checkRep();
    }

    /**
     * Queues the gadget for removal from the set of active gadgets
     * @param gadget
     */
    void addToDeactivatedList(Gadget gadget) {
        if(!deactivatedGadgets.contains(gadget))
            deactivatedGadgets.add(gadget);
        checkRep();
    }

    /**
     * Clears the list of deactivated gadgets from the active list
     */
    private void clearDeactiveList() {
        for(Gadget gadget : deactivatedGadgets) {
            activeGadgets.remove(gadget);
        }
        deactivatedGadgets.clear();
        checkRep();
    }

    /**
     * Returns a string version of the board.
     */
    public synchronized String toString() {
        char[][] out = new char[BUFFERED_FIELD_HEIGHT][BUFFERED_FIELD_WIDTH];
        //fill it with spaces
        for(char[] row : out) {
            for(int j = 0; j < row.length; j++) {
                row[j] = ' ';
            }
        }
        //place gadgets in the board
        for(String gadgetName: gadgets.keySet()) {
            gadgets.get(gadgetName).putInDisplay(out, 1, 1);
        }

        //place balls into the board
        for(String ballName : balls.keySet()) {
            balls.get(ballName).putInDisplay(out, 1, 1);
        }

        //create a string from it
        StringBuilder b = new StringBuilder();
        for(char[] row : out) {
            b.append(row).append("\n");
        }

        return b.toString();
    }

    /** REP Invariant: Boards all have internal names. However "Named" boards
     * that can be connected to one another must have a name that is a letter
     * or underscore followed by optional letters, numbers, and underscores.
     * Unnamed boards have an empty string as their name. The gravity and '
     * friction values must also be non-negative for the board to simulate real
     * world physics. Lastly no internal gadgets can have their bounding boxes
     * overlap.
     *
     * 1) name matches [A-Za-z_][A-Za-z_0-9]* | ""
     * 2) gravity, mu, mu2 >=0
     * 3) non-overlapping bounding boxes
     */
    private boolean checkRep() {
        assert name.equals("") || name.matches("[A-Za-z_][A-Za-z_0-9]*");
        assert gravity >= 0 && mu >= 0 && mu2 >= 0;
        //mark where gadgets are
        int[][] gadgetsInLoc = new int[20][20];
        for(String gadgetName : gadgets.keySet()) {
            Gadget gadget = gadgets.get(gadgetName);
            if(gadget.equals(walls)) { // ignore the walls
                continue;
            }
            for(int i = gadget.getY(); i < gadget.getY() + gadget.getHeight(); i++) {
                for(int j = gadget.getX(); j < gadget.getX() + gadget.getWidth(); j++) {
                    gadgetsInLoc[i][j] ++;
                }
            }
        }
        //each location can have a value of at most 1
        for(int i = 0; i < gadgetsInLoc.length; i++) {
            for(int j = 0; j < gadgetsInLoc[0].length; j++) {
                assert gadgetsInLoc[i][j] < 2;
            }
        }
        return true;
    }
}
