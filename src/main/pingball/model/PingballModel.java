package pingball.model;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import pingball.simulation.Board;
import pingball.simulation.GameObject;
import pingball.simulation.GameObject.GameObjectType;
import pingball.util.Pair;

/**
 * Model for a Pingball Game for phase 2.
 *
 * This model supports operation in single player mode and multiplayer
 * mode, where it can connect to a server.
 *
 * Thread safety argument:
 * All methods on the pingball model will be synchronized, so by the monitor 
 * pattern is threadsafe.
 * 
 * PingballModel has two background threads for sending and receiving 
 * messages from the server. Messages can include
 * adding/removing of balls, adding/removing transparent walls, information about
 * other boards on server, teleporting balls to/from etc.
 * Keypress messages are put by the GUI in the model's receive queue, which is threadsafe.
 *
 * Each of the background threads will have a blocking queue for sending 
 * messages to and from the server.
 * 
 * When the evolveFrame method is called by the GUI. it will poll to see if 
 * there are any messages available in the receiveQueue and perform the
 * corresponding actions. It will then evolve the model for a frame.
 * When there are messages to send to the server, the messages will be put in the queue
 * for the thread responsible for sending messages to the server, and this background thread 
 * will perform the actual sending operation.
 */
public class PingballModel {

    private boolean running = false;

    private boolean connected = false;

    private static final int DEFAULT_PORT = 10987;

    private static final double FRAMERATE = 20;

    private Board board = null; 

    private BlockingQueue<String> modelSendQueue = null;

    private BlockingQueue<String> modelReceiveQueue = null;
    
    private File file = null;
    
    private String host = null;
    
    private Integer port = DEFAULT_PORT;

    private Socket socket = null;

    /**
     * Start a PingballClient using the given arguments.
     *
     * Usage: PingballClient [--port PORT] [--host HOST] [FILE]
     *
     * The optional [--port PORT] argument is the port to connect to on the server.
     *
     * The optional [--host HOST] argument is the host to connect to.
     *
     * The optional [FILE] argument is the board file to read from.
     */
    public PingballModel(String[] args) throws IOException {
        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        try {
            while (!arguments.isEmpty()) {
                String flag = arguments.remove();
                try {
                    if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        if (port < 0 || port > 65535) {
                            throw new IllegalArgumentException("port " + port + " out of range");
                        }
                    } else if (flag.equals("--host")) {
                        host = arguments.remove();
                    } else if (file == null) {
                        setFile(new File(flag));
                        if (!file.isFile()) {
                            throw new IllegalArgumentException("file not found: \"" + file + "\"");
                        }
                    } else {
                        throw new IllegalArgumentException("unknown option: \"" + flag + "\"");
                    }
                } catch (NoSuchElementException nsee) {
                    throw new IllegalArgumentException("missing argument for " + flag);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("unable to parse number for " + flag);
                }
            }
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.err.println("usage: PingballClient [--host HOST] [--port PORT] [FILE]");
            return;
        }
        
    }

    /**
     * Evolve the board for a frame. 
     * 
     * Processes all the messages in the receive queue of the model, resets the trigger state of
     * all gadgets, and evolves the board for a fixed period of time.
     */
    public synchronized void evolveFrame() {
        if(this.running){
            List<String> messages = new ArrayList<String>();
            modelReceiveQueue.drainTo(messages);
            for (String message: messages) {
                board.processMessage(message);
            }
            board.deTrigger();
            board.evolve(1.0 / FRAMERATE);
        }
    }
    
    /**
     * Returns all the data for the current state of all the game objects on the board.
     * 
     * Each data entry has the type of the game object and data representing its current state.
     * @return The data of all the objects 
     */
    public synchronized List<Pair<GameObjectType, List<Object>>> getObjectData(){
        List<Pair<GameObjectType,List<Object>>> objectData = new ArrayList<>();
        if(board!=null) {
            for (GameObject gameObject: this.board.getGameObjects()){
                objectData.add(gameObject.getObjectData());
            }
        }
        return objectData;
    }
    
    /**
     * Shows the current state of the board in the console.
     */
    public synchronized void consoleOutput(){
        if(board!=null){
            List<String> representation = board.gridRepresentation();
            for (String line: representation) {
                System.out.println(line);
            }
        }
    }
    
    /**
     * Sets up the pingball game. Assumes game is ready to be set up.
     * @throws IOException
     */
    private void setup() throws IOException {
        modelSendQueue = new LinkedBlockingQueue<>();
        modelReceiveQueue = new LinkedBlockingQueue<>();
        board = new Board(modelSendQueue, file);
        startServerConnection();  
    }
    
    /**
     * Connects the client to the server.
     */
    private void startServerConnection() {
        if (host != null) {
            try {
                socket = new Socket(host, port);
                this.connected = true;
                Thread receiver = new Thread(new Receiver(socket, modelReceiveQueue));
                receiver.start();
                Thread sender = new Thread(new Sender(socket, modelSendQueue));
                sender.start();
            }
           catch (IOException e) {
             
            }
        } 
    }
    
    /**
     * Disconnects the client from the server.
     * Disconnects all the connected walls and portals as well, if any.
     */
    private void endServerConnection(){
        if(socket!=null){
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Coudn't end connection");
                e.printStackTrace();
            }
        }
        this.connected = false;
        sendMessage("disconnect left");
        sendMessage("disconnect right");
        sendMessage("disconnect top");
        sendMessage("disconnect bottom");
        sendMessage("portalSelfOnly");
    }
    
    /**
     * Return if the pingball model is ready to start.
     * @return true if model is ready to start, false otherwise.
     */
    private boolean isReady() {
        return file!=null;
    }
    
    /**
     * Starts the pingball model. 
     * @return true if model was ready to start, false otherwise
     * @throws IOException
     */
    public synchronized boolean start() throws IOException {
        boolean ready = isReady();
        if(ready){
            this.setup();
            this.running = true;
        }
        return ready;
    }
    
    /**
     * Pause the pingball client.
     * Pausing the client will disconnect it from the server.
     */
    public synchronized void pause() {
        endServerConnection();
        
        this.running = false;
    }
    
    /**
     * Resume the pingball client.
     * Resuming the client reconnects it to the server. 
     * Note: Server does not automatically rejoin old walls, as this is a new connection.
     */
    public synchronized void resume() {
        startServerConnection();
        this.running = true;
    }
    
    /**
     * Restarts the pingball model. 
     * 
     * Starts the pingball model again with same file, host, port.
     * Restarting disconnects the client from the server and reconnects again.
     * Note: Server does not automatically rejoin old walls, as this is a new connection.
     * @throws IOException
     */
    public synchronized void restart() throws IOException {
        endServerConnection();
        this.start();
    }

    /**
     * Stops the pingball client completely.
     * Resets file, host and port to defaults.
     */
    public synchronized void stop(){
        endServerConnection();
        unsetup();
    }
    
    
    public synchronized void setFile(File _file) throws IOException{
        this.file = _file;
        modelSendQueue = new LinkedBlockingQueue<>();
        this.board = new Board(modelSendQueue,file); //Board is only for showing
    }

    public synchronized boolean isFileSet() {
        return (file!=null);
    }
    
    public synchronized String getFileName() {
        if(file!=null){
            return file.getName();
        }
        else return "No file set";
    }
    
    public synchronized void setHost(String _host){
        this.host = _host;
    }
    
    public synchronized boolean isHostSet() {
        return (this.host!=null);
    }
    
    public synchronized String getHostName() {
        if(host!=null) return host;
        else return "No host set";
    }
    
    public synchronized void setPort(int _port){
        this.port = _port;
    }
    
    public synchronized boolean isPortSet() {
        return (port!=null);
    }
    
    public synchronized int getPort() {
        return port;
    }

    public synchronized boolean isValidPort(Integer port){
        return !(port < 0 || port > 65535);
    }

    /**
     * Returns whether the pingball client is connected to the server.
     * @return true if successfully connected, false otherwise.
     */
    public synchronized boolean isConnected() {
        return this.connected;
    }

    /**
     * Returns whether the pingball model is currently running.
     * @return true if running, false otherwise.
     */
    public synchronized boolean isRunning() {
        return this.running;
    }
    
    /**
     * Resets all the fields to thier default values.
     */
    private void unsetup() {
        running = false;
        
        connected = false;
        modelSendQueue = null;
        
        modelReceiveQueue = null;
            
        file = null;
        
        board = null;

        host = null;

        port = DEFAULT_PORT;

        socket = null;
    }


    /**
     * Send a message to the model.
     * Message can be a key press etc.
     * @param message the message to be sent to this model
     */
    public synchronized void sendMessage(String message) {
        try {
        	if(modelReceiveQueue != null){
                modelReceiveQueue.put(message);
        	}
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * A receiver to asynchronously receive data from a socket.
     */
    private class Receiver implements Runnable {

        private final Socket socket;
        private final BlockingQueue<String> receiveQueue;

        /**
         * Make a Receiver.
         *
         * @param socket The socket to receive from.
         *
         * @param receiveQueue The queue to put received data into.
         */
        public Receiver(Socket socket, BlockingQueue<String> receiveQueue) throws IOException {
            this.socket = socket;
            this.receiveQueue = receiveQueue;
        }

        /**
         * Receive data from the socket and put it into the queue.
         */
        public void run() {
            try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
                for (String line = input.readLine(); line != null; line = input.readLine()) {
                    receiveQueue.put(line);
                }
            } catch (InterruptedException | IOException e) {
                //Might have to end these threads, so might need to remove this stack trace.
                //e.printStackTrace();
            }
        }

    }

    /**
     * A sender to asynchronously send data to a socket.
     */
    private class Sender implements Runnable {

        private final Socket socket;
        private final BlockingQueue<String> sendQueue;

        /**
         * Make a Sender.
         *
         * @param socket The socket to receive from.
         *
         * @param sendQueue The queue to put received data into.
         */
        public Sender(Socket socket, BlockingQueue<String> sendQueue) throws IOException {
            this.socket = socket;
            this.sendQueue = sendQueue;
        }

        /**
         * Send data from the socket from the queue.
         */
        public void run() {
            try (PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {
                while (true) {
                    String line = sendQueue.take();
                    output.println(line);
                }
            } catch (InterruptedException | IOException e) {
                //e.printStackTrace();
            }
        }

    }


}
