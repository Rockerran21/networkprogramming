import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;
import java.util.concurrent.*;

public class AsyncIOExample {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("my_file.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Future<Integer> result = fileChannel.read(buffer, 0);

        // Do other tasks while the file is being read...

        int bytesRead = result.get();  // May block until read is complete
        buffer.flip();
        System.out.println(new String(buffer.array(), 0, bytesRead));

        fileChannel.close();
    }
}
