package model;
import java.util.Objects;

import model.fighters.AWing;
import model.fighters.YWing;
import model.fighters.XWing;
import model.fighters.TIEFighter;
import model.fighters.TIEInterceptor;
import model.fighters.TIEBomber;

/**
 *  Clase en donde van a ser creadas nuestros distintos cazas.
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 **/
public class FighterFactory {
	/**
	 * CreateFighter se encarga de crear los tipos cazas adecuados
	 * @param type del caza que va a intentar crearse 
	 * @param mother perteneciente al caza que va a ser creado
	 * @return una subclase de fighter del tipo elegido
	 */
	static public Fighter createFighter(String type, Ship mother) {
		Objects.requireNonNull(type);
		Objects.requireNonNull(mother);
		switch(type) {
			case "AWing":
				return new AWing(mother);
				
			case "YWing":
				return new YWing(mother);
				
			case "XWing":
				return new XWing(mother);
				
			case "TIEBomber":
				return new TIEBomber(mother);
				
			case "TIEFighter":
				return new TIEFighter(mother);
				
			case "TIEInterceptor":
				return new TIEInterceptor(mother);
		
			default:
				return null;
		}
	}
}
