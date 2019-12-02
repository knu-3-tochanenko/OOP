import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.*;

public class SocketWriter {
    private CharsetEncoder encoder;


    public SocketWriter() {
        Charset charset = StandardCharsets.UTF_8;
        encoder = charset.newEncoder();
    }

    public ByteBuffer write(final SocketChannel channel, final String request) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(request.length() * 2);
        buffer.put(encoder.encode(CharBuffer.wrap(request)));
        buffer.flip();
        channel.write(buffer);
        return buffer;
    }

}
