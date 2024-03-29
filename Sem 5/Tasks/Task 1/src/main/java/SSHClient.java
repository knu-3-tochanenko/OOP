import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SSHClient {
    private InetSocketAddress address;
    private SocketIO io;

    private Logger logger;

    public SSHClient(String ip, int port) {
        address = new InetSocketAddress(ip, port);

        io = new SocketIO();

        logger = Logger.getLogger(SSHClient.class.getName());
    }

    public void connect() {
        try (SocketChannel channel = SocketChannel.open(this.address)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            String userMessage;
            String response;

            response = io.read(channel);
            logger.info("Received response: " + response);
            while ((userMessage = in.readLine()) != null) {
                logger.info("Message: " + userMessage);
                ByteBuffer buffer = io.write(channel, userMessage);
                logger.info("Sending message: " + userMessage + "; buffer bytes: " + buffer.position());
                response = io.read(channel);
                logger.info("Received response: " + response);
            }
        } catch (IOException e) {
            logger.log(Level.INFO, "Exception handled" + e.getMessage());
        }
    }

    public InetSocketAddress getAddress() {
        return address;
    }
}
