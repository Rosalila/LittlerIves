package com.RosalilaStudio.LittlerIves.Characters;

import com.RosalilaStudio.LittlerIves.Paths;
import com.RosalilaStudio.LittlerIves.State;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Character extends Actor {
	private String name;

	public float MAX_VELOCITY;
	public float JUMP_VELOCITY;
	public float DAMPING;

	// Velocity, State and direction of the Character
	private final Vector2 velocity;
	public State state;
	public float stateTime;
	public boolean facesRight;
	public boolean grounded;
	public boolean animation;

	// Textures and Animations
	private Texture koalaTexture;
	private TextureRegion frame;
	private Animation stand;
	private Animation walk;
	private Animation jump;
	
	// Box
	public Rectangle bb;

	public Character(String name) {
		this.name=name;
		MAX_VELOCITY = 10f;
		JUMP_VELOCITY = 40f;
		DAMPING = 0.87f;
		velocity = new Vector2();
		state = State.Standing;
		stateTime = 0;
		facesRight = true;
		grounded = false;
		animation = true;
		init();
	}

	private void init() {
		// load the Ivis frames, split them, and assign them to Animations
		koalaTexture = new Texture(Paths.C.getPath(name));
		TextureRegion[] regions = TextureRegion.split(koalaTexture, 18, 26)[0];
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
		bb.set(getX(), getY(), getWidth(), getHeight());
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
			case 1: velocity.x	=  MAX_VELOCITY; break;
			case -1: velocity.x	= -MAX_VELOCITY; break;
		}
	}
	
	public void addVelocityY() {
		velocity.y+=JUMP_VELOCITY;
	}
	
	private void update(float delta){
		
		// unscale the velocity by the inverse delta time and set
		// the latest position
		addPosition();
		velocity.scl(1 / delta);

		// Apply damping to the velocity on the x-axis so we don't
		// walk infinitely once a key was pressed
		velocity.x *= DAMPING;
	}
	
	//Seters
//	public void setStateTime(float stateTime) {
//		this.stateTime += stateTime;
//	}
//	
//	public void setState(State state) {
//		this.state = state;
//	}
//	
//	public void setGrounded(boolean grounded) {
//		this.grounded = grounded;
//	}
	
	public void setVelocityX(float x){
		velocity.x=x;
	}
	
	public void setVelocityY(float y){
		velocity.y=y;
	}
	
//	public void setFacesRight(boolean facesRight) {
//		this.facesRight = facesRight;
//	}
	
	//Geters
//	public boolean isGrounded(){
//		return grounded;
//	}
//	
//	public Vector2 getPosition() {
//		return position;
//	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public Animation getStand() {
		return stand;
	}
	
//	public State getState() {
//		return state;
//	}
//	
//	public float getStateTime() {
//		return stateTime;
//	}

}
