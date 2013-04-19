/**
 * SunaphaYogaTestCase.java
 Created On 2007, Nov 1, 2007 10:50:10 PM
 @author E. Rajasekar
 */

package app.astrosoft.xps.yoga.test;

import static app.astrosoft.util.CollectionUtil.newEntry;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.constructChart;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.getSession;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.loadFacts;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.setUp;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.testYogaPresent;

import org.junit.Ignore;
import org.junit.Test;

import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.YogaCombination;
import app.astrosoft.xps.beans.PlanetChart;

@SuppressWarnings("unchecked")
public class SunaphaYogaTestCase extends YogaAnalysisTestCase {

	
	@Test
	//@Ignore
	public void testSunaphaYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Mesha),
				newEntry(Planet.Mercury, Rasi.Vrishabha));
		
		setUp("Sunapha Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.SunaphaYoga, "100%");
	}
	
	@Test
	//@Ignore
	public void testSunaphaYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Mesha),
				newEntry(Planet.Jupiter, Rasi.Vrishabha),
				newEntry(Planet.Ketu, Rasi.Vrishabha));
		
		setUp("Sunapha Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.SunaphaYoga, "50%");
	}
}

