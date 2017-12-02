package ca.ece.ubc.cpen221.mp5;

import java.util.*;

import org.antlr.v4.runtime.tree.ErrorNode;

import org.antlr.v4.runtime.tree.TerminalNode;

import ca.ece.ubc.cpen221.parser.*;
import ca.ece.ubc.cpen221.parser.QueryParser.*;

import ca.ece.ubc.cpen221.booleanexpressions.*;
import ca.ece.ubc.cpen221.operators.*;

public class QueryCreator extends QueryBaseListener {

    private RestaurantHandle r; // Eventually need to return this so we have something to keep changing (think
    // of it as a handle)

    private Stack<Expression> expressionStack = new Stack<Expression>(); // Whenever we make a terminal expression put
                                                                         // it on the stack
    // Whenever we exit an AND/OR expression, make a chain of expressions depending
    // on the number of expressions at that level
    // Then push the resultant expression back on the stack
    // At the end, there should be a single expression on the stack which represents
    // the final expression we can use

    private Expression booleanExp;

    public boolean evaluateExpression() {
        return booleanExp.eval();
    }

    public void setRestaurantHandle(RestaurantHandle r) {
        this.r = r;
    }

    @Override
    public void exitRoot(RootContext ctx) {
        this.booleanExp = expressionStack.pop();
    }

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

    @Override
    public void exitCategoryExpr(CategoryExprContext ctx) {
        TerminalNode token = ctx.STRING();
        String category = token.getText();

        CategoryExpression exp = new CategoryExpression(r, category);
        expressionStack.push(exp);
    }

    @Override
    public void exitInExpr(InExprContext ctx) {
        TerminalNode token = ctx.STRING();
        String location = token.getText();

        InExpression exp = new InExpression(r, location);
        expressionStack.push(exp);
    }

    @Override
    public void exitNameExpr(NameExprContext ctx) {
        TerminalNode token = ctx.STRING();
        String name = token.getText();

        NameExpression exp = new NameExpression(r, name);
        expressionStack.push(exp);
    }

    @Override
    public void exitRatingExpr(RatingExprContext ctx) {

        TerminalNode numToken = ctx.NUM();
        TerminalNode compToken = ctx.COMP();
        String numText = numToken.getText();
        String compText = compToken.getText();
        double rating = Integer.parseInt(numText);

        RatingExpression exp = new RatingExpression(r, rating, compText);
        expressionStack.push(exp);
    }

    @Override
    public void exitPriceExpr(PriceExprContext ctx) {

        TerminalNode numToken = ctx.NUM();
        TerminalNode compToken = ctx.COMP();
        String numText = numToken.getText();
        String compText = compToken.getText();
        double price = Integer.parseInt(numText);

        PriceExpression exp = new PriceExpression(r, price, compText);
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
