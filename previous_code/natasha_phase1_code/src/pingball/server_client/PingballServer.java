package pingball.server_client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

import pingball.datastructures.Side;
import pingball.physics.Ball;
/**
 * Pingball server. Controls input and output streams between server and
 * clients.
 *
 * THREAD SAFETY ARGUMENT:
 * ---------------------------
 * All interaction between clients is moderated by the server.

 * Balls that travel between boards will do so via a BallMessage which

 * upon creation deletes the ball from the native client. The message

 * then travels to the server where it enters a threadsafe queue of

 * messages where the server parses the messages one at a time, and responds

 * to each one at a time. The server then sends the message back to

 * the client specified, where it enters another threadsafe queue to wait

 * on the client to handle. No ball is ever on more than one board at a time.

 * Also handleRequest is synchronized so only one message can be dealt with at

 * a time. In addition, all server-side data structures are private and contained

 * so that nothing outside can access them.

 *
 * modeled after MinesweeperServer which was written by 6.005 instructors
 *
 * @author enguyen, nconsul, spefley
 *
 */
public class PingballServer {
    private final ServerSocket serverSocket;
    private final int port;
    private Map<String, Map<Side, String>> users = Collections.synchronizedMap(new HashMap<String, Map<Side, String>>());
    private Map<String, PrintWriter> connections = Collections.synchronizedMap(new HashMap<String, PrintWriter>());
    private Map<Socket, String> sockets = Collections.synchronizedMap(new HashMap<Socket, String>());
    //String[] = [left, top, right, bottom]
    public PingballServer(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
    }

    public void serve() throws IOException {
        while(true) {
            final Socket socket = serverSocket.accept();
            //corresponds to client who connects

            new Thread(
            new Runnable() {
                @Override
                public void run() {
                    try {
                        handleConnection(socket);
                    } catch(IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            String userLeaving = sockets.get(socket);
                            synchronized(connections) {
                                Set<String> people = connections.keySet();
                                people.remove(userLeaving);
                                for (String user : people) {
                                    Map<Side, String> neighbors = users.get(user);
                                    for(Side s : Side.values()) {
                                        if(neighbors.get(s).equals(user)) {
                                            neighbors.put(s, "");
                                        }
                                    }
                                    PrintWriter out = connections.get(user);
                                    synchronized(out) {
                                        out.println("USERLEAVING: " + userLeaving);
                                    }
                                }
                            }

                            socket.close();
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            new Thread(
            new Runnable() {
                @Override
                public void run() {
                    try {
                        handleSystemInput();
                    } finally {
                        try {
                            socket.close();
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }


    public synchronized void handleSystemInput() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            for(String line = in.readLine(); line != null; line = in.readLine()) {
                String regex = "(h(\\s+[A-Za-z_][A-Za-z_0-9]*){2}\\s*)|(v(\\s+[A-Za-z_][A-Za-z_0-9]*){2})";
                if (!line.matches(regex)) {
                    continue;
                }
                String[] output = line.split(" ");
                if(!users.containsKey(output[1]) || !users.containsKey(output[2])) {
                    continue;
                }
                if(output[0].equals("h")) {

                    synchronized(users) {

                        Map<Side, String> neighbors1 = users.get(output[1]); //get the neighbors of 1

                        String displacedPerson = neighbors1.put(Side.RIGHT, output[2]); //set 2 to be right of 1
                        if(displacedPerson != null && !displacedPerson.equals("")) {
                            Map<Side, String> neighborsOfDisplacedPerson = users.get(displacedPerson);
                            neighborsOfDisplacedPerson.put(Side.LEFT, "");
                            PrintWriter outDisplaced = connections.get(displacedPerson);
                            outDisplaced.println("REMOVE: " + output[1] + " " + Side.LEFT);
                        }
                        users.put(output[1], neighbors1); //update  1's entry in users

                        Map<Side, String> neighbors2 = users.get(output[2]);
                        String dP2 = neighbors2.put(Side.LEFT, output[1]);
                        if(dP2 != null && !dP2.equals("")) {
                            Map<Side, String> neighborsOfDisplacedPerson = users.get(dP2);
                            neighborsOfDisplacedPerson.put(Side.RIGHT, "");
                            PrintWriter outDP = connections.get(dP2);
                            outDP.println("REMOVE: " + output[2] + " " + Side.RIGHT);
                        }

                        users.put(output[2], neighbors2);
                    }
                    PrintWriter out1 = connections.get(output[1]);
                    PrintWriter out2 = connections.get(output[2]); //mixing this up could be causing issues
                    synchronized(out1) {
                        out1.println("JOIN: " + output[2] + " " + Side.RIGHT);
                    }
                    synchronized(out2) {
                        out2.println("JOIN: " + output[1] + " " + Side.LEFT);
                    }
                }

                //joins boards vertically
                if(output[0].equals("v")) {
                    synchronized(users) {
                        Map<Side, String> neighbors1 = users.get(output[1]);
                        String displacedPerson = neighbors1.put(Side.BOTTOM, output[2]);
                        if(displacedPerson != null && !displacedPerson.equals("")) {
                            Map<Side, String> neighborsOfDisplacedPerson = users.get(displacedPerson);
                            neighborsOfDisplacedPerson.put(Side.TOP, "");
                            PrintWriter outDisplaced = connections.get(displacedPerson);
                            outDisplaced.println("REMOVE: " + output[1] + " " + Side.TOP);
                        }
                        users.put(output[1], neighbors1);

                        Map<Side, String> neighbors2 = users.get(output[2]);
                        String dp2 = neighbors2.put(Side.TOP, output[1]);
                        if(dp2 != null && !dp2.equals("")) {
                            Map<Side, String> neighborsOfDisplacedPerson = users.get(dp2);
                            neighborsOfDisplacedPerson.put(Side.BOTTOM, "");
                            PrintWriter outDp = connections.get(dp2);
                            outDp.println("REMOVE: " + output[2] + " " + Side.BOTTOM);
                        }
                        users.put(output[2], neighbors2);
                    }

                    PrintWriter out1 = connections.get(output[1]);
                    PrintWriter out2 = connections.get(output[2]);
                    synchronized(out1) {
                        out1.println("JOIN: " + output[2] + " " + Side.BOTTOM);
                    }
                    synchronized(out2) {
                        out2.println("JOIN: " + output[1] + " " + Side.TOP );
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handle a single client connection. Returns when client disconnects.
     *
     * @param socket socket where client is connected
     * @throws IOException if connection has an error or terminates unexpectedly
     */
    public void handleConnection(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Welcome to Pingball by enguyen, nconsul, & spefley."); //this is meant to be here. Don't change.
        String userFrom = "";
        Side side = null;
        String userTo = "";
        Double posX = 0.0;
        Double posY = 0.0;
        try {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] output = handleRequest(line);
                if (output != null) {
                    if(output[0].equals("BALL")) {
                        //change the positions before sending
                        posX = Double.parseDouble(output[3]);
                        posY = Double.parseDouble(output[4]);
                        if(posX > 20 - Ball.DEFAULT_DIAMETER/2) {
                            posX -= 19.75;
                        } else if (posX < 0 + Ball.DEFAULT_DIAMETER/2) {
                            posX += 19.75;
                        }
                        if(posY > 20 - Ball.DEFAULT_DIAMETER/2) {
                            posY -= 19.75;
                        } else if (posY < 0 + Ball.DEFAULT_DIAMETER/2) {
                            posX += 19.75;
                        }
                        //

                        String newBallMessage = output[0] + " " + output[1] + " " +
                                                output[2] + " " + posX + " " +
                                                posY + " " + output[5];

                        userFrom = output[6];
                        side = Side.toSide(output[7]);
                        userTo = users.get(userFrom).get(side);

                        //send ball message to the user "userTo"
                        PrintWriter out2 = connections.get(userTo);
                        synchronized(out2) {
                            out2.println(newBallMessage);
                        }

                    }
                    if(output[0].equals("USERNAME:")) {
                        connections.put(output[1], out);
                        sockets.put(socket, output[1]);
                        out.println(output[1] + " is connected");
                    }

                }
            }
        } finally {
            out.close();
            in.close();
        }


    }


    /**
     * Handler for client input, performing requested operations and returning
     * an output message.
     *
     * @param input message from client
     * @return message to client
     */
    private String[] handleRequest(String input) {
        String[] tokens = input.split(" ");
        if(tokens[0].equals("BALL")) {
            Set<String> usernames = users.keySet();
            if(tokens.length < 8 || !usernames.contains(tokens[6])) {
                return null;
            }
            return tokens;
        }
        if(tokens[0].equals("USERNAME:")) {
            HashMap<Side, String> neighborMap = new HashMap<Side, String>();
            neighborMap.put(Side.LEFT, "");
            neighborMap.put(Side.RIGHT, "");
            neighborMap.put(Side.TOP, "");
            neighborMap.put(Side.BOTTOM, "");
            users.put(tokens[1], neighborMap);
            return tokens;
        } else {
            return null;
        }



    }

    /**
     * Checks to see if a client is connected to the server
     *
     * @param clientName name of client
     * @return boolean true/false
     */
    public boolean containsClients(String clientName) {
        boolean contains;
        synchronized(users) {
            contains = users.containsKey(clientName);
        }
        return contains;
    }

    /**
     * Getter for port.
     * @return the port number that clients can connect to the server on. 0<= port <=65535
     */
    public int getPort() {
        return port;
    }
    /**
     * Start a PingballServer using the given arguments.
     *
     * Usage: Pingball Server [--port PORT]
     *
     * Port is an optional integer in the range 0 to 65535 inclusive, specifying
     * the port the server should be listening on. Should no port be specified,
     * the server will use the default port of 10987.
     *
     * @param args command line arguments passed into the program when it is called
     */
    public static void main(String[] args) {
        int port = 10987;
        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        try {
            while (!arguments.isEmpty()) {
                String flag = arguments.remove();
                try {
                    if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        if (port < 0 || port > 65535) {
                            throw new IllegalArgumentException("port " + port
                                                               + " out of range");
                        }
                    } else {
                        throw new IllegalArgumentException("unknown option: \""
                                                           + flag + "\"");
                    }
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException(
                        "unable to parse number for " + flag);
                }
            }
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.err.println("usage: PingballServer [--port PORT]");
            return;
        }
        try {
            PingballServer server = new PingballServer(port);
            server.serve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
