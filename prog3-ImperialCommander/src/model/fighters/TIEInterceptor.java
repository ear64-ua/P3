package model.fighters;

import model.Fighter;
import model.Ship;

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
	
	public int getDamage(int n, Fighter enemy) {
		
		if (enemy.getSymbol()=='Y')
			return 2*super.getDamage(n, enemy);
		
		else if (enemy.getSymbol()=='A')
			return (1/2)*super.getDamage(n, enemy);
		
		return super.getDamage(n, enemy);
	}

}
