package model.game;

import java.util.List;

import model.*;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

public class PlayerRandom implements IPlayer {
	
	private int numFighters;
	
	private GameBoard board;
	
	private GameShip ship;
	
	private final String[] imperialType = {"TIEFighter", "TIEBomber", "TIEInterceptor"}; 
	
	private final String[] rebelType = {"XWing", "YWing", "AWing"}; 
	
	public PlayerRandom(Side side, int numFighters) {
		this.numFighters = numFighters;
		ship = new GameShip("PlayerRandom " + ((side == Side.REBEL) ? "REBEL" : "IMPERIAL" ) + " Ship" ,side);
	}
	
	public void setBoard(GameBoard g) {
		this.board = g;
	}
	
	public GameShip getGameShip() {
		return null;
	}
	
	@Override
	public void initFighters() {
		
		String[] type = (ship.getSide() == Side.REBEL ) ? rebelType : imperialType;
		
		for (int i = 0; i < type.length; i++) {
			
			int random = RandomNumber.newRandomNumber(numFighters-1);
			
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
	
	//excepciones ID?
	private void actionPlay(int option, int random, List<Integer> l) {
		
		try {
			switch ((0 <= option && option <= 24 ) ? 0 : (25 <= option && option <= 84) ? 1 :
	    		(85 <= option && option <= 98) ? 2 : 3) {
	
		        case 0:
					ship.patrol(l.get(random), board);
		            break;
		            
		        case 1:
		        	int x = RandomNumber.newRandomNumber(board.getSize());
					int y = RandomNumber.newRandomNumber(board.getSize());
					ship.launch(l.get(random), new Coordinate(x,y), board);
		            break;
		            
		        case 2:
					ship.improveFighter(l.get(random), option, board);
		            break;
		            
		        case 3:
		        	break;
			}
		} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException | FighterNotInBoardException e) {
			throw new RuntimeException();
		}
		

	}
	
	@Override
	public boolean nextPlay() {
		
		int option = RandomNumber.newRandomNumber(100);
		
		if (option == 99)
			return false;
		
		List<Integer> l = ship.getFightersId("ship");
		
		int random = RandomNumber.newRandomNumber(l.size());
		
		if (l.isEmpty()) {
			System.out.println("ERROR: empty ship");
			return true;
		}
		
		actionPlay( option, random, l);
		
		return true;
	}
}
