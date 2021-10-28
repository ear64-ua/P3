package model.fighters;

import model.Fighter;
import model.Ship;

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
	
	public int getDamage(int n, Fighter enemy) {
		
		if ((enemy.getSymbol()=='X') || (enemy.getSymbol()=='Y'))
			return (1/2)*super.getDamage(n, enemy);
		
		else if (enemy.getSymbol()=='A')
			return (1/3)*super.getDamage(n, enemy);
		
		return super.getDamage(n, enemy);
	}

}
