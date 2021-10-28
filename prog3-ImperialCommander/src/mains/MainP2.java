package mains;


import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Ship;
import model.Side;

public class MainP2 {

	public static void main(String[] args) {

		Board b = new Board(10);  // 10x10

		System.out.println("-----------------------------");
		
		Ship imperialCommander = new Ship("Imperial Commander 1",Side.IMPERIAL);
		Ship rebel = new Ship("Corellian Cruiser",Side.REBEL);
	
		imperialCommander.addFighters("8/TIEFighter:8/TIEBomber:5/TIEInterceptor:2/TIEFighter");
		rebel.addFighters("10/XWing:7/YWing:4/AWing:1/YWing");
	
		Fighter fi = imperialCommander.getFirstAvailableFighter("TIEFighter");
		System.out.println(fi);
		
		
		b.launch(new Coordinate(1,1), fi);

		Fighter fr = rebel.getFirstAvailableFighter("");
		b.launch(new Coordinate(0,1), fr);
		
		System.out.println("-----------------------------");

		System.out.println(imperialCommander+"\n"+imperialCommander.showFleet());
		
		System.out.println(rebel+"\n"+rebel.showFleet());
		
		
		
		System.out.println("Patrol "+fr);
		b.patrol(fr);
		
		System.out.println(imperialCommander+"\n"+imperialCommander.showFleet());
		System.out.println(rebel+"\n"+rebel.showFleet());
		
		System.out.println(imperialCommander.getFleetTest().size());
		rebel.purgeFleet();
		System.out.println(imperialCommander+"\n"+imperialCommander.showFleet());

		System.out.println(imperialCommander.getFleetTest().size());
		
		
		 
	}
	
}