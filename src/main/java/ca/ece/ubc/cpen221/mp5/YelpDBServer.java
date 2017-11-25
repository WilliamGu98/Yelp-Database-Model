package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.JsonSyntaxException;

/**
 * FibonacciServerMulti is a server that finds the n^th Fibonacci number given
 * n. It accepts requests of the form: Request ::= Number "\n" Number ::= [0-9]+
 * and for each request, returns a reply of the form: Reply ::= (Number | "err")
 * "\n" where a Number is the request Fibonacci number, or "err" is used to
 * indicate a misformatted request. FinbonacciServerMulti can handle multiple
 * concurrent clients.
 */
public class YelpDBServer {

    private ServerSocket serverSocket;
    private YelpDB yelpDB; // Wraps a yelp database

    // Rep invariant: serverSocket != null

    /**
     * Make a FibonacciServerMulti that listens for connections on port.
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
     * @param input
     *            from client
     * @return a response from the server
     */
    public String requestParser(String input) {

        input = input.trim();
        String[] words = input.split(" +");

        String command = words[0];
        String jsonInfo = words[1];
        String response = "ERR: ILLEGAL_REQUEST";

        if (command.equals("GETRESTAURANT")) {
            response = yelpDB.getRestaurantJSON(jsonInfo);
        } else if (command.equals("ADDUSER")) {
            try {
                response = yelpDB.addUserJSON(jsonInfo);
            } catch (JsonSyntaxException e) {
                response = "ERR: INVALID_USER_STRING";
            }
        } else if (command.equals("ADDRESTAURANT")) {
            try {
                response = yelpDB.addRestaurantJSON(jsonInfo);
            } catch (JsonSyntaxException e) {
                response = "ERR: INVALID_RESTAURANT_STRING";
            }
        } else if (command.equals("ADDREVIEW")) {
            response = null; //TODO
        } else if (command.equals("QUERY")) {
            response = null; //TODO
        }
 
        return response;
    }

    /**
     * Start a FibonacciServerMulti running on some port.
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
