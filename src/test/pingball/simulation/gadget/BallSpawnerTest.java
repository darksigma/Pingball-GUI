package pingball.simulation.gadget;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.Vect;
import pingball.simulation.Ball;
import pingball.simulation.Board;
import pingball.simulation.GridLocation;
import pingball.simulation.collidable.Collidable;
import pingball.util.Pair;

/*
 * Testing Strategy:
 *
 * Partitioning based on 
 * 1. Location of ballspawner on board
 * 2. Triggering each possible collidable in it.
 * 3. Colliding zero, one or multiple balls
 * 
 * We check the working of:
 * 1. The constructor
 * 2. The action
 * 3. The gridRepresentation
 * 4. The getLocation method
 * 5. The timeUntil collision method
 * 6. The collide method.
 */

public class BallSpawnerTest {

	private static Vect center = new Vect(4,4);
    private static Vect velocity = new Vect(-1,-1);
    
    private static BlockingQueue<String> sendQueue;    
    private static Board board;
    private static Ball ball;
    private static File file;
    
    @BeforeClass public static void setUpBeforeClass() throws IOException {
        file = new File("src/test-resources/blankBoard.pb");
        sendQueue = new LinkedBlockingDeque<String>();
        board = new Board(sendQueue, file);
        ball = new Ball(center, velocity, "TestBall", 10, 1, 1);
    }
    
    @Test public void testConstructor(){
        BallSpawner ballSpawner = new BallSpawner(board, "TestBallSpawner", new GridLocation(2,2));
        //assertTrue(checkRep()); //would be called inside class       
    }
    
    @Test public void testAction(){
        BallSpawner ballSpawner = new BallSpawner(board, "TestBallSpawner", new GridLocation(2,2));
        ballSpawner.action();
        //action does nothing
        //assertTrue(checkRep()) would be called inside classes
    }
    @Test public void testGridRepresentation(){
        BallSpawner ballSpawner = new BallSpawner(board, "TestBallSpawner", new GridLocation(2,2));
        assertEquals("@", ballSpawner.gridRepresentation().get(0));
        //assertTrue(checkRep()) would be called inside classes
    }

    @Test public void getLocation() {
        BallSpawner ballSpawner = new BallSpawner(board, "TestBallSpawner", new GridLocation(2,2));
        assertEquals(new GridLocation(2,2),ballSpawner.getLocation());
    }
    
    @Test public void testTimeUntilCollision(){
        Ball ball = new Ball(new Vect(5,3), new Vect(-1,0), "TestBall", 10, 1, 1);
        BallSpawner ballSpawner = new BallSpawner(board, "TestBallSpawner", new GridLocation(2,2));
        Pair<Double, Collidable> p1 = ballSpawner.timeUntilCollision(ball);
        assertEquals(p1.getFirst(),1.9,0.1);
        
    }
    
    @Test public void testCollide(){
        Ball ball = new Ball(new Vect(5,3), new Vect(-1,0), "TestBall", 10, 0, 0);
        int numBalls = board.getBalls().size();
        BallSpawner ballSpawner = new BallSpawner(board, "TestBallSpawner", new GridLocation(2,2));
        Pair<Double, Collidable> p1 = ballSpawner.timeUntilCollision(ball);
        ballSpawner.collide(ball, p1.getSecond());
        assertTrue(ball.getVelocity().x()>0);
        assertEquals(ballSpawner.triggerState, Gadget.TriggerState.TRIGGERED);
        assertEquals(numBalls + 1, board.getBalls().size());
    }
    
    @Test public void testCollideMultipleBalls(){
        Ball ball1 = new Ball(new Vect(5,3), new Vect(-1,0), "TestBall", 10, 0, 0);
        Ball ball2 = new Ball(new Vect(1,3), new Vect(1,0), "TestBall", 10, 0, 0);
        int numBalls = board.getBalls().size();
        BallSpawner ballSpawner = new BallSpawner(board, "TestBallSpawner", new GridLocation(2,2));
        Pair<Double, Collidable> p1 = ballSpawner.timeUntilCollision(ball1);
        ballSpawner.collide(ball1, p1.getSecond());
        assertTrue(ball1.getVelocity().x()>0);
        Pair<Double, Collidable> p2 = ballSpawner.timeUntilCollision(ball2);
        ballSpawner.collide(ball2, p2.getSecond());
        assertTrue(ball2.getVelocity().x()<0);
        assertEquals(numBalls + 2, board.getBalls().size());
    }
    
    @Test public void testNoCollide(){
        Ball ball = new Ball(new Vect(5,3), new Vect(1,0), "TestBall", 10, 0, 0);
        int numBalls = board.getBalls().size();
        BallSpawner ballSpawner = new BallSpawner(board, "TestBallSpawner", new GridLocation(2,2));
        Pair<Double, Collidable> p1 = ballSpawner.timeUntilCollision(ball);
        assertTrue(p1.getSecond()==null);
        assertEquals(numBalls, board.getBalls().size());
    }
   
}
