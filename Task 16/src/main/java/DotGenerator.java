import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class DotGenerator {
    private static Random r = new Random();

    private static int minTime = 100;
    private static int maxTime = 150;

    public static Dot getNextDot(int width, int height, CyclicBarrier barrier) {
        int x, finish;
        switch (getSide()) {
            case LEFT:
                x = 0;
                finish = width;
                break;
            case RIGHT:
                x = width;
                finish = 0;
                break;
            default:
                throw new EnumConstantNotPresentException(Side.class, "Side is not identified.");
        }
        return getNextDot(x, finish, height, barrier);
    }

    public static Dot getNextDot(int x, int finish, int height, CyclicBarrier barrier) {
        int y, speed;
        y = getY(height);
        System.out.println(y);
        speed = getSpeed();
        return new Dot(x, y, finish, speed, barrier);
    }

    private static Side getSide() {
        return r.nextBoolean() ? Side.LEFT : Side.RIGHT;
    }

    private static int getY(int height) {
        return Math.abs(r.nextInt()) % height;
    }

    private static int getSpeed() {
        return minTime + r.nextInt(maxTime - minTime);
    }
}
