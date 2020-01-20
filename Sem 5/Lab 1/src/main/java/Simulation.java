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
    private Voyager voyager = new Voyager();
    private Jupiter jupiter = new Jupiter();

    // Scene properties
    private static final int G = Config.App.G;

    private BitmapText footer;

    private List<Float> speeds = new ArrayList<>();
    private List<Float> Gs = new ArrayList<>();
    private boolean isSelfTerminated = false;
    private int secondsTillTermination = 0;
    private long startTime = 0L;

    public Simulation(boolean isSelfTerminated, int secondsTillTermination) {
        this.isSelfTerminated = isSelfTerminated;
        this.secondsTillTermination = secondsTillTermination;
    }

    Simulation() {}

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(Config.App.CAM_SPEED);

        // Disable standard gravity
        BulletAppState bullet = new BulletAppState();
        stateManager.attach(bullet);
        bullet.getPhysicsSpace().setGravity(Config.App.ZERO_GRAVITY);
        bullet.getPhysicsSpace().setAccuracy(Config.App.ACCURACY);

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
                        Config.App.SKY_TEXTURE,
                        SkyFactory.EnvMapType.SphereMap)
        );

        this.startTime = System.currentTimeMillis();
    }

    private void initJupiter(BulletAppState bullet) {
        jupiter.setMaterial(assetManager);
        jupiter.setGeometry();
        jupiter.setPhysics(bullet);
        rootNode.attachChild(jupiter.getGeometry());
    }

    private void initVoyager(BulletAppState bullet) {
        voyager.setMaterial(assetManager);
        voyager.setPhysics(bullet);
        rootNode.attachChild(voyager.getGeometry());
    }

    private void addLight(Node rootNode) {
        DirectionalLight light = new DirectionalLight();
        light.setDirection(Config.App.LIGHT_DIRECTION.normalizeLocal());
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

        float distance = (float) Math.sqrt(x * x + y * y + z * z);
        float newGravity = jupiter.getMass() * G / (distance * distance);
        Vector3f newPosition = new Vector3f(
                newGravity * x / distance,
                newGravity * y / distance,
                newGravity * z / distance);
        voyager.getControl().setGravity(
                newPosition
        );

        cam.setLocation(
                voyagerPosition.add(
                        new Vector3f(-x * .2f, 30, -z * .2f)
                )
        );
        cam.lookAt(jupiterPosition, voyagerPosition);

        footer.setText(
                "Speed : " + String.format("%.3f", voyager.getControl().getLinearVelocity().length()) +
                        "\t Gravity : " + String.format("%.3f", newGravity) +
                        "\t Distance : " + String.format("%.3f", distance));

        if (isSelfTerminated) {
            long endTime = System.currentTimeMillis();
            speeds.add(voyager.getControl().getLinearVelocity().length());
            Gs.add(newGravity);
            if (endTime - startTime >= secondsTillTermination * 1000L) {
                stop();
            }
        }
    }

    public List<Float> getGs() {
        return Gs;
    }

    public List<Float> getSpeeds() {
        return speeds;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

}
