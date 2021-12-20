package model.game.score;
import model.*;

/**
*   @param <T> the type of the class modeled by this {@code Class}
* 	object.  For example, the type of {@code String.class} is {@code
* 	Class<String>}.  Use {@code Class<?>} if the class being modeled is
* 	unknown.
* 	
* 	
**/
public abstract class Score<T> implements Comparable<Score<T>> {
	protected int score;
	private Side side;
	
	public Score(Side side) {
		this.side=side;
	}
	
	public int getScore() {
		return score;
	}
	
	public int compareTo(Score<T> other) {
		if (Integer.compare(other.score, this.score)==0)
			return this.side.compareTo(other.side);
		
		else if (other.score>this.score)
			return 1;
		else 
			return -1;
	}
	
	public String toString() {
		return "Player "+side+": "+score;
	}
	
	public abstract void score(T sc);
		
	
}
