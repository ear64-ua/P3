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
	
	/**
	 * Variable que va a contener las puntuaciones segun el tipo
	 */
	private SortedSet<ScoreType> scoreSet;
	
	/**
	 * Contructor de la clase Ranking
	 */
	public Ranking() {
		scoreSet=new TreeSet<>();
	}
	
	/**
	 * Incluye el tipo a scoreSet
	 * @param score puntuacion que se va a sumar
	 */
	public void addScore(ScoreType score) {
		scoreSet.add(score);
	}
	
	/**
	 * Devuelve el ganador si no esta vacio scoreSet
	 * @return el ganador
	 */
	public ScoreType getWinner() {
		if (scoreSet.isEmpty())
			throw new RuntimeException();
		return this.scoreSet.first();
	}
	
	/**
	 * Getter de scoreSet
	 * @return el valor de scoreSet
	 */
	public SortedSet<ScoreType> getSortedRanking(){
		return scoreSet;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("|");
		for(ScoreType sc: scoreSet)
			s.append(" ").append(sc).append(" |");
		
		if ((s != null) && (s.length() > 0)) {
		      s.substring(0, s.length() - 1);
		 }
		
		return s+"";
	}
}
