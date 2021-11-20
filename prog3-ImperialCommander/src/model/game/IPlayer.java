package model.game;

/**
 *  -------------------
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
public interface IPlayer {

		/**
		 * 
		 * @param gb
		 */
		public void setBoard(GameBoard gb);
		
		/**
		 * 
		 * @return
		 */
		public GameShip getGameShip();
		
		/**
		 * 
		 */
		public void initFighters();
		
		/**
		 * 
		 * @return
		 */
		public boolean isFleetDestroyed();
		
		/**
		 * 
		 * @return
		 */
		public String showShip();
		
		/**
		 * 
		 */
		public void purgeFleet();
		
		/**
		 * 
		 * @return
		 */
		public boolean nextPlay();
		
}
