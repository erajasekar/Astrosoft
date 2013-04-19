/**
 * KahalaYogaTestCase.java
 Created On 2007, Nov 8, 2007 5:48:10 PM
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

@SuppressWarnings("unchecked")
public class KahalaYogaTestCase extends YogaAnalysisTestCase {

	
	
	@Test
	//@Ignore
	public void testKahalaYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mars, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Thula),
				newEntry(Planet.Jupiter, Rasi.Thula)
				);
	
		setUp("Kahala Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.KahalaYoga,"100%");
	}
	
	@Test
	//@Ignore
	public void testKahalaYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mars, Rasi.Makara),
				newEntry(Planet.Moon, Rasi.Thula),
				newEntry(Planet.Jupiter, Rasi.Makara)
				);
	
		setUp("Kahala Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.KahalaYoga,"100%");
	}
	
	@Test
	//@Ignore
	public void testKahalaYoga5() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mars, Rasi.Thula),
				newEntry(Planet.Moon, Rasi.Thula),
				newEntry(Planet.Jupiter, Rasi.Mesha)
				);
	
		setUp("Kahala Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.KahalaYoga,"100%");
	}
	
	@Test
	//@Ignore
	public void testKahalaYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mars, Rasi.Vrishabha),
				newEntry(Planet.Moon, Rasi.Thula),
				newEntry(Planet.Jupiter, Rasi.Thula)
				);
	
		setUp("Kahala Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.KahalaYoga);
	}
	
	@Test
	public void testKahalaYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mars, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Vrichika),
				newEntry(Planet.Jupiter, Rasi.Thula)
				);
	
		setUp("Kahala Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.KahalaYoga);
	}

	@Test
	//@Ignore
	public void testKahalaYoga7() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mars, Rasi.Kataka),
				newEntry(Planet.Moon, Rasi.Thula),
				newEntry(Planet.Jupiter, Rasi.Thula)
				);
	
		setUp("Kahala Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.KahalaYoga);
	}
}


