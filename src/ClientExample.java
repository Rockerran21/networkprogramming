import java.io.*;
import java.net.*;

public class ClientExample {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 1234);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        out.println("Hello, Server!");
        socket.close();
    }
}
