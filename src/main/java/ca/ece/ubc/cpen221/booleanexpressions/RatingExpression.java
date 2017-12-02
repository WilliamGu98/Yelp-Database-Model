package ca.ece.ubc.cpen221.booleanexpressions;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class RatingExpression implements Expression{
    private RestaurantHandle r;
    private double rating;
    public String op;

    public RatingExpression (RestaurantHandle r, double rating, String op) {
        this.r = r;
        this.rating = rating;
        this.op = op;
    }

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
