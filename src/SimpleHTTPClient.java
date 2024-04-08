import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleHTTPClient {
    public static void main(String[] args) throws Exception {
        // 1. Setup the target URL
        URL url = new URL("http://127.0.0.1:8000"); // Corrected URL

        // 2. Open the connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true); // Enable sending a request body

        // 3. Set Content-Type (adjust based on your server)
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        // 4. Prepare your data
        String postData = "key1=value1&key2=value2"; // Example data

        // 5. Send the POST request
        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.writeBytes(postData);
        }

        // 6. Get the response
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        try (BufferedReader inputStream = new BufferedReader(
                responseCode >= 400
                        ? new InputStreamReader(connection.getErrorStream())
                        : new InputStreamReader(connection.getInputStream()))) {

            String responseLine;
            StringBuilder response = new StringBuilder();
            while ((responseLine = inputStream.readLine()) != null) {
                response.append(responseLine);
            }
            System.out.println("Response: " + response.toString());
        }
    }
}
