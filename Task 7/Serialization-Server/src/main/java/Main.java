import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        PrintWriter writer = new PrintWriter("Students.txt", StandardCharsets.UTF_8);
        Server server = new Server(5555, writer);
        server.checkConnection();
        server.readData();
    }
}
