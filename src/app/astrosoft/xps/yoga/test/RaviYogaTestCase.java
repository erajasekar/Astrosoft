/**
 * RaviYogaTestCase.java
 Created On 2008, Jan 3, 2008 2:37:03 PM
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
public class RaviYogaTestCase extends YogaAnalysisTestCase {
	
	@Test
	public void testRaviYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Kumbha),
				newEntry(Planet.Sun, Rasi.Vrichika),
				newEntry(Planet.Mars, Rasi.Mesha),
				newEntry(Planet.Saturn, Rasi.Mesha)
				);
	
		setUp("Ravi Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.RaviYoga,"100%");
	}
	
	@Test
	public void testRaviYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Makara),
				newEntry(Planet.Sun, Rasi.Thula),
				newEntry(Planet.Venus, Rasi.Meena),
				newEntry(Planet.Saturn, Rasi.Meena)
				);
	
		setUp("Ravi Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.RaviYoga,"100%");
	}
	
	@Test
	public void testRaviYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Makara),
				newEntry(Planet.Sun, Rasi.Vrichika),
				newEntry(Planet.Venus, Rasi.Meena),
				newEntry(Planet.Saturn, Rasi.Meena)
				);
	
		setUp("Ravi Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.RaviYoga);
	}
	
	@Test
	public void testRaviYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Vrichika),
				newEntry(Planet.Sun, Rasi.Simha),
				newEntry(Planet.Saturn, Rasi.Makara)
				);
	
		setUp("Ravi Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.RaviYoga);
	}

}

