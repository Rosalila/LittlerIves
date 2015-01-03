package com.RosalilaStudio.LittlerIves.Characters;

import com.RosalilaStudio.LittlerIves.LittlerIvis;
import com.RosalilaStudio.LittlerIves.Path;
import com.RosalilaStudio.LittlerIves.State;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Character extends Actor {
	// Name of the Character
	private String name;

	// State, maxVelocity, jumpVelocity, damping and stateTime
	public State state;
	public float maxVelocity, jumpVelocity, damping, stateTime;

	// Velocity, animation and direction of the Character
	private final Vector2 velocity;
	private boolean facesRight, grounded;

	// Textures and Animations
	private Texture texture;
	private TextureRegion frame;
	private Animation stand, walk, jump;
	
	// Box to detect when overLaps
	public Rectangle bb;
	
	public Character(float x, float y, String name) {
		this.name=name;
		maxVelocity = 10f;
		jumpVelocity = 40f;
		damping = 0.87f;
		velocity = new Vector2();
		state = State.Standing;
		stateTime = 0;
		facesRight = false;
		grounded = false;
		setPosition(x, y);
		init();
	}

	public Character(String name) {
		this(0, 0, name);
	}

	private void init() {
		// load the Ivis frames, split them, and assign them to Animations
		texture = LittlerIvis.MANAGER.get(Path.C.getPath(name)); //new Texture(Path.C.getPath(name)); //
		TextureRegion[] regions = TextureRegion.split(texture, 18, 26)[0];
		stand = new Animation(0, regions[0]);
		jump = new Animation(0, regions[1]);
		walk = new Animation(0.15f, regions[2], regions[3], regions[4]);
		walk.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		// figure out the width and height of the Ivis for collision
		// detection and rendering by converting a Ivis frames pixel
		// size into world units (1 unit == 16 pixels)
		setWidth(1 / 16f * regions[0].getRegionWidth());
		setHeight(1 / 16f * regions[0].getRegionHeight());
		
		bb = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		update(delta);
		bb.x = getX();
		bb.y = getY();
		bb.width = getWidth();
		bb.height = getHeight();
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
			batch.draw(frame, getX(), getY(), getWidth(), // exch for position
					getHeight());
		} else {
			batch.draw(frame, getX() + getWidth(), getY(), // exch for position
					-getWidth(), getHeight());
		}
	}
	
	public void addPosition(){
		setX(getX()+velocity.x);
		setY(getY()+velocity.y);
	}
	
	public void addVelocity(float x, float y){
		velocity.x+=x;
		velocity.y+=y;
	}
	
	public void addVelocityX(int x){
		switch(x){
			case 1: velocity.x	=  maxVelocity; break;
			case -1: velocity.x	= -maxVelocity; break;
		}
	}
	
	public void addVelocityY() {
		velocity.y+=jumpVelocity;
	}
	
	private void update(float delta){
		stateTime += delta;
		
		// unscale the velocity by the inverse delta time and set
		// the latest position
		addPosition();
		velocity.scl(1 / delta);

		// Apply damping to the velocity on the x-axis so we don't
		// walk infinitely once a key was pressed
		velocity.x *= damping;
	}
	
	//Seters
	public void grounded() {
		grounded = true;
	}
	
	public void unGrounded() {
		grounded = false;
	}
	
	/**
	 * call to setPosition's Parent and set faceRight gave into param faceRight
	 * @param x
	 * @param y
	 * @param faceRight
	 */
	public void setPosition(float x, float y, boolean faceRight) {
		setPosition(x, y);
		facesRight=faceRight;
	}
	
	public void setVelocityX(float x){
		velocity.x=x;
	}
	
	public void setVelocityY(float y){
		velocity.y=y;
	}
	
	public void faceRight() {
		facesRight = true;
	}
	public void faceLeft() {
		facesRight = false;
	}
	
	//Geters
	public boolean isGrounded(){
		return grounded;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public boolean isFaceRight(){
		return facesRight;
	}

}
