package com.RosalilaStudio.LittlerIves.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
<<<<<<< HEAD
import com.RosalilaStudio.LittlerIves.LittlerIvis;
=======
import com.RosalilaStudio.LittlerIves.LittlerIves;
>>>>>>> aa45a5b3ab4fc5231618cb211aff2351e5706c5e

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener getApplicationListener () {
<<<<<<< HEAD
                return new LittlerIvis();
=======
                return new LittlerIves();
>>>>>>> aa45a5b3ab4fc5231618cb211aff2351e5706c5e
        }
}