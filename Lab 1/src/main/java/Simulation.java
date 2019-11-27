import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.util.SkyFactory;

import java.util.ArrayList;
import java.util.List;

public class Simulation extends SimpleApplication {
    // Scene objects
    private Voyager voyager = new Voyager("Voyager 2", 130.0f, 0.05f);
    private Jupiter jupiter = new Jupiter("Jupiter", 5_000_000, 200);

    // Scene properties
    private static final int G = 10;
    private static final int CAM_SPEED = 200;
    private static final Vector3f ZERO_GRAVITY = new Vector3f(0, 0, 0);

    private BitmapText footer;

    private List<Float> speeds = new ArrayList<>();
    private List<Float> Gs = new ArrayList<>();
    private boolean isSelfTerminated = false;
    private int secondsTillTermination = 0;
    private long startTime = 0l, endTime = 0l;

    public Simulation(boolean isSelfTerminated, int secondsTillTermination) {
        this.isSelfTerminated = isSelfTerminated;
        this.secondsTillTermination = secondsTillTermination;
    }

    Simulation() {}

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(CAM_SPEED);

        // Disable standard gravity
        BulletAppState bullet = new BulletAppState();
        stateManager.attach(bullet);
        bullet.getPhysicsSpace().setGravity(ZERO_GRAVITY);
        bullet.getPhysicsSpace().setAccuracy(1f / 160f);

        initJupiter(bullet);
        initVoyager(bullet);

        addLight(rootNode);
        cam.setLocation(jupiter.getGeometry().getLocalTranslation().add(-900, 400, 500));

        setFooter();

        voyager.start();

        cam.setFrustumFar(10000);
        cam.onFrameChange();

        getRootNode().attachChild(
                SkyFactory.createSky(getAssetManager(),
                        "Textures/sky.jpg",
                        SkyFactory.EnvMapType.SphereMap)
        );

        this.startTime = System.currentTimeMillis();
    }

    private void initJupiter(BulletAppState bullet) {
        jupiter.setMaterial(assetManager, "Textures/jupiter.jpg");
        jupiter.setGeometry(new Vector3f(0, 2, -20), 1.3f, 0, 0);
        jupiter.setPhysics(bullet, jupiter.getMass());
        rootNode.attachChild(jupiter.getGeometry());
    }

    private void initVoyager(BulletAppState bullet) {
        voyager.setMaterial(
                assetManager,
                "Models/cat.obj",
                new Vector3f(-500, 0, 1000)
        );
        voyager.setPhysics(bullet, 1f);
        rootNode.attachChild(voyager.getGeometry());
    }

    void addLight(Node rootNode) {
        DirectionalLight light = new DirectionalLight();
        light.setDirection(new Vector3f(1, 0, -2).normalizeLocal());
        light.setColor(ColorRGBA.Yellow);
        rootNode.addLight(light);
    }

    private void setFooter() {
        footer = new BitmapText(guiFont, false);
        footer.setText("");
        footer.setLocalTranslation(
                settings.getWidth() / 2.0f - guiFont.getCharSet().getRenderedSize() / 2.0f,
                20, 0);
        guiNode.attachChild(footer);
    }

    @Override
    public void simpleUpdate(float tpf) {
        Vector3f jupiterPosition = jupiter.getGeometry().getLocalTranslation();
        Vector3f voyagerPosition = voyager.getGeometry().getLocalTranslation();

        float x = jupiterPosition.x - voyagerPosition.x;
        float y = jupiterPosition.y - voyagerPosition.y;
        float z = jupiterPosition.z - voyagerPosition.z;

        float length = (float) Math.sqrt(x * x + y * y + z * z);
        float gravity = jupiter.getMass() * G / (length * length);
        Vector3f newPosition = new Vector3f(
                gravity * x / length,
                gravity * y / length,
                gravity * z / length);
        voyager.getControl().setGravity(
                newPosition
        );

        speeds.add(voyager.getControl().getLinearVelocity().length());
        Gs.add(gravity);

        cam.setLocation(
                voyagerPosition.add(
                        new Vector3f(-x * .2f, 30, -z * .2f)
                )
        );
        cam.lookAt(jupiterPosition, voyagerPosition);

        footer.setText(
                "Speed : " + String.format("%.3f", voyager.getControl().getLinearVelocity().length()) +
                        "\t Gravity : " + String.format("%.3f", gravity) +
                        "\t Distance : " + String.format("%.3f", length));

        if (isSelfTerminated) {
            endTime = System.currentTimeMillis();
            if (endTime - startTime >= secondsTillTermination * 1000l) {
                stop();
            }
        }
    }

    public List getGs() {
        return Gs;
    }

    public List getSpeeds() {
        return speeds;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

}
