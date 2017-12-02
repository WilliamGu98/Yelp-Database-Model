package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.ToDoubleBiFunction;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.google.gson.*;

import ca.ece.ubc.cpen221.booleanexpressions.*;
import ca.ece.ubc.cpen221.parser.QueryLexer;
import ca.ece.ubc.cpen221.parser.QueryListener;
import ca.ece.ubc.cpen221.parser.QueryParser;

@SuppressWarnings("deprecation")
public class YelpDB implements MP5Db<Restaurant> {

	/**
	 * AF: Represents a yelp database that includes lookup tables for the
	 * restaurants, reviews, and users included in the database. The database also
	 * supports several operations
	 */

	/* Rep Invariants */
	private ConcurrentMap<String, Restaurant> restaurantMap; // Maps a Business_id -> Restaurant. The business id should
																// match that of the restaurant
	private ConcurrentMap<String, Review> reviewMap; // Maps a Review_id -> Review. The review id should match that of
														// the review
	private ConcurrentMap<String, YelpUser> userMap; // Maps a User_id -> YelpUser. The yelp user id should match that
														// of the user id
	private Gson gson; // For JSON parsing

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
		this.restaurantMap = new ConcurrentHashMap<String, Restaurant>();
		this.reviewMap = new ConcurrentHashMap<String, Review>();
		this.userMap = new ConcurrentHashMap<String, YelpUser>();

		// Process restaurants
		try (BufferedReader reader = new BufferedReader(new FileReader(restaurants))) {
			String line;
			while ((line = reader.readLine()) != null) {
				Restaurant r = gson.fromJson(line, Restaurant.class);
				String business_id = r.getID();
				this.restaurantMap.put(business_id, r);
			}
		}

		// Process reviews
		try (BufferedReader reader = new BufferedReader(new FileReader(reviews))) {
			String line;
			while ((line = reader.readLine()) != null) {
				Review r = gson.fromJson(line, Review.class);
				String review_id = r.getID();
				this.reviewMap.put(review_id, r);
			}
		}

		// Process users
		try (BufferedReader reader = new BufferedReader(new FileReader(users))) {
			String line;
			while ((line = reader.readLine()) != null) {
				YelpUser u = gson.fromJson(line, YelpUser.class);
				String user_id = u.getID();
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
	 * Perform a structured query and return the set of objects that matches the
	 * query
	 * 
	 * @param queryString
	 * @return the set of objects that matches the query
	 */
	@Override
	public Set<Restaurant> getMatches(String queryString) {
		// TODO Auto-generated method stub

		/* Setup grammar listener */
		CharStream stream = new ANTLRInputStream(queryString);
		QueryLexer lexer = new QueryLexer(stream);
		TokenStream tokens = new CommonTokenStream(lexer);
		QueryParser parser = new QueryParser(tokens);
		ParseTree tree = parser.root();
		ParseTreeWalker walker = new ParseTreeWalker();
		QueryCreator creator = new QueryCreator();		
		
		RestaurantHandle rH = new RestaurantHandle();
		creator.setRestaurantHandle(rH);
		
		walker.walk(creator, tree);

		Set<Restaurant> matches = new HashSet<Restaurant>();	
		
		// Look through every restaurant, if one matches query add it to the set
		for (Restaurant r : this.restaurantMap.values()) {
			rH.setRestaurant(r);
			if (creator.evaluateExpression()) {
			    matches.add(r);
			}
		}

		return matches;
	}

	/**
	 * Cluster objects into k clusters using k-means clustering. The resulting
	 * clusters are restaurants identified by their business ID.
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
		Map<double[], Set<String>> restaurantClusters = this.initiateClusters(k, this.restaurantMap.keySet());

		// DO WHILE LOOP HERE, WHILE THERE ARE NO EMPTY CLUSTERS

		do {
			Set<String> emptyCluster = findEmptyCluster(restaurantClusters);
			Set<String> largestCluster = findLargestCluster(restaurantClusters);

			// Code below finds some random centroid within the largest cluster
			double minLat = Double.MAX_VALUE;
			double minLon = Double.MAX_VALUE;
			double maxLat = -Double.MAX_VALUE;
			double maxLon = -Double.MAX_VALUE;
			for (String rID : largestCluster) {
				double lat = this.getRestaurant(rID).getLatitude();
				double lon = this.getRestaurant(rID).getLongitude();
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
			double[] centroidInLargestCluster = this.generateRandomCentroid(minLat, maxLat, minLon, maxLon);

			// Find the empty cluster and reassign its centroid by placing it somewhere in
			// the largest cluster
			for (double[] centroid : restaurantClusters.keySet()) {
				if (restaurantClusters.get(centroid).equals(emptyCluster)) {
					centroid[0] = centroidInLargestCluster[0]; // New longitude based on largest cluster
					centroid[1] = centroidInLargestCluster[1]; // New latitude based on largest cluster
					break; // Once we find the empty cluster, we are done
				}
			}

			// Flag is true if any restaurants are reassigned to a new centroid
			// If no restaurants are reassigned, then we are done one run through
			boolean flag;
			do {
				this.reassignCentroids(restaurantClusters);
				flag = reassignRestaurants(restaurantClusters, this.restaurantMap.keySet());
			} while (flag);

		} while (atLeastOneEmptyCluster(restaurantClusters)); // Repeat if any empty clusters are detected

		// Add final restaurant clusters to list of clusters
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
	 * @throws illegal
	 *             argument exception if there is insufficient data in the database
	 *             to create a valid predictor function
	 * @return a function that predicts the user's ratings for objects (of type
	 *         Restaurant) in the database of type MP5Db<Restaurant>. The function
	 *         that is returned takes two arguments: one is the database and other
	 *         other is a String that represents the id of an object of type
	 *         Restaurant that exists in the database. If there is insufficient
	 *         quantities of data in the database to create a predictor function, an
	 *         illegal argument exception is thrown.
	 */
	@Override
	public ToDoubleBiFunction<MP5Db<Restaurant>, String> getPredictorFunction(String user) {
		List<Integer> user_ratings = new ArrayList<Integer>(); // x
		List<Integer> restaurant_price = new ArrayList<Integer>(); // y

		// Construct two lists of user_ratings and restaurant_prices
		for (Review r : this.reviewMap.values()) {
			if (r.getUserId().equals(user)) {
				user_ratings.add(r.getStars());
				restaurant_price.add(this.restaurantMap.get(r.getBusinessId()).getPrice());
			}
		}

		double meanX = computeMean(restaurant_price);
		double meanY = computeMean(user_ratings);
		double Sxx = computeSxx(restaurant_price, meanX);
		double Sxy = computeSxy(restaurant_price, meanX, user_ratings, meanY);

		double b = Sxy / Sxx;

		// If predictor function does not contain enough data, throw runtime exception
		if (Double.isNaN(b)) {
			throw new IllegalArgumentException();
		}

		double a = meanY - b * meanX;

		ToDoubleBiFunction<MP5Db<Restaurant>, String> function = (database, restaurantID) -> {
			// Function logic
			double price = ((YelpDB) database).getRestaurant(restaurantID).getPrice();
			double ratingPrediction = a + b * price;
			if (ratingPrediction > 5) {
				return 5;
			} else if (ratingPrediction < 1) {
				return 1;
			} else {
				return ratingPrediction;
			}
		};

		return function;
	}

	/** Methods for Server **/

	/**
	 * Retrieves the JSON representation of a given restaurant, found by its ID
	 * 
	 * @param rID
	 * @return returns a string in JSON format of the restaurant info
	 */
	public String getRestaurantJSON(String rID) {
		return gson.toJson(this.restaurantMap.get(rID));
	}

	/**
	 * Attempts to add a new user to the database given user info in json format
	 * 
	 * @param jsonInfo
	 *            string in json format that represents new user
	 * @modifies this database by adding a new user if the new user info is valid
	 * @return the json format info of the newly added user
	 * @throws JsonSyntaxException
	 *             if jsonInfo is not in json format or if the name field is null
	 */
	public String addUserJSON(String jsonInfo) throws JsonSyntaxException {
		YelpUser user = gson.fromJson(jsonInfo, YelpUser.class);
		// Check if required fields (ie. name) are null
		if (user.getName() == null) {
			throw new JsonSyntaxException(jsonInfo);
		}
		user.generateNewUserInfo(this); // Add new user fields (such as user_id, url, votes, etc.)
		this.userMap.put(user.getID(), user); // Add the user to the database
		return gson.toJson(user); // Return jsonInfo of completed user (with all fields filled)
	}

	/**
	 * Attempts to add a new restaurant to the database given restaurant info in
	 * json format
	 * 
	 * @param jsonInfo
	 *            string in json format that represents new user
	 * @modifies this database by adding a new restaurant if the new restaurant info
	 *           is valid
	 * @return the json format info of the newly added restaurant
	 * @throws JsonSyntaxException
	 *             if jsonInfo is not in json format or if any required fields for
	 *             restaurant are null
	 */
	public String addRestaurantJSON(String jsonInfo) throws JsonSyntaxException {
		Restaurant rest = gson.fromJson(jsonInfo, Restaurant.class);
		// Check if any required fields are null
		if (rest.getName() == null || rest.getPrice() > 5 || rest.getPrice() < 1 || rest.getFullAddress() == null) {
			throw new JsonSyntaxException(jsonInfo);
		}
		rest.generateNewRestaurantInfo(this);
		this.restaurantMap.put(rest.getID(), rest);
		return gson.toJson(rest);
	}

	/**
	 * Attempts to add a new review to the database given review info in json format
	 * 
	 * @param jsonInfo
	 *            string in json format that represents new review
	 * @modifies this database by adding a new review if review is valid
	 * @return the json format info of the newly added review if valid. If the
	 *         review refers to a restaurant or user that does not exist in this
	 *         database, return a corresponding error message
	 * @throws JsonSyntaxException
	 *             if jsonInfo is not in proper json format or if any required
	 *             fields for review are null
	 */
	public String addReviewJSON(String jsonInfo) throws JsonSyntaxException {
		Review review = gson.fromJson(jsonInfo, Review.class);

		// If any required fields are null, throw a JsonSyntaxException (Invalid review
		// format)
		if (review.getText() == null || review.getUserId() == null || review.getBusinessId() == null
				|| review.getStars() > 5 || review.getStars() < 1) {
			throw new JsonSyntaxException(jsonInfo);
		}
		// We confirm if the review actually has a valid user id and restaurant id
		// associated with it
		else if (!this.restaurantMap.containsKey(review.getBusinessId())) {
			return "ERR: NO_SUCH_RESTAURANT";
		} else if (!this.userMap.containsKey(review.getUserId())) {
			return "ERR: NO_SUCH_USER";
		}
		// Valid json review with valid restaurant and user references
		else {
			review.generateNewReviewInfo(this); // Generate new review info
			this.reviewMap.put(review.getID(), review); // Add the review to our review map
			this.userMap.get(review.getUserId()).updateWithNewReview(review); // Update user info with new review info
			this.restaurantMap.get(review.getBusinessId()).updateWithNewReview(review); // Update restaurant info with
																						// new review info
			return gson.toJson(review);
		}
	}

	/**
	 * Performs a query search of restaurants on this database, based on parameters
	 * specified in query
	 * 
	 * @param query
	 *            a string that represents the characteristics of restaurant(s) we
	 *            want to look for. For querySearch to be successful, the query
	 *            string should be in proper grammatical format. One example of a
	 *            properly formatted string is: in(Telegraph Ave) &&
	 *            (category(Chinese) || category(Italian)) && price <= 2
	 * @return a string that represents the json info of any matching restaurants
	 *         specified by the query, or an error message if the query string is
	 *         invalid or no matching restaurants are found
	 */
	public String querySearch(String query) {
		Set<Restaurant> matching;
		try {
			matching = getMatches(query);
		} catch (IllegalArgumentException e) {
			return "ERR: INVALID_QUERY";
		}
		if (matching.isEmpty()) {
			return "ERR: NO_MATCH";
		} else {
			return gson.toJson(matching);
		}
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
	 * @param restaurantIDs
	 *            the set of restaurantIDs we wish to involve in cluster generation
	 * @return a map where the keys represent centroids and the values represent a
	 *         set of strings where each string represents one of the restaurants in
	 *         the database with its ID. each unique restaurant is mapped by exactly
	 *         one centroid
	 */
	private Map<double[], Set<String>> initiateClusters(int k, Set<String> restaurantIDs) {
		// centroids[0] is lattitude and centroids[1] is longitude for a centroid
		// Map assigns restaurants to a specific centroid
		Map<double[], Set<String>> restaurantClusters = new HashMap<double[], Set<String>>();

		// Find the min and max latitudes and longitudes
		double minLat = Double.MAX_VALUE;
		double minLon = Double.MAX_VALUE;
		double maxLat = -Double.MAX_VALUE;
		double maxLon = -Double.MAX_VALUE;

		for (String rID : restaurantIDs) {
			double lat = this.getRestaurant(rID).getLatitude();
			double lon = this.getRestaurant(rID).getLongitude();
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
			// generate random centroid based on min and max latitudes
			// and longitudes, with (currently) no assigned restaurants
			HashSet<String> emptySet = new HashSet<String>();
			restaurantClusters.put(this.generateRandomCentroid(minLat, maxLat, minLon, maxLon), emptySet);
		}

		// Assign each restaurant to its closest centroid

		for (String rID : restaurantIDs) {

			double[] newCentroid = null;
			double minDist = Double.MAX_VALUE;

			for (double[] centroid : restaurantClusters.keySet()) {
				double distance = this.computeDistance(centroid, this.getRestaurant(rID));
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
	private boolean reassignRestaurants(Map<double[], Set<String>> restaurantClusters, Set<String> restaurantIDs) {
		// For each restaurant, go through each centroid and find which one it belongs
		// to (old centroid)
		// At the same time, keep track of the distances for a potential new centroid
		// target
		boolean flag = false;

		for (String rID : restaurantIDs) {
			Restaurant r = this.getRestaurant(rID);

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
				Restaurant r = this.getRestaurant(rID);
				// Get the total value for lattitude and longitude for all restaurants
				// in the cluster
				totalLat += r.getLatitude();
				totalLon += r.getLongitude();
			}

			centroid[0] = totalLat / size;
			centroid[1] = totalLon / size;
		}
	}

	private boolean atLeastOneEmptyCluster(Map<double[], Set<String>> restaurantClusters) {
		for (Set<String> cluster : restaurantClusters.values()) {
			if (cluster.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Finds an empty cluster, if none exist return null
	 * 
	 * @param kMeansClusters
	 * @return
	 */
	private Set<String> findEmptyCluster(Map<double[], Set<String>> restaurantClusters) {
		for (Set<String> cluster : restaurantClusters.values()) {
			if (cluster.isEmpty()) {
				return cluster;
			}
		}
		return null;
	}

	/**
	 * Finds the largest cluster
	 * 
	 * @param kMeansClusters
	 * @return
	 */
	private Set<String> findLargestCluster(Map<double[], Set<String>> restaurantClusters) {
		int maxSize = 0;
		Set<String> largestCluster = null;
		for (Set<String> cluster : restaurantClusters.values()) {
			if (cluster.size() > maxSize) {
				largestCluster = cluster;
				maxSize = cluster.size();
			}
		}
		return largestCluster;
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
	 * Helper class to convert restaurant clusters to JSON format. Each cluster
	 * represents a restaurant and contains an identifier that defines which cluster
	 * the restaurant is in
	 */
	protected class RestaurantCluster {

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

	/**
	 * Generates a new user ID that does not yet exist in the database
	 * 
	 * @return the new user id
	 */
	protected String generateUserID() {
		Random r = new Random();
		String userID;
		do {
			int randomIDnum = r.nextInt(999999999) + 1;
			userID = String.valueOf(randomIDnum);
		} while (this.userMap.containsKey(userID));
		return userID;
	}

	/**
	 * Generates a new restaurant ID that does not yet exist in the database
	 * 
	 * @return the new restaurant id
	 */
	protected String generateRestaurantID() {
		Random r = new Random();
		String restID;
		do {
			int randomIDnum = r.nextInt(999999999) + 1;
			restID = String.valueOf(randomIDnum);
		} while (this.restaurantMap.containsKey(restID));
		return restID;
	}

	/**
	 * Generates a new review ID that does not yet exist in the database
	 * 
	 * @return the new restaurant id
	 */
	protected String generateReviewID() {
		Random r = new Random();
		String restID;
		do {
			int randomIDnum = r.nextInt(999999999) + 1;
			restID = String.valueOf(randomIDnum);
		} while (this.reviewMap.containsKey(restID));
		return restID;
	}
}