package mains;

import model.Coordinate;
import model.Ship;
import model.Fighter;
import model.Side;

public class MainP1 {

	/* Comentario metido por Paco Moreno (se puede borrar) */
	
	public static void main(String[] args) {
		
		Ship s1 = new Ship("Nave",Side.IMPERIAL);
		Coordinate c1 = new Coordinate(5,4);
		Coordinate c2 = new Coordinate(5,4);
		
		Fighter f1 = new Fighter("XWing",s1);
	}
}
