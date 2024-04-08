import java.io.*;

public class SyncIOExample {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInput = new FileInputStream("my_file.txt");
        byte[] data = new byte[1024];
        int bytesRead = fileInput.read(data); // Blocks until data is read

        // Process the read data...
        System.out.println(new String(data, 0, bytesRead));

        fileInput.close();
    }
}
