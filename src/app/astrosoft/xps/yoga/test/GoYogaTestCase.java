/**
 * GoYogaTestCase.java
 Created On 2008, Jan 3, 2008 2:56:54 PM
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
public class GoYogaTestCase extends YogaAnalysisTestCase {
	
	@Test
	public void testGoYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Thula),
				newEntry(Planet.Venus, Rasi.Meena),
				newEntry(Planet.Jupiter, Rasi.Dhanus),
				newEntry(Planet.Mars, Rasi.Dhanus)
				);
	
		setUp("Go Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.GoYoga,"100%");
	}
	
	@Test
	public void testGoYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Kanya),
				newEntry(Planet.Mercury, Rasi.Kanya),
				newEntry(Planet.Jupiter, Rasi.Dhanus),
				newEntry(Planet.Venus, Rasi.Dhanus)
				);
	
		setUp("Go Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.GoYoga,"100%");
	}
	
	@Test
	public void testGoYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Kumbha),
				newEntry(Planet.Mercury, Rasi.Kanya),
				newEntry(Planet.Jupiter, Rasi.Dhanus)
				);
	
		setUp("Go Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.GoYoga);
	}
	
	@Test
	public void testGoYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Kanya),
				newEntry(Planet.Mercury, Rasi.Kanya),
				newEntry(Planet.Jupiter, Rasi.Makara),
				newEntry(Planet.Venus, Rasi.Dhanus)
				);
	
		setUp("Go Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.GoYoga);
	}
	
}

