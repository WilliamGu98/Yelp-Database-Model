package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.util.*;
import java.util.function.ToDoubleBiFunction;

import com.google.gson.*;

public class YelpDB implements MP5Db {

    private Map<String, Restaurant> restaurantMap; // Business_id -> Restaurant
    private Map<String, Review> reviewMap; // Review_id -> Review
    private Map<String, User> userMap; // User_id -> User

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

        // Process restaurants
        try (BufferedReader reader = new BufferedReader(new FileReader(restaurants))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Restaurant r = gson.fromJson(line, Restaurant.class);
                String business_id = r.toString();
                this.restaurantMap.put(business_id, r);
            }
        }

        // Process reviews
        try (BufferedReader reader = new BufferedReader(new FileReader(reviews))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Review r = gson.fromJson(line, Review.class);
                String review_id = r.toString();
                this.reviewMap.put(review_id, r);
            }
        }

        // Process users
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
     * @return Returns a copy of the set of all the restaurant business_ids in the
     *         database
     */
    public Set<String> getRestaurantSet() {
        return new HashSet<String>(restaurantMap.keySet());
    }

    /**
     * 
     * @return Returns a copy of the set of all the review_id's in the database
     */
    public Set<String> getReviewSet() {
        return new HashSet<String>(reviewMap.keySet());
    }

    /**
     * @return Returns a copy of the set of all user_id's in the database
     */
    public Set<String> getUserSet() {
        return new HashSet<String>(this.userMap.keySet());
    }

    /**
     * @param id
     *            Restaurant business id
     * @return Returns the price rating of the restaurant
     */
    public int getRestaurantPrice(String id) {
        return this.restaurantMap.get(id).getPrice();
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
     * Requires that the user has at least 2 unique ratings (with different
     * rating-price combo)
     * 
     * @param user
     *            represents a user_id in the database
     * @return a function that predicts the user's ratings for objects (of type T)
     *         in the database of type MP5Db<T>. The function that is returned takes
     *         two arguments: one is the database and other other is a String that
     *         represents the id of an object of type T.
     */
    @Override
    public ToDoubleBiFunction getPredictorFunction(String user) {
        List<Integer> user_ratings = new ArrayList<Integer>(); // x
        List<Integer> restaurant_price = new ArrayList<Integer>(); // y

        // Construct two lists of user_ratings and restaurant_prices
        for (Review r : this.reviewMap.values()) {
            if (r.getUserId().equals(user)) {
                user_ratings.add(r.getRating());
                restaurant_price.add(this.restaurantMap.get(r.getBusinessId()).getPrice());
            }
        }

        double meanX = computeMean(restaurant_price);
        double meanY = computeMean(user_ratings);
        double Sxx = computeSxx(restaurant_price, meanX);
        double Syy = computeSxx(user_ratings, meanY);
        double Sxy = computeSxy(restaurant_price, meanX, user_ratings, meanY);

        double b = Sxy / Sxx;
        double a = meanY - b * meanX;

        ToDoubleBiFunction<String, YelpDB> function = (restaurantID, database) -> {
            // Function logic
            double price = database.getRestaurantPrice(restaurantID);
            return a + b * price;
        };

        return function;
    }

    private static double computeMean(List<Integer> intList) {
        double count = 0;
        for (Integer i : intList) {
            count += i;
        }
        return count / intList.size();
    }

    // Helper for computing Sxx and Sxy
    private static double computeSxx(List<Integer> intList, double mean) {
        double total = 0;
        for (Integer i : intList) {
            total += Math.pow((i - mean), 2);
        }
        return total;
    }

    // Requires size of intListX and intListY to be the same
    private static double computeSxy(List<Integer> intListX, double meanX, List<Integer> intListY, double meanY) {
        double total = 0;
        for (int i = 0; i < intListX.size(); i++) {
            total += (intListX.get(i) - meanX) * (intListY.get(i) - meanY);
        }
        return total;
    }

}
