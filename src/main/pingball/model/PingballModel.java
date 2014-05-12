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

    private boolean running;
    
    private static final int DEFAULT_PORT = 10987;

    private static final double FRAMERATE = 20;

    private Board board; // in FPS
    
    private BlockingQueue<String> sendQueue;
    
    private BlockingQueue<String> receiveQueue;
    
    private File file = null;
    
    private String host = null;
    
    private Integer port = DEFAULT_PORT;

    /**
     * Start a PingballClient using the given arguments.
     *
     * Usage: PingballClient [--port PORT] [--host HOST] FILE
     *
     * The optional [--port PORT] argument is the port to connect to on the server.
     *
     * The optional [--host HOST] argument is the host to connect to.
     *
     * The FILE argument is the board file to read from.
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
                        file = new File(flag);
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
            receiveQueue.drainTo(messages);
            for (String message: messages) {
                board.processMessage(message);
            }
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
    
    public synchronized void consoleOutput(){
        List<String> representation = board.gridRepresentation();
        for (String line: representation) {
            System.out.println(line);
        }
    }
    
    private boolean isReady() {
        return file!=null;
    }
    
    //Set's up game, assumes is ready
    private void setup() throws IOException {
        sendQueue = new LinkedBlockingQueue<>();
        receiveQueue = new LinkedBlockingQueue<>();
        board = new Board(sendQueue, file);
        if (host != null) {
            System.out.println("Running server mode");
            try (Socket socket = new Socket(host, port)) {
                Thread receiver = new Thread(new Receiver(socket, receiveQueue));
                receiver.start();
                Thread sender = new Thread(new Sender(socket, sendQueue));
                sender.start();
                //mainLoop(board, receiveQueue);
                receiver.join();
                sender.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            //mainLoop(board, receiveQueue);
        }   
    }
    
    public synchronized void setFile(File _file){
        this.file = _file;
    }
    
    public synchronized void setHost(String _host){
        this.host = _host;
    }
    
    public synchronized void setPort(int _port){
        this.port = _port;
    }
    
    //start will start the model, called only if ready
    public synchronized boolean start() throws IOException {
        boolean ready = isReady();
        if(ready){
            System.out.println("Ready and Started");
            this.setup();
            this.running = true;
        }
        return ready;
    }
    
    //Pause will send a pause message to all clients connected to this ie to the server.
    public synchronized void pause() {
        this.running = false;
    }
    
    public synchronized void resume() {
        this.running = true;
    }
    //Restart will restart the model
    public synchronized void restart() throws IOException {
        //send restart message
        
        this.start();
    }

    public synchronized void stop(){
        //System.exit(0);
    }
    
    /*
     * Is used by GUI listeners to send a message to the model.
     * Messages can be key press messages etc
     * 
     */
    
    public synchronized void sendMessage(String message) {
        try {
            receiveQueue.put(message);
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
                e.printStackTrace();
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
                e.printStackTrace();
            }
        }

    }
}
