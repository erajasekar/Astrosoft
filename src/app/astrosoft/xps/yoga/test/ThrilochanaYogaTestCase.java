/**
 * ThrilochanaYogaTestCase.java
 Created On 2008, Jan 3, 2008 9:05:27 PM
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
public class ThrilochanaYogaTestCase extends YogaAnalysisTestCase {

	@Test
	public void testThrilochanaYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Sun, Rasi.Kanya),
				newEntry(Planet.Moon, Rasi.Makara),
				newEntry(Planet.Mars, Rasi.Vrishabha)
				);
	
		setUp("Thrilochana Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.ThrilochanaYoga,"100%");
	}

	@Test
	public void testThrilochanaYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Mesha),
				newEntry(Planet.Sun, Rasi.Simha),
				newEntry(Planet.Mars, Rasi.Dhanus)
				);
	
		setUp("Thrilochana Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.ThrilochanaYoga,"100%");
	}
	
	@Test
	public void testThrilochanaYoga5() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Mithuna),
				newEntry(Planet.Sun, Rasi.Thula),
				newEntry(Planet.Mars, Rasi.Thula)
				);
	
		setUp("Thrilochana Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.ThrilochanaYoga,"100%");
	}
	
	@Test
	public void testThrilochanaYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Mithuna),
				newEntry(Planet.Sun, Rasi.Thula),
				newEntry(Planet.Mars, Rasi.Vrichika)
				);
	
		setUp("Thrilochana Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.ThrilochanaYoga);
	}
}

