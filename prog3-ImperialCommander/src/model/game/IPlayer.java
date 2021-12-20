package model.game;

import model.game.score.*;

/**
 *  Interfaz que modela un jugador del juego
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
public interface IPlayer {
		
		/**
		 * Asigna el tablero pasado como parametro al atributo {@code board} del jugador
		 * @param gb usado para igualarlo al atributo {@code board} del jugador
		 */
		public void setBoard(GameBoard gb);
		
		/**
		 * Getter de GameShip
		 * @return el objeto GameShip al que pertenece
		 */
		public GameShip getGameShip();
		
		/**
		 * Obtiene una cadena para inicializar los cazas de cada jugador
		 */
		public void initFighters();
		
		/**
		 * Llama al metodo del mismo nombre de la nave del jugador
		 * @return {@code true} si la nave se encuentra destruida, {@code false} en caso contrario
		 */
		public boolean isFleetDestroyed();
		
		/**
		 * Metodo que muestra una cadena con informacion acerca de la nave
		 * @return una cadena formada por el metodo toString, un cambio de linea y la cadena devuelta por el metodo showFleet
		 */
		public String showShip();
		
		/**
		 * Llama al metodo del mismo nombre de la nave del jugador
		 */
		public void purgeFleet();
		
		/**
		 * Metodo que se dedica a analizar la siguiente jugada de un jugador
		 * @return {@code true} si sigue jugando, o {@code false} si el jugador abandona
		 */
		public boolean nextPlay();
		
		/**
		 * Llama al metodo del mismo nombre de la nave del jugador
		 * @return el valor de winsScore
		 */
		public WinsScore getWinsScore();
		
		/**
		 * Llama al metodo del mismo nombre de la nave del jugador
		 * @return el valor de destroyedFightersScore
		 */
		public DestroyedFightersScore getDestroyedFightersScore();

}
