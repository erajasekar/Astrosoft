/**
 * KusumaYogaTestCase.java
 Created On 2007, Nov 7, 2007 5:17:55 PM
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
public class KusumaYogaTestCase extends YogaAnalysisTestCase {

	
	@Test
	public void testKusumaYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Thula),
				newEntry(Planet.Jupiter, Rasi.Mesha),
				newEntry(Planet.Sun, Rasi.Vrishabha)
				);
	
		setUp("Kusuma Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.KusumaYoga,"100%");
	}
	
	@Test
	public void testKusumaYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Thula),
				newEntry(Planet.Jupiter, Rasi.Mesha),
				newEntry(Planet.Sun, Rasi.Mesha)
				);
	
		setUp("Kusuma Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.KusumaYoga);
	}
}

