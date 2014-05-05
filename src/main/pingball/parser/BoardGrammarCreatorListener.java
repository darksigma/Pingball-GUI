package pingball.parser;


import java.util.HashMap;
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
import pingball.simulation.gadget.SquareBumper;
import pingball.simulation.gadget.TriangleBumper;


public class BoardGrammarCreatorListener extends BoardGrammarBaseListener{

	private Map<String, Gadget> gadgets = new HashMap<>();
	
	private String username = "";
    private double gravity = Constants.DEFAULT_GRAVITY;
    private double mu = Constants.DEFAULT_FRICTION_MU1;
    private double mu2 = Constants.DEFAULT_FRICTION_MU2;
    private Board gameBoard;
	
	
    public String listenerName(){
    	return this.username;
    }
    
    public Double listenerGravity(){
    	return this.gravity;
    }
    
    public Double listenerFric1(){
    	return this.mu;
    }
    
    public Double listenerFric2(){
    	return this.mu2;
    }
    
    public Board listenerBoard(){
    	return this.gameBoard;
    }
    
    public void setListenerBoard(Board board) {
        this.gameBoard = board;
    }
    
	@Override 
	public void exitBoardName(BoardGrammarParser.BoardNameContext ctx) { 
		String boardName = ctx.NAME().getText();
        this.username = boardName;
	}

	@Override
	public void exitBoardGravity(BoardGrammarParser.BoardGravityContext ctx) { 
		double boardGravity = Double.valueOf(ctx.FLOAT().getText());
        this.gravity = boardGravity;
	}

    
	@Override 
	public void exitBoardFric1(BoardGrammarParser.BoardFric1Context ctx) {
		double boardfric1 = Double.valueOf(ctx.FLOAT().getText());
        this.mu = boardfric1;
	}

    
    @Override 
    public void exitBoardFric2(BoardGrammarParser.BoardFric2Context ctx) { 
		double boardfric2 = Double.valueOf(ctx.FLOAT().getText());
        this.mu2 = boardfric2;
	}
    

	@Override
	public void exitBoardLine(BoardGrammarParser.BoardLineContext ctx) { 
		
	}
    
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
	
	@Override 
	public void exitSqBumperLine(BoardGrammarParser.SqBumperLineContext ctx) { 
		String sqBmpName = ctx.NAME().getText();
        int sqBmpX = Double.valueOf(ctx.FLOAT(0).getText()).intValue();
        int sqBmpY = Double.valueOf(ctx.FLOAT(1).getText()).intValue();
        
        SquareBumper sb = new SquareBumper(gameBoard, sqBmpName, new GridLocation(sqBmpX, sqBmpY));
        gameBoard.addGameObject(sb);
        gadgets.put(sqBmpName, sb);
	}

	@Override
	public void exitCirBumperLine(BoardGrammarParser.CirBumperLineContext ctx) {
		String cirBmpName = ctx.NAME().getText();
        int cirBmpX = Double.valueOf(ctx.FLOAT(0).getText()).intValue();
        int cirBmpY = Double.valueOf(ctx.FLOAT(1).getText()).intValue();
        
        CircleBumper cb = new CircleBumper(gameBoard, cirBmpName, new GridLocation(cirBmpX, cirBmpY));
        gameBoard.addGameObject(cb);
        gadgets.put(cirBmpName, cb);
	}

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
	
	@Override
	public void exitFireLine(BoardGrammarParser.FireLineContext ctx) {
		String trigger = ctx.NAME(0).getText();
        String action = ctx.NAME(1).getText();
        
        gadgets.get(trigger).linkGadget(gadgets.get(action));
        
        
	}

}
