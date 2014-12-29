package com.RosalilaStudio.LittlerIves.Listeners;

<<<<<<< HEAD
import com.RosalilaStudio.LittlerIves.GlobalNPCs;
import com.RosalilaStudio.LittlerIves.LittlerIvis;
=======
import com.RosalilaStudio.LittlerIves.LittlerIves;
>>>>>>> aa45a5b3ab4fc5231618cb211aff2351e5706c5e
import com.RosalilaStudio.LittlerIves.Screens.PlayScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class InputDYAListener extends InputListener {

<<<<<<< HEAD
	private Image btn;
	private LittlerIvis game;
	private int num;

	public InputDYAListener(Image btn, int num, LittlerIvis game) {
=======
	Image btn;
	LittlerIves game;
	int num;

	public InputDYAListener(Image btn, int num, LittlerIves game) {
>>>>>>> aa45a5b3ab4fc5231618cb211aff2351e5706c5e
		this.btn = btn;
		this.num = num;
		this.game = game;
	}

	@Override
	public boolean touchDown(InputEvent e, float x, float y, int pointer,
			int button) {
<<<<<<< HEAD
		GlobalNPCs.level=num;
		game.setScreen(new PlayScreen(game));
=======
		game.setScreen(new PlayScreen(game, num));
>>>>>>> aa45a5b3ab4fc5231618cb211aff2351e5706c5e
		System.out.println("TouchDown" + num + "!!");
		return true;

	}

	@Override
	public void touchUp(InputEvent e, float x, float y, int pointer, int button) {
		btn.setColor(Color.WHITE);

	}

}
