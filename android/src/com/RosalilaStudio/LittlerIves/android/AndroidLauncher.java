package com.RosalilaStudio.LittlerIves.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
<<<<<<< HEAD
import com.RosalilaStudio.LittlerIves.LittlerIvis;
=======
import com.RosalilaStudio.LittlerIves.LittlerIves;
>>>>>>> aa45a5b3ab4fc5231618cb211aff2351e5706c5e

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
<<<<<<< HEAD
		initialize(new LittlerIvis(), config);
=======
		initialize(new LittlerIves(), config);
>>>>>>> aa45a5b3ab4fc5231618cb211aff2351e5706c5e
	}
}
