import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Spatial;

public class Voyager {
    private double speed;
    private double scale;
    private String name;

    private Spatial spatial;
    private RigidBodyControl control;

    private Voyager(String name, double speed, double scale) {
        this.name = name;
        this.speed = speed;
        this.scale = scale;
    }

    // Generated getters and setters

    public Spatial getSpatial() {
        return spatial;
    }

    public void setSpatial(Spatial spatial) {
        this.spatial = spatial;
    }

    public RigidBodyControl getControl() {
        return control;
    }

    public void setControl(RigidBodyControl control) {
        this.control = control;
    }
}
