// Generated from /Users/mohammad.abubeih/Desktop/Others/Grammars/sqlite/MiniSQLParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MiniSQLParser}.
 */
public interface MiniSQLParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(MiniSQLParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(MiniSQLParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#select_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSelect_stmt(MiniSQLParser.Select_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#select_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSelect_stmt(MiniSQLParser.Select_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#create_table_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCreate_table_stmt(MiniSQLParser.Create_table_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#create_table_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCreate_table_stmt(MiniSQLParser.Create_table_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#insert_stmt}.
	 * @param ctx the parse tree
	 */
	void enterInsert_stmt(MiniSQLParser.Insert_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#insert_stmt}.
	 * @param ctx the parse tree
	 */
	void exitInsert_stmt(MiniSQLParser.Insert_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#update_stmt}.
	 * @param ctx the parse tree
	 */
	void enterUpdate_stmt(MiniSQLParser.Update_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#update_stmt}.
	 * @param ctx the parse tree
	 */
	void exitUpdate_stmt(MiniSQLParser.Update_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#create_index_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCreate_index_stmt(MiniSQLParser.Create_index_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#create_index_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCreate_index_stmt(MiniSQLParser.Create_index_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#column_def}.
	 * @param ctx the parse tree
	 */
	void enterColumn_def(MiniSQLParser.Column_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#column_def}.
	 * @param ctx the parse tree
	 */
	void exitColumn_def(MiniSQLParser.Column_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#column_constraint}.
	 * @param ctx the parse tree
	 */
	void enterColumn_constraint(MiniSQLParser.Column_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#column_constraint}.
	 * @param ctx the parse tree
	 */
	void exitColumn_constraint(MiniSQLParser.Column_constraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#select_core}.
	 * @param ctx the parse tree
	 */
	void enterSelect_core(MiniSQLParser.Select_coreContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#select_core}.
	 * @param ctx the parse tree
	 */
	void exitSelect_core(MiniSQLParser.Select_coreContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#delete_stmt}.
	 * @param ctx the parse tree
	 */
	void enterDelete_stmt(MiniSQLParser.Delete_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#delete_stmt}.
	 * @param ctx the parse tree
	 */
	void exitDelete_stmt(MiniSQLParser.Delete_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#col_type}.
	 * @param ctx the parse tree
	 */
	void enterCol_type(MiniSQLParser.Col_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#col_type}.
	 * @param ctx the parse tree
	 */
	void exitCol_type(MiniSQLParser.Col_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#result_column}.
	 * @param ctx the parse tree
	 */
	void enterResult_column(MiniSQLParser.Result_columnContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#result_column}.
	 * @param ctx the parse tree
	 */
	void exitResult_column(MiniSQLParser.Result_columnContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#table}.
	 * @param ctx the parse tree
	 */
	void enterTable(MiniSQLParser.TableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#table}.
	 * @param ctx the parse tree
	 */
	void exitTable(MiniSQLParser.TableContext ctx);
	/**
	 * Enter a parse tree produced by .
	 * @param ctx the parse tree
	 */
	void enterExpr(MiniSQLParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by .
	 * @param ctx the parse tree
	 */
	void exitExpr(MiniSQLParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#update_cond}.
	 * @param ctx the parse tree
	 */
	void enterUpdate_cond(MiniSQLParser.Update_condContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#update_cond}.
	 * @param ctx the parse tree
	 */
	void exitUpdate_cond(MiniSQLParser.Update_condContext ctx);
	/**
	 * Enter a parse tree produced by .
	 * @param ctx the parse tree
	 */
	void enterLimited_expr(MiniSQLParser.Limited_exprContext ctx);
	/**
	 * Exit a parse tree produced by .
	 * @param ctx the parse tree
	 */
	void exitLimited_expr(MiniSQLParser.Limited_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#table_name}.
	 * @param ctx the parse tree
	 */
	void enterTable_name(MiniSQLParser.Table_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#table_name}.
	 * @param ctx the parse tree
	 */
	void exitTable_name(MiniSQLParser.Table_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#column_name}.
	 * @param ctx the parse tree
	 */
	void enterColumn_name(MiniSQLParser.Column_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#column_name}.
	 * @param ctx the parse tree
	 */
	void exitColumn_name(MiniSQLParser.Column_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#any_name}.
	 * @param ctx the parse tree
	 */
	void enterAny_name(MiniSQLParser.Any_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#any_name}.
	 * @param ctx the parse tree
	 */
	void exitAny_name(MiniSQLParser.Any_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSQLParser#literals}.
	 * @param ctx the parse tree
	 */
	void enterLiterals(MiniSQLParser.LiteralsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSQLParser#literals}.
	 * @param ctx the parse tree
	 */
	void exitLiterals(MiniSQLParser.LiteralsContext ctx);
}