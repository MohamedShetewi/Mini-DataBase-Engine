// Generated from /Users/mohammad.abubeih/Desktop/Others/Grammars/sqlite/MiniSQLParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MiniSQLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MiniSQLParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(MiniSQLParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#select_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_stmt(MiniSQLParser.Select_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#create_table_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_table_stmt(MiniSQLParser.Create_table_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#insert_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert_stmt(MiniSQLParser.Insert_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#update_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate_stmt(MiniSQLParser.Update_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#create_index_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_index_stmt(MiniSQLParser.Create_index_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#column_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_def(MiniSQLParser.Column_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#column_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_constraint(MiniSQLParser.Column_constraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#select_core}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_core(MiniSQLParser.Select_coreContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#delete_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete_stmt(MiniSQLParser.Delete_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#col_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCol_type(MiniSQLParser.Col_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#result_column}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResult_column(MiniSQLParser.Result_columnContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#table}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable(MiniSQLParser.TableContext ctx);
	/**
	 * Visit a parse tree produced by .
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(MiniSQLParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#update_cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate_cond(MiniSQLParser.Update_condContext ctx);
	/**
	 * Visit a parse tree produced by .
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimited_expr(MiniSQLParser.Limited_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#table_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_name(MiniSQLParser.Table_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#column_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_name(MiniSQLParser.Column_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#any_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAny_name(MiniSQLParser.Any_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniSQLParser#literals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiterals(MiniSQLParser.LiteralsContext ctx);
}