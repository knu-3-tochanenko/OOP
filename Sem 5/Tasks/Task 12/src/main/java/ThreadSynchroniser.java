import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSynchroniser implements Runnable {
    private AtomicInteger[] t0x;
    private AtomicInteger[] t1x;
    private AtomicInteger[] t2x;
    private AtomicInteger[] y;

    private AtomicInteger[] t0xStart;
    private AtomicInteger[] t1xStart;
    private AtomicInteger[] t2xStart;
    private AtomicInteger[] yStart;

    private AtomicInteger[] centerX;
    private AtomicInteger[] centerY;

    public ThreadSynchroniser(AtomicInteger[] t0x, AtomicInteger[] t1x, AtomicInteger[] t2x, AtomicInteger[] y,
                              AtomicInteger[] t0xStart, AtomicInteger[] t1xStart,
                              AtomicInteger[] t2xStart, AtomicInteger[] yStart,
                              AtomicInteger[] centerX, AtomicInteger[] centerY) {
        this.t0x = t0x;
        this.t1x = t1x;
        this.t2x = t2x;
        this.y = y;
        this.t0xStart = t0xStart;
        this.t1xStart = t1xStart;
        this.t2xStart = t2xStart;
        this.yStart = yStart;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    private int findAngle() {
        int angle = 0;
        for (int i = 0; i < 360; i++) {
            if (t0x[0].equals(Rotate.rotateY(t0xStart[0], yStart[0], centerX[0], centerY[0], new AtomicInteger(i)))) {
                angle = i;
                break;
            }
        }
        return angle;
    }

    private void updateArrays(AtomicInteger angle) {
        for (int i = 0; i < 3; i++) {
            t0x[i] = Rotate.rotateX(t0xStart[i], yStart[i], centerX[0], centerY[0], angle);
            t1x[i] = Rotate.rotateX(t1xStart[i], yStart[i], centerX[1], centerY[1], angle);
            t2x[i] = Rotate.rotateX(t2xStart[i], yStart[i], centerX[2], centerY[2], angle);
            t0x[i] = Rotate.rotateY(t0xStart[i], yStart[i], centerX[0], centerY[0], angle);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int angle = findAngle();
            updateArrays(new AtomicInteger(angle));
            System.out.println("Threads are synced");
        }
    }
}
