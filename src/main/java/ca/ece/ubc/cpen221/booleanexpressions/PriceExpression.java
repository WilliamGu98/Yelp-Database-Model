package ca.ece.ubc.cpen221.booleanexpressions;

public class PriceExpression implements Expression{
    
    /**
     * AF: Represents a terminal boolean expression that evaluates whether a
     * restaurant matches the conditions of a price identifier
     */
    
    /** Rep Invariant **/
    private RestaurantHandle r; // Contains a valid restaurant object
    private double price; 
    public String op; // Should be one of >, >=, =, <, <=

    /**
     * Constructor for a price expression
     * @param r 
     *      restaurant handle that contains a restaurant
     * @param op
     *      a string that represents the inequality operator to use in the price comparison
     * @param price
     *      a double that represents the price to compare the restaurant price with
     */
    public PriceExpression (RestaurantHandle r, double price, String op) {
        this.r = r;
        this.price = price;
        this.op = op;
    }

    /**
     * Evaluate the boolean expression
     * 
     * @return true if the price comparison evaluates to true, false if not
     */
    @Override
    public boolean eval() {
        if (op.equals("<")) {
            return r.getRestaurant().getPrice() < price;
        }
        else if (op.equals("<=")) {
            return r.getRestaurant().getPrice() <= price;
        }
        else if (op.equals(">")) {
            return r.getRestaurant().getPrice() > price;
        }
        else if (op.equals(">=")) {
            return r.getRestaurant().getPrice() >= price;
        }
        else {
            return r.getRestaurant().getPrice() == price;
        }
    }
    

}
