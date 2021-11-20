package model.game;

import model.*;
import model.exceptions.InvalidSizeException;

/**
 *  -------------------
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
public class GameBoard extends Board {

	
	/**
	 * 
	 * @param size
	 * @throws InvalidSizeException
	 */
	public GameBoard(int size) throws InvalidSizeException { 
		super(size); 
	}
	
	/**
	 * 
	 * @param side
	 * @return
	 */
	public int numFighters(Side side) {
		
		int num = 0, j, i;
		System.out.println(super.getSize());
		System.out.println(side);
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
	 * 
	 * @param c
	 * @return
	 */
	private String positionType(Coordinate c) {
		
		Fighter f = super.getFighter(c);
		
		if (f != null) 
			return ""+f.getSymbol();
		
		return " ";
	}
	
	/**
	 * 
	 */
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
							s = s + (positionType(new Coordinate(i,j)));
					}
				}
			}
			s+="\n";
		}
		
		return s;
	}
}
