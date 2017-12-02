package ca.ece.ubc.cpen221.booleanexpressions;
import ca.ece.ubc.cpen221.operators.*;

public class BinaryExpression implements Expression{
    
    private BinaryOperator op;
    private Expression leftArg;
    private Expression rightArg;
    
    public BinaryExpression (Expression leftArg, BinaryOperator op, Expression rightArg) {
        this.op = op;
        this.leftArg = leftArg;
        this.rightArg = rightArg;
    }

    @Override
    public boolean eval() {
        return op.apply(leftArg, rightArg);
    }
    

}
