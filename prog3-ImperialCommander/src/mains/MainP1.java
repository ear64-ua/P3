package mains;

import model.Ship;
import model.Side;

public class MainP1 {

	/* Comentario metido por Paco Moreno (se puede borrar) */
	
	public static void main(String[] args) {
		
		Ship s1 = new Ship("N1",Side.IMPERIAL);
		Ship s2 = new Ship("N2",Side.REBEL);
				
		s1.addFighters("4/AWing:2/BWing");
		
		s2.addFighters("2/XWing:3/YWing");
		
		s1.updateResults(1);
		s1.updateResults(1);
		s2.updateResults(1);
		s1.updateResults(-1);
		
		s2.updateResults(-1);
		s2.updateResults(4);
		
		System.out.println(s1.getFirstAvailableFighter("BWing"));
		
		System.out.println(s1);
		System.out.println(s2);
		
		
		
		
		
		/*
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
		
		
		
	}
}