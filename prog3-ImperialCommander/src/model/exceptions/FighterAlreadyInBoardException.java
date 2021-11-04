package model.exceptions;
import model.Fighter;

@SuppressWarnings("serial")
public class FighterAlreadyInBoardException extends Exception {
	private Fighter f;
	
	public FighterAlreadyInBoardException(Fighter f) {
		super();
		this.f = f;
	}
	
	public String getMessage() {
		return "ERROR: fighter " + f + " is already on board ";
	}
}
