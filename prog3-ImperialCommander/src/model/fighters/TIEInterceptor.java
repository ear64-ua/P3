package model.fighters;

import model.Fighter;
import model.Ship;

/**
 *  Subclase de la clase Fighter
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 *  @see model.Fighter Fighter
 **/
public class TIEInterceptor extends model.Fighter{

	/**
	 * Constructor de TIEInterceptor
	 * @param mother nave nodriza del subcaza
	 */
	public TIEInterceptor(Ship mother) {
		super(mother);
		super.addVelocity(45);
		super.addAttack(5);
		super.addShield(-20);
	}
	
	/**
	 * Constructor de TIEInterceptor
	 * @param f caza de tipo TIEInterceptor
	 */
	private TIEInterceptor(TIEInterceptor f){
		super(f);
	}
	
	@Override
	public model.Fighter copy() {
		return new TIEInterceptor(this);
	}
	
	@Override
	public char getSymbol() {
		return 'i';
	}
	
	@Override
	public int getDamage(int n, Fighter enemy) {
		
		if (enemy.getSymbol()=='Y')
			return 2*super.getDamage(n, enemy);
		
		else if (enemy.getSymbol()=='A')
			return super.getDamage(n, enemy)/2;
		
		return super.getDamage(n, enemy);
	}

}
