import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhysicsTest {
    private final Vector3f ZERO_GRAVITY = new Vector3f(0, 0, 0);

    @Test
    void physicsTest() throws InterruptedException {
        Simulation simulation = new Simulation(true, 10);
        BulletAppState bullet = new BulletAppState();
        simulation.start();
        simulation.getStateManager().attach(bullet);
        bullet.getPhysicsSpace().setGravity(ZERO_GRAVITY);
        
        List<Float> Gs = simulation.getGs();
        List<Float> speeds = simulation.getSpeeds();

        int size = Math.min(Gs.size(), speeds.size());

        boolean flewBy = false;
        for (int i = 1; i < size; i++) {
            if (speeds.get(i - 1) > speeds.get(i))
                flewBy = true;
            if (flewBy)
                assertTrue(speeds.get(i - 1) > speeds.get(i));
            else
                assertTrue(speeds.get(i - 1) < speeds.get(i));
        }
    }

}
