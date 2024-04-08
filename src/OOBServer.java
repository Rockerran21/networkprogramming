import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class OOBServer {
    public static void main(String[] args) {
        int port = 1234; // Server port

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port " + port);

            // Accept client connections
            Socket server = serverSocket.accept();
            System.out.println("Connected to " + server.getRemoteSocketAddress());

            // ServerSocket does not have a built-in mechanism to handle OOB data distinctly
            // Additional logic would be needed to properly parse and act on OOB data

            server.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
