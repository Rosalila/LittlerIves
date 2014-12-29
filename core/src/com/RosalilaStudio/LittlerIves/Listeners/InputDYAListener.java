package com.RosalilaStudio.LittlerIves.Listeners;

import com.RosalilaStudio.LittlerIves.GlobalNPCs;
import com.RosalilaStudio.LittlerIves.LittlerIvis;
import com.RosalilaStudio.LittlerIves.Screens.InfoScreen;
import com.RosalilaStudio.LittlerIves.Screens.PlayScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class InputDYAListener extends InputListener {

	private Image btn;
	private LittlerIvis game;
	private int num;

	public InputDYAListener(Image btn, int num, LittlerIvis game) {
		this.btn = btn;
		this.num = num;
		this.game = game;
	}

	@Override
	public boolean touchDown(InputEvent e, float x, float y, int pointer,
			int button) {
		GlobalNPCs.level=num;
		game.setScreen(game.INFO);
		System.out.println("TouchDown" + num + "!!");
		return true;

	}

	@Override
	public void touchUp(InputEvent e, float x, float y, int pointer, int button) {
		btn.setColor(Color.WHITE);

	}

}
