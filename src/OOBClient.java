import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class OOBClient {
    public static void main(String[] args) {
        String serverName = "localhost"; // Server name or IP
        int port = 1234; // Server port

        try {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);

            System.out.println("Connected to " + client.getRemoteSocketAddress());

            // Send OOB data
            client.sendUrgentData(0xFF);
            System.out.println("Sent urgent data.");

            client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
