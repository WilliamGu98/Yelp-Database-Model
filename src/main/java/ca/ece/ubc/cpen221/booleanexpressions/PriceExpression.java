package ca.ece.ubc.cpen221.booleanexpressions;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class PriceExpression implements Expression{
    
    private RestaurantHandle r;
    private double price;
    public String op;

    public PriceExpression (RestaurantHandle r, double price, String op) {
        this.r = r;
        this.price = price;
        this.op = op;
    }

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
