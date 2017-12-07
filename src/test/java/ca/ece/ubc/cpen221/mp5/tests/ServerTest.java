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

}
