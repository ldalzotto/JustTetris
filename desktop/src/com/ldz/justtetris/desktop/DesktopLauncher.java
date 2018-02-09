package com.ldz.justtetris.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ldz.justtetris.JustTetrisGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 120;
        config.height = 360;
        new LwjglApplication(new JustTetrisGame(), config);
    }
}
