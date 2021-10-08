package model;

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 *  Esta diseñada para contener en un mapa, 
 *  nuestros cazas.
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/

public class Board {
	
	private int size;
	private Map<Coordinate, Fighter> board;
	
	/**
	 * Es el constructor que inicializa el tamaño 
	 * y crea la instancia de un HashMap
	 * @param size dimension del cual se va a crear el tablero (size x size)
	 * @see HashMap
	 */
	public Board(int size) {
		this.size=size;
		board = new HashMap<Coordinate,Fighter>();
	}
	
	/**
	 * getFighter es un getter del objeto Fighter 
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
	 * @return la dimension del tablero
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * RemoveFighter compara la posicion del caza
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
	 * inside se encarga de controlar que la coordenada se
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
	  * getNeighborhood() deviuelve las coordenadas que le rodean y que estan dentro del tablero
	  * @param c es la coordenada de la que sacaremos sus coordenadas vecinas
	  * @return conjunto  de valores que rodean a la coordenada
	  * @see Coordinate#getNeighborhood() getNeighborhood from Coordinate
	  * @see TreeSet
	  */
	
	public Set<Coordinate> getNeighborhood(Coordinate c) {
		
		Objects.requireNonNull(c);

		TreeSet<Coordinate> conjunto = new TreeSet<Coordinate>();
		
		for (Coordinate caux : c.getNeighborhood()) {
			//revisar inside
			if (this.inside(caux))
				conjunto.add(caux);
		}	
			
		return conjunto;
	}
	
	/**
	 * launch() lanza un caza al tablero y entra en conflicto dependiendo la casilla y 
	 * el caza que la ocupa. Si el caza ya esta en el tablero, es decir que esta patrullando,
	 * no se le asignara ninguna casilla nueva.
	 * @param c coordenada donde el caza va a intentar colocarse
	 * @param f caza que va a ser lanzado
	 * @return el resultado del conflicto llamando al
	 * 		   metodo fight de la clase Fighter, y cero si c esta fuera del tablero 
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
			resultado = f.fight(board.get(c));
			if (resultado == 1) {
				board.get(c).getMotherShip().updateResults(-1);
				f.getMotherShip().updateResults(1);
				this.removeFighter(board.get(c));
				
				if (f.getPosition()==null) {
					f.setPosition(c);
					board.put(c, f);
				}
			}
			
			else if (resultado == -1){
				f.getMotherShip().updateResults(-1);
				board.get(c).getMotherShip().updateResults(1);
				this.removeFighter(f);
				
			}
		}
		
		return resultado;
	}
	
	/**
	 * patrol realiza movimientos alrededor suya intentando 
	 * encontrar contrincantes con los que luchar
	 * @param f caza que va a realizar la patrulla
	 * @see #launch(Coordinate,Fighter) launch
	 * @see Map#containsValue(Object) containsValue(Object)
	 */
	public void patrol(Fighter f) {
		Objects.requireNonNull(f);
		
		if (board.containsValue(f)) {
			for (Coordinate c : this.getNeighborhood(f.getPosition())) {
				this.launch(c,f);
			}
		}
	}
}