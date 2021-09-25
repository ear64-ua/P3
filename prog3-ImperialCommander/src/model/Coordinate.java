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
    * Getter de x
    * @return el valor x...
    */
	public int getX() {
		return this.x;
	}
	
	/**
    * Getter de y
    * @return el valor y...
    */
	public int getY() {
		return this.y;
	}
	
	/**
	 * hashCode() nos permite localizar el objeto en un mapa
	 * @return un valor hash code del objeto 
	 */
	@Override
	
	public int hashCode() {
		return Objects.hash(x, y);
	}

	/**
	 * Equals
	 * @param obj es la referencia objeto el cual vamos a comparar
	 * @return TRUE: si este objeto es el mismo que el argumento obj                 
	 *         FALSE: en caso contrario
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
	 * Convierte la coordenada en String
   	* @return un string del formato "[ x , y ]"
   	*/
	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}
	
	/**
   	* Sumamos dos coordenadas, el del objeto actual con el parámetro pasado
   	* @param c Es la coordenada con la que se va a sumar el constructor de suma
   	* @return Se devuelve la coordenada resultante de la suma
   	*/
	public Coordinate add(Coordinate c) {
		return new Coordinate(x + c.x, y + c.y);
	}

	/**
   	* Sumamos dos coordenadas el del objeto actual con los dos enteros pasados por parámetro
   	* @param x Es el primer valor de una coordenada para poder sumarla con su equivalente de otro objeto
   	* @param y Es el segundo valor de una coordenada para poder sumarla con su equivalente de otro objeto
   	* @return Se devuelve la coordenada resultante de la suma
   	*/
	public Coordinate add(int x, int y) {
		return new Coordinate(this.x+x , this.y+y);
	}
	
}






