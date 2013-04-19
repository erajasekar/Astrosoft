/**
 * KemadrumaYogaTestCase.java
 Created On 2007, Nov 1, 2007 10:48:41 PM
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
public class KemadrumaYogaTestCase extends YogaAnalysisTestCase {

	
	@Test
	//@Ignore
	public void testKemadrumaYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Dhanus),
				newEntry(Planet.Jupiter, Rasi.Mesha));
	
		setUp("Kemadruma Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.KemadrumaYoga);
	}
	
	@Test
	//@Ignore
	public void testKemadrumaYoga2() throws Exception{
	
		setUp("Kemadruma Yoga");
		loadFacts(SAMPLE_HOROSCOPE.RAJA);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.KemadrumaYoga);
	}
	
	
	
}

