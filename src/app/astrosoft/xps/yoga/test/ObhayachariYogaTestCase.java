/**
 * ObhayachariYogaTestCase.java
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
public class ObhayachariYogaTestCase extends YogaAnalysisTestCase {

	@Test
	public void testObhayachariYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Sun, Rasi.Kanya),
				newEntry(Planet.Jupiter, Rasi.Simha),
				newEntry(Planet.Saturn, Rasi.Thula)
				);
	
		setUp("Obhayachari Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.ObhayachariYoga,"100%");
	}
	
	@Test
	public void testObhayachariYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Sun, Rasi.Kanya),
				newEntry(Planet.Jupiter, Rasi.Simha)
				);
	
		setUp("Obhayachari Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.ObhayachariYoga);
	}
	
}
