import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SocketWriterTest {
    private SocketChannel channel;
    private SocketWriter writer;

    @Before
    public void setUp() {
        channel = Mockito.mock(SocketChannel.class);
        writer = new SocketWriter();
    }

    @Test
    public void write() throws IOException {
        String message = "Message";
        when(channel.write((ByteBuffer) any())).thenReturn(message.length());
        writer.write(channel, message);
        assertEquals(message.length(), channel.write((ByteBuffer) any()));
        verify(channel, times(2)).write((ByteBuffer) any());
    }
}