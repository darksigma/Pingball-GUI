package pingball.grammars;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pingball.datastructures.Pair;
import pingball.datastructures.Side;
import pingball.physics.Ball;
import pingball.physics.Board;

public class BoardGrammarCentral {


    private String username = "";
    private Board gameBoard;
    private double gravity;
    private double friction1;
    private double friction2;

    public BoardGrammarCentral() {
        ;
    }

    /**
     * Creates and returns a Board after parsing through the input file representation of the pingball board.
     * This is done based off of the grammar rules outlined in the BoardGrammar.g4 file
     * @param file: input File containing location, orientation, and constant values for gadgets, balls,
     * and essential constants needed to create the pingball board
     * @param out: linked blocking queue passed as a parameter to the board's constructor to create a Board.
     * @return Board containing all the balls and gadgets in their correct locations based off of the
     * lexing and parsing of the input file
     * @throws IOException
     */
    public Board parse(File file, LinkedBlockingQueue<Pair<Ball, Pair<String, Side>>> out) throws IOException {

        CharStream stream = new ANTLRFileStream(file.getPath());
        BoardGrammarLexer lexer = new BoardGrammarLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        BoardGrammarParser parser = new BoardGrammarParser(tokens);

        ParseTree tree = parser.root();

        ParseTreeWalker walker = new ParseTreeWalker();
        BoardGrammarCreatorListener listener = new BoardGrammarCreatorListener();
        listener.setListenerQueue(out);

        walker.walk(listener, tree);

        this.gameBoard = listener.listenerBoard();
        this.username = listener.listenerUsername();
        this.friction1 = listener.listenerFric1();
        this.friction2 = listener.listenerFric2();
        this.gravity = listener.listenerGravity();

        return this.gameBoard;
    }

    /**
     * Used in BoardGrammarTests class to make sure the name of the board is correct, since this cannot be
     * tested through creating a board. The name is set through a function in BoardGrammarCreatorListener.java
     * @return String representation of the board's name
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Used in BoardGrammarTests class to make sure the gravity is correct. If gravity isn't defined in the
     * input file, it defaults to 25.0. Gravity's value is found through a function in
     * BoardGrammarCreatorListener.java
     * @return gravity in the board, represented as a double
     */
    public double getGravity() {
        return this.gravity;
    }

    /**
     * Used in BoardGrammarTests class to make sure the mu is correct. If mu isn't defined in
     * the input file, it defaults to 0.025. Mu's value is found through a function in
     * BoardGrammarCreatorListener.java
     * @return mu in the board, represented as a double
     */
    public double getFric1() {
        return this.friction1;
    }

    /**
     * Used in BoardGrammarTests class to make sure the mu2 value is correct. If mu2 isn't defined
     * in the input file, it defaults to 0.025. Mu2's value is found through a function in
     * BoardGrammarCreatorListener.java
     * @return mu2 in the board, represented as a double
     */
    public double getFric2() {
        return this.friction2;
    }
}
