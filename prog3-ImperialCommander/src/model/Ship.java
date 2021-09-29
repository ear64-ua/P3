package model;

import java.util.ArrayList;

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
	
	public Side getSide(){
		return side;
	}

}
