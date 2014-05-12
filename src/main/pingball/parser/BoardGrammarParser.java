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
		T__27=1, T__26=2, T__25=3, T__24=4, T__23=5, T__22=6, T__21=7, T__20=8, 
		T__19=9, T__18=10, T__17=11, T__16=12, T__15=13, T__14=14, T__13=15, T__12=16, 
		T__11=17, T__10=18, T__9=19, T__8=20, T__7=21, T__6=22, T__5=23, T__4=24, 
		T__3=25, T__2=26, T__1=27, T__0=28, WHITESPACE=29, COMMENT=30, EQUALS=31, 
		FLOAT=32, INTEGER=33, NAME=34, KEY=35;
	public static final String[] tokenNames = {
		"<INVALID>", "'yVelocity'", "'name'", "'friction1'", "'gravity'", "'ball'", 
		"'otherBoard'", "'y'", "'action'", "'fire'", "'triangleBumper'", "'key'", 
		"'squareBumper'", "'friction2'", "'circleBumper'", "'keyup'", "'otherPortal'", 
		"'board'", "'xVelocity'", "'portal'", "'orientation'", "'height'", "'x'", 
		"'absorber'", "'trigger'", "'leftFlipper'", "'width'", "'keydown'", "'rightFlipper'", 
		"WHITESPACE", "COMMENT", "'='", "FLOAT", "INTEGER", "NAME", "KEY"
	};
	public static final int
		RULE_root = 0, RULE_fileLines = 1, RULE_boardLine = 2, RULE_boardName = 3, 
		RULE_boardGravity = 4, RULE_boardFric1 = 5, RULE_boardFric2 = 6, RULE_ballLine = 7, 
		RULE_sqBumperLine = 8, RULE_cirBumperLine = 9, RULE_triBumperLine = 10, 
		RULE_rtFlipLine = 11, RULE_lftFlipLine = 12, RULE_absorberLine = 13, RULE_fireLine = 14, 
		RULE_portalLine = 15, RULE_keyIntDownLine = 16, RULE_keyNameDownLine = 17, 
		RULE_keyIntUpLine = 18, RULE_keyNameUpLine = 19;
	public static final String[] ruleNames = {
		"root", "fileLines", "boardLine", "boardName", "boardGravity", "boardFric1", 
		"boardFric2", "ballLine", "sqBumperLine", "cirBumperLine", "triBumperLine", 
		"rtFlipLine", "lftFlipLine", "absorberLine", "fireLine", "portalLine", 
		"keyIntDownLine", "keyNameDownLine", "keyIntUpLine", "keyNameUpLine"
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
			setState(40); fileLines();
			setState(41); match(EOF);
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
		public PortalLineContext portalLine(int i) {
			return getRuleContext(PortalLineContext.class,i);
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
		public List<PortalLineContext> portalLine() {
			return getRuleContexts(PortalLineContext.class);
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
			setState(43); boardLine();
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 5) | (1L << 9) | (1L << 10) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 19) | (1L << 23) | (1L << 25) | (1L << 27) | (1L << 28))) != 0)) {
				{
				setState(57);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(44); ballLine();
					}
					break;

				case 2:
					{
					setState(45); sqBumperLine();
					}
					break;

				case 3:
					{
					setState(46); cirBumperLine();
					}
					break;

				case 4:
					{
					setState(47); triBumperLine();
					}
					break;

				case 5:
					{
					setState(48); rtFlipLine();
					}
					break;

				case 6:
					{
					setState(49); lftFlipLine();
					}
					break;

				case 7:
					{
					setState(50); absorberLine();
					}
					break;

				case 8:
					{
					setState(51); fireLine();
					}
					break;

				case 9:
					{
					setState(52); portalLine();
					}
					break;

				case 10:
					{
					setState(53); keyIntDownLine();
					}
					break;

				case 11:
					{
					setState(54); keyIntUpLine();
					}
					break;

				case 12:
					{
					setState(55); keyNameDownLine();
					}
					break;

				case 13:
					{
					setState(56); keyNameUpLine();
					}
					break;
				}
				}
				setState(61);
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
			setState(62); match(17);
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 3) | (1L << 4) | (1L << 13))) != 0)) {
				{
				setState(67);
				switch (_input.LA(1)) {
				case 2:
					{
					setState(63); boardName();
					}
					break;
				case 4:
					{
					setState(64); boardGravity();
					}
					break;
				case 3:
					{
					setState(65); boardFric1();
					}
					break;
				case 13:
					{
					setState(66); boardFric2();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(71);
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
			setState(72); match(2);
			setState(73); match(EQUALS);
			setState(74); match(NAME);
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
			setState(76); match(4);
			setState(77); match(EQUALS);
			setState(78);
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
			setState(80); match(3);
			setState(81); match(EQUALS);
			setState(82); match(FLOAT);
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
			setState(84); match(13);
			setState(85); match(EQUALS);
			setState(86); match(FLOAT);
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
			setState(88); match(5);
			setState(89); match(2);
			setState(90); match(EQUALS);
			setState(91); match(NAME);
			setState(92); match(22);
			setState(93); match(EQUALS);
			setState(94); match(FLOAT);
			setState(95); match(7);
			setState(96); match(EQUALS);
			setState(97); match(FLOAT);
			setState(98); match(18);
			setState(99); match(EQUALS);
			setState(100); match(FLOAT);
			setState(101); match(1);
			setState(102); match(EQUALS);
			setState(103); match(FLOAT);
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
			setState(105); match(12);
			setState(106); match(2);
			setState(107); match(EQUALS);
			setState(108); match(NAME);
			setState(109); match(22);
			setState(110); match(EQUALS);
			setState(111); match(FLOAT);
			setState(112); match(7);
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
			setState(116); match(14);
			setState(117); match(2);
			setState(118); match(EQUALS);
			setState(119); match(NAME);
			setState(120); match(22);
			setState(121); match(EQUALS);
			setState(122); match(FLOAT);
			setState(123); match(7);
			setState(124); match(EQUALS);
			setState(125); match(FLOAT);
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
			setState(127); match(10);
			setState(128); match(2);
			setState(129); match(EQUALS);
			setState(130); match(NAME);
			setState(131); match(22);
			setState(132); match(EQUALS);
			setState(133); match(FLOAT);
			setState(134); match(7);
			setState(135); match(EQUALS);
			setState(136); match(FLOAT);
			setState(137); match(20);
			setState(138); match(EQUALS);
			setState(139); match(FLOAT);
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
			setState(141); match(28);
			setState(142); match(2);
			setState(143); match(EQUALS);
			setState(144); match(NAME);
			setState(145); match(22);
			setState(146); match(EQUALS);
			setState(147); match(FLOAT);
			setState(148); match(7);
			setState(149); match(EQUALS);
			setState(150); match(FLOAT);
			setState(151); match(20);
			setState(152); match(EQUALS);
			setState(153); match(FLOAT);
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
			setState(155); match(25);
			setState(156); match(2);
			setState(157); match(EQUALS);
			setState(158); match(NAME);
			setState(159); match(22);
			setState(160); match(EQUALS);
			setState(161); match(FLOAT);
			setState(162); match(7);
			setState(163); match(EQUALS);
			setState(164); match(FLOAT);
			setState(165); match(20);
			setState(166); match(EQUALS);
			setState(167); match(FLOAT);
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
			setState(169); match(23);
			setState(170); match(2);
			setState(171); match(EQUALS);
			setState(172); match(NAME);
			setState(173); match(22);
			setState(174); match(EQUALS);
			setState(175); match(FLOAT);
			setState(176); match(7);
			setState(177); match(EQUALS);
			setState(178); match(FLOAT);
			setState(179); match(26);
			setState(180); match(EQUALS);
			setState(181); match(FLOAT);
			setState(182); match(21);
			setState(183); match(EQUALS);
			setState(184); match(FLOAT);
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
			setState(186); match(9);
			setState(187); match(24);
			setState(188); match(EQUALS);
			setState(189); match(NAME);
			setState(190); match(8);
			setState(191); match(EQUALS);
			setState(192); match(NAME);
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

	public static class PortalLineContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(BoardGrammarParser.NAME); }
		public List<TerminalNode> FLOAT() { return getTokens(BoardGrammarParser.FLOAT); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public TerminalNode NAME(int i) {
			return getToken(BoardGrammarParser.NAME, i);
		}
		public TerminalNode FLOAT(int i) {
			return getToken(BoardGrammarParser.FLOAT, i);
		}
		public PortalLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_portalLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterPortalLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitPortalLine(this);
		}
	}

	public final PortalLineContext portalLine() throws RecognitionException {
		PortalLineContext _localctx = new PortalLineContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_portalLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194); match(19);
			setState(195); match(2);
			setState(196); match(EQUALS);
			setState(197); match(NAME);
			setState(198); match(22);
			setState(199); match(EQUALS);
			setState(200); match(FLOAT);
			setState(201); match(7);
			setState(202); match(EQUALS);
			setState(203); match(FLOAT);
			setState(204); match(6);
			setState(205); match(EQUALS);
			setState(206); match(NAME);
			setState(207); match(16);
			setState(208); match(EQUALS);
			setState(209); match(NAME);
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
		enterRule(_localctx, 32, RULE_keyIntDownLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211); match(27);
			setState(212); match(11);
			setState(213); match(EQUALS);
			setState(214); match(FLOAT);
			setState(215); match(8);
			setState(216); match(EQUALS);
			setState(217); match(NAME);
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
		enterRule(_localctx, 34, RULE_keyNameDownLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219); match(27);
			setState(220); match(11);
			setState(221); match(EQUALS);
			setState(222); match(NAME);
			setState(223); match(8);
			setState(224); match(EQUALS);
			setState(225); match(NAME);
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
		enterRule(_localctx, 36, RULE_keyIntUpLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227); match(15);
			setState(228); match(11);
			setState(229); match(EQUALS);
			setState(230); match(FLOAT);
			setState(231); match(8);
			setState(232); match(EQUALS);
			setState(233); match(NAME);
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
		enterRule(_localctx, 38, RULE_keyNameUpLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235); match(15);
			setState(236); match(11);
			setState(237); match(EQUALS);
			setState(238); match(NAME);
			setState(239); match(8);
			setState(240); match(EQUALS);
			setState(241); match(NAME);
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
		"\2\3%\u00f6\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20"+
		"\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3<\n\3\f\3\16\3?\13"+
		"\3\3\4\3\4\3\4\3\4\3\4\7\4F\n\4\f\4\16\4I\13\4\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\2\26"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\3\4\"\"$$\u00f2\2*\3\2"+
		"\2\2\4-\3\2\2\2\6@\3\2\2\2\bJ\3\2\2\2\nN\3\2\2\2\fR\3\2\2\2\16V\3\2\2"+
		"\2\20Z\3\2\2\2\22k\3\2\2\2\24v\3\2\2\2\26\u0081\3\2\2\2\30\u008f\3\2\2"+
		"\2\32\u009d\3\2\2\2\34\u00ab\3\2\2\2\36\u00bc\3\2\2\2 \u00c4\3\2\2\2\""+
		"\u00d5\3\2\2\2$\u00dd\3\2\2\2&\u00e5\3\2\2\2(\u00ed\3\2\2\2*+\5\4\3\2"+
		"+,\7\1\2\2,\3\3\2\2\2-=\5\6\4\2.<\5\20\t\2/<\5\22\n\2\60<\5\24\13\2\61"+
		"<\5\26\f\2\62<\5\30\r\2\63<\5\32\16\2\64<\5\34\17\2\65<\5\36\20\2\66<"+
		"\5 \21\2\67<\5\"\22\28<\5&\24\29<\5$\23\2:<\5(\25\2;.\3\2\2\2;/\3\2\2"+
		"\2;\60\3\2\2\2;\61\3\2\2\2;\62\3\2\2\2;\63\3\2\2\2;\64\3\2\2\2;\65\3\2"+
		"\2\2;\66\3\2\2\2;\67\3\2\2\2;8\3\2\2\2;9\3\2\2\2;:\3\2\2\2<?\3\2\2\2="+
		";\3\2\2\2=>\3\2\2\2>\5\3\2\2\2?=\3\2\2\2@G\7\23\2\2AF\5\b\5\2BF\5\n\6"+
		"\2CF\5\f\7\2DF\5\16\b\2EA\3\2\2\2EB\3\2\2\2EC\3\2\2\2ED\3\2\2\2FI\3\2"+
		"\2\2GE\3\2\2\2GH\3\2\2\2H\7\3\2\2\2IG\3\2\2\2JK\7\4\2\2KL\7!\2\2LM\7$"+
		"\2\2M\t\3\2\2\2NO\7\6\2\2OP\7!\2\2PQ\t\2\2\2Q\13\3\2\2\2RS\7\5\2\2ST\7"+
		"!\2\2TU\7\"\2\2U\r\3\2\2\2VW\7\17\2\2WX\7!\2\2XY\7\"\2\2Y\17\3\2\2\2Z"+
		"[\7\7\2\2[\\\7\4\2\2\\]\7!\2\2]^\7$\2\2^_\7\30\2\2_`\7!\2\2`a\7\"\2\2"+
		"ab\7\t\2\2bc\7!\2\2cd\7\"\2\2de\7\24\2\2ef\7!\2\2fg\7\"\2\2gh\7\3\2\2"+
		"hi\7!\2\2ij\7\"\2\2j\21\3\2\2\2kl\7\16\2\2lm\7\4\2\2mn\7!\2\2no\7$\2\2"+
		"op\7\30\2\2pq\7!\2\2qr\7\"\2\2rs\7\t\2\2st\7!\2\2tu\7\"\2\2u\23\3\2\2"+
		"\2vw\7\20\2\2wx\7\4\2\2xy\7!\2\2yz\7$\2\2z{\7\30\2\2{|\7!\2\2|}\7\"\2"+
		"\2}~\7\t\2\2~\177\7!\2\2\177\u0080\7\"\2\2\u0080\25\3\2\2\2\u0081\u0082"+
		"\7\f\2\2\u0082\u0083\7\4\2\2\u0083\u0084\7!\2\2\u0084\u0085\7$\2\2\u0085"+
		"\u0086\7\30\2\2\u0086\u0087\7!\2\2\u0087\u0088\7\"\2\2\u0088\u0089\7\t"+
		"\2\2\u0089\u008a\7!\2\2\u008a\u008b\7\"\2\2\u008b\u008c\7\26\2\2\u008c"+
		"\u008d\7!\2\2\u008d\u008e\7\"\2\2\u008e\27\3\2\2\2\u008f\u0090\7\36\2"+
		"\2\u0090\u0091\7\4\2\2\u0091\u0092\7!\2\2\u0092\u0093\7$\2\2\u0093\u0094"+
		"\7\30\2\2\u0094\u0095\7!\2\2\u0095\u0096\7\"\2\2\u0096\u0097\7\t\2\2\u0097"+
		"\u0098\7!\2\2\u0098\u0099\7\"\2\2\u0099\u009a\7\26\2\2\u009a\u009b\7!"+
		"\2\2\u009b\u009c\7\"\2\2\u009c\31\3\2\2\2\u009d\u009e\7\33\2\2\u009e\u009f"+
		"\7\4\2\2\u009f\u00a0\7!\2\2\u00a0\u00a1\7$\2\2\u00a1\u00a2\7\30\2\2\u00a2"+
		"\u00a3\7!\2\2\u00a3\u00a4\7\"\2\2\u00a4\u00a5\7\t\2\2\u00a5\u00a6\7!\2"+
		"\2\u00a6\u00a7\7\"\2\2\u00a7\u00a8\7\26\2\2\u00a8\u00a9\7!\2\2\u00a9\u00aa"+
		"\7\"\2\2\u00aa\33\3\2\2\2\u00ab\u00ac\7\31\2\2\u00ac\u00ad\7\4\2\2\u00ad"+
		"\u00ae\7!\2\2\u00ae\u00af\7$\2\2\u00af\u00b0\7\30\2\2\u00b0\u00b1\7!\2"+
		"\2\u00b1\u00b2\7\"\2\2\u00b2\u00b3\7\t\2\2\u00b3\u00b4\7!\2\2\u00b4\u00b5"+
		"\7\"\2\2\u00b5\u00b6\7\34\2\2\u00b6\u00b7\7!\2\2\u00b7\u00b8\7\"\2\2\u00b8"+
		"\u00b9\7\27\2\2\u00b9\u00ba\7!\2\2\u00ba\u00bb\7\"\2\2\u00bb\35\3\2\2"+
		"\2\u00bc\u00bd\7\13\2\2\u00bd\u00be\7\32\2\2\u00be\u00bf\7!\2\2\u00bf"+
		"\u00c0\7$\2\2\u00c0\u00c1\7\n\2\2\u00c1\u00c2\7!\2\2\u00c2\u00c3\7$\2"+
		"\2\u00c3\37\3\2\2\2\u00c4\u00c5\7\25\2\2\u00c5\u00c6\7\4\2\2\u00c6\u00c7"+
		"\7!\2\2\u00c7\u00c8\7$\2\2\u00c8\u00c9\7\30\2\2\u00c9\u00ca\7!\2\2\u00ca"+
		"\u00cb\7\"\2\2\u00cb\u00cc\7\t\2\2\u00cc\u00cd\7!\2\2\u00cd\u00ce\7\""+
		"\2\2\u00ce\u00cf\7\b\2\2\u00cf\u00d0\7!\2\2\u00d0\u00d1\7$\2\2\u00d1\u00d2"+
		"\7\22\2\2\u00d2\u00d3\7!\2\2\u00d3\u00d4\7$\2\2\u00d4!\3\2\2\2\u00d5\u00d6"+
		"\7\35\2\2\u00d6\u00d7\7\r\2\2\u00d7\u00d8\7!\2\2\u00d8\u00d9\7\"\2\2\u00d9"+
		"\u00da\7\n\2\2\u00da\u00db\7!\2\2\u00db\u00dc\7$\2\2\u00dc#\3\2\2\2\u00dd"+
		"\u00de\7\35\2\2\u00de\u00df\7\r\2\2\u00df\u00e0\7!\2\2\u00e0\u00e1\7$"+
		"\2\2\u00e1\u00e2\7\n\2\2\u00e2\u00e3\7!\2\2\u00e3\u00e4\7$\2\2\u00e4%"+
		"\3\2\2\2\u00e5\u00e6\7\21\2\2\u00e6\u00e7\7\r\2\2\u00e7\u00e8\7!\2\2\u00e8"+
		"\u00e9\7\"\2\2\u00e9\u00ea\7\n\2\2\u00ea\u00eb\7!\2\2\u00eb\u00ec\7$\2"+
		"\2\u00ec\'\3\2\2\2\u00ed\u00ee\7\21\2\2\u00ee\u00ef\7\r\2\2\u00ef\u00f0"+
		"\7!\2\2\u00f0\u00f1\7$\2\2\u00f1\u00f2\7\n\2\2\u00f2\u00f3\7!\2\2\u00f3"+
		"\u00f4\7$\2\2\u00f4)\3\2\2\2\6;=EG";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}