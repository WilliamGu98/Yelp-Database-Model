package ca.ubc.ece.cpen221.mp5.tests;

import ca.ece.ubc.cpen221.mp5.*;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

public class JsonTest {

    @Test
    public void test() throws IOException{
        YelpDB db = new YelpDB ("data/restaurants.json", "data/reviews.json","data/users.json");

        
    }

}
