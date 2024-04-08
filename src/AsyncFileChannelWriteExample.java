import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsyncFileChannelWriteExample {
    public static void main(String[] args) {
        try {
            Path path = Paths.get("async-output.txt");
            AsynchronousFileChannel fileChannel =
                    AsynchronousFileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("Hello, this is a test.".getBytes());
            buffer.flip();

            Future<Integer> operation = fileChannel.write(buffer, 0);
            while (!operation.isDone());

            System.out.println("Write operation completed.");
            fileChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
