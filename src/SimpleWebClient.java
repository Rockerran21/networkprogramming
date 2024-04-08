import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleWebClient {

    public static void main(String[] args) {
        // Corrected hostname and port
        String hostname = "127.0.0.1"; // IP address without port and trailing slash
        int port = 8000; // Corrected port number to match your comment

        try (Socket socket = new Socket(hostname, port)) {
            // Send an HTTP GET request
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("GET / HTTP/1.1");
            out.println("Host: " + hostname + ":" + port); // Include port in Host header if not default
            out.println("Connection: Close");
            out.println(); // HTTP request headers should end with a blank line

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
