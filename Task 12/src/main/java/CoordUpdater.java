import java.util.concurrent.atomic.AtomicInteger;

public class CoordUpdater implements Runnable {

    private AtomicInteger[] x;
    private AtomicInteger[] y;
    private AtomicInteger[] startX;
    private AtomicInteger[] startY;
    private AtomicInteger centerX;
    private AtomicInteger centerY;

    private AtomicInteger step = new AtomicInteger(0);

    private boolean isX;

    public CoordUpdater(AtomicInteger[] x, AtomicInteger[] y, AtomicInteger[] startX, AtomicInteger[] startY,
                        AtomicInteger centerX, AtomicInteger centerY, boolean isX) {
        this.x = x;
        this.y = y;
        this.startX = startX;
        this.startY = startY;
        this.centerX = centerX;
        this.centerY = centerY;
        this.isX = isX;
    }

    @Override
    public void run() {
        while(true) {
//            if (step.get() == 360) {
//                x = startX.clone();
//                y = startY.clone();
//                step.set(0);
//                continue;
//            }

            step.incrementAndGet();
            for (int i = 0; i < 3; i++)
                if (isX)
                    x[i] = Rotate.rotateX(startX[i], startY[i], centerX, centerY, step);
                else
                    y[i] = Rotate.rotateY(startX[i], startY[i], centerX, centerY, step);
            try {
                Thread.sleep(Config.SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
