/**
 * VidyutYogaTestCase.java
 Created On 2008, Jan 2, 2008 8:36:38 PM
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
public class VidyutYogaTestCase  extends YogaAnalysisTestCase {
	
	
	
	@Test
	public void testVidyutYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Simha),
				newEntry(Planet.Mercury, Rasi.Kanya),
				newEntry(Planet.Venus, Rasi.Kanya),
				newEntry(Planet.Sun, Rasi.Kanya)
				);
	
		setUp("Vidyut Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.VidyutYoga,"100%");
	}
	
	@Test
	public void testVidyutYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Kataka),
				newEntry(Planet.Venus, Rasi.Meena),
				newEntry(Planet.Moon, Rasi.Kanya)
				);
	
		setUp("Vidyut Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.VidyutYoga,"100%");
	}

	@Test
	public void testVidyutYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Simha),
				newEntry(Planet.Mercury, Rasi.Mithuna),
				newEntry(Planet.Venus, Rasi.Kanya),
				newEntry(Planet.Sun, Rasi.Kanya)
				);
	
		setUp("Vidyut Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.VidyutYoga);
	}
	
	@Test
	public void testVidyutYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Simha),
				newEntry(Planet.Mercury, Rasi.Kanya),
				newEntry(Planet.Venus, Rasi.Kanya),
				newEntry(Planet.Sun, Rasi.Thula)
				);
	
		setUp("Vidyut Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.VidyutYoga);
	}
	
}

