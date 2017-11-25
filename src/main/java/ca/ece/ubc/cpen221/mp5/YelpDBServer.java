package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
    private YelpDB yelpDB; //Wraps a yelp database

    // Rep invariant: serverSocket != null
    //
    // Thread safety argument:
    // TODO FIBONACCI_PORT
    // TODO serverSocket
    // TODO socket objects
    // TODO readers and writers in handle()
    // TODO data in handle()

    /**
     * Make a FibonacciServerMulti that listens for connections on port.
     * 
     * @param port
     *            port number, requires 0 <= port <= 65535
     */
    public YelpDBServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.yelpDB = new YelpDB ("data/restaurants.json", "data/reviews.json", "data/users.json");
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
        BufferedReader in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));

        // similarly, wrap character=>bytestream converter around the
        // socket output stream, and wrap a PrintWriter around that so
        // that we have more convenient ways to write Java primitive
        // types to it.
        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                socket.getOutputStream()), true);

        try {
            // each request is a single line containing a number
            for (String line = in.readLine(); line != null; line = in
                    .readLine()) {
                System.err.println("request: " + line);
                try {
                    // compute answer and send back to client
                    
                    // Check what the request is and respond accordingly
                    String response = requestParser(line);
                    
                    
                    System.err.println("reply: " + response);
                    out.println(response);
                } catch (NumberFormatException e) {
                    // complain about ill-formatted request
                    System.err.println("reply: err");
                    out.print("err\n");
                }
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
     * @param input from client
     * @return a response from the server
     */
    public String requestParser(String input) {
        
        //Possible requests:
        //GETRESTAURANT <business id>
        
        //ADDUSER <user info>
        //ADDRESTAURANT <restaurant info>
        //ADDREVIEW <review info>
    	
    	int split = input.indexOf(" ");
    	String command = input.substring(0, split +1);
    	String response;
    	
    	if (command.equals("GETRESTAURANT")) {
    		String bID = input.substring(split +1, input.length() +1);  // is length +1 necessary?
    		response = yelpDB.getRestaurantJSON(bID);
    	}
    	else if (command.equals("ADDUSER")) {
    		
    	}
    	else if (command.equals("ADDRESTAURANT")) {
    		
    	}
    	else if (command.equals("ADDREVIEW")) {
    		
    	}
    	else {
    		response = "ERR: ILLEGAL_REQUEST";

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
