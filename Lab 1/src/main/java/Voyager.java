import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class Voyager extends SpaceObject {
    private float scale;

    private Spatial geometry;

    public Voyager() {
        this.name = Config.Voyager.NAME;
        this.scale = Config.Voyager.SCALE;
    }

    void start(){
        control.setLinearVelocity(new Vector3f(Config.Voyager.SPEED, 0, 0));
    }

    void setPhysics(BulletAppState state) {
        this.getGeometry().setLocalTranslation(-500, 0, 1000);
        RigidBodyControl control = new RigidBodyControl(Config.Voyager.MASS);
        this.getGeometry().addControl(control);
        state.getPhysicsSpace().add(control);
        this.setControl(control);
    }

    public void setMaterial(AssetManager manager) {
        Material voyagerMaterial;
        Spatial newSpatial;
        newSpatial = manager.loadModel(Config.Voyager.MODEL_PATH);
        newSpatial.setLocalTranslation(Config.Voyager.POSITION);
        newSpatial.scale(scale);
        voyagerMaterial = new Material(
                manager,
                Config.Voyager.DEF_NAME
        );

        newSpatial.setMaterial(voyagerMaterial);
        newSpatial.setName(this.name);
        this.setGeometry(newSpatial);
    }

    // Generated getters and setters

    public Spatial getGeometry() {
        return geometry;
    }

    private void setGeometry(Spatial geometry) {
        this.geometry = geometry;
    }
}
