package model.fighters;

import model.Fighter;
import model.Ship;

/**
 *  Subclase de la clase Fighter
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 *  @see model.Fighter Fighter
 **/
public class TIEBomber extends model.Fighter{

	/**
	 * Constructor de TIEBomber
	 * @param mother nave nodriza del subcaza
	 */
	public TIEBomber(Ship mother) {
		super(mother);
		super.addVelocity(-30);
		super.addAttack(-50);
		super.addShield(35);
	}
	
	/**
	 * Constructor de TIEBomber
	 * @param f caza de tipo TIEBomber
	 */
	private TIEBomber(TIEBomber f){
		super(f);
	}
	
	@Override
	public Fighter copy() {
		return new TIEBomber(this);
	}
	
	@Override
	public char getSymbol() {
		return 'b';
	}
	
	@Override
	public int getDamage(int n, Fighter enemy) {
		
		if ((enemy.getSymbol()=='X') || (enemy.getSymbol()=='Y'))
			return super.getDamage(n, enemy)/2;
		
		else if (enemy.getSymbol()=='A')
			return super.getDamage(n, enemy)/3;
		
		return super.getDamage(n, enemy);
	}

}
