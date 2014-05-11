// Generated from BoardGrammar.g4 by ANTLR 4.0

package pingball.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BoardGrammarParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__23=1, T__22=2, T__21=3, T__20=4, T__19=5, T__18=6, T__17=7, T__16=8, 
		T__15=9, T__14=10, T__13=11, T__12=12, T__11=13, T__10=14, T__9=15, T__8=16, 
		T__7=17, T__6=18, T__5=19, T__4=20, T__3=21, T__2=22, T__1=23, T__0=24, 
		WHITESPACE=25, COMMENT=26, EQUALS=27, FLOAT=28, INTEGER=29, NAME=30, KEY=31;
	public static final String[] tokenNames = {
		"<INVALID>", "'keyup'", "'yVelocity'", "'fire trigger'", "'squareBumper name'", 
		"'friction1'", "'name'", "'absorber name'", "'gravity'", "'board'", "'xVelocity'", 
		"'circleBumper name'", "'orientation'", "'height'", "'triangleBumper name'", 
		"'x'", "'y'", "'action'", "'key'", "'rightFlipper name'", "'leftFlipper name'", 
		"'ball name'", "'friction2'", "'width'", "'keydown'", "WHITESPACE", "COMMENT", 
		"'='", "FLOAT", "INTEGER", "NAME", "KEY"
	};
	public static final int
		RULE_root = 0, RULE_fileLines = 1, RULE_boardLine = 2, RULE_boardName = 3, 
		RULE_boardGravity = 4, RULE_boardFric1 = 5, RULE_boardFric2 = 6, RULE_ballLine = 7, 
		RULE_sqBumperLine = 8, RULE_cirBumperLine = 9, RULE_triBumperLine = 10, 
		RULE_rtFlipLine = 11, RULE_lftFlipLine = 12, RULE_absorberLine = 13, RULE_fireLine = 14, 
		RULE_keyIntDownLine = 15, RULE_keyNameDownLine = 16, RULE_keyIntUpLine = 17, 
		RULE_keyNameUpLine = 18;
	public static final String[] ruleNames = {
		"root", "fileLines", "boardLine", "boardName", "boardGravity", "boardFric1", 
		"boardFric2", "ballLine", "sqBumperLine", "cirBumperLine", "triBumperLine", 
		"rtFlipLine", "lftFlipLine", "absorberLine", "fireLine", "keyIntDownLine", 
		"keyNameDownLine", "keyIntUpLine", "keyNameUpLine"
	};

	@Override
	public String getGrammarFileName() { return "BoardGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }



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

	public BoardGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public FileLinesContext fileLines() {
			return getRuleContext(FileLinesContext.class,0);
		}
		public TerminalNode EOF() { return getToken(BoardGrammarParser.EOF, 0); }
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38); fileLines();
			setState(39); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FileLinesContext extends ParserRuleContext {
		public CirBumperLineContext cirBumperLine(int i) {
			return getRuleContext(CirBumperLineContext.class,i);
		}
		public List<TriBumperLineContext> triBumperLine() {
			return getRuleContexts(TriBumperLineContext.class);
		}
		public AbsorberLineContext absorberLine(int i) {
			return getRuleContext(AbsorberLineContext.class,i);
		}
		public List<KeyNameDownLineContext> keyNameDownLine() {
			return getRuleContexts(KeyNameDownLineContext.class);
		}
		public List<KeyIntUpLineContext> keyIntUpLine() {
			return getRuleContexts(KeyIntUpLineContext.class);
		}
		public List<SqBumperLineContext> sqBumperLine() {
			return getRuleContexts(SqBumperLineContext.class);
		}
		public List<FireLineContext> fireLine() {
			return getRuleContexts(FireLineContext.class);
		}
		public SqBumperLineContext sqBumperLine(int i) {
			return getRuleContext(SqBumperLineContext.class,i);
		}
		public List<RtFlipLineContext> rtFlipLine() {
			return getRuleContexts(RtFlipLineContext.class);
		}
		public List<KeyNameUpLineContext> keyNameUpLine() {
			return getRuleContexts(KeyNameUpLineContext.class);
		}
		public List<CirBumperLineContext> cirBumperLine() {
			return getRuleContexts(CirBumperLineContext.class);
		}
		public List<BallLineContext> ballLine() {
			return getRuleContexts(BallLineContext.class);
		}
		public RtFlipLineContext rtFlipLine(int i) {
			return getRuleContext(RtFlipLineContext.class,i);
		}
		public BoardLineContext boardLine() {
			return getRuleContext(BoardLineContext.class,0);
		}
		public LftFlipLineContext lftFlipLine(int i) {
			return getRuleContext(LftFlipLineContext.class,i);
		}
		public List<LftFlipLineContext> lftFlipLine() {
			return getRuleContexts(LftFlipLineContext.class);
		}
		public List<AbsorberLineContext> absorberLine() {
			return getRuleContexts(AbsorberLineContext.class);
		}
		public TriBumperLineContext triBumperLine(int i) {
			return getRuleContext(TriBumperLineContext.class,i);
		}
		public KeyNameUpLineContext keyNameUpLine(int i) {
			return getRuleContext(KeyNameUpLineContext.class,i);
		}
		public FireLineContext fireLine(int i) {
			return getRuleContext(FireLineContext.class,i);
		}
		public KeyIntUpLineContext keyIntUpLine(int i) {
			return getRuleContext(KeyIntUpLineContext.class,i);
		}
		public List<KeyIntDownLineContext> keyIntDownLine() {
			return getRuleContexts(KeyIntDownLineContext.class);
		}
		public KeyIntDownLineContext keyIntDownLine(int i) {
			return getRuleContext(KeyIntDownLineContext.class,i);
		}
		public KeyNameDownLineContext keyNameDownLine(int i) {
			return getRuleContext(KeyNameDownLineContext.class,i);
		}
		public BallLineContext ballLine(int i) {
			return getRuleContext(BallLineContext.class,i);
		}
		public FileLinesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fileLines; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterFileLines(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitFileLines(this);
		}
	}

	public final FileLinesContext fileLines() throws RecognitionException {
		FileLinesContext _localctx = new FileLinesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_fileLines);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41); boardLine();
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 3) | (1L << 4) | (1L << 7) | (1L << 11) | (1L << 14) | (1L << 19) | (1L << 20) | (1L << 21) | (1L << 24))) != 0)) {
				{
				setState(54);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(42); ballLine();
					}
					break;

				case 2:
					{
					setState(43); sqBumperLine();
					}
					break;

				case 3:
					{
					setState(44); cirBumperLine();
					}
					break;

				case 4:
					{
					setState(45); triBumperLine();
					}
					break;

				case 5:
					{
					setState(46); rtFlipLine();
					}
					break;

				case 6:
					{
					setState(47); lftFlipLine();
					}
					break;

				case 7:
					{
					setState(48); absorberLine();
					}
					break;

				case 8:
					{
					setState(49); fireLine();
					}
					break;

				case 9:
					{
					setState(50); keyIntDownLine();
					}
					break;

				case 10:
					{
					setState(51); keyIntUpLine();
					}
					break;

				case 11:
					{
					setState(52); keyNameDownLine();
					}
					break;

				case 12:
					{
					setState(53); keyNameUpLine();
					}
					break;
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoardLineContext extends ParserRuleContext {
		public List<BoardFric2Context> boardFric2() {
			return getRuleContexts(BoardFric2Context.class);
		}
		public List<BoardFric1Context> boardFric1() {
			return getRuleContexts(BoardFric1Context.class);
		}
		public BoardGravityContext boardGravity(int i) {
			return getRuleContext(BoardGravityContext.class,i);
		}
		public BoardFric1Context boardFric1(int i) {
			return getRuleContext(BoardFric1Context.class,i);
		}
		public BoardFric2Context boardFric2(int i) {
			return getRuleContext(BoardFric2Context.class,i);
		}
		public BoardNameContext boardName(int i) {
			return getRuleContext(BoardNameContext.class,i);
		}
		public List<BoardNameContext> boardName() {
			return getRuleContexts(BoardNameContext.class);
		}
		public List<BoardGravityContext> boardGravity() {
			return getRuleContexts(BoardGravityContext.class);
		}
		public BoardLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boardLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterBoardLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitBoardLine(this);
		}
	}

	public final BoardLineContext boardLine() throws RecognitionException {
		BoardLineContext _localctx = new BoardLineContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_boardLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59); match(9);
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 5) | (1L << 6) | (1L << 8) | (1L << 22))) != 0)) {
				{
				setState(64);
				switch (_input.LA(1)) {
				case 6:
					{
					setState(60); boardName();
					}
					break;
				case 8:
					{
					setState(61); boardGravity();
					}
					break;
				case 5:
					{
					setState(62); boardFric1();
					}
					break;
				case 22:
					{
					setState(63); boardFric2();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoardNameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public TerminalNode EQUALS() { return getToken(BoardGrammarParser.EQUALS, 0); }
		public BoardNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boardName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterBoardName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitBoardName(this);
		}
	}

	public final BoardNameContext boardName() throws RecognitionException {
		BoardNameContext _localctx = new BoardNameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_boardName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69); match(6);
			setState(70); match(EQUALS);
			setState(71); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoardGravityContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public TerminalNode FLOAT() { return getToken(BoardGrammarParser.FLOAT, 0); }
		public TerminalNode EQUALS() { return getToken(BoardGrammarParser.EQUALS, 0); }
		public BoardGravityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boardGravity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterBoardGravity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitBoardGravity(this);
		}
	}

	public final BoardGravityContext boardGravity() throws RecognitionException {
		BoardGravityContext _localctx = new BoardGravityContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_boardGravity);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73); match(8);
			setState(74); match(EQUALS);
			setState(75);
			_la = _input.LA(1);
			if ( !(_la==FLOAT || _la==NAME) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoardFric1Context extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(BoardGrammarParser.FLOAT, 0); }
		public TerminalNode EQUALS() { return getToken(BoardGrammarParser.EQUALS, 0); }
		public BoardFric1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boardFric1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterBoardFric1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitBoardFric1(this);
		}
	}

	public final BoardFric1Context boardFric1() throws RecognitionException {
		BoardFric1Context _localctx = new BoardFric1Context(_ctx, getState());
		enterRule(_localctx, 10, RULE_boardFric1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77); match(5);
			setState(78); match(EQUALS);
			setState(79); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoardFric2Context extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(BoardGrammarParser.FLOAT, 0); }
		public TerminalNode EQUALS() { return getToken(BoardGrammarParser.EQUALS, 0); }
		public BoardFric2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boardFric2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterBoardFric2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitBoardFric2(this);
		}
	}

	public final BoardFric2Context boardFric2() throws RecognitionException {
		BoardFric2Context _localctx = new BoardFric2Context(_ctx, getState());
		enterRule(_localctx, 12, RULE_boardFric2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81); match(22);
			setState(82); match(EQUALS);
			setState(83); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BallLineContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(BoardGrammarParser.FLOAT); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public TerminalNode FLOAT(int i) {
			return getToken(BoardGrammarParser.FLOAT, i);
		}
		public BallLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ballLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterBallLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitBallLine(this);
		}
	}

	public final BallLineContext ballLine() throws RecognitionException {
		BallLineContext _localctx = new BallLineContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ballLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85); match(21);
			setState(86); match(EQUALS);
			setState(87); match(NAME);
			setState(88); match(15);
			setState(89); match(EQUALS);
			setState(90); match(FLOAT);
			setState(91); match(16);
			setState(92); match(EQUALS);
			setState(93); match(FLOAT);
			setState(94); match(10);
			setState(95); match(EQUALS);
			setState(96); match(FLOAT);
			setState(97); match(2);
			setState(98); match(EQUALS);
			setState(99); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SqBumperLineContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(BoardGrammarParser.FLOAT); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public TerminalNode FLOAT(int i) {
			return getToken(BoardGrammarParser.FLOAT, i);
		}
		public SqBumperLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqBumperLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterSqBumperLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitSqBumperLine(this);
		}
	}

	public final SqBumperLineContext sqBumperLine() throws RecognitionException {
		SqBumperLineContext _localctx = new SqBumperLineContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_sqBumperLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); match(4);
			setState(102); match(EQUALS);
			setState(103); match(NAME);
			setState(104); match(15);
			setState(105); match(EQUALS);
			setState(106); match(FLOAT);
			setState(107); match(16);
			setState(108); match(EQUALS);
			setState(109); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CirBumperLineContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(BoardGrammarParser.FLOAT); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public TerminalNode FLOAT(int i) {
			return getToken(BoardGrammarParser.FLOAT, i);
		}
		public CirBumperLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cirBumperLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterCirBumperLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitCirBumperLine(this);
		}
	}

	public final CirBumperLineContext cirBumperLine() throws RecognitionException {
		CirBumperLineContext _localctx = new CirBumperLineContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cirBumperLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111); match(11);
			setState(112); match(EQUALS);
			setState(113); match(NAME);
			setState(114); match(15);
			setState(115); match(EQUALS);
			setState(116); match(FLOAT);
			setState(117); match(16);
			setState(118); match(EQUALS);
			setState(119); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TriBumperLineContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(BoardGrammarParser.FLOAT); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public TerminalNode FLOAT(int i) {
			return getToken(BoardGrammarParser.FLOAT, i);
		}
		public TriBumperLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triBumperLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterTriBumperLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitTriBumperLine(this);
		}
	}

	public final TriBumperLineContext triBumperLine() throws RecognitionException {
		TriBumperLineContext _localctx = new TriBumperLineContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_triBumperLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121); match(14);
			setState(122); match(EQUALS);
			setState(123); match(NAME);
			setState(124); match(15);
			setState(125); match(EQUALS);
			setState(126); match(FLOAT);
			setState(127); match(16);
			setState(128); match(EQUALS);
			setState(129); match(FLOAT);
			setState(130); match(12);
			setState(131); match(EQUALS);
			setState(132); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RtFlipLineContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(BoardGrammarParser.FLOAT); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public TerminalNode FLOAT(int i) {
			return getToken(BoardGrammarParser.FLOAT, i);
		}
		public RtFlipLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rtFlipLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterRtFlipLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitRtFlipLine(this);
		}
	}

	public final RtFlipLineContext rtFlipLine() throws RecognitionException {
		RtFlipLineContext _localctx = new RtFlipLineContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_rtFlipLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134); match(19);
			setState(135); match(EQUALS);
			setState(136); match(NAME);
			setState(137); match(15);
			setState(138); match(EQUALS);
			setState(139); match(FLOAT);
			setState(140); match(16);
			setState(141); match(EQUALS);
			setState(142); match(FLOAT);
			setState(143); match(12);
			setState(144); match(EQUALS);
			setState(145); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LftFlipLineContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(BoardGrammarParser.FLOAT); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public TerminalNode FLOAT(int i) {
			return getToken(BoardGrammarParser.FLOAT, i);
		}
		public LftFlipLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lftFlipLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterLftFlipLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitLftFlipLine(this);
		}
	}

	public final LftFlipLineContext lftFlipLine() throws RecognitionException {
		LftFlipLineContext _localctx = new LftFlipLineContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_lftFlipLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147); match(20);
			setState(148); match(EQUALS);
			setState(149); match(NAME);
			setState(150); match(15);
			setState(151); match(EQUALS);
			setState(152); match(FLOAT);
			setState(153); match(16);
			setState(154); match(EQUALS);
			setState(155); match(FLOAT);
			setState(156); match(12);
			setState(157); match(EQUALS);
			setState(158); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbsorberLineContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public List<TerminalNode> FLOAT() { return getTokens(BoardGrammarParser.FLOAT); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public TerminalNode FLOAT(int i) {
			return getToken(BoardGrammarParser.FLOAT, i);
		}
		public AbsorberLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_absorberLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterAbsorberLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitAbsorberLine(this);
		}
	}

	public final AbsorberLineContext absorberLine() throws RecognitionException {
		AbsorberLineContext _localctx = new AbsorberLineContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_absorberLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160); match(7);
			setState(161); match(EQUALS);
			setState(162); match(NAME);
			setState(163); match(15);
			setState(164); match(EQUALS);
			setState(165); match(FLOAT);
			setState(166); match(16);
			setState(167); match(EQUALS);
			setState(168); match(FLOAT);
			setState(169); match(23);
			setState(170); match(EQUALS);
			setState(171); match(FLOAT);
			setState(172); match(13);
			setState(173); match(EQUALS);
			setState(174); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FireLineContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(BoardGrammarParser.NAME); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public TerminalNode NAME(int i) {
			return getToken(BoardGrammarParser.NAME, i);
		}
		public FireLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fireLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterFireLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitFireLine(this);
		}
	}

	public final FireLineContext fireLine() throws RecognitionException {
		FireLineContext _localctx = new FireLineContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_fireLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176); match(3);
			setState(177); match(EQUALS);
			setState(178); match(NAME);
			setState(179); match(17);
			setState(180); match(EQUALS);
			setState(181); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeyIntDownLineContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public TerminalNode FLOAT() { return getToken(BoardGrammarParser.FLOAT, 0); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public KeyIntDownLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyIntDownLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterKeyIntDownLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitKeyIntDownLine(this);
		}
	}

	public final KeyIntDownLineContext keyIntDownLine() throws RecognitionException {
		KeyIntDownLineContext _localctx = new KeyIntDownLineContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_keyIntDownLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183); match(24);
			setState(184); match(18);
			setState(185); match(EQUALS);
			setState(186); match(FLOAT);
			setState(187); match(17);
			setState(188); match(EQUALS);
			setState(189); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeyNameDownLineContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(BoardGrammarParser.NAME); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public TerminalNode NAME(int i) {
			return getToken(BoardGrammarParser.NAME, i);
		}
		public KeyNameDownLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyNameDownLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterKeyNameDownLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitKeyNameDownLine(this);
		}
	}

	public final KeyNameDownLineContext keyNameDownLine() throws RecognitionException {
		KeyNameDownLineContext _localctx = new KeyNameDownLineContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_keyNameDownLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191); match(24);
			setState(192); match(18);
			setState(193); match(EQUALS);
			setState(194); match(NAME);
			setState(195); match(17);
			setState(196); match(EQUALS);
			setState(197); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeyIntUpLineContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public TerminalNode FLOAT() { return getToken(BoardGrammarParser.FLOAT, 0); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public KeyIntUpLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyIntUpLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterKeyIntUpLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitKeyIntUpLine(this);
		}
	}

	public final KeyIntUpLineContext keyIntUpLine() throws RecognitionException {
		KeyIntUpLineContext _localctx = new KeyIntUpLineContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_keyIntUpLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199); match(1);
			setState(200); match(18);
			setState(201); match(EQUALS);
			setState(202); match(FLOAT);
			setState(203); match(17);
			setState(204); match(EQUALS);
			setState(205); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeyNameUpLineContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(BoardGrammarParser.NAME); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public TerminalNode NAME(int i) {
			return getToken(BoardGrammarParser.NAME, i);
		}
		public KeyNameUpLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyNameUpLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterKeyNameUpLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitKeyNameUpLine(this);
		}
	}

	public final KeyNameUpLineContext keyNameUpLine() throws RecognitionException {
		KeyNameUpLineContext _localctx = new KeyNameUpLineContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_keyNameUpLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207); match(1);
			setState(208); match(18);
			setState(209); match(EQUALS);
			setState(210); match(NAME);
			setState(211); match(17);
			setState(212); match(EQUALS);
			setState(213); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\2\3!\u00da\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20"+
		"\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\39\n\3\f\3\16\3<\13\3\3\4\3\4\3\4"+
		"\3\4\3\4\7\4C\n\4\f\4\16\4F\13\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\2\25\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&\2\3\4\36\36  \u00d6"+
		"\2(\3\2\2\2\4+\3\2\2\2\6=\3\2\2\2\bG\3\2\2\2\nK\3\2\2\2\fO\3\2\2\2\16"+
		"S\3\2\2\2\20W\3\2\2\2\22g\3\2\2\2\24q\3\2\2\2\26{\3\2\2\2\30\u0088\3\2"+
		"\2\2\32\u0095\3\2\2\2\34\u00a2\3\2\2\2\36\u00b2\3\2\2\2 \u00b9\3\2\2\2"+
		"\"\u00c1\3\2\2\2$\u00c9\3\2\2\2&\u00d1\3\2\2\2()\5\4\3\2)*\7\1\2\2*\3"+
		"\3\2\2\2+:\5\6\4\2,9\5\20\t\2-9\5\22\n\2.9\5\24\13\2/9\5\26\f\2\609\5"+
		"\30\r\2\619\5\32\16\2\629\5\34\17\2\639\5\36\20\2\649\5 \21\2\659\5$\23"+
		"\2\669\5\"\22\2\679\5&\24\28,\3\2\2\28-\3\2\2\28.\3\2\2\28/\3\2\2\28\60"+
		"\3\2\2\28\61\3\2\2\28\62\3\2\2\28\63\3\2\2\28\64\3\2\2\28\65\3\2\2\28"+
		"\66\3\2\2\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;\5\3\2\2\2<:\3\2"+
		"\2\2=D\7\13\2\2>C\5\b\5\2?C\5\n\6\2@C\5\f\7\2AC\5\16\b\2B>\3\2\2\2B?\3"+
		"\2\2\2B@\3\2\2\2BA\3\2\2\2CF\3\2\2\2DB\3\2\2\2DE\3\2\2\2E\7\3\2\2\2FD"+
		"\3\2\2\2GH\7\b\2\2HI\7\35\2\2IJ\7 \2\2J\t\3\2\2\2KL\7\n\2\2LM\7\35\2\2"+
		"MN\t\2\2\2N\13\3\2\2\2OP\7\7\2\2PQ\7\35\2\2QR\7\36\2\2R\r\3\2\2\2ST\7"+
		"\30\2\2TU\7\35\2\2UV\7\36\2\2V\17\3\2\2\2WX\7\27\2\2XY\7\35\2\2YZ\7 \2"+
		"\2Z[\7\21\2\2[\\\7\35\2\2\\]\7\36\2\2]^\7\22\2\2^_\7\35\2\2_`\7\36\2\2"+
		"`a\7\f\2\2ab\7\35\2\2bc\7\36\2\2cd\7\4\2\2de\7\35\2\2ef\7\36\2\2f\21\3"+
		"\2\2\2gh\7\6\2\2hi\7\35\2\2ij\7 \2\2jk\7\21\2\2kl\7\35\2\2lm\7\36\2\2"+
		"mn\7\22\2\2no\7\35\2\2op\7\36\2\2p\23\3\2\2\2qr\7\r\2\2rs\7\35\2\2st\7"+
		" \2\2tu\7\21\2\2uv\7\35\2\2vw\7\36\2\2wx\7\22\2\2xy\7\35\2\2yz\7\36\2"+
		"\2z\25\3\2\2\2{|\7\20\2\2|}\7\35\2\2}~\7 \2\2~\177\7\21\2\2\177\u0080"+
		"\7\35\2\2\u0080\u0081\7\36\2\2\u0081\u0082\7\22\2\2\u0082\u0083\7\35\2"+
		"\2\u0083\u0084\7\36\2\2\u0084\u0085\7\16\2\2\u0085\u0086\7\35\2\2\u0086"+
		"\u0087\7\36\2\2\u0087\27\3\2\2\2\u0088\u0089\7\25\2\2\u0089\u008a\7\35"+
		"\2\2\u008a\u008b\7 \2\2\u008b\u008c\7\21\2\2\u008c\u008d\7\35\2\2\u008d"+
		"\u008e\7\36\2\2\u008e\u008f\7\22\2\2\u008f\u0090\7\35\2\2\u0090\u0091"+
		"\7\36\2\2\u0091\u0092\7\16\2\2\u0092\u0093\7\35\2\2\u0093\u0094\7\36\2"+
		"\2\u0094\31\3\2\2\2\u0095\u0096\7\26\2\2\u0096\u0097\7\35\2\2\u0097\u0098"+
		"\7 \2\2\u0098\u0099\7\21\2\2\u0099\u009a\7\35\2\2\u009a\u009b\7\36\2\2"+
		"\u009b\u009c\7\22\2\2\u009c\u009d\7\35\2\2\u009d\u009e\7\36\2\2\u009e"+
		"\u009f\7\16\2\2\u009f\u00a0\7\35\2\2\u00a0\u00a1\7\36\2\2\u00a1\33\3\2"+
		"\2\2\u00a2\u00a3\7\t\2\2\u00a3\u00a4\7\35\2\2\u00a4\u00a5\7 \2\2\u00a5"+
		"\u00a6\7\21\2\2\u00a6\u00a7\7\35\2\2\u00a7\u00a8\7\36\2\2\u00a8\u00a9"+
		"\7\22\2\2\u00a9\u00aa\7\35\2\2\u00aa\u00ab\7\36\2\2\u00ab\u00ac\7\31\2"+
		"\2\u00ac\u00ad\7\35\2\2\u00ad\u00ae\7\36\2\2\u00ae\u00af\7\17\2\2\u00af"+
		"\u00b0\7\35\2\2\u00b0\u00b1\7\36\2\2\u00b1\35\3\2\2\2\u00b2\u00b3\7\5"+
		"\2\2\u00b3\u00b4\7\35\2\2\u00b4\u00b5\7 \2\2\u00b5\u00b6\7\23\2\2\u00b6"+
		"\u00b7\7\35\2\2\u00b7\u00b8\7 \2\2\u00b8\37\3\2\2\2\u00b9\u00ba\7\32\2"+
		"\2\u00ba\u00bb\7\24\2\2\u00bb\u00bc\7\35\2\2\u00bc\u00bd\7\36\2\2\u00bd"+
		"\u00be\7\23\2\2\u00be\u00bf\7\35\2\2\u00bf\u00c0\7 \2\2\u00c0!\3\2\2\2"+
		"\u00c1\u00c2\7\32\2\2\u00c2\u00c3\7\24\2\2\u00c3\u00c4\7\35\2\2\u00c4"+
		"\u00c5\7 \2\2\u00c5\u00c6\7\23\2\2\u00c6\u00c7\7\35\2\2\u00c7\u00c8\7"+
		" \2\2\u00c8#\3\2\2\2\u00c9\u00ca\7\3\2\2\u00ca\u00cb\7\24\2\2\u00cb\u00cc"+
		"\7\35\2\2\u00cc\u00cd\7\36\2\2\u00cd\u00ce\7\23\2\2\u00ce\u00cf\7\35\2"+
		"\2\u00cf\u00d0\7 \2\2\u00d0%\3\2\2\2\u00d1\u00d2\7\3\2\2\u00d2\u00d3\7"+
		"\24\2\2\u00d3\u00d4\7\35\2\2\u00d4\u00d5\7 \2\2\u00d5\u00d6\7\23\2\2\u00d6"+
		"\u00d7\7\35\2\2\u00d7\u00d8\7 \2\2\u00d8\'\3\2\2\2\68:BD";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}