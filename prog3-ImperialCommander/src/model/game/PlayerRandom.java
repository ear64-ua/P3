package model.game;

import java.util.List;
import java.util.Objects;

import model.*;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

/**
 *  Clase que simula un jugador que juega al azar
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
public class PlayerRandom implements IPlayer {
	
	/**
	 * Atributo que contiene el numero de cazas del jugador
	 */
	private int numFighters;
	
	/**
	 * Atributo que contiene el tablero al que pertenece dentro del juego
	 */
	private GameBoard board;
	
	/**
	 * Atributo que contiene la nave al que pertenece dentro del juego
	 */
	private GameShip ship;
	
	/**
	 * Cadena de tipo String que contiene los diferentes tipos de caza del bando Imperial
 	 */
	public final String[] imperialType = {"TIEFighter", "TIEBomber", "TIEInterceptor"}; 
	
	/**
	 * Cadena de tipo String que contiene los diferentes tipos de caza del bando Rebel
 	 */
	public final String[] rebelType = {"XWing", "YWing", "AWing"}; 
	
	/**
	 * Constructor de PlayerRandom
	 * @param side bando al que va a pertenecer
	 * @param numFighters numero de cazas que llega a tener
	 */
	public PlayerRandom(Side side, int numFighters) {
		Objects.requireNonNull(side);
		this.numFighters = numFighters;
		ship = new GameShip("PlayerRandom " + ((side == Side.REBEL) ? "REBEL" : "IMPERIAL" ) + " Ship" ,side);
	}
	
	@Override
	public void setBoard(GameBoard g) {
		Objects.requireNonNull(g);
		this.board = g;
	}
	
	@Override
	public GameShip getGameShip() {
		return ship;
	}
	
	@Override
	public void initFighters() {

		String[] type = (ship.getSide() == Side.REBEL ) ? rebelType : imperialType;

		for (int i = 0; i < type.length; i++) {
			
			int random = RandomNumber.newRandomNumber(numFighters);
			if (random != 0)
				ship.addFighters(random + "/" + type[i]);
		}
	}

	@Override
	public boolean isFleetDestroyed() {
		return ship.isFleetDestroyed();
	}

	@Override
	public String showShip() {
		return ship + "\n" + ship.showFleet();
	}

	@Override
	public void purgeFleet() {
		ship.purgeFleet();
	}
	
	/**
	 * Metodo que se encarga de realizar un determinado movimiento dependiendo de la opcion
	 * @param option numero al que esta asignado un determinado movimiento
	 * @param id perteneciente a la posicion de la lista
	 */
	private void actionPlay(int option, int id) {
		
		try {
			switch ((0 <= option && option <= 24 ) ? 0 : (25 <= option && option <= 84) ? 1 :
	    		(85 <= option && option <= 98) ? 2 : 3) {
	
		        case 0:	
						ship.patrol(id, this.board);
			            break;
		            
		        case 1:
		        	int x = RandomNumber.newRandomNumber(board.getSize());
					int y = RandomNumber.newRandomNumber(board.getSize());
					ship.launch(id, new Coordinate(x,y), this.board);
		            break;
		            
		        case 2:
					ship.improveFighter(id, option, this.board);
		            break;
		            
		        case 3:
		        	break;
			}
		} catch (WrongFighterIdException | FighterAlreadyInBoardException |
				 OutOfBoundsException | FighterNotInBoardException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public boolean nextPlay() {
		
		int option = RandomNumber.newRandomNumber(100);
		
		if (option == 99)
			return false;
		
		List<Integer> l = (0 <= option && option <= 24) ? ship.getFightersId("board") : (25 <= option && option <= 84) ? 
				ship.getFightersId("ship") : ship.getFightersId("");
		
		if (l.isEmpty()) {
			if (25 <= option && option <= 84)
				System.out.println("ERROR: empty "+ship.getSide()+" ship");
			else if (0 <= option && option <= 24)
				System.out.println("ERROR: empty "+ship.getSide()+" board");
			else
				System.out.println("ERROR: no "+ship.getSide()+" fighters available");
			return true;
		}
		
		int position = RandomNumber.newRandomNumber(l.size());
		
		actionPlay( option, l.get(position));
		
		return true;
	}
}
