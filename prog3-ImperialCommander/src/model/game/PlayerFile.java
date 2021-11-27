package model.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import model.*;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.NoFighterAvailableException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

/**
 *  Clase permite leer los movimientos de un jugador desde un fichero o desde la consola
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
public class PlayerFile implements IPlayer {
	
	/**
	 * Constante usada cuando hay error de sintaxis
	 */
	private final String ERR_SYNTAX = "ERROR: wrong syntax";
	
	/**
	 * Atributo de la nave
	 */
	private GameShip ship;
	
	/**
	 * Atributo del tablero
	 */
	private GameBoard board;
	
	/**
	 * Atributo de la clase BufferedReader
	 */
	private BufferedReader br;
	
	/**
	 * Constructor de PlayerFile
	 * @param side bando al que va a pertenecer
	 * @param br lector de los movimientos del jugador
	 */
	public PlayerFile(Side side, BufferedReader br) {
		Objects.requireNonNull(side);
		Objects.requireNonNull(br);
		
		ship = new GameShip("PlayerFile " + ((side == Side.REBEL) ? "REBEL" : "IMPERIAL" ) + " Ship" ,side);
		this.br = br;
	}

	@Override
	public void setBoard(GameBoard gb) {
		Objects.requireNonNull(gb);
		board = gb;
	}

	@Override
	public GameShip getGameShip() {
		return ship;
	}

	@Override
	public void initFighters() {
		
		try {
			String s = br.readLine();
			ship.addFighters(s);
		} catch (IOException e) { throw new RuntimeException(); }
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
	 * Metodo privado encargado de lanzar los cazas
	 * @param x
	 * @param y
	 * @param s
	 */
	private void launch(int x, int y, String s) {
		Coordinate c = new Coordinate(x,y);
		int id = 0;
		
		try {
			id = Integer.parseInt(s);
			
		} catch(NumberFormatException e) {
			try {
				id = ship.getFirstAvailableFighter(s).getId();
			} catch (NoFighterAvailableException e1) {
				e.getMessage();
				return;
				//comprobar si lanza dos mensajes de excepciones
			}
		}
		
		try {
			ship.launch(id, c, board);
		} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException e) {
			e.getMessage();
		}
	}
	
	@Override
	public boolean nextPlay() {
		
		String s = "";
		
		try {
			s = br.readLine();
			
		} catch (IOException e) { throw new RuntimeException(); }
		
		String saux[] = s.split(" ");
		switch(saux[0]) {
			case "exit":
				return false;
			
			case "improve":
				if(saux.length == 3) {
					try {
						ship.improveFighter(Integer.parseInt(saux[1]), Integer.parseInt(saux[2]), board);
					
					} catch (WrongFighterIdException e) { e.getMessage(); }
				}
				
				else 
					System.out.println(ERR_SYNTAX);
				break;
				
			case "patrol":
				if(saux.length == 2) {
					try {
						ship.patrol(Integer.parseInt(saux[1]), board);
					} catch (WrongFighterIdException | FighterNotInBoardException e) {
						e.getMessage();
					}
				}
					
				else
					System.out.println(ERR_SYNTAX);
				break;
			
			case "launch":
				switch(saux.length) {
					case 3:
						
						try {
							
							this.launch(Integer.parseInt(saux[1]),Integer.parseInt(saux[2]), ""+ship.getFirstAvailableFighter("").getId());
						
						} catch ( NoFighterAvailableException e) {
						
							e.getMessage();
						}
						
					break;
						
					case 4:
						
						this.launch(Integer.parseInt(saux[1]),Integer.parseInt(saux[2]),saux[3]);
							
						break;
						
					default:
						System.out.println(ERR_SYNTAX);
				}
				
				break;
				
			default:
				System.out.println(ERR_SYNTAX);
							
		}
		
		return true;
	}

}
