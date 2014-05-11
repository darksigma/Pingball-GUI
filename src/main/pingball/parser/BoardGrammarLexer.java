// Generated from BoardGrammar.g4 by ANTLR 4.0

package pingball.parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BoardGrammarLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__23=1, T__22=2, T__21=3, T__20=4, T__19=5, T__18=6, T__17=7, T__16=8, 
		T__15=9, T__14=10, T__13=11, T__12=12, T__11=13, T__10=14, T__9=15, T__8=16, 
		T__7=17, T__6=18, T__5=19, T__4=20, T__3=21, T__2=22, T__1=23, T__0=24, 
		WHITESPACE=25, COMMENT=26, EQUALS=27, FLOAT=28, INTEGER=29, NAME=30, KEY=31;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'keyup'", "'yVelocity'", "'fire trigger'", "'squareBumper name'", "'friction1'", 
		"'name'", "'absorber name'", "'gravity'", "'board'", "'xVelocity'", "'circleBumper name'", 
		"'orientation'", "'height'", "'triangleBumper name'", "'x'", "'y'", "'action'", 
		"'key'", "'rightFlipper name'", "'leftFlipper name'", "'ball name'", "'friction2'", 
		"'width'", "'keydown'", "WHITESPACE", "COMMENT", "'='", "FLOAT", "INTEGER", 
		"NAME", "KEY"
	};
	public static final String[] ruleNames = {
		"T__23", "T__22", "T__21", "T__20", "T__19", "T__18", "T__17", "T__16", 
		"T__15", "T__14", "T__13", "T__12", "T__11", "T__10", "T__9", "T__8", 
		"T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "WHITESPACE", 
		"COMMENT", "EQUALS", "FLOAT", "INTEGER", "NAME", "KEY"
	};



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


	public BoardGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BoardGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 24: WHITESPACE_action((RuleContext)_localctx, actionIndex); break;

		case 25: COMMENT_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WHITESPACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4!\u01ef\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\6\32"+
		"\u0134\n\32\r\32\16\32\u0135\3\32\3\32\3\33\3\33\7\33\u013c\n\33\f\33"+
		"\16\33\u013f\13\33\3\33\3\33\3\34\3\34\3\35\3\35\7\35\u0147\n\35\f\35"+
		"\16\35\u014a\13\35\3\35\3\35\6\35\u014e\n\35\r\35\16\35\u014f\3\35\3\35"+
		"\7\35\u0154\n\35\f\35\16\35\u0157\13\35\3\35\3\35\6\35\u015b\n\35\r\35"+
		"\16\35\u015c\3\35\3\35\6\35\u0161\n\35\r\35\16\35\u0162\5\35\u0165\n\35"+
		"\3\36\6\36\u0168\n\36\r\36\16\36\u0169\3\37\3\37\7\37\u016e\n\37\f\37"+
		"\16\37\u0171\13\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 "+
		"\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 "+
		"\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 "+
		"\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 "+
		"\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 "+
		"\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u01ee\n \2!\3\3\1\5\4\1"+
		"\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1"+
		"\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31"+
		"\1\61\32\1\63\33\2\65\34\3\67\35\19\36\1;\37\1= \1?!\1\3\2\f\5\13\f\17"+
		"\17\"\"\4\f\f\17\17\3\62;\3\62;\3\62;\3\62;\3\62;\3\62;\5C\\aac|\6\62"+
		";C\\aac|\u020e\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\3A\3\2\2\2\5G\3\2\2\2\7Q\3"+
		"\2\2\2\t^\3\2\2\2\13p\3\2\2\2\rz\3\2\2\2\17\177\3\2\2\2\21\u008d\3\2\2"+
		"\2\23\u0095\3\2\2\2\25\u009b\3\2\2\2\27\u00a5\3\2\2\2\31\u00b7\3\2\2\2"+
		"\33\u00c3\3\2\2\2\35\u00ca\3\2\2\2\37\u00de\3\2\2\2!\u00e0\3\2\2\2#\u00e2"+
		"\3\2\2\2%\u00e9\3\2\2\2\'\u00ed\3\2\2\2)\u00ff\3\2\2\2+\u0110\3\2\2\2"+
		"-\u011a\3\2\2\2/\u0124\3\2\2\2\61\u012a\3\2\2\2\63\u0133\3\2\2\2\65\u0139"+
		"\3\2\2\2\67\u0142\3\2\2\29\u0164\3\2\2\2;\u0167\3\2\2\2=\u016b\3\2\2\2"+
		"?\u01ed\3\2\2\2AB\7m\2\2BC\7g\2\2CD\7{\2\2DE\7w\2\2EF\7r\2\2F\4\3\2\2"+
		"\2GH\7{\2\2HI\7X\2\2IJ\7g\2\2JK\7n\2\2KL\7q\2\2LM\7e\2\2MN\7k\2\2NO\7"+
		"v\2\2OP\7{\2\2P\6\3\2\2\2QR\7h\2\2RS\7k\2\2ST\7t\2\2TU\7g\2\2UV\7\"\2"+
		"\2VW\7v\2\2WX\7t\2\2XY\7k\2\2YZ\7i\2\2Z[\7i\2\2[\\\7g\2\2\\]\7t\2\2]\b"+
		"\3\2\2\2^_\7u\2\2_`\7s\2\2`a\7w\2\2ab\7c\2\2bc\7t\2\2cd\7g\2\2de\7D\2"+
		"\2ef\7w\2\2fg\7o\2\2gh\7r\2\2hi\7g\2\2ij\7t\2\2jk\7\"\2\2kl\7p\2\2lm\7"+
		"c\2\2mn\7o\2\2no\7g\2\2o\n\3\2\2\2pq\7h\2\2qr\7t\2\2rs\7k\2\2st\7e\2\2"+
		"tu\7v\2\2uv\7k\2\2vw\7q\2\2wx\7p\2\2xy\7\63\2\2y\f\3\2\2\2z{\7p\2\2{|"+
		"\7c\2\2|}\7o\2\2}~\7g\2\2~\16\3\2\2\2\177\u0080\7c\2\2\u0080\u0081\7d"+
		"\2\2\u0081\u0082\7u\2\2\u0082\u0083\7q\2\2\u0083\u0084\7t\2\2\u0084\u0085"+
		"\7d\2\2\u0085\u0086\7g\2\2\u0086\u0087\7t\2\2\u0087\u0088\7\"\2\2\u0088"+
		"\u0089\7p\2\2\u0089\u008a\7c\2\2\u008a\u008b\7o\2\2\u008b\u008c\7g\2\2"+
		"\u008c\20\3\2\2\2\u008d\u008e\7i\2\2\u008e\u008f\7t\2\2\u008f\u0090\7"+
		"c\2\2\u0090\u0091\7x\2\2\u0091\u0092\7k\2\2\u0092\u0093\7v\2\2\u0093\u0094"+
		"\7{\2\2\u0094\22\3\2\2\2\u0095\u0096\7d\2\2\u0096\u0097\7q\2\2\u0097\u0098"+
		"\7c\2\2\u0098\u0099\7t\2\2\u0099\u009a\7f\2\2\u009a\24\3\2\2\2\u009b\u009c"+
		"\7z\2\2\u009c\u009d\7X\2\2\u009d\u009e\7g\2\2\u009e\u009f\7n\2\2\u009f"+
		"\u00a0\7q\2\2\u00a0\u00a1\7e\2\2\u00a1\u00a2\7k\2\2\u00a2\u00a3\7v\2\2"+
		"\u00a3\u00a4\7{\2\2\u00a4\26\3\2\2\2\u00a5\u00a6\7e\2\2\u00a6\u00a7\7"+
		"k\2\2\u00a7\u00a8\7t\2\2\u00a8\u00a9\7e\2\2\u00a9\u00aa\7n\2\2\u00aa\u00ab"+
		"\7g\2\2\u00ab\u00ac\7D\2\2\u00ac\u00ad\7w\2\2\u00ad\u00ae\7o\2\2\u00ae"+
		"\u00af\7r\2\2\u00af\u00b0\7g\2\2\u00b0\u00b1\7t\2\2\u00b1\u00b2\7\"\2"+
		"\2\u00b2\u00b3\7p\2\2\u00b3\u00b4\7c\2\2\u00b4\u00b5\7o\2\2\u00b5\u00b6"+
		"\7g\2\2\u00b6\30\3\2\2\2\u00b7\u00b8\7q\2\2\u00b8\u00b9\7t\2\2\u00b9\u00ba"+
		"\7k\2\2\u00ba\u00bb\7g\2\2\u00bb\u00bc\7p\2\2\u00bc\u00bd\7v\2\2\u00bd"+
		"\u00be\7c\2\2\u00be\u00bf\7v\2\2\u00bf\u00c0\7k\2\2\u00c0\u00c1\7q\2\2"+
		"\u00c1\u00c2\7p\2\2\u00c2\32\3\2\2\2\u00c3\u00c4\7j\2\2\u00c4\u00c5\7"+
		"g\2\2\u00c5\u00c6\7k\2\2\u00c6\u00c7\7i\2\2\u00c7\u00c8\7j\2\2\u00c8\u00c9"+
		"\7v\2\2\u00c9\34\3\2\2\2\u00ca\u00cb\7v\2\2\u00cb\u00cc\7t\2\2\u00cc\u00cd"+
		"\7k\2\2\u00cd\u00ce\7c\2\2\u00ce\u00cf\7p\2\2\u00cf\u00d0\7i\2\2\u00d0"+
		"\u00d1\7n\2\2\u00d1\u00d2\7g\2\2\u00d2\u00d3\7D\2\2\u00d3\u00d4\7w\2\2"+
		"\u00d4\u00d5\7o\2\2\u00d5\u00d6\7r\2\2\u00d6\u00d7\7g\2\2\u00d7\u00d8"+
		"\7t\2\2\u00d8\u00d9\7\"\2\2\u00d9\u00da\7p\2\2\u00da\u00db\7c\2\2\u00db"+
		"\u00dc\7o\2\2\u00dc\u00dd\7g\2\2\u00dd\36\3\2\2\2\u00de\u00df\7z\2\2\u00df"+
		" \3\2\2\2\u00e0\u00e1\7{\2\2\u00e1\"\3\2\2\2\u00e2\u00e3\7c\2\2\u00e3"+
		"\u00e4\7e\2\2\u00e4\u00e5\7v\2\2\u00e5\u00e6\7k\2\2\u00e6\u00e7\7q\2\2"+
		"\u00e7\u00e8\7p\2\2\u00e8$\3\2\2\2\u00e9\u00ea\7m\2\2\u00ea\u00eb\7g\2"+
		"\2\u00eb\u00ec\7{\2\2\u00ec&\3\2\2\2\u00ed\u00ee\7t\2\2\u00ee\u00ef\7"+
		"k\2\2\u00ef\u00f0\7i\2\2\u00f0\u00f1\7j\2\2\u00f1\u00f2\7v\2\2\u00f2\u00f3"+
		"\7H\2\2\u00f3\u00f4\7n\2\2\u00f4\u00f5\7k\2\2\u00f5\u00f6\7r\2\2\u00f6"+
		"\u00f7\7r\2\2\u00f7\u00f8\7g\2\2\u00f8\u00f9\7t\2\2\u00f9\u00fa\7\"\2"+
		"\2\u00fa\u00fb\7p\2\2\u00fb\u00fc\7c\2\2\u00fc\u00fd\7o\2\2\u00fd\u00fe"+
		"\7g\2\2\u00fe(\3\2\2\2\u00ff\u0100\7n\2\2\u0100\u0101\7g\2\2\u0101\u0102"+
		"\7h\2\2\u0102\u0103\7v\2\2\u0103\u0104\7H\2\2\u0104\u0105\7n\2\2\u0105"+
		"\u0106\7k\2\2\u0106\u0107\7r\2\2\u0107\u0108\7r\2\2\u0108\u0109\7g\2\2"+
		"\u0109\u010a\7t\2\2\u010a\u010b\7\"\2\2\u010b\u010c\7p\2\2\u010c\u010d"+
		"\7c\2\2\u010d\u010e\7o\2\2\u010e\u010f\7g\2\2\u010f*\3\2\2\2\u0110\u0111"+
		"\7d\2\2\u0111\u0112\7c\2\2\u0112\u0113\7n\2\2\u0113\u0114\7n\2\2\u0114"+
		"\u0115\7\"\2\2\u0115\u0116\7p\2\2\u0116\u0117\7c\2\2\u0117\u0118\7o\2"+
		"\2\u0118\u0119\7g\2\2\u0119,\3\2\2\2\u011a\u011b\7h\2\2\u011b\u011c\7"+
		"t\2\2\u011c\u011d\7k\2\2\u011d\u011e\7e\2\2\u011e\u011f\7v\2\2\u011f\u0120"+
		"\7k\2\2\u0120\u0121\7q\2\2\u0121\u0122\7p\2\2\u0122\u0123\7\64\2\2\u0123"+
		".\3\2\2\2\u0124\u0125\7y\2\2\u0125\u0126\7k\2\2\u0126\u0127\7f\2\2\u0127"+
		"\u0128\7v\2\2\u0128\u0129\7j\2\2\u0129\60\3\2\2\2\u012a\u012b\7m\2\2\u012b"+
		"\u012c\7g\2\2\u012c\u012d\7{\2\2\u012d\u012e\7f\2\2\u012e\u012f\7q\2\2"+
		"\u012f\u0130\7y\2\2\u0130\u0131\7p\2\2\u0131\62\3\2\2\2\u0132\u0134\t"+
		"\2\2\2\u0133\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0133\3\2\2\2\u0135"+
		"\u0136\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0138\b\32\2\2\u0138\64\3\2\2"+
		"\2\u0139\u013d\7%\2\2\u013a\u013c\n\3\2\2\u013b\u013a\3\2\2\2\u013c\u013f"+
		"\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u0140\3\2\2\2\u013f"+
		"\u013d\3\2\2\2\u0140\u0141\b\33\3\2\u0141\66\3\2\2\2\u0142\u0143\7?\2"+
		"\2\u01438\3\2\2\2\u0144\u0165\5;\36\2\u0145\u0147\t\4\2\2\u0146\u0145"+
		"\3\2\2\2\u0147\u014a\3\2\2\2\u0148\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149"+
		"\u014b\3\2\2\2\u014a\u0148\3\2\2\2\u014b\u014d\7\60\2\2\u014c\u014e\t"+
		"\5\2\2\u014d\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u014d\3\2\2\2\u014f"+
		"\u0150\3\2\2\2\u0150\u0165\3\2\2\2\u0151\u0155\7/\2\2\u0152\u0154\t\6"+
		"\2\2\u0153\u0152\3\2\2\2\u0154\u0157\3\2\2\2\u0155\u0153\3\2\2\2\u0155"+
		"\u0156\3\2\2\2\u0156\u0158\3\2\2\2\u0157\u0155\3\2\2\2\u0158\u015a\7\60"+
		"\2\2\u0159\u015b\t\7\2\2\u015a\u0159\3\2\2\2\u015b\u015c\3\2\2\2\u015c"+
		"\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u0165\3\2\2\2\u015e\u0160\7/"+
		"\2\2\u015f\u0161\t\b\2\2\u0160\u015f\3\2\2\2\u0161\u0162\3\2\2\2\u0162"+
		"\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0165\3\2\2\2\u0164\u0144\3\2"+
		"\2\2\u0164\u0148\3\2\2\2\u0164\u0151\3\2\2\2\u0164\u015e\3\2\2\2\u0165"+
		":\3\2\2\2\u0166\u0168\t\t\2\2\u0167\u0166\3\2\2\2\u0168\u0169\3\2\2\2"+
		"\u0169\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a<\3\2\2\2\u016b\u016f\t"+
		"\n\2\2\u016c\u016e\t\13\2\2\u016d\u016c\3\2\2\2\u016e\u0171\3\2\2\2\u016f"+
		"\u016d\3\2\2\2\u016f\u0170\3\2\2\2\u0170>\3\2\2\2\u0171\u016f\3\2\2\2"+
		"\u0172\u0173\7u\2\2\u0173\u0174\7j\2\2\u0174\u0175\7k\2\2\u0175\u0176"+
		"\7h\2\2\u0176\u01ee\7v\2\2\u0177\u0178\7e\2\2\u0178\u0179\7v\2\2\u0179"+
		"\u017a\7t\2\2\u017a\u01ee\7n\2\2\u017b\u017c\7c\2\2\u017c\u017d\7n\2\2"+
		"\u017d\u01ee\7v\2\2\u017e\u017f\7o\2\2\u017f\u0180\7g\2\2\u0180\u0181"+
		"\7v\2\2\u0181\u01ee\7c\2\2\u0182\u0183\7u\2\2\u0183\u0184\7r\2\2\u0184"+
		"\u0185\7c\2\2\u0185\u0186\7e\2\2\u0186\u01ee\7g\2\2\u0187\u0188\7n\2\2"+
		"\u0188\u0189\7g\2\2\u0189\u018a\7h\2\2\u018a\u01ee\7v\2\2\u018b\u018c"+
		"\7t\2\2\u018c\u018d\7k\2\2\u018d\u018e\7i\2\2\u018e\u018f\7j\2\2\u018f"+
		"\u01ee\7v\2\2\u0190\u0191\7w\2\2\u0191\u01ee\7r\2\2\u0192\u0193\7f\2\2"+
		"\u0193\u0194\7q\2\2\u0194\u0195\7y\2\2\u0195\u01ee\7p\2\2\u0196\u0197"+
		"\7o\2\2\u0197\u0198\7k\2\2\u0198\u0199\7p\2\2\u0199\u019a\7w\2\2\u019a"+
		"\u01ee\7u\2\2\u019b\u019c\7g\2\2\u019c\u019d\7s\2\2\u019d\u019e\7w\2\2"+
		"\u019e\u019f\7c\2\2\u019f\u01a0\7n\2\2\u01a0\u01ee\7u\2\2\u01a1\u01a2"+
		"\7d\2\2\u01a2\u01a3\7c\2\2\u01a3\u01a4\7e\2\2\u01a4\u01a5\7m\2\2\u01a5"+
		"\u01a6\7u\2\2\u01a6\u01a7\7r\2\2\u01a7\u01a8\7c\2\2\u01a8\u01a9\7e\2\2"+
		"\u01a9\u01ee\7g\2\2\u01aa\u01ab\7q\2\2\u01ab\u01ac\7r\2\2\u01ac\u01ad"+
		"\7g\2\2\u01ad\u01ae\7p\2\2\u01ae\u01af\7d\2\2\u01af\u01b0\7t\2\2\u01b0"+
		"\u01b1\7c\2\2\u01b1\u01b2\7e\2\2\u01b2\u01b3\7m\2\2\u01b3\u01b4\7g\2\2"+
		"\u01b4\u01ee\7v\2\2\u01b5\u01b6\7e\2\2\u01b6\u01b7\7n\2\2\u01b7\u01b8"+
		"\7q\2\2\u01b8\u01b9\7u\2\2\u01b9\u01ba\7g\2\2\u01ba\u01bb\7d\2\2\u01bb"+
		"\u01bc\7t\2\2\u01bc\u01bd\7c\2\2\u01bd\u01be\7e\2\2\u01be\u01bf\7m\2\2"+
		"\u01bf\u01c0\7g\2\2\u01c0\u01ee\7v\2\2\u01c1\u01c2\7d\2\2\u01c2\u01c3"+
		"\7c\2\2\u01c3\u01c4\7e\2\2\u01c4\u01c5\7m\2\2\u01c5\u01c6\7u\2\2\u01c6"+
		"\u01c7\7n\2\2\u01c7\u01c8\7c\2\2\u01c8\u01c9\7u\2\2\u01c9\u01ee\7j\2\2"+
		"\u01ca\u01cb\7u\2\2\u01cb\u01cc\7g\2\2\u01cc\u01cd\7o\2\2\u01cd\u01ce"+
		"\7k\2\2\u01ce\u01cf\7e\2\2\u01cf\u01d0\7q\2\2\u01d0\u01d1\7n\2\2\u01d1"+
		"\u01d2\7q\2\2\u01d2\u01ee\7p\2\2\u01d3\u01d4\7s\2\2\u01d4\u01d5\7w\2\2"+
		"\u01d5\u01d6\7q\2\2\u01d6\u01d7\7v\2\2\u01d7\u01ee\7g\2\2\u01d8\u01d9"+
		"\7g\2\2\u01d9\u01da\7p\2\2\u01da\u01db\7v\2\2\u01db\u01dc\7g\2\2\u01dc"+
		"\u01ee\7t\2\2\u01dd\u01de\7e\2\2\u01de\u01df\7q\2\2\u01df\u01e0\7o\2\2"+
		"\u01e0\u01e1\7o\2\2\u01e1\u01ee\7c\2\2\u01e2\u01e3\7r\2\2\u01e3\u01e4"+
		"\7g\2\2\u01e4\u01e5\7t\2\2\u01e5\u01e6\7k\2\2\u01e6\u01e7\7q\2\2\u01e7"+
		"\u01ee\7f\2\2\u01e8\u01e9\7u\2\2\u01e9\u01ea\7n\2\2\u01ea\u01eb\7c\2\2"+
		"\u01eb\u01ec\7u\2\2\u01ec\u01ee\7j\2\2\u01ed\u0172\3\2\2\2\u01ed\u0177"+
		"\3\2\2\2\u01ed\u017b\3\2\2\2\u01ed\u017e\3\2\2\2\u01ed\u0182\3\2\2\2\u01ed"+
		"\u0187\3\2\2\2\u01ed\u018b\3\2\2\2\u01ed\u0190\3\2\2\2\u01ed\u0192\3\2"+
		"\2\2\u01ed\u0196\3\2\2\2\u01ed\u019b\3\2\2\2\u01ed\u01a1\3\2\2\2\u01ed"+
		"\u01aa\3\2\2\2\u01ed\u01b5\3\2\2\2\u01ed\u01c1\3\2\2\2\u01ed\u01ca\3\2"+
		"\2\2\u01ed\u01d3\3\2\2\2\u01ed\u01d8\3\2\2\2\u01ed\u01dd\3\2\2\2\u01ed"+
		"\u01e2\3\2\2\2\u01ed\u01e8\3\2\2\2\u01ee@\3\2\2\2\16\2\u0135\u013d\u0148"+
		"\u014f\u0155\u015c\u0162\u0164\u0169\u016f\u01ed";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}