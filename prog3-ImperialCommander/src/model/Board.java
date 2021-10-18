package model;

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 *  Creada para contener en un mapa, 
 *  nuestros cazas.
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/

public class Board {
	
	/**
	 * Dimension del tablero
	 */
	private int size;
	
	/**
	 * Mapa del tablero
	 */
	private Map<Coordinate, Fighter> board;
	
	/**
	 * Es el constructor que inicializa las dimensiones del tablero 
	 * y crea la instancia de un HashMap
	 * @param size dimension del cual se va a crear el tablero (size x size)
	 * @see HashMap
	 */
	public Board(int size) {
		this.size=size;
		board = new HashMap<Coordinate,Fighter>();
	}
	
	/**
	 *  Getter del objeto Fighter 
	 * en el cual hace una copia defensiva de dicho objeto 
	 * contenido en el tablero
	 * @param c Coordenada en la que buscaremos si tiene valor 
	 * @return {@code .null} si no se encuentra en el tablero,
	 * 	y una copia del caza que lo ocupa en caso contrario
	 * @see Map#containsKey(Object) containsKey(Object)
	 */
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		if (board.containsKey(c))
			return new Fighter (board.get(c));
		else
			return null;
	}
	
	/**
	 * Getter de size
	 * @return la dimension del tablero
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * El metodo removeFighter compara la posicion del caza
	 * dentro del tablero con el caza y lo elimina 
	 * @param f caza que va a ser removido
	 * @return true si se ha podido remover, o false en caso 
	 * 	contrario
	 */
	public Boolean removeFighter(Fighter f) {
		
		Objects.requireNonNull(f);
		
		if(f.getPosition()!=null) {
			if (this.inside(f.getPosition())){
				if (board.containsKey(f.getPosition())){
					if (board.get(f.getPosition()).equals(f)) {
						board.remove(f.getPosition());
						f.setPosition(null);
						return true;
					}
				}
			}	
		}
		 
		return false;
	}
	
	/**
	 * El metodo inside se encarga de controlar que la coordenada se
	 * encuentra dentro del tablero
	 * @param c Coordenada a analizar dentro del tablero
	 * @return true si se encuentra dentro, y false si no
	 */
	public Boolean inside(Coordinate c) {
		
		Objects.requireNonNull(c);
		if(!c.equals(null)) {
			for (int i = 0; i < this.size;i++) {
				for (int j = 0; j < this.size; j++) {
					if (c.equals(new Coordinate(i,j)))
						return true;
				}
			}
		}
		
		return false;
	}
	
	 /**
	  * El metodo getNeighborhood devuelve las coordenadas que le rodean y que estan dentro del tablero
	  * @param c es la coordenada de la que sacaremos sus coordenadas vecinas
	  * @return conjunto  de valores que rodean a la coordenada
	  * @see Coordinate#getNeighborhood() getNeighborhood from Coordinate
	  * @see TreeSet
	  */
	
	public Set<Coordinate> getNeighborhood(Coordinate c) {
		
		Objects.requireNonNull(c);

		TreeSet<Coordinate> conjunto = new TreeSet<Coordinate>();
		
		for (Coordinate caux : c.getNeighborhood()) {
			if (this.inside(caux))
				conjunto.add(caux);
		}	
			
		return conjunto;
	}
	
	/**
	 * El metodo alphaFighter hace que luchen dos cazas y saber su resultado, a la vez
	 * que se le actualizan los resultados de cada nave nodriza
	 * @param f1 el primer luchador invasor
	 * @param f2 el segundo luchador colocado en el tablero
	 * @return el resultado del conflicto
	 */
	private int alphaFighter(Fighter f1, Fighter f2) {
		
		int resultado = f1.fight(f2);
		if (resultado == 1) {
			f2.getMotherShip().updateResults(-1);
			f1.getMotherShip().updateResults(1);
			this.removeFighter(f2);
		}
		
		else if (resultado == -1){
			f1.getMotherShip().updateResults(-1);
			f2.getMotherShip().updateResults(1);
			this.removeFighter(f1);
			
		}
		
		return resultado;
		
	}
	
	/**
	 * El metodo launch lanza un caza al tablero y entra en conflicto dependiendo la casilla y 
	 * el caza que la ocupa. Si el caza ya esta en el tablero, es decir que esta patrullando,
	 * no se le asignara ninguna casilla nueva.
	 * @param c coordenada donde el caza va a intentar colocarse
	 * @param f caza que va a ser lanzado
	 * @return el resultado del conflicto llamando al metodo alfaFighter
	 * @see Fighter#fight(Fighter) fight(Fighter)
	 * @see Map#containsValue(Object) containsValue(Object)
	 */
	public int launch(Coordinate c, Fighter f) {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		
		int resultado = 0;
		
		if (!this.inside(c) || f.isDestroyed())
			return 0;
		
		if (this.getFighter(c) == null) {
			if (!board.containsValue(f)) {
				f.setPosition(c);
				board.put(c, f);
				return 0;
			}
		} 
		
		else if (!this.getFighter(c).getSide().equals(f.getSide())) {
			resultado = alphaFighter(f,board.get(c));
			if (resultado == 1) {		
				f.setPosition(c);
				board.put(c, f);
			}
		}
		
		return resultado;
	}
	
	/**
	 * El metodo patrol realiza movimientos alrededor suya intentando 
	 * encontrar contrincantes con los que luchar
	 * @param f caza que va a realizar la patrulla
	 * @see #launch(Coordinate,Fighter) launch
	 * @see Map#containsValue(Object) containsValue(Object)
	 */
	public void patrol(Fighter f) {
		Objects.requireNonNull(f);
		
		if (board.containsValue(f)) {
			for (Coordinate c : this.getNeighborhood(f.getPosition())) {
				if ((board.get(c) != null) && !(f.getSide().equals(this.getFighter(c).getSide()))) 
					 alphaFighter(f,board.get(c));
					
			}	
		}
	}
}
