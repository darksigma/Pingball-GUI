// Generated from BoardGrammar.g4 by ANTLR 4.0

package parser;

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
		T__20=1, T__19=2, T__18=3, T__17=4, T__16=5, T__15=6, T__14=7, T__13=8, 
		T__12=9, T__11=10, T__10=11, T__9=12, T__8=13, T__7=14, T__6=15, T__5=16, 
		T__4=17, T__3=18, T__2=19, T__1=20, T__0=21, WHITESPACE=22, COMMENT=23, 
		EQUALS=24, FLOAT=25, INTEGER=26, NAME=27;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'yVelocity'", "'fire trigger'", "'squareBumper name'", "'friction1'", 
		"'name'", "'absorber name'", "'gravity'", "'board'", "'xVelocity'", "'circleBumper name'", 
		"'orientation'", "'height'", "'triangleBumper name'", "'x'", "'y'", "'action'", 
		"'rightFlipper name'", "'leftFlipper name'", "'ball name'", "'friction2'", 
		"'width'", "WHITESPACE", "COMMENT", "'='", "FLOAT", "INTEGER", "NAME"
	};
	public static final String[] ruleNames = {
		"T__20", "T__19", "T__18", "T__17", "T__16", "T__15", "T__14", "T__13", 
		"T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", 
		"T__3", "T__2", "T__1", "T__0", "WHITESPACE", "COMMENT", "EQUALS", "FLOAT", 
		"INTEGER", "NAME"
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
		case 21: WHITESPACE_action((RuleContext)_localctx, actionIndex); break;

		case 22: COMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\2\4\35\u0158\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\27\6\27\u011a\n\27\r\27\16\27\u011b\3\27\3\27\3\30\3\30\7"+
		"\30\u0122\n\30\f\30\16\30\u0125\13\30\3\30\3\30\3\31\3\31\3\32\3\32\7"+
		"\32\u012d\n\32\f\32\16\32\u0130\13\32\3\32\3\32\6\32\u0134\n\32\r\32\16"+
		"\32\u0135\3\32\3\32\7\32\u013a\n\32\f\32\16\32\u013d\13\32\3\32\3\32\6"+
		"\32\u0141\n\32\r\32\16\32\u0142\3\32\3\32\6\32\u0147\n\32\r\32\16\32\u0148"+
		"\5\32\u014b\n\32\3\33\6\33\u014e\n\33\r\33\16\33\u014f\3\34\3\34\7\34"+
		"\u0154\n\34\f\34\16\34\u0157\13\34\2\35\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1"+
		"\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37"+
		"\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\2/\31\3\61\32\1\63\33\1"+
		"\65\34\1\67\35\1\3\2\f\5\13\f\17\17\"\"\4\f\f\17\17\3\62;\3\62;\3\62;"+
		"\3\62;\3\62;\3\62;\5C\\aac|\6\62;C\\aac|\u0163\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2"+
		"\2\2\2\65\3\2\2\2\2\67\3\2\2\2\39\3\2\2\2\5C\3\2\2\2\7P\3\2\2\2\tb\3\2"+
		"\2\2\13l\3\2\2\2\rq\3\2\2\2\17\177\3\2\2\2\21\u0087\3\2\2\2\23\u008d\3"+
		"\2\2\2\25\u0097\3\2\2\2\27\u00a9\3\2\2\2\31\u00b5\3\2\2\2\33\u00bc\3\2"+
		"\2\2\35\u00d0\3\2\2\2\37\u00d2\3\2\2\2!\u00d4\3\2\2\2#\u00db\3\2\2\2%"+
		"\u00ed\3\2\2\2\'\u00fe\3\2\2\2)\u0108\3\2\2\2+\u0112\3\2\2\2-\u0119\3"+
		"\2\2\2/\u011f\3\2\2\2\61\u0128\3\2\2\2\63\u014a\3\2\2\2\65\u014d\3\2\2"+
		"\2\67\u0151\3\2\2\29:\7{\2\2:;\7X\2\2;<\7g\2\2<=\7n\2\2=>\7q\2\2>?\7e"+
		"\2\2?@\7k\2\2@A\7v\2\2AB\7{\2\2B\4\3\2\2\2CD\7h\2\2DE\7k\2\2EF\7t\2\2"+
		"FG\7g\2\2GH\7\"\2\2HI\7v\2\2IJ\7t\2\2JK\7k\2\2KL\7i\2\2LM\7i\2\2MN\7g"+
		"\2\2NO\7t\2\2O\6\3\2\2\2PQ\7u\2\2QR\7s\2\2RS\7w\2\2ST\7c\2\2TU\7t\2\2"+
		"UV\7g\2\2VW\7D\2\2WX\7w\2\2XY\7o\2\2YZ\7r\2\2Z[\7g\2\2[\\\7t\2\2\\]\7"+
		"\"\2\2]^\7p\2\2^_\7c\2\2_`\7o\2\2`a\7g\2\2a\b\3\2\2\2bc\7h\2\2cd\7t\2"+
		"\2de\7k\2\2ef\7e\2\2fg\7v\2\2gh\7k\2\2hi\7q\2\2ij\7p\2\2jk\7\63\2\2k\n"+
		"\3\2\2\2lm\7p\2\2mn\7c\2\2no\7o\2\2op\7g\2\2p\f\3\2\2\2qr\7c\2\2rs\7d"+
		"\2\2st\7u\2\2tu\7q\2\2uv\7t\2\2vw\7d\2\2wx\7g\2\2xy\7t\2\2yz\7\"\2\2z"+
		"{\7p\2\2{|\7c\2\2|}\7o\2\2}~\7g\2\2~\16\3\2\2\2\177\u0080\7i\2\2\u0080"+
		"\u0081\7t\2\2\u0081\u0082\7c\2\2\u0082\u0083\7x\2\2\u0083\u0084\7k\2\2"+
		"\u0084\u0085\7v\2\2\u0085\u0086\7{\2\2\u0086\20\3\2\2\2\u0087\u0088\7"+
		"d\2\2\u0088\u0089\7q\2\2\u0089\u008a\7c\2\2\u008a\u008b\7t\2\2\u008b\u008c"+
		"\7f\2\2\u008c\22\3\2\2\2\u008d\u008e\7z\2\2\u008e\u008f\7X\2\2\u008f\u0090"+
		"\7g\2\2\u0090\u0091\7n\2\2\u0091\u0092\7q\2\2\u0092\u0093\7e\2\2\u0093"+
		"\u0094\7k\2\2\u0094\u0095\7v\2\2\u0095\u0096\7{\2\2\u0096\24\3\2\2\2\u0097"+
		"\u0098\7e\2\2\u0098\u0099\7k\2\2\u0099\u009a\7t\2\2\u009a\u009b\7e\2\2"+
		"\u009b\u009c\7n\2\2\u009c\u009d\7g\2\2\u009d\u009e\7D\2\2\u009e\u009f"+
		"\7w\2\2\u009f\u00a0\7o\2\2\u00a0\u00a1\7r\2\2\u00a1\u00a2\7g\2\2\u00a2"+
		"\u00a3\7t\2\2\u00a3\u00a4\7\"\2\2\u00a4\u00a5\7p\2\2\u00a5\u00a6\7c\2"+
		"\2\u00a6\u00a7\7o\2\2\u00a7\u00a8\7g\2\2\u00a8\26\3\2\2\2\u00a9\u00aa"+
		"\7q\2\2\u00aa\u00ab\7t\2\2\u00ab\u00ac\7k\2\2\u00ac\u00ad\7g\2\2\u00ad"+
		"\u00ae\7p\2\2\u00ae\u00af\7v\2\2\u00af\u00b0\7c\2\2\u00b0\u00b1\7v\2\2"+
		"\u00b1\u00b2\7k\2\2\u00b2\u00b3\7q\2\2\u00b3\u00b4\7p\2\2\u00b4\30\3\2"+
		"\2\2\u00b5\u00b6\7j\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8\7k\2\2\u00b8\u00b9"+
		"\7i\2\2\u00b9\u00ba\7j\2\2\u00ba\u00bb\7v\2\2\u00bb\32\3\2\2\2\u00bc\u00bd"+
		"\7v\2\2\u00bd\u00be\7t\2\2\u00be\u00bf\7k\2\2\u00bf\u00c0\7c\2\2\u00c0"+
		"\u00c1\7p\2\2\u00c1\u00c2\7i\2\2\u00c2\u00c3\7n\2\2\u00c3\u00c4\7g\2\2"+
		"\u00c4\u00c5\7D\2\2\u00c5\u00c6\7w\2\2\u00c6\u00c7\7o\2\2\u00c7\u00c8"+
		"\7r\2\2\u00c8\u00c9\7g\2\2\u00c9\u00ca\7t\2\2\u00ca\u00cb\7\"\2\2\u00cb"+
		"\u00cc\7p\2\2\u00cc\u00cd\7c\2\2\u00cd\u00ce\7o\2\2\u00ce\u00cf\7g\2\2"+
		"\u00cf\34\3\2\2\2\u00d0\u00d1\7z\2\2\u00d1\36\3\2\2\2\u00d2\u00d3\7{\2"+
		"\2\u00d3 \3\2\2\2\u00d4\u00d5\7c\2\2\u00d5\u00d6\7e\2\2\u00d6\u00d7\7"+
		"v\2\2\u00d7\u00d8\7k\2\2\u00d8\u00d9\7q\2\2\u00d9\u00da\7p\2\2\u00da\""+
		"\3\2\2\2\u00db\u00dc\7t\2\2\u00dc\u00dd\7k\2\2\u00dd\u00de\7i\2\2\u00de"+
		"\u00df\7j\2\2\u00df\u00e0\7v\2\2\u00e0\u00e1\7H\2\2\u00e1\u00e2\7n\2\2"+
		"\u00e2\u00e3\7k\2\2\u00e3\u00e4\7r\2\2\u00e4\u00e5\7r\2\2\u00e5\u00e6"+
		"\7g\2\2\u00e6\u00e7\7t\2\2\u00e7\u00e8\7\"\2\2\u00e8\u00e9\7p\2\2\u00e9"+
		"\u00ea\7c\2\2\u00ea\u00eb\7o\2\2\u00eb\u00ec\7g\2\2\u00ec$\3\2\2\2\u00ed"+
		"\u00ee\7n\2\2\u00ee\u00ef\7g\2\2\u00ef\u00f0\7h\2\2\u00f0\u00f1\7v\2\2"+
		"\u00f1\u00f2\7H\2\2\u00f2\u00f3\7n\2\2\u00f3\u00f4\7k\2\2\u00f4\u00f5"+
		"\7r\2\2\u00f5\u00f6\7r\2\2\u00f6\u00f7\7g\2\2\u00f7\u00f8\7t\2\2\u00f8"+
		"\u00f9\7\"\2\2\u00f9\u00fa\7p\2\2\u00fa\u00fb\7c\2\2\u00fb\u00fc\7o\2"+
		"\2\u00fc\u00fd\7g\2\2\u00fd&\3\2\2\2\u00fe\u00ff\7d\2\2\u00ff\u0100\7"+
		"c\2\2\u0100\u0101\7n\2\2\u0101\u0102\7n\2\2\u0102\u0103\7\"\2\2\u0103"+
		"\u0104\7p\2\2\u0104\u0105\7c\2\2\u0105\u0106\7o\2\2\u0106\u0107\7g\2\2"+
		"\u0107(\3\2\2\2\u0108\u0109\7h\2\2\u0109\u010a\7t\2\2\u010a\u010b\7k\2"+
		"\2\u010b\u010c\7e\2\2\u010c\u010d\7v\2\2\u010d\u010e\7k\2\2\u010e\u010f"+
		"\7q\2\2\u010f\u0110\7p\2\2\u0110\u0111\7\64\2\2\u0111*\3\2\2\2\u0112\u0113"+
		"\7y\2\2\u0113\u0114\7k\2\2\u0114\u0115\7f\2\2\u0115\u0116\7v\2\2\u0116"+
		"\u0117\7j\2\2\u0117,\3\2\2\2\u0118\u011a\t\2\2\2\u0119\u0118\3\2\2\2\u011a"+
		"\u011b\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011d\3\2"+
		"\2\2\u011d\u011e\b\27\2\2\u011e.\3\2\2\2\u011f\u0123\7%\2\2\u0120\u0122"+
		"\n\3\2\2\u0121\u0120\3\2\2\2\u0122\u0125\3\2\2\2\u0123\u0121\3\2\2\2\u0123"+
		"\u0124\3\2\2\2\u0124\u0126\3\2\2\2\u0125\u0123\3\2\2\2\u0126\u0127\b\30"+
		"\3\2\u0127\60\3\2\2\2\u0128\u0129\7?\2\2\u0129\62\3\2\2\2\u012a\u014b"+
		"\5\65\33\2\u012b\u012d\t\4\2\2\u012c\u012b\3\2\2\2\u012d\u0130\3\2\2\2"+
		"\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0131\3\2\2\2\u0130\u012e"+
		"\3\2\2\2\u0131\u0133\7\60\2\2\u0132\u0134\t\5\2\2\u0133\u0132\3\2\2\2"+
		"\u0134\u0135\3\2\2\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u014b"+
		"\3\2\2\2\u0137\u013b\7/\2\2\u0138\u013a\t\6\2\2\u0139\u0138\3\2\2\2\u013a"+
		"\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013e\3\2"+
		"\2\2\u013d\u013b\3\2\2\2\u013e\u0140\7\60\2\2\u013f\u0141\t\7\2\2\u0140"+
		"\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0140\3\2\2\2\u0142\u0143\3\2"+
		"\2\2\u0143\u014b\3\2\2\2\u0144\u0146\7/\2\2\u0145\u0147\t\b\2\2\u0146"+
		"\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u0146\3\2\2\2\u0148\u0149\3\2"+
		"\2\2\u0149\u014b\3\2\2\2\u014a\u012a\3\2\2\2\u014a\u012e\3\2\2\2\u014a"+
		"\u0137\3\2\2\2\u014a\u0144\3\2\2\2\u014b\64\3\2\2\2\u014c\u014e\t\t\2"+
		"\2\u014d\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u014d\3\2\2\2\u014f\u0150"+
		"\3\2\2\2\u0150\66\3\2\2\2\u0151\u0155\t\n\2\2\u0152\u0154\t\13\2\2\u0153"+
		"\u0152\3\2\2\2\u0154\u0157\3\2\2\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2"+
		"\2\2\u01568\3\2\2\2\u0157\u0155\3\2\2\2\r\2\u011b\u0123\u012e\u0135\u013b"+
		"\u0142\u0148\u014a\u014f\u0155";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}