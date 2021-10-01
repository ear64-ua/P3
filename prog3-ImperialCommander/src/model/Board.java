package model;

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 * 
 **/

public class Board {
	
	private int size;
	Map<Coordinate, Fighter> board;
	
	public Board(int size) {
		board = new HashMap<Coordinate,Fighter>();
	}
	
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		if (board.containsKey(c))
			return new Fighter (board.get(c));
		else
			return null;
	}
	
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
		
		if (board.get(f.getPosition()).equals(f)) {
			board.remove(f.getPosition());
			return true;
		}
		else 
			return false;
	}
	
	public Boolean inside(Coordinate c) {
		
		Objects.requireNonNull(c);
		Set<Coordinate> coordenadas = board.keySet();
		
		for (Coordinate caux : coordenadas) {
			
			if (caux.equals(c))
				return true;
		}
		
		return true;
		
	}
	
	 /**
	  * getNeighborhood() deviuelve las coordenadas que le rodean
	  * @param c es la coordenada de la que sacaremos sus coordenadas vecinas
	  * @return conjunto  de valores que rodean a la coordenada
	  */
	
	public Set<Coordinate> getNeighborhood(Coordinate c) {
		
		Objects.requireNonNull(c);

		return c.getNeighborhood();
	}
	
	public int launch(Coordinate c, Fighter f) {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		
		int resultado = 0;
		
		if (this.getFighter(c) == null) {
			board.put(c, f);
			f.setPosition(c);
		}
		
		else if (!this.getFighter(c).getSide().equals(f.getSide())) {
			resultado = board.get(c).fight(f);
			
			if (resultado == -1) {
				board.get(c).getMotherShip().updateResults(-1);
				f.getMotherShip().updateResults(1);
				this.removeFighter(board.get(c));
				if (!board.containsValue(f)) {
					board.put(c, f);
					f.setPosition(c);
				}
			}
			
			else if (resultado == 1) {
				f.getMotherShip().updateResults(-1);
				board.get(c).getMotherShip().updateResults(1);
			}
		}
		
		return resultado;
	}
	
	public void patrol(Fighter f) {
		Objects.requireNonNull(f);
		
		if (board.containsValue(f)) {
			for (Coordinate c : this.getNeighborhood(f.getPosition())) {
				this.launch(c,f);
			}
		}
	}
	
	

}
