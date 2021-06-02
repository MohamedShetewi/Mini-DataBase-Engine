// Generated from /Users/mohammad.abubeih/Desktop/Others/Grammars/sqlite/MiniSQLParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniSQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SCOL=1, DOT=2, OPEN_PAR=3, CLOSE_PAR=4, COMMA=5, ASSIGN=6, STAR=7, PLUS=8, 
		MINUS=9, TILDE=10, PIPE2=11, DIV=12, MOD=13, LT2=14, GT2=15, AMP=16, PIPE=17, 
		LT=18, LT_EQ=19, GT=20, GT_EQ=21, EQ=22, NOT_EQ1=23, NOT_EQ2=24, ABORT_=25, 
		ACTION_=26, ADD_=27, AFTER_=28, ALL_=29, ALTER_=30, ANALYZE_=31, AND_=32, 
		XOR_=33, AS_=34, ASC_=35, ATTACH_=36, AUTOINCREMENT_=37, BEFORE_=38, BEGIN_=39, 
		BETWEEN_=40, BY_=41, CASCADE_=42, CASE_=43, CAST_=44, CHECK_=45, COLLATE_=46, 
		COLUMN_=47, INT_=48, DECIMAL_=49, VARCHAR_=50, DATE_=51, COMMIT_=52, CONFLICT_=53, 
		CONSTRAINT_=54, CREATE_=55, CROSS_=56, CURRENT_DATE_=57, CURRENT_TIME_=58, 
		CURRENT_TIMESTAMP_=59, DATABASE_=60, DEFAULT_=61, DEFERRABLE_=62, DEFERRED_=63, 
		DELETE_=64, DESC_=65, DETACH_=66, DISTINCT_=67, DROP_=68, EACH_=69, ELSE_=70, 
		END_=71, ESCAPE_=72, EXCEPT_=73, EXCLUSIVE_=74, EXISTS_=75, EXPLAIN_=76, 
		FAIL_=77, FOR_=78, FOREIGN_=79, FROM_=80, FULL_=81, GLOB_=82, GROUP_=83, 
		HAVING_=84, IF_=85, IGNORE_=86, IMMEDIATE_=87, IN_=88, INDEX_=89, INDEXED_=90, 
		INITIALLY_=91, INNER_=92, INSERT_=93, INSTEAD_=94, INTERSECT_=95, INTO_=96, 
		IS_=97, ISNULL_=98, JOIN_=99, KEY_=100, LEFT_=101, LIKE_=102, LIMIT_=103, 
		MATCH_=104, NATURAL_=105, NO_=106, NOT_=107, NOTNULL_=108, NULL_=109, 
		OF_=110, OFFSET_=111, ON_=112, OR_=113, ORDER_=114, OUTER_=115, PLAN_=116, 
		PRAGMA_=117, PRIMARY_=118, QUERY_=119, RAISE_=120, RECURSIVE_=121, REFERENCES_=122, 
		REGEXP_=123, REINDEX_=124, RELEASE_=125, RENAME_=126, REPLACE_=127, RESTRICT_=128, 
		RIGHT_=129, ROLLBACK_=130, ROW_=131, ROWS_=132, SAVEPOINT_=133, SELECT_=134, 
		SET_=135, TABLE_=136, TEMP_=137, TEMPORARY_=138, THEN_=139, TO_=140, TRANSACTION_=141, 
		TRIGGER_=142, UNION_=143, UNIQUE_=144, UPDATE_=145, USING_=146, VACUUM_=147, 
		VALUES_=148, VIEW_=149, VIRTUAL_=150, WHEN_=151, WHERE_=152, WITH_=153, 
		WITHOUT_=154, FIRST_VALUE_=155, OVER_=156, PARTITION_=157, RANGE_=158, 
		PRECEDING_=159, UNBOUNDED_=160, CURRENT_=161, FOLLOWING_=162, CUME_DIST_=163, 
		DENSE_RANK_=164, LAG_=165, LAST_VALUE_=166, LEAD_=167, NTH_VALUE_=168, 
		NTILE_=169, PERCENT_RANK_=170, RANK_=171, ROW_NUMBER_=172, GENERATED_=173, 
		ALWAYS_=174, STORED_=175, TRUE_=176, FALSE_=177, WINDOW_=178, NULLS_=179, 
		FIRST_=180, LAST_=181, FILTER_=182, GROUPS_=183, EXCLUDE_=184, TIES_=185, 
		OTHERS_=186, DO_=187, NOTHING_=188, IDENTIFIER=189, NUMERIC_LITERAL=190, 
		BIND_PARAMETER=191, STRING_LITERAL=192, BLOB_LITERAL=193, SINGLE_LINE_COMMENT=194, 
		MULTILINE_COMMENT=195, SPACES=196, UNEXPECTED_CHAR=197;
	public static final int
		RULE_start = 0, RULE_select_stmt = 1, RULE_create_table_stmt = 2, RULE_insert_stmt = 3, 
		RULE_update_stmt = 4, RULE_create_index_stmt = 5, RULE_column_def = 6, 
		RULE_column_constraint = 7, RULE_select_core = 8, RULE_delete_stmt = 9, 
		RULE_col_type = 10, RULE_result_column = 11, RULE_table = 12, RULE_expr = 13, 
		RULE_update_cond = 14, RULE_limited_expr = 15, RULE_table_name = 16, RULE_column_name = 17, 
		RULE_any_name = 18, RULE_literals = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "select_stmt", "create_table_stmt", "insert_stmt", "update_stmt", 
			"create_index_stmt", "column_def", "column_constraint", "select_core", 
			"delete_stmt", "col_type", "result_column", "table", "expr", "update_cond", 
			"limited_expr", "table_name", "column_name", "any_name", "literals"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'.'", "'('", "')'", "','", "'='", "'*'", "'+'", "'-'", 
			"'~'", "'||'", "'/'", "'%'", "'<<'", "'>>'", "'&'", "'|'", "'<'", "'<='", 
			"'>'", "'>='", "'=='", "'!='", "'<>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SCOL", "DOT", "OPEN_PAR", "CLOSE_PAR", "COMMA", "ASSIGN", "STAR", 
			"PLUS", "MINUS", "TILDE", "PIPE2", "DIV", "MOD", "LT2", "GT2", "AMP", 
			"PIPE", "LT", "LT_EQ", "GT", "GT_EQ", "EQ", "NOT_EQ1", "NOT_EQ2", "ABORT_", 
			"ACTION_", "ADD_", "AFTER_", "ALL_", "ALTER_", "ANALYZE_", "AND_", "XOR_", 
			"AS_", "ASC_", "ATTACH_", "AUTOINCREMENT_", "BEFORE_", "BEGIN_", "BETWEEN_", 
			"BY_", "CASCADE_", "CASE_", "CAST_", "CHECK_", "COLLATE_", "COLUMN_", 
			"INT_", "DECIMAL_", "VARCHAR_", "DATE_", "COMMIT_", "CONFLICT_", "CONSTRAINT_", 
			"CREATE_", "CROSS_", "CURRENT_DATE_", "CURRENT_TIME_", "CURRENT_TIMESTAMP_", 
			"DATABASE_", "DEFAULT_", "DEFERRABLE_", "DEFERRED_", "DELETE_", "DESC_", 
			"DETACH_", "DISTINCT_", "DROP_", "EACH_", "ELSE_", "END_", "ESCAPE_", 
			"EXCEPT_", "EXCLUSIVE_", "EXISTS_", "EXPLAIN_", "FAIL_", "FOR_", "FOREIGN_", 
			"FROM_", "FULL_", "GLOB_", "GROUP_", "HAVING_", "IF_", "IGNORE_", "IMMEDIATE_", 
			"IN_", "INDEX_", "INDEXED_", "INITIALLY_", "INNER_", "INSERT_", "INSTEAD_", 
			"INTERSECT_", "INTO_", "IS_", "ISNULL_", "JOIN_", "KEY_", "LEFT_", "LIKE_", 
			"LIMIT_", "MATCH_", "NATURAL_", "NO_", "NOT_", "NOTNULL_", "NULL_", "OF_", 
			"OFFSET_", "ON_", "OR_", "ORDER_", "OUTER_", "PLAN_", "PRAGMA_", "PRIMARY_", 
			"QUERY_", "RAISE_", "RECURSIVE_", "REFERENCES_", "REGEXP_", "REINDEX_", 
			"RELEASE_", "RENAME_", "REPLACE_", "RESTRICT_", "RIGHT_", "ROLLBACK_", 
			"ROW_", "ROWS_", "SAVEPOINT_", "SELECT_", "SET_", "TABLE_", "TEMP_", 
			"TEMPORARY_", "THEN_", "TO_", "TRANSACTION_", "TRIGGER_", "UNION_", "UNIQUE_", 
			"UPDATE_", "USING_", "VACUUM_", "VALUES_", "VIEW_", "VIRTUAL_", "WHEN_", 
			"WHERE_", "WITH_", "WITHOUT_", "FIRST_VALUE_", "OVER_", "PARTITION_", 
			"RANGE_", "PRECEDING_", "UNBOUNDED_", "CURRENT_", "FOLLOWING_", "CUME_DIST_", 
			"DENSE_RANK_", "LAG_", "LAST_VALUE_", "LEAD_", "NTH_VALUE_", "NTILE_", 
			"PERCENT_RANK_", "RANK_", "ROW_NUMBER_", "GENERATED_", "ALWAYS_", "STORED_", 
			"TRUE_", "FALSE_", "WINDOW_", "NULLS_", "FIRST_", "LAST_", "FILTER_", 
			"GROUPS_", "EXCLUDE_", "TIES_", "OTHERS_", "DO_", "NOTHING_", "IDENTIFIER", 
			"NUMERIC_LITERAL", "BIND_PARAMETER", "STRING_LITERAL", "BLOB_LITERAL", 
			"SINGLE_LINE_COMMENT", "MULTILINE_COMMENT", "SPACES", "UNEXPECTED_CHAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MiniSQLParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniSQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MiniSQLParser.EOF, 0); }
		public Select_stmtContext select_stmt() {
			return getRuleContext(Select_stmtContext.class,0);
		}
		public Create_table_stmtContext create_table_stmt() {
			return getRuleContext(Create_table_stmtContext.class,0);
		}
		public Insert_stmtContext insert_stmt() {
			return getRuleContext(Insert_stmtContext.class,0);
		}
		public Delete_stmtContext delete_stmt() {
			return getRuleContext(Delete_stmtContext.class,0);
		}
		public Update_stmtContext update_stmt() {
			return getRuleContext(Update_stmtContext.class,0);
		}
		public Create_index_stmtContext create_index_stmt() {
			return getRuleContext(Create_index_stmtContext.class,0);
		}
		public TerminalNode SCOL() { return getToken(MiniSQLParser.SCOL, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(40);
				select_stmt();
				}
				break;
			case 2:
				{
				setState(41);
				create_table_stmt();
				}
				break;
			case 3:
				{
				setState(42);
				insert_stmt();
				}
				break;
			case 4:
				{
				setState(43);
				delete_stmt();
				}
				break;
			case 5:
				{
				setState(44);
				update_stmt();
				}
				break;
			case 6:
				{
				setState(45);
				create_index_stmt();
				}
				break;
			}
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SCOL) {
				{
				setState(48);
				match(SCOL);
				}
			}

			setState(51);
			match(EOF);
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

	public static class Select_stmtContext extends ParserRuleContext {
		public Select_coreContext select_core() {
			return getRuleContext(Select_coreContext.class,0);
		}
		public Select_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterSelect_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitSelect_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitSelect_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_stmtContext select_stmt() throws RecognitionException {
		Select_stmtContext _localctx = new Select_stmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_select_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			select_core();
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

	public static class Create_table_stmtContext extends ParserRuleContext {
		public TerminalNode CREATE_() { return getToken(MiniSQLParser.CREATE_, 0); }
		public TerminalNode TABLE_() { return getToken(MiniSQLParser.TABLE_, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode OPEN_PAR() { return getToken(MiniSQLParser.OPEN_PAR, 0); }
		public TerminalNode CLOSE_PAR() { return getToken(MiniSQLParser.CLOSE_PAR, 0); }
		public List<Column_defContext> column_def() {
			return getRuleContexts(Column_defContext.class);
		}
		public Column_defContext column_def(int i) {
			return getRuleContext(Column_defContext.class,i);
		}
		public Column_constraintContext column_constraint() {
			return getRuleContext(Column_constraintContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(MiniSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MiniSQLParser.COMMA, i);
		}
		public Create_table_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_table_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterCreate_table_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitCreate_table_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitCreate_table_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_table_stmtContext create_table_stmt() throws RecognitionException {
		Create_table_stmtContext _localctx = new Create_table_stmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_create_table_stmt);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(CREATE_);
			setState(56);
			match(TABLE_);
			setState(57);
			table_name();
			{
			setState(58);
			match(OPEN_PAR);
			setState(64);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(59);
					column_def();
					setState(60);
					match(COMMA);
					}
					} 
				}
				setState(66);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			{
			setState(67);
			column_def();
			setState(68);
			column_constraint();
			}
			setState(74);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(70);
					match(COMMA);
					setState(71);
					column_def();
					}
					} 
				}
				setState(76);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(77);
			match(CLOSE_PAR);
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

	public static class Insert_stmtContext extends ParserRuleContext {
		public TerminalNode INTO_() { return getToken(MiniSQLParser.INTO_, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode INSERT_() { return getToken(MiniSQLParser.INSERT_, 0); }
		public List<TerminalNode> OPEN_PAR() { return getTokens(MiniSQLParser.OPEN_PAR); }
		public TerminalNode OPEN_PAR(int i) {
			return getToken(MiniSQLParser.OPEN_PAR, i);
		}
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public List<TerminalNode> CLOSE_PAR() { return getTokens(MiniSQLParser.CLOSE_PAR); }
		public TerminalNode CLOSE_PAR(int i) {
			return getToken(MiniSQLParser.CLOSE_PAR, i);
		}
		public TerminalNode VALUES_() { return getToken(MiniSQLParser.VALUES_, 0); }
		public List<LiteralsContext> literals() {
			return getRuleContexts(LiteralsContext.class);
		}
		public LiteralsContext literals(int i) {
			return getRuleContext(LiteralsContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MiniSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MiniSQLParser.COMMA, i);
		}
		public Insert_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insert_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterInsert_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitInsert_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitInsert_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Insert_stmtContext insert_stmt() throws RecognitionException {
		Insert_stmtContext _localctx = new Insert_stmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_insert_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(79);
			match(INSERT_);
			}
			setState(80);
			match(INTO_);
			setState(81);
			table_name();
			{
			setState(82);
			match(OPEN_PAR);
			setState(83);
			column_name();
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(84);
				match(COMMA);
				setState(85);
				column_name();
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(91);
			match(CLOSE_PAR);
			}
			{
			{
			setState(93);
			match(VALUES_);
			setState(94);
			match(OPEN_PAR);
			setState(95);
			literals();
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(96);
				match(COMMA);
				setState(97);
				literals();
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(103);
			match(CLOSE_PAR);
			}
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

	public static class Update_stmtContext extends ParserRuleContext {
		public TerminalNode UPDATE_() { return getToken(MiniSQLParser.UPDATE_, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode SET_() { return getToken(MiniSQLParser.SET_, 0); }
		public List<TerminalNode> ASSIGN() { return getTokens(MiniSQLParser.ASSIGN); }
		public TerminalNode ASSIGN(int i) {
			return getToken(MiniSQLParser.ASSIGN, i);
		}
		public List<LiteralsContext> literals() {
			return getRuleContexts(LiteralsContext.class);
		}
		public LiteralsContext literals(int i) {
			return getRuleContext(LiteralsContext.class,i);
		}
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public TerminalNode WHERE_() { return getToken(MiniSQLParser.WHERE_, 0); }
		public Update_condContext update_cond() {
			return getRuleContext(Update_condContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(MiniSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MiniSQLParser.COMMA, i);
		}
		public Update_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterUpdate_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitUpdate_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitUpdate_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Update_stmtContext update_stmt() throws RecognitionException {
		Update_stmtContext _localctx = new Update_stmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_update_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(UPDATE_);
			setState(106);
			table_name();
			setState(107);
			match(SET_);
			{
			setState(108);
			column_name();
			}
			setState(109);
			match(ASSIGN);
			setState(110);
			literals();
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(111);
				match(COMMA);
				{
				setState(112);
				column_name();
				}
				setState(113);
				match(ASSIGN);
				setState(114);
				literals();
				}
				}
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			{
			setState(121);
			match(WHERE_);
			setState(122);
			update_cond();
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

	public static class Create_index_stmtContext extends ParserRuleContext {
		public TerminalNode CREATE_() { return getToken(MiniSQLParser.CREATE_, 0); }
		public TerminalNode INDEX_() { return getToken(MiniSQLParser.INDEX_, 0); }
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public TerminalNode ON_() { return getToken(MiniSQLParser.ON_, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode OPEN_PAR() { return getToken(MiniSQLParser.OPEN_PAR, 0); }
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public TerminalNode CLOSE_PAR() { return getToken(MiniSQLParser.CLOSE_PAR, 0); }
		public List<TerminalNode> COMMA() { return getTokens(MiniSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MiniSQLParser.COMMA, i);
		}
		public Create_index_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_index_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterCreate_index_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitCreate_index_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitCreate_index_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_index_stmtContext create_index_stmt() throws RecognitionException {
		Create_index_stmtContext _localctx = new Create_index_stmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_create_index_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(CREATE_);
			setState(125);
			match(INDEX_);
			setState(126);
			any_name();
			setState(127);
			match(ON_);
			setState(128);
			table_name();
			setState(129);
			match(OPEN_PAR);
			setState(130);
			column_name();
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(131);
				match(COMMA);
				setState(132);
				column_name();
				}
				}
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(138);
			match(CLOSE_PAR);
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

	public static class Column_defContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Col_typeContext col_type() {
			return getRuleContext(Col_typeContext.class,0);
		}
		public Column_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterColumn_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitColumn_def(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitColumn_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_defContext column_def() throws RecognitionException {
		Column_defContext _localctx = new Column_defContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_column_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			column_name();
			setState(141);
			col_type();
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

	public static class Column_constraintContext extends ParserRuleContext {
		public TerminalNode PRIMARY_() { return getToken(MiniSQLParser.PRIMARY_, 0); }
		public TerminalNode KEY_() { return getToken(MiniSQLParser.KEY_, 0); }
		public Column_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterColumn_constraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitColumn_constraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitColumn_constraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_constraintContext column_constraint() throws RecognitionException {
		Column_constraintContext _localctx = new Column_constraintContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_column_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(143);
			match(PRIMARY_);
			setState(144);
			match(KEY_);
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

	public static class Select_coreContext extends ParserRuleContext {
		public TerminalNode SELECT_() { return getToken(MiniSQLParser.SELECT_, 0); }
		public TerminalNode STAR() { return getToken(MiniSQLParser.STAR, 0); }
		public TerminalNode FROM_() { return getToken(MiniSQLParser.FROM_, 0); }
		public TerminalNode WHERE_() { return getToken(MiniSQLParser.WHERE_, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public Select_coreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_core; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterSelect_core(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitSelect_core(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitSelect_core(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_coreContext select_core() throws RecognitionException {
		Select_coreContext _localctx = new Select_coreContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_select_core);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(146);
			match(SELECT_);
			{
			setState(147);
			match(STAR);
			}
			{
			setState(148);
			match(FROM_);
			{
			setState(149);
			table();
			}
			}
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE_) {
				{
				setState(151);
				match(WHERE_);
				setState(152);
				expr(0);
				}
			}

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

	public static class Delete_stmtContext extends ParserRuleContext {
		public TerminalNode DELETE_() { return getToken(MiniSQLParser.DELETE_, 0); }
		public TerminalNode FROM_() { return getToken(MiniSQLParser.FROM_, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode WHERE_() { return getToken(MiniSQLParser.WHERE_, 0); }
		public Limited_exprContext limited_expr() {
			return getRuleContext(Limited_exprContext.class,0);
		}
		public Delete_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterDelete_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitDelete_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitDelete_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Delete_stmtContext delete_stmt() throws RecognitionException {
		Delete_stmtContext _localctx = new Delete_stmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_delete_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(DELETE_);
			setState(156);
			match(FROM_);
			setState(157);
			table_name();
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE_) {
				{
				setState(158);
				match(WHERE_);
				setState(159);
				limited_expr(0);
				}
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

	public static class Col_typeContext extends ParserRuleContext {
		public TerminalNode INT_() { return getToken(MiniSQLParser.INT_, 0); }
		public TerminalNode DECIMAL_() { return getToken(MiniSQLParser.DECIMAL_, 0); }
		public TerminalNode VARCHAR_() { return getToken(MiniSQLParser.VARCHAR_, 0); }
		public TerminalNode DATE_() { return getToken(MiniSQLParser.DATE_, 0); }
		public Col_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_col_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterCol_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitCol_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitCol_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Col_typeContext col_type() throws RecognitionException {
		Col_typeContext _localctx = new Col_typeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_col_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT_) | (1L << DECIMAL_) | (1L << VARCHAR_) | (1L << DATE_))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class Result_columnContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Result_columnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_result_column; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterResult_column(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitResult_column(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitResult_column(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Result_columnContext result_column() throws RecognitionException {
		Result_columnContext _localctx = new Result_columnContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_result_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			column_name();
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

	public static class TableContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableContext table() throws RecognitionException {
		TableContext _localctx = new TableContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_table);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(166);
			table_name();
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

	public static class ExprContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public LiteralsContext literals() {
			return getRuleContext(LiteralsContext.class,0);
		}
		public TerminalNode LT() { return getToken(MiniSQLParser.LT, 0); }
		public TerminalNode LT_EQ() { return getToken(MiniSQLParser.LT_EQ, 0); }
		public TerminalNode GT() { return getToken(MiniSQLParser.GT, 0); }
		public TerminalNode GT_EQ() { return getToken(MiniSQLParser.GT_EQ, 0); }
		public TerminalNode ASSIGN() { return getToken(MiniSQLParser.ASSIGN, 0); }
		public TerminalNode NOT_EQ1() { return getToken(MiniSQLParser.NOT_EQ1, 0); }
		public TerminalNode NOT_EQ2() { return getToken(MiniSQLParser.NOT_EQ2, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode AND_() { return getToken(MiniSQLParser.AND_, 0); }
		public TerminalNode OR_() { return getToken(MiniSQLParser.OR_, 0); }
		public TerminalNode XOR_() { return getToken(MiniSQLParser.XOR_, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(169);
				column_name();
				setState(170);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT) | (1L << LT_EQ) | (1L << GT) | (1L << GT_EQ))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(171);
				literals();
				}
				break;
			case 2:
				{
				setState(173);
				column_name();
				setState(174);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ASSIGN) | (1L << NOT_EQ1) | (1L << NOT_EQ2))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(175);
				literals();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(190);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(188);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(179);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(180);
						match(AND_);
						setState(181);
						expr(4);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(182);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(183);
						match(OR_);
						setState(184);
						expr(3);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(185);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(186);
						match(XOR_);
						setState(187);
						expr(2);
						}
						break;
					}
					} 
				}
				setState(192);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Update_condContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(MiniSQLParser.ASSIGN, 0); }
		public LiteralsContext literals() {
			return getRuleContext(LiteralsContext.class,0);
		}
		public Update_condContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update_cond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterUpdate_cond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitUpdate_cond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitUpdate_cond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Update_condContext update_cond() throws RecognitionException {
		Update_condContext _localctx = new Update_condContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_update_cond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			column_name();
			setState(194);
			match(ASSIGN);
			setState(195);
			literals();
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

	public static class Limited_exprContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public LiteralsContext literals() {
			return getRuleContext(LiteralsContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(MiniSQLParser.ASSIGN, 0); }
		public List<Limited_exprContext> limited_expr() {
			return getRuleContexts(Limited_exprContext.class);
		}
		public Limited_exprContext limited_expr(int i) {
			return getRuleContext(Limited_exprContext.class,i);
		}
		public TerminalNode AND_() { return getToken(MiniSQLParser.AND_, 0); }
		public Limited_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limited_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterLimited_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitLimited_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitLimited_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Limited_exprContext limited_expr() throws RecognitionException {
		return limited_expr(0);
	}

	private Limited_exprContext limited_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Limited_exprContext _localctx = new Limited_exprContext(_ctx, _parentState);
		Limited_exprContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_limited_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(198);
			column_name();
			{
			setState(199);
			match(ASSIGN);
			}
			setState(200);
			literals();
			}
			_ctx.stop = _input.LT(-1);
			setState(207);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Limited_exprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_limited_expr);
					setState(202);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(203);
					match(AND_);
					setState(204);
					limited_expr(2);
					}
					} 
				}
				setState(209);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Table_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Table_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterTable_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitTable_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitTable_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_nameContext table_name() throws RecognitionException {
		Table_nameContext _localctx = new Table_nameContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			any_name();
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

	public static class Column_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Column_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterColumn_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitColumn_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitColumn_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_nameContext column_name() throws RecognitionException {
		Column_nameContext _localctx = new Column_nameContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			any_name();
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

	public static class Any_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MiniSQLParser.IDENTIFIER, 0); }
		public Any_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_any_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterAny_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitAny_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitAny_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Any_nameContext any_name() throws RecognitionException {
		Any_nameContext _localctx = new Any_nameContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_any_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			match(IDENTIFIER);
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

	public static class LiteralsContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(MiniSQLParser.STRING_LITERAL, 0); }
		public TerminalNode NUMERIC_LITERAL() { return getToken(MiniSQLParser.NUMERIC_LITERAL, 0); }
		public LiteralsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literals; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).enterLiterals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSQLParserListener ) ((MiniSQLParserListener)listener).exitLiterals(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSQLParserVisitor ) return ((MiniSQLParserVisitor<? extends T>)visitor).visitLiterals(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralsContext literals() throws RecognitionException {
		LiteralsContext _localctx = new LiteralsContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_literals);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			_la = _input.LA(1);
			if ( !(_la==NUMERIC_LITERAL || _la==STRING_LITERAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 13:
			return expr_sempred((ExprContext)_localctx, predIndex);
		case 15:
			return limited_expr_sempred((Limited_exprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean limited_expr_sempred(Limited_exprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u00c7\u00dd\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\2\5\2\61\n\2\3\2"+
		"\5\2\64\n\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4A\n\4\f\4\16"+
		"\4D\13\4\3\4\3\4\3\4\3\4\3\4\7\4K\n\4\f\4\16\4N\13\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\7\5Y\n\5\f\5\16\5\\\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\7\5e\n\5\f\5\16\5h\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\7\6w\n\6\f\6\16\6z\13\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\7\7\u0088\n\7\f\7\16\7\u008b\13\7\3\7\3\7\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u009c\n\n\3\13\3\13\3\13\3\13\3"+
		"\13\5\13\u00a3\n\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\5\17\u00b4\n\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\7\17\u00bf\n\17\f\17\16\17\u00c2\13\17\3\20\3\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u00d0\n\21\f\21\16\21\u00d3"+
		"\13\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\4BL\4\34 \26\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\6\3\2\62\65\3\2\24\27\4\2\b"+
		"\b\31\32\4\2\u00c0\u00c0\u00c2\u00c2\2\u00dc\2\60\3\2\2\2\4\67\3\2\2\2"+
		"\69\3\2\2\2\bQ\3\2\2\2\nk\3\2\2\2\f~\3\2\2\2\16\u008e\3\2\2\2\20\u0091"+
		"\3\2\2\2\22\u0094\3\2\2\2\24\u009d\3\2\2\2\26\u00a4\3\2\2\2\30\u00a6\3"+
		"\2\2\2\32\u00a8\3\2\2\2\34\u00b3\3\2\2\2\36\u00c3\3\2\2\2 \u00c7\3\2\2"+
		"\2\"\u00d4\3\2\2\2$\u00d6\3\2\2\2&\u00d8\3\2\2\2(\u00da\3\2\2\2*\61\5"+
		"\4\3\2+\61\5\6\4\2,\61\5\b\5\2-\61\5\24\13\2.\61\5\n\6\2/\61\5\f\7\2\60"+
		"*\3\2\2\2\60+\3\2\2\2\60,\3\2\2\2\60-\3\2\2\2\60.\3\2\2\2\60/\3\2\2\2"+
		"\60\61\3\2\2\2\61\63\3\2\2\2\62\64\7\3\2\2\63\62\3\2\2\2\63\64\3\2\2\2"+
		"\64\65\3\2\2\2\65\66\7\2\2\3\66\3\3\2\2\2\678\5\22\n\28\5\3\2\2\29:\7"+
		"9\2\2:;\7\u008a\2\2;<\5\"\22\2<B\7\5\2\2=>\5\16\b\2>?\7\7\2\2?A\3\2\2"+
		"\2@=\3\2\2\2AD\3\2\2\2BC\3\2\2\2B@\3\2\2\2CE\3\2\2\2DB\3\2\2\2EF\5\16"+
		"\b\2FG\5\20\t\2GL\3\2\2\2HI\7\7\2\2IK\5\16\b\2JH\3\2\2\2KN\3\2\2\2LM\3"+
		"\2\2\2LJ\3\2\2\2MO\3\2\2\2NL\3\2\2\2OP\7\6\2\2P\7\3\2\2\2QR\7_\2\2RS\7"+
		"b\2\2ST\5\"\22\2TU\7\5\2\2UZ\5$\23\2VW\7\7\2\2WY\5$\23\2XV\3\2\2\2Y\\"+
		"\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[]\3\2\2\2\\Z\3\2\2\2]^\7\6\2\2^_\3\2\2\2"+
		"_`\7\u0096\2\2`a\7\5\2\2af\5(\25\2bc\7\7\2\2ce\5(\25\2db\3\2\2\2eh\3\2"+
		"\2\2fd\3\2\2\2fg\3\2\2\2gi\3\2\2\2hf\3\2\2\2ij\7\6\2\2j\t\3\2\2\2kl\7"+
		"\u0093\2\2lm\5\"\22\2mn\7\u0089\2\2no\5$\23\2op\7\b\2\2px\5(\25\2qr\7"+
		"\7\2\2rs\5$\23\2st\7\b\2\2tu\5(\25\2uw\3\2\2\2vq\3\2\2\2wz\3\2\2\2xv\3"+
		"\2\2\2xy\3\2\2\2y{\3\2\2\2zx\3\2\2\2{|\7\u009a\2\2|}\5\36\20\2}\13\3\2"+
		"\2\2~\177\79\2\2\177\u0080\7[\2\2\u0080\u0081\5&\24\2\u0081\u0082\7r\2"+
		"\2\u0082\u0083\5\"\22\2\u0083\u0084\7\5\2\2\u0084\u0089\5$\23\2\u0085"+
		"\u0086\7\7\2\2\u0086\u0088\5$\23\2\u0087\u0085\3\2\2\2\u0088\u008b\3\2"+
		"\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008c\3\2\2\2\u008b"+
		"\u0089\3\2\2\2\u008c\u008d\7\6\2\2\u008d\r\3\2\2\2\u008e\u008f\5$\23\2"+
		"\u008f\u0090\5\26\f\2\u0090\17\3\2\2\2\u0091\u0092\7x\2\2\u0092\u0093"+
		"\7f\2\2\u0093\21\3\2\2\2\u0094\u0095\7\u0088\2\2\u0095\u0096\7\t\2\2\u0096"+
		"\u0097\7R\2\2\u0097\u0098\5\32\16\2\u0098\u009b\3\2\2\2\u0099\u009a\7"+
		"\u009a\2\2\u009a\u009c\5\34\17\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2"+
		"\2\u009c\23\3\2\2\2\u009d\u009e\7B\2\2\u009e\u009f\7R\2\2\u009f\u00a2"+
		"\5\"\22\2\u00a0\u00a1\7\u009a\2\2\u00a1\u00a3\5 \21\2\u00a2\u00a0\3\2"+
		"\2\2\u00a2\u00a3\3\2\2\2\u00a3\25\3\2\2\2\u00a4\u00a5\t\2\2\2\u00a5\27"+
		"\3\2\2\2\u00a6\u00a7\5$\23\2\u00a7\31\3\2\2\2\u00a8\u00a9\5\"\22\2\u00a9"+
		"\33\3\2\2\2\u00aa\u00ab\b\17\1\2\u00ab\u00ac\5$\23\2\u00ac\u00ad\t\3\2"+
		"\2\u00ad\u00ae\5(\25\2\u00ae\u00b4\3\2\2\2\u00af\u00b0\5$\23\2\u00b0\u00b1"+
		"\t\4\2\2\u00b1\u00b2\5(\25\2\u00b2\u00b4\3\2\2\2\u00b3\u00aa\3\2\2\2\u00b3"+
		"\u00af\3\2\2\2\u00b4\u00c0\3\2\2\2\u00b5\u00b6\f\5\2\2\u00b6\u00b7\7\""+
		"\2\2\u00b7\u00bf\5\34\17\6\u00b8\u00b9\f\4\2\2\u00b9\u00ba\7s\2\2\u00ba"+
		"\u00bf\5\34\17\5\u00bb\u00bc\f\3\2\2\u00bc\u00bd\7#\2\2\u00bd\u00bf\5"+
		"\34\17\4\u00be\u00b5\3\2\2\2\u00be\u00b8\3\2\2\2\u00be\u00bb\3\2\2\2\u00bf"+
		"\u00c2\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\35\3\2\2"+
		"\2\u00c2\u00c0\3\2\2\2\u00c3\u00c4\5$\23\2\u00c4\u00c5\7\b\2\2\u00c5\u00c6"+
		"\5(\25\2\u00c6\37\3\2\2\2\u00c7\u00c8\b\21\1\2\u00c8\u00c9\5$\23\2\u00c9"+
		"\u00ca\7\b\2\2\u00ca\u00cb\5(\25\2\u00cb\u00d1\3\2\2\2\u00cc\u00cd\f\3"+
		"\2\2\u00cd\u00ce\7\"\2\2\u00ce\u00d0\5 \21\4\u00cf\u00cc\3\2\2\2\u00d0"+
		"\u00d3\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2!\3\2\2\2"+
		"\u00d3\u00d1\3\2\2\2\u00d4\u00d5\5&\24\2\u00d5#\3\2\2\2\u00d6\u00d7\5"+
		"&\24\2\u00d7%\3\2\2\2\u00d8\u00d9\7\u00bf\2\2\u00d9\'\3\2\2\2\u00da\u00db"+
		"\t\5\2\2\u00db)\3\2\2\2\20\60\63BLZfx\u0089\u009b\u00a2\u00b3\u00be\u00c0"+
		"\u00d1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}