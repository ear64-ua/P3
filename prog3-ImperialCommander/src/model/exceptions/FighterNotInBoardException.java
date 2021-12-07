package model.exceptions;
import model.Fighter;

/**
 *  Lanzada cuando el caza que queremos eliminar del tablero no esta en el tablero o no es igual que el caza situado en su posicion.
 *  Tambien debe lanzarse cuando el caza con el que queremos patrullar no tiene posicion asignada en el tablero.
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
@SuppressWarnings("serial")
public class FighterNotInBoardException extends Exception {
	
	/**
	 * Fighter usado en su constructor
	 */
	private Fighter f;
	
	/**
	 * Constructor de la excepcion
	 * @param f Fighter que causa dicha excepcion
	 */
	public FighterNotInBoardException(Fighter f) {
		super();
		this.f = f;
	}
	
	/**
	 * Getter en forma de mensaje para la excepcion
	 * @return un mensaje con el error generado
	 */
	public String getMessage() {
		return "ERROR: fighter " + f + " not in board";
	}
}
