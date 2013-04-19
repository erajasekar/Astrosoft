/**
 * GajaYogaTestCase.java
 Created On 2008, Jan 2, 2008 11:49:41 AM
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
public class GajaYogaTestCase extends YogaAnalysisTestCase {
	
	
	@Test
	public void testGajaYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Dhanus),
				newEntry(Planet.Mercury, Rasi.Thula),
				newEntry(Planet.Moon, Rasi.Thula),
				newEntry(Planet.Venus, Rasi.Mesha)
				);
	
		setUp("Gaja Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.GajaYoga,"100%");
	}
	
	@Test
	public void testGajaYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mithuna),
				newEntry(Planet.Jupiter, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Mesha),
				newEntry(Planet.Mars, Rasi.Kanya)
				);
	
		setUp("Gaja Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.GajaYoga,"100%");
	}
	
	@Test
	public void testGajaYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Dhanus),
				newEntry(Planet.Mercury, Rasi.Vrichika),
				newEntry(Planet.Moon, Rasi.Thula),
				newEntry(Planet.Venus, Rasi.Mesha)
				);
	
		setUp("Gaja Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.GajaYoga);
	}
	
	@Test
	public void testGajaYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Dhanus),
				newEntry(Planet.Mercury, Rasi.Thula),
				newEntry(Planet.Moon, Rasi.Vrichika),
				newEntry(Planet.Venus, Rasi.Mesha)
				);
	
		setUp("Gaja Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.GajaYoga);
	}
	
	
	@Test
	public void testGajaYoga6() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Dhanus),
				newEntry(Planet.Mercury, Rasi.Thula),
				newEntry(Planet.Moon, Rasi.Thula),
				newEntry(Planet.Venus, Rasi.Mithuna)
				);
	
		setUp("Gaja Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.GajaYoga);
	}
	
}


