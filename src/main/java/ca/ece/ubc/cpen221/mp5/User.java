package ca.ece.ubc.cpen221.mp5;
import java.util.*;

public class User implements YelpElement{
    
    private String url;
    private Map<String,Integer> votes;
    private int review_count;
    private String type;
    private String user_id;
    private String name;
    private double average_stars;
    
    @Override
    public String toString() {
        return this.user_id;
    }
    
    @Override
    public int hashCode() {
        return this.user_id.hashCode();
        
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        return this.user_id.equals(other.user_id);
    }
}
