package model.exceptions;

import model.Coordinate;

/**
 *  Lanzada cuando una coordenada en la que queremos colocar un caza o de la que queremos obtener la vecindad esta fuera del tablero.
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
@SuppressWarnings("serial")
public class OutOfBoundsException extends Exception{
	
	/**
	 * Coordinate usado en su constructor
	 */
	private Coordinate c;
	
	/**
	 * Constructor de la excepcion
	 * @param c coordinada que causa dicha excepcion
	 */
	public OutOfBoundsException(Coordinate c) {
		super();
		this.c = c;
	}
	
	/**
	 * Getter en forma de mensaje para la excepcion
	 * @return un mensaje con el error generado
	 */
	public String getMessage() {
		return "ERROR: invalid coordinate " + c;
	}
}
