package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.JsonSyntaxException;

public class YelpDBServer {

    /**
     * Abstraction Function: Represents a yelp data base server, which holds data
     * about restaurants, users, and reviews, and can handle specific requests from
     * clients that connect the server
     */

    /** Rep invariant **/
    private ServerSocket serverSocket; // serverSocket != null
    private YelpDB yelpDB; // Wraps a non-null yelp database

    /**
     * Make a YelpDBServer that listens for connections on port.
     * 
     * @param port
     *            port number, requires 0 <= port <= 65535
     */
    public YelpDBServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.yelpDB = new YelpDB("data/restaurants.json", "data/reviews.json", "data/users.json");
    }

    /**
     * Run the server, listening for connections and handling them.
     * 
     * @throws IOException
     *             if the main server socket is broken
     */
    public void serve() throws IOException {
        while (true) {
            // block until a client connects
            final Socket socket = serverSocket.accept();
            // create a new thread to handle that client
            Thread handler = new Thread(new Runnable() {
                public void run() {
                    try {
                        try {
                            handle(socket);
                        } finally {
                            socket.close();
                        }
                    } catch (IOException ioe) {
                        // this exception wouldn't terminate serve(),
                        // since we're now on a different thread, but
                        // we still need to handle it
                        ioe.printStackTrace();
                    }
                }
            });
            // start the thread
            handler.start();
        }
    }

    /**
     * Handle one client connection. Returns when client disconnects.
     * 
     * @param socket
     *            socket where client is connected
     * @throws IOException
     *             if connection encounters an error
     */
    private void handle(Socket socket) throws IOException {
        System.err.println("client connected");

        // get the socket's input stream, and wrap converters around it
        // that convert it from a byte stream to a character stream,
        // and that buffer it so that we can read a line at a time
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // similarly, wrap character=>bytestream converter around the
        // socket output stream, and wrap a PrintWriter around that so
        // that we have more convenient ways to write Java primitive
        // types to it.
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        try {
            // each request is a single line containing a number
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                System.err.println("request: " + line);

                // Check what the request is and respond accordingly
                String response = requestParser(line);
                System.err.println("reply: " + response);
                out.println(response);

                // important! our PrintWriter is auto-flushing, but if it were
                // not:
                // out.flush();
            }
        } finally {
            out.close();
            in.close();
        }
    }

    /**
     * Parses a request from a client (there are 5 different possible requests)
     * 
     * @param request
     *            from client
     * @return a response from the server, depending on the request
     */
    public String requestParser(String request) {

        request = request.trim();
        String[] words = request.split(" +", 2); // Split client request into two words

        String command = words[0];
        String data = words[1];
        //andrew
    //    data = data.replaceAll(" ", "");
        //end
        String response = "ERR: ILLEGAL_REQUEST"; // Default response

        // Check the command to execute a method accordingly
        if (command.equals("GETRESTAURANT")) {
            response = yelpDB.getRestaurantJSON(data);
        } else if (command.equals("GETUSER")) {
            response = yelpDB.getUserJSON(data); // For testing
        } else if (command.equals("GETREVIEW")) {
            response = yelpDB.getReviewJSON(data); // For testing
        } else if (command.equals("ADDUSER")) {
            try {
                response = yelpDB.addUserJSON(data);
            } catch (JsonSyntaxException e) {
                response = "ERR: INVALID_USER_STRING";
            }
            //andrew
        	if (data.contains("\"name\":\"\"")) {	//have to use '.contains' because the param can contain extra inputs which have empty strings (i.e. "user_id: """)
        		response = "ERR: INVALID_USER_STRING";
        	}
        	//end
        } else if (command.equals("ADDRESTAURANT")) {
            try {
                response = yelpDB.addRestaurantJSON(data);
            } catch (JsonSyntaxException e) {
                response = "ERR: INVALID_RESTAURANT_STRING";
            }
            //andrew
            if (data.contains("\"full_address\":\"\"") || data.contains("\"name\":\"\"")) {	//if the price param is empty or does not contain an integer it will fail
            	response = "ERR: INVALID_RESTAURANT_STRING";
            }
            //end
        } else if (command.equals("ADDREVIEW")) {
            try {
                response = yelpDB.addReviewJSON(data);
            } catch (JsonSyntaxException e) {
                response = "ERR: INVALID_REVIEW_STRING";
            }
            //andrew
   //         if (data.contains("\"text\":\"\"")) {
    //        	response = "ERR: INVALID_REVIEW_STRING";
     //       }
            //end
        } else if (command.equals("QUERY")) {
            response = yelpDB.querySearch(data);
        }
        return response;
    }

    /**
     * Start a YelpDBServer running on some port.
     */
    public static void main(String[] args) {
        try {
            int portNum = Integer.parseInt(args[0]);
            YelpDBServer server = new YelpDBServer(portNum);
            server.serve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
