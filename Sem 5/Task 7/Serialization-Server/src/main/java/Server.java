import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private ObjectInputStream in = null;

    private int port;

    private ServerSocket serverSocket;
    private Socket client;

    private Logger logger;

    private PrintWriter storage;

    public Server(int port, PrintWriter storage) {
        this.port = port;
        logger = Logger.getLogger(Server.class.getName());
        this.storage = storage;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            logger.log(Level.INFO, "Exception handled: " + e.getMessage());
        }
    }

    public int getPort() {
        return port;
    }

    public void checkConnection() {
        try {
            client = serverSocket.accept();
            logger.info("Connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeStreams() throws IOException {
        in.close();
        serverSocket.close();
    }

    public void readData() {
        try {
            in = new ObjectInputStream(client.getInputStream());
            logger.info("Received object");
            Object object = in.readObject();
            closeStreams();
            client.close();
            saveData((Student) object);
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.INFO, "Can't read data.");
        }
    }


    private void saveData(Student student) {
        storage.println(student.toString());
        logger.log(Level.INFO, "Data stored.");
        storage.close();
    }
}
