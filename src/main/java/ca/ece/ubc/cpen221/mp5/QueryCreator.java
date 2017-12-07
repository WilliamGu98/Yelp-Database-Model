package ca.ece.ubc.cpen221.mp5;

import java.util.*;

import org.antlr.v4.runtime.tree.ErrorNode;

import org.antlr.v4.runtime.tree.TerminalNode;

import ca.ece.ubc.cpen221.parser.*;
import ca.ece.ubc.cpen221.parser.QueryParser.*;

import ca.ece.ubc.cpen221.booleanexpressions.*;
import ca.ece.ubc.cpen221.operators.*;

public class QueryCreator extends QueryBaseListener {

    private RestaurantHandle rH; // Allows us to switch between restaurants to be tested by the booleanExp.

    // This stack helps us construct the boolean tree expression. Every time we exit
    // a terminal boolean expression,
    // it is pushed to this stack. Whenever a binary (OR/AND) expression is exited,
    // we pop the stack and form
    // a single OR/AND expression depending on the number of operators specified,
    // and repush it onto the stack. At the
    // end of the walkthrough, there will be a final expression on the stack that
    // represents the final boolean tree.
    private Stack<Expression> expressionStack = new Stack<Expression>();

    private Expression booleanExp;

    /**
     * Get the booleanExp tree for given the search query that this walker walks
     * through
     * 
     * @return the boolean expression tree
     */
    public Expression getExpression() {
        return this.booleanExp;
    }

    /**
     * Get the restaurant handle for the given search query that this walker walks
     * though
     * 
     * @return the restaurant handle
     */
    public RestaurantHandle getHandle() {
        return this.rH;
    }

    /**
     * When the root expression context is entered, the restaurant handle is
     * initialized. This handle supports methods that allow different restaurants to
     * be set, allowing the booleanExp to be able to quickly re-evaluate different
     * restaurants.
     */
    public void enterRoot(RootContext ctx) {
        this.rH = new RestaurantHandle();
    }

    /**
     * When the root expression context is exited, the final expression left on the
     * stack is popped and represents the final booleanExp tree that we can use to
     * evaluate if a given restaurant matches a query string.
     */
    @Override
    public void exitRoot(RootContext ctx) {
        this.booleanExp = expressionStack.pop();
    }

    /**
     * When an and expression context is exited, an AND expression tree is formed by
     * popping a certain number of expressions from the stack depending on how many
     * terms the context specifies. The resulting AND expression is pushed back onto
     * the stack.
     */
    @Override
    public void exitAndExpr(AndExprContext ctx) {
        List<TerminalNode> tokens = ctx.AND();
        int numExpressions = tokens.size();

        AndOperator op = new AndOperator();
        BinaryExpression exp;

        if (numExpressions > 0) {
            exp = new BinaryExpression(expressionStack.pop(), op, expressionStack.pop());

            for (int i = 1; i < numExpressions; i++) {
                exp = new BinaryExpression(exp, op, expressionStack.pop());
            }
            expressionStack.push(exp);
        }
    }

    /**
     * When an or expression context is exited, an OR expression tree is formed by
     * popping a certain number of expressions from the stack depending on how many
     * terms the context specifies. The resulting OR expression is pushed back onto
     * the stack.
     */
    @Override
    public void exitOrExpr(OrExprContext ctx) {
        List<TerminalNode> tokens = ctx.OR();
        int numExpressions = tokens.size();

        OrOperator op = new OrOperator();
        BinaryExpression exp;

        if (numExpressions > 0) {
            exp = new BinaryExpression(expressionStack.pop(), op, expressionStack.pop());

            for (int i = 1; i < numExpressions; i++) {
                exp = new BinaryExpression(exp, op, expressionStack.pop());
            }
            expressionStack.push(exp);
        }
    }

    /**
     * When a category expression context is exited, a corresponding category
     * expression is formed and pushed onto the expression stack
     */
    @Override
    public void exitCategoryExpr(CategoryExprContext ctx) {
        TerminalNode token = ctx.STRING();
        String category = token.getText();

        CategoryExpression exp = new CategoryExpression(rH, category);
        expressionStack.push(exp);
    }

    /**
     * When an in expression context is exited, a corresponding in expression is
     * formed and pushed onto the expression stack
     */
    @Override
    public void exitInExpr(InExprContext ctx) {
        TerminalNode token = ctx.STRING();
        String location = token.getText();

        InExpression exp = new InExpression(rH, location);
        expressionStack.push(exp);
    }

    /**
     * When a name expression context is exited, a corresponding name expression is
     * formed and pushed onto the expression stack
     */
    @Override
    public void exitNameExpr(NameExprContext ctx) {
        TerminalNode token = ctx.STRING();
        String name = token.getText();

        NameExpression exp = new NameExpression(rH, name);
        expressionStack.push(exp);
    }

    /**
     * When a rating expression context is exited, a corresponding rating expression
     * is formed and pushed onto the expression stack
     */
    @Override
    public void exitRatingExpr(RatingExprContext ctx) {

        TerminalNode numToken = ctx.NUM();
        TerminalNode compToken = ctx.COMP();
        String numText = numToken.getText();
        String compText = compToken.getText();
        double rating = Integer.parseInt(numText);

        RatingExpression exp = new RatingExpression(rH, rating, compText);
        expressionStack.push(exp);
    }

    /**
     * When a price expression context is exited, a corresponding price expression
     * is formed and pushed onto the expression stack
     */
    @Override
    public void exitPriceExpr(PriceExprContext ctx) {

        TerminalNode numToken = ctx.NUM();
        TerminalNode compToken = ctx.COMP();
        String numText = numToken.getText();
        String compText = compToken.getText();
        double price = Integer.parseInt(numText);

        PriceExpression exp = new PriceExpression(rH, price, compText);
        expressionStack.push(exp);
    }

    /**
     * Throws an exception if the given query has an error
     */
    @Override
    public void visitErrorNode(ErrorNode node) {
        throw new IllegalArgumentException();
    }
}
