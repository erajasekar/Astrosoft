/**
 * PanchamahapurushaYogaTestCase.java
 Created On 2007, Nov 2, 2007 5:50:49 PM
 @author E. Rajasekar
 */

package app.astrosoft.xps.yoga.test;

import static app.astrosoft.util.CollectionUtil.newEntry;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.constructChart;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.getLogger;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.getSession;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.loadFacts;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.setUp;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.testYogaNotPresent;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.testYogaPresent;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.YogaCombination;
import app.astrosoft.xps.beans.PlanetChart;

@SuppressWarnings("unchecked")
public class PanchamahapurushaYogaTestCase extends YogaAnalysisTestCase {

	@Test
	public void testHamsaYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Jupiter, Rasi.Kataka)
				);
	
		setUp("Hamsa Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.HamsaYoga,"100%");
	}
	
	@Test
	public void testHamsaYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Dhanus),
				newEntry(Planet.Jupiter, Rasi.Meena)
				);
	
		setUp("Hamsa Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.HamsaYoga,"100%");
	}
	
	@Test
	public void testHamsaYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Jupiter, Rasi.Meena)
				);
	
		setUp("Hamsa Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.HamsaYoga);
	}
	
	@Test
	public void testHamsaYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Meena),
				newEntry(Planet.Jupiter, Rasi.Kataka)
				);
	
		setUp("Hamsa Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.HamsaYoga);
	}
	
	@Test
	public void testMalavyaYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Venus, Rasi.Thula)
				);
	
		setUp("Malavya Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.MalavyaYoga,"100%");
	}
	
	@Test
	public void testMalavyaYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Dhanus),
				newEntry(Planet.Venus, Rasi.Meena)
				);
	
		setUp("Malavya Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.MalavyaYoga,"100%");
	}
	
	@Test
	public void testMalavyaYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Venus, Rasi.Meena)
				);
	
		setUp("Malavya Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.MalavyaYoga);
	}
	
	@Test
	public void testMalavyaYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Venus, Rasi.Kataka)
				);
	
		setUp("Malavya Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.MalavyaYoga);
	}
	
	@Test
	public void testSasaYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Saturn, Rasi.Thula)
				);
	
		setUp("Sasa Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.SasaYoga,"100%");
	}
	
	@Test
	public void testSasaYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Saturn, Rasi.Makara)
				);
	
		setUp("Sasa Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.SasaYoga,"100%");
	}
	
	@Test
	public void testSasaYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Vrishabha),
				newEntry(Planet.Saturn, Rasi.Makara)
				);
	
		setUp("Sasa Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.SasaYoga);
	}
	
	@Test
	public void testSasaYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Vrishabha),
				newEntry(Planet.Saturn, Rasi.Thula)
				);
	
		setUp("Sasa Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.SasaYoga);
	}
	
	@Test
	public void testRuchakaYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mars, Rasi.Mesha)
				);
	
		setUp("Ruchaka Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.RuchakaYoga);
	}
	
	@Test
	public void testRuchakaYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mars, Rasi.Makara)
				);
	
		setUp("Ruchaka Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.RuchakaYoga);
	}
	
	@Test
	public void testRuchakaYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Meena),
				newEntry(Planet.Mars, Rasi.Makara)
				);
	
		setUp("Ruchaka Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.RuchakaYoga);
	}
	
	@Test
	public void testRuchakaYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Meena),
				newEntry(Planet.Mars, Rasi.Mithuna)
				);
	
		setUp("Ruchaka Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.RuchakaYoga);
	}
	
	@Test
	public void testBhadraYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mithuna),
				newEntry(Planet.Mercury, Rasi.Kanya)
				);
	
		setUp("Bhadra Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.BhadraYoga);
	}
	
	@Test
	public void testBhadraYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mithuna),
				newEntry(Planet.Mercury, Rasi.Mithuna)
				);
	
		setUp("Bhadra Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.BhadraYoga);
	}
	
	@Test
	public void testBhadraYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Vrichika),
				newEntry(Planet.Mercury, Rasi.Mithuna)
				);
	
		setUp("Bhadra Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.BhadraYoga);
	}
	
	@Test
	public void testBhadraYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Kumbha),
				newEntry(Planet.Mercury, Rasi.Kanya)
				);
	
		setUp("Bhadra Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.BhadraYoga);
	}
}
