package model;

import java.util.Objects;

public class Fighter {
	
	private String type;
	
	private int velocity, attack, shield, id;
	
	private static int nextId = 1;
	
	private Coordinate position;
	
	// Fighter(String type, Ship mother)

	public Fighter(Fighter f) {
		
		f.id  = this.id;
		f.velocity  = this.velocity;
		f.attack  = this.attack;
		f.shield  = this.shield;
		f.type = this.type;
		f.position = this.position;
		
	}
	
	public static void resetNextId() {
		nextId = 1;
	}
	
	public void getType() {
		
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
	
	public int getSide() {
		return 0;
		//return this.side;
	}
	
	public Coordinate getPosition() {
		
		return new Coordinate(position);
	}
	
	//public Ship getShip() {
		
	//}
	
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
		
		//int n = new RandomNumber.randomNumber();
		
		
		if (enemy.shield <= 0 || this.shield <= 0)
			return 0;
		
		else {
			do {
				
			}while(!(enemy.shield <= 0 || this.shield <= 0));
		}
		
		return 0;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "(" + this.type + " " + this.id + " REBEL [" + position.getX() + ","  + 
				position.getY() + "{" + this.velocity + "," + this.attack + "," + this.shield + "})";
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
