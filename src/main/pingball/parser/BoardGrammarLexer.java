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
		T__28=1, T__27=2, T__26=3, T__25=4, T__24=5, T__23=6, T__22=7, T__21=8, 
		T__20=9, T__19=10, T__18=11, T__17=12, T__16=13, T__15=14, T__14=15, T__13=16, 
		T__12=17, T__11=18, T__10=19, T__9=20, T__8=21, T__7=22, T__6=23, T__5=24, 
		T__4=25, T__3=26, T__2=27, T__1=28, T__0=29, WHITESPACE=30, COMMENT=31, 
		EQUALS=32, FLOAT=33, INTEGER=34, NAME=35, KEY=36;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'yVelocity'", "'name'", "'friction1'", "'gravity'", "'ball'", "'otherBoard'", 
		"'y'", "'action'", "'fire'", "'triangleBumper'", "'key'", "'squareBumper'", 
		"'friction2'", "'circleBumper'", "'keyup'", "'otherPortal'", "'board'", 
		"'xVelocity'", "'portal'", "'orientation'", "'height'", "'ballSpawner'", 
		"'x'", "'absorber'", "'trigger'", "'leftFlipper'", "'width'", "'keydown'", 
		"'rightFlipper'", "WHITESPACE", "COMMENT", "'='", "FLOAT", "INTEGER", 
		"NAME", "KEY"
	};
	public static final String[] ruleNames = {
		"T__28", "T__27", "T__26", "T__25", "T__24", "T__23", "T__22", "T__21", 
		"T__20", "T__19", "T__18", "T__17", "T__16", "T__15", "T__14", "T__13", 
		"T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", 
		"T__3", "T__2", "T__1", "T__0", "WHITESPACE", "COMMENT", "EQUALS", "FLOAT", 
		"INTEGER", "NAME", "KEY"
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
	     * rtFlipLine, lftFlipLine, absorberLine, and fireLine, keyPressLine, portalLine, ballSpawnerLine
	     * in any order. Each line defines the type of object - board, ball, keypress, or specific gadget.
	     *
	     * Keypress parsing has to be handled with special cases for keys that are numerics, text, and
	     * the letters 'x' or 'y', because all of these behave specially as tokens. Numerics and text are
	     * two different types of tokens and therefore can't be possibilities for the same token.
	     * 'x' and 'y' are fields that are used to parse and therefore can't be tokens. In addition, anything
	     * else used for parsing cannot be a token in ANTLR (unless parsed specially like with keypresses of
	     * 'x' and 'y'), so no tokens can have values of 'name', 'x', 'y', 'xVelocity', 'yVelocity', etc.
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
		case 29: WHITESPACE_action((RuleContext)_localctx, actionIndex); break;

		case 30: COMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\2\4&\u0200\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3"+
		"\36\3\36\3\36\3\36\3\36\3\37\6\37\u0145\n\37\r\37\16\37\u0146\3\37\3\37"+
		"\3 \3 \7 \u014d\n \f \16 \u0150\13 \3 \3 \3!\3!\3\"\3\"\7\"\u0158\n\""+
		"\f\"\16\"\u015b\13\"\3\"\3\"\6\"\u015f\n\"\r\"\16\"\u0160\3\"\3\"\7\""+
		"\u0165\n\"\f\"\16\"\u0168\13\"\3\"\3\"\6\"\u016c\n\"\r\"\16\"\u016d\3"+
		"\"\3\"\6\"\u0172\n\"\r\"\16\"\u0173\5\"\u0176\n\"\3#\6#\u0179\n#\r#\16"+
		"#\u017a\3$\3$\7$\u017f\n$\f$\16$\u0182\13$\3%\3%\3%\3%\3%\3%\3%\3%\3%"+
		"\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%"+
		"\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%"+
		"\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%"+
		"\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%"+
		"\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\5%"+
		"\u01ff\n%\2&\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13"+
		"\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25"+
		"\1)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1"+
		"= \2?!\3A\"\1C#\1E$\1G%\1I&\1\3\2\f\5\13\f\17\17\"\"\4\f\f\17\17\3\62"+
		";\3\62;\3\62;\3\62;\3\62;\3\62;\5C\\aac|\6\62;C\\aac|\u021f\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2"+
		"\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3"+
		"\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2"+
		"\2\3K\3\2\2\2\5U\3\2\2\2\7Z\3\2\2\2\td\3\2\2\2\13l\3\2\2\2\rq\3\2\2\2"+
		"\17|\3\2\2\2\21~\3\2\2\2\23\u0085\3\2\2\2\25\u008a\3\2\2\2\27\u0099\3"+
		"\2\2\2\31\u009d\3\2\2\2\33\u00aa\3\2\2\2\35\u00b4\3\2\2\2\37\u00c1\3\2"+
		"\2\2!\u00c7\3\2\2\2#\u00d3\3\2\2\2%\u00d9\3\2\2\2\'\u00e3\3\2\2\2)\u00ea"+
		"\3\2\2\2+\u00f6\3\2\2\2-\u00fd\3\2\2\2/\u0109\3\2\2\2\61\u010b\3\2\2\2"+
		"\63\u0114\3\2\2\2\65\u011c\3\2\2\2\67\u0128\3\2\2\29\u012e\3\2\2\2;\u0136"+
		"\3\2\2\2=\u0144\3\2\2\2?\u014a\3\2\2\2A\u0153\3\2\2\2C\u0175\3\2\2\2E"+
		"\u0178\3\2\2\2G\u017c\3\2\2\2I\u01fe\3\2\2\2KL\7{\2\2LM\7X\2\2MN\7g\2"+
		"\2NO\7n\2\2OP\7q\2\2PQ\7e\2\2QR\7k\2\2RS\7v\2\2ST\7{\2\2T\4\3\2\2\2UV"+
		"\7p\2\2VW\7c\2\2WX\7o\2\2XY\7g\2\2Y\6\3\2\2\2Z[\7h\2\2[\\\7t\2\2\\]\7"+
		"k\2\2]^\7e\2\2^_\7v\2\2_`\7k\2\2`a\7q\2\2ab\7p\2\2bc\7\63\2\2c\b\3\2\2"+
		"\2de\7i\2\2ef\7t\2\2fg\7c\2\2gh\7x\2\2hi\7k\2\2ij\7v\2\2jk\7{\2\2k\n\3"+
		"\2\2\2lm\7d\2\2mn\7c\2\2no\7n\2\2op\7n\2\2p\f\3\2\2\2qr\7q\2\2rs\7v\2"+
		"\2st\7j\2\2tu\7g\2\2uv\7t\2\2vw\7D\2\2wx\7q\2\2xy\7c\2\2yz\7t\2\2z{\7"+
		"f\2\2{\16\3\2\2\2|}\7{\2\2}\20\3\2\2\2~\177\7c\2\2\177\u0080\7e\2\2\u0080"+
		"\u0081\7v\2\2\u0081\u0082\7k\2\2\u0082\u0083\7q\2\2\u0083\u0084\7p\2\2"+
		"\u0084\22\3\2\2\2\u0085\u0086\7h\2\2\u0086\u0087\7k\2\2\u0087\u0088\7"+
		"t\2\2\u0088\u0089\7g\2\2\u0089\24\3\2\2\2\u008a\u008b\7v\2\2\u008b\u008c"+
		"\7t\2\2\u008c\u008d\7k\2\2\u008d\u008e\7c\2\2\u008e\u008f\7p\2\2\u008f"+
		"\u0090\7i\2\2\u0090\u0091\7n\2\2\u0091\u0092\7g\2\2\u0092\u0093\7D\2\2"+
		"\u0093\u0094\7w\2\2\u0094\u0095\7o\2\2\u0095\u0096\7r\2\2\u0096\u0097"+
		"\7g\2\2\u0097\u0098\7t\2\2\u0098\26\3\2\2\2\u0099\u009a\7m\2\2\u009a\u009b"+
		"\7g\2\2\u009b\u009c\7{\2\2\u009c\30\3\2\2\2\u009d\u009e\7u\2\2\u009e\u009f"+
		"\7s\2\2\u009f\u00a0\7w\2\2\u00a0\u00a1\7c\2\2\u00a1\u00a2\7t\2\2\u00a2"+
		"\u00a3\7g\2\2\u00a3\u00a4\7D\2\2\u00a4\u00a5\7w\2\2\u00a5\u00a6\7o\2\2"+
		"\u00a6\u00a7\7r\2\2\u00a7\u00a8\7g\2\2\u00a8\u00a9\7t\2\2\u00a9\32\3\2"+
		"\2\2\u00aa\u00ab\7h\2\2\u00ab\u00ac\7t\2\2\u00ac\u00ad\7k\2\2\u00ad\u00ae"+
		"\7e\2\2\u00ae\u00af\7v\2\2\u00af\u00b0\7k\2\2\u00b0\u00b1\7q\2\2\u00b1"+
		"\u00b2\7p\2\2\u00b2\u00b3\7\64\2\2\u00b3\34\3\2\2\2\u00b4\u00b5\7e\2\2"+
		"\u00b5\u00b6\7k\2\2\u00b6\u00b7\7t\2\2\u00b7\u00b8\7e\2\2\u00b8\u00b9"+
		"\7n\2\2\u00b9\u00ba\7g\2\2\u00ba\u00bb\7D\2\2\u00bb\u00bc\7w\2\2\u00bc"+
		"\u00bd\7o\2\2\u00bd\u00be\7r\2\2\u00be\u00bf\7g\2\2\u00bf\u00c0\7t\2\2"+
		"\u00c0\36\3\2\2\2\u00c1\u00c2\7m\2\2\u00c2\u00c3\7g\2\2\u00c3\u00c4\7"+
		"{\2\2\u00c4\u00c5\7w\2\2\u00c5\u00c6\7r\2\2\u00c6 \3\2\2\2\u00c7\u00c8"+
		"\7q\2\2\u00c8\u00c9\7v\2\2\u00c9\u00ca\7j\2\2\u00ca\u00cb\7g\2\2\u00cb"+
		"\u00cc\7t\2\2\u00cc\u00cd\7R\2\2\u00cd\u00ce\7q\2\2\u00ce\u00cf\7t\2\2"+
		"\u00cf\u00d0\7v\2\2\u00d0\u00d1\7c\2\2\u00d1\u00d2\7n\2\2\u00d2\"\3\2"+
		"\2\2\u00d3\u00d4\7d\2\2\u00d4\u00d5\7q\2\2\u00d5\u00d6\7c\2\2\u00d6\u00d7"+
		"\7t\2\2\u00d7\u00d8\7f\2\2\u00d8$\3\2\2\2\u00d9\u00da\7z\2\2\u00da\u00db"+
		"\7X\2\2\u00db\u00dc\7g\2\2\u00dc\u00dd\7n\2\2\u00dd\u00de\7q\2\2\u00de"+
		"\u00df\7e\2\2\u00df\u00e0\7k\2\2\u00e0\u00e1\7v\2\2\u00e1\u00e2\7{\2\2"+
		"\u00e2&\3\2\2\2\u00e3\u00e4\7r\2\2\u00e4\u00e5\7q\2\2\u00e5\u00e6\7t\2"+
		"\2\u00e6\u00e7\7v\2\2\u00e7\u00e8\7c\2\2\u00e8\u00e9\7n\2\2\u00e9(\3\2"+
		"\2\2\u00ea\u00eb\7q\2\2\u00eb\u00ec\7t\2\2\u00ec\u00ed\7k\2\2\u00ed\u00ee"+
		"\7g\2\2\u00ee\u00ef\7p\2\2\u00ef\u00f0\7v\2\2\u00f0\u00f1\7c\2\2\u00f1"+
		"\u00f2\7v\2\2\u00f2\u00f3\7k\2\2\u00f3\u00f4\7q\2\2\u00f4\u00f5\7p\2\2"+
		"\u00f5*\3\2\2\2\u00f6\u00f7\7j\2\2\u00f7\u00f8\7g\2\2\u00f8\u00f9\7k\2"+
		"\2\u00f9\u00fa\7i\2\2\u00fa\u00fb\7j\2\2\u00fb\u00fc\7v\2\2\u00fc,\3\2"+
		"\2\2\u00fd\u00fe\7d\2\2\u00fe\u00ff\7c\2\2\u00ff\u0100\7n\2\2\u0100\u0101"+
		"\7n\2\2\u0101\u0102\7U\2\2\u0102\u0103\7r\2\2\u0103\u0104\7c\2\2\u0104"+
		"\u0105\7y\2\2\u0105\u0106\7p\2\2\u0106\u0107\7g\2\2\u0107\u0108\7t\2\2"+
		"\u0108.\3\2\2\2\u0109\u010a\7z\2\2\u010a\60\3\2\2\2\u010b\u010c\7c\2\2"+
		"\u010c\u010d\7d\2\2\u010d\u010e\7u\2\2\u010e\u010f\7q\2\2\u010f\u0110"+
		"\7t\2\2\u0110\u0111\7d\2\2\u0111\u0112\7g\2\2\u0112\u0113\7t\2\2\u0113"+
		"\62\3\2\2\2\u0114\u0115\7v\2\2\u0115\u0116\7t\2\2\u0116\u0117\7k\2\2\u0117"+
		"\u0118\7i\2\2\u0118\u0119\7i\2\2\u0119\u011a\7g\2\2\u011a\u011b\7t\2\2"+
		"\u011b\64\3\2\2\2\u011c\u011d\7n\2\2\u011d\u011e\7g\2\2\u011e\u011f\7"+
		"h\2\2\u011f\u0120\7v\2\2\u0120\u0121\7H\2\2\u0121\u0122\7n\2\2\u0122\u0123"+
		"\7k\2\2\u0123\u0124\7r\2\2\u0124\u0125\7r\2\2\u0125\u0126\7g\2\2\u0126"+
		"\u0127\7t\2\2\u0127\66\3\2\2\2\u0128\u0129\7y\2\2\u0129\u012a\7k\2\2\u012a"+
		"\u012b\7f\2\2\u012b\u012c\7v\2\2\u012c\u012d\7j\2\2\u012d8\3\2\2\2\u012e"+
		"\u012f\7m\2\2\u012f\u0130\7g\2\2\u0130\u0131\7{\2\2\u0131\u0132\7f\2\2"+
		"\u0132\u0133\7q\2\2\u0133\u0134\7y\2\2\u0134\u0135\7p\2\2\u0135:\3\2\2"+
		"\2\u0136\u0137\7t\2\2\u0137\u0138\7k\2\2\u0138\u0139\7i\2\2\u0139\u013a"+
		"\7j\2\2\u013a\u013b\7v\2\2\u013b\u013c\7H\2\2\u013c\u013d\7n\2\2\u013d"+
		"\u013e\7k\2\2\u013e\u013f\7r\2\2\u013f\u0140\7r\2\2\u0140\u0141\7g\2\2"+
		"\u0141\u0142\7t\2\2\u0142<\3\2\2\2\u0143\u0145\t\2\2\2\u0144\u0143\3\2"+
		"\2\2\u0145\u0146\3\2\2\2\u0146\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147"+
		"\u0148\3\2\2\2\u0148\u0149\b\37\2\2\u0149>\3\2\2\2\u014a\u014e\7%\2\2"+
		"\u014b\u014d\n\3\2\2\u014c\u014b\3\2\2\2\u014d\u0150\3\2\2\2\u014e\u014c"+
		"\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0151\3\2\2\2\u0150\u014e\3\2\2\2\u0151"+
		"\u0152\b \3\2\u0152@\3\2\2\2\u0153\u0154\7?\2\2\u0154B\3\2\2\2\u0155\u0176"+
		"\5E#\2\u0156\u0158\t\4\2\2\u0157\u0156\3\2\2\2\u0158\u015b\3\2\2\2\u0159"+
		"\u0157\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u015c\3\2\2\2\u015b\u0159\3\2"+
		"\2\2\u015c\u015e\7\60\2\2\u015d\u015f\t\5\2\2\u015e\u015d\3\2\2\2\u015f"+
		"\u0160\3\2\2\2\u0160\u015e\3\2\2\2\u0160\u0161\3\2\2\2\u0161\u0176\3\2"+
		"\2\2\u0162\u0166\7/\2\2\u0163\u0165\t\6\2\2\u0164\u0163\3\2\2\2\u0165"+
		"\u0168\3\2\2\2\u0166\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u0169\3\2"+
		"\2\2\u0168\u0166\3\2\2\2\u0169\u016b\7\60\2\2\u016a\u016c\t\7\2\2\u016b"+
		"\u016a\3\2\2\2\u016c\u016d\3\2\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2"+
		"\2\2\u016e\u0176\3\2\2\2\u016f\u0171\7/\2\2\u0170\u0172\t\b\2\2\u0171"+
		"\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173\u0171\3\2\2\2\u0173\u0174\3\2"+
		"\2\2\u0174\u0176\3\2\2\2\u0175\u0155\3\2\2\2\u0175\u0159\3\2\2\2\u0175"+
		"\u0162\3\2\2\2\u0175\u016f\3\2\2\2\u0176D\3\2\2\2\u0177\u0179\t\t\2\2"+
		"\u0178\u0177\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u0178\3\2\2\2\u017a\u017b"+
		"\3\2\2\2\u017bF\3\2\2\2\u017c\u0180\t\n\2\2\u017d\u017f\t\13\2\2\u017e"+
		"\u017d\3\2\2\2\u017f\u0182\3\2\2\2\u0180\u017e\3\2\2\2\u0180\u0181\3\2"+
		"\2\2\u0181H\3\2\2\2\u0182\u0180\3\2\2\2\u0183\u0184\7u\2\2\u0184\u0185"+
		"\7j\2\2\u0185\u0186\7k\2\2\u0186\u0187\7h\2\2\u0187\u01ff\7v\2\2\u0188"+
		"\u0189\7e\2\2\u0189\u018a\7v\2\2\u018a\u018b\7t\2\2\u018b\u01ff\7n\2\2"+
		"\u018c\u018d\7c\2\2\u018d\u018e\7n\2\2\u018e\u01ff\7v\2\2\u018f\u0190"+
		"\7o\2\2\u0190\u0191\7g\2\2\u0191\u0192\7v\2\2\u0192\u01ff\7c\2\2\u0193"+
		"\u0194\7u\2\2\u0194\u0195\7r\2\2\u0195\u0196\7c\2\2\u0196\u0197\7e\2\2"+
		"\u0197\u01ff\7g\2\2\u0198\u0199\7n\2\2\u0199\u019a\7g\2\2\u019a\u019b"+
		"\7h\2\2\u019b\u01ff\7v\2\2\u019c\u019d\7t\2\2\u019d\u019e\7k\2\2\u019e"+
		"\u019f\7i\2\2\u019f\u01a0\7j\2\2\u01a0\u01ff\7v\2\2\u01a1\u01a2\7w\2\2"+
		"\u01a2\u01ff\7r\2\2\u01a3\u01a4\7f\2\2\u01a4\u01a5\7q\2\2\u01a5\u01a6"+
		"\7y\2\2\u01a6\u01ff\7p\2\2\u01a7\u01a8\7o\2\2\u01a8\u01a9\7k\2\2\u01a9"+
		"\u01aa\7p\2\2\u01aa\u01ab\7w\2\2\u01ab\u01ff\7u\2\2\u01ac\u01ad\7g\2\2"+
		"\u01ad\u01ae\7s\2\2\u01ae\u01af\7w\2\2\u01af\u01b0\7c\2\2\u01b0\u01b1"+
		"\7n\2\2\u01b1\u01ff\7u\2\2\u01b2\u01b3\7d\2\2\u01b3\u01b4\7c\2\2\u01b4"+
		"\u01b5\7e\2\2\u01b5\u01b6\7m\2\2\u01b6\u01b7\7u\2\2\u01b7\u01b8\7r\2\2"+
		"\u01b8\u01b9\7c\2\2\u01b9\u01ba\7e\2\2\u01ba\u01ff\7g\2\2\u01bb\u01bc"+
		"\7q\2\2\u01bc\u01bd\7r\2\2\u01bd\u01be\7g\2\2\u01be\u01bf\7p\2\2\u01bf"+
		"\u01c0\7d\2\2\u01c0\u01c1\7t\2\2\u01c1\u01c2\7c\2\2\u01c2\u01c3\7e\2\2"+
		"\u01c3\u01c4\7m\2\2\u01c4\u01c5\7g\2\2\u01c5\u01ff\7v\2\2\u01c6\u01c7"+
		"\7e\2\2\u01c7\u01c8\7n\2\2\u01c8\u01c9\7q\2\2\u01c9\u01ca\7u\2\2\u01ca"+
		"\u01cb\7g\2\2\u01cb\u01cc\7d\2\2\u01cc\u01cd\7t\2\2\u01cd\u01ce\7c\2\2"+
		"\u01ce\u01cf\7e\2\2\u01cf\u01d0\7m\2\2\u01d0\u01d1\7g\2\2\u01d1\u01ff"+
		"\7v\2\2\u01d2\u01d3\7d\2\2\u01d3\u01d4\7c\2\2\u01d4\u01d5\7e\2\2\u01d5"+
		"\u01d6\7m\2\2\u01d6\u01d7\7u\2\2\u01d7\u01d8\7n\2\2\u01d8\u01d9\7c\2\2"+
		"\u01d9\u01da\7u\2\2\u01da\u01ff\7j\2\2\u01db\u01dc\7u\2\2\u01dc\u01dd"+
		"\7g\2\2\u01dd\u01de\7o\2\2\u01de\u01df\7k\2\2\u01df\u01e0\7e\2\2\u01e0"+
		"\u01e1\7q\2\2\u01e1\u01e2\7n\2\2\u01e2\u01e3\7q\2\2\u01e3\u01ff\7p\2\2"+
		"\u01e4\u01e5\7s\2\2\u01e5\u01e6\7w\2\2\u01e6\u01e7\7q\2\2\u01e7\u01e8"+
		"\7v\2\2\u01e8\u01ff\7g\2\2\u01e9\u01ea\7g\2\2\u01ea\u01eb\7p\2\2\u01eb"+
		"\u01ec\7v\2\2\u01ec\u01ed\7g\2\2\u01ed\u01ff\7t\2\2\u01ee\u01ef\7e\2\2"+
		"\u01ef\u01f0\7q\2\2\u01f0\u01f1\7o\2\2\u01f1\u01f2\7o\2\2\u01f2\u01ff"+
		"\7c\2\2\u01f3\u01f4\7r\2\2\u01f4\u01f5\7g\2\2\u01f5\u01f6\7t\2\2\u01f6"+
		"\u01f7\7k\2\2\u01f7\u01f8\7q\2\2\u01f8\u01ff\7f\2\2\u01f9\u01fa\7u\2\2"+
		"\u01fa\u01fb\7n\2\2\u01fb\u01fc\7c\2\2\u01fc\u01fd\7u\2\2\u01fd\u01ff"+
		"\7j\2\2\u01fe\u0183\3\2\2\2\u01fe\u0188\3\2\2\2\u01fe\u018c\3\2\2\2\u01fe"+
		"\u018f\3\2\2\2\u01fe\u0193\3\2\2\2\u01fe\u0198\3\2\2\2\u01fe\u019c\3\2"+
		"\2\2\u01fe\u01a1\3\2\2\2\u01fe\u01a3\3\2\2\2\u01fe\u01a7\3\2\2\2\u01fe"+
		"\u01ac\3\2\2\2\u01fe\u01b2\3\2\2\2\u01fe\u01bb\3\2\2\2\u01fe\u01c6\3\2"+
		"\2\2\u01fe\u01d2\3\2\2\2\u01fe\u01db\3\2\2\2\u01fe\u01e4\3\2\2\2\u01fe"+
		"\u01e9\3\2\2\2\u01fe\u01ee\3\2\2\2\u01fe\u01f3\3\2\2\2\u01fe\u01f9\3\2"+
		"\2\2\u01ffJ\3\2\2\2\16\2\u0146\u014e\u0159\u0160\u0166\u016d\u0173\u0175"+
		"\u017a\u0180\u01fe";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}