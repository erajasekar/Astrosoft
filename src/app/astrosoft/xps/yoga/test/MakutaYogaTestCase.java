/**
 * MakutaYogaTestCase.java
 Created On 2008, Jan 2, 2008 8:13:15 PM
 @author E. Rajasekar
 */

package app.astrosoft.xps.yoga.test;

import static app.astrosoft.util.CollectionUtil.newEntry;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.constructChart;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.getSession;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.loadFacts;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.setUp;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.testYogaPresent;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.testYogaNotPresent;

import org.junit.Test;

import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.YogaCombination;
import app.astrosoft.xps.beans.PlanetChart;

@SuppressWarnings("unchecked")
public class MakutaYogaTestCase extends YogaAnalysisTestCase {
	
	
	@Test
	public void testMakutaYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Dhanus),
				newEntry(Planet.Sun, Rasi.Dhanus),
				newEntry(Planet.Jupiter, Rasi.Simha),
				newEntry(Planet.Mercury, Rasi.Mesha),
				newEntry(Planet.Saturn, Rasi.Kanya)
				);
	
		setUp("Makuta Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.MakutaYoga,"100%");
	}
	
	@Test
	public void testMakutaYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Kanya),
				newEntry(Planet.Venus, Rasi.Kanya),
				newEntry(Planet.Jupiter, Rasi.Vrishabha),
				newEntry(Planet.Moon, Rasi.Makara),
				newEntry(Planet.Saturn, Rasi.Mithuna)
				);
	
		setUp("Makuta Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.MakutaYoga,"100%");
	}

	@Test
	public void testMakutaYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Dhanus),
				newEntry(Planet.Sun, Rasi.Dhanus),
				newEntry(Planet.Jupiter, Rasi.Simha),
				newEntry(Planet.Mars, Rasi.Mesha),
				newEntry(Planet.Saturn, Rasi.Kanya)
				);
	
		setUp("Makuta Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.MakutaYoga);
	}
	
	@Test
	public void testMakutaYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Dhanus),
				newEntry(Planet.Sun, Rasi.Dhanus),
				newEntry(Planet.Jupiter, Rasi.Kanya),
				newEntry(Planet.Mercury, Rasi.Mesha),
				newEntry(Planet.Saturn, Rasi.Kanya)
				);
	
		setUp("Makuta Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.MakutaYoga);
	}
}

