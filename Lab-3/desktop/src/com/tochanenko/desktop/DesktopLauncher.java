package com.tochanenko.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tochanenko.GameCore;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Labyrinth by Vladislav Tochanenko";
        config.width = 1184;
        config.height = 800;
        new LwjglApplication(new GameCore(), config);
    }
}
