package ca.ece.ubc.cpen221.mp5;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import ca.ece.ubc.cpen221.parser.*;
import ca.ece.ubc.cpen221.parser.QueryParser.CategoryContext;
import ca.ece.ubc.cpen221.parser.QueryParser.InContext;
import ca.ece.ubc.cpen221.parser.QueryParser.NameContext;
import ca.ece.ubc.cpen221.parser.QueryParser.PriceContext;
import ca.ece.ubc.cpen221.parser.QueryParser.RatingContext;

public class QueryCreator extends QueryBaseListener{

    @Override
    public void exitCategory(CategoryContext ctx) {
        TerminalNode token = ctx.STRING();
        String text = token.getText();
        
        //System.err.println(text);
    }
    
    @Override
    public void exitIn (InContext ctx) {
        TerminalNode token = ctx.STRING();
        String text = token.getText();
        
        //System.err.println(text);
    }
    
    @Override
    public void exitName (NameContext ctx) {
        TerminalNode token = ctx.STRING();
        String text = token.getText();
        
        //System.err.println(text);
    }
    
    @Override
    public void exitRating (RatingContext ctx) {
        TerminalNode token = ctx.NUM();
        String text = token.getText();
        
        //System.err.println(text);
    }
    
    @Override
    public void exitPrice (PriceContext ctx) {
        TerminalNode token = ctx.NUM();
        String text = token.getText();
        
        //System.err.println(text);
    }
    
    /**
     * Throws an exception if the given query has an error
     */
    @Override
    public void visitErrorNode(ErrorNode node) { 
        throw new IllegalArgumentException();
    }
}
