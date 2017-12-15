package ca.ece.ubc.cpen221.mp5.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.gson.*;

import static org.junit.Assert.*;

import ca.ece.ubc.cpen221.mp5.*;

public class ServerTest {
    
    Gson gson = new Gson();

    Thread testServer = new Thread (new Runnable() {
        public void run() {
            try {
                YelpDBServer server = new YelpDBServer(7777);
                server.serve();
            } catch (IOException e) {
                    
            }
        }      
    });

    @Test
    public void testGetRestaurant1() throws IOException {

        testServer.start();
        
        YelpDBClient client = new YelpDBClient("localhost", 7777);
        client.sendRequest("GETRESTAURANT h_we4E3zofRTf4G0JTEF0A");
        String reply = client.getReply();
        
        assertTrue(reply.contains("\"business_id\":\"h_we4E3zofRTf4G0JTEF0A\""));
        assertTrue(reply.contains("\"name\":\"Fondue Fred\""));
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
    
    @Test
    public void testGetRestaurant2() throws IOException {

        testServer.start();
        
        YelpDBClient client = new YelpDBClient("localhost", 7777);
        client.sendRequest("GETRESTAURANT BJKIoQa5N2T_oDlLVf467Q");
        String reply = client.getReply();
        
        assertTrue(reply.contains("\"business_id\":\"BJKIoQa5N2T_oDlLVf467Q\""));
        assertTrue(reply.contains("\"name\":\"Jasmine Thai\""));
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
    
    @Test
    public void testGetRestaurant3() throws IOException {

        testServer.start();
        
        YelpDBClient client = new YelpDBClient("localhost", 7777);
        client.sendRequest("GETRESTAURANT INVALID");
        String reply = client.getReply();
        
        assertEquals(reply, "ERR: NO_SUCH_RESTAURANT");
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
    
    @Test
    public void testAddUser1() throws IOException {

        testServer.start();
        
        YelpDBClient client = new YelpDBClient("localhost", 7777);
        client.sendRequest("ADDUSER {\"name\": \"Jim\"}");
        String reply1 = client.getReply();
        
        assertTrue(reply1.contains("\"name\":\"Jim\""));
        assertTrue(reply1.contains("\"review_count\":0"));
        assertTrue(reply1.contains("\"average_stars\":0.0"));
        
        /* Confirm that user was added into database */
        YelpUser u = gson.fromJson(reply1, YelpUser.class);
        String addedID = u.getID(); // ID of newly added user
        
        client.sendRequest("GETUSER " + addedID);
        String reply2 = client.getReply();
        
        assertTrue(reply2.contains("\"name\":\"Jim\""));
        assertTrue(reply2.contains("\"review_count\":0"));
        assertTrue(reply2.contains("\"average_stars\":0.0"));
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
    
    @Test
    public void testAddUserExtraInfo() throws IOException {

        testServer.start();
        
        YelpDBClient client = new YelpDBClient("localhost", 7777);
        client.sendRequest("ADDUSER {\"name\": \"qwerty123\",\"review_count\": 5,\"extra\": \"\",\"another_one\":5}");
        String reply1 = client.getReply();
        
        assertTrue(reply1.contains("\"name\":\"qwerty123\""));
        assertTrue(reply1.contains("\"review_count\":0"));
        assertTrue(reply1.contains("\"average_stars\":0.0"));
        assertFalse(reply1.contains("\"extra\":\"\""));
        assertFalse(reply1.contains("\"another_one\":5"));
        
        /* Confirm that user was added into database */
        YelpUser u = gson.fromJson(reply1, YelpUser.class);
        String addedID = u.getID(); // ID of newly added user
        
        client.sendRequest("GETUSER " + addedID);
        String reply2 = client.getReply();
        
        assertTrue(reply2.contains("\"name\":\"qwerty123\""));
        assertTrue(reply2.contains("\"review_count\":0"));
        assertTrue(reply2.contains("\"average_stars\":0.0"));
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
    
    @Test
    public void testAddUserNO_NAME() throws IOException {
//THIS ONE DOESNT ACTUALLY WORK. IF YOU INSERT " " BETWEEN : AND " LINE 150 IT FAILS
        testServer.start();
        
        YelpDBClient client = new YelpDBClient("localhost", 7777);
        client.sendRequest("ADDUSER {\"name\":\"\"}");
        String reply1 = client.getReply();
        
        assertEquals(reply1, "ERR: INVALID_USER_STRING");
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
    
    @Test
    public void testAddUserInvalid() throws IOException {

        testServer.start();
        
        YelpDBClient client = new YelpDBClient("localhost", 7777);
        client.sendRequest("ADDUSER {\"nam\": \"Jim\"}");
        String reply1 = client.getReply();
        
        assertEquals(reply1, "ERR: INVALID_USER_STRING");
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
    
    @Test
    public void testAddRestaurant1() throws IOException {

        testServer.start();
        
        YelpDBClient client = new YelpDBClient("localhost", 7777);
        client.sendRequest("ADDRESTAURANT {\"name\":\"McDonalds\",\"full_address\":\"123 Wimbo Street\",\"price\":3}");
        String reply1 = client.getReply();
        
        assertTrue(reply1.contains("\"name\":\"McDonalds\""));
        assertTrue(reply1.contains("\"full_address\":\"123 Wimbo Street\""));
        assertTrue(reply1.contains("\"price\":3"));
        
        /* Confirm that restaurant was added into database */
        Restaurant r = gson.fromJson(reply1, Restaurant.class);
        String addedID = r.getID(); // ID of newly added user
        
        client.sendRequest("GETRESTAURANT " + addedID);
        String reply2 = client.getReply();       
        
        assertTrue(reply2.contains("\"name\":\"McDonalds\""));
        assertTrue(reply2.contains("\"full_address\":\"123 Wimbo Street\""));
        assertTrue(reply2.contains("\"price\":3"));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
    
    @Test
    public void testAddRestaurant2() throws IOException {

        testServer.start();
        
        YelpDBClient client = new YelpDBClient("localhost", 7777);
        client.sendRequest("ADDRESTAURANT {\"full_address\":\"123 Wimbo Street\",\"price\":3,\"name\":\"McDonalds\"}");
        String reply1 = client.getReply();
        
        assertTrue(reply1.contains("\"name\":\"McDonalds\""));
        assertTrue(reply1.contains("\"full_address\":\"123 Wimbo Street\""));
        assertTrue(reply1.contains("\"price\":3"));
        
        /* Confirm that restaurant was added into database */
        Restaurant r = gson.fromJson(reply1, Restaurant.class);
        String addedID = r.getID(); // ID of newly added user
        
        client.sendRequest("GETRESTAURANT " + addedID);
        String reply2 = client.getReply();       
        
        assertTrue(reply2.contains("\"name\":\"McDonalds\""));
        assertTrue(reply2.contains("\"full_address\":\"123 Wimbo Street\""));
        assertTrue(reply2.contains("\"price\":3"));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
    @Test
    public void testAddRestaurantMissingInfo() throws IOException {

        testServer.start();
        
        YelpDBClient client = new YelpDBClient("localhost", 7777);
        client.sendRequest("ADDRESTAURANT {\"full_address\":\"123 Wimbo Street\",\"name\":\"McDonalds\"}");
        String reply1 = client.getReply();
        
        assertEquals(reply1, "ERR: INVALID_RESTAURANT_STRING");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
    @Test
    public void testAddRestaurantInvalidJSon() throws IOException {

        testServer.start();
        
        YelpDBClient client = new YelpDBClient("localhost", 7777);
        client.sendRequest("ADDRESTAURANT {\"full_addres\":\"123 Wimbo Street\",\"price\":3,\"name\":\"McDonalds\"}");
        String reply1 = client.getReply();
       
        assertEquals(reply1, "ERR: INVALID_RESTAURANT_STRING");
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
    @Test
    public void testAddRestaurantEmptyString() throws IOException {

        testServer.start();
        
        YelpDBClient client = new YelpDBClient("localhost", 7777);
        client.sendRequest("ADDRESTAURANT {\"full_address\":\"\",\"price\":3,\"name\":\"McDonalds\"}");
        String reply1 = client.getReply();
       
        assertEquals(reply1, "ERR: INVALID_RESTAURANT_STRING");
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
    @Test
    public void testAddRestaurantInvalidPrice() throws IOException {

        testServer.start();
        
        YelpDBClient client = new YelpDBClient("localhost", 7777);
        client.sendRequest("ADDRESTAURANT {\"full_address\":\"123 Wimbo Street\",\"name\":\"McDonalds\",\"price\":6}");
        String reply1 = client.getReply();
        
        assertEquals(reply1, "ERR: INVALID_RESTAURANT_STRING");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
    
    @Test
    public void testAddReview1() throws IOException {

        testServer.start();
        YelpDBClient client = new YelpDBClient("localhost", 7777);
        
        /*Confirm initial average stars and review count of user and restaurant*/
        
        client.sendRequest("GETUSER _NH7Cpq3qZkByP5xR4gXog");
        String initialUserInfo = client.getReply();
        assertTrue(initialUserInfo.contains("\"review_count\":29"));
        assertTrue(initialUserInfo.contains("\"average_stars\":3.89")); //Use truncation to confirm
        
        client.sendRequest("GETRESTAURANT gclB3ED6uk6viWlolSb_uA");
        String initialRestaurantInfo = client.getReply();
        assertTrue(initialRestaurantInfo.contains("\"review_count\":9"));
        assertTrue(initialRestaurantInfo.contains("\"stars\":2.0"));    
        
        client.sendRequest("ADDREVIEW {\"business_id\":\"gclB3ED6uk6viWlolSb_uA\",\"user_id\":\"_NH7Cpq3qZkByP5xR4gXog\",\"text\":\"Good cafe!\",\"stars\":4}");
        String confirmReply = client.getReply();
                
        assertTrue(confirmReply.contains("\"text\":\"Good cafe!\""));
        assertTrue(confirmReply.contains("\"stars\":4"));
        
        /* Confirm that review was added into database */
        Review rev = gson.fromJson(confirmReply, Review.class);
        String addedID = rev.getID(); // ID of newly added user
        
        client.sendRequest("GETREVIEW " + addedID);
        String reviewReply = client.getReply();       
        
        assertTrue(reviewReply.contains("\"text\":\"Good cafe!\""));
        assertTrue(reviewReply.contains("\"stars\":4"));
        
        /* Confirm that corresponding user and restaurant in database was updated correctly */
        
        client.sendRequest("GETUSER _NH7Cpq3qZkByP5xR4gXog");
        String updatedUserInfo = client.getReply();
        assertTrue(updatedUserInfo.contains("\"review_count\":30"));
        assertTrue(updatedUserInfo.contains("\"average_stars\":3.89"));
        
        client.sendRequest("GETRESTAURANT gclB3ED6uk6viWlolSb_uA");
        String updatedRestaurantInfo = client.getReply();
        assertTrue(updatedRestaurantInfo.contains("\"review_count\":10"));
        assertTrue(updatedRestaurantInfo.contains("\"stars\":2.2"));   

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
    
    @Test
    public void testQuery1() throws IOException {

        testServer.start();
        
        YelpDBClient client = new YelpDBClient("localhost", 7777);
        client.sendRequest("QUERY in(Telegraph Ave) && (category(Chinese) || category(Western)) && price <= 2");
        String reply = client.getReply();
        
        System.out.println(reply);
        
        assertTrue(reply.contains("\"business_id\":\"ERRowW4pGO6pK9sVYyA1nQ\""));
        assertTrue(reply.contains("\"name\":\"Happy Valley\""));
        
        assertTrue(reply.contains("\"business_id\":\"_mv3DhRD3L3okFXYjxX_Cg\""));
        assertTrue(reply.contains("\"name\":\"Chang Luong Restaurant\""));
        
        assertTrue(reply.contains("\"business_id\":\"1E2MQLWfwpsId185Fs2gWw\""));
        assertTrue(reply.contains("\"name\":\"Peking Express\""));
        
        assertTrue(reply.contains("\"business_id\":\"XBPMMfMchDlxZG-qSsSdtw\""));
        assertTrue(reply.contains("\"name\":\"Lotus House\""));
        
        assertTrue(reply.contains("\"business_id\":\"5fneYCWLhgBZQUcNPOch-w\""));
        assertTrue(reply.contains("\"name\":\"Mandarin House\""));
        
        assertTrue(reply.contains("\"business_id\":\"8Xq5VtwYjayKlxEY2PipQA\""));
        assertTrue(reply.contains("\"name\":\"Sun Hong Kong Restaurant\""));
        
        assertTrue(reply.contains("\"business_id\":\"t-xuA4yR02gud00gTS2iyw\""));
        assertTrue(reply.contains("\"name\":\"Chinese Express\""));
        
        assertFalse(reply.contains("\"price\":3"));
        assertFalse(reply.contains("\"price\":4"));
        assertFalse(reply.contains("\"price\":5"));
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void testQueryINVALID_QUERY() throws IOException {

        testServer.start();
        
        YelpDBClient client = new YelpDBClient("localhost", 7777);
        client.sendRequest("QUERY in(Telegraph Ave) && (categoy(Chinese) || category(Western)) && price <= 2");
        String reply = client.getReply();
        
        System.out.println(reply);
        assertEquals(reply, "ERR: INVALID_QUERY");
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
    @Test
    public void testQueryNO_MATCH() throws IOException {

        testServer.start();
        
        YelpDBClient client = new YelpDBClient("localhost", 7777);
        client.sendRequest("QUERY price > 5");
        String reply = client.getReply();
        
        System.out.println(reply);
        assertEquals(reply, "ERR: NO_MATCH");
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }
}
