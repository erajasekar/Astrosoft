/**
 * RajalakshanaYogaTestCase.java
 Created On 2007, Nov 1, 2007 9:22:19 PM
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
import app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.SAMPLE_HOROSCOPE;

@SuppressWarnings("unchecked")
public class RajalakshanaYogaTestCase extends YogaAnalysisTestCase {

	
	@Test
	//@Ignore
	public void testRajalakshanaYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Mesha),
				newEntry(Planet.Jupiter, Rasi.Kataka),
				newEntry(Planet.Mercury, Rasi.Thula),
				newEntry(Planet.Venus, Rasi.Thula)
				);
	
		setUp("Rajalakshana Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.RajalakshanaYoga,"100%");
	}
	
	@Test
	//@Ignore
	public void testRajalakshanaYoga2() throws Exception{
		
		setUp("Rajalakshana Yoga");
		loadFacts(SAMPLE_HOROSCOPE.RAJA);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.RajalakshanaYoga);
	}
	
}
