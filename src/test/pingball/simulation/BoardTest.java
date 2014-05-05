package pingball.simulation;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.Vect;
import pingball.util.StringUtils;

/*
 * Testing Strategy:
 *
 * Partition inputs to board by changing the input board file.
 * We will test boards with all the different types of gadgets,
 * linked in different ways (including self-links). We will test
 * multiple balls, and we will test to make sure the board handles
 * connections between different walls.
 * We will test that the board properly handles messages from the
 * server.
 *
 * We can indirectly observe the state of the board by obtaining
 * its grid representation, so this can be used to verify
 * correct operation of the board. All objects inside the board, 
 * will call assert(checkRep()) in all methods, so those will also verify
 * the correct operation of the board. Apart from this, we also ran these 
 * boards for a long time and observed for any abnormal behaviour. 
 */
public class BoardTest {
    private static BlockingQueue<String> sendQueue;    
    private static File file;
    
    @BeforeClass public static void setUpBeforeClass() throws IOException {
       
    
    }
    
    //Single client tests
    @Test 
    public void testConstructorBlankFile() throws IOException{
        file = new File("src/test-resources/blankBoard.pb");
        sendQueue = new LinkedBlockingQueue<String>();
        Board board = new Board(sendQueue, file);
        List<String> grid = board.gridRepresentation();
        assertEquals(StringUtils.repeat(".", 22),grid.get(0));
        assertEquals("."+StringUtils.repeat(" ", 20)+".",grid.get(1));
        assertEquals(StringUtils.repeat(".", 22),grid.get(21));
    }
    
    @Test 
    public void testConstructorSampleFile1() throws IOException{
        file = new File("src/test-resources/sampleBoard1.pb");
        sendQueue = new LinkedBlockingQueue<String>();
        Board board = new Board(sendQueue, file);
        List<String> grid = board.gridRepresentation();
        assertEquals("......................",grid.get(0));
        assertEquals(".*                   .",grid.get(1));
        assertEquals(".    O   |  |   O    .",grid.get(4));
        assertEquals(".====================.",grid.get(20));
    }
    
    @Test
    public void testSingleClientNoBallLeavesBoard() throws IOException{
        file = new File("src/test-resources/sampleBoard1.pb");
        sendQueue = new LinkedBlockingQueue<String>();
        Board board = new Board(sendQueue, file);
        board.evolve(100);
        //checkRep() of all gameObjects checks if they are inside board and working correctly.
        //So no error means no object left the board.
    }
    
    @Test 
    public void testGetName() throws IOException {
        file = new File("src/test-resources/sampleBoard1.pb");
        sendQueue = new LinkedBlockingQueue<String>();
        Board board = new Board(sendQueue, file);
        assertEquals("sampleBoard1",board.getName());
    }
    
    @Test 
    public void testGetWidth() throws IOException {
        file = new File("src/test-resources/sampleBoard1.pb");
        sendQueue = new LinkedBlockingQueue<String>();
        Board board = new Board(sendQueue, file);
        assertEquals(20,board.getWidth());    
    }
    
    @Test
    public void testGetHeight() throws IOException {
        file = new File("src/test-resources/sampleBoard1.pb");
        sendQueue = new LinkedBlockingQueue<String>();
        Board board = new Board(sendQueue, file);
        assertEquals(20,board.getWidth());    
    }
    
    @Test 
    public void testGridRepresentation() throws IOException {
        file = new File("src/test-resources/sampleBoard1.pb");
        sendQueue = new LinkedBlockingQueue<String>();
        Board board = new Board(sendQueue, file);
        List<String> grid = board.gridRepresentation();
        assertEquals("......................",grid.get(0));
        assertEquals(".*                   .",grid.get(1));
        assertEquals(".########|  |########.",grid.get(3));
        assertEquals(".    O   |  |   O    .",grid.get(4));
        assertEquals(".====================.",grid.get(20));        
    }
    
    @Test
    public void testGameOnGivenSampleBoard1() throws IOException {
        file = new File("src/test-resources/sampleBoard1.pb");
        sendQueue = new LinkedBlockingQueue<String>();
        Board board = new Board(sendQueue, file);
        board.evolve(200);
        //checkRep() of all objects called.
        //So if no error means no rep Violated
    }
    
    @Test
    public void testGameOnGivenSampleBoard21() throws IOException {
        file = new File("src/test-resources/sampleBoard2-1.pb");
        sendQueue = new LinkedBlockingQueue<String>();
        Board board = new Board(sendQueue, file);
        board.evolve(200);
        //checkRep() of all objects called.
        //So if no error means no rep Violated
    }
    @Test
    public void testGameOnGivenSampleBoard22() throws IOException {
        file = new File("src/test-resources/sampleBoard2-2.pb");
        sendQueue = new LinkedBlockingQueue<String>();
        Board board = new Board(sendQueue, file);
        board.evolve(200);
        //checkRep() of all objects called.
        //So if no error means no rep Violated
    }
    @Test
    public void testGameOnGivenSampleBoard3() throws IOException {
        file = new File("src/test-resources/sampleBoard3.pb");
        sendQueue = new LinkedBlockingQueue<String>();
        Board board = new Board(sendQueue, file);
        board.evolve(200);
        //checkRep() of all objects called.
        //So if no error means no rep Violated
    }
    @Test
    public void testGameOnGivenSampleBoard4() throws IOException {
        file = new File("src/test-resources/sampleBoard4.pb");
        sendQueue = new LinkedBlockingQueue<String>();
        Board board = new Board(sendQueue, file);
        board.evolve(200);
        //checkRep() of all objects called.
        //So if no error means no rep Violated
    }

    @Test
    public void testGameOnOurBoard1() throws IOException {
        //Checks friction, gravity, object boundaries etc.
        file = new File("src/test-resources/bottleNeck.pb");
        sendQueue = new LinkedBlockingQueue<String>();
        Board board = new Board(sendQueue, file);
        board.evolve(200);
        //checkRep() of all objects called.
        //So if no error means no rep Violated
    }

    @Test
    public void testGameOnOurBoard2() throws IOException {
        //Flipper world has a lot of flippers. Checks rendering
        //for lots of objects on board.
        file = new File("src/test-resources/flipperWorld.pb");
        sendQueue = new LinkedBlockingQueue<String>();
        Board board = new Board(sendQueue, file);
        board.evolve(200);
        //checkRep() of all objects called.
        //So if no error means no rep Violated
    }

    @Test
    public void testGameOnOurBoard3() throws IOException {
        file = new File("src/test-resources/juggler.pb");
        sendQueue = new LinkedBlockingQueue<String>();
        Board board = new Board(sendQueue, file);
        board.evolve(200);
        //checkRep() of all objects called.
        //So if no error means no rep Violated
    }

    @Test
    public void testGameOnOurBoard4() throws IOException {
        file = new File("src/test-resources/perpetual.pb");
        sendQueue = new LinkedBlockingQueue<String>();
        Board board = new Board(sendQueue, file);
        board.evolve(200);
        //checkRep() of all objects called.
        //So if no error means no rep Violated
    }

    @Test
    public void testGameOnOurBoard5() throws IOException {
        //Has a lot of balls and circle bumpers. Checks rendering
        //when lots of objects on board.
        file = new File("src/test-resources/stressTest.pb");
        sendQueue = new LinkedBlockingQueue<String>();
        Board board = new Board(sendQueue, file);
        board.evolve(200);
        //checkRep() of all objects called.
        //So if no error means no rep Violated
    }
    
}
