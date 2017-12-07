package ca.ece.ubc.cpen221.mp5;

import java.io.*;
import java.net.Socket;

/**
 * YelpDBClient is a client that sends requests to the YelpDBServer and
 * interprets its replies. A new YelpDBClient is "open" until the close()
 * method is called, at which point it is "closed" and may not be used further.
 */
public class YelpDBClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    // Rep invariant: socket, in, out != null

    /**
     * Make a YelpDBClient and connect it to a server running on hostname at the
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
     *            one of certain requests
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
     * @return the response from the server
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
     * Start a YelpDB Client
     */
    public static void main(String[] args) {
        try {
            int portNum = Integer.parseInt(args[0]);
            YelpDBClient client = new YelpDBClient("localhost", portNum);
            
            //Send requests here
            
            client.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
