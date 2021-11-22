package mains;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Ship;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.InvalidSizeException;
import model.exceptions.NoFighterAvailableException;
import model.exceptions.OutOfBoundsException;
import model.game.GameBoard;

public class MainP3 {

	public static void main(String[] args) {
		Board b;
		try {
			b = new GameBoard(10);
			Ship imperialCommander = new Ship("Imperial Commander 1",Side.IMPERIAL);
			Ship rebel = new Ship("Corellian Cruiser",Side.REBEL);
		
			imperialCommander.addFighters("8/TIEFighter:8/TIEBomber:5/TIEInterceptor:2/TIEFighter");
			rebel.addFighters("10/XWing:7/YWing:4/AWing:1/YWing");
		
			
			Fighter fi = imperialCommander.getFirstAvailableFighter("TIEFighter");
			try {
				b.launch(new Coordinate(1,1), fi);
			} catch (OutOfBoundsException e) {
				e.printStackTrace();   // error de ejecuciÃ³n
			} catch (FighterAlreadyInBoardException e) {
				e.printStackTrace();   // error de ejecuciÃ³n
			}
			
			
			Fighter fA = rebel.getFirstAvailableFighter("AWing");
			Fighter fB = rebel.getFirstAvailableFighter("XWing");
			try {
				b.launch(new Coordinate(1,3), fB);
				b.launch(new Coordinate(1,2), fA);
			} catch (OutOfBoundsException e) {
				e.printStackTrace();
			} catch (FighterAlreadyInBoardException e) {
				e.printStackTrace();
			}
			
			System.out.println(b);
			
			b.patrol(fi);
			System.out.println(b);
			
			
		} catch (InvalidSizeException | NoFighterAvailableException e1) {
			e1.printStackTrace();  // error de ejecuciÃ³n
		} catch (FighterNotInBoardException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*try {
			GameBoard g = new GameBoard(10);
			System.out.println(g);
		} catch (InvalidSizeException e) {}
		*/
		
	
	}
	
}