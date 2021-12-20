package model;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

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
		Class<?> c = null;
		try {
			c = Class.forName("model.fighters."+type);
			Class<?>[] paramTypes = new Class[] {Ship.class};
			Constructor<?> m= c.getConstructor(paramTypes);
			Object[] arguments = new Object[] {mother}; 
			Fighter f = (Fighter) m.newInstance(arguments);
			
			return f; 
			
		} catch (Exception any) {}		
		return null;
	}
}
