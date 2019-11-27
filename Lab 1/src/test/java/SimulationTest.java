import com.jme3.app.state.RootNodeAppState;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {
    static Simulation simulation = new Simulation();
    static BulletAppState bullet = new BulletAppState();

    private Jupiter jupiter;
    private Voyager voyager;
    private static final float G = 3;

    private static final Vector3f ZERO_GRAVITY = new Vector3f(0, 0, 0);

    static {
        simulation.start();
        simulation.getStateManager().attach(bullet);
        bullet.getPhysicsSpace().setGravity(ZERO_GRAVITY);
    }

    @Test
    void jupiterObjectTest() {
        initJupiter();
        simulation.getRootNode().attachChild(jupiter.getGeometry());

        assertEquals(
                simulation.getRootNode().getChild("Jupiter").getName() + 'T',
                simulation.getRootNode().getChild("JupiterT").getName()
        );
        assertEquals(
                simulation.getRootNode().getChild("Jupiter").getLocalScale(),
                simulation.getRootNode().getChild("JupiterT").getLocalScale()
        );
        assertEquals(
                simulation.getRootNode().getChild("Jupiter").getLocalRotation(),
                simulation.getRootNode().getChild("JupiterT").getLocalRotation()
        );
        assertEquals(
                simulation.getRootNode().getChild("Jupiter").getLocalTranslation(),
                simulation.getRootNode().getChild("JupiterT").getLocalTranslation()
        );
    }

    @Test
    void voyagerObjectTest() {
        initVoyager(simulation.getAssetManager(), simulation.getRootNode());
        simulation.getRootNode().attachChild(voyager.getGeometry());

        assertEquals(
                simulation.getRootNode().getChild("Voyager 2").getName() + 'T',
                simulation.getRootNode().getChild("Voyager 2T").getName()
        );
        assertEquals(
                simulation.getRootNode().getChild("Voyager 2").getLocalScale(),
                simulation.getRootNode().getChild("Voyager 2T").getLocalScale()
        );
        assertEquals(
                simulation.getRootNode().getChild("Voyager 2").getLocalRotation(),
                simulation.getRootNode().getChild("Voyager 2T").getLocalRotation()
        );
        // Check if Voyager was launched
        assertNotEquals(
                simulation.getRootNode().getChild("Voyager 2").getLocalTranslation(),
                simulation.getRootNode().getChild("Voyager 2T").getLocalTranslation()
        );
    }

//    @Test
//    void simulationTest() {
//
//        initJupiter();
//        initVoyager(simulation.getAssetManager(), simulation.getRootNode());
//
//        assertEquals(simulation.getRootNode().getChild("Voyager 2").getName(),
//                "Voyager 2");
//        assertEquals(simulation.getRootNode().getChild("Voyager 2").getLocalScale(),
//                new Vector3f(.1f, .1f, .1f));
//    }

    @Test
    void jupiterCreatedTest() {
        assertEquals(simulation.getRootNode().getChild("Jupiter").getName(),
                "Jupiter");
    }

    @Test
    void voyagerCreatedTest() {
        assertEquals(simulation.getRootNode().getChild("Voyager 2").getLocalScale(),
                new Vector3f(.1f, .1f, .1f));
    }

    @Test
    void jupiterTest() {
        assertEquals(
                simulation.getRootNode().getChild("Jupiter").getLocalScale(),
                new Vector3f(1f, 1f, 1f)
        );
    }

    @Test
    void voyagerTest() {
        assertEquals(
                simulation.getRootNode().getChild("Voyager 2").getLocalScale(),
                new Vector3f(.1f, .1f, .1f)
        );
    }

    void initJupiter() {
        jupiter = new Jupiter("JupiterT", 5_000_000, 200);
        jupiter.setGeometry(new Vector3f(0, 2, -20), 1.3f, 0, 0);
        jupiter.setPhysics(bullet, jupiter.getMass());
    }

    void initVoyager(AssetManager assetManager, Node rootNode) {
        voyager = new Voyager("Voyager 2T", 500.0f, .1f);
        voyager.setMaterial(
                assetManager,
                "Models/satellite.obj",
                new Vector3f(-500, 0, 1000)
        );
        voyager.setPhysics(bullet, 1f);
        rootNode.attachChild(voyager.getGeometry());
    }
}
