package ca.ece.ubc.cpen221.mp5;

import java.util.*;

public class YelpUser extends User {

    protected String url;
    protected Map<String, Integer> votes;
    protected int review_count;
    protected String type;
    protected double average_stars;

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
    
    public void updateWithNewReview(Review r) {
        double new_average_stars = (average_stars * review_count + r.getStars())/(review_count + 1);
        this.review_count++;
        this.average_stars = new_average_stars;
    }

    /**
     * Adds all fields required for a new user. Also modifies db by this user to the
     * given database, along with a randomly generated user id
     * 
     * @param db
     */
    public void generateNewUserInfo(YelpDB db) {
        this.votes = new HashMap<String, Integer>();
        this.review_count = 0;
        this.type = "user";
        this.average_stars = 0.0;
        this.user_id = db.generateUserID();
        this.url = "http://www.yelp.com/user_details?userid=" + this.user_id;
    }
}
