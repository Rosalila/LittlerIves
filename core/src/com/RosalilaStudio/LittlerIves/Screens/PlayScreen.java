package com.RosalilaStudio.LittlerIves.Screens;

import com.RosalilaStudio.LittlerIves.GlobalNPCs;
import com.RosalilaStudio.LittlerIves.LittlerIves;
import com.RosalilaStudio.LittlerIves.State;
import com.RosalilaStudio.LittlerIves.Characters.Character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class PlayScreen extends AbstractScreen {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Texture koalaTexture;
	private Animation stand;
	private Animation walk;
	private Animation jump;
	private Character Ives;

	int NIVEL = 1;

	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject() {
			return new Rectangle();
		}
	};
	private Array<Rectangle> tiles = new Array<Rectangle>();

	private static final float GRAVITY = -2.5f;

	public PlayScreen(LittlerIves game, int level) {
		super(game);
		NIVEL = level;
	}

	@Override
	public void show() {
		// load the koala frames, split them, and assign them to Animations
		koalaTexture = new Texture("ivis2.png");
		TextureRegion[] regions = TextureRegion.split(koalaTexture, 18, 26)[0];
		stand = new Animation(0, regions[0]);
		jump = new Animation(0, regions[1]);
		walk = new Animation(0.15f, regions[2], regions[3], regions[4]);
		walk.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

		// figure out the width and height of the koala for collision
		// detection and rendering by converting a koala frames pixel
		// size into world units (1 unit == 16 pixels)
		Ives.WIDTH = 1 / 16f * regions[0].getRegionWidth();
		Ives.HEIGHT = 1 / 16f * regions[0].getRegionHeight();

		// load the map, set the unit scale to 1/16 (1 unit == 16 pixels)
		map = new TmxMapLoader().load("nivel" + NIVEL + ".tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 16f);

		// create an orthographic camera, shows us 30x20 units of the world
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 30, 20);
		camera.update();

		// create the Koala we want to move around the world
		Ives = new Character();
		Ives.position.set(20, 15);

		Music oggMusic = Gdx.audio.newMusic(Gdx.files.internal("music.ogg"));
		oggMusic.play();

		GlobalNPCs.init(NIVEL);
	}

	@Override
	public void render(float delta) {
		// clear the screen
		Gdx.gl.glClearColor(0.7f, 0.7f, 1.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// get the delta time
		float deltaTime = Gdx.graphics.getDeltaTime();

		// update the koala (process input, collision detection, position
		// update)
		updateKoala(deltaTime);

		// let the camera follow the koala, x-axis only
		camera.position.x = Ives.position.x;
		camera.update();

		// set the tile map rendere view based on what the
		// camera sees and render the map
		renderer.setView(camera);
		renderer.render();

		// render the koala
		renderKoala(deltaTime);
	}
	
	private void updateKoala(float deltaTime) {
		if(deltaTime == 0) return;
		Ives.stateTime += deltaTime;	

		// check input and apply to velocity & state
		if((Gdx.input.isKeyPressed(Keys.SPACE) || isTouched(0.75f, 1)) && Ives.grounded) {
			Ives.velocity.y += Character.JUMP_VELOCITY;
			Ives.state = State.Jumping;
			Ives.grounded = false;
		}

		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A) || isTouched(0, 0.25f)) {
			Ives.velocity.x = -Character.MAX_VELOCITY;
			if(Ives.grounded) Ives.state = State.Walking;
			Ives.facesRight = false;
		}

		if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D) || isTouched(0.25f, 0.5f)) {
			Ives.velocity.x = Character.MAX_VELOCITY;
			if(Ives.grounded) Ives.state = State.Walking;
			Ives.facesRight = true;
		}

		// apply gravity if we are falling
		Ives.velocity.add(0, GRAVITY);

		// clamp the velocity to the maximum, x-axis only
		if(Math.abs(Ives.velocity.x) > Character.MAX_VELOCITY) {
			Ives.velocity.x = Math.signum(Ives.velocity.x) * Character.MAX_VELOCITY;
		}

		// clamp the velocity to 0 if it's < 1, and set the state to standign
		if(Math.abs(Ives.velocity.x) < 1) {
			Ives.velocity.x = 0;
			if(Ives.grounded) Ives.state = State.Standing;
		}

		// multiply by delta time so we know how far we go
		// in this frame
		Ives.velocity.scl(deltaTime);

		// perform collision detection & response, on each axis, separately
		// if the koala is moving right, check the tiles to the right of it's
		// right bounding box edge, otherwise check the ones to the left
		Rectangle koalaRect = rectPool.obtain();
		koalaRect.set(Ives.position.x, Ives.position.y, Character.WIDTH, Character.HEIGHT);
		int startX, startY, endX, endY;
		if(Ives.velocity.x > 0) {
			startX = endX = (int)(Ives.position.x + Character.WIDTH + Ives.velocity.x);
		} else {
			startX = endX = (int)(Ives.position.x + Ives.velocity.x);
		}
		startY = (int)(Ives.position.y);
		endY = (int)(Ives.position.y + Character.HEIGHT);
		getTiles(startX, startY, endX, endY, tiles,1);
		koalaRect.x += Ives.velocity.x;
		for(Rectangle tile: tiles) {
			if(koalaRect.overlaps(tile)) {
				Ives.velocity.x = 0;
				break;
			}
		}
		koalaRect.x = Ives.position.x;

		// if the koala is moving upwards, check the tiles to the top of it's
		// top bounding box edge, otherwise check the ones to the bottom
		if(Ives.velocity.y > 0) {
			startY = endY = (int)(Ives.position.y + Character.HEIGHT + Ives.velocity.y);
		} else {
			startY = endY = (int)(Ives.position.y + Ives.velocity.y);
		}
		startX = (int)(Ives.position.x);
		endX = (int)(Ives.position.x + Character.WIDTH);
		getTiles(startX, startY, endX, endY, tiles,1);
		koalaRect.y += Ives.velocity.y;
		for(Rectangle tile: tiles) {
			if(koalaRect.overlaps(tile)) {
				// we actually reset the koala y-position here
				// so it is just below/above the tile we collided with
				// this removes bouncing :)
				if(Ives.velocity.y > 0) {
					Ives.position.y = tile.y - Character.HEIGHT;
					// we hit a block jumping upwards, let's destroy it!
					TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(1);
//					layer.setCell((int)tile.x, (int)tile.y, null);
				} else {
					Ives.position.y = tile.y + tile.height;
					// if we hit the ground, mark us as grounded so we can jump
					Ives.grounded = true;
				}
				Ives.velocity.y = 0;
				break;
			}
		}
		//Inicio cambio
		getTiles(startX, startY, endX, endY, tiles,2);
		for(Rectangle tile: tiles) {
			if(koalaRect.overlaps(tile)) {
				TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(2);
				layer.setCell((int)tile.x, (int)tile.y, null);
			}
		}
		//fin cambio
		rectPool.free(koalaRect);

		// unscale the velocity by the inverse delta time and set 
		// the latest position
		Ives.position.add(Ives.velocity);
		Ives.velocity.scl(1/deltaTime);

		// Apply damping to the velocity on the x-axis so we don't
		// walk infinitely once a key was pressed
		Ives.velocity.x *= Character.DAMPING;

	}

	private boolean isTouched(float startX, float endX) {
		// check if any finge is touch the area between startX and endX
		// startX/endX are given between 0 (left edge of the screen) and 1 (right edge of the screen)
		for(int i = 0; i < 2; i++) {
			float x = Gdx.input.getX() / (float)Gdx.graphics.getWidth();
			if(Gdx.input.isTouched(i) && (x >= startX && x <= endX)) {
				return true;
			}
		}
		return false;
	}

	private void getTiles(int startX, int startY, int endX, int endY, Array<Rectangle> tiles, int num_layer) {
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(num_layer);
		rectPool.freeAll(tiles);
		tiles.clear();
		for(int y = startY; y <= endY; y++) {
			for(int x = startX; x <= endX; x++) {
				Cell cell = layer.getCell(x, y);
				if(cell != null)
				{
					TiledMapTile tile = cell.getTile();
					MapProperties properties = tile.getProperties();
					if(properties.containsKey("hola"))
						System.out.println("test");
					
					
					Rectangle rect = rectPool.obtain();
					rect.set(x, y, 1, 1);
					tiles.add(rect);
				}
			}
		}
	}

	private void renderKoala(float deltaTime) {
		// based on the koala state, get the animation frame
		TextureRegion frame = null;
		switch(Ives.state) {
			case Standing: frame = stand.getKeyFrame(Ives.stateTime); break;
			case Walking: frame = walk.getKeyFrame(Ives.stateTime); break;
			case Jumping: frame = jump.getKeyFrame(Ives.stateTime); break; 
		}

		// draw the koala, depending on the current velocity
		// on the x-axis, draw the koala facing either right
		// or left
		Batch batch = renderer.getSpriteBatch();
		batch.begin();
		if(Ives.facesRight) {
			batch.draw(frame, Ives.position.x, Ives.position.y, Character.WIDTH, Character.HEIGHT);
		} else {
			batch.draw(frame, Ives.position.x + Character.WIDTH, Ives.position.y, -Character.WIDTH, Character.HEIGHT);
		}
		GlobalNPCs.render(batch,NIVEL);
		batch.end();
	}

}
