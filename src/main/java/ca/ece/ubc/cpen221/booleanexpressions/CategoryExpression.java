package ca.ece.ubc.cpen221.booleanexpressions;

public class CategoryExpression implements Expression {

    /**
     * AF: Represents a terminal boolean expression that evaluates whether a
     * restaurant matches some specific category identifier
     */

    /** Rep Invariant **/
    private RestaurantHandle r; // Contains a valid restaurant object
    private String category;

    /**
     * Constructor for a category expression
     * @param r 
     *      restaurant handle that contains a restaurant
     * @param category
     *      a string that represents a category we wish to look for
     */
    public CategoryExpression(RestaurantHandle r, String category) {
        this.r = r;
        this.category = category;
    }

    /**
     * Evaluate the boolean expression
     * 
     * @return true if the restaurant in the handle has a category that contains the
     *         specified identifier (category)
     */
    @Override
    public boolean eval() {
        for (String categ : r.getRestaurant().getCategories()) {
            if (categ.toLowerCase().contains(category.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
