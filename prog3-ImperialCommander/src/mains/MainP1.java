package mains;

import java.util.ArrayList;

import model.Fighter;

public class MainP1 {

	/* Comentario metido por Paco Moreno (se puede borrar) */
	
	public static void main(String[] args) {
		/*
		Ship s1 = new Ship("Nave",Side.IMPERIAL);
		Ship s2 = new Ship("Nave",Side.REBEL);
		Coordinate c1 = new Coordinate(5,4);
		//Coordinate c2 = new Coordinate(5,4);
		
		Fighter f1 = new Fighter("XWing",s1);
		
		Fighter f2 = new Fighter("XPaladin",s2);
		
		System.out.println(f1);
		System.out.println(f2);
		
		f2.addAttack(-20);
		f2.addShield(100);
		System.out.println("f2 info " + f2.getAttack() + " " + f2.getShield() + " " + f2.getVelocity());
		System.out.println("f1 info " + f1.getAttack() + " " + f1.getShield() + " " + f1.getVelocity());
		int lucha = f1.fight(f2);
		
		if (lucha == 1)
			System.out.println("f1 ha ganado");
		else if(lucha == -1)
			System.out.println("f2 ha ganado");
		*/
		
		ArrayList<String> flota = new ArrayList<String>();
		
		String s1 = "5/XWing:12/AWing:3/YWing:2/XWing";
		if (s1.contains(":")) { 
			String s2[] = s1.split(":");
			
			for (int i = 0; i < s2.length; i++ ) {
				
				String saux1[] = s2[i].split("/");
				for(int j = 0; j < Integer.parseInt(saux1[0]); j++) {
					flota.add(saux1[1]);
				}
			}
		}
			
		else {
			String s2[] = s1.split("/");
			
			for (int i = 0; i < Integer.parseInt(s2[0]); i++) {
				flota.add(s2[1]);
			}
		}
		
	}
}