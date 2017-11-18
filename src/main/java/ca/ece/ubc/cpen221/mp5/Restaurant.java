package ca.ece.ubc.cpen221.mp5;

public class Restaurant implements YelpElement {
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

    public double getLongitude() {
        return this.longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    @Override
    public String toString() {
        return this.business_id;
    }

    @Override
    public int hashCode() {
        return this.business_id.hashCode();

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
        return this.business_id.equals(other.business_id);
    }
}
