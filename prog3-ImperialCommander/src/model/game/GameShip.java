package model.game;

import java.util.List;
import java.util.ArrayList;
import model.*;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

public class GameShip extends Ship {

	public GameShip(String name, Side side) { 
		super(name, side); 
	}
	
	public boolean isFleetDestroyed() {
		
		for (Fighter f : super.fleet) {
			if (!f.isDestroyed())
				return false;
		}
		
		return true;
	}
	
	private Fighter getFighter(int id) throws WrongFighterIdException{
		
		for (Fighter f : super.fleet) {
			if (f.getId()==id && !f.isDestroyed())
				return f;
		}
		
		throw new WrongFighterIdException(id);
	}
	
	public List<Integer> getFightersId(String where){
		
		List<Integer> l = new ArrayList<>();
		
		for (Fighter f : super.fleet) {
			
			switch(where) {
				case "board": 
					if (f.getPosition()!=null)
						l.add(f.getId());
				break;
				
				case "ship":
					if (f.getPosition()==null && !f.isDestroyed())
						l.add(f.getId());
				break;
				
				default: 
					if (f.getPosition()!=null)
						l.add(f.getId());
			}
		}
		return l;
	}
	
	public void launch(int id, Coordinate c, Board b) throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException {
		
		Fighter f = this.getFighter(id);
		
		b.launch(c, f);
	}
	
	public void patrol(int id,Board b) throws WrongFighterIdException, FighterNotInBoardException {
		
		Fighter f = this.getFighter(id);
		
		b.patrol(f);
	}
	
	public void improveFighter(int id,int qty, Board b) throws WrongFighterIdException {
		
		Fighter f = this.getFighter(id);
		
		try {
			b.removeFighter(f);
			f.addAttack(qty/2);
			f.addShield(qty-(qty/2));
		} catch (FighterNotInBoardException e) {}

	}
	

}
