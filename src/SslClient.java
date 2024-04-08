import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SslClient {
    public static void main(String[] args) throws Exception {
        System.setProperty("javax.net.ssl.keyStore", "./keystore.p12");
        System.setProperty("javax.net.ssl.keyStorePassword", "Ranjan@79115");
        System.setProperty("https.protocols", "TLSv1.2");
        SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) ssf.createSocket("localhost", 8443);

        InputStream in = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
