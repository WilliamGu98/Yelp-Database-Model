package ca.ece.ubc.cpen221.booleanexpressions;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class RestaurantHandle {

    /**
     * AF: Represents a restaurant container that can hold different restaurants,
     * but one at a time at most
     */

    /**
     * Rep Invariant: Restaurant should not be null
     */
    private Restaurant r;

    /**
     * Changes the restaurant that the handle contains
     * 
     * @param r
     *            restaurant to "put" in handle
     */
    public void setRestaurant(Restaurant r) {
        this.r = r;
    }

    /**
     * Retrieves the restaurant that the handle contains
     * 
     * @return the restaurant "in" the handle
     */
    public Restaurant getRestaurant() {
        return r;
    }

}
