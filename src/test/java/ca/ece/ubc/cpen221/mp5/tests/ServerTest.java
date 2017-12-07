package ca.ece.ubc.cpen221.mp5.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import static org.junit.Assert.*;

import ca.ece.ubc.cpen221.mp5.*;

public class ServerTest {

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
        
        //assertEquals(reply, "hey");
        assertTrue(reply.contains("\"business_id\":\"h_we4E3zofRTf4G0JTEF0A\""));
        assertTrue(reply.contains("\"name\":\"Fondue Fred\""));
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
        
    }

}
