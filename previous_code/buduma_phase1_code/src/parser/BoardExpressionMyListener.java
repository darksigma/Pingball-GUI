package parser;

public class BoardExpressionMyListener extends BoardExpressionBaseListener {

	@Override public void enterTriangleBumper(BoardExpressionParser.TriangleBumperContext ctx) { }
	@Override public void exitTriangleBumper(BoardExpressionParser.TriangleBumperContext ctx) {
		String triangleBumpName =  ctx.NAME().getText();
		
		double x = Double.valueOf(ctx.NUMBAH(0).getText());
		double y = Double.valueOf(ctx.NUMBAH(1).getText());
		int orientation = Double.valueOf(ctx.NUMBAH(2).getText()).intValue();
	}

	@Override public void enterAbsorber(BoardExpressionParser.AbsorberContext ctx) { }
	@Override public void exitAbsorber(BoardExpressionParser.AbsorberContext ctx) {
		String absorberNAme =  ctx.NAME().getText();
		
		double x = Double.valueOf(ctx.NUMBAH(0).getText());
		double y = Double.valueOf(ctx.NUMBAH(1).getText());
		double width = Double.valueOf(ctx.NUMBAH(2).getText());
		double height = Double.valueOf(ctx.NUMBAH(3).getText());
	}

	@Override public void enterBall(BoardExpressionParser.BallContext ctx) { }
	@Override public void exitBall(BoardExpressionParser.BallContext ctx) { 
		String absorberNAme =  ctx.NAME().getText();
		
		double x = Double.valueOf(ctx.NUMBAH(0).getText());
		double y = Double.valueOf(ctx.NUMBAH(1).getText());
		double xVel = Double.valueOf(ctx.NUMBAH(2).getText());
		double yVel = Double.valueOf(ctx.NUMBAH(3).getText());
	}

	@Override public void enterPremise(BoardExpressionParser.PremiseContext ctx) { }
	@Override public void exitPremise(BoardExpressionParser.PremiseContext ctx) { }

	@Override public void enterSquareBumper(BoardExpressionParser.SquareBumperContext ctx) { }
	@Override public void exitSquareBumper(BoardExpressionParser.SquareBumperContext ctx) {
		String squareBumpName =  ctx.NAME().getText();
		
		double x = Double.valueOf(ctx.NUMBAH(0).getText());
		double y = Double.valueOf(ctx.NUMBAH(1).getText());
	}

	@Override public void enterRoot(BoardExpressionParser.RootContext ctx) { }
	@Override public void exitRoot(BoardExpressionParser.RootContext ctx) { }

	@Override public void enterRightFlipper(BoardExpressionParser.RightFlipperContext ctx) { }
	@Override public void exitRightFlipper(BoardExpressionParser.RightFlipperContext ctx) { 
		String rightFlipperName =  ctx.NAME().getText();
		
		double x = Double.valueOf(ctx.NUMBAH(0).getText());
		double y = Double.valueOf(ctx.NUMBAH(1).getText());
		int orientation = Double.valueOf(ctx.NUMBAH(2).getText()).intValue();
	}

	@Override public void enterCircleBumper(BoardExpressionParser.CircleBumperContext ctx) { }
	@Override public void exitCircleBumper(BoardExpressionParser.CircleBumperContext ctx) { 
		String circleBumpName =  ctx.NAME().getText();
		
		double x = Double.valueOf(ctx.NUMBAH(0).getText());
		double y = Double.valueOf(ctx.NUMBAH(1).getText());
	}

	@Override public void enterBoard(BoardExpressionParser.BoardContext ctx) { }
	@Override public void exitBoard(BoardExpressionParser.BoardContext ctx) { }

	@Override public void enterFire(BoardExpressionParser.FireContext ctx) { }
	@Override public void exitFire(BoardExpressionParser.FireContext ctx) { 
		String trigger =  ctx.NAME(0).getText();
		String action =  ctx.NAME(1).getText();
	}

	@Override public void enterLeftFlipper(BoardExpressionParser.LeftFlipperContext ctx) { }
	@Override public void exitLeftFlipper(BoardExpressionParser.LeftFlipperContext ctx) { 
		String leftFlipperName =  ctx.NAME().getText();
		
		double x = Double.valueOf(ctx.NUMBAH(0).getText());
		double y = Double.valueOf(ctx.NUMBAH(1).getText());
		int orientation = Double.valueOf(ctx.NUMBAH(2).getText()).intValue();
	}
	
	@Override public void enterName(BoardExpressionParser.NameContext ctx) { }
	@Override public void exitName(BoardExpressionParser.NameContext ctx) { 
		String boardName =  ctx.NAME().getText();
	}
	
	@Override public void enterFriction1(BoardExpressionParser.Friction1Context ctx) { }
	@Override public void exitFriction1(BoardExpressionParser.Friction1Context ctx) { 
		double friction1 = Double.valueOf(ctx.NUMBAH().getText());
	}
	
	@Override public void enterFriction2(BoardExpressionParser.Friction2Context ctx) { }
	@Override public void exitFriction2(BoardExpressionParser.Friction2Context ctx) { 
		double friction2 = Double.valueOf(ctx.NUMBAH().getText());
	}
	
	@Override public void enterGravity(BoardExpressionParser.GravityContext ctx) { }
	@Override public void exitGravity(BoardExpressionParser.GravityContext ctx) { 
		double gravity = Double.valueOf(ctx.NUMBAH().getText());
	}

}
