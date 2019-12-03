public class Main {
    public static void main(String[] args) {
        SSHClient client = new SSHClient("localhost", 1050);
        client.connect();
    }
}
