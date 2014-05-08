package pingball.model;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import pingball.simulation.Board;

/**
 * Model for a Pingball simulator.
 *
 * This client supports operation in single player mode and multiplayer
 * mode, where it can connect to a server.
 *
 * Thread safety argument:
 *
 * PingballModel will have a main render thread, and two background threads
 * for sending and receiving messages from the server. Messages can include
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

    private static final int DEFAULT_PORT = 10987;

    private static final double FRAMERATE = 20;

    private Board board; // in FPS
    
    private BlockingQueue<String> sendQueue;
    
    private BlockingQueue<String> receiveQueue;

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
        File file = null;
        String host = null;
        Integer port = DEFAULT_PORT;

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
            if (file == null) {
                throw new IllegalArgumentException("missing positional argument FILE");
            }
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.err.println("usage: PingballClient [--host HOST] [--port PORT] FILE");
            return;
        }
        
        sendQueue = new LinkedBlockingQueue<>();
        receiveQueue = new LinkedBlockingQueue<>();
        board = new Board(sendQueue, file);

        if (host != null) {
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

    /**
     * Evolve the board for a frame.
     */
    public void evolveFrame() {
        List<String> messages = new ArrayList<String>();
        receiveQueue.drainTo(messages);
        for (String message: messages) {
            board.processMessage(message);
        }
        board.evolve(1.0 / FRAMERATE);
    }

    /**
     * Main render loop of the client.
     *
     * This method evolves and renders the board. It tries to achieve a frame
     * rate as close to FRAMERATE as possible.
     */
    private void mainLoop(Board board, BlockingQueue<String> receiveQueue) {
        while (true) {
            long start = System.nanoTime();
            evolveFrame();
            List<String> representation = board.gridRepresentation();
            for (String line: representation) {
                System.out.println(line);
            }
            long mid = System.nanoTime();
            try {
                long sleepTime = (long) (1e3 / FRAMERATE - (mid - start) / 1e6);
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long end = System.nanoTime();
            
            // compute stats
            double frameRender = ((double) (mid - start) / 1e6);
            double closeness = frameRender * FRAMERATE / 1e3 * 1e2; // percent
            double fps = 1 / ((double) (end - start) / 1e9);
            System.out.printf("%.1f FPS | %.1f msec render (%02.0f%%)%n", fps, frameRender, closeness);
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


    public void pause() {
        // TODO Auto-generated method stub
        
    }

    public void restart() {
        // TODO Auto-generated method stub
        
    }

    public void start() {
        // TODO Auto-generated method stub
        
    }

}
