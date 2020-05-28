import org.junit.Test;

import java.net.InetSocketAddress;

import static org.junit.Assert.assertEquals;

public class SSHClientTest {
    SSHClient client;

    @Test
    public void connect() {
        final String host = "localhost";
        final int port = 1051;
        client = new SSHClient(host, port);
        InetSocketAddress address = new InetSocketAddress("localhost", port);
        assertEquals(client.getAddress(), address);
        assertEquals(client.getAddress().getHostName(), host);
        assertEquals(client.getAddress().getPort(), port);
    }
}