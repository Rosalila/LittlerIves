package com.RosalilaStudio.LittlerIves.Screens;

import com.RosalilaStudio.LittlerIves.LittlerIvis;
import com.RosalilaStudio.LittlerIves.Path;
import com.RosalilaStudio.LittlerIves.Listeners.InputDYAListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MenuScreen extends AbstractScreen {

	public MenuScreen(LittlerIvis game) {
		super(game);
	}

	private Texture texture;
	private Stage stg;
	private Image btns[] = new Image[5];
	private Texture txt;
	
	@Override
	public void show() {		
		
		Path path = Path.I;
		
		txt = new Texture(path.getPath("background.png"));
		txt.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion txtr = new TextureRegion(txt, 512, 275);
		stg = new Stage(game.getView(), game.getSb());
		stg.getViewport().update(game.getWidth(), game.getHeight());
		Gdx.input.setInputProcessor(stg);
		
		Image img = new Image(txtr);
		stg.addActor(img);
		img.setFillParent(true);
		
		for (int i = 0; i < btns.length; i++) {
			btns[i] = getImage(path.getPath((i+1)+".png"));
			
			if(i!=4)
				btns[i].setPosition(5 + btns[i].getWidth()*i, stg.getHeight()/2 - btns[i].getHeight()/2);
			else
				btns[i].setPosition(5 + btns[i].getWidth()*1.6f, -20);
			
			stg.addActor(btns[i]);
			btns[i].addListener(new InputDYAListener(btns[i], (i+1), game));
		}
		
	}

	private Image getImage(String path) {
		txt = new Texture(path);
		txt.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return new Image(txt);
	}

	@Override
	public void dispose() {
		texture.dispose();
		txt.dispose();
		stg.dispose();
	}

	@Override
	public void resize(int width, int height) {
		stg.getViewport().update(game.getWidth(), game.getHeight());
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
		stg.draw();
		stg.act();
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}
}
