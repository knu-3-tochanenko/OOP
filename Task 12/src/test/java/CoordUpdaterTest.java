import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class CoordUpdaterTest {

    private AtomicInteger[] centresX = Config.intToAtomicInteger(new int[]{150, 300, 450});
    private AtomicInteger[] centresY = Config.intToAtomicInteger(new int[]{121, 121, 121});

    private AtomicInteger[] trianglesY = Config.intToAtomicInteger(new int[]{150, 63, 150});
    private AtomicInteger[] triangle0X = Config.intToAtomicInteger(new int[]{100, 150, 200});

    private AtomicInteger[] trianglesStartY = Config.intToAtomicInteger(new int[]{150, 63, 150});
    private AtomicInteger[] triangle0StartX = Config.intToAtomicInteger(new int[]{100, 150, 200});

    @Test
    void test() {
        Thread testX = new Thread(new CoordUpdater(triangle0X, trianglesY,
                triangle0StartX, trianglesStartY, centresX[0], centresY[0], true));
        Thread testY = new Thread(new CoordUpdater(triangle0X, trianglesY,
                triangle0StartX, trianglesStartY, centresX[0], centresY[0], false));
        testX.start();
        testY.start();

        try {
            Thread.sleep(Config.SPEED * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 3; i++) {
            assertNotEquals(triangle0StartX[i].get(), triangle0X[i].get());
            assertNotEquals(trianglesStartY[i].get(), trianglesY[i].get());
        }
    }

}