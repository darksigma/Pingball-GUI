package pingball.server;

import java.net.Socket;
import java.io.IOException;
import java.io.BufferedReader;

import static org.junit.Assert.*;
import org.junit.Test;

import pingball.util.Pair;
import static pingball.util.NetworkTestUtils.*;

/*
 * Testing Strategy:
 *
 * Test PingballServer by connecting "fake" clients (plain old sockets)
 * that act like clients would. We can verify that the server correctly
 * accepts the connection from the client, and that it correctly connects
 * and disconnects walls and passes balls between connected walls.
 * 
 * We are testing to make sure that:
 *
 * The server accepts new connections from clients.
 *
 * The server issues connect messages to clients when they are joined
 * to themselves (in a loop).
 *
 * The server issues connect messages to clients when the boards
 * are joined together vertically.
 *
 * The server issues connect messages to clients when the boards
 * are joined together horizontally.
 *
 * The server issues connect messages to clients when multiple sides
 * of the boards are joined together.
 *
 * The server issues disconnect messages to clients when their paired
 * board disconnects.
 *
 * The server issues disconnect messages to clients when their paired
 * board's pairing changes.
 *
 * The server passes balls between connected boards, and passes balls
 * to the proper side.
 *
 * The server passes balls between boards that are connected
 * to themselves, and passes balls to the proper side.
 *
 * The server sends connect messages to three clients that are connected
 * together in a complex configuration.
 */
public class PingballServerTest {

    private Pair<PingballServer, Thread> createServer() {
        try {
            final PingballServer server = new PingballServer(0);
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        server.runServer(); 
                    } catch (IOException ioe) {
                        // ignore
                    }
                }
            });
            t.start();
            return Pair.of(server, t);
        } catch (IOException ioe) {
            throw new RuntimeException("error creating server");
        }
    }

    @Test public void connectTest() throws IOException {
        Pair<PingballServer, Thread> t = createServer();
        try (Socket s = connect(t.getFirst().getPort())) {
            assertNotNull(s);
        }
        t.getSecond().interrupt();
    }

    @Test public void joinSelfMessage() throws IOException {
        Pair<PingballServer, Thread> p = createServer();
        try (Socket a = connect(p.getFirst().getPort())) {
            BufferedReader ar = reader(a);
            sendln(a, "myportals a");
            sleep();
            p.getFirst().handleCommand("v a a");
            assertEquals("connect bottom a", ar.readLine());
            assertEquals("connect top a", ar.readLine());
        }
        p.getSecond().interrupt();
    }

    @Test public void joinHorizontalMessage() throws IOException {
        Pair<PingballServer, Thread> p = createServer();
        try (Socket a = connect(p.getFirst().getPort())) {
            try (Socket b = connect(p.getFirst().getPort())) {
                BufferedReader ar = reader(a), br = reader(b);
                sendln(a, "myportals a");
                sendln(b, "myportals b");
                sleep();
                p.getFirst().handleCommand("h a b");
                String line;
                line = ar.readLine();
                while(line.startsWith("myportals")) line= ar.readLine();
                assertEquals("connect right b", line);
                line = br.readLine();
                while(line.startsWith("myportals")) line= br.readLine();
                assertEquals("connect left a", line);
            }
        }
        p.getSecond().interrupt();
    }

    @Test public void joinVerticalMessage() throws IOException {
        Pair<PingballServer, Thread> p = createServer();
        try (Socket a = connect(p.getFirst().getPort())) {
            try (Socket b = connect(p.getFirst().getPort())) {
                BufferedReader ar = reader(a), br = reader(b);
                sendln(a, "myportals a");
                sendln(b, "myportals b");
                sleep();
                p.getFirst().handleCommand("v a b");
                String line;
                line = ar.readLine();
                while(line.startsWith("myportals")) line= ar.readLine();
                assertEquals("connect bottom b", line);
                line = br.readLine();
                while(line.startsWith("myportals")) line= br.readLine();
                assertEquals("connect top a", line);
            }
        }
        p.getSecond().interrupt();
    }

    @Test public void joinMultipleMessage() throws IOException {
        Pair<PingballServer, Thread> p = createServer();
        try (Socket a = connect(p.getFirst().getPort())) {
            try (Socket b = connect(p.getFirst().getPort())) {
                BufferedReader ar = reader(a), br = reader(b);
                sendln(a, "myportals a");
                sendln(b, "myportals b");
                sleep();
                p.getFirst().handleCommand("v a b");
                String line;
                line = ar.readLine();
                while(line.startsWith("myportals")) line= ar.readLine();
                assertEquals("connect bottom b", line);

                line = br.readLine();
                while(line.startsWith("myportals")) line= br.readLine();
                assertEquals("connect top a", line);
                p.getFirst().handleCommand("h b a");
                assertEquals("connect left b", ar.readLine());
                assertEquals("connect right a", br.readLine());
            }
        }
        p.getSecond().interrupt();
    }

    @Test public void disconnectMessage() throws IOException {
        Pair<PingballServer, Thread> p = createServer();
        try (Socket a = connect(p.getFirst().getPort())) {
            Socket b = connect(p.getFirst().getPort());
            BufferedReader ar = reader(a), br = reader(b);
            sendln(a, "myportals a");
            sendln(b, "myportals b");
            sleep();
            p.getFirst().handleCommand("v a b");
            String line;
            line = ar.readLine();
            while(line.startsWith("myportals")) line= ar.readLine();
            assertEquals("connect bottom b", line);

            line = br.readLine();
            while(line.startsWith("myportals")) line= br.readLine();
            assertEquals("connect top a", line);
            b.close();
            assertEquals("disconnect bottom", ar.readLine());
        }
        p.getSecond().interrupt();
    }

    @Test public void disconnectPairingMessage() throws IOException {
        Pair<PingballServer, Thread> p = createServer();
        try (Socket a = connect(p.getFirst().getPort())) {
            try (Socket b = connect(p.getFirst().getPort())) {
                BufferedReader ar = reader(a), br = reader(b);
                sendln(a, "myportals a");
                sendln(b, "myportals b");
                sleep();
                p.getFirst().handleCommand("v a b");
                String line;
                line = ar.readLine();
                while(line.startsWith("myportals")) line= ar.readLine();
                assertEquals("connect bottom b", line);
                line = br.readLine();
                while(line.startsWith("myportals")) line= br.readLine();
                assertEquals("connect top a", line);
                p.getFirst().handleCommand("v b b");
                assertEquals("disconnect bottom", ar.readLine());
            }
        }
        p.getSecond().interrupt();
    }

    @Test public void passBalls() throws IOException {
        Pair<PingballServer, Thread> p = createServer();
        try (Socket a = connect(p.getFirst().getPort())) {
            try (Socket b = connect(p.getFirst().getPort())) {
                BufferedReader ar = reader(a), br = reader(b);
                sendln(a, "myportals a");
                sendln(b, "myportals b");
                sleep();
                p.getFirst().handleCommand("v a b");
                String line;
                line = ar.readLine();
                while(line.startsWith("myportals")) line= ar.readLine();
                assertEquals("connect bottom b", line);
                line = br.readLine();
                while(line.startsWith("myportals")) line= br.readLine();
                assertEquals("connect top a", line);
                sendln(a, "ball testball bottom 1.2 2.2 3.4 5.5");
                assertEquals("ball testball top 1.2 2.2 3.4 5.5", br.readLine());
                sendln(b, "ball testball top 5.55 2.22 -4.1 1.5");
                assertEquals("ball testball bottom 5.55 2.22 -4.1 1.5", ar.readLine());
            }
        }
        p.getSecond().interrupt();
    }

    @Test public void passBallsSelf() throws IOException {
        Pair<PingballServer, Thread> p = createServer();
        try (Socket a = connect(p.getFirst().getPort())) {
            BufferedReader ar = reader(a);
            sendln(a, "myportals a");
            sleep();
            p.getFirst().handleCommand("v a a");
            assertEquals("connect bottom a", ar.readLine());
            assertEquals("connect top a", ar.readLine());
            sendln(a, "ball testball bottom 1.2 2.2 3.4 5.5");
            assertEquals("ball testball top 1.2 2.2 3.4 5.5", ar.readLine());
        }
        p.getSecond().interrupt();
    }

    @Test public void joinComplex() throws IOException {
        Pair<PingballServer, Thread> p = createServer();
        try (Socket a = connect(p.getFirst().getPort())) {
            try (Socket b = connect(p.getFirst().getPort())) {
                try (Socket c = connect(p.getFirst().getPort())) {
                    BufferedReader ar = reader(a), br = reader(b), cr = reader(c);
                    sendln(a, "myportals a");
                    sendln(b, "myportals b");
                    sendln(c, "myportals c");
                    sleep();
                    p.getFirst().handleCommand("h a b");
                    String line;
                    line = ar.readLine();
                    while(line.startsWith("myportals")) line= ar.readLine();
                    assertEquals("connect right b", line);

                    line = br.readLine();
                    while(line.startsWith("myportals")) line= br.readLine();
                    assertEquals("connect left a", line);
                    p.getFirst().handleCommand("h b c");

                    line = cr.readLine();
                    while(line.startsWith("myportals")) line= cr.readLine();
                    assertEquals("connect right c", br.readLine());
                    assertEquals("connect left b", line);
                    p.getFirst().handleCommand("h c a");
                    assertEquals("connect left c", ar.readLine());
                    assertEquals("connect right a", cr.readLine());
                }
            }
        }
        p.getSecond().interrupt();
    }

}
