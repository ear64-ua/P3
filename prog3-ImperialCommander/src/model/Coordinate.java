package model;

import java.util.Objects;

/**
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 * 
 **/

public class Coordinate{	/** Empezamos creando nuestra clase Coordinate **/
	
	private int x, y;
	/** Se crea un constructor con dos argumentos enteros 
	 *	@param x es la primera coordenada del constructor
	 *  @param y es la segunda coordenada del constructor 
	 * 
	 */
	
	public Coordinate(int x,int y) {	
		this.x = x;
		this.y = y;
	}
	
	/** Se crea un constructor de copia 
	 * @param c Es el objeto del constructor
	 */
	public Coordinate(Coordinate c) {	
		x = c.x;
		y = c.y;
	}
	
	/**
    * Getter.
    * @return el valor x...
    */
	public int getX() {
		return this.x;
	}
	
	/**
    * Getter.
    * @return el valor y...
    */
	public int getY() {
		return this.y;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	/**
	 * Equals
	 * @param obj
	 * @return TRUE: si es el mismo objeto o si las coordenadas 'x' e 'y' son iguales                      
	 *         FALSE: si el parametro es nulo o si el nombre de la clase es diferente
	 * 			o si las coordenadas 'x' e 'y' son diferentes
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		return x == other.x && y == other.y;
	}
	
	/**
   	* @return un string del formato "[ x , y ]"
   	*/
	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}
	
	/**
   	* Sumamos dos coordenadas
   	* @param c Es la coordenada con la que se va a sumar el constructor de suma
   	* @return Se devuelve la coordenada resultante de la suma
   	*/
	public Coordinate add(Coordinate c) {
		return new Coordinate(x + c.x, y + c.y);
	}

	/**
   	* Comparamos dos coordinadas con lógica boleana
   	* @param x Es el primer valor de una coordenada para poder sumarla con su equivalente de otro objeto
   	* @param y Es el segundo valor de una coordenada para poder sumarla con su equivalente de otro objeto
   	* @return Se devuelve la coordenada resultante de la suma
   	*/
	public Coordinate add(int x, int y) {
		return new Coordinate(this.x+x , this.y+y);
	}
	
}






