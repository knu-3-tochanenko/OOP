import java.util.concurrent.atomic.AtomicInteger;

public class Rotate {
    public static AtomicInteger rotateX(AtomicInteger x, AtomicInteger y, AtomicInteger x0, AtomicInteger y0, AtomicInteger angle) {
        return new AtomicInteger((int) (x0.get() + (x.get() - x0.get()) * Math.cos(Math.toRadians(angle.get())) - (y.get() - y0.get()) * Math.sin(Math.toRadians(angle.get()))));
    }

    public static AtomicInteger rotateY(AtomicInteger x, AtomicInteger y, AtomicInteger x0, AtomicInteger y0, AtomicInteger angle) {
        return new AtomicInteger((int) (y0.get() + (y.get() - y0.get()) * Math.cos(Math.toRadians(angle.get())) + (x.get() - x0.get()) * Math.sin(Math.toRadians(angle.get()))));
    }
}
