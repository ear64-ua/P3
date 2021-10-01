package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

public class Ship {
	
	private String name;
	private int wins;
	private int losses;
	
	private ArrayList<Fighter> fleet;
	
	private Side side;
	
	public Ship(String name, Side side) {
		this.wins = 0;
		this.losses = 0;
		this.name = name;
		this.side = side;
		fleet =new ArrayList<Fighter>();
	}
	
	public Side getSide() {
		return side;
	}

	public String getName() {
		return name;
	}

	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}
	
	public List<Fighter>  getFleetTest() {
		return fleet;
	}
	
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

	public void updateResults(int r) {
		
		if(r == 1)
			this.wins++;
		
		else if(r == -1)
			this.losses++;
	}
	
	
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
	
	public void purgeFleet() {
		for(int i = 0; i < fleet.size(); i++) {
			if(fleet.get(i).isDestroyed())
				fleet.remove(i);
			
		}
	}
	
	
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
	 * únicos y se ordenan de la forma en la que se añaden sus elementos.
	 * Usamos un contador de iteracción y de duplicados para lograr que nuestra
	 * función funcione correctamente
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
	
	public String toString() {
		return super.toString() + " [" + this.name + " " + this.wins + "/" 
				+ this.losses + "] " + this.myFleet();
	}
	
}
