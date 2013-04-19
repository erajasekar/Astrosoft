/**
 * IndraYogaTestCase.java
 * Created On 2008, Jan 3, 2008 2:02:58 PM
 * @author E. Rajasekar
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
public class IndraYogaTestCase extends YogaAnalysisTestCase {
	
	@Test
	public void testIndraYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Dhanus),
				newEntry(Planet.Mars, Rasi.Thula),
				newEntry(Planet.Venus, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Mesha)
				);
	
		setUp("Indra Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.IndraYoga,"100%");
	}
	
	@Test
	public void testIndraYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Kanya),
				newEntry(Planet.Moon, Rasi.Makara),
				newEntry(Planet.Saturn, Rasi.Kataka)
				);
	
		setUp("Indra Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.IndraYoga,"100%");
	}
	
	@Test
	public void testIndraYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Dhanus),
				newEntry(Planet.Mars, Rasi.Thula),
				newEntry(Planet.Venus, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Vrishabha)
				);
	
		setUp("Indra Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.IndraYoga);
	}
	
	@Test
	public void testIndraYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Dhanus),
				newEntry(Planet.Mars, Rasi.Vrichika),
				newEntry(Planet.Venus, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Mesha)
				);
	
		setUp("Indra Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.IndraYoga);
	}
}

