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
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

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
			if (s!=null)
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
	
	@Override
	public WinsScore getWinsScore() {
		return ship.getWinsScore();
	}
	
	@Override
	public DestroyedFightersScore getDestroyedFightersScore() {
		return ship.getDestroyedFightersScore();
	}

	/**
	 * Metodo privado encargado de lanzar los cazas
	 * @param x coordenada X
	 * @param y coordenada Y
	 * @param s cadena a leer
	 */
	private void launch(int x, int y, String s) {
		Coordinate c = new Coordinate(x,y);
		int id = 0;
		
		try {
			id = Integer.parseInt(s);
			
		} catch(NumberFormatException e_num) {
			try {
				id = ship.getFirstAvailableFighter(s).getId();
			} catch (NoFighterAvailableException e1) {
				System.out.println(e1.getMessage());
				return;
			}
		}
		
		try {
			ship.launch(id, c, board);
		} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Metodo privado encargado de mejorar un caza
	 * @param saux cadenas a leer
	 */
	private void improve(String saux[]) {
		if(saux.length == 3) {
			try {
				if( Integer.parseInt(saux[2])<100)
					ship.improveFighter(Integer.parseInt(saux[1]), Integer.parseInt(saux[2]), board);
				else
					System.out.println("ERROR: quantity must be less than 100");
			
			} catch (WrongFighterIdException e) { System.out.println(e.getMessage()); }
		}
		
		else 
			System.out.println(ERR_SYNTAX);
	}
	
	/**
	 * Metodo privado que se encarga de decidir el movimiento que se va a realizar
	 * @param saux cadenas de las cuales serviran para decidir la jugada
	 * @return true si sigue jugando o false en caso contrario
	 */
	private boolean decidePlay(String saux[]) {
		
		switch(saux[0]) {
			case "exit":
				return false;
			
			case "improve":
				improve(saux);
				break;
				
			case "patrol":
				if(saux.length==2) {
					try {
						ship.patrol(Integer.parseInt(saux[1]), board);
					} catch (WrongFighterIdException | FighterNotInBoardException e) {  System.out.println(e.getMessage()); }
				}
					
				else
					System.out.println(ERR_SYNTAX);
				break;
			
			case "launch":
				if(saux.length==3) {
					try {
						this.launch(Integer.parseInt(saux[1]),Integer.parseInt(saux[2]), ""+ship.getFirstAvailableFighter("").getId());
					} catch ( NoFighterAvailableException e) {  System.out.println(e.getMessage()); }
				}
				else if(saux.length==4) 
					this.launch(Integer.parseInt(saux[1]),Integer.parseInt(saux[2]),saux[3]);
				
				else
					System.out.println(ERR_SYNTAX);
				
				break;
				
			default:
				System.out.println(ERR_SYNTAX);
		}
		
		return true;
	}
	
	@Override
	public boolean nextPlay() {
		
		String s = "";
		String saux[]= {""};
		
		try {
			s = br.readLine();
		} catch (IOException e) { throw new RuntimeException(); }
		
		
		try {
			saux = s.split(" ");
		}catch(NullPointerException e_null) {System.out.println("ERROR: out of movements");return true;}

		return decidePlay(saux);
		
	}

}
