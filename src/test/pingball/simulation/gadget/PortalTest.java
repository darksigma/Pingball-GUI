package pingball.simulation.gadget;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.Vect;
import pingball.simulation.Ball;
import pingball.simulation.Board;
import pingball.simulation.GridLocation;
import pingball.simulation.GameObject.GameObjectType;
import pingball.simulation.collidable.Collidable;
import pingball.simulation.gadget.Gadget.TriggerState;
import pingball.util.Pair;

/*
 * Testing Strategy:
 *
 * Partitioning based on 
 * 1. Location of portal on board
 * 2. Triggering each possible collidable in it.
 * 3. Colliding zero, one or multiple balls
 * 4. Checking working in active and inactive state
 * 
 * We check the working of:
 * 1. The constructor
 * 2. The action
 * 3. The gridRepresentation
 * 4. The getLocation method
 * 5. The timeUntil collision method
 * 6. The collide method.
 * 7. The find method
 *  in both active and inactive state  
 */
public class PortalTest {
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
        Portal portal = new Portal(board, "TestPortal", new GridLocation(2,2), "testBoard", "testPortal", false);
        //assertTrue(checkRep()) would be called inside classes
        
    }
    
    @Test public void testAction(){
        Portal portal = new Portal(board, "TestPortal", new GridLocation(2,2), "testBoard", "testPortal", false);
        portal.action();
        //action does nothing
        //assertTrue(checkRep()) would be called inside classes
    }
    @Test public void testGridRepresentation(){
        Portal portal = new Portal(board, "TestPortal", new GridLocation(2,2), "testBoard", "testPortal", false);
        assertEquals("0",portal.gridRepresentation().get(0));
        //assertTrue(checkRep()) would be called inside classes
    }

    @Test public void getLocation() {
        Portal portal = new Portal(board, "TestPortal", new GridLocation(2,2), "testBoard", "testPortal", false);
        assertEquals(new GridLocation(2,2),portal.getLocation());
    }
    
    @Test public void testActivate() {
        Portal portal = new Portal(board, "TestPortal", new GridLocation(2,2), "testBoard", "testPortal", false);
        portal.activate();
        assertTrue(portal.isActive());
    }
    
    @Test public void testDeactivate() {
        Portal portal = new Portal(board, "TestPortal", new GridLocation(2,2), "testBoard", "testPortal", false);
        portal.deactivate();
        assertTrue(!portal.isActive());
    }
    
    @Test public void testTimeUntilCollisionWhenActive(){
        Ball ball = new Ball(new Vect(5,3), new Vect(-1,0), "TestBall", 10, 1, 1);
        Portal portal = new Portal(board, "TestPortal", new GridLocation(2,2), "testBoard", "testPortal", false);
        portal.activate();
        Pair<Double, Collidable> p1 = portal.timeUntilCollision(ball);
        assertEquals(p1.getFirst(),1.9,0.1);
    }
    
    @Test public void testTimeUntilCollisionWhenInactive(){
        Ball ball = new Ball(new Vect(5,3), new Vect(-1,0), "TestBall", 10, 1, 1);
        Portal portal = new Portal(board, "TestPortal", new GridLocation(2,2), "testBoard", "testPortal", false);
        Pair<Double, Collidable> p1 = portal.timeUntilCollision(ball);
        assertTrue(p1.getFirst().equals(Double.POSITIVE_INFINITY));
    }
    
    @Test public void testCollideWhenInactive(){
        Ball ball = new Ball(new Vect(5,3), new Vect(-1,0), "TestBall", 10, 0, 0);
        Portal portal = new Portal(board, "TestPortal", new GridLocation(2,2), "testBoard", "testPortal", false);
        Pair<Double, Collidable> p1 = portal.timeUntilCollision(ball);
        assertTrue(p1.getFirst().equals(Double.POSITIVE_INFINITY));
    }
    
    @Test public void testCollideWhenActive(){
        Ball ball = new Ball(new Vect(5,3), new Vect(-1,0), "TestBall", 10, 0, 0);
        Portal portal = new Portal(board, "TestPortal", new GridLocation(2,2), "BlankBoard", "OtherPortal", true);
        Portal portal2 = new Portal(board,"OtherPortal", new GridLocation(10,12),"testBoard","testPortal",false);
        portal.find(new HashSet<>(Arrays.asList(portal,portal2)));
        assertTrue(portal.isActive());
        Pair<Double, Collidable> p1 = portal.timeUntilCollision(ball);
        portal.collide(ball, p1.getSecond());
        assertEquals(10,ball.getLocation().x(),0.01);
        assertEquals(12,ball.getLocation().y(),0.01); 
    }
    
    @Test public void testFindOtherPortalNotThere(){
        Ball ball = new Ball(new Vect(5,3), new Vect(-1,0), "TestBall", 10, 0, 0);
        Portal portal = new Portal(board, "TestPortal", new GridLocation(2,2), "BlankBoard", "OtherPortal", true);
        Portal portal2 = new Portal(board,"NotOtherPortal", new GridLocation(10,12),"testBoard","testPortal",false);
        portal.find(new HashSet<>(Arrays.asList(portal,portal2)));
        assertTrue(!portal.isActive());
    }

    @Test public void testFindOtherPortalThere(){
        Ball ball = new Ball(new Vect(5,3), new Vect(-1,0), "TestBall", 10, 0, 0);
        Portal portal = new Portal(board, "TestPortal", new GridLocation(2,2), "BlankBoard", "OtherPortal", true);
        Portal portal2 = new Portal(board,"OtherPortal", new GridLocation(10,12),"testBoard","testPortal",false);
        portal.find(new HashSet<>(Arrays.asList(portal,portal2)));
        assertTrue(portal.isActive());
    }
    
    @Test public void getObjectData() {
        Portal portal = new Portal(board, "TestPortal", new GridLocation(2,2), "BlankBoard", "OtherPortal", true);
        portal.activate();
        Pair<GameObjectType, List<Object>> p = portal.getObjectData();
        GameObjectType type = p.getFirst();
        List<Object> data = p.getSecond();
        assertTrue(type.equals(GameObjectType.PORTAL));
        Pair<Double, Double> topLeft = (Pair<Double, Double>) data.get(0);
        double radius = (double) data.get(1);
        boolean active = (boolean) data.get(2);
        assertEquals(0.5, radius, 0.001);
        assertEquals(2.0,topLeft.getFirst(),0.001);
        assertEquals(2.0,topLeft.getSecond(),0.001);
        assertEquals(true,active);
    }
/*
    @Test public void testCollideMultipleBallsWhenInactive(){
        Ball ball1 = new Ball(new Vect(5,3), new Vect(-1,0), "TestBall", 10, 0, 0);
        Ball ball2 = new Ball(new Vect(1,3), new Vect(1,0), "TestBall", 10, 0, 0);
        Portal portal = new Portal(board, "TestPortal", new GridLocation(2,2));
        Pair<Double, Collidable> p1 = portal.timeUntilCollision(ball1);
        portal.collide(ball1, p1.getSecond());
        assertTrue(ball1.getVelocity().x()>0);
        Pair<Double, Collidable> p2 = portal.timeUntilCollision(ball2);
        portal.collide(ball2, p2.getSecond());
        assertTrue(ball2.getVelocity().x()<0);
    }
    
    @Test public void testNoCollide(){
        Ball ball = new Ball(new Vect(5,3), new Vect(1,0), "TestBall", 10, 0, 0);
        Portal portal = new Portal(board, "TestPortal", new GridLocation(2,2));
        Pair<Double, Collidable> p1 = portal.timeUntilCollision(ball);
        assertTrue(p1.getSecond()==null);
    }
    */
}
