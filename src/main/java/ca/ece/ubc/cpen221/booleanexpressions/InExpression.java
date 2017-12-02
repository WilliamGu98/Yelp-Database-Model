package ca.ece.ubc.cpen221.booleanexpressions;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class InExpression implements Expression {

    private RestaurantHandle r;
    private String location;

    public InExpression(RestaurantHandle r, String location) {
        this.r = r;
        this.location = location;
    }

    @Override
    public boolean eval() {

        for (String neighborhood : r.getRestaurant().getNeighborhoods()) {
            if (neighborhood.contains(location)) {
                return true;
            }
        }

        return r.getRestaurant().getCity().contains(location) || r.getRestaurant().getState().contains(location)
                || r.getRestaurant().getFullAddress().contains(location);
    }

}
