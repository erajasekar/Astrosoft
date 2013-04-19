/**
 * VesiYogaTestCase.java
 Created On 2007, Nov 1, 2007 5:50:49 PM
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
public class VesiYogaTestCase extends YogaAnalysisTestCase {

	@Test
	public void testSubhaVesiYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Sun, Rasi.Kanya),
				newEntry(Planet.Jupiter, Rasi.Thula)
				);
	
		setUp("SubhaVesi Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.SubhaVesiYoga,"100%");
	}
	
	@Test
	public void testPapaVesiYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Sun, Rasi.Kanya),
				newEntry(Planet.Saturn, Rasi.Thula)
				);
	
		setUp("PapaVesi Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.PapaVesiYoga,"100%");
	}
	
	@Test
	public void testSubhaVesiYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Sun, Rasi.Kanya),
				newEntry(Planet.Rahu, Rasi.Thula)
				);
	
		setUp("SubhaVesi Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.SubhaVesiYoga);
	}
	
	@Test
	public void testPapaVesiYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Thula),
				newEntry(Planet.Sun, Rasi.Kanya)
				);
	
		setUp("PapaVesi Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.PapaVesiYoga);
	}
	
}
