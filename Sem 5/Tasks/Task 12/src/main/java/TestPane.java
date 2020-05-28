import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

public class TestPane extends JPanel implements ActionListener {
    private Polygon triangle0;
    private Polygon triangle1;
    private Polygon triangle2;

    private AtomicInteger[] centresX = Config.intToAtomicInteger(new int[]{150, 300, 450});
    private AtomicInteger[] centresY = Config.intToAtomicInteger(new int[]{121, 121, 121});

    private AtomicInteger[] trianglesY = Config.intToAtomicInteger(new int[]{150, 63, 150});
    private AtomicInteger[] triangle0X = Config.intToAtomicInteger(new int[]{100, 150, 200});
    private AtomicInteger[] triangle1X = Config.intToAtomicInteger(new int[]{250, 300, 350});
    private AtomicInteger[] triangle2X = Config.intToAtomicInteger(new int[]{400, 450, 500});

    private AtomicInteger[] trianglesStartY = Config.intToAtomicInteger(new int[]{150, 63, 150});
    private AtomicInteger[] triangle0StartX = Config.intToAtomicInteger(new int[]{100, 150, 200});
    private AtomicInteger[] triangle1StartX = Config.intToAtomicInteger(new int[]{250, 300, 350});
    private AtomicInteger[] triangle2StartX = Config.intToAtomicInteger(new int[]{400, 450, 500});

    private void updateCoords() {
        triangle0 = new Polygon(
                Config.atomicIntegerToInt(triangle0X),
                Config.atomicIntegerToInt(trianglesY),
                3);

        triangle1 = new Polygon(
                Config.atomicIntegerToInt(triangle1X),
                Config.atomicIntegerToInt(trianglesY),
                3
        );

        triangle2 = new Polygon(
                Config.atomicIntegerToInt(triangle2X),
                Config.atomicIntegerToInt(trianglesY),
                3
        );
    }

    Timer timer = new Timer(Config.SPEED, this);

    public TestPane() {
        timer.start();
        triangle0 = new Polygon(
                Config.atomicIntegerToInt(triangle0X),
                Config.atomicIntegerToInt(trianglesY),
                3);

        triangle1 = new Polygon(
                Config.atomicIntegerToInt(triangle1X),
                Config.atomicIntegerToInt(trianglesY),
                3
        );

        triangle2 = new Polygon(
                Config.atomicIntegerToInt(triangle2X),
                Config.atomicIntegerToInt(trianglesY),
                3
        );
        Thread triangle0Updater = new Thread(
                new CoordUpdater(triangle0X, trianglesY,
                        triangle0StartX, trianglesStartY, centresX[0], centresY[0], true));
        Thread triangle1Updater = new Thread(
                new CoordUpdater(triangle1X, trianglesY,
                        triangle1StartX, trianglesStartY, centresX[1], centresY[1], true));
        Thread triangle2Updater = new Thread(
                new CoordUpdater(triangle2X, trianglesY,
                        triangle2StartX, trianglesStartY, centresX[2], centresY[2], true));
        Thread triangleYUpdater = new Thread(
                new CoordUpdater(triangle0X, trianglesY,
                        triangle0StartX, trianglesStartY, centresX[0], centresY[0], false));
        Thread threadSynchroniser = new Thread(new ThreadSynchroniser(
                triangle0X,
                triangle1X,
                triangle2X,
                trianglesY,
                triangle0StartX,
                triangle1StartX,
                triangle2StartX,
                trianglesStartY,
                centresX,
                centresY
        ));
        triangle0Updater.setPriority(8);
        triangle0Updater.start();
        triangle1Updater.setPriority(4);
        triangle1Updater.start();
        triangle2Updater.setPriority(1);
        triangle2Updater.start();
        triangleYUpdater.setPriority(9);
        triangleYUpdater.start();
        threadSynchroniser.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateCoords();
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        g2d.fill(triangle0);

        g2d.setColor(Color.darkGray);
        g2d.fill(triangle1);

        g2d.setColor(Color.GRAY);
        g2d.fill(triangle2);

        g2d.setColor(Color.RED);

        g2d.drawLine(centresX[0].get(), centresY[0].get(), centresX[0].get(), centresY[0].get());
        g2d.drawLine(centresX[1].get(), centresY[1].get(), centresX[1].get(), centresY[1].get());
        g2d.drawLine(centresX[2].get(), centresY[2].get(), centresX[2].get(), centresY[2].get());

        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();
        }
    }
}