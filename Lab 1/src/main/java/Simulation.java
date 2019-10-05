import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;

public class Simulation extends SimpleApplication {

    @Override
    public void simpleInitApp() {
        Box b = new Box(5, 0, 5); // create cube shape
        Geometry geom = new Geometry("Box", b);  // create cube geometry from the shape
        Material mat = new Material(assetManager,
                "Common/MatDefs/Light/Lighting.j3md");  // create a simple material
        Material mat2 = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.Green);
        mat.setColor("Ambient", ColorRGBA.White);
        geom.setMaterial(mat2);                   // set the cube's material
        rootNode.attachChild(geom);              // make the cube appear in the scene

        Sphere s = new Sphere(32, 32, 1);
        Geometry geomSphere = new Geometry("Sphere", s);
        geomSphere.setMaterial(mat);
        rootNode.attachChild(geomSphere);

        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.Yellow.mult(1.3f));
        rootNode.addLight(al);
    }
}
