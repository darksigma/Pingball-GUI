// Generated from src/pingball/grammars/BoardGrammar.g4 by ANTLR 4.0

package pingball.grammars;

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
		T__18=1, T__17=2, T__16=3, T__15=4, T__14=5, T__13=6, T__12=7, T__11=8, 
		T__10=9, T__9=10, T__8=11, T__7=12, T__6=13, T__5=14, T__4=15, T__3=16, 
		T__2=17, T__1=18, T__0=19, WHITESPACE=20, COMMENT=21, EQUALS=22, FLOAT=23, 
		INTEGER=24, NAME=25, BOARDNAME=26;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'yVelocity'", "'fire trigger'", "'squareBumper name'", "'friction1'", 
		"'absorber name'", "'gravity'", "'xVelocity'", "'orientation'", "'circleBumper name'", 
		"'height'", "'triangleBumper name'", "'x'", "'y'", "'action'", "'rightFlipper name'", 
		"'leftFlipper name'", "'ball name'", "'friction2'", "'width'", "WHITESPACE", 
		"COMMENT", "'='", "FLOAT", "INTEGER", "NAME", "'board name='"
	};
	public static final String[] ruleNames = {
		"T__18", "T__17", "T__16", "T__15", "T__14", "T__13", "T__12", "T__11", 
		"T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", 
		"T__1", "T__0", "WHITESPACE", "COMMENT", "EQUALS", "FLOAT", "INTEGER", 
		"NAME", "BOARDNAME"
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
		case 19: WHITESPACE_action((RuleContext)_localctx, actionIndex); break;

		case 20: COMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\2\4\34\u0157\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\6"+
		"\25\u010d\n\25\r\25\16\25\u010e\3\25\3\25\3\26\3\26\7\26\u0115\n\26\f"+
		"\26\16\26\u0118\13\26\3\26\3\26\3\27\3\27\3\30\3\30\7\30\u0120\n\30\f"+
		"\30\16\30\u0123\13\30\3\30\3\30\6\30\u0127\n\30\r\30\16\30\u0128\3\30"+
		"\3\30\7\30\u012d\n\30\f\30\16\30\u0130\13\30\3\30\3\30\6\30\u0134\n\30"+
		"\r\30\16\30\u0135\3\30\3\30\6\30\u013a\n\30\r\30\16\30\u013b\5\30\u013e"+
		"\n\30\3\31\6\31\u0141\n\31\r\31\16\31\u0142\3\32\3\32\7\32\u0147\n\32"+
		"\f\32\16\32\u014a\13\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\33\3\33\2\34\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1"+
		"\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24"+
		"\1\'\25\1)\26\2+\27\3-\30\1/\31\1\61\32\1\63\33\1\65\34\1\3\2\f\5\13\f"+
		"\17\17\"\"\4\f\f\17\17\3\62;\3\62;\3\62;\3\62;\3\62;\3\62;\5C\\aac|\6"+
		"\62;C\\aac|\u0162\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\3\67\3\2\2\2"+
		"\5A\3\2\2\2\7N\3\2\2\2\t`\3\2\2\2\13j\3\2\2\2\rx\3\2\2\2\17\u0080\3\2"+
		"\2\2\21\u008a\3\2\2\2\23\u0096\3\2\2\2\25\u00a8\3\2\2\2\27\u00af\3\2\2"+
		"\2\31\u00c3\3\2\2\2\33\u00c5\3\2\2\2\35\u00c7\3\2\2\2\37\u00ce\3\2\2\2"+
		"!\u00e0\3\2\2\2#\u00f1\3\2\2\2%\u00fb\3\2\2\2\'\u0105\3\2\2\2)\u010c\3"+
		"\2\2\2+\u0112\3\2\2\2-\u011b\3\2\2\2/\u013d\3\2\2\2\61\u0140\3\2\2\2\63"+
		"\u0144\3\2\2\2\65\u014b\3\2\2\2\678\7{\2\289\7X\2\29:\7g\2\2:;\7n\2\2"+
		";<\7q\2\2<=\7e\2\2=>\7k\2\2>?\7v\2\2?@\7{\2\2@\4\3\2\2\2AB\7h\2\2BC\7"+
		"k\2\2CD\7t\2\2DE\7g\2\2EF\7\"\2\2FG\7v\2\2GH\7t\2\2HI\7k\2\2IJ\7i\2\2"+
		"JK\7i\2\2KL\7g\2\2LM\7t\2\2M\6\3\2\2\2NO\7u\2\2OP\7s\2\2PQ\7w\2\2QR\7"+
		"c\2\2RS\7t\2\2ST\7g\2\2TU\7D\2\2UV\7w\2\2VW\7o\2\2WX\7r\2\2XY\7g\2\2Y"+
		"Z\7t\2\2Z[\7\"\2\2[\\\7p\2\2\\]\7c\2\2]^\7o\2\2^_\7g\2\2_\b\3\2\2\2`a"+
		"\7h\2\2ab\7t\2\2bc\7k\2\2cd\7e\2\2de\7v\2\2ef\7k\2\2fg\7q\2\2gh\7p\2\2"+
		"hi\7\63\2\2i\n\3\2\2\2jk\7c\2\2kl\7d\2\2lm\7u\2\2mn\7q\2\2no\7t\2\2op"+
		"\7d\2\2pq\7g\2\2qr\7t\2\2rs\7\"\2\2st\7p\2\2tu\7c\2\2uv\7o\2\2vw\7g\2"+
		"\2w\f\3\2\2\2xy\7i\2\2yz\7t\2\2z{\7c\2\2{|\7x\2\2|}\7k\2\2}~\7v\2\2~\177"+
		"\7{\2\2\177\16\3\2\2\2\u0080\u0081\7z\2\2\u0081\u0082\7X\2\2\u0082\u0083"+
		"\7g\2\2\u0083\u0084\7n\2\2\u0084\u0085\7q\2\2\u0085\u0086\7e\2\2\u0086"+
		"\u0087\7k\2\2\u0087\u0088\7v\2\2\u0088\u0089\7{\2\2\u0089\20\3\2\2\2\u008a"+
		"\u008b\7q\2\2\u008b\u008c\7t\2\2\u008c\u008d\7k\2\2\u008d\u008e\7g\2\2"+
		"\u008e\u008f\7p\2\2\u008f\u0090\7v\2\2\u0090\u0091\7c\2\2\u0091\u0092"+
		"\7v\2\2\u0092\u0093\7k\2\2\u0093\u0094\7q\2\2\u0094\u0095\7p\2\2\u0095"+
		"\22\3\2\2\2\u0096\u0097\7e\2\2\u0097\u0098\7k\2\2\u0098\u0099\7t\2\2\u0099"+
		"\u009a\7e\2\2\u009a\u009b\7n\2\2\u009b\u009c\7g\2\2\u009c\u009d\7D\2\2"+
		"\u009d\u009e\7w\2\2\u009e\u009f\7o\2\2\u009f\u00a0\7r\2\2\u00a0\u00a1"+
		"\7g\2\2\u00a1\u00a2\7t\2\2\u00a2\u00a3\7\"\2\2\u00a3\u00a4\7p\2\2\u00a4"+
		"\u00a5\7c\2\2\u00a5\u00a6\7o\2\2\u00a6\u00a7\7g\2\2\u00a7\24\3\2\2\2\u00a8"+
		"\u00a9\7j\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac\7i\2\2"+
		"\u00ac\u00ad\7j\2\2\u00ad\u00ae\7v\2\2\u00ae\26\3\2\2\2\u00af\u00b0\7"+
		"v\2\2\u00b0\u00b1\7t\2\2\u00b1\u00b2\7k\2\2\u00b2\u00b3\7c\2\2\u00b3\u00b4"+
		"\7p\2\2\u00b4\u00b5\7i\2\2\u00b5\u00b6\7n\2\2\u00b6\u00b7\7g\2\2\u00b7"+
		"\u00b8\7D\2\2\u00b8\u00b9\7w\2\2\u00b9\u00ba\7o\2\2\u00ba\u00bb\7r\2\2"+
		"\u00bb\u00bc\7g\2\2\u00bc\u00bd\7t\2\2\u00bd\u00be\7\"\2\2\u00be\u00bf"+
		"\7p\2\2\u00bf\u00c0\7c\2\2\u00c0\u00c1\7o\2\2\u00c1\u00c2\7g\2\2\u00c2"+
		"\30\3\2\2\2\u00c3\u00c4\7z\2\2\u00c4\32\3\2\2\2\u00c5\u00c6\7{\2\2\u00c6"+
		"\34\3\2\2\2\u00c7\u00c8\7c\2\2\u00c8\u00c9\7e\2\2\u00c9\u00ca\7v\2\2\u00ca"+
		"\u00cb\7k\2\2\u00cb\u00cc\7q\2\2\u00cc\u00cd\7p\2\2\u00cd\36\3\2\2\2\u00ce"+
		"\u00cf\7t\2\2\u00cf\u00d0\7k\2\2\u00d0\u00d1\7i\2\2\u00d1\u00d2\7j\2\2"+
		"\u00d2\u00d3\7v\2\2\u00d3\u00d4\7H\2\2\u00d4\u00d5\7n\2\2\u00d5\u00d6"+
		"\7k\2\2\u00d6\u00d7\7r\2\2\u00d7\u00d8\7r\2\2\u00d8\u00d9\7g\2\2\u00d9"+
		"\u00da\7t\2\2\u00da\u00db\7\"\2\2\u00db\u00dc\7p\2\2\u00dc\u00dd\7c\2"+
		"\2\u00dd\u00de\7o\2\2\u00de\u00df\7g\2\2\u00df \3\2\2\2\u00e0\u00e1\7"+
		"n\2\2\u00e1\u00e2\7g\2\2\u00e2\u00e3\7h\2\2\u00e3\u00e4\7v\2\2\u00e4\u00e5"+
		"\7H\2\2\u00e5\u00e6\7n\2\2\u00e6\u00e7\7k\2\2\u00e7\u00e8\7r\2\2\u00e8"+
		"\u00e9\7r\2\2\u00e9\u00ea\7g\2\2\u00ea\u00eb\7t\2\2\u00eb\u00ec\7\"\2"+
		"\2\u00ec\u00ed\7p\2\2\u00ed\u00ee\7c\2\2\u00ee\u00ef\7o\2\2\u00ef\u00f0"+
		"\7g\2\2\u00f0\"\3\2\2\2\u00f1\u00f2\7d\2\2\u00f2\u00f3\7c\2\2\u00f3\u00f4"+
		"\7n\2\2\u00f4\u00f5\7n\2\2\u00f5\u00f6\7\"\2\2\u00f6\u00f7\7p\2\2\u00f7"+
		"\u00f8\7c\2\2\u00f8\u00f9\7o\2\2\u00f9\u00fa\7g\2\2\u00fa$\3\2\2\2\u00fb"+
		"\u00fc\7h\2\2\u00fc\u00fd\7t\2\2\u00fd\u00fe\7k\2\2\u00fe\u00ff\7e\2\2"+
		"\u00ff\u0100\7v\2\2\u0100\u0101\7k\2\2\u0101\u0102\7q\2\2\u0102\u0103"+
		"\7p\2\2\u0103\u0104\7\64\2\2\u0104&\3\2\2\2\u0105\u0106\7y\2\2\u0106\u0107"+
		"\7k\2\2\u0107\u0108\7f\2\2\u0108\u0109\7v\2\2\u0109\u010a\7j\2\2\u010a"+
		"(\3\2\2\2\u010b\u010d\t\2\2\2\u010c\u010b\3\2\2\2\u010d\u010e\3\2\2\2"+
		"\u010e\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0111"+
		"\b\25\2\2\u0111*\3\2\2\2\u0112\u0116\7%\2\2\u0113\u0115\n\3\2\2\u0114"+
		"\u0113\3\2\2\2\u0115\u0118\3\2\2\2\u0116\u0114\3\2\2\2\u0116\u0117\3\2"+
		"\2\2\u0117\u0119\3\2\2\2\u0118\u0116\3\2\2\2\u0119\u011a\b\26\3\2\u011a"+
		",\3\2\2\2\u011b\u011c\7?\2\2\u011c.\3\2\2\2\u011d\u013e\5\61\31\2\u011e"+
		"\u0120\t\4\2\2\u011f\u011e\3\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2"+
		"\2\2\u0121\u0122\3\2\2\2\u0122\u0124\3\2\2\2\u0123\u0121\3\2\2\2\u0124"+
		"\u0126\7\60\2\2\u0125\u0127\t\5\2\2\u0126\u0125\3\2\2\2\u0127\u0128\3"+
		"\2\2\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u013e\3\2\2\2\u012a"+
		"\u012e\7/\2\2\u012b\u012d\t\6\2\2\u012c\u012b\3\2\2\2\u012d\u0130\3\2"+
		"\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0131\3\2\2\2\u0130"+
		"\u012e\3\2\2\2\u0131\u0133\7\60\2\2\u0132\u0134\t\7\2\2\u0133\u0132\3"+
		"\2\2\2\u0134\u0135\3\2\2\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136"+
		"\u013e\3\2\2\2\u0137\u0139\7/\2\2\u0138\u013a\t\b\2\2\u0139\u0138\3\2"+
		"\2\2\u013a\u013b\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c"+
		"\u013e\3\2\2\2\u013d\u011d\3\2\2\2\u013d\u0121\3\2\2\2\u013d\u012a\3\2"+
		"\2\2\u013d\u0137\3\2\2\2\u013e\60\3\2\2\2\u013f\u0141\t\t\2\2\u0140\u013f"+
		"\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143"+
		"\62\3\2\2\2\u0144\u0148\t\n\2\2\u0145\u0147\t\13\2\2\u0146\u0145\3\2\2"+
		"\2\u0147\u014a\3\2\2\2\u0148\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149\64"+
		"\3\2\2\2\u014a\u0148\3\2\2\2\u014b\u014c\7d\2\2\u014c\u014d\7q\2\2\u014d"+
		"\u014e\7c\2\2\u014e\u014f\7t\2\2\u014f\u0150\7f\2\2\u0150\u0151\7\"\2"+
		"\2\u0151\u0152\7p\2\2\u0152\u0153\7c\2\2\u0153\u0154\7o\2\2\u0154\u0155"+
		"\7g\2\2\u0155\u0156\7?\2\2\u0156\66\3\2\2\2\r\2\u010e\u0116\u0121\u0128"+
		"\u012e\u0135\u013b\u013d\u0142\u0148";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}