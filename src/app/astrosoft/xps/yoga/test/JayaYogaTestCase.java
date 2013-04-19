/**
 * JayaYogaTestCase.java
 * Created On 2007, Nov 8, 2007 4:24:42 PM
 * @author E. Rajasekar
 */

package app.astrosoft.xps.yoga.test;

import static app.astrosoft.util.CollectionUtil.newEntry;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.*;


import org.junit.Test;

import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.YogaCombination;
import app.astrosoft.xps.beans.PlanetChart;

@SuppressWarnings("unchecked")
public class JayaYogaTestCase extends YogaAnalysisTestCase {

	
	@Test
	public void testJayaYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mercury, Rasi.Meena),
				newEntry(Planet.Saturn, Rasi.Thula)
				);
	
		setUp("Jaya Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.JayaYoga,"100%");
	}
	
	@Test
	public void testJayaYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mercury, Rasi.Kanya),
				newEntry(Planet.Saturn, Rasi.Mesha)
				);
	
		setUp("Jaya Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.JayaYoga);
	}

}

