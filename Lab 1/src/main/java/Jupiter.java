import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;

public class Jupiter extends SpaceObject {
    private int mass;
    private float radius;

    private Material material;
    private Sphere sphere;
    private Geometry geometry;

    private static final int SPHERE_SAMPLES = 16;

    public Jupiter(String name, int mass, float radius) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;

        sphere = new Sphere(
                SPHERE_SAMPLES,
                SPHERE_SAMPLES,
                1
        );
        // Allow texture to be be flat on sphere
        sphere.setTextureMode(Sphere.TextureMode.Projected);
    }

    public void setMaterial(AssetManager manager, String texturePath) {
        Material newMaterial;
        newMaterial = new Material(
                manager,
                "Common/MatDefs/Misc/Unshaded.j3md"
        );

        TextureKey textureKey = new TextureKey(texturePath);
        textureKey.setGenerateMips(true);
        Texture texture = manager.loadTexture(textureKey);
        texture.setWrap(Texture.WrapMode.Repeat);

        newMaterial.setTexture("ColorMap", texture);
        this.material = newMaterial;
    }

    public void setGeometry(Vector3f translation,
                            float xAngle,
                            float yAngle,
                            float zAngle
    ) {
        Geometry newGeometry = new Geometry(this.name, this.getSphere());
        newGeometry.setName(this.name);
        newGeometry.setMaterial(this.getMaterial());
        newGeometry.setLocalTranslation(translation);
        newGeometry.rotate(xAngle, zAngle, yAngle);
        this.geometry = newGeometry;
    }

    // Generated getters and setters

    public Material getMaterial() {
        return material;
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


    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }
}
