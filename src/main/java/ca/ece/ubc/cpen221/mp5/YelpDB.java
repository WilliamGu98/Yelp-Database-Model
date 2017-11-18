package ca.ece.ubc.cpen221.mp5;

import java.util.Set;
import java.util.function.ToDoubleBiFunction;
import com.google.gson.*;

public class YelpDB implements MP5Db {
	
	private Set<Restaurant> restaurantSet;
	private Set<Review> reviewSet;
	private Set<User> userSet;

	public YelpDB(String restaurantList, String reviewList, String userList) {
	
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
	public ToDoubleBiFunction<MP5Db, String> getPredictorFunction(String user) {
		// TODO Auto-generated method stub
		return null;
	}

}
