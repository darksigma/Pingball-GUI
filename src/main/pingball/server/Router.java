package pingball.server;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

import pingball.simulation.Wall;
import pingball.util.BidirectionalMap;
import pingball.util.Pair;
import pingball.util.StringUtils;

/** A thread-safe mutable routing to keep track of connections between walls.
 *
 * Sockets passed into this class must not be used for outputting data anywhere
 * else. Clients of this class are allowed to read from sockets that are passed
 * in.
 *
 * Representation invariant:
 *
 * The router keeps links of walls of boards to other walls. In order for the
 * system to function correctly (and have valid meaning), these links must be
 * bidirectional. The links in wallLinkMap have to be bidirectional at all
 * times. If a link such as (a, left) -> b is in the wallLinkMap, then the
 * opposite link (b, right) -> a must be present in the map.
 * The router also keeps a map of every board's portalMsg which it sends when it first connects.
 * On receiveing the portalMsg, this message is routed to every other board on the server.
 * Also, every other boards portalMsg is routed back to the sender. 
 * These maps are accordingly updated whenever a new client connects/disconnects so that 
 * they store the correct information for the current state of the server.
 * 
 * Thread safety argument:
 *
 * This class is meant to be shared between threads. All methods on router are
 * synchronized, which will guarantee thread safety.
 */
public class Router {
    
    /**
     * Bidirectional map between a board's name and its socket.
     */
    private final BidirectionalMap<Socket, String> mapSocketName = new BidirectionalMap<>();
    
    // store bidirectional links in here like:
    // (a, left) -> b
    // (b, right) -> a
    private final Map<Pair<String, Wall.Side>, String> wallLinkMap = new HashMap<>();
    
    /**
     * Map between name of board and its portalMsg
     */
    private final Map<String, String> mapNamePortalmsg = new HashMap<>();
    
    /**
     * Make a Router.
     */
    public Router() {
        //Do nothing
    }
    
    /**
     * Removes a client from the routing system. Sends the appropriate notonboard
     * messages to ensure that portals connecting boards on the server to the board
     * most recently disconnected from the server are deactivated.
     *
     * @param caller The user to remove.
     *
     * This method must be called when a user is removed from the system.
     */
    public synchronized void removeUser(Socket caller) {
        if( mapSocketName.containsForward(caller) ) {
            String user = mapSocketName.getForward(caller);
            
            for (Wall.Side wall: Wall.Side.values()) {
                removeWallLinkOf(user, wall);
            }
            
            mapSocketName.removeForward(caller);
            mapNamePortalmsg.remove(user);
            
            for(Socket s : mapSocketName.keySet()){
            	send(s, String.format("notonboard %s", user));
            }
        }
        checkRep();
    }

    public synchronized void send(Socket socket, String message) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
        } catch (IOException e) {
            // ignore
        }
    }
    
    public synchronized Set<String> getUsers() {
        checkRep();
        return new HashSet<>(mapSocketName.valueSet());
    }

    /**
     * Processes a message from the client.
     *
     * @param message The message to process.
     *
     * @param caller The recipient of the message.
     */
    public synchronized void processClientMessage(String message, Socket caller) {
        String[] split = message.split(" ");

        String ballmsg = "^ball [A-Za-z_][A-Za-z_0-9]* (left|right|top|bottom)( -?(?:[0-9]+\\.[0-9]*|\\.?[0-9]+)){4}$";
        String portalBallmsg = "^portalball [A-Za-z_][A-Za-z_0-9]* [A-Za-z_][A-Za-z_0-9]* [A-Za-z_][A-Za-z_0-9]*( -?(?:[0-9]+\\.[0-9]*|\\.?[0-9]+)){4}$";
        String myportalmsg = "^myportals( [A-Za-z_][A-Za-z_0-9]*)+";
        if (message.matches(myportalmsg)){
            String boardName = split[1];
            for(Socket s : mapSocketName.keySet()){
                send(s, message);
            }

            for(String board : mapSocketName.valueSet()){
                send(caller, mapNamePortalmsg.get(board));
            }
            addUser(boardName, caller);
            mapNamePortalmsg.put(boardName, message);

        } else if (message.matches(ballmsg)) {
            String clientName = mapSocketName.getForward(caller);
            Wall.Side wall = Wall.Side.fromString(split[2]);
            Socket dest = mapSocketName.getReverse(wallLinkMap.get(Pair.of(clientName, wall)));
            if (dest != null) {
                split[2] = wall.opposite().toString();
                send(dest, StringUtils.join(" ", split));
            }
        } else if (message.matches(portalBallmsg)){
            String otherBoard = split[2];
            Socket dest = mapSocketName.getReverse(otherBoard);
            if(dest!=null){
                send(dest, message);
            }
        } else {

            System.err.println("ignoring invalid message from client:"+message);
        }
        checkRep();
    }

    /**
     * Links two boards together horizontally.
     *
     * This breaks any preexisting connections if necessary.
     *
     * @param left The left board to link.
     *
     * @param right The right board to link.
     *
     * @return Whether the boards were successfully linked.
     */
    public synchronized boolean addConnectionHorizontal(String left, String right) {
        if (mapSocketName.getReverse(left) == null || mapSocketName.getReverse(right) == null) {
            checkRep();
            return false;
        }
        removeWallLinkOf(left, Wall.Side.RIGHT);
        removeWallLinkOf(right, Wall.Side.LEFT);
        
        wallLinkMap.put(Pair.of(left, Wall.Side.RIGHT), right);
        sendMessageMergeWall(mapSocketName.getReverse(left), Wall.Side.RIGHT, right);
        
        wallLinkMap.put(Pair.of(right, Wall.Side.LEFT), left);
        sendMessageMergeWall(mapSocketName.getReverse(right), Wall.Side.LEFT, left);
        checkRep();
        return true;
    }

    /**
     * Links two boards together vertically.
     *
     * This breaks any preexisting connections if necessary.
     *
     * @param top The top board to link.
     *
     * @param bottom The bottom board to link.
     *
     * @return Whether the boards were successfully linked.
     */
    public synchronized boolean addConnectionVertical(String top, String bottom) {
        if (mapSocketName.getReverse(top) == null || mapSocketName.getReverse(bottom) == null) {
            checkRep();
            return false;
        }
        removeWallLinkOf(top, Wall.Side.BOTTOM);
        removeWallLinkOf(bottom, Wall.Side.TOP);
        
        wallLinkMap.put(Pair.of(top, Wall.Side.BOTTOM), bottom);
        sendMessageMergeWall(mapSocketName.getReverse(top), Wall.Side.BOTTOM, bottom);
        
        wallLinkMap.put(Pair.of(bottom, Wall.Side.TOP), top);
        sendMessageMergeWall(mapSocketName.getReverse(bottom), Wall.Side.TOP, top);
        checkRep();
        return true;
    }
    
    /**
     * Remove the merging wall of that user on the corresonding side
     * 
     * @param user the name of user
     * 
     * @param side the side of the wall
     */
    private synchronized void removeWallLinkOf(String user, Wall.Side side) {
        if( wallLinkMap.containsKey(Pair.of(user, side)) ) {
            String anotherUser = wallLinkMap.get(Pair.of(user, side));
            
            wallLinkMap.remove(Pair.of(user, side));
            sendMessageReturnToWall(mapSocketName.getReverse(user), side);
            
            removeWallLinkOf(anotherUser, side.opposite());
        }
    }
    
    /**
     * Adds a client to the routing system. Ensures that an onboard messages is sent
     * to all of the boards regarding the most recently added board and to the recently
     * added board regarding all of the boards already on the server. This enables
     * proper connectivity and activation of portals.
     *
     * @param name The name of the board.
     *
     * @param socket The socket corresponding to the client.
     */
    private synchronized void addUser(String name, Socket socket) {
    	
    	
    	if( !mapSocketName.containsReverse(name) ) {
            
            mapSocketName.putForward(socket, name);
            
    	}
        
        checkRep();
    }
    
    /**
     * Send message to the user to make one side of his wall to wall (not a user name anymore)
     * 
     * @param socket The socket of client
     * 
     * @param side The side of the wall
     */
    private synchronized void sendMessageReturnToWall(Socket socket, Wall.Side side) {
        send(socket, String.format("disconnect %s", side.toString()));
    }
    
    /**
     * Send message to the user to make one side of his wall merge with the corresponding name
     * 
     * @param socket The socket of client
     * 
     * @param side The side of the wall
     * 
     * @param name The name of another user 
     */
    private synchronized void sendMessageMergeWall(Socket socket, Wall.Side side, String name) {
        send(socket, String.format("connect %s %s", side.toString(), name));
    }

    private synchronized void checkRep() {
        // only run if assertions are enabled
        boolean assertionsEnabled = false;
        assert assertionsEnabled = true; // assignment
        if (assertionsEnabled) {
            for (Pair<String, Wall.Side> p: wallLinkMap.keySet()) {
                String otherBoard = wallLinkMap.get(p);
                Wall.Side oppositeSide = p.getSecond().opposite();
                assert wallLinkMap.containsKey(Pair.of(otherBoard, oppositeSide));
            }
        }
    }
    
}
