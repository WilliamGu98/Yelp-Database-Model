package ca.ece.ubc.cpen221.mp5;
import java.util.*;

public class User {
    
    private String url;
    private Map<String,Integer> votes;
    private int review_count;
    private String type;
    private String user_id;
    private String name;
    private double average_stars;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("url: " + this.url + "\n");
        sb.append("votes: " + this.votes + "\n");
        sb.append("review_count: " + this.review_count + "\n");
        sb.append("type: " + this.type + "\n");
        sb.append("user_id: " + this.user_id + "\n");
        sb.append("name: " + this.name + "\n");
        sb.append("average_stars: " + this.average_stars);
        
        return sb.toString();
    }
}
