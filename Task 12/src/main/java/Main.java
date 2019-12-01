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

        Timer timer = new Timer(500, this);

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
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.BLACK);
            g2d.fill(triangle0);

            g2d.setColor(Color.BLACK);
            g2d.fill(triangle1);

            g2d.setColor(Color.BLACK);
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
                repaint();// this will call at every 1 second
            }
        }
    }
}