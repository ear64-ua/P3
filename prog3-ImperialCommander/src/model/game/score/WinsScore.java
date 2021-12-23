package model.game.score;

import model.Side;

/**
 *  Subclase de score que se encarga de almacenar las puntuaciones las batallas ganadas
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
public class WinsScore extends Score<Integer>{

	/**
	 * Constructor de la clase WinsScore
	 * @param side
	 */
	public WinsScore(Side side) {
		super(side);
	}

	@Override
	public void score(Integer sc) {
		
		
		if (sc!=null && sc == 1)
			super.score+=1;
		
	}

}
