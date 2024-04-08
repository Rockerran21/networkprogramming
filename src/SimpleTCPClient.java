import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SimpleTCPClient {
    public static void main(String[] args) {
        // Server's IP address and the port
        String serverAddress = "localhost";
        int port = 5000;

        try {
            // Connect to the server
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(serverAddress, port));
            System.out.println("Connected to the server.");

            // Sending a message to the server
            String message = "Hello from the client!";
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
            socketChannel.write(buffer);
            System.out.println("Message sent to the server.");

            // Clearing the buffer for reading the response
            buffer.clear();

            // Optionally: Reading the response from the server
            socketChannel.read(buffer);
            String response = new String(buffer.array()).trim();
            System.out.println("Response from the server: " + response);

            // Closing the connection
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
