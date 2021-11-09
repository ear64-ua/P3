package model.fighters;

import model.Fighter;
import model.Ship;

/**
 *  Subclase de la clase Fighter
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 *  @see model.Fighter Fighter
 **/
public class YWing extends model.Fighter{

	/**
	 * Constructor de YWing
	 * @param mother nave nodriza del subcaza
	 */
	public YWing(Ship mother) {
		super(mother);
		super.addVelocity(-20);
		super.addAttack(-10);
		super.addShield(30);
	}
	
	/**
	 * Constructor de YWing
	 * @param f caza de tipo YWing
	 */
	private YWing(YWing f){
		super(f);
	}
	
	@Override
	public model.Fighter copy() {
		return new YWing(this);
	}
	
	@Override
	public char getSymbol() {
		return 'Y';
	}

	@Override
	public int getDamage(int n, Fighter enemy) {
		
		if ((enemy.getSymbol()=='f') || (enemy.getSymbol()=='i'))
			return super.getDamage(n, enemy)/3;
		
		else if (enemy.getSymbol()=='b')
			return super.getDamage(n, enemy)/2;
		
		return super.getDamage(n, enemy);
	}
}
