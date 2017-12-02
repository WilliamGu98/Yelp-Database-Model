package ca.ece.ubc.cpen221.mp5.tests;

import java.io.IOException;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.*;

public class ServerTest {

    Thread testServer = new Thread(new Runnable() {
        public void run() {
            YelpDBServer.main(new String[] { "7777" });
        }
    });

    Thread testClient = new Thread(new Runnable() {
        public void run() {
            YelpDBClient.main(new String[] { "7777" });
        }
    });

    @Test
    public void test() throws IOException {

        testServer.start();
        testClient.start();

    }

}
