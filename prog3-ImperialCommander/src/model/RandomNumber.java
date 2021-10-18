package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * clase para generar numeros aleatorios
 * @author paco
 *
 */
public class RandomNumber {

	/**
	 * generador de numeros aleatorios
	 */
	private static Random generator = new Random(1L);
	
	/**
	 * lista de numeros generados (para debug)
	 */
	private static List<Integer> list = new ArrayList<Integer>();
	
	/**
	 * genera un numero aleatorio entre 0 y max-1
	 * @param max indica el maximo valor (no incluido)
	 * @return numero aleatorio entre 0 y max-1
	 */
	public static int newRandomNumber(int max) {
		int r = generator.nextInt(max);
		list.add(r);
		return r;
	}
	
	/**
	 * getter (debug)
	 * @return lista de numeros generados
	 */
	public static List<Integer> getRandomNumberList() {
		return list;
	}
	
	/**
	 * reinicializa el generador y la lista, para las pruebas unitarias
	 */
	public static void resetRandomCounter() {
        list.clear();
        generator = new Random(1L);
    } 
}