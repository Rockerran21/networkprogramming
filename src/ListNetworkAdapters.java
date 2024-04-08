import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class ListNetworkAdapters {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                System.out.println("Name: " + networkInterface.getName());
                System.out.println("Display Name: " + networkInterface.getDisplayName());

                System.out.println("-------------------------");
            }

        } catch (SocketException e) {
            System.out.println("Error getting network interfaces: " + e.getMessage());
        }
    }
}
