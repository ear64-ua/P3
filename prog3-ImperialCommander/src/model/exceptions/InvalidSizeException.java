package model.exceptions;

/**
 *  Lanzada si size es menor que 5.
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
@SuppressWarnings("serial")
public class InvalidSizeException extends Exception {
	
	/**
	 * Entero usado en su constructor 
	 */
	private int size;
	
	/**
	 * Constructor de la excepcion
	 * @param size su valor causa dicha excepcion
	 */
	public InvalidSizeException(int size) {
		super();
		this.size = size;
	}
	
	/**
	 * Getter en forma de mensaje para la excepcion
	 * @return un mensaje con el error generado
	 */
	public String getMessage() {
		return "ERROR: invalid size " + size;
	}
}
