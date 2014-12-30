package com.RosalilaStudio.LittlerIves.Characters;

import com.RosalilaStudio.LittlerIves.Paths;
import com.RosalilaStudio.LittlerIves.State;
import com.RosalilaStudio.LittlerIves.Screens.PlayScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Character extends Actor {

	public static float WIDTH;
	public static float HEIGHT;
	public static float MAX_VELOCITY;
	public static float JUMP_VELOCITY;
	public static float DAMPING;

	public final Vector2 position;
	public final Vector2 velocity;
	public State state;
	public float stateTime;
	public boolean facesRight;
	public boolean grounded;

	private Texture koalaTexture;
	private TextureRegion frame;
	private Animation stand;
	private Animation walk;
	private Animation jump;

	public Character() {
		MAX_VELOCITY = 10f;
		JUMP_VELOCITY = 40f;
		DAMPING = 0.87f;
		position = new Vector2();
		velocity = new Vector2();
		state = State.Walking;
		stateTime = 0;
		facesRight = true;
		grounded = false;
		init();
	}

	private void init() {
		// load the Ivis frames, split them, and assign them to Animations
		koalaTexture = new Texture(Paths.C.getPath("ivis2.png"));
		TextureRegion[] regions = TextureRegion.split(koalaTexture, 18, 26)[0];
		stand = new Animation(0, regions[0]);
		jump = new Animation(0, regions[1]);
		walk = new Animation(0.15f, regions[2], regions[3], regions[4]);
		walk.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		// figure out the width and height of the Ivis for collision
		// detection and rendering by converting a Ivis frames pixel
		// size into world units (1 unit == 16 pixels)
		WIDTH = 1 / 16f * regions[0].getRegionWidth();
		HEIGHT = 1 / 16f * regions[0].getRegionHeight();
	}

	@Override
	public void act(float delta) {
		super.act(delta);

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		// based on the Ivis state, get the animation frame
		frame = null;
		switch (state) {
		case Standing:
			frame = stand.getKeyFrame(stateTime);
			break;
		case Walking:
			frame = walk.getKeyFrame(stateTime);
			break;
		case Jumping:
			frame = jump.getKeyFrame(stateTime);
			break;
		}

		// draw Ivis, depending on the current velocity
		// on the x-axis, draw the Ivis facing either right
		// or left
		if (facesRight) {
			batch.draw(frame, position.x, position.y, Character.WIDTH,
					Character.HEIGHT);
		} else {
			batch.draw(frame, position.x + Character.WIDTH, position.y,
					-Character.WIDTH, Character.HEIGHT);
		}
	}

}
