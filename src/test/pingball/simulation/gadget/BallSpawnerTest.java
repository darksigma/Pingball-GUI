package pingball.simulation.gadget;

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
    
    
    
}
