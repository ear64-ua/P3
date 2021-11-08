package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.fighters.AWing;
import model.fighters.TIEBomber;
import model.fighters.*;

public class FightersPreTest {

	Ship rebelShip, imperialShip;
	Board board;
	
	@Before
	public void setUp() throws Exception {
		rebelShip = new Ship("Tydirium", Side.REBEL);
		imperialShip = new Ship("Lanzadera T-4a", Side.IMPERIAL);
		board = new Board(5);
	}

	@Test
	public void testCreateFighter() {
		Fighter rebel = FighterFactory.createFighter("AWing", rebelShip);
		assertTrue(rebel instanceof Fighter);
		assertTrue(rebel instanceof AWing);
		
		Fighter imperial = FighterFactory.createFighter("TIEBomber", imperialShip);
		assertTrue(imperial instanceof Fighter);
		assertTrue(imperial instanceof TIEBomber);	
	}
	
	@Test
	public void testCopyRebel() {
		Fighter fx = FighterFactory.createFighter("XWing", rebelShip);
		Fighter fy = FighterFactory.createFighter("YWing", rebelShip);
		Fighter fa = FighterFactory.createFighter("AWing", rebelShip);
		
		assertEquals("copy XWing",fx,fx.copy());
		assertEquals("copy YWing",fy,fy.copy());
		assertEquals("copy AWing",fa,fa.copy());
		
		XWing fxx = (XWing)fx;
		XWing fxcopy = (XWing) fx.copy();
		assertEquals("copy XWing as XWing",fxx,fxcopy);
		
		YWing fyy = (YWing)fy;
		YWing fycopy = (YWing) fy.copy();
		assertEquals("copy XWing as XWing",fyy,fycopy);
		
		AWing faa = (AWing)fa;
		AWing facopy = (AWing) fa.copy();
		assertEquals("copy XWing as XWing",faa,facopy);
		
	}
	
	@Test
	public void testCopyImperial() {
		Fighter ff = FighterFactory.createFighter("TIEFighter", rebelShip);
		Fighter fb = FighterFactory.createFighter("TIEBomber", rebelShip);
		Fighter fi = FighterFactory.createFighter("TIEInterceptor", rebelShip);
		
		assertEquals("copy TIEFighter",ff,ff.copy());
		assertEquals("copy TIEBomber",fb,fb.copy());
		assertEquals("copy TIEInterceptor",fi,fi.copy());
		
		TIEFighter fxx = (TIEFighter)ff;
		TIEFighter fxcopy = (TIEFighter) ff.copy();
		assertEquals("copy XWing as XWing",fxx,fxcopy);
		
		TIEBomber fbb = (TIEBomber)fb;
		TIEBomber fbcopy = (TIEBomber) fb.copy();
		assertEquals("copy XWing as XWing",fbb,fbcopy);
		
		TIEInterceptor fii = (TIEInterceptor)fi;
		TIEInterceptor ficopy = (TIEInterceptor) fi.copy();
		assertEquals("copy XWing as XWing",fii,ficopy);
	}
	
	@Test
	public void testTIEInterceptor() {
		Fighter ti = FighterFactory.createFighter("TIEInterceptor", imperialShip);
		
		assertEquals("velocity",145,ti.getVelocity());
		assertEquals("attack",85,ti.getAttack());
		assertEquals("shield",60,ti.getShield());
		assertEquals("symbol",'i',ti.getSymbol());
		
		Fighter fx = FighterFactory.createFighter("XWing", rebelShip);
		Fighter fy = FighterFactory.createFighter("YWing", rebelShip);
		Fighter fa = FighterFactory.createFighter("AWing", rebelShip);
		
		assertEquals("getDamage(50,XWing)",14,ti.getDamage(50, fx));
		assertEquals("getDamage(50,YWing)",28,ti.getDamage(50, fy));
		assertEquals("getDamage(50,AWing)",7,ti.getDamage(50, fa));
		
	}
	
	@Test
	public void testTIEFighter() {
		Fighter tf = FighterFactory.createFighter("TIEFighter", imperialShip);
		
		assertEquals("velocity",110,tf.getVelocity());
		assertEquals("attack",85,tf.getAttack());
		assertEquals("shield",70,tf.getShield());
		assertEquals("symbol",'f',tf.getSymbol());
		
		
	}
	
	@Test
	public void testTIEBomber() {
		Fighter tb = FighterFactory.createFighter("TIEBomber", imperialShip);
		
		assertEquals("velocity",70,tb.getVelocity());
		assertEquals("attack",30,tb.getAttack());
		assertEquals("shield",115,tb.getShield());
		assertEquals("symbol",'b',tb.getSymbol());
		
		Fighter fx = FighterFactory.createFighter("XWing", rebelShip);
		Fighter fy = FighterFactory.createFighter("YWing", rebelShip);
		Fighter fa = FighterFactory.createFighter("AWing", rebelShip);
		
		assertEquals("getDamage(50,XWing)",2,tb.getDamage(50, fx));
		assertEquals("getDamage(50,YWing)",2,tb.getDamage(50, fy));
		assertEquals("getDamage(50,AWing)",1,tb.getDamage(50, fa));
		
		
	}
	
	@Test
	public void AWing() {
		Fighter ta = FighterFactory.createFighter("AWing", rebelShip);
		
		assertEquals("velocity",140,ta.getVelocity());
		assertEquals("attack",85,ta.getAttack());
		assertEquals("shield",30,ta.getShield());
		assertEquals("symbol",'A',ta.getSymbol());
		
		Fighter fb = FighterFactory.createFighter("TIEBomber", rebelShip);
		Fighter fi = FighterFactory.createFighter("TIEInterceptor", rebelShip);
		
		assertEquals("getDamage(50,TIEBomber)",28,ta.getDamage(50, fb));
		assertEquals("getDamage(50,TIEInterceptor)",14,ta.getDamage(50, fi));
	}
	
	@Test
	public void XWing() {
		Fighter tx = FighterFactory.createFighter("XWing", rebelShip);
		
		assertEquals("velocity",110,tx.getVelocity());
		assertEquals("attack",100,tx.getAttack());
		assertEquals("shield",80,tx.getShield());
		assertEquals("symbol",'X',tx.getSymbol());
	}
	
	@Test
	public void YWing() {
		Fighter ty = FighterFactory.createFighter("YWing", rebelShip);
		
		assertEquals("velocity",80,ty.getVelocity());
		assertEquals("attack",70,ty.getAttack());
		assertEquals("shield",110,ty.getShield());
		assertEquals("symbol",'Y',ty.getSymbol());
		
		Fighter ti = FighterFactory.createFighter("TIEInterceptor", imperialShip);
		Fighter tf = FighterFactory.createFighter("TIEFighter", imperialShip);
		Fighter tb = FighterFactory.createFighter("TIEBomber", imperialShip);
		
		assertEquals("getDamage(50,TIEInterceptor)",3,ty.getDamage(50, ti));
		assertEquals("getDamage(50,TIEFighter)",3,ty.getDamage(50, tf));
		assertEquals("getDamage(50,TIEBomber)",5,ty.getDamage(50, tb));
		
		// añade métodos con tests similares para los demás tipos de cazas
	}
}
