package model.game;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.RandomNumber;
import model.Ship;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.InvalidSizeException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

public class GameBoardPreTest {

	GameBoard gameBoard;
	GameShip gameShip;
	final String kEXAMPLEBOARD = "  0123456789\n" + 
								 "  ----------\n" + 
								 "0|   A    AX\n" + 
								 "1|          \n" + 
								 "2|    X     \n" + 
								 "3|       A  \n" + 
								 "4|          \n" + 
								 "5|   A      \n" + 
								 "6| i Y      \n" + 
								 "7|    Y b X \n" + 
								 "8|          \n" + 
								 "9|          ";
	final String kEMPTYBOARD = "   0123456789\n" + 
							   "   ----------\n" + 
							   " 0|          \n" + 
							   " 1|          \n" + 
							   " 2|          \n" + 
							   " 3|          \n" + 
							   " 4|          \n" + 
							   " 5|          \n" + 
							   " 6|          \n" + 
							   " 7|          \n" + 
							   " 8|          \n" + 
							   " 9|          ";

								  
	@Before
	public void setUp() throws Exception {
		gameBoard = new GameBoard(10);
		gameShip = new GameShip("Tydirium", Side.REBEL);
		Fighter.resetNextId();
	}

	/* Se comprueba que el constructor copia de GameBoard es correcto y que
	   GameBoard es una clase derivada de Board.
	 */
	@Test
	public void testGameBoard() {
		assertEquals (10, gameBoard.getSize());
		assertTrue(gameBoard instanceof Board );
	}

	/* Se comprueba que en un tablero vacío el número de Fighters de ambos bandos
	 * es 0
	 */
	@Test
	public void testNumFightersInEmptyBoard() {
		gameShip.addFighters("10/AWing:35/XWing:5/YWing");
		assertEquals(0,gameBoard.numFighters(Side.IMPERIAL));
		assertEquals(0,gameBoard.numFighters(Side.REBEL));
	}

	/* Se añaden a un GameShip varios cazas rebeldes. Todos ellos se posicionan sobre un 
	 * tablero. Se comprueba que numFighters para los rebeldes coincide con el número de
	 * cazas del GameShip.
	 */
	@Test
	public void testNumFighters1() throws FighterAlreadyInBoardException, OutOfBoundsException {
		gameShip.addFighters("7/AWing:6/XWing:2/YWing");
		int i=0;
		int j=0;
		for (Fighter f : gameShip.getFleetTest()) {
			if (i==gameBoard.getSize()) {
				i=0; j+=3;
			}
			gameBoard.launch(new Coordinate(i,j), f);
			i++;
			
		}
		assertEquals(15,gameBoard.numFighters(Side.REBEL));
	}
	
	/* Se añaden cazas rebeldes a un GameShip rebelde y cazas imperiales a un GameShip imperial.
	 * Se posiciona una parte de los cazas rebeldes e imperiales en un tablero. 
	 * Se comprueba que el numFighers para los cazas rebeldes e imperiales coinciden respectivamente
	 * con los que están en el tablero.
	 */
	//TODO
	@Test
	public void testNumFighters2() throws FighterAlreadyInBoardException, OutOfBoundsException {
		gameShip.addFighters("7/AWing:6/XWing:2/YWing");
		GameShip gameImperialShip = new GameShip("Lanzadera T-4a", Side.IMPERIAL);
		gameImperialShip.addFighters("3/TIEBomber:9/TIEInterceptor:2/TIEFighter");
		fail("Termina el test");
	}
	
	/* Se prueba toString para un tablero de 15x15 vacío
	 * 
	 */
	@Test
	public void testToStringAnEmptyBoard()  {
		
		compareLines(kEMPTYBOARD, gameBoard.toString());
	}
	
	/* Crea dos GameShip: uno imperial y otro rebelde. Añade los cazas suficientes en
	 * cada uno de ellos para luego ponerlos en el tablero como en el ejemplo del enunciado.
	 * Se añaden los cazas del ejemplo del enunciado en un tablero 10x10. Se comprueba que la salida
	 * es correcta: gameBoard.toString() debe coincidir con la constante String kEXAMPLEBOARD.
	 * Para compararlos usa el método auxiliar compareLines del final.
	 * compareLines(kEXAMPLEBOARD, gameBoard.toString());
	 */
	//TODO
	@Test
	public void testToStringExample() throws FighterAlreadyInBoardException, OutOfBoundsException, InvalidSizeException {
		
		//launches a mano
		String inputImp = "1/TIEInterceptor:1/TIEBomber\nlaunch 1 6\nlaunch 6 7\nexit\n";
		Reader stringReaderImp = new StringReader(inputImp);
		BufferedReader brI = new BufferedReader(stringReaderImp);
		PlayerFile plimperial = new PlayerFile(Side.IMPERIAL, brI);
		
		String inputReb = "4/AWing:3/XWing:2/YWing\nlaunch 3 0\nlaunch 8 0\nlaunch 7 3\nlaunch 3 5\n"
						+ "launch 4 2\nlaunch 9 0\nlaunch 8 7\n"
						+ "launch 3 6\nlaunch 3 7\nexit\n";
		Reader stringReaderReb = new StringReader(inputReb);
		BufferedReader brR = new BufferedReader(stringReaderReb);
		PlayerFile plrebel = new PlayerFile(Side.REBEL, brR);
		
		plrebel.initFighters();
		plimperial.initFighters();
		plrebel.setBoard(gameBoard);
		plimperial.setBoard(gameBoard);
		
		try {
			plrebel.getGameShip().launch(1, new Coordinate(3,0), gameBoard);
			plrebel.getGameShip().launch(2, new Coordinate(8,0), gameBoard);
			plrebel.getGameShip().launch(3, new Coordinate(7,3), gameBoard);
			plrebel.getGameShip().launch(4, new Coordinate(3,5), gameBoard);
			
			plrebel.getGameShip().launch(5, new Coordinate(4,2), gameBoard);
			plrebel.getGameShip().launch(6, new Coordinate(9,0), gameBoard);
			plrebel.getGameShip().launch(7, new Coordinate(8,7), gameBoard);
			
			plrebel.getGameShip().launch(8, new Coordinate(3,6), gameBoard);
			plrebel.getGameShip().launch(9, new Coordinate(4,7), gameBoard);
			
		} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException e) { }
		
		try {
			plimperial.getGameShip().launch(10, new Coordinate(1,6), gameBoard);
			plimperial.getGameShip().launch(11, new Coordinate(6,7), gameBoard);
			
		} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException e) { }
		System.out.println(gameBoard.toString());
		compareLines(kEXAMPLEBOARD, gameBoard.toString());
		
		
		
		
	}

	/*************************
	 * FUNCIONES AUXILIARES
	 * 
	 *************************/
	//Compara dos String línea a línea
	private void  compareLines(String expected, String result) {
		String exp[]=expected.split("\n");
		String res[]=result.split("\n");
		boolean iguales = true;
		if (exp.length!=res.length) 
			fail("Cadena esperada de tamaño ("+exp.length+") distinto a la resultante ("+res.length+")");
		for (int i=0; i<exp.length && iguales; i++) {
				 assertEquals("linea "+i, exp[i].trim(),res[i].trim());
		}
	}
}
