grammar BoardGrammar;

 	
@header{
package pingball;
}

// This adds code to the generated lexer and parser.
@members {

    /**
     * Specs:
     *
   	 * This class contains the grammar for the Board. It gives the grammar rules to read through a .pb file
     * and generate a pingball board with the location and/or orientation of balls and gadgets given, 
     * along with friction, gravity, and actions that trigger other actions. If mu, mu2, and/or gravity are not
     * given, they default to .025, .025, and 25.0 respectively.
     *
     * The lexer rules generate tokens FLOAT and NAME that are used to retrieve the name, location,
     * velocity, and/or orientation of gadgets and balls. FLOATs can be decimal values or INTEGER
     * tokens, both of whose values are correctly determined by the generated listener.
     * Other lexer rules include WHITESPACE and COMMENT, which are skipped. BOARDNAME, which starts
     * the parsing of the board. EQUALS, which represents the '=' symbol to ensure correct parsing
     * through whitespaces.
     *
     * The parsing rules generate the tree that the listener walks through, with tokens at the nodes
     * of the generated tree. The tree starts at root and ends at EOF (end of file). root consists
     * of filelines, which represent each line in the parsed file. In order, this consists of a single
     * boardLine, followed by any number of ballLine, sqBumperLine, cirBumperLine, triBumperLine, 
     * rtFlipLine, lftFlipLine, absorberLine, and fireLine, in that specific order. Each line defines
     * the type of object - board, ball, or specific gadget.
     */

    /**
     * Call this method to have the lexer or parser throw a RuntimeException if
     * it encounters an error.
     */
    public void reportErrorsAsExceptions() {
        addErrorListener(new ExceptionThrowingErrorListener());
    }
    
    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            throw new RuntimeException(msg);
        }
    }
}

//lexical rules

WHITESPACE : [ \t\r\n]+ -> skip;
COMMENT : '#' ~[\r\n]* -> skip;
EQUALS : '=' ;
FLOAT : INTEGER | [0-9]*('.'[0-9]+) | '-'[0-9]*('.'[0-9]+) | '-'[0-9]+ ;
INTEGER : [0-9]+ ;
NAME : [A-Za-z_][A-Za-z_0-9]* ;
BOARDNAME : 'board name=' ;

//parsing rules

root : fileLines EOF;
fileLines : boardLine (ballLine | sqBumperLine | cirBumperLine | triBumperLine | rtFlipLine | lftFlipLine | absorberLine | fireLine ) * ;
boardLine : BOARDNAME NAME (boardGravity | boardFric1 | boardFric2)* ;
boardGravity : 'gravity' EQUALS FLOAT ;
boardFric1 : 'friction1' EQUALS FLOAT ;
boardFric2 : 'friction2' EQUALS FLOAT ;
ballLine : 'ball name' EQUALS NAME 'x' EQUALS FLOAT 'y' EQUALS FLOAT 'xVelocity' EQUALS FLOAT 'yVelocity' EQUALS FLOAT ;
sqBumperLine :  'squareBumper name' EQUALS NAME 'x' EQUALS FLOAT 'y' EQUALS FLOAT ;
cirBumperLine :  'circleBumper name' EQUALS NAME 'x' EQUALS FLOAT 'y' EQUALS FLOAT ;
triBumperLine : 'triangleBumper name' EQUALS NAME 'x' EQUALS FLOAT 'y' EQUALS FLOAT 'orientation' EQUALS FLOAT ;
rtFlipLine : 'rightFlipper name' EQUALS NAME 'x' EQUALS FLOAT 'y' EQUALS FLOAT 'orientation' EQUALS FLOAT ;
lftFlipLine : 'leftFlipper name' EQUALS NAME 'x' EQUALS FLOAT 'y' EQUALS FLOAT 'orientation' EQUALS FLOAT ;
absorberLine : 'absorber name' EQUALS NAME 'x' EQUALS FLOAT 'y' EQUALS FLOAT 'width' EQUALS FLOAT 'height' EQUALS FLOAT ;
fireLine : 'fire trigger' EQUALS NAME 'action' EQUALS NAME ;

