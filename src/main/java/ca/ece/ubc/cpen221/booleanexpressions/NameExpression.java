package ca.ece.ubc.cpen221.booleanexpressions;

public class NameExpression implements Expression{
    
    /**
     * AF: Represents a terminal boolean expression that evaluates whether a
     * restaurant matches some specific name identifier
     */
    
    /** Rep Invariant **/
    private RestaurantHandle r; // Contains a valid restaurant object
    private String name;

    /**
     * Constructor for a name expression
     * @param r 
     *      restaurant handle that contains a restaurant
     * @param name
     *      a string that represents a restaurant name we wish to look for
     */
    public NameExpression (RestaurantHandle r, String name) {
        this.r = r;
        this.name = name;
    }

    /**
     * Evaluate the boolean expression
     * 
     * @return true if the restaurant in the handle has a name that contains the
     *         specified identifier (name)
     */
    @Override
    public boolean eval() {
        return r.getRestaurant().getName().toLowerCase().contains(name.toLowerCase());
    }

}
