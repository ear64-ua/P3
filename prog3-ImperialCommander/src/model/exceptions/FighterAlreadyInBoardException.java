package model.exceptions;
import model.Fighter;

/**
 *  Lanzada si el caza que se quiere situar en el tablero ya tiene posicion asignada.
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
@SuppressWarnings("serial")
public class FighterAlreadyInBoardException extends Exception {
	/**
	 * Fighter usado en su constructor
	 */
	private Fighter f;
	
	/**
	 * Constructor de la excepcion
	 * @param f Fighter que causa dicha excepcion
	 */
	public FighterAlreadyInBoardException(Fighter f) {
		super();
		this.f = f;
	}
	
	/**
	 * Getter en forma de mensaje para la excepcion
	 * @return un mensaje con el error generado
	 */
	public String getMessage() {
		return "ERROR: fighter " + f + " is already on board ";
	}
}
