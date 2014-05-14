package pingball.model;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class PingballModelTest {
    
    /*
     * The pingball model is a modification of the old pingball client and board for the GUI
     * These are tests for the additional methods added in phase 2. The testing for the functioning
     * of the phase 1 is written in pingballClientTest.java. 
     * 
     * The server operations of the model were tested by console output givn when running it in server mode.
     * The evolve frame was tested by seeing the visual output in the GUI.
     * Similarly, startServerConnection and endServerConnection were tested by manually checking the console
     * output on the server to see if connections were successfully made/removed.
     */

    @Test
    public void testContructorNoArguments() throws IOException {
        PingballModel pingballModel = new PingballModel(new String[] {});
        assertFalse(pingballModel.isFileSet());
        assertFalse(pingballModel.isHostSet());
    }
    
    @Test
    public void testContructorFileArguments() throws IOException {
        PingballModel pingballModel = new PingballModel(new String[] {"/pingball-phase2/src/test-resources/juggler.pb"});
        assertTrue(pingballModel.isFileSet());
        assertEquals("juggler.pb",pingballModel.getFileName());
        assertFalse(pingballModel.isHostSet());
    }
    
    @Test
    public void testContructorHostArguments() throws IOException {
        PingballModel pingballModel = new PingballModel(new String[] {"--host", "localhost"});
        assertTrue(pingballModel.isHostSet());
        assertEquals("localhost",pingballModel.getHostName());
    }
    
    @Test
    public void testContructorPortArguments() throws IOException {
        PingballModel pingballModel = new PingballModel(new String[] {"--port", "11111"});
        assertTrue(pingballModel.isPortSet());
        assertEquals(11111,pingballModel.getPort());
    }
    
    @Test
    public void testSetFile() throws IOException{
        PingballModel pingballModel = new PingballModel(new String[] {});
        File file = new File("src/test-resources/juggler.pb");
        pingballModel.setFile(file);
        assertTrue(pingballModel.isFileSet());
        assertEquals("juggler.pb",pingballModel.getFileName());
    }
    
    @Test
    public void testSetHost() throws IOException {
        PingballModel pingballModel = new PingballModel(new String[] {});
        pingballModel.setHost("localhost");
        assertTrue(pingballModel.isHostSet());
        assertEquals("localhost",pingballModel.getHostName());
    }
    
    @Test
    public void testSetPort() throws IOException {
        PingballModel pingballModel = new PingballModel(new String[] {});
        pingballModel.setPort(11111);
        assertTrue(pingballModel.isPortSet());
        assertEquals(11111,pingballModel.getPort());
    }
    
    @Test
    public void testStart() throws IOException {
        PingballModel pingballModel = new PingballModel(new String[] {});
        File file = new File("src/test-resources/juggler.pb");
        pingballModel.setFile(file);
        assertTrue(pingballModel.isFileSet());
        assertTrue(pingballModel.start()); 
        //Start returns true if started successfully. 
        //Test also checks if no exceptions thrown
        assertTrue(pingballModel.isRunning());
    }
    
    @Test
    public void testPause() throws IOException {
        PingballModel pingballModel = new PingballModel(new String[] {});
        File file = new File("src/test-resources/juggler.pb");
        pingballModel.setFile(file);
        assertTrue(pingballModel.isFileSet());
        assertTrue(pingballModel.start()); 
        assertTrue(pingballModel.isRunning());
        pingballModel.pause();
        assertFalse(pingballModel.isRunning());
    }
    
    @Test
    public void testResume() throws IOException {
        PingballModel pingballModel = new PingballModel(new String[] {});
        File file = new File("src/test-resources/juggler.pb");
        pingballModel.setFile(file);
        assertTrue(pingballModel.isFileSet());
        assertTrue(pingballModel.start()); 
        assertTrue(pingballModel.isRunning());
        pingballModel.pause();
        assertFalse(pingballModel.isRunning());
        pingballModel.resume();
        assertTrue(pingballModel.isRunning());
    }
    
    @Test
    public void testRestart() throws IOException{
        PingballModel pingballModel = new PingballModel(new String[] {});
        File file = new File("src/test-resources/juggler.pb");
        pingballModel.setFile(file);
        assertTrue(pingballModel.isFileSet());
        assertTrue(pingballModel.start()); 
        assertTrue(pingballModel.isRunning());
        pingballModel.pause();
        assertFalse(pingballModel.isRunning());
        pingballModel.restart();
        assertTrue(pingballModel.isFileSet());
        assertTrue(pingballModel.isRunning());
    }
    
    @Test
    public void testStop() throws IOException{
        PingballModel pingballModel = new PingballModel(new String[] {});
        File file = new File("src/test-resources/juggler.pb");
        pingballModel.setFile(file);
        assertTrue(pingballModel.isFileSet());
        assertTrue(pingballModel.start()); 
        assertTrue(pingballModel.isRunning());
        pingballModel.stop();
        assertFalse(pingballModel.isFileSet());
        assertFalse(pingballModel.isRunning());
    }
    
    
}
