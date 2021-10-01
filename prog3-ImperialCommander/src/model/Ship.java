package model;

import java.util.ArrayList;
import java.util.List;

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

	/*
	 * updateResults(int r)
	 * getFighterAvailable(String type)
	 * purgeFleet()
	 * showFleet()
	 * myFleet()
	 * toString()
	 */
	
}
