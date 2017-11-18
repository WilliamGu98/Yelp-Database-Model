package ca.ece.ubc.cpen221.mp5;

public class Restaurant extends YelpElement {
    private String name;
    private boolean open;
    private String url;
    private String photo_url;
    private double longitude;
    private double latitude;
    private String full_address;
    private String[] neighborhoods;
    private String business_id;
    private String[] categories;
    private String state;
    private String type;
    private double stars;
    private String city;
    private int review_count;
    private String[] schools;
    private int price;
    
    public int getPrice() {
    	return this.price;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode();
        
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Restaurant)) {
            return false;
        }
        Restaurant other = (Restaurant) obj;
        return this.name.equals(other.name);
    }
}
