package com.RosalilaStudio.LittlerIves.Screens;

import com.RosalilaStudio.LittlerIves.GlobalNPCs;
import com.RosalilaStudio.LittlerIves.LittlerIvis;
import com.RosalilaStudio.LittlerIves.Paths;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class InfoScreen extends AbstractScreen {
	private Stage stage;
	private char cont;
	private boolean active;
	private int c;

	public InfoScreen(LittlerIvis game) {
		super(game);
		
	}

	@Override
	public void show() {
		stage = new Stage(game.getView(), game.getSb());
		stage.getViewport().update(game.getWidth(), game.getHeight());
		Gdx.input.setInputProcessor(stage);
		
		cont='a';
		final Image info[] = new Image[2];
		if(GlobalNPCs.level!=5){
			info[0] = genSliptImage(game.MANAGER.get(Paths.I.getPath("intro"+GlobalNPCs.level+".png"), Texture.class), game.getWidth(), game.getHeight());
			info[0].addListener(new InputListener(){
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					game.setScreen(game.PLAY);
					return true;
				}
			});
			stage.addActor(info[0]);
		}else{
			info[1] = genSliptImage(game.MANAGER.get(Paths.I.getPath("intro"+GlobalNPCs.level+"a.png"), Texture.class), game.getWidth(), game.getHeight());
			info[1].addListener(new InputListener(){
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					info[1].setVisible(false);
					return true;
				}
			});
			
			info[0] = genSliptImage(game.MANAGER.get(Paths.I.getPath("intro"+GlobalNPCs.level+"b.png"), Texture.class), game.getWidth(), game.getHeight());
			info[0].addListener(new InputListener(){
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					game.setScreen(game.PLAY);
					return true;
				}
			});
			stage.addActor(info[0]);
			stage.addActor(info[1]);
		}
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
		stage.act();
	}
	
	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(game.getWidth(), game.getHeight());
	}
	
	private Image genSliptImage(Texture txt, int width, int height){
		TextureRegion region = new TextureRegion(txt, width, height);
		return new Image(region);
	}

}
