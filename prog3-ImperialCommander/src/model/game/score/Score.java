package model.game.score;
import model.*;

public abstract class Score<T> implements Comparable<T> {
	protected int score;
	private Side side;
	
	public Score(Side side) {
		
	}
	
	public int getScore() {
		return score;
	}
	
	public int compareTo(Score<T> other) {
		return 0;
	}
	
	public String toString() {
		return "";
	}
	
	public abstract void score(T sc);
		
	
}
