package model.exceptions;
import model.Fighter;

/**
 *  Lanzada cuando alguno de los cazas implicados en la lucha es un caza ya destruido antes de empezar la lucha.
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
@SuppressWarnings("serial")
public class FighterIsDestroyedException extends Exception{
	/**
	 * Fighter usado en su constructor
	 */
	private Fighter f;
	
	/**
	 * Constructor de la excepcion
	 * @param f Fighter que causa dicha excepcion
	 */
	public FighterIsDestroyedException(Fighter f) {
		super();
		this.f = f;
	}
	
	/**
	 * Getter en forma de mensaje para la excepcion
	 * @return un mensaje con el error generado
	 */
	public String getMessage() {
		return "ERROR: fighter " + f + " is destroyed";
	}
}
