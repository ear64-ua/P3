package model.exceptions;
import model.Fighter;

@SuppressWarnings("serial")
public class FighterNotInBoardException extends Exception {
	private Fighter f;
	
	public FighterNotInBoardException(Fighter f) {
		super();
		this.f = f;
	}
	
	public String getMessage() {
		return "ERROR: fighter " + f + "not in board";
	}
}
