import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class SocketReaderTest {
    private SocketChannel channel;

    private SocketReader reader;

    @Before
    public void setUp() {
        channel = Mockito.mock(SocketChannel.class);
        reader = new SocketReader();
    }

    @Test
    public void read() throws IOException {
        when(channel.read((ByteBuffer) any())).thenReturn(5);
        String response = reader.read(channel);
        Assert.assertNotEquals(channel.read((ByteBuffer) any()), 0);
        Assert.assertEquals(response, "Empty");
        verify(channel, times(2)).read((ByteBuffer) any());
    }
}