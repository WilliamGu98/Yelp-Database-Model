package ca.ece.ubc.cpen221.mp5;

import java.util.*;

public class Review implements DataEntry {
    protected String review_id;
    protected String type;
    protected String business_id;
    protected Map votes;
    protected String text;
    protected int stars;
    protected String user_id;
    protected String date;

    public String getUserId() {
        return this.user_id;
    }

    public int getRating() {
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

    @Override
    public String toString() {
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
