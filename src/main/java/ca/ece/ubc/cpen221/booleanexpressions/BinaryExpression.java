package ca.ece.ubc.cpen221.booleanexpressions;

import ca.ece.ubc.cpen221.operators.*;

public class BinaryExpression implements Expression {

    /**
     * AF: Represents a binary boolean expression with a specific joining operator
     * (OR/AND), a left boolean expression, and a right boolean expression
     */

    /** Rep Invariant **/
    private BinaryOperator op; // AND/OR operator
    private Expression leftArg;
    private Expression rightArg;

    public BinaryExpression(Expression leftArg, BinaryOperator op, Expression rightArg) {
        this.op = op;
        this.leftArg = leftArg;
        this.rightArg = rightArg;
    }

    @Override
    public boolean eval() {
        return op.apply(leftArg, rightArg);
    }

}
