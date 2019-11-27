import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.system.AppSettings;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhysicsTest {
    private final Vector3f ZERO_GRAVITY = new Vector3f(0, 0, 0);

    @Test
    void physicsTest() {
        Simulation simulation = new Simulation(true, 10);
        BulletAppState bullet = new BulletAppState();
        simulation.setShowSettings(false);
        AppSettings settings = new AppSettings(true);
        settings.setResolution(1280, 800);
        settings.setVSync(true);
        settings.setDepthBits(24);
        settings.setGammaCorrection(false);
        simulation.setSettings(settings);
        simulation.start();
        simulation.getStateManager().attach(bullet);
        bullet.getPhysicsSpace().setGravity(ZERO_GRAVITY);

        List<Float> Gs = simulation.getGs();
        List<Float> speeds = simulation.getSpeeds();

        int size = Math.min(Gs.size(), speeds.size());

        boolean flewByG = false;
        boolean flewBySpeed = false;
        for (int i = 1; i < size; i++) {
            if (Gs.get(i - 1) > Gs.get(i))
                flewByG = true;
            if (speeds.get(i - 1) > speeds.get(i))
                flewBySpeed = true;

            if (flewByG)
                assertTrue(Gs.get(i - 1) >= Gs.get(i));
            else
                assertTrue(Gs.get(i - 1) <= Gs.get(i));

            if (flewBySpeed)
                assertTrue(speeds.get(i - 1) >= speeds.get(i));
            else
                assertTrue(speeds.get(i - 1) <= speeds.get(i));
        }
    }

}
