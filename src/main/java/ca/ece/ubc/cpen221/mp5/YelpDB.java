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

        // Should first check to see if k = 0
        if (k == 0) {

        }

        // restaurant clusters contains random centroids mapped to a set of restaurants
        // initially, one centroid is mapped to all restaurants while the other
        // centroids are mapped to none
        Map<double[], Set<Restaurant>> restaurantClusters = this.initiateClusters(k);
        
        // Some form of loop here?
        this.reassignRestaurants(restaurantClusters);

        // keep alternating between:
        // 1) shuffle the restaurants so they belong to the set with the closest
        // centroid
        // 2) assigning each centroid a new value based on its set of restaurants

        // Before converting to json we should have a list of sets that represent the
        // clusters

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

    private double computeMean(List<Integer> intList) {
        double count = 0;
        for (Integer i : intList) {
            count += i;
        }
        return count / intList.size();
    }

    // Helper for computing Sxx and Sxy
    private double computeSxx(List<Integer> intList, double mean) {
        double total = 0;
        for (Integer i : intList) {
            total += Math.pow((i - mean), 2);
        }
        return total;
    }

    // Requires size of intListX and intListY to be the same
    private double computeSxy(List<Integer> intListX, double meanX, List<Integer> intListY, double meanY) {
        double total = 0;
        for (int i = 0; i < intListX.size(); i++) {
            total += (intListX.get(i) - meanX) * (intListY.get(i) - meanY);
        }
        return total;
    }

    // Get the distance between a centroid and a restaurant
    private double computeDistance(double[] centroid, Restaurant restaurant) {
        return Math.sqrt(
                Math.pow(centroid[0] - restaurant.getLatitude(), 2) + Math.pow(centroid[1] - restaurant.getLongitude(), 2));
    }

    // Based on min and max lattitudes and longitudes, generate a random centroid
    private double[] generateRandomCentroid(double minLat, double maxLat, double minLon, double maxLon) {
        Random r = new Random();
        double randomLat = minLat + (maxLat - minLat) * r.nextDouble();
        double randomLon = minLon + (maxLon - minLon) * r.nextDouble();

        double[] centroid = new double[2];
        centroid[0] = randomLat;
        centroid[1] = randomLon;

        return centroid;
    }

    // Initiate a number of restaurant clusters, with all restaurants beginning in
    // one cluster
    private Map<double[], Set<Restaurant>> initiateClusters(int k) {
        // centroids[0] is lattitude and centroids[1] is longitude for a centroid
        // Map assigns restaurants to a specific centroid
        Map<double[], Set<Restaurant>> restaurantClusters = new HashMap<double[], Set<Restaurant>>();

        // Find the min and max lattitudes and longitudes
        double minLat = Double.MAX_VALUE;
        double minLon = Double.MAX_VALUE;
        double maxLat = Double.MIN_VALUE;
        double maxLon = Double.MIN_VALUE;

        for (Restaurant r : this.restaurantMap.values()) {
            double lat = r.getLatitude();
            double lon = r.getLongitude();
            if (lat < minLat) {
                minLat = lat;
            }
            if (lat > maxLat) {
                maxLat = lat;
            }
            if (lon < minLon) {
                minLon = lon;
            }
            if (lon > maxLon) {
                maxLon = lon;
            }
        }

        // Create a number of centroids equal to k
        for (int i = 0; i < k; i++) {
            // Since we checked for k=0 condition, there is at least one key in restaurant
            // clusters, so put all the restaurants in the first set we make
            if (i == 0) {
                HashSet<Restaurant> fullSet = new HashSet<Restaurant>(this.restaurantMap.values());
                restaurantClusters.put(this.generateRandomCentroid(minLat, maxLat, minLon, maxLon), fullSet);

            } else {
                // For other sets, make a generate random centroid based on min and max
                // lattitudes
                // and longitudes, with (currently) no restaurants
                HashSet<Restaurant> emptySet = new HashSet<Restaurant>();
                restaurantClusters.put(this.generateRandomCentroid(minLat, maxLat, minLon, maxLon), emptySet);
            }
        }
        return restaurantClusters;
    }

    // Reassigns each restaurant to its closest centroid
    private void reassignRestaurants(Map<double[], Set<Restaurant>> restaurantClusters) {
        // For each restaurant, go through each centroid and find which one it belongs
        // to (old centroid)
        // At the same time, keep track of the distances for a potential new centroid target
        // After going through all centroids, check if old = new
        // If not, drop the restaurant from its old centroid and put it in the new centroid
        for (Restaurant r : this.restaurantMap.values()) {
            double[] oldCentroid = null;
            double[] newCentroid = null;
            double minDist = Double.MAX_VALUE;
            
            for (double[] centroid : restaurantClusters.keySet()) {
                if (restaurantClusters.get(centroid).contains(r)) {
                    oldCentroid = centroid;
                }
                if (this.computeDistance(centroid, r) < minDist) {
                    newCentroid = centroid;
                }
            }
            // If old and new centroids are different, then reassian the restaurant to
            // a different centroid
            if (oldCentroid!=newCentroid) {
                restaurantClusters.get(oldCentroid).remove(r);
                restaurantClusters.get(newCentroid).add(r);
            }
        }
    }
    
    private void reassignCentroids(Map<double[], Set<Restaurant>> restaurantClusters) {
        
    }

}
