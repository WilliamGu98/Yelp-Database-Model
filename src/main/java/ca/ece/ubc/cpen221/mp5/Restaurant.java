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

    private boolean open;
    private String url; // Is in valid url format
    private String photo_url; // Is in valid url format
    private String[] neighborhoods; // Has at least one neighborhood entry that is exists in the city/location its
                                    // in
    private String[] categories; // Category entries must be one of predetermined categories (i.e. chinese,
                                 // indian, etc.)
    private String state; // Must be one of the 50 states in the US, in abbrev. format
    private String type; // Must be a business
    private double stars; // Must be a double between 1 and 5 inclusive, with only 0.5 increments
    private String city; // Must be a valid city in the restaurant's state
    private int review_count; // The number of reviews for the restaurant, and should match the number of
                              // review data entries in the same database
    private String[] schools; 
    private int price; // An integer between 1 and 5, inclusive

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
}
