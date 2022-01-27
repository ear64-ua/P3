package model.game.score;
import model.*;

/**
 *  Subclase de score que se encarga de almacenar las puntuaciones de los cazas destruidos
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
public class DestroyedFightersScore  extends Score<Fighter>{

	/**
	 * Contructor de la clase DestroyedFightersScore
	 * @param side lado al que va a pertenecer
	 */
	public DestroyedFightersScore(Side side) {
		super(side);
	}

	@Override
	public void score(Fighter sc) {
		if (sc!=null)
			super.score+=sc.getValue();
	}

}
