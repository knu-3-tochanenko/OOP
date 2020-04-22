import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

@ServerEndpoint("/socket")
public class WebSocketEndpoint {
    private Thread coordinatesUpdater = null;
    private final AtomicBoolean isAlive = new AtomicBoolean(false);

    @OnMessage(maxMessageSize = 1024000)
    public byte[] handleBinaryMessage(byte[] buffer) {
        System.out.println("New Binary Message Received");
        return buffer;
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        System.out.println("Received message " + msg);
        switch (msg) {
            case "Start":
                System.out.println("START");
                for (Session sess : session.getOpenSessions()) {
                    start(sess);
                }
                break;
            case "Stop":
                System.out.println("STOP");
                stop();
                break;
            case "INIT":
                for (Session sess : session.getOpenSessions()) {
                    try {
                        sess.getBasicRemote().sendText(
                                "Z " + S.A_X_POS + " " + S.A_Y_POS + " " + S.A_RADIUS +
                                        " " + S.B_X_POS + " " + S.B_Y_POS + " " + S.B_RADIUS
                        );
                        System.out.println("Send initial values");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void start(Session session) {
        if (coordinatesUpdater == null) {
            coordinatesUpdater = new Thread(new BallsPhysics(session, isAlive, S.SLEEP_TIME));
        } else if (coordinatesUpdater.isAlive()) {
            System.out.println("Can't start the service. Restarting...");
            isAlive.set(false);
        }

        if (!coordinatesUpdater.isAlive()) {
            System.out.println("Starting the service...");
            isAlive.set(true);
            coordinatesUpdater = new Thread(new BallsPhysics(session, isAlive, S.SLEEP_TIME));
            coordinatesUpdater.start();
        }
    }

    private void stop() {
        if (coordinatesUpdater == null) {
            System.out.println("Start service before stopping it!");
        } else if (!coordinatesUpdater.isAlive()) {
            System.out.println("Service is dead. Consider starting it first...");
        } else {
            System.out.println("Stopping the service...");
            isAlive.set(false);
        }
    }
}
