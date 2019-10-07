import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class Voyager extends SpaceObject {
    private float speed;
    private float scale;

    private Spatial geometry;

    public Voyager(String name, float speed, float scale) {
        this.name = name;
        this.speed = speed;
        this.scale = scale;
    }

    public void start(){
        control.setLinearVelocity(new Vector3f(speed, 0, 0));
    }

    void setPhysics(BulletAppState state, float mass) {
        this.getGeometry().setLocalTranslation(-500, 0, 1000);
        RigidBodyControl control = new RigidBodyControl(mass);
        this.getGeometry().addControl(control);
        state.getPhysicsSpace().add(control);
        this.setControl(control);
    }

    public void setMaterial(AssetManager manager, String modelPath, Vector3f position) {
        Material voyagerMaterial;
        Spatial newSpatial;
        newSpatial = manager.loadModel(modelPath);
        newSpatial.setLocalTranslation(position);
        newSpatial.scale(scale);
        voyagerMaterial = new Material(
                manager,
                "Common/MatDefs/Misc/ShowNormals.j3md"
        );

        newSpatial.setMaterial(voyagerMaterial);
        newSpatial.setName(this.name);
        this.setGeometry(newSpatial);
    }

    // Generated getters and setters

    public Spatial getGeometry() {
        return geometry;
    }

    public void setGeometry(Spatial geometry) {
        this.geometry = geometry;
    }
}
