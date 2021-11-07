package model;

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterIsDestroyedException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.InvalidSizeException;
import model.exceptions.OutOfBoundsException;

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
	 * @throws InvalidSizeException 
	 * @see HashMap
	 */
	public Board(int size) throws InvalidSizeException {
		if (size < 5)
			throw new InvalidSizeException(size);
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
			return board.get(c).copy();
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
	 * @throws FighterNotInBoardException 
	 */
	public void removeFighter(Fighter f) throws FighterNotInBoardException {
		
		Objects.requireNonNull(f);
		
		if (board.containsKey(f.getPosition()) && board.get(f.getPosition()).equals(f) && f.getPosition()!=null){
			board.remove(f.getPosition());
			f.setPosition(null);
		}

		else 
			throw new FighterNotInBoardException(f);
		
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
	 * @throws OutOfBoundsException 
	  * @see Coordinate#getNeighborhood() getNeighborhood from Coordinate
	  * @see TreeSet
	  */
	
	public Set<Coordinate> getNeighborhood(Coordinate c) throws OutOfBoundsException{
		
		Objects.requireNonNull(c);
		
		if (!this.inside(c))
			throw new OutOfBoundsException(c);

		TreeSet<Coordinate> conjunto = (TreeSet<Coordinate>)c.getNeighborhood();
		TreeSet<Coordinate> conjaux = new TreeSet<Coordinate>();
		
		for (Coordinate caux : c.getNeighborhood()) {
			
				if (!this.inside(caux))
					conjaux.add(caux);
		}
		
		conjunto.removeAll(conjaux);
			
		return conjunto;
	}
	
	/**
	 * El metodo alphaFighter hace que luchen dos cazas y saber su resultado, a la vez
	 * que se le actualizan los resultados de cada nave nodriza
	 * @param f1 el primer luchador invasor
	 * @param f2 el segundo luchador colocado en el tablero
	 * @return el resultado del conflicto
	 */
	private int alphaFighter(Fighter f1, Fighter f2){
		try {
			int resultado = f1.fight(f2);
			
			if (resultado == 1) {
				f2.getMotherShip().updateResults(-1);
				f1.getMotherShip().updateResults(1);
			}
		
			else if (resultado == -1){
				f1.getMotherShip().updateResults(-1);
				f2.getMotherShip().updateResults(1);
			}
			
			return resultado;
		} catch(FighterIsDestroyedException e){throw new RuntimeException();}
	}
	
	/**
	 * El metodo launch lanza un caza al tablero y entra en conflicto dependiendo la casilla y 
	 * el caza que la ocupa. Si el caza ya esta en el tablero, es decir que esta patrullando,
	 * no se le asignara ninguna casilla nueva.
	 * @param c coordenada donde el caza va a intentar colocarse
	 * @param f caza que va a ser lanzado
	 * @return el resultado del conflicto llamando al metodo alfaFighter
	 * @throws FighterAlreadyInBoardException 
	 * @throws OutOfBoundsException
	 * @see Fighter#fight(Fighter) fight(Fighter)
	 * @see Map#containsValue(Object) containsValue(Object)
	 */
	public int launch(Coordinate c, Fighter f) throws FighterAlreadyInBoardException, OutOfBoundsException {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		
		int resultado = 0;
		
		// si c no pertenece al tablero --> Exception
		if (!this.inside(c))
			throw new OutOfBoundsException(c);
		
		// si c sí pertenece al tablero
		else{
			// si f ya tiene una coordenada --> Exception
			if (board.containsValue(f))
				throw new FighterAlreadyInBoardException(f);
			// si f no tiene ya una coordenada
			else {
				// si no hay fighter en c 
				if (this.getFighter(c) == null) {
					f.setPosition(c);
					board.put(c, f);
					return resultado;
				}
				
				// si son del mismo bando
				if (!this.getFighter(c).getSide().equals(f.getSide()) && board.containsKey(c)) {
					
					resultado = alphaFighter(f,board.get(c));
					
					if (resultado == 1) {		
						f.setPosition(c);
						board.put(c, f);
						try {
							this.removeFighter(board.get(c));
						}catch(FighterNotInBoardException e) {throw new RuntimeException();}
					}
					else if (resultado == -1) {
						try {
							this.removeFighter(f);
						}catch(FighterNotInBoardException e) {throw new RuntimeException();}
							
					
					}
						
				}
			}
				 
		}
		
		return resultado;
	}
	
	/**
	 * El metodo patrol realiza movimientos alrededor suya intentando 
	 * encontrar contrincantes con los que luchar
	 * @param f caza que va a realizar la patrulla
	 * @throws FighterNotInBoardException 
	 * @see #alphaFighter(Fighter,Fighter) AlphaFighter
	 * @see Map#containsValue(Object) containsValue(Object)
	 */
	public void patrol(Fighter f) throws FighterNotInBoardException{
		Objects.requireNonNull(f);
		int resultado;
		
		if (board.containsValue(f)) {
				try {
					for (Coordinate c : this.getNeighborhood(f.getPosition())) {
						if ((board.get(c) != null) && !(f.getSide().equals(this.getFighter(c).getSide()))) {
								resultado = alphaFighter(f,board.get(c));
								
								if (resultado == 1) {	
									this.removeFighter(board.get(c));
									
								}
								else if (resultado == -1) {
									this.removeFighter(f);
								}
						}
							
					}
				} catch (OutOfBoundsException e) {throw new RuntimeException();}
		}
		
		else
			throw new FighterNotInBoardException(f);
		
		
		
		
	}
}
