package pingball.server_client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

import physics.Vect;
import pingball.datastructures.Pair;
import pingball.datastructures.Side;
import pingball.grammars.BoardGrammarCentral;
import pingball.physics.Ball;
import pingball.physics.Board;

/**
 * Pingball Client is the Class that is used to connect to the server.
 *
 * Clients can connect boards together with either of the following commands:
 * h NAME_left NAME_right || v NAME_top NAME_bottom
 * where NAME_left's right wall is connected with NAME_right's left wall, or
 * NAME_top's bottom wall is connected with NAME_bottom's top wall.
 * Connecting two walls together makes those wall invisible, permeable to balls.
 * It is possible to connect a board's own walls together.
 *
 * When the client disconnects from the server, boards joined to it revert to
 * solid walls. The server forgets the lost client's joins so if a client
 * reconnects with the same board name, it does not automatically regain its
 * joined walls. When a client disconnects any balls on the client's board are
 * lost.
 *
 * THREADSAFETY ARGUMENT:
 * ---------------------
 * The only thing shared between threads is a blocking queue used for passing balls to

 * this client before sending it to the server for delivery to the neighbor board. The

 * blocking queue is a Java provided thread safe data type, and is therefore okay to share.

 * Aside from this, all other instance variables are contained and not shared beyond the

 * instance of this class. Message passing occurs through wire protocols and hence leaking

 * references is also not an issue. Furthermore outgoing and ingoing messages are decoupled

 * such that there is no potential for a loss of a message. Access to the contained Board

 * instance occurs through the synchronized methods in the thread safe Board instance and

 * hence conflicting messages between the in and outgoing messages can't cause corrupted

 * data. The primary arguments are usage of thread safe types, containment of all instance

 * variables and implicit synchronization on certain types.
 *
 * @author enguyen, nconsul, spefley
 *
 */
public class PingballClient {
    private final String computer; //name or IP address
    private final Socket socket;
    private final int port;
    private Board game; //read in from file
    private final String username; //read in from file
    private final LinkedBlockingQueue<Pair<Ball, Pair<String, Side>>> ballsToBeSent;
    //Ball, (Person ball is from, side of the board the ball went over) ^

    /**
     * Constructor for the PingballClient.
     *
     * @param port the port used to connect to the server
     * @param computer the IP address/name of the computer the server is
     *      hosted on.
     */
    public PingballClient(int port, String computer, File board) throws IOException {
        this.computer = computer;
        this.port = port;
        this.ballsToBeSent = new LinkedBlockingQueue<Pair<Ball, Pair<String, Side>>>();

        socket = new Socket(computer, port);

        BoardGrammarCentral bgc = new BoardGrammarCentral();
        try {
            this.game = bgc.parse(board, ballsToBeSent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.username = game.getUsername();
        System.out.println(port);

    }

    /**
     * A class to handle input and output streams. Called to open connection
     * with the specified server and listens for a connection.
     * It also handles the printing of the board to terminal. Never returns
     * unless an exception is thrown.
     *
     * @throws IOException if a connection fails
     */
    public void start() throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
        final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("USERNAME: " + username);

        class Update extends TimerTask {
            public void run() {
                //game.update(.05);
                System.out.println(game.update(.05));
            }
        }

        new Thread(
        new Runnable() {
            @Override
            public void run() {
                try {
                    handleInput(in);
                } catch(IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    try {
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
                    handleOutput(out);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    out.close();
                    try {
                        socket.close();
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        Timer timer = new Timer();
        timer.schedule(new Update(), 0, 50);
    }

    /**
     * Handles output stream for client
     * @param out
     * @throws IOException if failure to read from connection
     */
    private void handleOutput(PrintWriter out) throws IOException {
        Pair<Ball, Pair<String, Side>> pair;
        while(true) { //TODO necessary?
            try {
                pair = ballsToBeSent.take();
                Ball ball = pair.first();
                Vect velocity = ball.getVelocityVector();
                Vect position = ball.getPositionVector();
                Pair<String, Side> locationInfo = pair.second();
                String userBallIsFrom = locationInfo.first();
                Side sideBallWentOver = locationInfo.second();
                String ballMessage = "BALL " + velocity.x() + " "
                                     + velocity.y() + " " + position.x() + " "
                                     + position.y() + " " + ball.getName() + " "
                                     + userBallIsFrom + " " + sideBallWentOver;

                out.println(ballMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * Handles the input from the server.
     *
     * @param in input stream
     * @throws IOException
     */
    public void handleInput(BufferedReader in) throws IOException {
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            String output = handleInputRequest(line);
            if (output != null) {
                continue;
            }
        }

    }

    /**
     * Handler for client input, performing requested operations and returning
     * an output message.
     * @param input message from client
     * @return message to client
     */
    private String handleInputRequest(String input) {

        String[] tokens = input.split(" ");


        if(tokens[0].equals("BALL")) {
            //position is fixed in the server
            /*
             * Array of Strings
             * [0] "BALL"
             * [1] Vx
             * [2] Vy
             * [3] X
             * [4] Y
             * [5] ball name
             */
            game.addBall(tokens);
            return null;
        }
        if(tokens[0].equals("USERLEAVING:")) {
            game.disconnectBoard(tokens[1]);
            return null;
        }
        if(tokens[0].equals("JOIN:")) {
            game.connectBoard(tokens[1], Side.toSide(tokens[2]));
            return null;
        }
        if(tokens[0].equals("REMOVE:")) {
            game.disconnectBoard(tokens[1]);
            return null;
        } else {
            return null;
        }

    }


    /**
     * Starts a PingballClient with the given arguments.
     *
     * Usage: PingballClient [--port PORT] [--host HOST] FILE
     *
     * You can run it with up to three tags, two of which are optional.
     * The first tag is [--port PORT] where PORT is the port number at the
     * server to which you wish to connect. The default port is 10987.
     * The second tag is [--host HOST] where HOST is the IP address of the
     * computer to which you wish to connect. The default host is "localhost"
     * which causes a single player game to start. The final tage is FILE which
     * is the pathname of the Pingball board file that this client should run.
     *
     * The client then starts the 20x20 Pingball board as described in the file.
     *
     * @param args command line arguments to be passed into the program when it
     *      is called.
     * @throws IOException if the file specified isn't read in
     */
    public static void main(String[] args) {
        int port = 10987;
        String computer = "singlePlayer";
        File board = null;
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
                    }
                    if (flag.equals("--host")) {
                        computer = arguments.remove();
                    } else {
                        try {
                            board = new File(flag);

                        } catch(Exception e) {
                            throw new IllegalArgumentException("404 File Not Found: \""
                                                               + flag + "\"");
                        }
                    }
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException(
                        "unable to parse number for " + flag);
                }
            }
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.err.println("usage: PingballClient [--port PORT] [--host HOST] FILE");
            return;
        }

        if(computer.equals("singlePlayer")) {
            BoardGrammarCentral bgc = new BoardGrammarCentral();
            final Board game;
            try {
                game = bgc.parse(board, new LinkedBlockingQueue<Pair<Ball, Pair<String, Side>>>());
                class Update extends TimerTask {
                    public void run() {
                        System.out.println(game.update(.05));
                    }
                }

                Timer timer = new Timer();
                timer.schedule(new Update(), 0, 50);
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            try {
                PingballClient client = new PingballClient(port, computer, board);
                client.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
