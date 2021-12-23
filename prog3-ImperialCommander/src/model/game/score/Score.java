package model.game.score;
import model.*;

/**
*   Clase que implementa las puntuaciones
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
*   @param <T> el tipo de clase modelada por esta
**/
public abstract class Score<T> implements Comparable<Score<T>> {
	protected int score;
	private Side side;
	
	/**
	 * Contructor de la clase Score
	 * @param side
	 */
	public Score(Side side) {
		this.side=side;
	}
	
	/**
	 * Getter de score
	 * @return el valor de score
	 */
	public int getScore() {
		return score;
	}
	
	@Override
	public int compareTo(Score<T> other) {
		if (Integer.compare(other.score, this.score)==0)
			return this.side.compareTo(other.side);
		
		else if (other.score>this.score)
			return 1;
		else 
			return -1;
	}
	
	@Override
	public String toString() {
		return "Player "+side+": "+score;
	}
	
	/**
	 * Metodo abstracto de score
	 * @param sc su valor varia segun el tipo
	 */
	public abstract void score(T sc);
		
	
}
