package model.fighters;

import model.Ship;

/**
 *  Subclase de la clase Fighter
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 *  @see model.Fighter Fighter
 **/
public class TIEFighter extends model.Fighter{

	/**
	 * Constructor de TIEFighter
	 * @param mother nave nodriza del subcaza
	 */
	public TIEFighter(Ship mother) {
		super(mother);
		super.addVelocity(10);
		super.addAttack(5);
		super.addShield(-10);

	}
	
	/**
	 * Constructor de TIEFighter
	 * @param f caza de tipo TIEFighter
	 */
	private TIEFighter(TIEFighter f){
		super(f);
	}
	
	@Override
	public model.Fighter copy() {
		return new TIEFighter(this);
	}
	
	@Override
	public char getSymbol() {
		return 'f';
	}

}
