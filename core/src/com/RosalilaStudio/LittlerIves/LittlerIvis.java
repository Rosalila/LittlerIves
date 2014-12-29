package com.RosalilaStudio.LittlerIves;

import com.RosalilaStudio.LittlerIves.Screens.AbstractScreen;
import com.RosalilaStudio.LittlerIves.Screens.MenuScreen;
import com.RosalilaStudio.LittlerIves.Screens.PlayScreen;
import com.badlogic.gdx.Game;

public class LittlerIvis extends Game {
	
	public static AbstractScreen MAIN, PLAY;
	
	public LittlerIvis() {
		MAIN = new MenuScreen(this);
		PLAY = new PlayScreen(this);
	}
	
	@Override
	public void create () {
		setScreen(MAIN);
	}
}
