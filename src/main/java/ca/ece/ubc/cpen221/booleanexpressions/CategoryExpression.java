package ca.ece.ubc.cpen221.booleanexpressions;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class CategoryExpression implements Expression {
    
    private RestaurantHandle r;
    private String category;

    public CategoryExpression (RestaurantHandle r, String category) {
        this.r = r;
        this.category = category;
    }
    
    @Override
    public boolean eval() {
        for (String categ : r.getRestaurant().getCategories()) {
            if (category.contains(categ)) {
                return true;
            }
        }
        return false;
    }

}
