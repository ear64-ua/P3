package model.game.exceptions;

/**
 *  Lanzada cuando id no es correcto.
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
public class WrongFighterIdException {
	/**
	 * Coordinate usado en su constructor
	 */
	private int id;
	
	/**
	 * Constructor de la excepcion
	 * @param int id que causa dicha excepcion
	 */
	public WrongFighterIdException(int id) {
		super();
		this.id = id;
	}
	
	/**
	 * Getter en forma de mensaje para la excepcion
	 * @return un mensaje con el error generado
	 */
	public String getMessage() {
		return "ERROR: invalid id " + id;
	}
}
