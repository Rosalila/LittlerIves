package com.RosalilaStudio.LittlerIves;

import com.RosalilaStudio.LittlerIves.Characters.Character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

public class GlobalNPCs {
	
	public static Character policia, policia2;
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
			policia = new Character(124f, 2f, "policia.png");
		}
		if(level==2)
		{
			comprador1 = new Character("comprador1.png");
			comprador2 = new Character("comprador2.png");
			comprador3 = new Character("comprador3.png");
			policia2 = new Character(100f, 2f, "policia.png");
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
			
			policia2.setPosition(100f, 2f, true);
			policia2.draw(batch, 1);
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
	
	public static void detectColicions(Character Ivis, float deltaTime) {
		switch(level){
		case 1:
			if(Ivis.bb.overlaps(GlobalNPCs.policia.bb)){
				System.out.println("Arrived");
			}
			GlobalNPCs.policia.act(deltaTime);
			break;
		case 2:
			if(Ivis.bb.overlaps(GlobalNPCs.policia.bb)|| Ivis.bb.overlaps(GlobalNPCs.policia2.bb)){
				System.out.println("Arrived");
			}
			GlobalNPCs.policia.act(deltaTime);
			GlobalNPCs.policia2.act(deltaTime);
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		}
	}
}
