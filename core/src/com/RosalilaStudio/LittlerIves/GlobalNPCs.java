package com.RosalilaStudio.LittlerIves;

import com.RosalilaStudio.LittlerIves.Characters.Character;
import com.badlogic.gdx.graphics.g2d.Batch;

public class GlobalNPCs {
	
	private static Character policia;
	public static Character comprador1;
	public static Character comprador2;
	public static Character comprador3;
	public static Character extorsionada;
	public static Character asesinado;
	public static Character jefe;
	public static int level;
	
	public static void init()
	{
		String path = Paths.C.getPath("");
		if(level==1)
		{
			policia = new Character("policia.png");
		}
		if(level==2)
		{
			comprador1 = new Character("comprador1.png"); //(0, regions[0]);
			comprador2 = new Character("comprador2.png"); //(0, regions2[0]);
			comprador3 = new Character("comprador3.png"); //(0, regions3[0]);
		}
		if(level==3)
		{
			extorsionada = new Character("extorsionada.png"); //(0, regions[0]);
		}
		if(level==4)
		{
			asesinado = new Character("asesinado.png"); //(0, regions[0]);
		}
		if(level==5)
		{
			jefe = new Character("jefe.png"); //(0, regions[0]);
		}
	}
	
	public static void render(Batch batch)
	{
		if(level==1)
		{
			float x=124f;
			float y=2f;
			batch.draw(policia.getStand().getKeyFrame(0),x + policia.WIDTH, y, - policia.WIDTH, policia.HEIGHT);
		}
		if(level==2)
		{
			batch.draw(comprador1.getStand().getKeyFrame(0),(float)68f + (float)comprador1.WIDTH, (float)10f, -(float)comprador1.WIDTH, (float)comprador1.HEIGHT);
			batch.draw(comprador2.getStand().getKeyFrame(0),(float)110f + (float)comprador2.WIDTH, (float)14f, -(float)comprador2.WIDTH, (float)comprador2.HEIGHT);
			batch.draw(comprador3.getStand().getKeyFrame(0),(float)176f + (float)comprador3.WIDTH, (float)2f, -(float)comprador3.WIDTH, (float)comprador3.HEIGHT);
		}
		if(level==3)
		{
			batch.draw(extorsionada.getStand().getKeyFrame(0),(float)122f + (float)extorsionada.WIDTH, (float)2f, -(float)extorsionada.WIDTH, (float)extorsionada.HEIGHT);
		}
		if(level==4)
		{
			batch.draw(asesinado.getStand().getKeyFrame(0),(float)90f + (float)asesinado.WIDTH, (float)7f, -(float)asesinado.WIDTH, (float)asesinado.HEIGHT);
		}
		if(level==5)
		{
			batch.draw(jefe.getStand().getKeyFrame(0),(float)38f + (float)jefe.WIDTH, (float)2f, -(float)jefe.WIDTH, (float)jefe.HEIGHT);
		}
	}
}
