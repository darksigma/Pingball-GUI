package parser;

import Ball;
import Board;
import Pair;
import Side;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class BoardGrammarCreatorListener extends BoardGrammarBaseListener{

    private String username = "";
    private double gravity = Board.DEFAULT_GRAVITY;
    private double mu = Board.DEFAULT_MU;
    private double mu2 = Board.DEFAULT_MU2;
    private Board gameBoard;
	
	
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
	}
	
	@Override 
	public void exitSqBumperLine(BoardGrammarParser.SqBumperLineContext ctx) { 
		String sqBmpName = ctx.NAME().getText();
        int sqBmpX = Double.valueOf(ctx.FLOAT(0).getText()).intValue();
        int sqBmpY = Double.valueOf(ctx.FLOAT(1).getText()).intValue();
	}

	@Override
	public void exitCirBumperLine(BoardGrammarParser.CirBumperLineContext ctx) {
		String cirBmpName = ctx.NAME().getText();
        int cirBmpX = Double.valueOf(ctx.FLOAT(0).getText()).intValue();
        int cirBmpY = Double.valueOf(ctx.FLOAT(1).getText()).intValue();
	}

	@Override 
	public void exitTriBumperLine(BoardGrammarParser.TriBumperLineContext ctx) {
		int angle = Double.valueOf(ctx.FLOAT(2).getText()).intValue();
        String triBmpAngle = "_" + Integer.toString(angle);

        String triBmpName = ctx.NAME().getText();
        int triBmpX = Double.valueOf(ctx.FLOAT(0).getText()).intValue();
        int triBmpY = Double.valueOf(ctx.FLOAT(1).getText()).intValue();
	}

	@Override 
	public void exitRtFlipLine(BoardGrammarParser.RtFlipLineContext ctx) { 
		int angle = Double.valueOf(ctx.FLOAT(2).getText()).intValue();
        String rtFlipAngle = "_" + Integer.toString(angle);

        String rtFlipName = ctx.NAME().getText();
        int rtFlipX = Double.valueOf(ctx.FLOAT(0).getText()).intValue();
        int rtFlipY = Double.valueOf(ctx.FLOAT(1).getText()).intValue();
	}

	@Override 
	public void exitLftFlipLine(BoardGrammarParser.LftFlipLineContext ctx) {
		int angle = Double.valueOf(ctx.FLOAT(2).getText()).intValue();
        String lftFlipAngle = "_" + Integer.toString(angle);

        String lftFlipName = ctx.NAME().getText();
        int lftFlipX = Double.valueOf(ctx.FLOAT(0).getText()).intValue();
        int lftFlipY = Double.valueOf(ctx.FLOAT(1).getText()).intValue();
	}

	@Override 
	public void exitAbsorberLine(BoardGrammarParser.AbsorberLineContext ctx) { 
		String absName = ctx.NAME().getText();
        int absX = Double.valueOf(ctx.FLOAT(0).getText()).intValue();
        int absY = Double.valueOf(ctx.FLOAT(1).getText()).intValue();
        int absWidth = Double.valueOf(ctx.FLOAT(2).getText()).intValue();
        int absHeight = Double.valueOf(ctx.FLOAT(3).getText()).intValue();
	}
	
	@Override
	public void exitFireLine(BoardGrammarParser.FireLineContext ctx) {
		String trigger = ctx.NAME(0).getText();
        String action = ctx.NAME(1).getText();
	}

}
