/**
 * PlanetChartTestCase.java
 Created On 2007, Oct 17, 2007 2:37:02 PM
 @author E. Rajasekar
 */

package app.astrosoft.xps.yoga.test;

import static org.junit.Assert.*;

import java.util.EnumMap;
import java.util.EnumSet;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import app.astrosoft.beans.PlanetCharacter;
import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.Varga;
import app.astrosoft.core.Horoscope;
import app.astrosoft.test.SampleHoroscopes;
import app.astrosoft.xps.beans.PlanetChart;

public class PlanetChartTestCase {

	private static PlanetChart chart1;
	
	private static PlanetChart chart2;
	
	private static PlanetCharacter character1;
	
	@BeforeClass
	public static void setUpTest(){

		setUpChart1();
		setUpChart2();
	}
	
	private static void setUpChart2() {
		
		EnumMap<Planet, Integer> planetLoc = new EnumMap<Planet, Integer>(Planet.class);
		
		planetLoc.put(Planet.Sun, 1);
    	planetLoc.put(Planet.Moon, 4);
    	planetLoc.put(Planet.Mars, 4);
    	planetLoc.put(Planet.Mercury, 6);
    	planetLoc.put(Planet.Jupiter, 2);
    	planetLoc.put(Planet.Venus, 2);
    	planetLoc.put(Planet.Saturn, 2);
    	planetLoc.put(Planet.Rahu, 2);
    	planetLoc.put(Planet.Ketu, 2);
    	planetLoc.put(Planet.Ascendant, 1);
    	
    	EnumMap<Planet, Rasi> planetHouse = new EnumMap<Planet, Rasi>(Planet.class);
    	
    	planetHouse.put(Planet.Sun, Rasi.Mesha); //Exalted
    	planetHouse.put(Planet.Moon, Rasi.Kataka); // Own
    	planetHouse.put(Planet.Mars, Rasi.Kataka); // Debilitated
    	planetHouse.put(Planet.Mercury, Rasi.Kanya); //Moola trikona
    	planetHouse.put(Planet.Jupiter, Rasi.Vrishabha);
    	planetHouse.put(Planet.Venus, Rasi.Vrishabha);
    	planetHouse.put(Planet.Saturn, Rasi.Vrishabha);
    	planetHouse.put(Planet.Rahu, Rasi.Vrishabha);
    	planetHouse.put(Planet.Ketu, Rasi.Vrishabha);
    	planetHouse.put(Planet.Ascendant, Rasi.Mesha);
    	
    	chart2 = new PlanetChart(Varga.Rasi,planetLoc, planetHouse );
		
	}

	private static void setUpChart1() {
		Horoscope h = SampleHoroscopes.getMyHoroscope();
		chart1 = new PlanetChart(Varga.Rasi, h.getPlanetaryInfo());
		character1 = new PlanetCharacter(h.getPlanetaryInfo().getPlanetCharacter());
	}

	@Test
	public void testIsPlanetsAtLocation(){
		
		assertTrue("testIsPlanetsAtLocation() sucess", chart1.isPlanetsAt(EnumSet.of(Planet.Sun,Planet.Mercury),3));
	}
	
	@Test
	public void testIsPlanetsAtHouse1(){
		
		assertTrue("testIsPlanetsAtHouse() sucess", chart1.isPlanetsAt(EnumSet.of(Planet.Sun,Planet.Mercury),Rasi.Vrichika));
	}
	
	@Test
	public void testIsPlanetsAtHouse2(){
		
		assertFalse("testIsPlanetsAtLocation() sucess", chart1.isPlanetsAt(EnumSet.of(Planet.Sun,Planet.Mercury, Planet.Moon),Rasi.Mesha));
	}
	
	@Test
	public void testIsPlanetWithAll(){
		assertTrue(chart1.isPlanetWithAll(Planet.Ascendant, EnumSet.of(Planet.Saturn,Planet.Jupiter)));
	}
	
	@Test
	public void testIsPlanetWithAny(){
		assertTrue(chart1.isPlanetWithAny(Planet.Ascendant, EnumSet.of(Planet.Sun,Planet.Jupiter)));
	}
	
	@Test
	public void testIsPlanetExalted1() throws Exception {
		assertTrue(chart2.isPlanetExalted(Planet.Sun));
	}
	
	@Test
	public void testIsPlanetExalted2() throws Exception {
		assertFalse(chart2.isPlanetExalted(Planet.Jupiter));
	}
	
	@Test
	public void testIsPlanetDebilitated1() throws Exception {
		assertTrue(chart2.isPlanetDebilitated(Planet.Mars));
	}
	
	@Test
	public void testIsPlanetDebilitated2() throws Exception {
		assertFalse(chart2.isPlanetDebilitated(Planet.Sun));
	}
	
	@Test
	public void testIsPlanetInOwnHouse1() throws Exception {
		assertTrue(chart2.isPlanetInOwnHouse(Planet.Moon));
	}
	
	@Test
	public void testIsPlanetInOwnHouse2() throws Exception {
		assertFalse(chart2.isPlanetInOwnHouse(Planet.Jupiter));
	}
	
	@Test
	public void testIsPlanetInMoolaTrikona1() throws Exception {
		assertTrue(chart1.isPlanetInMoolaTrikona(Planet.Venus));
	}
	
	@Test
	public void testIsPlanetInKendra1() throws Exception {
		assertTrue(chart1.isPlanetInKendra(Planet.Mars));
	}
	
	@Test
	public void testIsPlanetInKendra2() throws Exception {
		assertFalse(chart1.isPlanetInKendra(Planet.Sun));
	}
	
	@Test
	public void testIsPlanetInTrine1() throws Exception {
		assertTrue(chart1.isPlanetInTrine(Planet.Moon));
	}
	
	@Test
	public void testIsPlanetInTrine2() throws Exception {
		assertFalse(chart1.isPlanetInTrine(Planet.Mars));
	}
	
	@Test
	public void testIsPlanetInKendraTo1() throws Exception {
		assertTrue(chart1.isPlanetInKendraTo(Planet.Moon, Planet.Venus));
	}
	
	@Test
	public void testIsPlanetInKendraTo2() throws Exception {
		assertFalse(chart1.isPlanetInKendraTo(Planet.Mars, Planet.Venus));
	}
	
	@Test
	public void testIsPlanetInTrineTo1() throws Exception {
		assertTrue(chart1.isPlanetInTrineTo(Planet.Jupiter, Planet.Moon));
	}
	
	@Test
	public void testIsPlanetAspectedBy1() throws Exception {
		assertTrue(chart1.isPlanetAspectedBy(Planet.Mercury, Planet.Saturn));
	}
	
	@Test
	public void testIsPlanetAspectedBy2() throws Exception {
		assertFalse(chart1.isPlanetAspectedBy(Planet.Saturn, Planet.Mercury));
	}
	
	@Test
	public void testIsPlanetAspectedBy3() throws Exception {
		assertFalse(chart1.isPlanetAspectedBy(Planet.Mars, Planet.Jupiter));
	}
	
	@Test
	public void testIsPlanetAspectedBy4() throws Exception {
		assertTrue(chart1.isPlanetAspectedBy(Planet.Rahu, Planet.Ketu));
	}
	
	@Test
	public void testIsPlanetAspectedBy5() throws Exception {
		assertTrue(chart1.isPlanetAspectedBy(Planet.Ketu, Planet.Rahu));
	}
	
	@Test
	public void testIsPlanetAspectedBy6() throws Exception {
		assertFalse(chart1.isPlanetAspectedBy(Planet.Jupiter, Planet.Saturn));
	}
	
	@Test
	public void testIsPlanetAspectedBy7() throws Exception {
		assertTrue(chart1.isPlanetAspectedBy(Planet.Rahu, Planet.Moon));
	}
	
	@Test
	public void testIsHouseAspectedBy1() throws Exception {
		assertTrue(chart1.isHouseAspectedBy(Rasi.Mithuna,Planet.Saturn));
	}
	
	@Test
	public void testIsHouseAspectedBy2() throws Exception {
		assertTrue(chart1.isHouseAspectedBy(Rasi.Vrishabha,Planet.Sun));
	}
	
	@Test
	public void testIsHouseAspectedBy3() throws Exception {
		assertFalse(chart1.isHouseAspectedBy(Rasi.Vrishabha,Planet.Moon));
	}
	
	@Test
	public void testIsHouseAspectedBy4() throws Exception {
		assertTrue(chart1.isHouseAspectedBy(Rasi.Kataka,Planet.Mars));
	}
	
	@Test
	public void testIsHouseAspectedBy5() throws Exception {
		assertFalse(chart1.isHouseAspectedBy(Rasi.Vrichika,Planet.Mercury));
	}
	
	@Test
	public void testIsHouseAspectedBy6() throws Exception {
		assertTrue(chart1.isHouseAspectedBy(Rasi.Mesha,Planet.Venus));
	}
	
	@Test
	public void testIsHouseAspectedBy7() throws Exception {
		assertTrue(chart1.isHouseAspectedBy(Rasi.Makara,Planet.Jupiter));
	}
	
	@Test
	public void testIsHouseAspectedBy8() throws Exception {
		assertTrue(chart1.isHouseAspectedBy(Rasi.Kataka,Planet.Ketu));
	}
	
	@Test
	public void testIsHouseAspectedBy9() throws Exception {
		assertTrue(chart1.isHouseAspectedBy(Rasi.Makara,Planet.Rahu));
	}
	
	@Test
	public void testPlanetsAt1(){
		assertTrue(chart1.planetsAt(Planet.Moon, 1).containsAll(EnumSet.of(Planet.Moon, Planet.Ketu)));
	}
	
	@Test
	public void testPlanetsAt2(){
		assertTrue(chart1.planetsAt(Planet.Saturn, 5).containsAll(EnumSet.of(Planet.Moon, Planet.Ketu)));
	}
	
	@Test
	public void testPlanetsAt3(){
		assertTrue(chart1.planetsAt(Planet.Mars, 12).containsAll(EnumSet.of(Planet.Sun, Planet.Mercury)));
	}
	
	@Test
	public void testPlanetsAt4(){
		assertTrue(chart1.planetsAt(Planet.Sun, 7).isEmpty());
	}
	
	@Test
	public void testIsAllBenefic(){
		assertTrue(character1.isAllBenefics(EnumSet.of(Planet.Moon, Planet.Jupiter,Planet.Venus,Planet.Mercury)));
	}
	
	@Test
	public void testIsAllBenefic2(){
		assertFalse(character1.isAllBenefics(EnumSet.of(Planet.Moon, Planet.Saturn,Planet.Venus,Planet.Mercury)));
	}
	
	@Test
	public void testIsAnyBenefic1(){
		assertTrue(character1.isAnyBenefics(EnumSet.of(Planet.Moon, Planet.Sun,Planet.Rahu,Planet.Ketu)));
	}
	
	@Test
	public void testIsAnyBenefic2(){
		assertFalse(character1.isAnyBenefics(EnumSet.of(Planet.Mars, Planet.Sun,Planet.Rahu,Planet.Ketu)));
	}
	
	@Test
	public void testIsAllMalefic1(){
		assertTrue(character1.isAllMalefics(EnumSet.of(Planet.Mars, Planet.Sun,Planet.Rahu,Planet.Ketu)));
	}
	
	@Test
	public void testIsAllMalefic2(){
		assertFalse(character1.isAllMalefics(EnumSet.of(Planet.Jupiter, Planet.Sun,Planet.Rahu,Planet.Ketu)));
	}
	
	@Test
	public void testIsAnyMalefic1(){
		assertTrue(character1.isAnyMalefics(EnumSet.of(Planet.Mars, Planet.Jupiter,Planet.Rahu,Planet.Ketu)));
	}
	
	@Test
	public void testIsAnyMalefic2(){
		assertFalse(character1.isAnyMalefics(EnumSet.of(Planet.Moon, Planet.Jupiter,Planet.Venus,Planet.Mercury)));
	}
	
	@Test
	public void testLordOf1(){
		assertEquals(chart1.lordOf(1), Planet.Mercury);
	}
	
	@Test
	public void testLordOf2(){
		assertEquals(chart1.lordOf(2), Planet.Venus);
	}
	
	@Test
	public void testLordOf3(){
		assertEquals(chart1.lordOf(3), Planet.Mars);
	}
	
	@Test
	public void testLordOf4(){
		assertEquals(chart1.lordOf(12), Planet.Sun);
	}
}
