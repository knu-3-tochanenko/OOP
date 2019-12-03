import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Application extends JFrame implements ActionListener {

    private CyclicBarrier barrier;

    private Timer timer = new Timer(10, this);

    Application() {
        super();
        barrier = new CyclicBarrier(2);
    }

    public void start() {
        setVisible(true);
        new Thread(addDot()).start();
        timer.start();
    }

    private Runnable addDot() {
        return () -> {
            try {
                Dot dot = DotGenerator.getNextDot((int) getContentPane().getSize().getWidth(), (int) getContentPane().getSize().getHeight(), barrier);
                add(dot);
                Thread thread = new Thread(dot);
                thread.start();
                barrier.await();
                remove(dot);
                new Thread(addDot()).start();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == timer) {
            paintAll(getGraphics());
            revalidate();
        }
    }
}
