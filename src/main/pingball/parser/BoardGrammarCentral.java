package pingball.parser;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pingball.simulation.Board;

public class BoardGrammarCentral {

	private String name = "";
    private Board gameBoard;
    private double gravity;
    private double friction1;
    private double friction2;

    /**
     * Empty constructor that we use to call the parse method in BoardGrammarCreatorListener,
     * where all the lines of the file are parsed for tokens.
     */
    public BoardGrammarCentral() {
        ;
    }
    
    /**
     * Parses through a given file to add game objects / gadgets and keep track of key presses
     * and trigger/action relationships to a given board
     * @param file containing the details of how the board should look
     * @param board Board that we add all the parsed gadgets to
     * @throws IOException
     */
    public void parse(File file, Board board) throws IOException {

        CharStream stream = new ANTLRFileStream(file.getPath());
        BoardGrammarLexer lexer = new BoardGrammarLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        BoardGrammarParser parser = new BoardGrammarParser(tokens);

        ParseTree tree = parser.root();

        ParseTreeWalker walker = new ParseTreeWalker();
        BoardGrammarCreatorListener listener = new BoardGrammarCreatorListener();
        listener.setListenerBoard(board);

        walker.walk(listener, tree);

        this.gameBoard = listener.listenerBoard();
        this.name = listener.listenerName();
        this.friction1 = listener.listenerFric1();
        this.friction2 = listener.listenerFric2();
        this.gravity = listener.listenerGravity();
    }
    
    /**
     * Returns the name of the board
     * @return String board's name
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Returns the friction value mu of the board
     * @return double board's mu (friction1)
     */
    public double getFriction1(){
        return this.friction1;
    }
    
    /**
     * Returns the friction2 value mu2 of the board
     * @return double board's mu2 (friction2)
     */
    public double getFriction2(){
        return this.friction2;
    }
    
    /**
     * Returns the gravity of the board
     * @return double gravity of board
     */
    public double getGravity(){
        return this.gravity;
    }
    
}
