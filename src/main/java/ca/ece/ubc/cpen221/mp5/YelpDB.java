package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.util.*;
import java.util.function.ToDoubleBiFunction;

import com.google.gson.*;

public class YelpDB<DataEntry> implements MP5Db {

    /**
     * AF: Represents a yelp database that includes lookup tables for the
     * restaurants, reviews, and users included in the database. The database also
     * supports several operations
     */

    private Map<String, Restaurant> restaurantMap; // Maps a Business_id -> Restaurant. The business id should match
                                                   // that of the restaurant
    private Map<String, Review> reviewMap; // Maps a Review_id -> Review. The review id should match that of the review
    private Map<String, YelpUser> userMap; // Maps a User_id -> YelpUser. The yelp user id should match that of the user
                                           // id
    Gson gson; //For JSON parsing

    /**
     * Constructor for a yelp database
     * 
     * @param restaurants
     *            name of file for list of restaurants in JSON format
     * @param reviews
     *            name of file for list of reviews in JSON format
     * @param users
     *            name of file for list of yelp users in JSON format
     * @throws IOException
     *             if there is an error opening one of the files
     */
    public YelpDB(String restaurants, String reviews, String users) throws IOException {

        this.gson = new Gson();
        this.restaurantMap = new HashMap<String, Restaurant>();
        this.reviewMap = new HashMap<String, Review>();
        this.userMap = new HashMap<String, YelpUser>();

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
                YelpUser u = gson.fromJson(line, YelpUser.class);
                String user_id = u.toString();
                this.userMap.put(user_id, u);
            }
        }
    }

    /**
     * Lookup a specific restaurant given its ID
     * 
     * @param rID
     *            the ID of the restaurant to lookup
     * @return the restaurant that matches rID
     */
    public Restaurant getRestaurant(String rID) {
        return this.restaurantMap.get(rID);
    }

    /**
     * Lookup a specific review given its ID
     * 
     * @param rID
     *            the ID of the review to lookup
     * @return the review that matches rID
     */
    public Review getReview(String rID) {
        return this.reviewMap.get(rID);
    }

    /**
     * Lookup a specific yelp user given its ID
     * 
     * @param uID
     *            the ID of the user to lookup
     * @return the yelp user that matches uID
     */
    public YelpUser getYelpUser(String uID) {
        return this.userMap.get(uID);
    }

    /**
     * Perform a structured query and return the set of objects that matches the
     * query
     * 
     * @param queryString
     * @return the set of objects that matches the query
     */
    @Override
    public Set<DataEntry> getMatches(String queryString) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Cluster objects into k clusters using k-means clustering. The resulting
     * clusters are restaurants identified by their business ID. If k = 0, then no
     * clusters are created
     * 
     * @param k
     *            number of clusters to create (0 < k <= number of objects)
     * @return a String, in JSON format, that represents the clusters
     */
    @Override
    public String kMeansClusters_json(int k) {

        List<Set<String>> kMeansClusters = new ArrayList<Set<String>>();

        if (k == 1) {
            // No algorithm needed
            kMeansClusters.add(new HashSet<String>(this.restaurantMap.keySet()));
            return this.clusterToJSON(kMeansClusters);
        }

        // restaurant clusters contains random centroids mapped to a set of its closest
        // restaurants (in id form)
        Map<double[], Set<String>> restaurantClusters = this.initiateClusters(k);

        // Flag is true if any restaurants are reassigned to a new centroid
        // If no restaurants are reassigned, then we are done
        boolean flag;
        do {
            this.reassignCentroids(restaurantClusters);
            flag = this.reassignRestaurants(restaurantClusters);
        } while (flag);

        // Add final restaurant clusters to list
        for (Set<String> cluster : restaurantClusters.values()) {
            kMeansClusters.add(cluster);
        }

        return this.clusterToJSON(kMeansClusters);
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
    public ToDoubleBiFunction<YelpDB<DataEntry>, String> getPredictorFunction(String user) {
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
        double Sxy = computeSxy(restaurant_price, meanX, user_ratings, meanY);

        double b = Sxy / Sxx;
        double a = meanY - b * meanX;

        ToDoubleBiFunction<YelpDB<DataEntry>, String> function = (database, restaurantID) -> {
            // Function logic
            double price = database.getRestaurant(restaurantID).getPrice();
            return a + b * price;
        };

        return function;
    }
    
    
    /** Methods for Server **/
    
    /**
     * 
     * @param rID
     * @return returns a string in JSON format of the restaurant info
     */
    public String getRestaurantJSON(String rID) {        
        return gson.toJson(this.restaurantMap.get(rID));
    }
    
    
    public String addUser(String jsonInfo) {
        YelpUser user = gson.fromJson(jsonInfo, YelpUser.class);
        
        //We need to only create "new" fields for the yelpuser if
        //it is not included in jsonInfo
        //How to do this?
        //Also need to randomize a user id and a url
        //We could add a method that does this? Or do we change constructor?
        
        return null; //Return jsonInfo of completed user (with all fields filled)
    }
    
    public String addRestaurant(String jsonInfo) {
        return null;
    }
    
    public String addReview(String jsonInfo) {
        return null;
    }

    /** HELPER METHODS **/

    /**
     * Helper method to compute the mean of the integers in a given list
     * 
     * @param intList
     *            list of integers where no integers in the list are null
     * @return the mean of the integers in intList
     */
    private double computeMean(List<Integer> intList) {
        double count = 0;
        for (Integer i : intList) {
            count += i;
        }
        return count / intList.size();
    }

    /**
     * Helper method for computing Sxx, where Sxx = SUMi of (xi-mean(x))^2
     * 
     * @param intList
     *            list of integers where each integer represents xi, where x is the
     *            integer and i is the index of the integer. requires that no
     *            integers are null
     * @param mean
     *            the mean value of the integers in the list
     * @return Sxx for the integers in the list
     */
    private double computeSxx(List<Integer> intList, double mean) {
        double total = 0;
        for (Integer i : intList) {
            total += Math.pow((i - mean), 2);
        }
        return total;
    }

    /**
     * Helper method for computing Sxy, where Sxy = SUMi of (xi-mean(x))(yi-mean(y))
     * 
     * @param intListX
     *            list of integers where each integer represents xi, where x is the
     *            integer and i is the index of the integer. requires that no
     *            integers are null
     * @param meanX
     *            the mean value of the integers in intListX
     * @param intListY
     *            list of integers where each integer represents yi, where y is the
     *            integer and y is the index of the integer. requires that no
     *            integers are null
     * @param meanY
     *            the mean value of the integers in intListY
     * @requires that intListX and intListY have the same size
     * @return Sxy for the integers from the combined list
     */
    private double computeSxy(List<Integer> intListX, double meanX, List<Integer> intListY, double meanY) {
        double total = 0;
        for (int i = 0; i < intListX.size(); i++) {
            total += (intListX.get(i) - meanX) * (intListY.get(i) - meanY);
        }
        return total;
    }

    /**
     * Helper method for computing the distance between a centroid and a restaurant
     * 
     * @param centroid
     *            where centroid[0] represents an x coordinate and centroid[1]
     *            represents a y coordinate. requires that both these elements are
     *            not null.
     * @param restaurant
     *            with a valid latitude (x) and longitude (y)
     * @return the direct distance between the two points
     */
    private double computeDistance(double[] centroid, Restaurant restaurant) {
        return Math.sqrt(Math.pow(centroid[0] - restaurant.getLatitude(), 2)
                + Math.pow(centroid[1] - restaurant.getLongitude(), 2));
    }

    /**
     * Helper method for generating a random centroid given a certain valid latitude
     * (x) and longitude (y) domain and range
     * 
     * @param minLat
     *            minimum latitude of the random centroid
     * @param maxLat
     *            maximum latitude of the random centroid
     * @param minLon
     *            minimum longitude of the random centroid
     * @param maxLon
     *            maximum longitude of the random centroid
     * @return a two element double array where the 0th element is the random
     *         lattitude and the 1st element is the random longitude
     */
    private double[] generateRandomCentroid(double minLat, double maxLat, double minLon, double maxLon) {
        Random r = new Random();
        double randomLat = minLat + (maxLat - minLat) * r.nextDouble();
        double randomLon = minLon + (maxLon - minLon) * r.nextDouble();

        double[] centroid = new double[2];
        centroid[0] = randomLat;
        centroid[1] = randomLon;

        return centroid;
    }

    /**
     * Helper method for initiating k restaurant clusters, where k random centroids
     * are generated within the smallest square area that includes all the
     * restaurants in this database. Each restaurant in this database is assigned to
     * the closest centroid that was generated.
     * 
     * @param k
     *            number of clusters to generate. k should be greater than 0
     * @return a map where the keys represent centroids and the values represent a
     *         set of strings where each string represents one of the restaurants in
     *         the database with its ID. each unique restaurant is mapped by exactly
     *         one centroid
     */
    private Map<double[], Set<String>> initiateClusters(int k) {
        // centroids[0] is lattitude and centroids[1] is longitude for a centroid
        // Map assigns restaurants to a specific centroid
        Map<double[], Set<String>> restaurantClusters = new HashMap<double[], Set<String>>();

        // Find the min and max latitudes and longitudes
        double minLat = Double.MAX_VALUE;
        double minLon = Double.MAX_VALUE;
        double maxLat = -Double.MAX_VALUE;
        double maxLon = -Double.MAX_VALUE;

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
            // generate random centroid based on min and max lattitudes
            // and longitudes, with (currently) no assigned restaurants
            HashSet<String> emptySet = new HashSet<String>();
            restaurantClusters.put(this.generateRandomCentroid(minLat, maxLat, minLon, maxLon), emptySet);
        }

        // Assign each resetaurant to its closest centroid
        for (String rID : this.restaurantMap.keySet()) {
            double[] newCentroid = null;
            double minDist = Double.MAX_VALUE;

            for (double[] centroid : restaurantClusters.keySet()) {
                double distance = this.computeDistance(centroid, this.restaurantMap.get(rID));
                if (distance < minDist) {
                    newCentroid = centroid;
                    minDist = distance;
                }
            }
            restaurantClusters.get(newCentroid).add(rID);
        }
        return restaurantClusters;
    }

    /**
     * Reassigns each restaurant to its closest centroid
     * 
     * @param restaurantClusters
     *            is a map that represents the existing centroid -> restaurant
     *            mappings
     * @return true if at least one restaurant is reassigned and false if 0
     *         restaurants are reassigned
     */
    private boolean reassignRestaurants(Map<double[], Set<String>> restaurantClusters) {
        // For each restaurant, go through each centroid and find which one it belongs
        // to (old centroid)
        // At the same time, keep track of the distances for a potential new centroid
        // target
        boolean flag = false;

        for (String rID : this.restaurantMap.keySet()) {
            Restaurant r = this.restaurantMap.get(rID);

            double[] oldCentroid = null;
            double[] newCentroid = null;
            double minDist = Double.MAX_VALUE;

            for (double[] centroid : restaurantClusters.keySet()) {
                double distance = this.computeDistance(centroid, r);
                if (restaurantClusters.get(centroid).contains(rID)) {
                    oldCentroid = centroid;
                }
                if (distance < minDist) {
                    newCentroid = centroid;
                    minDist = distance;
                }
            }
            // If old and new centroids are different, then reassign the restaurant to
            // a different centroid
            if (oldCentroid != newCentroid) {
                flag = true;
                restaurantClusters.get(oldCentroid).remove(rID);
                restaurantClusters.get(newCentroid).add(rID);
            }
        }
        return flag;
    }

    /**
     * Re-evaluates each centroid based on the mean value of the coordinates of the
     * restaurants in its cluster
     * 
     * @param restaurantClusters
     *            is a map that represents the existing centroid -> restaurant
     *            mappings
     */
    private void reassignCentroids(Map<double[], Set<String>> restaurantClusters) {

        for (double[] centroid : restaurantClusters.keySet()) {

            // If a centroid has no restaurants, do not update the centroid
            if (restaurantClusters.get(centroid).isEmpty()) {
                continue;
            }

            double totalLat = 0;
            double totalLon = 0;
            int size = restaurantClusters.get(centroid).size();

            for (String rID : restaurantClusters.get(centroid)) {
                Restaurant r = this.restaurantMap.get(rID);
                // Get the total value for lattitude and longitude for all restaurants
                // in the cluster
                totalLat += r.getLatitude();
                totalLon += r.getLongitude();
            }

            centroid[0] = totalLat / size;
            centroid[1] = totalLon / size;
        }
    }

    /**
     * Converts a list of restaurant clusters to its a string in JSON format
     * 
     * @param kMeansClusters
     * @return a string that represents the clusters, in JSON format
     */
    private String clusterToJSON(List<Set<String>> kMeansClusters) {

        Gson gson = new Gson();
        List<RestaurantCluster> formattedClusters = new ArrayList<RestaurantCluster>();

        for (int i = 0; i < kMeansClusters.size(); i++) {
            for (String rID : kMeansClusters.get(i)) {
                Restaurant r = this.restaurantMap.get(rID);
                RestaurantCluster cluster = new RestaurantCluster(r.getName(), i, r.getLatitude(), r.getLongitude());
                formattedClusters.add(cluster);
            }
        }
        return gson.toJson(formattedClusters);
    }

    /**
     * Helper class to convert restaurant clusters to JSON format
     */
    private class RestaurantCluster {

        private double x;
        private double y;
        private String name;
        private int cluster;
        private double weight;

        public RestaurantCluster(String name, int cluster, double lattitude, double longitude) {
            this.name = name;
            this.cluster = cluster;
            this.x = lattitude;
            this.y = longitude;
            this.weight = 1.0;
        }
    }
}
