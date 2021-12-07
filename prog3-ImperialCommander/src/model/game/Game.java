package model.game;

import java.util.Objects;

import model.Side;
import model.exceptions.InvalidSizeException;

/**
 *  Clase principal que engloba el juego
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
public class Game {
	/**
	 * Las dimensiones del tablero como constante
	 */
	private final int BOARD_SIZE = 10;
	
	/**
	 * Atributo que sirve como jugador del lado imperial
	 */
	private IPlayer imperial;
	
	/**
	 * Atributo que sirve como jugador del lado rebelde
	 */
	private IPlayer rebel;
	
	/**
	 * Atributo privado de GameBoard
	 */
	private GameBoard board;
	
	/**
	 * Constructor de Game
	 * @param imperial se iguala al parametro pasado
	 * @param rebel se iguala al parametro pasado
	 */
	public Game(IPlayer imperial, IPlayer rebel) {
		Objects.requireNonNull(imperial);
		Objects.requireNonNull(rebel);
		
		this.imperial=imperial;
		this.rebel=rebel;
		try {
			board = new GameBoard(BOARD_SIZE);
			rebel.setBoard(board);
			imperial.setBoard(board);
		} catch (InvalidSizeException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * Getter del tablero
	 * @return el tablero
	 */
	public GameBoard getGameBoard() {
		return board;
	}
	
	/**
	 * Metodo privado que realiza los movimientos del lado rebelde y los muestra
	 * @param numRebel numero de cazas en el tablero
	 * @return {@code -1} si no sigue jugando {@code 1} en caso contrario
	 */
	private int rebelMove(int numRebel) {
		
		System.out.println("REBEL("+numRebel+"): ");

		if (!rebel.nextPlay())
			return -1;
		
		System.out.println("AFTER REBEL");
		showBoardFleet();
		return 1;
		
	}
	
	private void showBoardFleet() {
		System.out.println(board);
		System.out.println(imperial.showShip());
		System.out.println(rebel.showShip());
	}
	/**
	 * Metodo privado que realiza los movimientos del lado imperial y los muestra
	 * @param numRebel numero de cazas en el tablero
	 * @return {@code 0} si no sigue jugando {@code 1} en caso contrario
	 */
	private int imperialMove(int numImperial) {
		
		System.out.println("BEFORE IMPERIAL");
		showBoardFleet();
		System.out.println("IMPERIAL("+numImperial+"): ");
		
		if (!imperial.nextPlay())
			return 0;
		
		System.out.println("AFTER IMPERIAL, BEFORE REBEL");
		showBoardFleet();
		
		return 1;
	}
	
	/**
	 * Metodo privado que prueba que alguna flota haya sido desrtuida
	 * @return {@code 2} si la flota imperial ha sido destruida, {@code -2} 
	 * si la flota rebelde ha sido destruida y {@code 1} en caso contrario
	 */
	private int checkFleet() {
		
		if (imperial.isFleetDestroyed())
			return 2;
		else if (rebel.isFleetDestroyed())
			return -2;
		
		return 1;
	}
	
	/**
	 * Metodo que simula el juego entre dos jugadores
	 * @return el lado del bando que gana
	 */
	public Side play() {
		imperial.initFighters();
		rebel.initFighters();

		int play = 1;
		
		while(play==1){
			
			play = imperialMove(board.numFighters(Side.IMPERIAL));
			play = checkFleet();
			
			play = rebelMove(board.numFighters(Side.REBEL));
			imperial.purgeFleet();
			rebel.purgeFleet();
			play = checkFleet();	
		}
		imperial.purgeFleet();
		rebel.purgeFleet();
		
		switch(play) {
			case(-1)://exit rebel
				return Side.IMPERIAL;
			case(-2)://rebel fleet destroyed
				return Side.IMPERIAL;
			case(0)://exit imperial
				return Side.REBEL;
			case(2)://imperial fleet destroyed
				return Side.REBEL;
		}
		
		return null;
	}
}
