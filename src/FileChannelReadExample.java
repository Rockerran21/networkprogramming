import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelReadExample {
    public static void main(String[] args) {
        try {
            RandomAccessFile file = new RandomAccessFile("async-output.txt", "r");
            FileChannel fileChannel = file.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(buffer) > 0) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
            }
            fileChannel.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
