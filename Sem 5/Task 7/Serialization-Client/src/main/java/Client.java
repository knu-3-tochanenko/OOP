import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private String host;
    private int port;
    private Logger logger;

    private Socket client = null;

    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        this.logger = Logger.getLogger(Client.class.getName());
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public void connect() {
        try {
            client = new Socket(host, port);
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
            logger.info(String.format("Connected to the %s", client.getInetAddress().getHostAddress()));
        } catch (IOException e) {
            logger.log(Level.INFO, "Exception handled: " + e.getMessage());
        }
    }

    public void sendObject(Serializable object) {
        if (client.isConnected()) {
            try {
                out.writeObject(object);
                out.flush();
                closeStreams();
                logger.info("Object was send.");
            } catch (IOException e) {
                logger.log(Level.INFO, "Object wasn't send. Exception handled: " + e.getMessage());
            }
        } else {
            logger.info("Not connected to server");
        }
    }

    private void closeStreams() throws IOException {
        out.close();
        in.close();
        client.close();
    }
}
