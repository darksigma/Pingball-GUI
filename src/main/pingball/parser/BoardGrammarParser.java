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
		RULE_portalLine = 15, RULE_portalOtherBoard = 16, RULE_keyIntDownLine = 17, 
		RULE_keyNameDownLine = 18, RULE_keyIntUpLine = 19, RULE_keyNameUpLine = 20, 
		RULE_keyXUpLine = 21, RULE_keyXDownLine = 22, RULE_keyYUpLine = 23, RULE_keyYDownLine = 24;
	public static final String[] ruleNames = {
		"root", "fileLines", "boardLine", "boardName", "boardGravity", "boardFric1", 
		"boardFric2", "ballLine", "sqBumperLine", "cirBumperLine", "triBumperLine", 
		"rtFlipLine", "lftFlipLine", "absorberLine", "fireLine", "portalLine", 
		"portalOtherBoard", "keyIntDownLine", "keyNameDownLine", "keyIntUpLine", 
		"keyNameUpLine", "keyXUpLine", "keyXDownLine", "keyYUpLine", "keyYDownLine"
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
			setState(50); fileLines();
			setState(51); match(EOF);
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
		public KeyXUpLineContext keyXUpLine(int i) {
			return getRuleContext(KeyXUpLineContext.class,i);
		}
		public List<RtFlipLineContext> rtFlipLine() {
			return getRuleContexts(RtFlipLineContext.class);
		}
		public List<KeyNameUpLineContext> keyNameUpLine() {
			return getRuleContexts(KeyNameUpLineContext.class);
		}
		public List<KeyXUpLineContext> keyXUpLine() {
			return getRuleContexts(KeyXUpLineContext.class);
		}
		public KeyXDownLineContext keyXDownLine(int i) {
			return getRuleContext(KeyXDownLineContext.class,i);
		}
		public KeyYDownLineContext keyYDownLine(int i) {
			return getRuleContext(KeyYDownLineContext.class,i);
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
		public KeyYUpLineContext keyYUpLine(int i) {
			return getRuleContext(KeyYUpLineContext.class,i);
		}
		public List<KeyYUpLineContext> keyYUpLine() {
			return getRuleContexts(KeyYUpLineContext.class);
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
		public List<KeyXDownLineContext> keyXDownLine() {
			return getRuleContexts(KeyXDownLineContext.class);
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
		public List<KeyYDownLineContext> keyYDownLine() {
			return getRuleContexts(KeyYDownLineContext.class);
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
			setState(53); boardLine();
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 5) | (1L << 9) | (1L << 10) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 19) | (1L << 23) | (1L << 25) | (1L << 27) | (1L << 28))) != 0)) {
				{
				setState(71);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(54); ballLine();
					}
					break;

				case 2:
					{
					setState(55); sqBumperLine();
					}
					break;

				case 3:
					{
					setState(56); cirBumperLine();
					}
					break;

				case 4:
					{
					setState(57); triBumperLine();
					}
					break;

				case 5:
					{
					setState(58); rtFlipLine();
					}
					break;

				case 6:
					{
					setState(59); lftFlipLine();
					}
					break;

				case 7:
					{
					setState(60); absorberLine();
					}
					break;

				case 8:
					{
					setState(61); fireLine();
					}
					break;

				case 9:
					{
					setState(62); portalLine();
					}
					break;

				case 10:
					{
					setState(63); keyIntDownLine();
					}
					break;

				case 11:
					{
					setState(64); keyIntUpLine();
					}
					break;

				case 12:
					{
					setState(65); keyNameDownLine();
					}
					break;

				case 13:
					{
					setState(66); keyNameUpLine();
					}
					break;

				case 14:
					{
					setState(67); keyXUpLine();
					}
					break;

				case 15:
					{
					setState(68); keyXDownLine();
					}
					break;

				case 16:
					{
					setState(69); keyYUpLine();
					}
					break;

				case 17:
					{
					setState(70); keyYDownLine();
					}
					break;
				}
				}
				setState(75);
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
			setState(76); match(17);
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 3) | (1L << 4) | (1L << 13))) != 0)) {
				{
				setState(81);
				switch (_input.LA(1)) {
				case 2:
					{
					setState(77); boardName();
					}
					break;
				case 4:
					{
					setState(78); boardGravity();
					}
					break;
				case 3:
					{
					setState(79); boardFric1();
					}
					break;
				case 13:
					{
					setState(80); boardFric2();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(85);
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
			setState(86); match(2);
			setState(87); match(EQUALS);
			setState(88); match(NAME);
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
			setState(90); match(4);
			setState(91); match(EQUALS);
			setState(92);
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
			setState(94); match(3);
			setState(95); match(EQUALS);
			setState(96); match(FLOAT);
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
			setState(98); match(13);
			setState(99); match(EQUALS);
			setState(100); match(FLOAT);
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
			setState(102); match(5);
			setState(103); match(2);
			setState(104); match(EQUALS);
			setState(105); match(NAME);
			setState(106); match(22);
			setState(107); match(EQUALS);
			setState(108); match(FLOAT);
			setState(109); match(7);
			setState(110); match(EQUALS);
			setState(111); match(FLOAT);
			setState(112); match(18);
			setState(113); match(EQUALS);
			setState(114); match(FLOAT);
			setState(115); match(1);
			setState(116); match(EQUALS);
			setState(117); match(FLOAT);
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
			setState(119); match(12);
			setState(120); match(2);
			setState(121); match(EQUALS);
			setState(122); match(NAME);
			setState(123); match(22);
			setState(124); match(EQUALS);
			setState(125); match(FLOAT);
			setState(126); match(7);
			setState(127); match(EQUALS);
			setState(128); match(FLOAT);
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
			setState(130); match(14);
			setState(131); match(2);
			setState(132); match(EQUALS);
			setState(133); match(NAME);
			setState(134); match(22);
			setState(135); match(EQUALS);
			setState(136); match(FLOAT);
			setState(137); match(7);
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
			setState(141); match(10);
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
			setState(155); match(28);
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
			setState(169); match(25);
			setState(170); match(2);
			setState(171); match(EQUALS);
			setState(172); match(NAME);
			setState(173); match(22);
			setState(174); match(EQUALS);
			setState(175); match(FLOAT);
			setState(176); match(7);
			setState(177); match(EQUALS);
			setState(178); match(FLOAT);
			setState(179); match(20);
			setState(180); match(EQUALS);
			setState(181); match(FLOAT);
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
			setState(183); match(23);
			setState(184); match(2);
			setState(185); match(EQUALS);
			setState(186); match(NAME);
			setState(187); match(22);
			setState(188); match(EQUALS);
			setState(189); match(FLOAT);
			setState(190); match(7);
			setState(191); match(EQUALS);
			setState(192); match(FLOAT);
			setState(193); match(26);
			setState(194); match(EQUALS);
			setState(195); match(FLOAT);
			setState(196); match(21);
			setState(197); match(EQUALS);
			setState(198); match(FLOAT);
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
			setState(200); match(9);
			setState(201); match(24);
			setState(202); match(EQUALS);
			setState(203); match(NAME);
			setState(204); match(8);
			setState(205); match(EQUALS);
			setState(206); match(NAME);
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
		public PortalOtherBoardContext portalOtherBoard(int i) {
			return getRuleContext(PortalOtherBoardContext.class,i);
		}
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
		public List<PortalOtherBoardContext> portalOtherBoard() {
			return getRuleContexts(PortalOtherBoardContext.class);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208); match(19);
			setState(209); match(2);
			setState(210); match(EQUALS);
			setState(211); match(NAME);
			setState(212); match(22);
			setState(213); match(EQUALS);
			setState(214); match(FLOAT);
			setState(215); match(7);
			setState(216); match(EQUALS);
			setState(217); match(FLOAT);
			setState(221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==6) {
				{
				{
				setState(218); portalOtherBoard();
				}
				}
				setState(223);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(224); match(16);
			setState(225); match(EQUALS);
			setState(226); match(NAME);
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

	public static class PortalOtherBoardContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public TerminalNode EQUALS() { return getToken(BoardGrammarParser.EQUALS, 0); }
		public PortalOtherBoardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_portalOtherBoard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterPortalOtherBoard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitPortalOtherBoard(this);
		}
	}

	public final PortalOtherBoardContext portalOtherBoard() throws RecognitionException {
		PortalOtherBoardContext _localctx = new PortalOtherBoardContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_portalOtherBoard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228); match(6);
			setState(229); match(EQUALS);
			setState(230); match(NAME);
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
		enterRule(_localctx, 34, RULE_keyIntDownLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232); match(27);
			setState(233); match(11);
			setState(234); match(EQUALS);
			setState(235); match(FLOAT);
			setState(236); match(8);
			setState(237); match(EQUALS);
			setState(238); match(NAME);
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
		enterRule(_localctx, 36, RULE_keyNameDownLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240); match(27);
			setState(241); match(11);
			setState(242); match(EQUALS);
			setState(243); match(NAME);
			setState(244); match(8);
			setState(245); match(EQUALS);
			setState(246); match(NAME);
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
		enterRule(_localctx, 38, RULE_keyIntUpLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248); match(15);
			setState(249); match(11);
			setState(250); match(EQUALS);
			setState(251); match(FLOAT);
			setState(252); match(8);
			setState(253); match(EQUALS);
			setState(254); match(NAME);
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
		enterRule(_localctx, 40, RULE_keyNameUpLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256); match(15);
			setState(257); match(11);
			setState(258); match(EQUALS);
			setState(259); match(NAME);
			setState(260); match(8);
			setState(261); match(EQUALS);
			setState(262); match(NAME);
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

	public static class KeyXUpLineContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public KeyXUpLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyXUpLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterKeyXUpLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitKeyXUpLine(this);
		}
	}

	public final KeyXUpLineContext keyXUpLine() throws RecognitionException {
		KeyXUpLineContext _localctx = new KeyXUpLineContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_keyXUpLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264); match(15);
			setState(265); match(11);
			setState(266); match(EQUALS);
			setState(267); match(22);
			setState(268); match(8);
			setState(269); match(EQUALS);
			setState(270); match(NAME);
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

	public static class KeyXDownLineContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public KeyXDownLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyXDownLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterKeyXDownLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitKeyXDownLine(this);
		}
	}

	public final KeyXDownLineContext keyXDownLine() throws RecognitionException {
		KeyXDownLineContext _localctx = new KeyXDownLineContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_keyXDownLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272); match(27);
			setState(273); match(11);
			setState(274); match(EQUALS);
			setState(275); match(22);
			setState(276); match(8);
			setState(277); match(EQUALS);
			setState(278); match(NAME);
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

	public static class KeyYUpLineContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public KeyYUpLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyYUpLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterKeyYUpLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitKeyYUpLine(this);
		}
	}

	public final KeyYUpLineContext keyYUpLine() throws RecognitionException {
		KeyYUpLineContext _localctx = new KeyYUpLineContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_keyYUpLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280); match(15);
			setState(281); match(11);
			setState(282); match(EQUALS);
			setState(283); match(7);
			setState(284); match(8);
			setState(285); match(EQUALS);
			setState(286); match(NAME);
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

	public static class KeyYDownLineContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardGrammarParser.NAME, 0); }
		public List<TerminalNode> EQUALS() { return getTokens(BoardGrammarParser.EQUALS); }
		public TerminalNode EQUALS(int i) {
			return getToken(BoardGrammarParser.EQUALS, i);
		}
		public KeyYDownLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyYDownLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).enterKeyYDownLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardGrammarListener ) ((BoardGrammarListener)listener).exitKeyYDownLine(this);
		}
	}

	public final KeyYDownLineContext keyYDownLine() throws RecognitionException {
		KeyYDownLineContext _localctx = new KeyYDownLineContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_keyYDownLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288); match(27);
			setState(289); match(11);
			setState(290); match(EQUALS);
			setState(291); match(7);
			setState(292); match(8);
			setState(293); match(EQUALS);
			setState(294); match(NAME);
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
		"\2\3%\u012b\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20"+
		"\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27"+
		"\4\30\t\30\4\31\t\31\4\32\t\32\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3J\n\3\f\3\16\3M\13\3\3"+
		"\4\3\4\3\4\3\4\3\4\7\4T\n\4\f\4\16\4W\13\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\7\21\u00de\n\21\f\21\16\21\u00e1\13\21\3\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\2\33\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2\3\4\"\"$$\u0127\2"+
		"\64\3\2\2\2\4\67\3\2\2\2\6N\3\2\2\2\bX\3\2\2\2\n\\\3\2\2\2\f`\3\2\2\2"+
		"\16d\3\2\2\2\20h\3\2\2\2\22y\3\2\2\2\24\u0084\3\2\2\2\26\u008f\3\2\2\2"+
		"\30\u009d\3\2\2\2\32\u00ab\3\2\2\2\34\u00b9\3\2\2\2\36\u00ca\3\2\2\2 "+
		"\u00d2\3\2\2\2\"\u00e6\3\2\2\2$\u00ea\3\2\2\2&\u00f2\3\2\2\2(\u00fa\3"+
		"\2\2\2*\u0102\3\2\2\2,\u010a\3\2\2\2.\u0112\3\2\2\2\60\u011a\3\2\2\2\62"+
		"\u0122\3\2\2\2\64\65\5\4\3\2\65\66\7\1\2\2\66\3\3\2\2\2\67K\5\6\4\28J"+
		"\5\20\t\29J\5\22\n\2:J\5\24\13\2;J\5\26\f\2<J\5\30\r\2=J\5\32\16\2>J\5"+
		"\34\17\2?J\5\36\20\2@J\5 \21\2AJ\5$\23\2BJ\5(\25\2CJ\5&\24\2DJ\5*\26\2"+
		"EJ\5,\27\2FJ\5.\30\2GJ\5\60\31\2HJ\5\62\32\2I8\3\2\2\2I9\3\2\2\2I:\3\2"+
		"\2\2I;\3\2\2\2I<\3\2\2\2I=\3\2\2\2I>\3\2\2\2I?\3\2\2\2I@\3\2\2\2IA\3\2"+
		"\2\2IB\3\2\2\2IC\3\2\2\2ID\3\2\2\2IE\3\2\2\2IF\3\2\2\2IG\3\2\2\2IH\3\2"+
		"\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2L\5\3\2\2\2MK\3\2\2\2NU\7\23\2\2OT\5"+
		"\b\5\2PT\5\n\6\2QT\5\f\7\2RT\5\16\b\2SO\3\2\2\2SP\3\2\2\2SQ\3\2\2\2SR"+
		"\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2V\7\3\2\2\2WU\3\2\2\2XY\7\4\2\2"+
		"YZ\7!\2\2Z[\7$\2\2[\t\3\2\2\2\\]\7\6\2\2]^\7!\2\2^_\t\2\2\2_\13\3\2\2"+
		"\2`a\7\5\2\2ab\7!\2\2bc\7\"\2\2c\r\3\2\2\2de\7\17\2\2ef\7!\2\2fg\7\"\2"+
		"\2g\17\3\2\2\2hi\7\7\2\2ij\7\4\2\2jk\7!\2\2kl\7$\2\2lm\7\30\2\2mn\7!\2"+
		"\2no\7\"\2\2op\7\t\2\2pq\7!\2\2qr\7\"\2\2rs\7\24\2\2st\7!\2\2tu\7\"\2"+
		"\2uv\7\3\2\2vw\7!\2\2wx\7\"\2\2x\21\3\2\2\2yz\7\16\2\2z{\7\4\2\2{|\7!"+
		"\2\2|}\7$\2\2}~\7\30\2\2~\177\7!\2\2\177\u0080\7\"\2\2\u0080\u0081\7\t"+
		"\2\2\u0081\u0082\7!\2\2\u0082\u0083\7\"\2\2\u0083\23\3\2\2\2\u0084\u0085"+
		"\7\20\2\2\u0085\u0086\7\4\2\2\u0086\u0087\7!\2\2\u0087\u0088\7$\2\2\u0088"+
		"\u0089\7\30\2\2\u0089\u008a\7!\2\2\u008a\u008b\7\"\2\2\u008b\u008c\7\t"+
		"\2\2\u008c\u008d\7!\2\2\u008d\u008e\7\"\2\2\u008e\25\3\2\2\2\u008f\u0090"+
		"\7\f\2\2\u0090\u0091\7\4\2\2\u0091\u0092\7!\2\2\u0092\u0093\7$\2\2\u0093"+
		"\u0094\7\30\2\2\u0094\u0095\7!\2\2\u0095\u0096\7\"\2\2\u0096\u0097\7\t"+
		"\2\2\u0097\u0098\7!\2\2\u0098\u0099\7\"\2\2\u0099\u009a\7\26\2\2\u009a"+
		"\u009b\7!\2\2\u009b\u009c\7\"\2\2\u009c\27\3\2\2\2\u009d\u009e\7\36\2"+
		"\2\u009e\u009f\7\4\2\2\u009f\u00a0\7!\2\2\u00a0\u00a1\7$\2\2\u00a1\u00a2"+
		"\7\30\2\2\u00a2\u00a3\7!\2\2\u00a3\u00a4\7\"\2\2\u00a4\u00a5\7\t\2\2\u00a5"+
		"\u00a6\7!\2\2\u00a6\u00a7\7\"\2\2\u00a7\u00a8\7\26\2\2\u00a8\u00a9\7!"+
		"\2\2\u00a9\u00aa\7\"\2\2\u00aa\31\3\2\2\2\u00ab\u00ac\7\33\2\2\u00ac\u00ad"+
		"\7\4\2\2\u00ad\u00ae\7!\2\2\u00ae\u00af\7$\2\2\u00af\u00b0\7\30\2\2\u00b0"+
		"\u00b1\7!\2\2\u00b1\u00b2\7\"\2\2\u00b2\u00b3\7\t\2\2\u00b3\u00b4\7!\2"+
		"\2\u00b4\u00b5\7\"\2\2\u00b5\u00b6\7\26\2\2\u00b6\u00b7\7!\2\2\u00b7\u00b8"+
		"\7\"\2\2\u00b8\33\3\2\2\2\u00b9\u00ba\7\31\2\2\u00ba\u00bb\7\4\2\2\u00bb"+
		"\u00bc\7!\2\2\u00bc\u00bd\7$\2\2\u00bd\u00be\7\30\2\2\u00be\u00bf\7!\2"+
		"\2\u00bf\u00c0\7\"\2\2\u00c0\u00c1\7\t\2\2\u00c1\u00c2\7!\2\2\u00c2\u00c3"+
		"\7\"\2\2\u00c3\u00c4\7\34\2\2\u00c4\u00c5\7!\2\2\u00c5\u00c6\7\"\2\2\u00c6"+
		"\u00c7\7\27\2\2\u00c7\u00c8\7!\2\2\u00c8\u00c9\7\"\2\2\u00c9\35\3\2\2"+
		"\2\u00ca\u00cb\7\13\2\2\u00cb\u00cc\7\32\2\2\u00cc\u00cd\7!\2\2\u00cd"+
		"\u00ce\7$\2\2\u00ce\u00cf\7\n\2\2\u00cf\u00d0\7!\2\2\u00d0\u00d1\7$\2"+
		"\2\u00d1\37\3\2\2\2\u00d2\u00d3\7\25\2\2\u00d3\u00d4\7\4\2\2\u00d4\u00d5"+
		"\7!\2\2\u00d5\u00d6\7$\2\2\u00d6\u00d7\7\30\2\2\u00d7\u00d8\7!\2\2\u00d8"+
		"\u00d9\7\"\2\2\u00d9\u00da\7\t\2\2\u00da\u00db\7!\2\2\u00db\u00df\7\""+
		"\2\2\u00dc\u00de\5\"\22\2\u00dd\u00dc\3\2\2\2\u00de\u00e1\3\2\2\2\u00df"+
		"\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e2\3\2\2\2\u00e1\u00df\3\2"+
		"\2\2\u00e2\u00e3\7\22\2\2\u00e3\u00e4\7!\2\2\u00e4\u00e5\7$\2\2\u00e5"+
		"!\3\2\2\2\u00e6\u00e7\7\b\2\2\u00e7\u00e8\7!\2\2\u00e8\u00e9\7$\2\2\u00e9"+
		"#\3\2\2\2\u00ea\u00eb\7\35\2\2\u00eb\u00ec\7\r\2\2\u00ec\u00ed\7!\2\2"+
		"\u00ed\u00ee\7\"\2\2\u00ee\u00ef\7\n\2\2\u00ef\u00f0\7!\2\2\u00f0\u00f1"+
		"\7$\2\2\u00f1%\3\2\2\2\u00f2\u00f3\7\35\2\2\u00f3\u00f4\7\r\2\2\u00f4"+
		"\u00f5\7!\2\2\u00f5\u00f6\7$\2\2\u00f6\u00f7\7\n\2\2\u00f7\u00f8\7!\2"+
		"\2\u00f8\u00f9\7$\2\2\u00f9\'\3\2\2\2\u00fa\u00fb\7\21\2\2\u00fb\u00fc"+
		"\7\r\2\2\u00fc\u00fd\7!\2\2\u00fd\u00fe\7\"\2\2\u00fe\u00ff\7\n\2\2\u00ff"+
		"\u0100\7!\2\2\u0100\u0101\7$\2\2\u0101)\3\2\2\2\u0102\u0103\7\21\2\2\u0103"+
		"\u0104\7\r\2\2\u0104\u0105\7!\2\2\u0105\u0106\7$\2\2\u0106\u0107\7\n\2"+
		"\2\u0107\u0108\7!\2\2\u0108\u0109\7$\2\2\u0109+\3\2\2\2\u010a\u010b\7"+
		"\21\2\2\u010b\u010c\7\r\2\2\u010c\u010d\7!\2\2\u010d\u010e\7\30\2\2\u010e"+
		"\u010f\7\n\2\2\u010f\u0110\7!\2\2\u0110\u0111\7$\2\2\u0111-\3\2\2\2\u0112"+
		"\u0113\7\35\2\2\u0113\u0114\7\r\2\2\u0114\u0115\7!\2\2\u0115\u0116\7\30"+
		"\2\2\u0116\u0117\7\n\2\2\u0117\u0118\7!\2\2\u0118\u0119\7$\2\2\u0119/"+
		"\3\2\2\2\u011a\u011b\7\21\2\2\u011b\u011c\7\r\2\2\u011c\u011d\7!\2\2\u011d"+
		"\u011e\7\t\2\2\u011e\u011f\7\n\2\2\u011f\u0120\7!\2\2\u0120\u0121\7$\2"+
		"\2\u0121\61\3\2\2\2\u0122\u0123\7\35\2\2\u0123\u0124\7\r\2\2\u0124\u0125"+
		"\7!\2\2\u0125\u0126\7\t\2\2\u0126\u0127\7\n\2\2\u0127\u0128\7!\2\2\u0128"+
		"\u0129\7$\2\2\u0129\63\3\2\2\2\7IKSU\u00df";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}