package pingball.model;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class PingballModelTest {

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
        pingballModel.setHost("locahost");
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
        assertTrue(pingballModel.start()); //Start returns true if started successfully
        assertTrue(pingballModel.isRunning());
    }
    
    @Test
    public void testPause() {
        
    }
    
}
