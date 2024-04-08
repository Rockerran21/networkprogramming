import java.io.*;
import java.net.*;

public class ServerExample {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server is listening on port 1234");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected");

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String message = in.readLine();
        System.out.println("Received from client: " + message);

        clientSocket.close();
        serverSocket.close();
    }
}
