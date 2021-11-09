package model.exceptions;

/**
 *  Lanzada cuando no sea posible encontrar en la flota un caza no destruido del tipo pedido.
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
@SuppressWarnings("serial")
public class NoFighterAvailableException extends Exception{
	
	/**
	 * String usado en su constructor
	 */
	private String type;
	
	/**
	 * Constructor de la excepcion
	 * @param type String causante de dicha excepcion
	 */
	public NoFighterAvailableException(String type) {
		super();
		this.type = type;
	}
	
	/**
	 * Getter en forma de mensaje para la excepcion
	 * @return un mensaje con el error generado
	 */
	public String getMessage() {
		return "ERROR: invalid type: " + type;
	}
}
