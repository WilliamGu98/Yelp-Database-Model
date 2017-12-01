// Generated from Query.g4 by ANTLR 4.7

 	package ca.ece.ubc.cpen221.parser;
 
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QueryParser}.
 */
public interface QueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QueryParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(QueryParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(QueryParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(QueryParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(QueryParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(QueryParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(QueryParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#comparator}.
	 * @param ctx the parse tree
	 */
	void enterComparator(QueryParser.ComparatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#comparator}.
	 * @param ctx the parse tree
	 */
	void exitComparator(QueryParser.ComparatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(QueryParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(QueryParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#inExpr}.
	 * @param ctx the parse tree
	 */
	void enterInExpr(QueryParser.InExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#inExpr}.
	 * @param ctx the parse tree
	 */
	void exitInExpr(QueryParser.InExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#categoryExpr}.
	 * @param ctx the parse tree
	 */
	void enterCategoryExpr(QueryParser.CategoryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#categoryExpr}.
	 * @param ctx the parse tree
	 */
	void exitCategoryExpr(QueryParser.CategoryExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#ratingExpr}.
	 * @param ctx the parse tree
	 */
	void enterRatingExpr(QueryParser.RatingExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#ratingExpr}.
	 * @param ctx the parse tree
	 */
	void exitRatingExpr(QueryParser.RatingExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#priceExpr}.
	 * @param ctx the parse tree
	 */
	void enterPriceExpr(QueryParser.PriceExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#priceExpr}.
	 * @param ctx the parse tree
	 */
	void exitPriceExpr(QueryParser.PriceExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#nameExpr}.
	 * @param ctx the parse tree
	 */
	void enterNameExpr(QueryParser.NameExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#nameExpr}.
	 * @param ctx the parse tree
	 */
	void exitNameExpr(QueryParser.NameExprContext ctx);
}