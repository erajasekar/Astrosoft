/**
 * SivaYogaTestCase.java
 Created On 2007, Nov 8, 2007 4:41:22 PM
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

import org.junit.Test;

import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.YogaCombination;
import app.astrosoft.xps.beans.PlanetChart;

@SuppressWarnings("unchecked")
public class SivaYogaTestCase extends YogaAnalysisTestCase {

	@Test
	public void testSivaYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Meena),
				newEntry(Planet.Moon, Rasi.Vrichika),
				newEntry(Planet.Mars, Rasi.Dhanus),
				newEntry(Planet.Jupiter, Rasi.Kataka)
				);
	
		setUp("Siva Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.SivaYoga,"100%");
	}
	
	@Test
	public void testSivaYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Jupiter, Rasi.Makara),
				newEntry(Planet.Saturn, Rasi.Simha),
				newEntry(Planet.Sun, Rasi.Simha)
				);
	
		setUp("Siva Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.SivaYoga);
	}


}

