package com.RosalilaStudio.LittlerIves.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
<<<<<<< HEAD
import com.RosalilaStudio.LittlerIves.LittlerIvis;
=======
import com.RosalilaStudio.LittlerIves.LittlerIves;
>>>>>>> aa45a5b3ab4fc5231618cb211aff2351e5706c5e

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = 512;
		config.height = 275;
		
<<<<<<< HEAD
		new LwjglApplication(new LittlerIvis(), config);
=======
		new LwjglApplication(new LittlerIves(), config);
>>>>>>> aa45a5b3ab4fc5231618cb211aff2351e5706c5e
	}
}
