package ca.ece.ubc.cpen221.operators;

import ca.ece.ubc.cpen221.booleanexpressions.Expression;

public class OrOperator implements BinaryOperator {

    /**
     * Evaluates the OR output of two boolean expressions
     * 
     * @param arg1
     *      first boolean expression
     * @param arg2
     *      second boolean expression
     * @return the boolean OR value of the two expressions
     */
    @Override
    public boolean apply(Expression arg1, Expression arg2) {
        return arg1.eval() || arg2.eval();
    }

}
