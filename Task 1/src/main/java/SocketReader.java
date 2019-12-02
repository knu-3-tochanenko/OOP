import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class SocketReader {
    private final int bufferSize = 1024;
    private ByteBuffer buffer;

    SocketReader() {
        buffer = ByteBuffer.allocate(bufferSize);
    }

    public String read(final SocketChannel channel) throws IOException {
        buffer.clear();
        channel.read(buffer);
        buffer.flip();
        if (buffer.remaining() != 0)
            return new String(buffer.array(), StandardCharsets.UTF_8).trim();
        return "Empty";
    }
}
