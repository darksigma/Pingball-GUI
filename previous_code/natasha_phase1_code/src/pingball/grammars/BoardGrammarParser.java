// Generated from src/pingball/grammars/BoardGrammar.g4 by ANTLR 4.0

package pingball.grammars;

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
		T__18=1, T__17=2, T__16=3, T__15=4, T__14=5, T__13=6, T__12=7, T__11=8, 
		T__10=9, T__9=10, T__8=11, T__7=12, T__6=13, T__5=14, T__4=15, T__3=16, 
		T__2=17, T__1=18, T__0=19, WHITESPACE=20, COMMENT=21, EQUALS=22, FLOAT=23, 
		INTEGER=24, NAME=25, BOARDNAME=26;
	public static final String[] tokenNames = {
		"<INVALID>", "'yVelocity'", "'fire trigger'", "'squareBumper name'", "'friction1'", 
		"'absorber name'", "'gravity'", "'xVelocity'", "'orientation'", "'circleBumper name'", 
		"'height'", "'triangleBumper name'", "'x'", "'y'", "'action'", "'rightFlipper name'", 
		"'leftFlipper name'", "'ball name'", "'friction2'", "'width'", "WHITESPACE", 
		"COMMENT", "'='", "FLOAT", "INTEGER", "NAME", "'board name='"
	};
	public static final int
		RULE_root = 0, RULE_fileLines = 1, RULE_boardLine = 2, RULE_boardGravity = 3, 
		RULE_boardFric1 = 4, RULE_boardFric2 = 5, RULE_ballLine = 6, RULE_sqBumperLine = 7, 
		RULE_cirBumperLine = 8, RULE_triBumperLine = 9, RULE_rtFlipLine = 10, 
		RULE_lftFlipLine = 11, RULE_absorberLine = 12, RULE_fireLine = 13;
	public static final String[] ruleNames = {
		"root", "fileLines", "boardLine", "boardGravity", "boardFric1", "boardFric2", 
		"ballLine", "sqBumperLine", "cirBumperLine", "triBumperLine", "rtFlipLine", 
		"lftFlipLine", "absorberLine", "fireLine"
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
			setState(28); fileLines();
			setState(29); match(EOF);
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
			setState(31); boardLine();
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 3) | (1L << 5) | (1L << 9) | (1L << 11) | (1L << 15) | (1L << 16) | (1L << 17))) != 0)) {
				{
				setState(40);
				switch (_input.LA(1)) {
				case 17:
					{
					setState(32); ballLine();
					}
					break;
				case 3:
					{
					setState(33); sqBumperLine();
					}
					break;
				case 9:
					{
					setState(34); cirBumperLine();
					}
					break;
				case 11:
					{
					setState(35); triBumperLine();
					}
					break;
				case 15:
					{
					setState(36); rtFlipLine();
					}
					break;
				case 16:
					{
					setState(37); lftFlipLine();
					}
					break;
				case 5:
					{
					setState(38); absorberLine();
					}
					break;
				case 2:
					{
					setState(39); fireLine();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(44);
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
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public TerminalNode BOARDNAME() { return getToken(BoardGrammarParser.BOARDNAME, 0); }
		public BoardGravityContext boardGravity(int i) {
			return getRuleContext(BoardGravityContext.class,i);
		}
		public BoardFric1Context boardFric1(int i) {
			return getRuleContext(BoardFric1Context.class,i);
		}
		public BoardFric2Context boardFric2(int i) {
			return getRuleContext(BoardFric2Context.class,i);
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
			setState(45); match(BOARDNAME);
			setState(46); match(NAME);
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 4) | (1L << 6) | (1L << 18))) != 0)) {
				{
				setState(50);
				switch (_input.LA(1)) {
				case 6:
					{
					setState(47); boardGravity();
					}
					break;
				case 4:
					{
					setState(48); boardFric1();
					}
					break;
				case 18:
					{
					setState(49); boardFric2();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(54);
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
		enterRule(_localctx, 6, RULE_boardGravity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55); match(6);
			setState(56); match(EQUALS);
			setState(57); match(FLOAT);
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
		enterRule(_localctx, 8, RULE_boardFric1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59); match(4);
			setState(60); match(EQUALS);
			setState(61); match(FLOAT);
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
		enterRule(_localctx, 10, RULE_boardFric2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63); match(18);
			setState(64); match(EQUALS);
			setState(65); match(FLOAT);
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
		enterRule(_localctx, 12, RULE_ballLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67); match(17);
			setState(68); match(EQUALS);
			setState(69); match(NAME);
			setState(70); match(12);
			setState(71); match(EQUALS);
			setState(72); match(FLOAT);
			setState(73); match(13);
			setState(74); match(EQUALS);
			setState(75); match(FLOAT);
			setState(76); match(7);
			setState(77); match(EQUALS);
			setState(78); match(FLOAT);
			setState(79); match(1);
			setState(80); match(EQUALS);
			setState(81); match(FLOAT);
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
		enterRule(_localctx, 14, RULE_sqBumperLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83); match(3);
			setState(84); match(EQUALS);
			setState(85); match(NAME);
			setState(86); match(12);
			setState(87); match(EQUALS);
			setState(88); match(FLOAT);
			setState(89); match(13);
			setState(90); match(EQUALS);
			setState(91); match(FLOAT);
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
		enterRule(_localctx, 16, RULE_cirBumperLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93); match(9);
			setState(94); match(EQUALS);
			setState(95); match(NAME);
			setState(96); match(12);
			setState(97); match(EQUALS);
			setState(98); match(FLOAT);
			setState(99); match(13);
			setState(100); match(EQUALS);
			setState(101); match(FLOAT);
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
		enterRule(_localctx, 18, RULE_triBumperLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); match(11);
			setState(104); match(EQUALS);
			setState(105); match(NAME);
			setState(106); match(12);
			setState(107); match(EQUALS);
			setState(108); match(FLOAT);
			setState(109); match(13);
			setState(110); match(EQUALS);
			setState(111); match(FLOAT);
			setState(112); match(8);
			setState(113); match(EQUALS);
			setState(114); match(FLOAT);
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
		enterRule(_localctx, 20, RULE_rtFlipLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116); match(15);
			setState(117); match(EQUALS);
			setState(118); match(NAME);
			setState(119); match(12);
			setState(120); match(EQUALS);
			setState(121); match(FLOAT);
			setState(122); match(13);
			setState(123); match(EQUALS);
			setState(124); match(FLOAT);
			setState(125); match(8);
			setState(126); match(EQUALS);
			setState(127); match(FLOAT);
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
		enterRule(_localctx, 22, RULE_lftFlipLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129); match(16);
			setState(130); match(EQUALS);
			setState(131); match(NAME);
			setState(132); match(12);
			setState(133); match(EQUALS);
			setState(134); match(FLOAT);
			setState(135); match(13);
			setState(136); match(EQUALS);
			setState(137); match(FLOAT);
			setState(138); match(8);
			setState(139); match(EQUALS);
			setState(140); match(FLOAT);
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
		enterRule(_localctx, 24, RULE_absorberLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142); match(5);
			setState(143); match(EQUALS);
			setState(144); match(NAME);
			setState(145); match(12);
			setState(146); match(EQUALS);
			setState(147); match(FLOAT);
			setState(148); match(13);
			setState(149); match(EQUALS);
			setState(150); match(FLOAT);
			setState(151); match(19);
			setState(152); match(EQUALS);
			setState(153); match(FLOAT);
			setState(154); match(10);
			setState(155); match(EQUALS);
			setState(156); match(FLOAT);
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
		enterRule(_localctx, 26, RULE_fireLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158); match(2);
			setState(159); match(EQUALS);
			setState(160); match(NAME);
			setState(161); match(14);
			setState(162); match(EQUALS);
			setState(163); match(NAME);
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
		"\2\3\34\u00a8\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3+\n\3\f\3\16\3.\13\3\3\4\3"+
		"\4\3\4\3\4\3\4\7\4\65\n\4\f\4\16\48\13\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\2\20\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\2\2\u00a4\2\36\3\2\2\2\4!\3\2\2\2\6/\3\2\2\2\b9\3\2\2\2\n=\3\2\2\2"+
		"\fA\3\2\2\2\16E\3\2\2\2\20U\3\2\2\2\22_\3\2\2\2\24i\3\2\2\2\26v\3\2\2"+
		"\2\30\u0083\3\2\2\2\32\u0090\3\2\2\2\34\u00a0\3\2\2\2\36\37\5\4\3\2\37"+
		" \7\1\2\2 \3\3\2\2\2!,\5\6\4\2\"+\5\16\b\2#+\5\20\t\2$+\5\22\n\2%+\5\24"+
		"\13\2&+\5\26\f\2\'+\5\30\r\2(+\5\32\16\2)+\5\34\17\2*\"\3\2\2\2*#\3\2"+
		"\2\2*$\3\2\2\2*%\3\2\2\2*&\3\2\2\2*\'\3\2\2\2*(\3\2\2\2*)\3\2\2\2+.\3"+
		"\2\2\2,*\3\2\2\2,-\3\2\2\2-\5\3\2\2\2.,\3\2\2\2/\60\7\34\2\2\60\66\7\33"+
		"\2\2\61\65\5\b\5\2\62\65\5\n\6\2\63\65\5\f\7\2\64\61\3\2\2\2\64\62\3\2"+
		"\2\2\64\63\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\67\7\3\2\2"+
		"\28\66\3\2\2\29:\7\b\2\2:;\7\30\2\2;<\7\31\2\2<\t\3\2\2\2=>\7\6\2\2>?"+
		"\7\30\2\2?@\7\31\2\2@\13\3\2\2\2AB\7\24\2\2BC\7\30\2\2CD\7\31\2\2D\r\3"+
		"\2\2\2EF\7\23\2\2FG\7\30\2\2GH\7\33\2\2HI\7\16\2\2IJ\7\30\2\2JK\7\31\2"+
		"\2KL\7\17\2\2LM\7\30\2\2MN\7\31\2\2NO\7\t\2\2OP\7\30\2\2PQ\7\31\2\2QR"+
		"\7\3\2\2RS\7\30\2\2ST\7\31\2\2T\17\3\2\2\2UV\7\5\2\2VW\7\30\2\2WX\7\33"+
		"\2\2XY\7\16\2\2YZ\7\30\2\2Z[\7\31\2\2[\\\7\17\2\2\\]\7\30\2\2]^\7\31\2"+
		"\2^\21\3\2\2\2_`\7\13\2\2`a\7\30\2\2ab\7\33\2\2bc\7\16\2\2cd\7\30\2\2"+
		"de\7\31\2\2ef\7\17\2\2fg\7\30\2\2gh\7\31\2\2h\23\3\2\2\2ij\7\r\2\2jk\7"+
		"\30\2\2kl\7\33\2\2lm\7\16\2\2mn\7\30\2\2no\7\31\2\2op\7\17\2\2pq\7\30"+
		"\2\2qr\7\31\2\2rs\7\n\2\2st\7\30\2\2tu\7\31\2\2u\25\3\2\2\2vw\7\21\2\2"+
		"wx\7\30\2\2xy\7\33\2\2yz\7\16\2\2z{\7\30\2\2{|\7\31\2\2|}\7\17\2\2}~\7"+
		"\30\2\2~\177\7\31\2\2\177\u0080\7\n\2\2\u0080\u0081\7\30\2\2\u0081\u0082"+
		"\7\31\2\2\u0082\27\3\2\2\2\u0083\u0084\7\22\2\2\u0084\u0085\7\30\2\2\u0085"+
		"\u0086\7\33\2\2\u0086\u0087\7\16\2\2\u0087\u0088\7\30\2\2\u0088\u0089"+
		"\7\31\2\2\u0089\u008a\7\17\2\2\u008a\u008b\7\30\2\2\u008b\u008c\7\31\2"+
		"\2\u008c\u008d\7\n\2\2\u008d\u008e\7\30\2\2\u008e\u008f\7\31\2\2\u008f"+
		"\31\3\2\2\2\u0090\u0091\7\7\2\2\u0091\u0092\7\30\2\2\u0092\u0093\7\33"+
		"\2\2\u0093\u0094\7\16\2\2\u0094\u0095\7\30\2\2\u0095\u0096\7\31\2\2\u0096"+
		"\u0097\7\17\2\2\u0097\u0098\7\30\2\2\u0098\u0099\7\31\2\2\u0099\u009a"+
		"\7\25\2\2\u009a\u009b\7\30\2\2\u009b\u009c\7\31\2\2\u009c\u009d\7\f\2"+
		"\2\u009d\u009e\7\30\2\2\u009e\u009f\7\31\2\2\u009f\33\3\2\2\2\u00a0\u00a1"+
		"\7\4\2\2\u00a1\u00a2\7\30\2\2\u00a2\u00a3\7\33\2\2\u00a3\u00a4\7\20\2"+
		"\2\u00a4\u00a5\7\30\2\2\u00a5\u00a6\7\33\2\2\u00a6\35\3\2\2\2\6*,\64\66";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}