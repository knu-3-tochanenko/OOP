package com.tochanenko.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tochanenko.GameCore;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Labyrinth by Vladislav Tochanenko";
        config.width = 1184;
        config.height = 640;
        config.x = 64;
        config.y = 64;
        new LwjglApplication(new GameCore(), config);
    }
}
