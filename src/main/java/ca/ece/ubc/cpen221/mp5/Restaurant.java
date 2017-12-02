package ca.ece.ubc.cpen221.mp5;

import java.util.Arrays;

public class Restaurant extends Business {

    /**
     * AF: Represents a restaurant with special identifiers that may identify the
     * restaurant. Identifiers inherited from a generic business (since every
     * restaurant is a business) include its name, its latitude and longitude
     * coordinates, its full address, and a unique id. Other identifiers include the
     * restaurant's url, its rating in stars, the city it's in, etc.
     */

    protected boolean open;
    protected String url; // Is in valid url format
    protected String photo_url; // Is in valid url format
    protected String[] neighborhoods; // Has at least one neighborhood entry that is exists in the city/location its
                                    // in
    protected String[] categories; // Category entries must be one of predetermined categories (i.e. chinese,
                                 // indian, etc.)
    protected String state; // Must be one of the 50 states in the US, in abbrev. format or empty
    protected String type; // Must be a "business"
    protected double stars; // Must be a double between 1 and 5 inclusive
    protected String city; // Must be a valid city in the restaurant's state or empty
    protected int review_count; // The number of reviews for the restaurant, and should match the number of
                              // review data entries in the same database
    protected String[] schools;
    protected int price; // An integer between 1 and 5, inclusive

    public boolean isOpen() {
        return this.open;
    }

    public String getURL() {
        return this.url;
    }

    public String getPhotoUrl() {
        return this.photo_url;
    }

    public String[] getNeighborhoods() {
        return Arrays.copyOf(this.neighborhoods, this.neighborhoods.length);
    }

    public String[] getCategories() {
        return Arrays.copyOf(this.categories, this.categories.length);
    }

    public String getState() {
        return this.state;
    }

    public String getType() {
        return this.type;
    }

    public double getStars() {
        return this.stars;
    }

    public String getCity() {
        return this.city;
    }

    public int getReviewCount() {
        return this.review_count;
    }

    public String[] getSchools() {
        return Arrays.copyOf(this.schools, this.schools.length);
    }

    public int getPrice() {
        return this.price;
    }
    
    public void updateWithNewReview(Review r) {
        double new_stars = (stars * review_count + r.getStars())/(review_count + 1);
        this.review_count++;
        this.stars = new_stars;
    }
    
    public void generateNewRestaurantInfo(YelpDB db) {
        this.type = "business";
        this.open = true;
        if (this.neighborhoods == null) {
            this.neighborhoods = new String[] {};
        }
        if (this.categories == null) {
            this.categories = new String[] {};
        }
        if (this.state == null) {
            this.state = "";
        }
        if (this.city == null) {
            this.city = "";
        }
        if (this.schools == null) {
            this.schools = new String[] {};
        }
        this.business_id = db.generateRestaurantID();
        this.url = "http://www.yelp.com/restaurant_details?businessid=" + this.business_id;
        this.photo_url = "http://www.yelp.com/restaurant_photos?businessid=" + this.business_id;
    }
}
