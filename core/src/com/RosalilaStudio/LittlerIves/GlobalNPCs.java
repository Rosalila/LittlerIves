package com.RosalilaStudio.LittlerIves;

import com.RosalilaStudio.LittlerIves.Characters.Character;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GlobalNPCs {

	public static Animation policia;
	public static Animation comprador1;
	public static Animation comprador2;
	public static Animation comprador3;
	public static Animation extorsionada;
	public static Animation asesinado;
	public static Animation jefe;
<<<<<<< HEAD
	public static int level;
	
	public static void init()
=======
	
	public static void init(int level)
>>>>>>> aa45a5b3ab4fc5231618cb211aff2351e5706c5e
	{
		if(level==1)
		{
			Texture texture = new Texture("policia.png"); 
			TextureRegion[] regions = TextureRegion.split(texture, 18, 26)[0];
			policia = new Animation(0, regions[0]);
		}
		if(level==2)
		{
			Texture texture1 = new Texture("comprador1.png"); 
			TextureRegion[] regions = TextureRegion.split(texture1, 18, 26)[0];
			comprador1 = new Animation(0, regions[0]);
			
			Texture texture2 = new Texture("comprador2.png"); 
			TextureRegion[] regions2 = TextureRegion.split(texture2, 18, 26)[0];
			comprador2 = new Animation(0, regions2[0]);
			
			Texture texture3 = new Texture("comprador3.png"); 
			TextureRegion[] regions3 = TextureRegion.split(texture3, 18, 26)[0];
			comprador3 = new Animation(0, regions3[0]);
		}
		if(level==3)
		{
			Texture texture = new Texture("extorsionada.png"); 
			TextureRegion[] regions = TextureRegion.split(texture, 18, 26)[0];
			extorsionada = new Animation(0, regions[0]);
		}
		if(level==4)
		{
			Texture texture = new Texture("asesinado.png"); 
			TextureRegion[] regions = TextureRegion.split(texture, 18, 26)[0];
			asesinado = new Animation(0, regions[0]);
		}
		if(level==5)
		{
			Texture texture = new Texture("jefe.png"); 
			TextureRegion[] regions = TextureRegion.split(texture, 18, 26)[0];
			jefe = new Animation(0, regions[0]);
		}
	}
	
<<<<<<< HEAD
	public static void render(Batch batch)
=======
	public static void render(Batch batch,int level)
>>>>>>> aa45a5b3ab4fc5231618cb211aff2351e5706c5e
	{
		if(level==1)
		{
			float x=124f;
			float y=2f;
			batch.draw(policia.getKeyFrame(0),x + Character.WIDTH, y, - Character.WIDTH, Character.HEIGHT);
		}
		if(level==2)
		{
			batch.draw(comprador1.getKeyFrame(0),(float)68f + (float)Character.WIDTH, (float)10f, -(float)Character.WIDTH, (float)Character.HEIGHT);
			batch.draw(comprador2.getKeyFrame(0),(float)110f + (float)Character.WIDTH, (float)14f, -(float)Character.WIDTH, (float)Character.HEIGHT);
			batch.draw(comprador3.getKeyFrame(0),(float)176f + (float)Character.WIDTH, (float)2f, -(float)Character.WIDTH, (float)Character.HEIGHT);
		}
		if(level==3)
		{
			batch.draw(extorsionada.getKeyFrame(0),(float)122f + (float)Character.WIDTH, (float)2f, -(float)Character.WIDTH, (float)Character.HEIGHT);
		}
		if(level==4)
		{
			batch.draw(asesinado.getKeyFrame(0),(float)90f + (float)Character.WIDTH, (float)7f, -(float)Character.WIDTH, (float)Character.HEIGHT);
		}
		if(level==5)
		{
			batch.draw(jefe.getKeyFrame(0),(float)38f + (float)Character.WIDTH, (float)2f, -(float)Character.WIDTH, (float)Character.HEIGHT);
		}
	}
}
