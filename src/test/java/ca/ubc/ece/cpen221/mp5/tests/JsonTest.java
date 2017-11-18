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
    public void test() {
        Gson gson = new Gson();

        User r1 = gson.fromJson("{\"url\": \"http://www.yelp.com/user_details?userid=_NH7Cpq3qZkByP5xR4gXog\", \"votes\": {\"funny\": 35, \"useful\": 21, \"cool\": 14}, \"review_count\": 29, \"type\": \"user\", \"user_id\": \"_NH7Cpq3qZkByP5xR4gXog\", \"name\": \"Chris M.\", \"average_stars\": 3.89655172413793}"
                ,User.class);
    }

}
