import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SimpleSMTPClient {
    public static void main(String[] args) {
        // SMTP server information
        String host = "localhost";
        int port = 2525;
        String from = "your-email@example.com";
        String to = "recipient-email@example.com";

        try {
            // Connect to the SMTP server
            Socket socket = new Socket(host, port);
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read the initial response from the server
            System.out.println("Server: " + br.readLine());

            // HELO command
            sendCommand(osw, br, "HELO " + host);

            // MAIL FROM command
            sendCommand(osw, br, "MAIL FROM:<" + from + ">");

            // RCPT TO command
            sendCommand(osw, br, "RCPT TO:<" + to + ">");

            // DATA command
            sendCommand(osw, br, "DATA");

            // Email content
            osw.write("Subject: Test Email from Simple SMTP Client\r\n");
            osw.write("This is a test email sent from a simple Java SMTP client.\r\n.");
            osw.write("\r\n.\r\n");
            osw.flush();
            System.out.println("Server: " + br.readLine());

            // QUIT command
            sendCommand(osw, br, "QUIT");

            // Close the socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendCommand(OutputStreamWriter osw, BufferedReader br, String command) throws Exception {
        osw.write(command + "\r\n");
        osw.flush();
        System.out.println("Server: " + br.readLine());
    }
}
