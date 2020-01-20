import com.jme3.bullet.BulletAppState;
import com.jme3.light.Light;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.system.AppSettings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    private static Simulation simulation = new Simulation(
            true,
            1
    );

    static {
        simulation.setShowSettings(false);
        AppSettings settings = new AppSettings(true);
        settings.setResolution(1280, 800);
        settings.setVSync(true);
        settings.setDepthBits(24);
        settings.setGammaCorrection(false);
        simulation.setSettings(settings);
        simulation.start();
    }

    @Test
    void jupiterCreatedTest() {
        assertEquals(
                simulation.getRootNode().getChild("Voyager 2").getName(),
                Config.Voyager.NAME
        );
    }

    @Test
    void voyagerCreatedTest() {
        assertEquals(
                simulation.getRootNode().getChild("Voyager 2").getName(),
                Config.Voyager.NAME
        );
    }

    @Test
    void jupiterPositionTest() {
        assertEquals(
                simulation.getRootNode().getChild("Jupiter").getLocalTranslation(),
                Config.Jupiter.POSITION
        );
    }

    @Test
    void voyagerPositionTest() {
        assertNotEquals(
                simulation.getRootNode().getChild("Voyager 2").getLocalTranslation(),
                Config.Voyager.POSITION
        );
    }

    @Test
    void jupiterScaleTest() {
        assertEquals(
                simulation.getRootNode().getChild("Jupiter").getLocalScale(),
                new Vector3f(
                        Config.Jupiter.SCALE,
                        Config.Jupiter.SCALE,
                        Config.Jupiter.SCALE
                )
        );
    }

    @Test
    void voyagerScaleTest() {
        assertEquals(
                simulation.getRootNode().getChild("Voyager 2").getLocalScale(),
                new Vector3f(
                        Config.Voyager.SCALE,
                        Config.Voyager.SCALE,
                        Config.Voyager.SCALE
                )
        );
    }

    @Test
    void jupiterRotationTest() {
        assertNotEquals(
                simulation.getRootNode().getChild("Jupiter").getLocalRotation().getZ(),
                Config.Jupiter.ROTATION
        );
    }

    @Test
    void voyagerRotationTest() {
        assertEquals(
                simulation.getRootNode().getChild("Voyager 2").getLocalRotation(),
                new Quaternion(0f, 0f, 0f, 1f)
        );
    }

    @Test
    void cameraTest() {
        assertFalse(simulation.getCamera().isViewportChanged());
    }

    @Test
    void gravityTest() {
        assertEquals(
                simulation.getStateManager()
                        .getState(BulletAppState.class)
                        .getPhysicsSpace().getGravity(Config.App.ZERO_GRAVITY),
                Config.App.ZERO_GRAVITY
        );
    }

    @Test
    void accuracyTest() {
        assertEquals(
                simulation.getStateManager()
                        .getState(BulletAppState.class)
                        .getPhysicsSpace().getAccuracy(),
                Config.App.ACCURACY
        );
    }

    @Test
    void flyCamTest() {
        assertTrue(simulation.getFlyByCamera().isEnabled());
        assertEquals(simulation.getFlyByCamera().getMoveSpeed(), Config.App.CAM_SPEED);
        assertTrue(simulation.getFlyByCamera().isEnabled());
    }

    @Test
    void settingsTest() {
        assertFalse(simulation.isPauseOnLostFocus());
    }

    @Test
    void lightTest() {
        assertTrue(simulation.getRootNode().getLocalLightList().size() > 0);
        Light light = simulation.getRootNode().getLocalLightList().get(0);
        assertEquals(light.getColor(), ColorRGBA.Yellow);
        assertTrue(light.isEnabled());
        assertEquals(light.getType(), Light.Type.Directional);
    }
}
