package model;

import java.util.Objects;

public class Fighter {
	
	private String type;
	
	private int velocity, attack, shield, id;
	
	private static int nextId = 1;
	
	private Ship motherShip;
	
	private Coordinate position = new Coordinate(null);
	
	Fighter(String type, Ship mother){
	  
		this.velocity = 100;
		this.attack = 80;
		this.shield = 80;
		type = this.type;
		this.id = nextId;
		nextId++;
	  
		motherShip = mother;
	  }
	 

	public Fighter(Fighter f) {
		
		this.velocity = f.velocity;
		this.attack = f.attack;
		this.shield = f.shield;
		this.type = f.type;
		this.position = f.position;
		this.id = f.id;
		this.motherShip = f.motherShip;
	}
	
	public static void resetNextId() {
		nextId = 1;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getVelocity() {
		return this.velocity;
	}
	
	public int getShield() {
		return this.shield;
	}
	
	public Side getSide() {
		return motherShip.getSide();
	}
	
	public Coordinate getPosition() {
		
		return new Coordinate(position);
	}
	
	public Ship getMotherShip() {
		return motherShip;
	}
	
	public void addAtack(int at) {
		this.attack =+ at;
		
		if (this.attack < 0)
			this.attack = 0;
	}

	public void addVelocity(int vel) {
		this.velocity =+ vel;
		
		if (this.velocity < 0)
			this.velocity = 0;
	}
	
	public void addShield(int sh) {
		this.shield = sh;
	}
	
	public Boolean isDestroyed() {
		
		if(this.shield<=0)
			return true;
		else
			return false;
	}
	
	public int getDamage(int n, Fighter enemy) {
		return (n*enemy.attack)/300;
		
	}
	
	public int fight(Fighter enemy) {
		
		int n;
		
		if (enemy.shield <= 0 || this.shield <= 0)
			return 0;
		
		else {
			do {
				
				n = RandomNumber.newRandomNumber(99);
				
				if ((this.velocity*100)/(this.velocity+enemy.velocity) <= n)
					enemy.shield = enemy.shield - getDamage(n,enemy);
				else
					this.shield = this.shield - enemy.getDamage(100-n,enemy);
				
			}while(enemy.isDestroyed() && this.isDestroyed());
			
			if (enemy.shield <= 0)
				return 1;
			else 
				return -1;
		}		
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "(" + this.type + " " + this.id + this.getSide() + position 
				+ "{" + this.velocity + "," + this.attack + "," + this.shield + "})";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fighter other = (Fighter) obj;
		return id == other.id;
	}


	
	
}
