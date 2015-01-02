package com.RosalilaStudio.LittlerIves;

import com.RosalilaStudio.LittlerIves.Characters.Character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;

public class GlobalNPCs {
	
	public static Character policia;
	public static Character comprador1;
	public static Character comprador2;
	public static Character comprador3;
	public static Character extorsionada;
	public static Character asesinado;
	public static Character jefe;
	public static int level;
	
	public static void init()
	{
		if(level==1 || level==2)
		{
			policia = new Character("policia.png");
		}
		if(level==2)
		{
			comprador1 = new Character("comprador1.png");
			comprador2 = new Character("comprador2.png");
			comprador3 = new Character("comprador3.png");
		}
		else if(level==3)
		{
			extorsionada = new Character("extorsionada.png");
		}
		else if(level==4)
		{
			asesinado = new Character("asesinado.png");
		}
		else if(level==5)
		{
			jefe = new Character("jefe.png");
		}
	}
	
	public static void render(Batch batch)
	{
		if(level==1)
		{
			policia.setPosition(124f, 2f);
			policia.draw(batch, 1);
		}
		else if(level==2)
		{
			comprador1.setPosition(68f, 10f);
			comprador1.draw(batch, 1);
			
			comprador2.setPosition(110f, 14f);
			comprador2.draw(batch, 1);
			
			comprador3.setPosition(176f, 2f);
			comprador3.draw(batch, 1);
			
			policia.setPosition(95f, 2f, false);
			policia.draw(batch, 1);
			
			policia.setPosition(100f, 2f, true);
			policia.draw(batch, 1);
		}
		else if(level==3)
		{
			extorsionada.setPosition(122f, 2f);
			extorsionada.draw(batch, 1);
		}
		else if(level==4)
		{
			asesinado.setPosition(90f, 7f);
			asesinado.draw(batch, 1);
		}
		else if(level==5)
		{
			jefe.setPosition(38f, 2f);
			jefe.draw(batch, 1);
		}
	}
}
