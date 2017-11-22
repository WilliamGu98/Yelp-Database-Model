package ca.ece.ubc.cpen221.mp5;

import java.util.Arrays;

public class Restaurant extends Business {
    private boolean open;
    private String url;
    private String photo_url;
    private String[] neighborhoods;
    private String[] categories;
    private String state;
    private String type;
    private double stars;
    private String city;
    private int review_count;
    private String[] schools;
    private int price;

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
