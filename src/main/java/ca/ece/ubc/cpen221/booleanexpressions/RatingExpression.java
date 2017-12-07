package ca.ece.ubc.cpen221.booleanexpressions;

public class RatingExpression implements Expression{
    
    /**
     * AF: Represents a terminal boolean expression that evaluates whether a
     * restaurant matches the conditions of a rating identifier
     */
    
    /** Rep Invariant **/
    private RestaurantHandle r; // Contains a valid restaurant object
    private double rating;
    public String op; // Should be one of >, >=, =, <, <=

    /**
     * Constructor for a rating expression
     * @param r 
     *      restaurant handle that contains a restaurant
     * @param op
     *      a string that represents the inequality operator to use in the rating comparison
     * @param price
     *      a double that represents the rating to compare the restaurant rating with
     */
    public RatingExpression (RestaurantHandle r, double rating, String op) {
        this.r = r;
        this.rating = rating;
        this.op = op;
    }

    /**
     * Evaluate the boolean expression
     * 
     * @return true if the rating comparison evaluates to true, false if not
     */
    @Override
    public boolean eval() {
        if (op.equals("<")) {
            return r.getRestaurant().getStars() < rating;
        }
        else if (op.equals("<=")) {
            return r.getRestaurant().getStars() <= rating;
        }
        else if (op.equals(">")) {
            return r.getRestaurant().getStars() > rating;
        }
        else if (op.equals(">=")) {
            return r.getRestaurant().getStars() >= rating;
        }
        else {
            return r.getRestaurant().getStars() == rating;
        }
    }

}
