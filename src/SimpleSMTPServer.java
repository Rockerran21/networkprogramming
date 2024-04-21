import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSMTPServer {
    public static void main(String[] args) {
        int port = 2525; // Port to listen on

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("SMTP Server started on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    System.out.println("Client connected: " + clientSocket.getInetAddress());

                    // Initial greeting
                    out.println("220 localhost Simple SMTP Server");

                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println("Received: " + line);
                        if (line.startsWith("HELO") || line.startsWith("EHLO")) {
                            out.println("250-Hello " + line.substring(5) + ", pleased to meet you");
                        } else if (line.startsWith("MAIL FROM:")) {
                            out.println("250 Sender OK");
                        } else if (line.startsWith("RCPT TO:")) {
                            out.println("250 Recipient OK");
                        } else if (line.startsWith("DATA")) {
                            out.println("354 Start mail input; end with <CRLF>.<CRLF>");
                        } else if (line.equals(".")) {
                            out.println("250 Message accepted for delivery");
                        } else if (line.equals("QUIT")) {
                            out.println("221 Bye");
                            break;
                        } else {
                            out.println("250 OK");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("Could not listen on port " + port);
            e.printStackTrace();
        }
    }
}
