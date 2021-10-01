package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 * 
 **/

public class Ship {
	
	private String name;
	private int wins;
	private int losses;
	
	private ArrayList<Fighter> fleet;
	
	private Side side;
	
	/**
	 * Constructor de la clase Ship en el que pone a cero las estadisticas
	 * y crea una lista de Luchadores(fleet) que inicialmente esta vacia
	 * @param name es el nombre asignado al objeto
	 * @param side es el lado al que se le asigna al objeto
	 */
	public Ship(String name, Side side) {
		this.wins = 0;
		this.losses = 0;
		this.name = name;
		this.side = side;
		fleet =new ArrayList<Fighter>();
	}
	
	/**
	 * @return el lado por el que pertenece la nave
	 */
	public Side getSide() {
		return side;
	}

	/**
	 * @return el nombre de la nave
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return victorias de la nave
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * @return derrotas de la nave
	 */
	public int getLosses() {
		return losses;
	}
	
	/**
	 * @return la flota por la que esta formada la nave
	 */
	public List<Fighter>  getFleetTest() {
		return fleet;
	}
	
	
	/**
	 * addFighters se encarga de separar el string leido y contenerlo 
	 * en sus respectivos campos
	 * @param fd  esta formado por un string del formato "duplicados/tipo:*"
	 */
	public void addFighters(String fd) {
		
		if (fd.contains(":")) { 
			String saux1[] = fd.split(":");
			
			for (int i = 0; i < saux1.length; i++ ) {
				
				String saux2[] = saux1[i].split("/");
				for(int j = 0; j < Integer.parseInt(saux2[0]); j++) {
					Fighter f1 = new Fighter(saux2[1],this);
					fleet.add(f1);
				}
			}
		}
			
		else {
			String saux1[] = fd.split("/");
			
			for (int i = 0; i < Integer.parseInt(saux1[0]); i++) {
				Fighter f1 = new Fighter(saux1[1],this);
				fleet.add(f1);
			}
		}
	}
	
	/**
	 * actualiza las derrotas o victorias de cada nave
	 * @param r	si obtiene el valor de 1, se suma uno a las victorias,
	 * 		  	y lo equivalente a las derrotas si le llega -1
	 */
	public void updateResults(int r) {
		
		if(r == 1)
			this.wins++;
		
		else if(r == -1)
			this.losses++;
	}
	
	/**
	 * getFighterAvailable realiza una busqueda de una nave 
	 * no destruida
	 * @param type	va a ser el tipo objetivo nuestra busqueda
	 * @return {@code .null} si el parametro esta vacio, o 
	 * 		   la flota que coincide
	 */
	public Fighter getFighterAvailable(String type) {
		
		for(int i = 0; i < fleet.size(); i++) {
			if(fleet.get(i).getType().equals(type) && !fleet.get(i).isDestroyed())
				return fleet.get(i);
			else if(type.equals(""))
				return fleet.get(i);
		}
		
		//for(Fighter f : fleet)
		// if(f.getType().equals(type) && !f.isDestroyed())
		//return f;
		//else if(type.equals(""))
		//return f;
		
		return null;
	}
	
	
	/**
	 * recorre el la flota y elimina de ella, las naves destruidas
	 */
	public void purgeFleet() {
		for(int i = 0; i < fleet.size(); i++) {
			if(fleet.get(i).isDestroyed())
				fleet.remove(i);	
		}
	}
	
	
	/**
	 * @return un string con la informacion de cada luchador y si esta destruido o no
	 */
	public String showFleet() {
		
		String s = "";
		
		for(Fighter f : fleet) {
			s = s + f;
			if(f.isDestroyed())
				s = s + " (X)\n";
		}
		
		return s;
	}
	
	
	/**
	 * myFleet usa la clase LinkedHashSet, que crea un mapa con valores 
	 * unicos y se ordenan de la forma en la que se agregan sus elementos.
	 * Usamos un contador de iteraccion y de duplicados para lograr que nuestra
	 * funcion funcione correctamente
	 * @return un String con el formato "duplicados/tipo:*"
	 */
	public String myFleet() {
		
		Set<String> mapa = new LinkedHashSet<>();
		String s = "";
		int duplicates;
		int iteration = 0;
		
		for(Fighter f: fleet) {
			mapa.add(f.getType());
		}
		
		for(String m: mapa) {
			duplicates = 0;
			iteration++;
			for(Fighter f: fleet) {
				if(f.getType().equals(m) && !f.isDestroyed())
					duplicates++;		
			}
			s = s + duplicates + "/" + m;
			if (mapa.size() != iteration)
				s = s + ":";
		}
		
		return s;
	}
	
	/**
	 * @return un string del formato  Ship [nombre victorias/derrotas miFlota]
	 */
	public String toString() {
		return super.toString() + " [" + this.name + " " + this.wins + "/" 
				+ this.losses + "] " + this.myFleet();
	}
	
}
