package com.RosalilaStudio.LittlerIves.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.RosalilaStudio.LittlerIves.LittlerIves;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = 512;
		config.height = 275;
		
		new LwjglApplication(new LittlerIves(), config);
	}
}
