package model.fighters;

import model.Fighter;
import model.Ship;

public class YWing extends model.Fighter{

	/**
	 * Constructor de YWing
	 * @param mother nave nodriza del subcaza
	 */
	public YWing(Ship mother) {
		super(mother);
	}
	
	/**
	 * Constructor de YWing
	 * @param f caza de tipo YWing
	 */
	private YWing(YWing f){
		super(f);
	}
	
	/**
	 * @return una copia del objeto
	 */
	@Override
	public model.Fighter copy() {
		return new YWing(this);
	}
	
	/**
	 * @return el simbolo del caza YWing
	 */
	@Override
	public char getSymbol() {
		return 'Y';
	}

	public int getDamage(int n, Fighter enemy) {
		
		if ((enemy.getSymbol()=='f') || (enemy.getSymbol()=='i'))
			return (1/3)*super.getDamage(n, enemy);
		
		else if (enemy.getSymbol()=='b')
			return (1/2)*super.getDamage(n, enemy);
		
		return super.getDamage(n, enemy);
	}
}
