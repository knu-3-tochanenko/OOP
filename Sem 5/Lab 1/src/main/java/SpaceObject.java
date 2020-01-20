import com.jme3.bullet.control.RigidBodyControl;

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
