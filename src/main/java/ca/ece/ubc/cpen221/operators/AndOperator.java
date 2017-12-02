package ca.ece.ubc.cpen221.operators;
import ca.ece.ubc.cpen221.booleanexpressions.*;

public class AndOperator implements BinaryOperator {

    @Override
    public boolean apply(Expression arg1, Expression arg2) {
        // TODO Auto-generated method stub
        return arg1.eval() && arg2.eval();
    }
    
}
