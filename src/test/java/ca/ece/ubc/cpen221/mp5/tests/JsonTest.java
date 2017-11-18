package ca.ece.ubc.cpen221.mp5.tests;

import ca.ece.ubc.cpen221.mp5.*;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.function.ToDoubleBiFunction;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

public class JsonTest {

    @Test
    public void test() throws IOException{
        YelpDB db = new YelpDB ("data/restaurants.json", "data/reviews.json","data/users.json");
        ToDoubleBiFunction func = db.getPredictorFunction("QScfKdcxsa7t5qfE0Ev0Cw");
        
        System.out.println(func.applyAsDouble("BJKIoQa5N2T_oDlLVf467Q", db)); //Price is 2
    }
    
    
    //@Test
    public void test1() throws IOException{
        List<StringBuilder> list1 = new ArrayList<StringBuilder>();
        StringBuilder one = new StringBuilder("1");
        StringBuilder two = new StringBuilder("2");
        list1.add(one);
        list1.add(two);
        
        System.out.println(list1);
        List<StringBuilder> list2 = new ArrayList<StringBuilder>(list1);
        System.out.println(list2);
        
        one.append("3");
        System.out.println(list1);
        list2.add(new StringBuilder("4"));
        System.out.println(list2); 
    }

}
