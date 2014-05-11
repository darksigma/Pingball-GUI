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

    public BoardGrammarCentral() {
        ;
    }
    
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
    
    public String getName(){
        return this.name;
    }
    
    public double getFriction1(){
        return this.friction1;
    }
    
    public double getFriction2(){
        return this.friction2;
    }
    
    public double getGravity(){
        return this.gravity;
    }
    
    public static void main(String[] args){
    	
    }
    
}
