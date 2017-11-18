package ca.ece.ubc.cpen221.mp5;


import java.io.*;
import java.util.*;
import java.util.function.ToDoubleBiFunction;

import com.google.gson.*;

public class YelpDB implements MP5Db {
    
    private Map<String, Restaurant> restaurantMap; //Name -> Restaurant
    private Map<String, Review> reviewMap; //Review_id -> Review
    private Map<String, User> userMap; //User_id -> User
    

    /**
     * Constructor
     * 
     * @param restaurants
     *            name of file for list of restaurants
     * @param reviews
     *            name of file for list of reviews
     * @param users
     *            name of file for list of users
     */
    public YelpDB(String restaurants, String reviews, String users) throws IOException {
        
        Gson gson = new Gson();
        this.restaurantMap = new HashMap<String, Restaurant>();
        this.reviewMap = new HashMap<String, Review>();
        this.userMap = new HashMap<String, User>();
        
        //Process restaurants
        try (BufferedReader reader = new BufferedReader(new FileReader(restaurants))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Restaurant r = gson.fromJson(line, Restaurant.class);
                String name = r.toString();
                this.restaurantMap.put(name, r);
            }
        }
        
        //Process reviews
        try (BufferedReader reader = new BufferedReader(new FileReader(reviews))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Review r = gson.fromJson(line, Review.class);
                String review_id = r.toString();
                this.reviewMap.put(review_id, r);
            }
        }
        
        //Process users
        try (BufferedReader reader = new BufferedReader(new FileReader(users))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User u = gson.fromJson(line, User.class);
                String user_id = u.toString();
                this.userMap.put(user_id, u);
            }
        }
    }
    
    public void addReview() {
    	
    }
    /**
     * 
     * @return Returns a set of all the restaurant names in the database
     */
    public Set<String> getRestaurantSet() {
    	return this.restaurantMap.keySet();
    }

    /**
     * 
     * @return Returns a set of all the review_id's in the database
     */
    public Set<String> getReviewSet() {
    	return this.reviewMap.keySet();
    }
    
    /**
     * @return Returns a set of all user_id's in the database
     */
    public Set<String> getUserSet() {
    	return this.userMap.keySet();
    }
    
    /**
     * @param r - Restaurant name
     * @return Returns the price rating of the restaurant
     */
    public int getRestaurantPrice(Restaurant r) {
    	return this.restaurantMap.get(r).getPrice();
    }
    public double getRestaurantLongitude(Restaurant r) {
    	return this.restaurantMap.get(r).getLongitude();
    }
    public double getRestaurantLatitude(Restaurant r) {
    	return this.restaurantMap.get(r).getLatitude();
    }
    /**
     * Perform a structured query and return the set of objects that matches the
     * query
     * 
     * @param queryString
     * @return the set of objects that matches the query
     */
    @Override
    public Set getMatches(String queryString) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Cluster objects into k clusters using k-means clustering
     * 
     * @param k
     *            number of clusters to create (0 < k <= number of objects)
     * @return a String, in JSON format, that represents the clusters
     */
    @Override
    public String kMeansClusters_json(int k) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 
     * @param user
     *            represents a user_id in the database
     * @return a function that predicts the user's ratings for objects (of type
     *         T) in the database of type MP5Db<T>. The function that is
     *         returned takes two arguments: one is the database and other other
     *         is a String that represents the id of an object of type T.
     */
    @Override
    public ToDoubleBiFunction getPredictorFunction(String user) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    private static double computeSxx(List<Integer> x) {
        return 0;
    }

}
