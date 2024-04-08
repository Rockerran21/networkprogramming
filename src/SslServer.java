import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import java.io.OutputStream;
import java.io.PrintWriter;

public class SslServer {
    public static void main(String[] args) throws Exception {
        SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(8443);

        System.out.println("Server started and waiting for connections...");

        while (true) {
            try (SSLSocket clientSocket = (SSLSocket) serverSocket.accept();
                 OutputStream out = clientSocket.getOutputStream();
                 PrintWriter writer = new PrintWriter(out, true)) {

                writer.println("Hello, this is the server speaking!");
                // Handle client in a separate thread or here directly
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
