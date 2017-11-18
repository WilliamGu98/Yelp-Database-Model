package ca.ece.ubc.cpen221.mp5;


import java.io.*;
import java.util.*;
import java.util.function.ToDoubleBiFunction;

import com.google.gson.*;

public class YelpDB implements MP5Db {
    
    private Set<Restaurant> restaurantSet;
    private Set<Review> reviewSet;
    private Set<User> userSet;
    

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
        this.restaurantSet = new HashSet<Restaurant>();
        this.reviewSet = new HashSet<Review>();
        this.userSet = new HashSet<User>();
        
        //Process restaurants
        try (BufferedReader reader = new BufferedReader(new FileReader(restaurants))) {
            String line;
            while ((line = reader.readLine()) != null) {
                this.restaurantSet.add(gson.fromJson(line, Restaurant.class));
            }
        }
        
        //Process reviews
        try (BufferedReader reader = new BufferedReader(new FileReader(reviews))) {
            String line;
            while ((line = reader.readLine()) != null) {
                this.restaurantSet.add(gson.fromJson(line, Restaurant.class));
            }
        }
        
        //Process users
        try (BufferedReader reader = new BufferedReader(new FileReader(users))) {
            String line;
            while ((line = reader.readLine()) != null) {
                this.restaurantSet.add(gson.fromJson(line, Restaurant.class));
            }
        }
    }

    @Override
    public Set getMatches(String queryString) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String kMeansClusters_json(int k) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ToDoubleBiFunction getPredictorFunction(String user) {
        // TODO Auto-generated method stub
        return null;
    }

}
