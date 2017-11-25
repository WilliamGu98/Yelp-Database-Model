package ca.ece.ubc.cpen221.mp5.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.*;

import java.io.*;
import java.util.*;
import java.util.function.ToDoubleBiFunction;

import com.google.gson.*;

public class JsonTest {

    //@Test
    public void test() throws IOException{
        YelpDB db = new YelpDB ("data/restaurants.json", "data/reviews.json", "data/users.json");
        ToDoubleBiFunction<String,YelpDB> func = db.getPredictorFunction("QScfKdcxsa7t5qfE0Ev0Cw");
        
        /*
        System.out.println(func.applyAsDouble("BJKIoQa5N2T_oDlLVf467Q", db)); //Price is 2
        System.out.println(func.applyAsDouble("h_we4E3zofRTf4G0JTEF0A", db)); //Price is 3
        System.out.println(func.applyAsDouble("sxIPX4ZAipVl3ZCkkqXqZw", db)); //Price is 4*/
        
        System.out.println(db.kMeansClusters_json(5));
    }
    
    //@Test
    public void testJSON() throws IOException{
        System.out.println("\"name\": \"asdf\"");
        Gson gson = new Gson();
        YelpUser u = gson.fromJson("{\"name\": \"asdf\"}", YelpUser.class);
        System.out.println(u.getName());
    }
    
    @Test
    public void testServerStuff() throws IOException{
        YelpDBServer serv = new YelpDBServer(7777);
        System.out.println(serv.requestParser("ADDUSER {\"name\": \"Jim\"}"));
    }
}
