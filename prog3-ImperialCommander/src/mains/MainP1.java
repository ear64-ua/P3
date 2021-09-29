package mains;

import model.Coordinate;
import model.Ship;
import model.Fighter;
import model.Side;

public class MainP1 {

	/* Comentario metido por Paco Moreno (se puede borrar) */
	
	public static void main(String[] args) {
		
		Ship s1 = new Ship("Nave",Side.IMPERIAL);
		Ship s2 = new Ship("Nave",Side.REBEL);
		Coordinate c1 = new Coordinate(5,4);
		//Coordinate c2 = new Coordinate(5,4);
		
		Fighter f1 = new Fighter("XWing",s1);
		
		Fighter f2 = new Fighter("XPaladin",s2);
		
		System.out.println(f1);
		System.out.println(f2);
		
		int lucha = f1.fight(f2);
		
		if (lucha == 1)
			System.out.println("f1 ha ganado");
		else if(lucha == -1)
			System.out.println("f2 ha ganado");
			
	}
}