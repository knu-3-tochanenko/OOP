import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

public class SocketIO {
    private final int bufferSize = 1024;
    private ByteBuffer buffer;
    private CharsetEncoder encoder;

    SocketIO() {
        buffer = ByteBuffer.allocate(bufferSize);
        Charset charset = StandardCharsets.UTF_8;
        encoder = charset.newEncoder();
    }

    public String read(final SocketChannel channel) throws IOException {
        buffer.clear();
        channel.read(buffer);
        buffer.flip();
        if (buffer.remaining() != 0)
            return new String(buffer.array(), StandardCharsets.UTF_8).trim();
        return "Empty";
    }


    public ByteBuffer write(final SocketChannel channel, final String request) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(request.length() * 2);
        buffer.put(encoder.encode(CharBuffer.wrap(request)));
        buffer.flip();
        channel.write(buffer);
        return buffer;
    }
}
