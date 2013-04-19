/**
 * SankhaYogaTestCase.java
 Created On 2007, Dec 24, 2007 4:52:42 PM
 @author E. Rajasekar
 */

package app.astrosoft.xps.yoga.test;

import static app.astrosoft.util.CollectionUtil.newEntry;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.constructChart;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.getSession;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.loadFacts;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.setUp;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.testYogaNotPresent;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.testYogaPresent;

import org.junit.Ignore;
import org.junit.Test;

import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.YogaCombination;
import app.astrosoft.xps.beans.PlanetChart;

@SuppressWarnings("unchecked")
public class SankhaYogaTestCase extends YogaAnalysisTestCase {

	@Test
	//@Ignore
	public void testSankhaYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mars, Rasi.Mesha),
				newEntry(Planet.Sun, Rasi.Thula),
				newEntry(Planet.Mercury, Rasi.Thula)
				);
	
		setUp("Sankha Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.SankhaYoga,"100%");
	}
	
	@Test
	//@Ignore
	public void testSankhaYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mars, Rasi.Thula),
				newEntry(Planet.Sun, Rasi.Thula),
				newEntry(Planet.Mercury, Rasi.Mesha)
				);
	
		setUp("Sankha Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.SankhaYoga,"100%");
	}
	
	@Test
	//@Ignore
	public void testSankhaYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mars, Rasi.Vrishabha),
				newEntry(Planet.Sun, Rasi.Thula),
				newEntry(Planet.Mercury, Rasi.Mesha)
				);
	
		setUp("Sankha Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.SankhaYoga);
	}
	
	@Test
	//@Ignore
	public void testSankhaYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mars, Rasi.Kataka),
				newEntry(Planet.Sun, Rasi.Thula),
				newEntry(Planet.Mercury, Rasi.Vrichika)
				);
	
		setUp("Sankha Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.SankhaYoga);
	}
}

