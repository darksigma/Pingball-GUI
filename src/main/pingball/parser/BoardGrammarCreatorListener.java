package pingball.parser;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import physics.Vect;
import pingball.simulation.Ball;
import pingball.simulation.Board;
import pingball.simulation.Constants;
import pingball.simulation.GridLocation;
import pingball.simulation.gadget.Absorber;
import pingball.simulation.gadget.CircleBumper;
import pingball.simulation.gadget.Flipper;
import pingball.simulation.gadget.Gadget;
import pingball.simulation.gadget.Portal;
import pingball.simulation.gadget.SquareBumper;
import pingball.simulation.gadget.TriangleBumper;
import pingball.util.StringUtils;

public class BoardGrammarCreatorListener extends BoardGrammarBaseListener{

	//keeps track of keys parsed and the gadgets that they trigger
	private Map<String, Gadget> gadgets = new HashMap<>();
	
	//represents possible keys that trigger gadgets
	private final String singleChar = "[a-z]";
	private final List<String> keys = new ArrayList<String>(Arrays.asList(
		    "shift", "ctrl", "alt", "meta",
			"space", "left", "right", "up", "down", "minus",
			"equals", "backspace", "openbracket", "closebracket",
			"backslash", "semicolon", "quote", "enter", "comma",
			"period", "slash", "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9"));
	
	private String username = "";
    private double gravity = Constants.DEFAULT_GRAVITY;
    private double mu = Constants.DEFAULT_FRICTION_MU1;
    private double mu2 = Constants.DEFAULT_FRICTION_MU2;
    private Board gameBoard;
    
    //Used for parsing a portal's connecting-portal in an optionally given board
    private boolean portalOtherBoard = false;
    private String otherBoardName = "";
	
	/**
	 * Returns the name of the board to be used in tests to make sure the name is correct through
     * the BoardGrammar class. There, only the listener that is created can call this method.
	 * @return String representing the name of the board
	 */
    public String listenerName(){
    	return this.username;    	
    }
    
    /**
     * Returns the value of gravity defined in a board. If it isn't defined, it takes the default
     * value of 25.0.
     * @return Double gravity of board
     */
    public Double listenerGravity(){
    	return this.gravity;
    }
    
    /**
     * Returns the value of mu defined in a board. If it isn't defined, it takes the default
     * value of 0.025
     * @return Double mu of board
     */
    public Double listenerFric1(){
    	return this.mu;
    }
    
    /**
     * Returns the value of mu2 defined in a board. If it isn't defined, it takes the default
     * value of 0.025
     * @return Double mu2 of board
     */
    public Double listenerFric2(){
    	return this.mu2;
    }
    
    /**
     * Returns the Board containing all the gadgets/game objects parsed in the given file
     * @return Board gameboard that has been created by the file
     */
    public Board listenerBoard(){
    	return this.gameBoard;
    }
    
    /**
     * Sets the gameBoard to the board that is passed in, which consists of only the 4 walls.
     * Gadgets that are parsed are added to this board
     * @param board that the gadgets are added to in order to create the gameBoard to play with
     */
    public void setListenerBoard(Board board) {
        this.gameBoard = board;
    }
    
    /**
     * Sets the name of the board to what is parsed in the file
     */
	@Override 
	public void exitBoardName(BoardGrammarParser.BoardNameContext ctx) { 
		String boardName = ctx.NAME().getText();
        this.username = boardName;
	}

	/**
	 * Sets the gravity of the board to what is parsed in the file, if there is a given gravity
	 * Otherwise, the board gravity defaults to 25.0
	 */
	@Override
	public void exitBoardGravity(BoardGrammarParser.BoardGravityContext ctx) { 
		double boardGravity = Double.valueOf(ctx.FLOAT().getText());
        this.gravity = boardGravity;
	}

    /**
     * Sets the friction1 (mu) value to what is parsed in the file, if there is a given mu
     * Otherwise, mu defaults to 0.025
     */
	@Override 
	public void exitBoardFric1(BoardGrammarParser.BoardFric1Context ctx) {
		double boardfric1 = Double.valueOf(ctx.FLOAT().getText());
        this.mu = boardfric1;
	}

    /**
     * Sets the friction2 (mu2) value to waht is parsed in the file, if it is given.
     * Otherwise, mu2 defaults to 0.025
     */
    @Override 
    public void exitBoardFric2(BoardGrammarParser.BoardFric2Context ctx) { 
		double boardfric2 = Double.valueOf(ctx.FLOAT().getText());
        this.mu2 = boardfric2;
	}

    /**
     * Adds a new ball to the board based on the parsed grid location coordinates, name, and velocity.
     */
	@Override 
	public void exitBallLine(BoardGrammarParser.BallLineContext ctx) { 
		String ballName = ctx.NAME().getText();
        double ballX = Double.valueOf(ctx.FLOAT(0).getText());
        double ballY = Double.valueOf(ctx.FLOAT(1).getText());
        double ballXvel = Double.valueOf(ctx.FLOAT(2).getText());
        double ballYvel = Double.valueOf(ctx.FLOAT(3).getText());
        
        Ball b = new Ball(new Vect(ballX, ballY), new Vect(ballXvel, ballYvel), ballName, gravity, mu, mu2);
        gameBoard.addBall(b);

	}
	
	/**
	 * Adds a new square bumper to the board based on the parsed name and grid location coordinates
	 */
	@Override 
	public void exitSqBumperLine(BoardGrammarParser.SqBumperLineContext ctx) { 
		String sqBmpName = ctx.NAME().getText();
        int sqBmpX = Double.valueOf(ctx.FLOAT(0).getText()).intValue();
        int sqBmpY = Double.valueOf(ctx.FLOAT(1).getText()).intValue();
        
        SquareBumper sb = new SquareBumper(gameBoard, sqBmpName, new GridLocation(sqBmpX, sqBmpY));
        gameBoard.addGameObject(sb);
        gadgets.put(sqBmpName, sb);
	}

	/**
	 * Adds a new circular bumper to the board based on the parsed name and grid location coordinates
	 */
	@Override
	public void exitCirBumperLine(BoardGrammarParser.CirBumperLineContext ctx) {
		String cirBmpName = ctx.NAME().getText();
        int cirBmpX = Double.valueOf(ctx.FLOAT(0).getText()).intValue();
        int cirBmpY = Double.valueOf(ctx.FLOAT(1).getText()).intValue();
        
        CircleBumper cb = new CircleBumper(gameBoard, cirBmpName, new GridLocation(cirBmpX, cirBmpY));
        gameBoard.addGameObject(cb);
        gadgets.put(cirBmpName, cb);
	}

	/**
	 * Adds a new triangular bumper to the board based on the given name, grid location coordinates,
	 * and orientation.
	 */
	@Override 
	public void exitTriBumperLine(BoardGrammarParser.TriBumperLineContext ctx) {
		int angle = Double.valueOf(ctx.FLOAT(2).getText()).intValue();

        String triBmpName = ctx.NAME().getText();
        int triBmpX = Double.valueOf(ctx.FLOAT(0).getText()).intValue();
        int triBmpY = Double.valueOf(ctx.FLOAT(1).getText()).intValue();
        
        TriangleBumper tb = new TriangleBumper(gameBoard, triBmpName, new GridLocation(triBmpX, triBmpY), Gadget.Orientation.of(angle));
        gameBoard.addGameObject(tb);
        gadgets.put(triBmpName, tb);
	}

	/**
	 * Adds right flipper to board based on parsed name, grid location coordinates, and orientation
	 */
	@Override 
	public void exitRtFlipLine(BoardGrammarParser.RtFlipLineContext ctx) { 
		int angle = Double.valueOf(ctx.FLOAT(2).getText()).intValue();

        String rtFlipName = ctx.NAME().getText();
        int rtFlipX = Double.valueOf(ctx.FLOAT(0).getText()).intValue();
        int rtFlipY = Double.valueOf(ctx.FLOAT(1).getText()).intValue();
        
        Flipper flipR = new Flipper(gameBoard, rtFlipName, new GridLocation(rtFlipX, rtFlipY), Flipper.FlipperType.RIGHT, Gadget.Orientation.of(angle));
        gameBoard.addGameObject(flipR);
        gadgets.put(rtFlipName, flipR);
	}

	/**
	 * Adds left flipper to board based on parsed name, grid location coordinates, and orientation
	 */
	@Override 
	public void exitLftFlipLine(BoardGrammarParser.LftFlipLineContext ctx) {
		int angle = Double.valueOf(ctx.FLOAT(2).getText()).intValue();

        String lftFlipName = ctx.NAME().getText();
        int lftFlipX = Double.valueOf(ctx.FLOAT(0).getText()).intValue();
        int lftFlipY = Double.valueOf(ctx.FLOAT(1).getText()).intValue();
        
        Flipper flipL = new Flipper(gameBoard, lftFlipName, new GridLocation(lftFlipX, lftFlipY), Flipper.FlipperType.LEFT, Gadget.Orientation.of(angle));
        gameBoard.addGameObject(flipL);
        gadgets.put(lftFlipName, flipL);
	}

	/**
	 * Adds absorber to board based on parsed name, grid location coordinates, and width and height
	 */
	@Override 
	public void exitAbsorberLine(BoardGrammarParser.AbsorberLineContext ctx) { 
		String absName = ctx.NAME().getText();
        int absX = Double.valueOf(ctx.FLOAT(0).getText()).intValue();
        int absY = Double.valueOf(ctx.FLOAT(1).getText()).intValue();
        int absWidth = Double.valueOf(ctx.FLOAT(2).getText()).intValue();
        int absHeight = Double.valueOf(ctx.FLOAT(3).getText()).intValue();
        
        Absorber a = new Absorber(gameBoard, absName, new GridLocation(absX, absY), absWidth, absHeight);
        gameBoard.addGameObject(a);
        gadgets.put(absName, a);
	}
	
	/**
	 * Adds a trigger/action to gadgets in the board.
	 */
	@Override
	public void exitFireLine(BoardGrammarParser.FireLineContext ctx) {
		String trigger = ctx.NAME(0).getText();
        String action = ctx.NAME(1).getText();
        
        gadgets.get(trigger).linkGadget(gadgets.get(action));
         
	}
	
	/**
	 * Adds a portal to the board based on parsed name, grid location coordinates, and
	 * connection-portal. A board name where the connection-portal can also be given, but if
	 * none is parsed, the connection-portal is located on the same board.
	 */
	@Override
	public void exitPortalLine(BoardGrammarParser.PortalLineContext ctx) { 
		String startPortal = ctx.NAME(0).getText();
		int x = Double.valueOf(ctx.FLOAT(0).getText()).intValue();
		int y = Double.valueOf(ctx.FLOAT(1).getText()).intValue();
		String endBoard = "";
		String endPortal = ctx.NAME(1).getText();
	
		Portal p;	
		if(this.portalOtherBoard){
			p = new Portal(gameBoard, startPortal, new GridLocation(x, y), this.otherBoardName, endPortal, false);
			endBoard = this.otherBoardName;
		}
		else{
			p = new Portal(gameBoard, startPortal, new GridLocation(x, y), this.username, endPortal, true);
			endBoard = this.username;
		}
		
		gameBoard.addPortal(p);
	}
	
	/**
	 * Gets the name of the optional board where the portal's connecting portal is located,
	 * given that it is located on a different board.
	 */
	@Override 
	public void exitPortalOtherBoard(BoardGrammarParser.PortalOtherBoardContext ctx) {
		String endBoard = ctx.NAME().getText();
		this.portalOtherBoard = true;
		this.otherBoardName = endBoard;
		
	}


	/**
	 * Adds a link between a parsed key and gadget so that if a key is released, the gadget
	 * performs its action. This is for non-numerical keys.
	 */
	@Override 
	public void exitKeyNameUpLine(BoardGrammarParser.KeyNameUpLineContext ctx) { 
		String keyName = ctx.NAME(0).getText();
		String action = ctx.NAME(1).getText();		
		Gadget g = gadgets.get(action);
		
		if(keys.contains(keyName) || keyName.matches(singleChar)){
			gameBoard.addKeyUpEvent(keyName, g);
		}
	
	}
	
	/**
	 * Adds a link between a parsed key and gadget so that if a key is pressed, the gadget
	 * performs its action. This is for non-numerical keys.
	 */
	@Override 
	public void exitKeyNameDownLine(BoardGrammarParser.KeyNameDownLineContext ctx) {
		String keyName = ctx.NAME(0).getText();
		String action = ctx.NAME(1).getText();
		Gadget g = gadgets.get(action);
		
		if(keys.contains(keyName) || keyName.matches(singleChar)){
			gameBoard.addKeyDownEvent(keyName, g);
		}
	}
	
	/**
	 * Adds a link between a parsed key and gadget so that if a key is released, the gadget
	 * performs its action. This is for numerical keys.
	 */
	@Override
	public void exitKeyIntUpLine(BoardGrammarParser.KeyIntUpLineContext ctx) { 
		String keyName = ctx.FLOAT().getText();
		String action = ctx.NAME().getText();
		Gadget g = gadgets.get(action);
		
		if(keys.contains(keyName)){
			gameBoard.addKeyUpEvent(keyName, g);
		}
	}

	/**
	 * Adds a link between a parsed key and gadget so that if a key is pressed, the gadget
	 * performs its action. This is for numerical keys.
	 */
	@Override
	public void exitKeyIntDownLine(BoardGrammarParser.KeyIntDownLineContext ctx) {
		String keyName = ctx.FLOAT().getText();
		String action = ctx.NAME().getText();
		Gadget g = gadgets.get(action);
		
		if(keys.contains(keyName)){
			gameBoard.addKeyDownEvent(keyName, g);
		}
	}
	
	/**
	 * Adds a link between the 'x' key and a gadget so that if 'x' is released, the gadget
	 * performs its action.
	 */
	@Override 
	public void exitKeyXUpLine(BoardGrammarParser.KeyXUpLineContext ctx) {
		String keyName = "x";
		String action = ctx.NAME().getText();
		Gadget g = gadgets.get(action);
		
		if(keys.contains(keyName)){
			gameBoard.addKeyUpEvent(keyName, g);
		}
	}
	
	/**
	 * Adds a link between the 'x' key and a gadget so that if 'x' is pressed, the gadget
	 * performs its action.
	 */
	@Override public void exitKeyXDownLine(BoardGrammarParser.KeyXDownLineContext ctx) {
		String keyName = "x";
		String action = ctx.NAME().getText();
		Gadget g = gadgets.get(action);
		
		if(keys.contains(keyName)){
			gameBoard.addKeyDownEvent(keyName, g);
		}
	}

	/**
	 * Adds a link between the 'y' key and a gadget so that if 'y' is released, the gadget
	 * performs its action.
	 */
	@Override public void exitKeyYUpLine(BoardGrammarParser.KeyYUpLineContext ctx) { 
		String keyName = "y";
		String action = ctx.NAME().getText();
		Gadget g = gadgets.get(action);
		
		if(keys.contains(keyName)){
			gameBoard.addKeyUpEvent(keyName, g);
		}
	}

	/**
	 * Adds a link between the 'y' key and a gadget so that if 'y' is pressed, the gadget
	 * performs its action.
	 */
	@Override public void exitKeyYDownLine(BoardGrammarParser.KeyYDownLineContext ctx) { 
		String keyName = "y";
		String action = ctx.NAME().getText();
		Gadget g = gadgets.get(action);
		
		if(keys.contains(keyName)){
			gameBoard.addKeyDownEvent(keyName, g);
		}
	}
	
	/**
	 * Adds a ball spawner gadget to the board, based on parsed name and gridlocation coordinates.
	 * This is an extra feature/gadget.
	 */
	@Override 
	public void exitBallSpawnerLine(BoardGrammarParser.BallSpawnerLineContext ctx) { 
		String ballSpawnerName = ctx.NAME().getText();
        int ballSpawnerX = Double.valueOf(ctx.FLOAT(0).getText()).intValue();
        int ballSpawnerY = Double.valueOf(ctx.FLOAT(1).getText()).intValue();
        
        BallSpawner bs = new BallSpawner(gameBoard, ballSpawnerName, new GridLocation(ballSpawnerX, ballSpawnerY));
        gameBoard.addGameObject(bs);
        gadgets.put(ballSpawnerName, bs);
	}


}
