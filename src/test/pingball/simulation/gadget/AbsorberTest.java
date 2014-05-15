package pingball.simulation.gadget;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.Vect;
import pingball.simulation.Ball;
import pingball.simulation.Board;
import pingball.simulation.Constants;
import pingball.simulation.GameObject.GameObjectType;
import pingball.simulation.GridLocation;
import pingball.simulation.collidable.Collidable;
import pingball.simulation.gadget.Gadget.Orientation;
import pingball.simulation.gadget.Gadget.TriggerState;
import pingball.util.Pair;
import pingball.util.StringUtils;

/*
 * Testing Strategy:
 *
 * Partition based on
 * 1. Check constructor
 * 2. Test action with 
 *    1. No ball inside 
 *    2. One ball inside 
 *    3. Two balls inside
 *    4. First shot ball hasn't left absorber
 *    5. First shot ball has left absorber 
 * 3. Checking if shot ball doesn't hit the top
 * 4. Check grid representation
 * 5. Check getLocation
 * 6. Check object data
 *  
 */
public class AbsorberTest {
    private static Vect center = new Vect(14,14);
    private static Vect velocity = new Vect(-1,-1);
    
    private static BlockingQueue<String> sendQueue;
    private static Board board;
    private static Ball ball,ball2,ball3;
    private static File file;
    
    @BeforeClass public static void setUpBeforeClass() throws IOException {
        file = new File("src/test-resources/blankBoard.pb");
        sendQueue = new LinkedBlockingDeque<String>();
        board = new Board(sendQueue, file);
        ball = new Ball(center, velocity, "TestBall", 10, 1, 1);
        ball2 = new Ball(center, velocity, "TestBall2", 10, 1, 1);
        ball3 = new Ball(center, velocity, "TestBall3", 10, 1, 1);
    }
    
    @Test public void testConstructor(){
        Absorber absorber = new Absorber(board, "TestAbsorber", new GridLocation(2,2), 10, 4);
        //assertTrue(absorber.checkRep());
    }
    
    @Test public void testActionNoBallInside() {
        Absorber absorber = new Absorber(board, "TestAbsorber", new GridLocation(2,2), 10, 4);
        absorber.action();
        //assertTrue(checkRep())
    }
    
    @Test public void testActionOneBallInside(){
        Absorber absorber = new Absorber(board, "TestAbsorber", new GridLocation(2,2), 10, 4);
        absorber.collide(ball, absorber.timeUntilCollision(ball).getSecond());
        assertEquals(new Vect(11.75,5.75),ball.getCircle().getCircle().getCenter());
        absorber.action();
        assertEquals(new Vect(0,-Constants.ABSORBER_SHOOT_VELOCITY),ball.getVelocity());
        //action does nothing
        //assertTrue(checkRep()) would be called inside classes
    }
    
    @Test public void testActionTwoBallsInside(){
        Absorber absorber = new Absorber(board, "TestAbsorber", new GridLocation(2,2), 10, 4);
        absorber.collide(ball, absorber.timeUntilCollision(ball).getSecond());
        assertEquals(new Vect(11.75,5.75),ball.getCircle().getCircle().getCenter());
        absorber.collide(ball2, absorber.timeUntilCollision(ball2).getSecond());
        assertEquals(new Vect(11.75,5.75),ball2.getCircle().getCircle().getCenter());
        absorber.action();
        assertEquals(new Vect(0,-Constants.ABSORBER_SHOOT_VELOCITY),ball.getVelocity());
        assertEquals(new Vect(0,0),ball2.getVelocity());
        //action does nothing
        //assertTrue(checkRep()) would be called inside classes
    }
    
    @Test public void testActionTwoBallsFirstBallHasntLeft(){
        Absorber absorber = new Absorber(board, "TestAbsorber", new GridLocation(2,2), 10, 4);
        absorber.collide(ball, absorber.timeUntilCollision(ball).getSecond());
        assertEquals(new Vect(11.75,5.75),ball.getCircle().getCircle().getCenter());
        absorber.collide(ball2, absorber.timeUntilCollision(ball2).getSecond());
        assertEquals(new Vect(11.75,5.75),ball2.getCircle().getCircle().getCenter());
        absorber.action();
        assertEquals(new Vect(0,-Constants.ABSORBER_SHOOT_VELOCITY),ball.getVelocity());
        absorber.action();
        assertEquals(new Vect(0,0),ball2.getVelocity());
        //action does nothing
        //assertTrue(checkRep()) would be called inside classes
    }
    
    @Test public void testShotBallDoesntCollideWithTop() {
        Absorber absorber = new Absorber(board, "TestAbsorber", new GridLocation(2,2), 10, 4);
        absorber.collide(ball, absorber.timeUntilCollision(ball).getSecond());
        assertEquals(new Vect(11.75,5.75),ball.getCircle().getCircle().getCenter());
        absorber.action();
        assertEquals(new Vect(0,-Constants.ABSORBER_SHOOT_VELOCITY),ball.getVelocity());
        assertTrue(absorber.timeUntilCollision(ball).getFirst().equals(Double.POSITIVE_INFINITY));
    }
    
    @Test public void testActionTwoBallsFirstBallHasLeft() {
        Absorber absorber = new Absorber(board, "TestAbsorber", new GridLocation(2,2), 10, 4);
        absorber.collide(ball, absorber.timeUntilCollision(ball).getSecond());
        assertEquals(new Vect(11.75,5.75),ball.getCircle().getCircle().getCenter());
        absorber.collide(ball2, absorber.timeUntilCollision(ball2).getSecond());
        assertEquals(new Vect(11.75,5.75),ball2.getCircle().getCircle().getCenter());
        absorber.action();
        assertEquals(new Vect(0,-Constants.ABSORBER_SHOOT_VELOCITY),ball.getVelocity());
        ball.evolve(0.1);
        absorber.action();
        assertEquals(new Vect(0,-Constants.ABSORBER_SHOOT_VELOCITY),ball2.getVelocity());
    }
    
    @Test public void testGridRepresentation(){
        Absorber absorber = new Absorber(board, "TestAbsorber", new GridLocation(2,2), 10, 4);
        assertEquals(StringUtils.repeat("=", 10),absorber.gridRepresentation().get(0));
        //assertTrue(checkRep()) would be called inside classes
    }
    
    @Test public void testGetLocation() {
        Absorber absorber = new Absorber(board, "TestAbsorber", new GridLocation(2,2), 10, 4);
        assertEquals(new GridLocation(2,2),absorber.getLocation());
    }
    
    @Test public void getObjectData() {
        Absorber absorber = new Absorber(board, "TestAbsorber", new GridLocation(2,2), 10, 4);
        Pair<GameObjectType, List<Object>> p = absorber.getObjectData();
        GameObjectType type = p.getFirst();
        List<Object> data = p.getSecond();
        assertTrue(type.equals(GameObjectType.ABSORBER));
        Pair<Double, Double> topLeft = (Pair<Double, Double>) data.get(0);
        double width = (double) data.get(1);
        double height = (double) data.get(2);
        TriggerState state = (TriggerState) data.get(3);
        assertEquals(10, width, 0.001);
        assertEquals(4, height, 0.001);
        assertEquals(2.0,topLeft.getFirst(),0.001);
        assertEquals(2.0,topLeft.getSecond(),0.001);
    }
    
}
