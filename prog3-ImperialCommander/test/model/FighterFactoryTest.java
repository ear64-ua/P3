package model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

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
	public void testCreateFighter() {
		fighter=FighterFactory.createFighter("FWing", ship);
		System.out.println(fighter);
		//assertTrue(fighter instanceof AWing);
	}
	
}