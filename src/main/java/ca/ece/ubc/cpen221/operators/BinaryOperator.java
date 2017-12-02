package ca.ece.ubc.cpen221.operators;
import ca.ece.ubc.cpen221.booleanexpressions.*;

public interface BinaryOperator extends Operator{
    
    boolean apply(Expression arg1, Expression arg2);

}
