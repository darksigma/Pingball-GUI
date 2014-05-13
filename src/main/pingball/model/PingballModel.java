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
 * Model for a Pingball simulator.
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
 * adding/removing of balls, adding/removing transparent walls, etc.
 *
 * Each of the background threads will have a blocking queue shared with the
 * main render thread, for sending messages to and from the server.
 *
 * The main render thread, at every frame render, will poll to see if there
 * are any messages available from the background thread and perform the
 * corresponding actions. When there are messages to send to the server,
 * the messages will be put in the queue for the thread responsible for sending
 * messages to the server, and this background thread will perform the actual
 * sending operation.
 */
public class PingballModel {

    private boolean running = false;
    
    private boolean connected = false;
    
    
    private static final int DEFAULT_PORT = 10987;

    private static final double FRAMERATE = 20;

    private Board board = null; // in FPS
    
    private BlockingQueue<String> modelSendQueue = null;
//    
   private BlockingQueue<String> modelReceiveQueue = null;
//    
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
    
    //VERY UNSAFE
//    public synchronized Set<GameObject> getGameObjects(){
//        if(board!=null) return this.board.getGameObjects();
//        else return new HashSet<GameObject>();
//    }

    public synchronized List<Pair<GameObjectType, List<Object>>> getObjectData(){
        List<Pair<GameObjectType,List<Object>>> objectData = new ArrayList<>();
        if(board!=null) {
            for (GameObject gameObject: this.board.getGameObjects()){
                objectData.add(gameObject.getObjectData());
            }
        }
        return objectData;
    }
    
    
    //TODO Remove at end
    public synchronized void consoleOutput(){
        if(board!=null){
            List<String> representation = board.gridRepresentation();
            for (String line: representation) {
                System.out.println(line);
            }
        }
    }
    
    private boolean isReady() {
        return file!=null;
    }
    
    
    //Set's up game, assumes is ready
    private void setup() throws IOException {
        modelSendQueue = new LinkedBlockingQueue<>();
        modelReceiveQueue = new LinkedBlockingQueue<>();
        board = new Board(modelSendQueue, file);
        startServerConnection();  
    }
    
    private void startServerConnection() {
        if (host != null) {
            try {
                socket = new Socket(host, port);
                this.connected = true;
                Thread receiver = new Thread(new Receiver(socket, modelReceiveQueue));
                receiver.start();
                Thread sender = new Thread(new Sender(socket, modelSendQueue));
                sender.start();
                //mainLoop(board, receiveQueue);
//                receiver.join();
//                sender.join();
            }
           catch (IOException e) {
                //e.printStackTrace();
            }
        } 
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
    
    //start will start the model, called only if ready
    public synchronized boolean start() throws IOException {
        boolean ready = isReady();
        if(ready){
            this.setup();
            this.running = true;
        }
        return ready;
    }
    
    //Pause will disconnect this client from server.
    public synchronized void pause() {
        endServerConnection();
        
        this.running = false;
    }
    
    //
    public synchronized void resume() {
        startServerConnection();
        this.running = true;
    }
    //Restart will restart the model
    public synchronized void restart() throws IOException {
        //send restart message
        endServerConnection();
        //don't unsetup
        this.start();
    }

    public synchronized void stop(){
        endServerConnection();
        unsetup();
        //this.
    }
    
    public synchronized boolean isValidPort(Integer port){
        return !(port < 0 || port > 65535);
    }
    
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
    /*
     * Is used by GUI listeners to send a message to the model.
     * Messages can be key press messages etc
     * 
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

	public synchronized boolean isConnected() {
		// TODO Auto-generated method stub
		return this.connected;
	}

	public synchronized boolean isRunning() {
		return this.running;
	}
}
