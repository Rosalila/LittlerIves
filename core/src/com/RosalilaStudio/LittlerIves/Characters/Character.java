package com.RosalilaStudio.LittlerIves.Characters;

import com.RosalilaStudio.LittlerIves.State;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Character extends Actor{
	
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
	}

}
