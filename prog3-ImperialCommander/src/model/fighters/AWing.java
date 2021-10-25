package model.fighters;

import model.Fighter;
import model.Ship;

public class AWing extends model.Fighter{

	/**
	 * Constructor de AWing
	 * @param mother nave nodriza del subcaza
	 */
	public AWing(Ship mother) {
		super(mother);
	}
	
	/**
	 * Constructor de AWing
	 * @param f caza de tipo AWing
	 */
	private AWing(AWing f){
		super(f);
	}
	
	/**
	 * @return una copia del objeto
	 */
	@Override
	public model.Fighter copy() {
		return new AWing(this);
	}
	
	/**
	 * @return el simbolo del caza AWing
	 */
	@Override
	public char getSymbol() {
		return 'A';
	}
	
	public int getDamage(int n, Fighter enemy) {
		
		if (enemy.getSymbol()=='b')
			return 2*super.getDamage(n, enemy);
		
		return super.getDamage(n, enemy);
	}
}
