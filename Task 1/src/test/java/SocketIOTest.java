import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class SocketIOTest {
    private SocketChannel channel;

    private SocketIO socketIO;

    @Before
    public void setUp() {
        channel = Mockito.mock(SocketChannel.class);
        socketIO = new SocketIO();
    }

    @Test
    public void read() throws IOException {
        when(channel.read((ByteBuffer) any())).thenReturn(5);
        String response = socketIO.read(channel);
        Assert.assertNotEquals(channel.read((ByteBuffer) any()), 0);
        Assert.assertEquals(response, "Empty");
        verify(channel, times(2)).read((ByteBuffer) any());
    }

    @Test
    public void write() throws IOException {
        String message = "Message";
        when(channel.write((ByteBuffer) any())).thenReturn(message.length());
        socketIO.write(channel, message);
        assertEquals(message.length(), channel.write((ByteBuffer) any()));
        verify(channel, Mockito.times(2)).write((ByteBuffer) any());
    }
}