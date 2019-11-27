import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Spatial;

public class SpaceObject {
    RigidBodyControl control;
    String name;

    RigidBodyControl getControl() {
        return control;
    }

    void setControl(RigidBodyControl control) {
        this.control = control;
    }

}
