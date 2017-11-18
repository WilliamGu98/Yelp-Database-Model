package ca.ece.ubc.cpen221.mp5;
import java.util.*;

public class Review extends YelpElement{
    private String review_id;
	private String type;
	private String business_id;
	private Map votes;
	private String text;
	private int stars;
	private String user_id;
	private String date;
	
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
