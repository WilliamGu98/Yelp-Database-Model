package ca.ece.ubc.cpen221.mp5;

import java.util.Map;

public class YelpUser extends User{
    
    private String url;
    private Map<String,Integer> votes;
    private int review_count;
    private String type;
    private double average_stars;
    
    public String getURL() {
        return this.url;
    }
    
    public int getReviewCount() {
        return this.review_count;
    }
    
    public String getType() {
        return this.type;
    }
    
    public double getAverageStars() {
        return this.average_stars;
    }
}
