// Generated from BoardExpression.g4 by ANTLR 4.0

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
public class BoardExpressionLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__19=1, T__18=2, T__17=3, T__16=4, T__15=5, T__14=6, T__13=7, T__12=8, 
		T__11=9, T__10=10, T__9=11, T__8=12, T__7=13, T__6=14, T__5=15, T__4=16, 
		T__3=17, T__2=18, T__1=19, T__0=20, WHITE=21, COMMENTERS=22, NAME=23, 
		NUMBAH=24, EQUALS=25;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'yVelocity'", "'fire trigger'", "'squareBumper name'", "'friction1'", 
		"'name'", "'absorber name'", "'gravity'", "'xVelocity'", "'circleBumper name'", 
		"'orientation'", "'height'", "'triangleBumper name'", "'x'", "'y'", "'action'", 
		"'leftFlipper name'", "'rightFlipper name'", "'ball name'", "'friction2'", 
		"'width'", "WHITE", "COMMENTERS", "NAME", "NUMBAH", "'='"
	};
	public static final String[] ruleNames = {
		"T__19", "T__18", "T__17", "T__16", "T__15", "T__14", "T__13", "T__12", 
		"T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", 
		"T__2", "T__1", "T__0", "WHITE", "COMMENTERS", "NAME", "NUMBAH", "EQUALS"
	};



		public void reportErrorAsExceptions(){
			addErrorListener(new ExceptionThrowingErrorListener());
		}
		
		private static class ExceptionThrowingErrorListener extends BaseErrorListener {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer,
					Object offendingSymbol, int line, int charPositionLine,
					String msg, RecognitionException e) {
				throw new RuntimeException(msg);
			}
		}


	public BoardExpressionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BoardExpression.g4"; }

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
		case 20: WHITE_action((RuleContext)_localctx, actionIndex); break;

		case 21: COMMENTERS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WHITE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}
	private void COMMENTERS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4\33\u0141\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\26\6\26\u0110\n\26\r\26\16\26\u0111\3\26\3\26\3\27\3\27\7\27"+
		"\u0118\n\27\f\27\16\27\u011b\13\27\3\27\3\27\3\30\3\30\7\30\u0121\n\30"+
		"\f\30\16\30\u0124\13\30\3\31\5\31\u0127\n\31\3\31\6\31\u012a\n\31\r\31"+
		"\16\31\u012b\3\31\5\31\u012f\n\31\3\31\7\31\u0132\n\31\f\31\16\31\u0135"+
		"\13\31\3\31\3\31\7\31\u0139\n\31\f\31\16\31\u013c\13\31\5\31\u013e\n\31"+
		"\3\32\3\32\2\33\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23"+
		"\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1"+
		"\'\25\1)\26\1+\27\2-\30\3/\31\1\61\32\1\63\33\1\3\2\t\5\13\f\17\17\"\""+
		"\4\f\f\17\17\5C\\aac|\6\62;C\\aac|\3\62;\3\62;\3\62;\u0149\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"+
		"\2\2\63\3\2\2\2\3\65\3\2\2\2\5?\3\2\2\2\7L\3\2\2\2\t^\3\2\2\2\13h\3\2"+
		"\2\2\rm\3\2\2\2\17{\3\2\2\2\21\u0083\3\2\2\2\23\u008d\3\2\2\2\25\u009f"+
		"\3\2\2\2\27\u00ab\3\2\2\2\31\u00b2\3\2\2\2\33\u00c6\3\2\2\2\35\u00c8\3"+
		"\2\2\2\37\u00ca\3\2\2\2!\u00d1\3\2\2\2#\u00e2\3\2\2\2%\u00f4\3\2\2\2\'"+
		"\u00fe\3\2\2\2)\u0108\3\2\2\2+\u010f\3\2\2\2-\u0115\3\2\2\2/\u011e\3\2"+
		"\2\2\61\u013d\3\2\2\2\63\u013f\3\2\2\2\65\66\7{\2\2\66\67\7X\2\2\678\7"+
		"g\2\289\7n\2\29:\7q\2\2:;\7e\2\2;<\7k\2\2<=\7v\2\2=>\7{\2\2>\4\3\2\2\2"+
		"?@\7h\2\2@A\7k\2\2AB\7t\2\2BC\7g\2\2CD\7\"\2\2DE\7v\2\2EF\7t\2\2FG\7k"+
		"\2\2GH\7i\2\2HI\7i\2\2IJ\7g\2\2JK\7t\2\2K\6\3\2\2\2LM\7u\2\2MN\7s\2\2"+
		"NO\7w\2\2OP\7c\2\2PQ\7t\2\2QR\7g\2\2RS\7D\2\2ST\7w\2\2TU\7o\2\2UV\7r\2"+
		"\2VW\7g\2\2WX\7t\2\2XY\7\"\2\2YZ\7p\2\2Z[\7c\2\2[\\\7o\2\2\\]\7g\2\2]"+
		"\b\3\2\2\2^_\7h\2\2_`\7t\2\2`a\7k\2\2ab\7e\2\2bc\7v\2\2cd\7k\2\2de\7q"+
		"\2\2ef\7p\2\2fg\7\63\2\2g\n\3\2\2\2hi\7p\2\2ij\7c\2\2jk\7o\2\2kl\7g\2"+
		"\2l\f\3\2\2\2mn\7c\2\2no\7d\2\2op\7u\2\2pq\7q\2\2qr\7t\2\2rs\7d\2\2st"+
		"\7g\2\2tu\7t\2\2uv\7\"\2\2vw\7p\2\2wx\7c\2\2xy\7o\2\2yz\7g\2\2z\16\3\2"+
		"\2\2{|\7i\2\2|}\7t\2\2}~\7c\2\2~\177\7x\2\2\177\u0080\7k\2\2\u0080\u0081"+
		"\7v\2\2\u0081\u0082\7{\2\2\u0082\20\3\2\2\2\u0083\u0084\7z\2\2\u0084\u0085"+
		"\7X\2\2\u0085\u0086\7g\2\2\u0086\u0087\7n\2\2\u0087\u0088\7q\2\2\u0088"+
		"\u0089\7e\2\2\u0089\u008a\7k\2\2\u008a\u008b\7v\2\2\u008b\u008c\7{\2\2"+
		"\u008c\22\3\2\2\2\u008d\u008e\7e\2\2\u008e\u008f\7k\2\2\u008f\u0090\7"+
		"t\2\2\u0090\u0091\7e\2\2\u0091\u0092\7n\2\2\u0092\u0093\7g\2\2\u0093\u0094"+
		"\7D\2\2\u0094\u0095\7w\2\2\u0095\u0096\7o\2\2\u0096\u0097\7r\2\2\u0097"+
		"\u0098\7g\2\2\u0098\u0099\7t\2\2\u0099\u009a\7\"\2\2\u009a\u009b\7p\2"+
		"\2\u009b\u009c\7c\2\2\u009c\u009d\7o\2\2\u009d\u009e\7g\2\2\u009e\24\3"+
		"\2\2\2\u009f\u00a0\7q\2\2\u00a0\u00a1\7t\2\2\u00a1\u00a2\7k\2\2\u00a2"+
		"\u00a3\7g\2\2\u00a3\u00a4\7p\2\2\u00a4\u00a5\7v\2\2\u00a5\u00a6\7c\2\2"+
		"\u00a6\u00a7\7v\2\2\u00a7\u00a8\7k\2\2\u00a8\u00a9\7q\2\2\u00a9\u00aa"+
		"\7p\2\2\u00aa\26\3\2\2\2\u00ab\u00ac\7j\2\2\u00ac\u00ad\7g\2\2\u00ad\u00ae"+
		"\7k\2\2\u00ae\u00af\7i\2\2\u00af\u00b0\7j\2\2\u00b0\u00b1\7v\2\2\u00b1"+
		"\30\3\2\2\2\u00b2\u00b3\7v\2\2\u00b3\u00b4\7t\2\2\u00b4\u00b5\7k\2\2\u00b5"+
		"\u00b6\7c\2\2\u00b6\u00b7\7p\2\2\u00b7\u00b8\7i\2\2\u00b8\u00b9\7n\2\2"+
		"\u00b9\u00ba\7g\2\2\u00ba\u00bb\7D\2\2\u00bb\u00bc\7w\2\2\u00bc\u00bd"+
		"\7o\2\2\u00bd\u00be\7r\2\2\u00be\u00bf\7g\2\2\u00bf\u00c0\7t\2\2\u00c0"+
		"\u00c1\7\"\2\2\u00c1\u00c2\7p\2\2\u00c2\u00c3\7c\2\2\u00c3\u00c4\7o\2"+
		"\2\u00c4\u00c5\7g\2\2\u00c5\32\3\2\2\2\u00c6\u00c7\7z\2\2\u00c7\34\3\2"+
		"\2\2\u00c8\u00c9\7{\2\2\u00c9\36\3\2\2\2\u00ca\u00cb\7c\2\2\u00cb\u00cc"+
		"\7e\2\2\u00cc\u00cd\7v\2\2\u00cd\u00ce\7k\2\2\u00ce\u00cf\7q\2\2\u00cf"+
		"\u00d0\7p\2\2\u00d0 \3\2\2\2\u00d1\u00d2\7n\2\2\u00d2\u00d3\7g\2\2\u00d3"+
		"\u00d4\7h\2\2\u00d4\u00d5\7v\2\2\u00d5\u00d6\7H\2\2\u00d6\u00d7\7n\2\2"+
		"\u00d7\u00d8\7k\2\2\u00d8\u00d9\7r\2\2\u00d9\u00da\7r\2\2\u00da\u00db"+
		"\7g\2\2\u00db\u00dc\7t\2\2\u00dc\u00dd\7\"\2\2\u00dd\u00de\7p\2\2\u00de"+
		"\u00df\7c\2\2\u00df\u00e0\7o\2\2\u00e0\u00e1\7g\2\2\u00e1\"\3\2\2\2\u00e2"+
		"\u00e3\7t\2\2\u00e3\u00e4\7k\2\2\u00e4\u00e5\7i\2\2\u00e5\u00e6\7j\2\2"+
		"\u00e6\u00e7\7v\2\2\u00e7\u00e8\7H\2\2\u00e8\u00e9\7n\2\2\u00e9\u00ea"+
		"\7k\2\2\u00ea\u00eb\7r\2\2\u00eb\u00ec\7r\2\2\u00ec\u00ed\7g\2\2\u00ed"+
		"\u00ee\7t\2\2\u00ee\u00ef\7\"\2\2\u00ef\u00f0\7p\2\2\u00f0\u00f1\7c\2"+
		"\2\u00f1\u00f2\7o\2\2\u00f2\u00f3\7g\2\2\u00f3$\3\2\2\2\u00f4\u00f5\7"+
		"d\2\2\u00f5\u00f6\7c\2\2\u00f6\u00f7\7n\2\2\u00f7\u00f8\7n\2\2\u00f8\u00f9"+
		"\7\"\2\2\u00f9\u00fa\7p\2\2\u00fa\u00fb\7c\2\2\u00fb\u00fc\7o\2\2\u00fc"+
		"\u00fd\7g\2\2\u00fd&\3\2\2\2\u00fe\u00ff\7h\2\2\u00ff\u0100\7t\2\2\u0100"+
		"\u0101\7k\2\2\u0101\u0102\7e\2\2\u0102\u0103\7v\2\2\u0103\u0104\7k\2\2"+
		"\u0104\u0105\7q\2\2\u0105\u0106\7p\2\2\u0106\u0107\7\64\2\2\u0107(\3\2"+
		"\2\2\u0108\u0109\7y\2\2\u0109\u010a\7k\2\2\u010a\u010b\7f\2\2\u010b\u010c"+
		"\7v\2\2\u010c\u010d\7j\2\2\u010d*\3\2\2\2\u010e\u0110\t\2\2\2\u010f\u010e"+
		"\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112"+
		"\u0113\3\2\2\2\u0113\u0114\b\26\2\2\u0114,\3\2\2\2\u0115\u0119\7%\2\2"+
		"\u0116\u0118\n\3\2\2\u0117\u0116\3\2\2\2\u0118\u011b\3\2\2\2\u0119\u0117"+
		"\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011c\3\2\2\2\u011b\u0119\3\2\2\2\u011c"+
		"\u011d\b\27\3\2\u011d.\3\2\2\2\u011e\u0122\t\4\2\2\u011f\u0121\t\5\2\2"+
		"\u0120\u011f\3\2\2\2\u0121\u0124\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123"+
		"\3\2\2\2\u0123\60\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0127\7/\2\2\u0126"+
		"\u0125\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0129\3\2\2\2\u0128\u012a\t\6"+
		"\2\2\u0129\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u0129\3\2\2\2\u012b"+
		"\u012c\3\2\2\2\u012c\u013e\3\2\2\2\u012d\u012f\7/\2\2\u012e\u012d\3\2"+
		"\2\2\u012e\u012f\3\2\2\2\u012f\u0133\3\2\2\2\u0130\u0132\t\7\2\2\u0131"+
		"\u0130\3\2\2\2\u0132\u0135\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2"+
		"\2\2\u0134\u0136\3\2\2\2\u0135\u0133\3\2\2\2\u0136\u013a\7\60\2\2\u0137"+
		"\u0139\t\b\2\2\u0138\u0137\3\2\2\2\u0139\u013c\3\2\2\2\u013a\u0138\3\2"+
		"\2\2\u013a\u013b\3\2\2\2\u013b\u013e\3\2\2\2\u013c\u013a\3\2\2\2\u013d"+
		"\u0126\3\2\2\2\u013d\u012e\3\2\2\2\u013e\62\3\2\2\2\u013f\u0140\7?\2\2"+
		"\u0140\64\3\2\2\2\f\2\u0111\u0119\u0122\u0126\u012b\u012e\u0133\u013a"+
		"\u013d";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}