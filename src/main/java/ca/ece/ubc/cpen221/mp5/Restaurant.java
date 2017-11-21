package ca.ece.ubc.cpen221.mp5;

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

    public int getPrice() {
        return this.price;
    }
}
