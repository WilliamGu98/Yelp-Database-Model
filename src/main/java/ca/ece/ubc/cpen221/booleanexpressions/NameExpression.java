package ca.ece.ubc.cpen221.booleanexpressions;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class NameExpression implements Expression{
    
    private RestaurantHandle r;
    private String name;

    public NameExpression (RestaurantHandle r, String name) {
        this.r = r;
        this.name = name;
    }

    @Override
    public boolean eval() {
        return r.getRestaurant().getName().contains(name);
    }

}
