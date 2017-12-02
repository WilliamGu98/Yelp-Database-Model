package ca.ece.ubc.cpen221.operators;

import ca.ece.ubc.cpen221.booleanexpressions.Expression;
import ca.ece.ubc.cpen221.mp5.Restaurant;

public interface ContainsOperator extends Operator{

    public boolean apply(Restaurant r, String arg);

}
