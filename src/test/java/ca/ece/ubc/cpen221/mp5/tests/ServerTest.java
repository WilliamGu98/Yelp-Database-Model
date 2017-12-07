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
    public void test1() throws IOException {
        
        Thread testClient = new Thread (new Runnable() {
            public void run() {
                try {
                    YelpDBClient client = new YelpDBClient("localhost", 7777);
                    client.sendRequest("GETRESTAURANT h_we4E3zofRTf4G0JTEF0A");
                    String reply = client.getReply();
                    System.out.println(reply);
                } catch (IOException e) {
                        
                }
            }      
        });

        testServer.start();
        testClient.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }

}
