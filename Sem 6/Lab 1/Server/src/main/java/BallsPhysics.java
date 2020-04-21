import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class BallsPhysics implements Runnable {

    public BallsPhysics(Session session, AtomicBoolean isAlive, int sleepTime) {
        this.session = session;
        this.isAlive = isAlive;
        this.sleepTime = sleepTime;
    }

    private AtomicBoolean isAlive;
    private int sleepTime = 100;
    private Session session;

    private Ball first = new Ball(new Pair<>(S.A_X_POS, S.A_Y_POS), S.A_RADIUS, S.A_MASS, new Pair<>(S.A_X_SPEED, S.A_Y_SPEED));
    private Ball second = new Ball(new Pair<>(S.B_X_POS, S.B_Y_POS), S.B_RADIUS, S.B_MASS, new Pair<>(S.B_X_SPEED, S.B_Y_SPEED));

    public void iterate() {
        if (collided(first, second)) {
            Pair<Double, Double> newASpeed = getNewSpeed(first, second);
            Pair<Double, Double> newBSpeed = getNewSpeed(second, first);

            first.setSpeed(newASpeed);
            second.setSpeed(newBSpeed);
        }

        first.iterate();
        second.iterate();

        try {
            session.getBasicRemote().sendText("" + first.getX() + " " + first.getY() + " " + second.getX() + " " + second.getY());
            System.out.println("Send new coordinates");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean collided(Ball a, Ball b) {
        return Double.compare(
                distanceBetweenDots(a.getX(), a.getY(), b.getX(), b.getY()),
                a.getRadius() + b.getRadius()) < 0;
    }

    private double distanceBetweenDots(double aX, double aY, double bX, double bY) {
        return Math.sqrt(Math.pow(bX - aX, 2.0) + Math.pow(bY - aY, 2.0));
    }

    private double len(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    private double angle(double x, double y) {
        return Math.atan(y / x);
    }

    private Pair<Double, Double> getNewSpeed(Ball a, Ball b) {
        double angleM = Math.atan2(a.getX() * b.getY() - a.getY() * b.getX(), a.getX() * b.getX() - a.getY() - b.getY());

        if (angleM >= Math.PI) {
            angleM -= Math.PI;
        }

        double sub = len(a.getVX(), a.getVY()) * Math.cos(angle(a.getVX(), a.getVY()) - angleM) * (a.getMass() - b.getMass())
                + 2 * b.getMass() * len(b.getVX(), b.getVY()) * Math.cos(angle(b.getVX(), b.getVY()) - angleM);

        double x = sub / (a.getMass() + b.getMass()) * Math.cos(angleM) + len(a.getVX(), a.getVY())
                * Math.sin(angle(a.getVX(), b.getVY()) - angleM) * Math.cos(angleM + Math.PI / 2);

        double y = sub / (a.getMass() + b.getMass()) * Math.sin(angleM) + len(a.getVX(), a.getVY())
                * Math.sin(angle(a.getVX(), b.getVY()) - angleM) * Math.sin(angleM + Math.PI / 2);

        return new Pair<>(x, y);
    }

    @Override
    public void run() {
        while(isAlive.get()) {
            iterate();
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
