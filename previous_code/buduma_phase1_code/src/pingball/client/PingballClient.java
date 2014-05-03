package pingball.client;

import boardPhysics.Board;

public class PingballClient{
	
   //the client's board, read from the file
    private final Board board;
    
    public PingballClient(Board board){
        this.board = board;
    }
    
	/**
     * Usage: PingballServer FILE
     * 
     * Clients are built to connect to a server and pass balls to the server,
     * they read a board via a parser from a file and can connect their boards 
     * to other boards by setting walls as invisible. 
     * 
     */
    public static void main(String[] args) {
        //TODO
    }
}