package ca.ece.ubc.cpen221.mp5;

import java.util.Set;
import java.util.function.ToDoubleBiFunction;

public class YelpDB implements MP5Db{
    
    /**
     * Constructor
     * 
     * @param restaurants name of file for list of restaurants
     * @param reviews name of file for list of reviews
     * @param users name of file for list of users
     */
    public YelpDB (String restaurants, String reviews, String users) {
        
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
