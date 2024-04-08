import java.io.IOException;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class SimpleWebServer {
    public static void main(String[] args) throws Exception {
        // Configure the port
        int port = 8000;

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new MyHandler(port));
        server.setExecutor(null);
        server.start();
        System.out.println("Server is listening on port " + port);
    }

    static class MyHandler implements HttpHandler {
        private int port;

        public MyHandler(int port) {
            this.port = port;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestedPath = exchange.getRequestURI().getPath();
            File file = new File("." + requestedPath);

            if (!file.exists()) {
                // Handle file not found (include server details)
                String response = "404 (Not Found)\n" +
                        "Server Port: " + port + "\n";
                exchange.sendResponseHeaders(404, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                // Serve the requested file (include server details)
                String mimeType = getMimeType(requestedPath);
                exchange.getResponseHeaders().set("Content-Type", mimeType);
                exchange.sendResponseHeaders(200, 0); // Send 200 OK, length unknown initially

                try (OutputStream os = exchange.getResponseBody();
                     FileInputStream fis = new FileInputStream(file)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                }
            }
        }

        // Simple function to guess MIME type from file extension
        private static String getMimeType(String fileName) {
            if (fileName.endsWith(".html")) return "text/html";
            if (fileName.endsWith(".css"))  return "text/css";
            if (fileName.endsWith(".js"))   return "application/javascript";
            return "text/plain";
        }
    }
}
