package model;
import model.fighters.AWing;
import model.fighters.YWing;
import model.fighters.XWing;
import model.fighters.TIEFighter;
import model.fighters.TIEInterceptor;
import model.fighters.TIEBomber;

public class FighterFactory {
	static public Fighter createFighter(String type, Ship mother) {
		
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
