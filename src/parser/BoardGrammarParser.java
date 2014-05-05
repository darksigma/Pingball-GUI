// Generated from BoardGrammar.g4 by ANTLR 4.0

package parser;

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
		T__20=1, T__19=2, T__18=3, T__17=4, T__16=5, T__15=6, T__14=7, T__13=8, 
		T__12=9, T__11=10, T__10=11, T__9=12, T__8=13, T__7=14, T__6=15, T__5=16, 
		T__4=17, T__3=18, T__2=19, T__1=20, T__0=21, WHITESPACE=22, COMMENT=23, 
		EQUALS=24, FLOAT=25, INTEGER=26, NAME=27;
	public static final String[] tokenNames = {
		"<INVALID>", "'yVelocity'", "'fire trigger'", "'squareBumper name'", "'friction1'", 
		"'name'", "'absorber name'", "'gravity'", "'board'", "'xVelocity'", "'circleBumper name'", 
		"'orientation'", "'height'", "'triangleBumper name'", "'x'", "'y'", "'action'", 
		"'rightFlipper name'", "'leftFlipper name'", "'ball name'", "'friction2'", 
		"'width'", "WHITESPACE", "COMMENT", "'='", "FLOAT", "INTEGER", "NAME"
	};
	public static final int
		RULE_root = 0, RULE_fileLines = 1, RULE_boardLine = 2, RULE_boardName = 3, 
		RULE_boardGravity = 4, RULE_boardFric1 = 5, RULE_boardFric2 = 6, RULE_ballLine = 7, 
		RULE_sqBumperLine = 8, RULE_cirBumperLine = 9, RULE_triBumperLine = 10, 
		RULE_rtFlipLine = 11, RULE_lftFlipLine = 12, RULE_absorberLine = 13, RULE_fireLine = 14;
	public static final String[] ruleNames = {
		"root", "fileLines", "boardLine", "boardName", "boardGravity", "boardFric1", 
		"boardFric2", "ballLine", "sqBumperLine", "cirBumperLine", "triBumperLine", 
		"rtFlipLine", "lftFlipLine", "absorberLine", "fireLine"
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
			setState(30); fileLines();
			setState(31); match(EOF);
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
		public List<CirBumperLineContext> cirBumperLine() {
			return getRuleContexts(CirBumperLineContext.class);
		}
		public List<BallLineContext> ballLine() {
			return getRuleContexts(BallLineContext.class);
		}
		public BoardLineContext boardLine() {
			return getRuleContext(BoardLineContext.class,0);
		}
		public RtFlipLineContext rtFlipLine(int i) {
			return getRuleContext(RtFlipLineContext.class,i);
		}
		public LftFlipLineContext lftFlipLine(int i) {
			return getRuleContext(LftFlipLineContext.class,i);
		}
		public List<FireLineContext> fireLine() {
			return getRuleContexts(FireLineContext.class);
		}
		public List<AbsorberLineContext> absorberLine() {
			return getRuleContexts(AbsorberLineContext.class);
		}
		public List<SqBumperLineContext> sqBumperLine() {
			return getRuleContexts(SqBumperLineContext.class);
		}
		public List<LftFlipLineContext> lftFlipLine() {
			return getRuleContexts(LftFlipLineContext.class);
		}
		public TriBumperLineContext triBumperLine(int i) {
			return getRuleContext(TriBumperLineContext.class,i);
		}
		public FireLineContext fireLine(int i) {
			return getRuleContext(FireLineContext.class,i);
		}
		public SqBumperLineContext sqBumperLine(int i) {
			return getRuleContext(SqBumperLineContext.class,i);
		}
		public List<RtFlipLineContext> rtFlipLine() {
			return getRuleContexts(RtFlipLineContext.class);
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
			setState(33); boardLine();
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 3) | (1L << 6) | (1L << 10) | (1L << 13) | (1L << 17) | (1L << 18) | (1L << 19))) != 0)) {
				{
				setState(42);
				switch (_input.LA(1)) {
				case 19:
					{
					setState(34); ballLine();
					}
					break;
				case 3:
					{
					setState(35); sqBumperLine();
					}
					break;
				case 10:
					{
					setState(36); cirBumperLine();
					}
					break;
				case 13:
					{
					setState(37); triBumperLine();
					}
					break;
				case 17:
					{
					setState(38); rtFlipLine();
					}
					break;
				case 18:
					{
					setState(39); lftFlipLine();
					}
					break;
				case 6:
					{
					setState(40); absorberLine();
					}
					break;
				case 2:
					{
					setState(41); fireLine();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(46);
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
			setState(47); match(8);
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 4) | (1L << 5) | (1L << 7) | (1L << 20))) != 0)) {
				{
				setState(52);
				switch (_input.LA(1)) {
				case 5:
					{
					setState(48); boardName();
					}
					break;
				case 7:
					{
					setState(49); boardGravity();
					}
					break;
				case 4:
					{
					setState(50); boardFric1();
					}
					break;
				case 20:
					{
					setState(51); boardFric2();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(56);
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
			setState(57); match(5);
			setState(58); match(EQUALS);
			setState(59); match(NAME);
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61); match(7);
			setState(62); match(EQUALS);
			setState(63); match(FLOAT);
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
			setState(65); match(4);
			setState(66); match(EQUALS);
			setState(67); match(FLOAT);
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
			setState(69); match(20);
			setState(70); match(EQUALS);
			setState(71); match(FLOAT);
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
			setState(73); match(19);
			setState(74); match(EQUALS);
			setState(75); match(NAME);
			setState(76); match(14);
			setState(77); match(EQUALS);
			setState(78); match(FLOAT);
			setState(79); match(15);
			setState(80); match(EQUALS);
			setState(81); match(FLOAT);
			setState(82); match(9);
			setState(83); match(EQUALS);
			setState(84); match(FLOAT);
			setState(85); match(1);
			setState(86); match(EQUALS);
			setState(87); match(FLOAT);
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
			setState(89); match(3);
			setState(90); match(EQUALS);
			setState(91); match(NAME);
			setState(92); match(14);
			setState(93); match(EQUALS);
			setState(94); match(FLOAT);
			setState(95); match(15);
			setState(96); match(EQUALS);
			setState(97); match(FLOAT);
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
			setState(99); match(10);
			setState(100); match(EQUALS);
			setState(101); match(NAME);
			setState(102); match(14);
			setState(103); match(EQUALS);
			setState(104); match(FLOAT);
			setState(105); match(15);
			setState(106); match(EQUALS);
			setState(107); match(FLOAT);
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
			setState(109); match(13);
			setState(110); match(EQUALS);
			setState(111); match(NAME);
			setState(112); match(14);
			setState(113); match(EQUALS);
			setState(114); match(FLOAT);
			setState(115); match(15);
			setState(116); match(EQUALS);
			setState(117); match(FLOAT);
			setState(118); match(11);
			setState(119); match(EQUALS);
			setState(120); match(FLOAT);
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
			setState(122); match(17);
			setState(123); match(EQUALS);
			setState(124); match(NAME);
			setState(125); match(14);
			setState(126); match(EQUALS);
			setState(127); match(FLOAT);
			setState(128); match(15);
			setState(129); match(EQUALS);
			setState(130); match(FLOAT);
			setState(131); match(11);
			setState(132); match(EQUALS);
			setState(133); match(FLOAT);
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
			setState(135); match(18);
			setState(136); match(EQUALS);
			setState(137); match(NAME);
			setState(138); match(14);
			setState(139); match(EQUALS);
			setState(140); match(FLOAT);
			setState(141); match(15);
			setState(142); match(EQUALS);
			setState(143); match(FLOAT);
			setState(144); match(11);
			setState(145); match(EQUALS);
			setState(146); match(FLOAT);
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
			setState(148); match(6);
			setState(149); match(EQUALS);
			setState(150); match(NAME);
			setState(151); match(14);
			setState(152); match(EQUALS);
			setState(153); match(FLOAT);
			setState(154); match(15);
			setState(155); match(EQUALS);
			setState(156); match(FLOAT);
			setState(157); match(21);
			setState(158); match(EQUALS);
			setState(159); match(FLOAT);
			setState(160); match(12);
			setState(161); match(EQUALS);
			setState(162); match(FLOAT);
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
			setState(164); match(2);
			setState(165); match(EQUALS);
			setState(166); match(NAME);
			setState(167); match(16);
			setState(168); match(EQUALS);
			setState(169); match(NAME);
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
		"\2\3\35\u00ae\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t"+
		"\20\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3-\n\3\f\3\16\3"+
		"\60\13\3\3\4\3\4\3\4\3\4\3\4\7\4\67\n\4\f\4\16\4:\13\4\3\5\3\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\2\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\2\u00aa\2 \3\2\2\2\4#\3"+
		"\2\2\2\6\61\3\2\2\2\b;\3\2\2\2\n?\3\2\2\2\fC\3\2\2\2\16G\3\2\2\2\20K\3"+
		"\2\2\2\22[\3\2\2\2\24e\3\2\2\2\26o\3\2\2\2\30|\3\2\2\2\32\u0089\3\2\2"+
		"\2\34\u0096\3\2\2\2\36\u00a6\3\2\2\2 !\5\4\3\2!\"\7\1\2\2\"\3\3\2\2\2"+
		"#.\5\6\4\2$-\5\20\t\2%-\5\22\n\2&-\5\24\13\2\'-\5\26\f\2(-\5\30\r\2)-"+
		"\5\32\16\2*-\5\34\17\2+-\5\36\20\2,$\3\2\2\2,%\3\2\2\2,&\3\2\2\2,\'\3"+
		"\2\2\2,(\3\2\2\2,)\3\2\2\2,*\3\2\2\2,+\3\2\2\2-\60\3\2\2\2.,\3\2\2\2."+
		"/\3\2\2\2/\5\3\2\2\2\60.\3\2\2\2\618\7\n\2\2\62\67\5\b\5\2\63\67\5\n\6"+
		"\2\64\67\5\f\7\2\65\67\5\16\b\2\66\62\3\2\2\2\66\63\3\2\2\2\66\64\3\2"+
		"\2\2\66\65\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29\7\3\2\2\2:8\3\2"+
		"\2\2;<\7\7\2\2<=\7\32\2\2=>\7\35\2\2>\t\3\2\2\2?@\7\t\2\2@A\7\32\2\2A"+
		"B\7\33\2\2B\13\3\2\2\2CD\7\6\2\2DE\7\32\2\2EF\7\33\2\2F\r\3\2\2\2GH\7"+
		"\26\2\2HI\7\32\2\2IJ\7\33\2\2J\17\3\2\2\2KL\7\25\2\2LM\7\32\2\2MN\7\35"+
		"\2\2NO\7\20\2\2OP\7\32\2\2PQ\7\33\2\2QR\7\21\2\2RS\7\32\2\2ST\7\33\2\2"+
		"TU\7\13\2\2UV\7\32\2\2VW\7\33\2\2WX\7\3\2\2XY\7\32\2\2YZ\7\33\2\2Z\21"+
		"\3\2\2\2[\\\7\5\2\2\\]\7\32\2\2]^\7\35\2\2^_\7\20\2\2_`\7\32\2\2`a\7\33"+
		"\2\2ab\7\21\2\2bc\7\32\2\2cd\7\33\2\2d\23\3\2\2\2ef\7\f\2\2fg\7\32\2\2"+
		"gh\7\35\2\2hi\7\20\2\2ij\7\32\2\2jk\7\33\2\2kl\7\21\2\2lm\7\32\2\2mn\7"+
		"\33\2\2n\25\3\2\2\2op\7\17\2\2pq\7\32\2\2qr\7\35\2\2rs\7\20\2\2st\7\32"+
		"\2\2tu\7\33\2\2uv\7\21\2\2vw\7\32\2\2wx\7\33\2\2xy\7\r\2\2yz\7\32\2\2"+
		"z{\7\33\2\2{\27\3\2\2\2|}\7\23\2\2}~\7\32\2\2~\177\7\35\2\2\177\u0080"+
		"\7\20\2\2\u0080\u0081\7\32\2\2\u0081\u0082\7\33\2\2\u0082\u0083\7\21\2"+
		"\2\u0083\u0084\7\32\2\2\u0084\u0085\7\33\2\2\u0085\u0086\7\r\2\2\u0086"+
		"\u0087\7\32\2\2\u0087\u0088\7\33\2\2\u0088\31\3\2\2\2\u0089\u008a\7\24"+
		"\2\2\u008a\u008b\7\32\2\2\u008b\u008c\7\35\2\2\u008c\u008d\7\20\2\2\u008d"+
		"\u008e\7\32\2\2\u008e\u008f\7\33\2\2\u008f\u0090\7\21\2\2\u0090\u0091"+
		"\7\32\2\2\u0091\u0092\7\33\2\2\u0092\u0093\7\r\2\2\u0093\u0094\7\32\2"+
		"\2\u0094\u0095\7\33\2\2\u0095\33\3\2\2\2\u0096\u0097\7\b\2\2\u0097\u0098"+
		"\7\32\2\2\u0098\u0099\7\35\2\2\u0099\u009a\7\20\2\2\u009a\u009b\7\32\2"+
		"\2\u009b\u009c\7\33\2\2\u009c\u009d\7\21\2\2\u009d\u009e\7\32\2\2\u009e"+
		"\u009f\7\33\2\2\u009f\u00a0\7\27\2\2\u00a0\u00a1\7\32\2\2\u00a1\u00a2"+
		"\7\33\2\2\u00a2\u00a3\7\16\2\2\u00a3\u00a4\7\32\2\2\u00a4\u00a5\7\33\2"+
		"\2\u00a5\35\3\2\2\2\u00a6\u00a7\7\4\2\2\u00a7\u00a8\7\32\2\2\u00a8\u00a9"+
		"\7\35\2\2\u00a9\u00aa\7\22\2\2\u00aa\u00ab\7\32\2\2\u00ab\u00ac\7\35\2"+
		"\2\u00ac\37\3\2\2\2\6,.\668";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}