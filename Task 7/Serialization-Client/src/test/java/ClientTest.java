import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {

    private final String host = "localhost";
    private final int port = 1051;

    private Client client;

    @Before
    public void setUp() {
        client = new Client(host, port);
    }

    @Test
    public void getHost() {
        assertEquals(client.getHost(), host);
    }

    @Test
    public void getPort() {
        assertEquals(client.getPort(), port);
    }
}