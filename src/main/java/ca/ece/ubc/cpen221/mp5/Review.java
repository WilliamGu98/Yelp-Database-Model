package ca.ece.ubc.cpen221.mp5;

import java.text.SimpleDateFormat;
import java.util.*;

public class Review implements DataEntry {
    protected String review_id;
    protected String type;
    protected String business_id;
    protected Map<String, Integer> votes;
    protected String text;
    protected int stars;
    protected String user_id;
    protected String date;

    public String getUserId() {
        return this.user_id;
    }

    public int getStars() {
        return this.stars;
    }

    public String getBusinessId() {
        return this.business_id;
    }

    public String getType() {
        return this.type;
    }

    public String getText() {
        return this.text;
    }

    public String getDate() {
        return this.date;
    }
    
    public void generateNewReviewInfo(YelpDB db) {
        this.type = "review";
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        this.review_id = db.generateReviewID();
    }

    @Override
    public String getID() {
        return this.review_id;
    }

    @Override
    public int hashCode() {
        return this.review_id.hashCode();

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Review)) {
            return false;
        }
        Review other = (Review) obj;
        return this.review_id.equals(other.review_id);
    }
}
