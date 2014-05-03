package pingball.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import pingball.server_client.PingballServer;

/*
 * Testing Strategy
 * -----------------
 * Check that ball messages can be sent between clients with the properties
 * that are supposed to be altered, altered, and the properties which are
 * supposed to stay constant, constant. Make sure that the user that gets
 * the ball is the one that is supposed to get it.
 *
 * Check that disconnecting removes that client from the list
 *
 * Check that the h and v command successfully connect boards. Connect boards
 * to other boards and to themselves.
 *
 * Check that connecting to the server successfully adds that user to the users
 * list with the appropriate neighbors(none).
 *
 *
 * Visual tests in terminal for client-server connection:
 * We first observed that the client-server connection forms correctly. We connected
 * boards side-by-side and observed that the walls of the boards changed to include
 * the name of the board connected to it, showing that a connection forms between the boards/
 * clients. This also includes disconnections when boards are connected different.
 * Additionally, we observed that the balls were passed from one board to another properly.
 * This shows that our message passing works, as the balls were passed from the client to the
 * server and to another client. This has been implemented correctly, since we observed that
 * no two boards can have the same ball(s) at the same time. We also observed that the h and v
 * commands successfully connected boards. Boards can be connected horizontally and vertically
 * and balls can be sent between boards. In addition, we are successfully able to disconnect
 * clients and remove them from the list. Visually, the board name disappears from the side
 * of the board, showing that the client has been disconnected.
 *
 *
 * The general format of the server/client tests are modeled after the released
 * tests for ps3 by Max Goldman
 *
 */
public class PingballServerClientTests {
    private static Thread serverThread;
    Socket socket;
    BufferedReader in;
    PrintWriter out;

    @BeforeClass
    public static void setUpBeforeClass() {
    }

    /**
     * Creates a socket. Tries multiple times in case the Server isn't running yet.
     * @param port to connect on
     * @return the connected socket
     * @throws IOException
     *
     * @author Max Goldman
     */
    public static Socket createSocket(int port) throws IOException {
        Socket socket = null;
        final int MAX_ATTEMPTS = 20;
        int attempts = 0;
        do {
            try {
                socket = new Socket("127.0.0.1", port);
            } catch (ConnectException ce) {
                if (serverThread != null && ! serverThread.isAlive()) {
                    throw new IllegalStateException("Server thread is not running");
                }
                if (++attempts > MAX_ATTEMPTS) {
                    throw new IOException("Exceeded max connection attempts", ce);
                }
                try {
                    Thread.sleep(attempts * 10);
                } catch (InterruptedException ie) { };
            }
        } while (socket == null);
        socket.setSoTimeout(3000);
        return socket;
    }

    /**
     * Return the next non-empty line of input from the given stream, or null if
     * the end of the stream has been reached.
     * @author Max Goldman
     */
    static String nextNonEmptyLine(BufferedReader in) throws IOException {
        while (true) {
            String ret = in.readLine();
            if (ret == null || ! ret.equals(""))
                return ret;
        }
    }

    //Tests start here
    @Test
    public void testServerInitialMessageOverSocket() throws IOException {
        Thread serverThread = new Thread(new Runnable() {
            public void run() {
                PingballServer.main(new String[] {"--port", "4444"});
            }

        });
        serverThread.start();

        Socket socket = createSocket(4444);


        //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        assertEquals("Welcome to Pingball by enguyen, nconsul, & spefley.", nextNonEmptyLine(in));

        socket.close();
    }

    @Test
    public void testServerClientConnectionUsernameMessage() throws IOException {
        Thread serverThread = new Thread(new Runnable() {
            public void run() {
                PingballServer.main(new String[] {"--port", "4448"});
            }

        });
        serverThread.start();

        Socket socket = createSocket(4448);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("USERNAME: user1");
        nextNonEmptyLine(in);
        assertEquals("user1 is connected", nextNonEmptyLine(in));

        socket.close();
    }

    @Test
    public void testServerClientConnectionDisconnectionUsernameMessageMultiple() throws IOException {
        Thread serverThread = new Thread(new Runnable() {
            public void run() {
                PingballServer.main(new String[] {"--port", "4452"});
            }

        });
        serverThread.start();

        Socket socket1 = createSocket(4452);

        BufferedReader in1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
        PrintWriter out1 = new PrintWriter(socket1.getOutputStream(), true);

        Socket socket2 = createSocket(4452);

        BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
        PrintWriter out2 = new PrintWriter(socket2.getOutputStream(), true);

        out2.println("USERNAME: user2");
        out1.println("USERNAME: user1");
        nextNonEmptyLine(in1);
        nextNonEmptyLine(in2);
        assertEquals("user2 is connected", nextNonEmptyLine(in2));
        assertEquals("user1 is connected", nextNonEmptyLine(in1));

        socket2.close();
        assertEquals("USERLEAVING: user2", nextNonEmptyLine(in1));
        socket1.close();
    }




}
