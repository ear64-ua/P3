package model.fighters;

import model.Ship;

public class XWing extends model.Fighter{

	/**
	 * Constructor de XWing
	 * @param mother nave nodriza del subcaza
	 */
	public XWing(Ship mother) {
		super(mother);
	}
	
	/**
	 * Constructor de XWing
	 * @param f caza de tipo XWing
	 */
	private XWing(XWing f){
		super(f);
	}
	
	/**
	 * @return una copia del objeto
	 */
	@Override
	public model.Fighter copy() {
		return new XWing(this);
	}
	
	/**
	 * @return el simbolo del caza XWing
	 */
	@Override
	public char getSymbol() {
		return 'X';
	}

}
