/**
 * AmalaYogaTestCase.java
 Created On 2007, Nov 1, 2007 6:20:09 PM
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
public class AmalaYogaTestCase extends YogaAnalysisTestCase{

	@Test
	//@Ignore
	public void testAmalaYoga1() throws Exception{
		
		setUp("Amala Yoga");
		loadFacts(SAMPLE_HOROSCOPE.RAJA);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.AmalaYoga);
	}
	
	@Test
	//@Ignore
	public void testAmalaYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
									newEntry(Planet.Ascendant, Rasi.Mesha),
									newEntry(Planet.Moon, Rasi.Mesha));
		
		log.fine("Chart " + chart);
		
		setUp("Amala Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.AmalaYoga);
	}
	
	@Test
	//@Ignore
	public void testAmalaYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
									newEntry(Planet.Ascendant, Rasi.Mesha),
									newEntry(Planet.Moon, Rasi.Makara));
		
		log.fine("Chart " + chart);
		
		setUp("Amala Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.AmalaYoga, "100%");
	}
}
