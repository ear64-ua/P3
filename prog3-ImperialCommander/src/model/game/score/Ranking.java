package model.game.score;

import java.util.SortedSet;
import java.util.TreeSet;

/**
*   @param <ScoreType> the type of the class modeled by this {@code Class}
* 	object.  For example, the type of {@code String.class} is {@code
* 	Class<String>}.  Use {@code Class<?>} if the class being modeled is
* 	unknown.
* 	
* 	
**/
public class Ranking<ScoreType extends Score<?>>{
	
	private SortedSet<ScoreType> scoreSet;
	
	public Ranking() {
		scoreSet=new TreeSet<>();
	}
	
	public void addScore(ScoreType score) {
		scoreSet.add(score);
	}
	
	public ScoreType getWinner() {
		if (scoreSet.isEmpty())
			throw new RuntimeException();
		return this.scoreSet.first();
	}
	
	public SortedSet<ScoreType> getSortedRanking(){
		return scoreSet;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder("| ");
		for(ScoreType sc: scoreSet)
			s.append(sc).append(" | ");
		
		if ((s != null) && (s.length() > 0)) {
		      s.substring(0, s.length() - 1);
		 }
		
		return s+"";
	}
}
