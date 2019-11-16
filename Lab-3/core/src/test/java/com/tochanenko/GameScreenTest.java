package com.tochanenko;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameScreenTest {
    @Test
    public void constructor() {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Labyrinth by Vladislav Tochanenko";
        config.width = 1184;
        config.height = 640;
        config.x = 64;
        config.y = 64;
        assertDoesNotThrow(() -> new LwjglApplication(new GameCore(), config));
    }
}
