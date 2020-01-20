import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;

public class Jupiter extends SpaceObject {
    private int mass;

    private Material material;
    private Sphere sphere;
    private Geometry geometry;

    public Jupiter() {
        this.name = Config.Jupiter.NAME;
        this.mass = Config.Jupiter.MASS;

        sphere = new Sphere(
                Config.Jupiter.SPHERE_SAMPLES,
                Config.Jupiter.SPHERE_SAMPLES,
                Config.Jupiter.RADIUS,
                true,
                false
        );
        // Allow texture to be be flat on sphere
        sphere.setTextureMode(Sphere.TextureMode.Projected);
    }

    void setPhysics(BulletAppState state) {
        RigidBodyControl control = new RigidBodyControl(mass);
        this.getGeometry().addControl(control);
        state.getPhysicsSpace().add(control);
        this.setControl(control);
    }

    void setMaterial(AssetManager manager) {
        Material newMaterial;
        newMaterial = new Material(
                manager,
                Config.Jupiter.DEF_NAME
        );

        TextureKey textureKey = new TextureKey(Config.Jupiter.TEXTURE_PATH);
        textureKey.setGenerateMips(true);
        Texture texture = manager.loadTexture(textureKey);
        texture.setWrap(Texture.WrapMode.Repeat);

        newMaterial.setTexture("ColorMap", texture);
        this.material = newMaterial;
    }

    public void setGeometry() {
        Geometry newGeometry = new Geometry(this.name, this.getSphere());
        newGeometry.setName(this.name);
        newGeometry.setMaterial(this.getMaterial());
        newGeometry.setLocalTranslation(Config.Jupiter.POSITION);
        newGeometry.rotate(Config.Jupiter.ROTATION, 0, 0);
        this.geometry = newGeometry;
    }

    // Generated getters and setters

    private Material getMaterial() {
        return material;
    }

    private Sphere getSphere() {
        return sphere;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public int getMass() {
        return mass;
    }
}
