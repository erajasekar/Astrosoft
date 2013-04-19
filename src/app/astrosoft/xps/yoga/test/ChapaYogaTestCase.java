/**
 * ChapaYogaTestCase.java
 Created On 2007, Nov 8, 2007 5:06:17 PM
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
public class ChapaYogaTestCase extends YogaAnalysisTestCase {

	@Test
	public void testChapaYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Vrishabha),
				newEntry(Planet.Venus, Rasi.Meena),
				newEntry(Planet.Sun, Rasi.Kumbha),
				newEntry(Planet.Saturn, Rasi.Simha)
				);
	
		setUp("Chapa Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.ChapaYoga,"100%");
	}
	
	
	@Test
	public void testChapaYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Vrishabha),
				newEntry(Planet.Venus, Rasi.Mesha),
				newEntry(Planet.Sun, Rasi.Kumbha),
				newEntry(Planet.Saturn, Rasi.Simha)
				);
	
		setUp("Chapa Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.ChapaYoga);
	}

}





