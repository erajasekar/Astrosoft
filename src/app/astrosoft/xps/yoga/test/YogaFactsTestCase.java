/**
 * YogaFactsTestCase.java
 Created On 2007, Nov 12, 2007 2:56:51 PM
 @author E. Rajasekar
 */

package app.astrosoft.xps.yoga.test;

import static org.junit.Assert.*;

import java.util.EnumSet;

import org.junit.Test;

import app.astrosoft.core.Horoscope;
import app.astrosoft.test.SampleHoroscopes;
import app.astrosoft.xps.beans.YogaFacts;
import app.astrosoft.consts.Planet;

public class YogaFactsTestCase {

	private static Horoscope raja = SampleHoroscopes.getMyHoroscope();
	
	private static Horoscope suba = SampleHoroscopes.getSubaHoroscope();
	
	@Test
	public void testPowerfulPlanet1(){
		
		YogaFacts facts = new YogaFacts();
		facts.setPowerfulPlanets(raja.getShadBala().getStrengthPer());
		
		//System.out.println("raja -> " + facts.powerfulPlanets());
		
		assertTrue(facts.powerfulPlanets().containsAll(EnumSet.range(Planet.Sun,Planet.Saturn)));
	}
	
	@Test
	public void testPowerfulPlanet3(){
		
		YogaFacts facts = new YogaFacts();
		facts.setPowerfulPlanets(suba.getShadBala().getStrengthPer());
		
		//System.out.println("suba -> " +facts.powerfulPlanets().contains(Planet.Mercury));
		
		assertFalse(facts.powerfulPlanets().contains(Planet.Mercury));
	}
	
	@Test
	public void testPowerfulPlanet5(){
		
		YogaFacts facts = new YogaFacts();
		facts.setPowerfulPlanets(suba.getShadBala().getStrengthPer());
		
		assertTrue(facts.isPlanetPowerful(Planet.Sun));
	}
	
	@Test
	public void testPowerfulPlanet2(){
		
		YogaFacts facts = new YogaFacts();
		facts.setPowerfulPlanets(suba.getShadBala().getStrengthPer());
		
		assertFalse(facts.isPlanetPowerful(Planet.Mercury));
	}
	
	@Test
	public void testPowerfulPlanet7(){
		
		YogaFacts facts = new YogaFacts();
		facts.setPowerfulPlanets(EnumSet.of(Planet.Sun,Planet.Moon,Planet.Mars));
		
		assertTrue(facts.isAnyPlanetPowerful(EnumSet.of(Planet.Sun,Planet.Saturn)));
	}
	
	@Test
	public void testPowerfulPlanet4(){
		
		YogaFacts facts = new YogaFacts();
		facts.setPowerfulPlanets(EnumSet.of(Planet.Sun,Planet.Moon,Planet.Mars));
		
		assertFalse(facts.isAnyPlanetPowerful(EnumSet.of(Planet.Mercury,Planet.Saturn)));
	}
}

