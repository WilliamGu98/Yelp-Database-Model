package ca.ece.ubc.cpen221.booleanexpressions;
import ca.ece.ubc.cpen221.operators.*;

public class BinaryExpression implements Expression{
    
    private BinaryOperator op;
    private Expression leftArg;
    private Expression rightArg;

    @Override
    public boolean eval() {
        return op.apply(leftArg, rightArg);
    }
    

}
