package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.Socket;

/**
 * FibonacciClient is a client that sends requests to the FibonacciServer and
 * interprets its replies. A new FibonacciClient is "open" until the close()
 * method is called, at which point it is "closed" and may not be used further.
 */
public class YelpDBClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    // Rep invariant: socket, in, out != null

    /**
     * Make a FibonacciClient and connect it to a server running on hostname at the
     * specified port.
     * 
     * @throws IOException
     *             if can't connect
     */
    public YelpDBClient(String hostname, int port) throws IOException {
        socket = new Socket(hostname, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    /**
     * Send a request to the server. Requires this is "open".
     * 
     * @param x
     *            to find Fibonacci(x)
     * @throws IOException
     *             if network or server failure
     */
    public void sendRequest(String x) throws IOException {
        out.print(x + "\n");
        out.flush(); // important! make sure x actually gets sent
    }

    /**
     * Get a reply from the next request that was submitted. Requires this is
     * "open".
     * 
     * @return the requested Fibonacci number
     * @throws IOException
     *             if network or server failure
     */
    public String getReply() throws IOException {
        String reply = in.readLine();
        if (reply == null) {
            throw new IOException("connection terminated unexpectedly");
        }
        return reply;
    }

    /**
     * Closes the client's connection to the server. This client is now "closed".
     * Requires this is "open".
     * 
     * @throws IOException
     *             if close fails
     */
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    /**
     * Use a FibonacciServer to find the first N Fibonacci numbers.
     */
    public static void main(String[] args) {
        try {
            int portNum = Integer.parseInt(args[0]);
            YelpDBClient client = new YelpDBClient("localhost", portNum);

            String request1 = "GETRESTAURANT h_we4E3zofRTf4G0JTEF0A";
            String request2 = "ADDUSER {\"name\": \"Jim\"}";
            String request3 = "ADDRESTAURANT {\"name\": \"Bob's burgers\"}"; // Expect an error b/c no address
            String request4 = "ADDREVIEW {\"business_id\": \"2u4DSD6F8RyFXp-Crhj8OA\", \"user_id\": \"7FcpuLQHNFA9xz8B3PGYNg\", \"text\": \"good burgers\", \"stars\": 2}";

            client.sendRequest(request1);
            System.out.println(request1);
            String reply1 = client.getReply();
            System.out.println(reply1);
            System.out.println();

            client.sendRequest(request2);
            System.out.println(request2);
            String reply2 = client.getReply();
            System.out.println(reply2);
            System.out.println();

            client.sendRequest(request3);
            System.out.println(request3);
            String reply3 = client.getReply();
            System.out.println(reply3);
            System.out.println();

            client.sendRequest(request4);
            System.out.println(request4);
            String reply4 = client.getReply();
            System.out.println(reply4);
            System.out.println();

            client.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
