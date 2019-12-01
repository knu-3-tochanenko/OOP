import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            }

            JFrame frame = new JFrame("Testing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBackground(Color.BLACK);
            frame.setLayout(new BorderLayout());
            frame.add(new TestPane());
            frame.setSize(610, 250);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private int[] atomicIntegerToInt(AtomicInteger[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            res[i] = arr[i].get();
        return res;
    }

    public class TestPane extends JPanel implements ActionListener {
        private Polygon triangle0;
        private Polygon triangle1;
        private Polygon triangle2;

        private AtomicInteger[] centresX = new AtomicInteger[]{
                new AtomicInteger(150),
                new AtomicInteger(300),
                new AtomicInteger(450)
        };
        private AtomicInteger[] centresY = new AtomicInteger[]{
                new AtomicInteger(121),
                new AtomicInteger(121),
                new AtomicInteger(121)
        };

        private AtomicInteger[] trianglesY = new AtomicInteger[]{
                new AtomicInteger(150),
                new AtomicInteger(63),
                new AtomicInteger(150)
        };
        private AtomicInteger[] triangle0X = new AtomicInteger[]{
                new AtomicInteger(100),
                new AtomicInteger(150),
                new AtomicInteger(200)
        };
        private AtomicInteger[] triangle1X = new AtomicInteger[]{
                new AtomicInteger(250),
                new AtomicInteger(300),
                new AtomicInteger(350)
        };
        private AtomicInteger[] triangle2X = new AtomicInteger[]{
                new AtomicInteger(400),
                new AtomicInteger(450),
                new AtomicInteger(500)
        };

        private AtomicInteger[] trianglesStartY = new AtomicInteger[]{
                new AtomicInteger(150),
                new AtomicInteger(63),
                new AtomicInteger(150)
        };
        private AtomicInteger[] triangle0StartX = new AtomicInteger[]{
                new AtomicInteger(100),
                new AtomicInteger(150),
                new AtomicInteger(200)
        };
        private AtomicInteger[] triangle1StartX = new AtomicInteger[]{
                new AtomicInteger(250),
                new AtomicInteger(300),
                new AtomicInteger(350)
        };
        private AtomicInteger[] triangle2StartX = new AtomicInteger[]{
                new AtomicInteger(400),
                new AtomicInteger(450),
                new AtomicInteger(500)
        };

        private void updateCoords() {
            triangle0 = new Polygon(
                    atomicIntegerToInt(triangle0X),
                    atomicIntegerToInt(trianglesY),
                    3);

            triangle1 = new Polygon(
                    atomicIntegerToInt(triangle1X),
                    atomicIntegerToInt(trianglesY),
                    3
            );

            triangle2 = new Polygon(
                    atomicIntegerToInt(triangle2X),
                    atomicIntegerToInt(trianglesY),
                    3
            );
        }

        Timer timer = new Timer(Config.SPEED, this);

        public TestPane() {
            timer.start();
            triangle0 = new Polygon(
                    atomicIntegerToInt(triangle0X),
                            atomicIntegerToInt(trianglesY),
                    3);

            triangle1 = new Polygon(
                    atomicIntegerToInt(triangle1X),
                            atomicIntegerToInt(trianglesY),
                    3
            );

            triangle2 = new Polygon(
                    atomicIntegerToInt(triangle2X),
                            atomicIntegerToInt(trianglesY),
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
            triangle0Updater.setPriority(8);
            triangle0Updater.start();
            triangle1Updater.setPriority(4);
            triangle1Updater.start();
            triangle2Updater.setPriority(1);
            triangle2Updater.start();
            triangleYUpdater.setPriority(9);
            triangleYUpdater.start();
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
}