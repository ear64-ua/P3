package model;

import java.util.Objects;

/**
 *  Representa cada caza que forma la flota
 *	@author Enrique Abma Romero X9853366M
 *	@version 1.8 2011
 * 
 **/

public abstract class Fighter {
	
	/**
	 * Caracteristicas del caza
	 */
	private int velocity, attack, shield;
	
	/**
	 * Numero de identificacion del caza
	 */
	private int id;
	
	/**
	 * Constante utilizada en el daño
	 * @see #getDamage
	 */
	private final int kDamage = 300;
	
	/**
	 * El id estatica de los cazas
	 */
	private static int nextId = 1;
	
	/**
	 * Nave nodriza del caza
	 */
	private Ship motherShip;
	
	/**
	 * Posicion del caza
	 */
	private Coordinate position; 
		
	/**
	 * Se crea un constructor de Fighter con valores predeterminados
	 * @param type se le asigna el tipo pasado al objeto que se crea
	 * @param mother es la nave nodriza a la que pertenece y se asigna
	 */
	protected Fighter(Ship mother){
	  
		this.velocity = 100;
		this.attack = 80;
		this.shield = 80;
		this.id = nextId;
		this.position = null;
		nextId++;
		
		
		motherShip = mother;
	  }
	 
	/**
	 * constructor de copia de Fighter
	 * @param f es el objeto a replicar
	 */
	protected Fighter(Fighter f) {
		
		this.velocity = f.velocity;
		this.attack = f.attack;
		this.shield = f.shield;
		this.position = f.position;
		this.id = f.id;
		this.motherShip = f.motherShip;
	}
	
	/**
	 * resetNextId() vuelve a poner a 1 su contador
	 */
	public static void resetNextId() {
		nextId = 1;
	}
	
	/**
	 * @return una copia del objeto Fighter
	 */
	
	public abstract Fighter copy();
		
	/**
	 * @return el tipo de caza
	 */
	public abstract char getSymbol();
		
	
	/**
	 * @return el simbolo del tipo de luchador
	 */
	public String getType() {
		return getClass().getSimpleName();
	}
	
	/**
	 * @return el id del luchador
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @return la velocidad que tiene
	 */
	public int getVelocity() {
		return this.velocity;
	}
	
	/**
	 * @return la durabilidad del escudo 
	 */
	public int getShield() {
		return this.shield;
	}
	
	/**
	 * @return la effectividad del ataque 
	 */
	public int getAttack() {
		return this.attack;
	}
	
	/**
	 * @return el lado al que pertenece
	 */
	public Side getSide() {
		return motherShip.getSide();
	}
	
	/**
	 * @return la posicion que ocupa
	 */
	public Coordinate getPosition() {
		
		return position;
	}
	
	/**
	 * setter de position
	 * @param c coordenada a la que se le va a asignar
	 */
	public void setPosition(Coordinate c) {
		this.position = c;
	}
	
	/**
	 * @return la nave nodriza a la que pertenece
	 */
	public Ship getMotherShip() {
		return motherShip;
	}
	
	/**
	 * addAttack agrega un valor, sea negativo o positivo al valor 
	 * que tiene ya ataque. Si el resultado es negativo, sera cero.
	 * @param at es el valor al que se le va a sumar al ataque
	 */
	public void addAttack(int at) {
		this.attack += at;
		
		if (this.attack < 0)
			this.attack = 0;
	}

	/**
	 * addVelocity agrega un valor, sea negativo o positivo al valor 
	 * que tiene ya la velocidad. Si este llega a ser negativo, sera cero.
	 * @param vel es el valor al que se le va a sumar a la velocidad
	 */
	public void addVelocity(int vel) {
		this.velocity += vel;
		
		if (this.velocity < 0)
			this.velocity = 0;
	}
	
	/**
	 * addShield agrega , sea negativo o positivo al valor 
	 * que tiene ya la velocidad.
	 * @param sh es el valor al que se le va a sumar al escudo
	 */
	public void addShield(int sh) {
		this.shield += sh;
	}
	
	/**
	 * isDestroyed comprueba si el escudo fue destruido
	 * @return true si el escudo es negativo o cero, y false en caso contrario
	 */
	public Boolean isDestroyed() {
		
		if(this.shield<=0)
			return true;
		else
			return false;
	}
	
	/**
	 * getDamage utiliza una formula que hace que se debilite el escudo del enemigo
	 * @param n es un numero aleatorio
	 * @param enemy lo usamos para conocer el ataque que tiene 
	 * @return el valor resultante de la formula
	 */
	public int getDamage(int n, Fighter enemy) {
		return (n*this.attack)/kDamage;
	}
	
	/**
	 * fight() simula la lucha entre dos luchadores
	 * @param enemy en el contrincante del objeto actual
	 * @return 1 si el objeto actual gana, -1 si ha perdido o 0 si ya hay uno destruido
	 * @see RandomNumber#newRandomNumber(int) newRandomNumber
	 */
	public int fight(Fighter enemy) {//revisar si la batalla es correcta con profe
		
		int n;
				
		if (enemy.isDestroyed() || this.isDestroyed())
			return 0;
		
  		else {
			do {
				n = RandomNumber.newRandomNumber(100);
				if (((this.velocity*100)/(this.velocity+enemy.velocity)) <= n) 
					enemy.addShield(- this.getDamage(n,enemy));
				
				else
					this.addShield(- enemy.getDamage(100-n,this));
				
			}while(!enemy.isDestroyed() && !this.isDestroyed());
			
			if (enemy.isDestroyed())
				return 1;
			else 
				return -1;
		}		
	}
	
	/**
	 * @return un string del formato "(tipo id lado [posicion] { vel att sh})"
	 */
	@Override
	public String toString() {
		return "(" + this.getType() + " " + this.id + " " + this.getSide() + " " + position 
				+ " {" + this.velocity + "," + this.attack + "," + this.shield + "})";
	}
	
	/**
	 * hashCode() nos permite localizar el objeto en un mapa
	 * @return un valor hash code del objeto
	 * @see Objects#hashCode() 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	/**
	 * Equals con atributo id
	 * @param obj es la referencia objeto el cual vamos a comparar
	 * @return TRUE: si este objeto es el mismo que el argumento obj                 
	 *         FALSE: en caso contrario
	 * @see Object#equals(Object) 
	 */
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
