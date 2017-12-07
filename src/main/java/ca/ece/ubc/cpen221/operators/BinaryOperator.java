package ca.ece.ubc.cpen221.operators;
import ca.ece.ubc.cpen221.booleanexpressions.*;

public interface BinaryOperator{
    
    /**
     * Evaluates the boolean output of two boolean expressions
     * 
     * @param arg1
     *      first boolean expression
     * @param arg2
     *      second boolean expression
     * @return the boolean value of the two expressions
     */
    boolean apply(Expression arg1, Expression arg2);

}
