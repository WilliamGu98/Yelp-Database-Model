package ca.ece.ubc.cpen221.mp5;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import ca.ece.ubc.cpen221.parser.*;
import ca.ece.ubc.cpen221.parser.QueryParser.CategoryExprContext;
import ca.ece.ubc.cpen221.parser.QueryParser.InExprContext;
import ca.ece.ubc.cpen221.parser.QueryParser.NameExprContext;
import ca.ece.ubc.cpen221.parser.QueryParser.PriceExprContext;
import ca.ece.ubc.cpen221.parser.QueryParser.RatingExprContext;

public class QueryCreator extends QueryBaseListener{

    @Override
    public void exitCategoryExpr(CategoryExprContext ctx) {
        TerminalNode token = ctx.STRING();
        String text = token.getText();
        
        System.err.println("Category: " + text);
    }
    
    @Override
    public void exitInExpr (InExprContext ctx) {
        TerminalNode token = ctx.STRING();
        String text = token.getText();
        
        System.err.println("In: " + text);
    }
    
    @Override
    public void exitNameExpr (NameExprContext ctx) {
        TerminalNode token = ctx.STRING();
        String text = token.getText();
        
        System.err.println("Name: " + text);
    }
    
    @Override
    public void exitRatingExpr (RatingExprContext ctx) {
        /*
        TerminalNode numToken = ctx.NUM();
        TerminalNode ineqToken = ctx.INEQ();
        String numText = numToken.getText();
        String ineqText = ineqToken.getText();
        
        System.err.println("Rating: " + ineqText + numText);*/
    }
    
    @Override
    public void exitPriceExpr (PriceExprContext ctx) {
        /*
        TerminalNode numToken = ctx.NUM();
        TerminalNode ineqToken = ctx.INEQ();
        String numText = numToken.getText();
        String ineqText = ineqToken.getText();
        
        System.err.println("Price: " + ineqText + numText);*/
    }
    
    /**
     * Throws an exception if the given query has an error
     */
    @Override
    public void visitErrorNode(ErrorNode node) { 
        throw new IllegalArgumentException();
    }
}
