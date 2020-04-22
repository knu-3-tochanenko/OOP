import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class BallsPhysics implements Runnable {

    public BallsPhysics(Session session, AtomicBoolean isAlive, int sleepTime) {
        this.session = session;
        this.isAlive = isAlive;
        this.sleepTime = sleepTime;
    }

    private final AtomicBoolean isAlive;
    private final int sleepTime;
    private final Session session;

    private final Ball first = new Ball(new Pair<>(S.A_X_POS, S.A_Y_POS), S.A_RADIUS, S.A_MASS, new Pair<>(S.A_X_SPEED, S.A_Y_SPEED));
    private final Ball second = new Ball(new Pair<>(S.B_X_POS, S.B_Y_POS), S.B_RADIUS, S.B_MASS, new Pair<>(S.B_X_SPEED, S.B_Y_SPEED));

    private boolean wasCollided = false;

    public void iterate() {
        if (collided(first, second) && !wasCollided) {
            Pair<Double, Double> newASpeed = getNewSpeed(first, second);
            Pair<Double, Double> newBSpeed = getNewSpeed(second, first);

            first.setSpeed(newASpeed);
            second.setSpeed(newBSpeed);
            wasCollided = true;
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

    private double len(Pair<Double, Double> v) {
        return Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY());
    }

    private double angle(Pair<Double, Double> v) {
        return Math.atan2(v.getY(), v.getX());
    }

    private Pair<Double, Double> getNewSpeed(Ball a, Ball b) {
        double angleM = Math.atan2(a.getY(), a.getX()) - Math.atan2(b.getY(), b.getX());
        if ( angleM < 0 ) { angleM += 2 * Math.PI; }
        if ( angleM > 2 * Math.PI ) angleM -= 2 * Math.PI;
//        angleM += (Math.PI / 2);

        double sub = len(a.getSpeed()) * Math.cos(angle(a.getSpeed()) - angleM) * (a.getMass() - b.getMass())
                + 2 * b.getMass() * len(b.getSpeed()) * Math.cos(angle(b.getSpeed()) - angleM);

        double x = sub / (a.getMass() + b.getMass()) * Math.cos(angleM) + len(a.getSpeed())
                * Math.sin(angle(a.getSpeed()) - angleM) * Math.cos(angleM + Math.PI / 2);

        double y = sub / (a.getMass() + b.getMass()) * Math.sin(angleM) + len(a.getSpeed())
                * Math.sin(angle(a.getSpeed()) - angleM) * Math.sin(angleM + Math.PI / 2);

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
