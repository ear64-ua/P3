package model.game;

import java.io.BufferedReader;
import java.io.IOException;

import model.*;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.NoFighterAvailableException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

public class PlayerFile implements IPlayer {
	
	/**
	 * 
	 */
	private final String ERR_SYNTAX = "ERROR: wrong syntax";
	
	/**
	 * 
	 */
	private GameShip ship;
	
	/**
	 * 
	 */
	private GameBoard board;
	
	/**
	 * 
	 */
	private BufferedReader br;
	
	/**
	 * 
	 * @param side
	 * @param br
	 */
	public PlayerFile(Side side, BufferedReader br) {
		
		ship = new GameShip("PlayerFile" + ((side == Side.REBEL) ? "REBEL" : "IMPERIAL" ) + super.getClass().getSimpleName() ,side);
		this.br = br;
	}

	@Override
	public void setBoard(GameBoard gb) {
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
