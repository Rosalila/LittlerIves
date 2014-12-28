package com.RosalilaStudio.LittlerIves.Listeners;

import com.RosalilaStudio.LittlerIves.LittlerIves;
import com.RosalilaStudio.LittlerIves.Screens.PlayScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class InputDYAListener extends InputListener {

	Image btn;
	LittlerIves game;
	int num;

	public InputDYAListener(Image btn, int num, LittlerIves game) {
		this.btn = btn;
		this.num = num;
		this.game = game;
	}

	@Override
	public boolean touchDown(InputEvent e, float x, float y, int pointer,
			int button) {
		game.setScreen(new PlayScreen(game, num));
		System.out.println("TouchDown" + num + "!!");
		return true;

	}

	@Override
	public void touchUp(InputEvent e, float x, float y, int pointer, int button) {
		btn.setColor(Color.WHITE);

	}

}
