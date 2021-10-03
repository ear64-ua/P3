package model;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 *  Clave para almacenar las posiciones de nuestras piezas
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 * 
 **/

public class Coordinate implements Comparable<Coordinate> {	/** Empezamos creando nuestra clase Coordinate **/
	
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
	 * @see Objects#hash hash(x,y)
	 */
	@Override
	
	public int hashCode() {
		return Objects.hash(x, y);
	}

	/**
	 * Equals con atributos x e y
	 * @param obj es la referencia objeto el cual vamos a comparar
	 * @return TRUE: si este objeto es el mismo que el argumento obj                 
	 *         FALSE: en caso contrario
	 * @see Object#equals(Object) 
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
   	* Sumamos dos coordenadas, el del objeto actual con el parametro pasado
   	* @param c Es la coordenada con la que se va a sumar el constructor de suma
   	* @return Se devuelve la coordenada resultante de la suma
   	*/
	public Coordinate add(Coordinate c) {
		return new Coordinate(x + c.x, y + c.y);
	}

	/**
   	* Sumamos dos coordenadas el del objeto actual con los dos enteros pasados por parametro
   	* @param x Es el primer valor de una coordenada para poder sumarla con su equivalente de otro objeto
   	* @param y Es el segundo valor de una coordenada para poder sumarla con su equivalente de otro objeto
   	* @return Se devuelve la coordenada resultante de la suma
   	*/
	public Coordinate add(int x, int y) {
		return new Coordinate(this.x+x , this.y+y);
	}
	
	/**
	 * Comparamos dos coordenadas
	 * @param	otra objeto de tipo Coordinate para comparar
	 * @return  devuelve un valor negativo si la x de la coordenada es menor que la x 
	 * 		    de otra, un valor positivo si x es mayor, y si ambas x son iguales debe proceder 
	 * 			de la misma forma con las y; si ambas componentes, x e y, son iguales, debe devolver 0
	 */
	 @Override
	 public int compareTo(Coordinate otra) {
		 
		 if (this.x < otra.getX())
			 return -1;
		 
		 else if (this.x > otra.getX())
			 return 1;
		 
		 else if (this.x == otra.getX() && this.y < otra.getY())
			 return -1;
		 
		 else if (this.x == otra.getX() && this.y > otra.getY())
			 return 1;
		 
		 else 
			 return 0;
	 }
	 
	 /**
	  * Recorremos dos bucles en los que obtendremos las coordenadas proximas a nuestra coordenada.
	  * Para ello hacemos uso del atributo conjunto que devuelve un grupo de coordenadas de tipo TreeSet
	  * @return conjunto  de valores que rodean a la coordenada
	  * @see TreeSet
	  * @see Set
	  */
	 public Set<Coordinate> getNeighborhood(){
		 
		 TreeSet<Coordinate> conjunto = new TreeSet<Coordinate>();
		 
		 for (int i = -1; i < 2; i++)
		 {
			 for(int j = -1; j < 2; j++) {
				 if ((i != 0) || (j!=0))
					 conjunto.add(new Coordinate (this.getX()+i,this.getY()+j));
			 }
		 }
		
		 return conjunto;
		 
	 }
	 
	 
}






