package com.RosalilaStudio.LittlerIves.Screens;

import com.RosalilaStudio.LittlerIves.GlobalNPCs;
import com.RosalilaStudio.LittlerIves.LittlerIvis;
import com.RosalilaStudio.LittlerIves.Path;
import com.RosalilaStudio.LittlerIves.State;
import com.RosalilaStudio.LittlerIves.Characters.Character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
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
import com.badlogic.gdx.utils.Pools;

public class PlayScreen extends AbstractScreen {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Character Ivis;
	private boolean in, out;
	private int counterLevel2, counterLevel3;

	private Pool<Rectangle> rectPool = Pools.get(Rectangle.class);
	private Array<Rectangle> tiles = new Array<Rectangle>();

	public PlayScreen(LittlerIvis game) {
		super(game);
	}

	@Override
	public void show() {
		// Initialize of Variables
		in=true; out=true;
		counterLevel2 = 0; counterLevel3=0;

		// load the map, set the unit scale to 1/16 (1 unit == 16 pixels)
		map = new TmxMapLoader().load(Path.M.getPath("nivel" + GlobalNPCs.level + ".tmx"));
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 16f, game.getSb());

		// create an orthographic camera, shows us 30x20 units of the world
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 30, 20);
		camera.update();

		// create the Ivis we want to move around the world
		Ivis = new Character("ivis2.png");
		Ivis.setPosition(20, 15, true);

		Music oggMusic = Gdx.audio.newMusic(Gdx.files.internal(Path.S.getPath("music.ogg")));
		oggMusic.play();

		GlobalNPCs.init();
	}

	@Override
	public void render(float delta) {
		// clear the screen
		Gdx.gl.glClearColor(0.7f, 0.7f, 1.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// get the delta time
//		float deltaTime = Gdx.graphics.getDeltaTime();

		// update the Ivis (process input, collision detection, position
		// update)
		updateCharacter(delta);
		
		// let the camera follow Ivis, x-axis only
		camera.position.x = Ivis.getX();
		if(Ivis.getY()>11)
			camera.position.y = Ivis.getY();
		camera.update();
		
		// set the tile map rendere view based on what the
		// camera sees and render the map
		renderer.setView(camera);
		renderer.render();
		
		// render the Ivis
		renderCharacter(game.getSb());
		GlobalNPCs.detectColicions(Ivis, delta);
	}

	private void updateCharacter(float deltaTime) {
		
		if(deltaTime == 0) return;
//		Ivis.stateTime += deltaTime; //try it putting into act 

		// check input and apply to velocity & state
		if((Gdx.input.isKeyPressed(Keys.SPACE) || isTouched(0.75f, 1)) && Ivis.isGrounded()) {
			Ivis.addVelocityY();
			Ivis.state = State.Jumping;
			Ivis.unGrounded();
		}

		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A) || isTouched(0, 0.25f)) {
			Ivis.addVelocityX(-1);
			if(Ivis.isGrounded()) Ivis.state = State.Walking;
			Ivis.faceLeft();
		}

		if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D) || isTouched(0.25f, 0.5f)) {
			Ivis.addVelocityX(1);
			if(Ivis.isGrounded()) Ivis.state = State.Walking;
			Ivis.faceRight();
		}

		// apply gravity if we are falling
		Ivis.addVelocity(0, LittlerIvis.GRAVITY);

		// clamp the velocity to the maximum, x-axis only
		if(Math.abs(Ivis.getVelocity().x) > Ivis.maxVelocity) {
			Ivis.getVelocity().x = Math.signum(Ivis.getVelocity().x) * Ivis.maxVelocity;
		}

		// clamp the velocity to 0 if it's < 1, and set the state to standing
		if(Math.abs(Ivis.getVelocity().x) < 1) {
			Ivis.setVelocityX(0);
			if(Ivis.isGrounded()) Ivis.state = State.Standing;
		}

		// multiply by delta time so we know how far we go
		// in this frame
		Ivis.getVelocity().scl(deltaTime);

		// perform collision detection & response, on each axis, separately
		// if the Ivis is moving right, check the tiles to the right of it's
		// right bounding box edge, otherwise check the ones to the left
		Ivis.bb = rectPool.obtain();
		Ivis.bb.set(Ivis.getX(), Ivis.getY(), Ivis.getWidth(), Ivis.getHeight());
		int startX, startY, endX, endY;
		if(Ivis.getVelocity().x > 0) {
			startX = endX = (int)(Ivis.getX() + Ivis.getWidth() + Ivis.getVelocity().x);
		} else {
			startX = endX = (int)(Ivis.getX() + Ivis.getVelocity().x);
		}
		startY = (int)(Ivis.getY());
		endY = (int)(Ivis.getY() + Ivis.getHeight());
		getTiles(startX, startY, endX, endY, tiles,1);
		Ivis.bb.x += Ivis.getVelocity().x;
		for(Rectangle tile: tiles) {
			if(Ivis.bb.overlaps(tile)) {
				Ivis.setVelocityX(0);
				break;
			}
		}
		Ivis.bb.x = Ivis.getX();

		// if the Ivis is moving upwards, check the tiles to the top of it's
		// top bounding box edge, otherwise check the ones to the bottom
		if(Ivis.getVelocity().y > 0) {
			startY = endY = (int)(Ivis.getY() + Ivis.getHeight() + Ivis.getVelocity().y);
		} else {
			startY = endY = (int)(Ivis.getY() + Ivis.getVelocity().y);
		}
		startX = (int)(Ivis.getX());
		endX = (int)(Ivis.getX() + Ivis.getWidth());
		getTiles(startX, startY, endX, endY, tiles,1);
		Ivis.bb.y += Ivis.getVelocity().y;
		for(Rectangle tile: tiles) {
			if(Ivis.bb.overlaps(tile)) {
				// we actually reset the Ivis y-position here
				// so it is just below/above the tile we collided with
				// this removes bouncing :)
				if(Ivis.getVelocity().y > 0) {
					Ivis.setY(tile.y - Ivis.getHeight());
					// we hit a block jumping upwards, let's destroy it!
//					TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(1);
//					Cell cell = layer.getCell((int)tile.x, (int)tile.y);
//					layer.setCell((int)tile.x, (int)tile.y, cell);
				} else {
					Ivis.setY(tile.y + tile.height);
					// if we hit the ground, mark us as grounded so we can jump
					Ivis.grounded();
				}
				Ivis.setVelocityY(0);
				break;
			}
		}
		//Inicio cambio
		getTiles(startX, startY, endX, endY, tiles,2);
		for(Rectangle tile: tiles) {
			if(Ivis.bb.overlaps(tile)) {
				TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(2);
				Cell cell = layer.getCell((int)tile.x, (int)tile.y);
				MapProperties properties = cell.getTile().getProperties();
				if(properties.containsKey("Extorcion"))
					layer.setCell((int)tile.x, (int)tile.y, cell);
				else
					layer.setCell((int)tile.x, (int)tile.y, null);
			}
		}
		//fin cambio
		rectPool.free(Ivis.bb);
		Ivis.act(deltaTime);

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
					
					if(num_layer==LittlerIvis.LAYER_COIN){
						if(properties.containsKey("Pointer")){
							System.out.println("Pointer");
							game.setScreen(LittlerIvis.MAIN);
						}else if(properties.containsKey("Droga")){
							System.out.println("Droga");
							counterLevel2++;
							if(counterLevel2==3)
								game.setScreen(LittlerIvis.MAIN);
						}else if(properties.containsKey("Matar")){
							System.out.println("Matar");
							game.setScreen(LittlerIvis.MAIN);
						}else if(properties.containsKey("Extorcion")){
							System.out.println("Extorcion");
							counterLevel3++;
							if(counterLevel3==10)
								game.setScreen(LittlerIvis.MAIN);
						}else if(properties.containsKey("Die")){
							System.out.println("Die");
							game.setScreen(LittlerIvis.MAIN);
						}
					
						Iglesia(properties);
					}else{
						if(properties.containsKey("hola")){
							System.out.println("test");
							game.setScreen(LittlerIvis.MAIN);
						}
					}
					
					Rectangle rect = rectPool.obtain();
					rect.set(x, y, 1, 1);
					tiles.add(rect);
				}
			}
		}
	}

	private void Iglesia(MapProperties properties) {
		if(in){
			if(properties.containsKey("Entrar")){
				System.out.println("Entrar");
				in=false;
			}
		}else if(out){
			if(properties.containsKey("Salir")){
				System.out.println("Salir");
				out=false;
				game.setScreen(LittlerIvis.MAIN);
			}
		}
	}

	private void renderCharacter(Batch batch) {
		batch.begin();
		Ivis.draw(batch, 1);
		GlobalNPCs.render(batch);
		batch.end();
	}

}
