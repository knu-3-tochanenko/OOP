import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;

public class Simulation extends SimpleApplication {
    // Scene objects
    private Voyager voyager = new Voyager("Voyager 2", 100.0f, 3.0f);
    private Jupiter jupiter = new Jupiter("Jupiter", 2_000_000, 700);

    // Scene properties
    private static final int G = 5;
    private static final int CAM_SPEED = 100;
    private static final Vector3f ZERO_GRAVITY = new Vector3f(0, 0, 0);

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(CAM_SPEED);

        // Disable standard gravity
        BulletAppState bullet = new BulletAppState();
        stateManager.attach(bullet);
        bullet.getPhysicsSpace().setGravity(ZERO_GRAVITY);

        // Jupiter attach block
        jupiter.setMaterial(assetManager, "Textures/jupiter.jpg");
        jupiter.setGeometry(new Vector3f(0, 2, -20), 1.3f, 0, 0);
        jupiter.getGeometry().setLocalTranslation(-500, 0, 1000);
        jupiter.setPhysics(bullet, jupiter.getMass());
        rootNode.attachChild(jupiter.getGeometry());

        // Voyager attach block
        voyager.setMaterial(assetManager, "Models/voyager.obj", 0.1f, new Vector3f(-500,0,1005));
        voyager.setPhysics(bullet, 1f);
        rootNode.attachChild(voyager.getGeometry());
    }


}
