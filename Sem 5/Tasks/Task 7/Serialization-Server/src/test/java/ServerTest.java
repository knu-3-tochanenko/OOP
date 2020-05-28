import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    private int port = 5000;
    private Server server;

    @Before
    public void setUp() throws Exception {
        server = new Server(port, new PrintWriter("Test.txt", StandardCharsets.UTF_8));
    }


    @Test
    public void getPort() {
        assertEquals(server.getPort(), port);
    }
}