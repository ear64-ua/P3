package model.game;

import model.Side;
import model.exceptions.InvalidSizeException;


public class Game {
	private final int BOARD_SIZE = 10;
	
	private IPlayer imperial;
	
	private IPlayer rebel;
	
	private GameBoard board;
	
	public Game(IPlayer imperial, IPlayer rebel) {
		this.imperial=imperial;
		this.rebel=rebel;
		try {
			board = new GameBoard(BOARD_SIZE);
		} catch (InvalidSizeException e) {
			throw new RuntimeException();
		}
	}
	
	public GameBoard getGameBoard() {
		return board;
	}
	
	private int rebelMove(int numRebel) {

		System.out.println(board);
		System.out.println(imperial.showShip());
		System.out.println(rebel.showShip());
		
		boolean keep = rebel.nextPlay();

		if (!keep)
			return -1;
		else{
			System.out.println("REBEL("+numRebel+"): AFTER REBEL");
			System.out.println(board);
			System.out.println(imperial.showShip());
			System.out.println(rebel.showShip());
			return 1;
		}
	}
	
	private int imperialMove(int numImperial) {
		
		System.out.println("BEFORE IMPERIAL");
		System.out.println(board);
		System.out.println(imperial.showShip());
		System.out.println(rebel.showShip());
		boolean keep = imperial.nextPlay();
		System.out.println("IMPERIAL("+numImperial+"): AFTER IMPERIAL, BEFORE REBEL");
		
		if (!keep)
			return 0;
		else
			return 1;
	}
	
	private int checkFleet() {
		
		if (imperial.isFleetDestroyed())
			return 2;
		else if (rebel.isFleetDestroyed())
			return -2;
		
		return 1;
	}
	
	public Side play() {
		imperial.initFighters();
		rebel.initFighters();
		
		rebel.setBoard(board);
		imperial.setBoard(board);

		int play = 1;
		
		while(play==1){
			
			play = imperialMove(board.numFighters(Side.IMPERIAL));
			play =checkFleet();
			
			play = rebelMove(board.numFighters(Side.REBEL));
			play = checkFleet();
			
		}
		
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
