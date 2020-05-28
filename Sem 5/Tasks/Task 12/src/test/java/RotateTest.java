import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class RotateTest {

    AtomicInteger[] x = Config.intToAtomicInteger(new int[]{0, 5, 10});
    AtomicInteger[] y = Config.intToAtomicInteger(new int[]{0, 9, 0});
    AtomicInteger centerX = new AtomicInteger(5);
    AtomicInteger centerY = new AtomicInteger(3);

    AtomicInteger angle0 = new AtomicInteger(0);
    AtomicInteger angle90 = new AtomicInteger(90);
    AtomicInteger angle180 = new AtomicInteger(180);
    AtomicInteger angle360 = new AtomicInteger(360);

    @Test
    void rotateX() {
        for (int i = 0; i < 3; i++) {
            assertEquals(x[i].get(), Rotate.rotateX(x[i], y[i], centerX, centerY, angle0).get());
            assertEquals(x[i].get(), Rotate.rotateX(x[i], y[i], centerX, centerY, angle360).get());
        }
        assertEquals(8, Rotate.rotateX(x[0], y[0], centerX, centerY, angle90).get());
        assertEquals(-1, Rotate.rotateX(x[1], y[1], centerX, centerY, angle90).get());
        assertEquals(8, Rotate.rotateX(x[2], y[2], centerX, centerY, angle90).get());

        assertEquals(10, Rotate.rotateX(x[0], y[0], centerX, centerY, angle180).get());
        assertEquals(4, Rotate.rotateX(x[1], y[1], centerX, centerY, angle180).get());
        assertEquals(0, Rotate.rotateX(x[2], y[2], centerX, centerY, angle180).get());
    }

    @Test
    void rotateY() {
        for (int i = 0; i < 3; i++) {
            assertEquals(y[i].get(), Rotate.rotateY(x[i], y[i], centerX, centerY, angle0).get());
            assertEquals(y[i].get(), Rotate.rotateY(x[i], y[i], centerX, centerY, angle360).get());
        }
        assertEquals(-2, Rotate.rotateY(x[0], y[0], centerX, centerY, angle90).get());
        assertEquals(3, Rotate.rotateY(x[1], y[1], centerX, centerY, angle90).get());
        assertEquals(8, Rotate.rotateY(x[2], y[2], centerX, centerY, angle90).get());

        assertEquals(5, Rotate.rotateY(x[0], y[0], centerX, centerY, angle180).get());
        assertEquals(-3, Rotate.rotateY(x[1], y[1], centerX, centerY, angle180).get());
        assertEquals(6, Rotate.rotateY(x[2], y[2], centerX, centerY, angle180).get());
    }
}