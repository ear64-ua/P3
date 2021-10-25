package model.fighters;

import model.Ship;

public class TIEFighter extends model.Fighter{

	/**
	 * Constructor de TIEFighter
	 * @param mother nave nodriza del subcaza
	 */
	public TIEFighter(Ship mother) {
		super(mother);
	}
	
	/**
	 * Constructor de TIEFighter
	 * @param f caza de tipo TIEFighter
	 */
	private TIEFighter(TIEFighter f){
		super(f);
	}
	
	/**
	 * @return una copia del objeto
	 */
	@Override
	public model.Fighter copy() {
		return new TIEFighter(this);
	}
	
	/**
	 * @return el simbolo del caza TIEFighter
	 */
	@Override
	public char getSymbol() {
		return 'f';
	}

}
