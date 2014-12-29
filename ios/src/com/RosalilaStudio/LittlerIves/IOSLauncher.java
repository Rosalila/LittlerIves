package com.RosalilaStudio.LittlerIves;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
<<<<<<< HEAD
import com.RosalilaStudio.LittlerIves.LittlerIvis;
=======
import com.RosalilaStudio.LittlerIves.LittlerIves;
>>>>>>> aa45a5b3ab4fc5231618cb211aff2351e5706c5e

public class IOSLauncher extends IOSApplication.Delegate {
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
<<<<<<< HEAD
        return new IOSApplication(new LittlerIvis(), config);
=======
        return new IOSApplication(new LittlerIves(), config);
>>>>>>> aa45a5b3ab4fc5231618cb211aff2351e5706c5e
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }
}