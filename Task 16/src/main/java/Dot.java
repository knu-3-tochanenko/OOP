import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Dot extends JPanel implements Runnable {
    private int x;
    private int y;
    private int time;
    private int finish;
    private CyclicBarrier barrier;

    public Dot(int x, int y, int finish, int time, CyclicBarrier barrier) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.barrier = barrier;
        this.finish = finish;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getTime() {
        return time;
    }

    public int getFinish() {
        return finish;
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    @Override
    public void run() {
        try {
            while (x != finish) {
                Thread.sleep(time);
                move();
            }
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(x, y, 10, 10);
    }

    private void move() {
        if (x > finish)
            x--;
        else if (x < finish)
            x++;
    }
}
