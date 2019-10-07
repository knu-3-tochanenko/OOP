import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Spatial;

public class SpaceObject {
    protected RigidBodyControl control;
    protected String name;

    public RigidBodyControl getControl() {
        return control;
    }

    public void setControl(RigidBodyControl control) {
        this.control = control;
    }

}
