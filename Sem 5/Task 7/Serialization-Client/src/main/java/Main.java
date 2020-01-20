public class Main {
    public static void main(String[] args) {
        Client client = new Client("localhost", 5555);
        client.connect();
        Student student = new Student("Nick", 21, "MIT");
        client.sendObject(student);
    }
}
