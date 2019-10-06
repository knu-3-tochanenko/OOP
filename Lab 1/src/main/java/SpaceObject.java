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

    void setPhysics(BulletAppState state, float mass) {
        RigidBodyControl control = new RigidBodyControl(mass);
        this.getGeometry().addControl(control);
        state.getPhysicsSpace().add(control);
        this.setControl(control);
    }

    public Spatial getGeometry() {
        return null;
    }

    public void setGeometry(Spatial geometry) {
    }

}
