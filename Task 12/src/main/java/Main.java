import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
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
            }
        });
    }

    public class TestPane extends JPanel implements ActionListener {
        private Polygon triangle0;
        private Polygon triangle1;
        private Polygon triangle2;

        private int[] centresX = new int[]{150, 300, 450};
        private int[] centresY = new int[]{121, 121, 121};

        private int[] trianglesY = new int[]{150, 63, 150};
        private int[] triangle0X = new int[]{100, 150, 200};
        private int[] triangle1X = new int[]{250, 300, 350};
        private int[] triangle2X = new int[]{400, 450, 500};

        private int[] trianglesStartY = new int[]{150, 63, 150};
        private int[] triangle0StartX = new int[]{100, 150, 200};
        private int[] triangle1StartX = new int[]{250, 300, 350};
        private int[] triangle2StartX = new int[]{400, 450, 500};

        private int iteration = 0;

        private int rotateX(int x, int y, int x0, int y0, int angle) {
            return (int) (x0 + (x - x0) * Math.cos(Math.toRadians(angle)) - (y - y0) * Math.sin(Math.toRadians(angle)));
        }

        private int rotateY(int x, int y, int x0, int y0, int angle) {
            return (int) (y0 + (y - y0) * Math.cos(Math.toRadians(angle)) + (x - x0) * Math.sin(Math.toRadians(angle)));
        }

        private void updateCoords() {

            if (iteration == 360) {
                trianglesY = trianglesStartY.clone();
                triangle0X = triangle0StartX.clone();
                triangle1X = triangle1StartX.clone();
                triangle2X = triangle2StartX.clone();
                iteration = 0;
                return;
            }

            for (int i = 0; i < 3; i++) {
                triangle0X[i] = rotateX(
                        triangle0StartX[i],
                        trianglesStartY[i],
                        centresX[0],
                        centresY[0],
                        iteration
                );
                triangle1X[i] = rotateX(
                        triangle1StartX[i],
                        trianglesStartY[i],
                        centresX[1],
                        centresY[1],
                        iteration
                );
                triangle2X[i] = rotateX(
                        triangle2StartX[i],
                        trianglesStartY[i],
                        centresX[2],
                        centresY[2],
                        iteration
                );
                trianglesY[i] = rotateY(
                        triangle0StartX[i],
                        trianglesStartY[i],
                        centresX[0],
                        centresY[0],
                        iteration
                );
            }

            iteration++;

            triangle0 = new Polygon(
                    triangle0X,
                    trianglesY,
                    3);

            triangle1 = new Polygon(
                    triangle1X,
                    trianglesY,
                    3
            );

            triangle2 = new Polygon(
                    triangle2X,
                    trianglesY,
                    3
            );
        }

        Timer timer = new Timer(5, this);

        public TestPane() {
            timer.start();
            triangle0 = new Polygon(
                    triangle0X,
                    trianglesY,
                    3);

            triangle1 = new Polygon(
                    triangle1X,
                    trianglesY,
                    3
            );

            triangle2 = new Polygon(
                    triangle2X,
                    trianglesY,
                    3
            );
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

            g2d.drawLine(centresX[0], centresY[0], centresX[0], centresY[0]);
            g2d.drawLine(centresX[1], centresY[1], centresX[1], centresY[1]);
            g2d.drawLine(centresX[2], centresY[2], centresX[2], centresY[2]);

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