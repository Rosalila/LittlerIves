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
		if(level==1)
		{
			policia = new Character("policia.png");
		}
		if(level==2)
		{
			comprador1 = new Character("comprador1.png");
			comprador2 = new Character("comprador2.png");
			comprador3 = new Character("comprador3.png");
		}
		if(level==3)
		{
			extorsionada = new Character("extorsionada.png");
		}
		if(level==4)
		{
			asesinado = new Character("asesinado.png");
		}
		if(level==5)
		{
			jefe = new Character("jefe.png");
		}
	}
	
	public static void render(Batch batch)
	{
		if(level==1)
		{
			float x=124f;
			float y=2f;
			policia.setPosition(x, y);
			policia.facesRight = false;
			policia.draw(batch, 1);
		}
		if(level==2)
		{
			comprador1.setPosition(68f, 10f);
			comprador1.facesRight = false;
			comprador1.draw(batch, 1);
			
			comprador2.setPosition(110f, 14f);
			comprador2.facesRight = false;
			comprador2.draw(batch, 1);
			
			comprador3.setPosition(176f, 2f);
			comprador3.facesRight = false;
			comprador3.draw(batch, 1);
		}
		if(level==3)
		{
			extorsionada.setPosition(122f, 2f);
			extorsionada.facesRight = false;
			extorsionada.draw(batch, 1);
		}
		if(level==4)
		{
			asesinado.setPosition(90f, 7f);
			asesinado.facesRight=false;
			asesinado.draw(batch, 1);
		}
		if(level==5)
		{
			jefe.setPosition(38f, 2f);
			jefe.facesRight=false;
			jefe.draw(batch, 1);
		}
	}
}
