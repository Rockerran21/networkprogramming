import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5001);
        System.out.println("Server started and listening for connections.");
        while (true) {
            Socket socket = serverSocket.accept();  // Accepts a connection from a client
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {  // Read message from the client
                System.out.println("Received from client: " + line);
                out.println("Echo: " + line);  // Echoes back the received message
            }
            socket.close();
        }
    }
}
