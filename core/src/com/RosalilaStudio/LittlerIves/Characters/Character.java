package com.RosalilaStudio.LittlerIves.Characters;

import com.RosalilaStudio.LittlerIves.State;
import com.badlogic.gdx.math.Vector2;
<<<<<<< HEAD
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
=======

public class Character {
	
	public static float WIDTH;
	public static float HEIGHT;
	public static float MAX_VELOCITY = 10f;
	public static float JUMP_VELOCITY = 40f;
	public static float DAMPING = 0.87f;
	
	public final Vector2 position = new Vector2();
	public final Vector2 velocity = new Vector2();
	public State state = State.Walking;
	public float stateTime = 0;
	public boolean facesRight = true;
	public boolean grounded = false;

	public Character() {
		
>>>>>>> aa45a5b3ab4fc5231618cb211aff2351e5706c5e
	}

}
