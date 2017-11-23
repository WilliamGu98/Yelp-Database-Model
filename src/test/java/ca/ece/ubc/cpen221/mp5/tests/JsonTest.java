package ca.ece.ubc.cpen221.mp5.tests;

import ca.ece.ubc.cpen221.mp5.*;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.function.ToDoubleBiFunction;

public class JsonTest {

    @Test
    public void test() throws IOException{
        YelpDB db = new YelpDB ("data/restaurants.json", "data/reviews.json","data/users.json");
        ToDoubleBiFunction<String,YelpDB> func = db.getPredictorFunction("QScfKdcxsa7t5qfE0Ev0Cw");
        
        /*
        System.out.println(func.applyAsDouble("BJKIoQa5N2T_oDlLVf467Q", db)); //Price is 2
        System.out.println(func.applyAsDouble("h_we4E3zofRTf4G0JTEF0A", db)); //Price is 3
        System.out.println(func.applyAsDouble("sxIPX4ZAipVl3ZCkkqXqZw", db)); //Price is 4*/
        
        System.out.println(db.kMeansClusters_json(1));
    }
}
