package model.game;

import model.*;
import model.exceptions.InvalidSizeException;

/**
 *  Subclase de Board que se usa en el juego
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
public class GameBoard extends Board {

	/**
	 * Constructor de GameBoard que llama al constructor de la clase padre
	 * @param size dimension del cual se va a crear el tablero (size x size)
	 * @throws InvalidSizeException cuando el valor size es menor a 5
	 */
	public GameBoard(int size) throws InvalidSizeException { 
		super(size); 
	}
	
	/**
	 * Metodo que devuelve el numero de cazas dependiendo del bando al que pertenezcan
	 * @param side lado que utilizaremos en la busqueda
	 * @return la cantidad de cazas
	 */
	public int numFighters(Side side) {
		
		int num = 0, j, i;
		
		for ( i = 0; i < super.getSize(); i++) {
			for (j = 0; j < super.getSize(); j++) {
				
					Fighter f = super.getFighter(new Coordinate(i,j));
					if (f!=null) {
						if (f.getSide().equals(side))
							num++;
					}
			}
		}
		return num;
	}
	
	/**
	 * Devuelve el tipo de caza posicionado en dicha coordenada
	 * @param c Coordenada del caza a buscar
	 * @return el tipo del caza dentro de c
	 */
	private String positionType(Coordinate c) {
		
		Fighter f = super.getFighter(c);
		
		if (f != null) 
			return ""+f.getSymbol();
		
		return " ";
	}
	
	/**
	 * Devuelve una cadena con la representacion del tablero
	 */
	@Override
	public String toString() {
		
		String s = "";
		
		for (int i = -2; i < (super.getSize()); i++)
		{
			for (int j = -2; j < super.getSize(); j++) {
				
				if ((i == -2 || i == -1) && (j == -2 || j == -1))
					s+=" ";
				
				else{
					if (i == -2) 
						s+=j;
				
					else if (i == -1)
						s+="-";
					
					else {
						if (j == -2)
							s +=i ;
						else if(j == -1)
							s += "|";
						else
							s = s + (positionType(new Coordinate(j,i)));
					}
				}
			}
			s+="\n";
		}
		
		return s;
	}
}
