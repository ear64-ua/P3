package model.fighters;

import model.Ship;

public class XWing extends model.Fighter{

	/**
	 * Constructor de XWing
	 * @param mother nave nodriza del subcaza
	 */
	public XWing(Ship mother) {
		super(mother);
		super.addVelocity(10);
		super.addAttack(20);

	}
	
	/**
	 * Constructor de XWing
	 * @param f caza de tipo XWing
	 */
	private XWing(XWing f){
		super(f);
	}
	
	@Override
	public model.Fighter copy() {
		return new XWing(this);
	}
	
	@Override
	public char getSymbol() {
		return 'X';
	}

}
