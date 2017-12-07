package ca.ece.ubc.cpen221.booleanexpressions;

public class InExpression implements Expression {

    /**
     * AF: Represents a terminal boolean expression that evaluates whether a
     * restaurant matches some specific location identifier
     */

    /** Rep Invariant **/
    private RestaurantHandle r; // Contains a valid restaurant object
    private String location;

    /**
     * Constructor for a in expression
     * 
     * @param r
     *            restaurant handle that contains a restaurant
     * @param location
     *            a string that represents a location we wish to look for
     */
    public InExpression(RestaurantHandle r, String location) {
        this.r = r;
        this.location = location;
    }

    /**
     * Evaluate the boolean expression
     * 
     * @return true if the restaurant in the handle has a location that contains the
     *         specified identifier (location)
     */
    @Override
    public boolean eval() {

        for (String neighborhood : r.getRestaurant().getNeighborhoods()) {
            if (neighborhood.toLowerCase().contains(location.toLowerCase())) {
                return true;
            }
        }
        return r.getRestaurant().getCity().toLowerCase().contains(location.toLowerCase())
                || r.getRestaurant().getState().toLowerCase().contains(location.toLowerCase())
                || r.getRestaurant().getFullAddress().toLowerCase().contains(location.toLowerCase());
    }

}
