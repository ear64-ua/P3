package model.game;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import model.*;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
import model.game.score.*;

/**
 *  Subclase de Ship que se usa en el juego
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
public class GameShip extends Ship {
	
	/**
	 * puntuacion de tipo winsScore
	 */
	WinsScore winsScore = new WinsScore(super.getSide());
	/**
	 * puntuacion de tipo destroyedFightersScore
	 */
	DestroyedFightersScore destroyedFightersScore = new DestroyedFightersScore(super.getSide());
	
	/**
	 * Contructor de GameShip que llama al constructor de la clase padre
	 * @param name es el nombre asignado al objeto
	 * @param side es el lado al que se le asigna al objeto
	 */
	public GameShip(String name, Side side) { 
		super(name, side); 
	}
	
	/**
	 * Metodo que comprueba si la flota se encuentra destruida
	 * @return {@code true} si se encuentra destruida, {@code false} en caso contrario
	 */
	public boolean isFleetDestroyed() {
		
		for (Fighter f : super.fleet) {
			if (!f.isDestroyed())
				return false;
		}
		
		return true;
	}
	
	/**
	 * Getter privado que busca en la flota de la nave,un caza cuyo id coincida con el argumento pasado
	 * @param id al que le pertenece al caza
	 * @return el caza perteneciente al id
	 * @throws WrongFighterIdException si no encuentra al caza o se encuentra destruido
	 */
	private Fighter getFighter(int id) throws WrongFighterIdException{
		
		for (Fighter f : super.fleet) {
			if (f.getId()==id && !f.isDestroyed())
				return f;
		}
		
		throw new WrongFighterIdException(id);
	}
	
	/**
	 * Getter de una lista de ids de cazas no destruidos
	 * @param where dependiendo de su valor va a buscar en el tablero, la nave o cualquiera de los dos
	 * @return la lista de ids
	 */
	public List<Integer> getFightersId(String where){
		
		List<Integer> l = new ArrayList<>();
		
		for (Fighter f : super.fleet) {
			
			switch(where) {
				case "board": 
					if (f.getPosition()!=null && !f.isDestroyed())
						l.add(f.getId());
				break;
				
				case "ship":
					if (f.getPosition()==null && !f.isDestroyed())
						l.add(f.getId());
				break;
				
				default: 
					if (!f.isDestroyed())
						l.add(f.getId());
			}
		}
		return l;
	}
	
	/**
	 * Getter de winsScore
	 * @return el valor de winsScore
	 */
	public WinsScore getWinsScore() {
		return winsScore;
	}
	
	/**
	 * Getter de destroyedFightersScore
	 * @return el valor de destroyedFighterScore
	 */
	public DestroyedFightersScore getDestroyedFightersScore() {
		return destroyedFightersScore;
	}
	
	@Override
	public void updateResults(int r, Fighter f) {
		super.updateResults(r, f);
		
		if(r==1) {
			winsScore.score(r);
			destroyedFightersScore.score(f);
		}
	}
	
	/**
	 * Metodo que obtiene un caza y lo lanza al tablero
	 * @param id usado para buscar el caza a lanzar
	 * @param c usado para lanzar el caza en dicha coordenada
	 * @param b usado para lanzar el caza en dicho tablero
	 * @throws WrongFighterIdException cuando no se encuentra el caza con el id pasado
	 * @throws FighterAlreadyInBoardException cuando el caza ya se encuentra en el tablero
	 * @throws OutOfBoundsException cuando la coordenada esta fuera de los limites
	 */
	public void launch(int id, Coordinate c, Board b) throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException {
		Objects.requireNonNull(c);
		Objects.requireNonNull(b);
		
		Fighter f = this.getFighter(id);
		
		b.launch(c, f);
	}
	
	/**
	 * Metodo que hace que un caza patrulle 
	 * @param id usado para buscar el caza a patrullar
	 * @param b usado para que el caza patrulle en dicho tablero 
	 * @throws WrongFighterIdException cuando no se encuentra el caza con el id pasado
	 * @throws FighterNotInBoardException cuando el caza no se encuentra en el tablero
	 */
	public void patrol(int id,Board b) throws WrongFighterIdException, FighterNotInBoardException {
		Objects.requireNonNull(b);
		
		Fighter f = this.getFighter(id);
		
		b.patrol(f);
	}
	
	
	/**
	 * Metodo que hace que un caza explore 
	 * @param id usado para buscar el caza a explorar
	 * @param b usado para que el caza explore en dicho tablero 
	 * @throws WrongFighterIdException cuando no se encuentra el caza con el id pasado
	 * @throws FighterNotInBoardException cuando el caza no se encuentra en el tablero
	 */
	public void cross(int id,Board b) throws WrongFighterIdException, FighterNotInBoardException {
		Objects.requireNonNull(b);
		
		Fighter f = this.getFighter(id);
		
		b.cross(f);
	}
	
	
	
	/**
	 * Metodo que hace que un caza sea mejorado
	 * @param id usado para buscar el caza a lanzar
	 * @param qty usado para saber la cantidad en la que va a ser mejorado
	 * @param b usado para quitar el caza del tablero
	 * @throws WrongFighterIdException cuando no se encuentra el caza con el id pasado
	 */
	public void improveFighter(int id,int qty, Board b) throws WrongFighterIdException {
		Objects.requireNonNull(b);
		
		Fighter f = this.getFighter(id);
		
		try {
			b.removeFighter(f);
		} catch (FighterNotInBoardException e) {}
		
		f.addAttack(qty/2);
		f.addShield(qty-(qty/2));
	}
}
