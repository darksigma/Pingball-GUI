package boardPhysics;


import java.util.Timer;
import java.util.TimerTask;

import physics.Vect;
import boardPhysics.Absorber;
import boardPhysics.Ball;
import boardPhysics.Board;
import boardPhysics.CircularBumper;
import boardPhysics.Flipper;
import boardPhysics.SquareBumper;
import boardPhysics.TriangularBumper;

/**
 * TODO: put documentation for your class here
 */
public class Main {
    
    /**
     * TODO: describe your main function's command line arguments here
     */
    public static void main(String[] args) {
        
    	final Board board = Board.newBoard(20,20);
		final Ball ball1 = new Ball("ball1", 5, 1, 0, 30, 0.5, 1);
		final Ball ball2 = new Ball("ball2", 4, 15, 10, -10, 0.5, 1);
		final Ball ball3 = new Ball("ball3", 7, 18, 50, -100, 0.5, 1);
		final SquareBumper s1 = new SquareBumper("s1", 5, 7);
		final CircularBumper c1 = new CircularBumper("s1", 2, 3);
		final CircularBumper c2 = new CircularBumper("s1", 17, 2);
		final TriangularBumper t1 = new TriangularBumper("t1", 3, 10, 270);
		final TriangularBumper t2 = new TriangularBumper("t2", 19, 0, 270);
		board.addBallToBoard(ball1);
		board.addBallToBoard(ball2);
		board.addBallToBoard(ball3);
		board.addGadgetToBoard(s1);
		board.addGadgetToBoard(t1);
		board.addGadgetToBoard(t2);
		board.addGadgetToBoard(c1);
		board.addGadgetToBoard(c2);
		
		final Absorber ab = new Absorber("ab", 0, 19, 19, 1, new Vect(0, -50));
		ab.connect(ab);
		s1.connect(ab);
		board.addGadgetToBoard(ab);
		
		final Flipper f1 = new Flipper("f1", 10, 8, true, 0);
		board.addGadgetToBoard(f1);
		s1.connect(f1);
		
		System.out.println(board.toString());

		//board.step(0.1);
		
		//System.out.println(5.5 < Double.POSITIVE_INFINITY);
		
//		System.out.println(board.toString());
//		System.out.println(ball1.getX() + " " + ball1.getY());
//		board.step(1);
//		System.out.println(board.toString());
// 		System.out.println(ball1.getX() + " " + ball1.getY());
		
		Timer t = new Timer();
    	TimerTask task= new TimerTask(){
    		public void run(){
    	    	board.step(0.05);
    			System.out.println(board.toString());
    		}
    	};
    	
    	t.scheduleAtFixedRate(task, 0, 50);
    	
    }
    
}