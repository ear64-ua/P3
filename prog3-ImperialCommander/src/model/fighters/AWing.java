package model.fighters;

import model.Fighter;
import model.Ship;

/**
 *  Subclase de la clase Fighter
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 *  @see model.Fighter Fighter
 **/
public class AWing extends Fighter{

	/**
	 * Constructor de AWing
	 * @param mother nave nodriza del subcaza
	 */
	public AWing(Ship mother) {
		super(mother);
		super.addVelocity(40);
		super.addAttack(5);
		super.addShield(-50);
	}
	
	/**
	 * Constructor de AWing
	 * @param f caza de tipo AWing
	 */
	private AWing(AWing f){
		super(f);
	}
	
	
	@Override
	public Fighter copy() {
		return new AWing(this);
	}
	
	@Override
	public char getSymbol() {
		return 'A';
	}
	
	@Override
	public int getDamage(int n, Fighter enemy) {
		
		if (enemy.getSymbol()=='b')
			return 2*super.getDamage(n, enemy);
		
		return super.getDamage(n, enemy);
	}
}
