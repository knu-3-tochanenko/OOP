import com.jme3.math.Vector3f;

class Config {
    static class Jupiter {
        static final int SPHERE_SAMPLES = 32;
        static final String NAME = "Jupiter";
        static final String DEF_NAME = "Common/MatDefs/Misc/Unshaded.j3md";
        static final String TEXTURE_PATH = "Textures/jupiter.jpg";
        static final Vector3f POSITION = new Vector3f(0, 2, -20);
        static final int MASS = 5_000_000;
        static final int RADIUS = 200;
        static final float ROTATION = 1.3f;
    }

    static class Voyager {
        static final String NAME = "Voyager 2";
        static final String DEF_NAME = "Common/MatDefs/Misc/ShowNormals.j3md";
        static final String MODEL_PATH = "Models/cat.obj";
        static final Vector3f POSITION = new Vector3f(-500, 0, 1000);
        static final float SPEED = 130.0f;
        static final float SCALE = 0.05f;
        static final float MASS = 1f;
    }

    static class App {
        static final int G = 10;
        static final int CAM_SPEED = 200;
        static final String SKY_TEXTURE = "Textures/sky.jpg";
        static final Vector3f LIGHT_DIRECTION = new Vector3f(1, 0, -2);
    }
}
