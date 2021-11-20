package model.game;

public class PlayerRandom {
	private int numFighters;
	
	private GameBoard board;
	
	public PlayerRandom(Side side, int numFighters) {
		this.numFighters = numFighters;
	}
	
	public void setBoard(GameBoard g) {
		this.board = g;
	}
	
	public GameShip getGameShip() {
		return null;
	}
	
	public boolean isFleetDestroyed() {return false;}
	
	public String showShip() {return "";}
}
