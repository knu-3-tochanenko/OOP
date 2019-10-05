import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;

public class Jupiter {
    private String name;
    private int mass;
    private float radius;

    private Material material;
    private RigidBodyControl control;
    private Sphere sphere;
    private Geometry geometry;

    public Jupiter(String name, int mass, float radius) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
    }

    // Generated getters and setters

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public RigidBodyControl getControl() {
        return control;
    }

    public void setControl(RigidBodyControl control) {
        this.control = control;
    }

    public Sphere getSphere() {
        return sphere;
    }

    public void setSphere(Sphere sphere) {
        this.sphere = sphere;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
