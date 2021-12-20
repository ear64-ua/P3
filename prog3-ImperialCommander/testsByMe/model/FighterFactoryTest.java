package testsByMe;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import model.Fighter;
import model.FighterFactory;
import model.Ship;
import model.Side;
import model.fighters.AWing;
import model.fighters.TIEBomber;
import model.fighters.TIEFighter;
import model.fighters.TIEInterceptor;
import model.fighters.XWing;
import model.fighters.YWing;

@SuppressWarnings("unused")
public class FighterFactoryTest {
	Fighter fighter;
	Ship ship;
	@Before
	public void setUp() throws Exception {
		ship = new Ship("Tydirium", Side.REBEL);
	}

	@Test
	public void testCreateFighterRebel() {
		Fighter fighterA=FighterFactory.createFighter("AWing", ship);
		Fighter fighterY=FighterFactory.createFighter("YWing", ship);
		Fighter fighterX=FighterFactory.createFighter("XWing", ship);
		Fighter fighterW=FighterFactory.createFighter("WWing", ship);
		Fighter fighterF=FighterFactory.createFighter("FWing", ship);

		assertNotNull(fighterA);
		assertNotNull(fighterY);
		assertNotNull(fighterX);
		assertNull(fighterW);
		assertNull(fighterF);		
	}
	
	@Test
	public void testCreateFighterImport() {
		Fighter fighterF=FighterFactory.createFighter("TIEFighter", ship);
		Fighter fighterB=FighterFactory.createFighter("TIEBomber", ship);
		Fighter fighterI=FighterFactory.createFighter("TIEInterceptor", ship);
		Fighter fighterO=FighterFactory.createFighter("TIEOne", ship);
		Fighter fighterT=FighterFactory.createFighter("TIETwo", ship);

		assertNotNull(fighterF);
		assertNotNull(fighterB);
		assertNotNull(fighterI);
		assertNull(fighterO);
		assertNull(fighterT);
	}
	
}