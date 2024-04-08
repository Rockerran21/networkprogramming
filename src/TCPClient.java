import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        String serverAddress = "127.0.0.1";  // Server address
        Socket socket = new Socket(serverAddress, 5001);  // Connect to the server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.println("Hello Server!");  // Send a message to the server
        String response = in.readLine();  // Read the response from the server
        System.out.println("Server says: " + response);
        socket.close();
    }
}
