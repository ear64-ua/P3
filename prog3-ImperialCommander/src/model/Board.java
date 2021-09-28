package model;

import java.util.Objects;
import java.util.TreeSet;
import java.util.Set;

/**
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 * 
 **/

public class Board {
	
	private int size;
	
	public Board(int size) {
		
	}
	
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		
	}
	
	public int getSize() {
		return size;
	}
	
	public Boolean removeFighter(Fighter f) {
		
		Objects.requireNonNull(f);
		
		return new Fighter(f);
		
	}
	
	public Boolean inside(Coordinate c) {
		
		Objects.requireNonNull(c);
		
		return true;
		
	}
	
	 /**
	  * Recorremos dos bucles en los que obtendremos las coordenadas proximas a nuestra coordenada.
	  * Para ello hacemos uso del atributo conjunto que devuelve un grupo de coordenadas de tipo TreeSet
	  * @param c es la coordenada de la que sacaremos sus coordenadas vecinas
	  * @return conjunto  de valores que rodean a la coordenada
	  */
	
	public Set<Coordinate> getNeighborhood(Coordinate c) {
		
		Objects.requireNonNull(c);
		
		TreeSet<Coordinate> conjunto = new TreeSet<Coordinate>();
		
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if ((i != 0) && (j!=0))
					conjunto.add(new Coordinate(c.getX(),c.getY()));
			}
		}

		return conjunto;
	}
	
	public int launch(Coordinate c, Fighter f) {
		
	}
	
	public void patrol(Fighter f) {
		
	}
	
	

}
