/**
 * SreenathaYogaTestCase.java
 Created On 2007, Dec 31, 2007 3:12:49 PM
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


import java.util.EnumSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.YogaCombination;
import app.astrosoft.xps.beans.PlanetChart;

@SuppressWarnings("unchecked")
public class SreenathaYogaTestCase extends YogaAnalysisTestCase {
	
	
	@Test
	public void testSreenathaYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Dhanus),
				newEntry(Planet.Mercury, Rasi.Kanya),
				newEntry(Planet.Sun, Rasi.Kanya)
				);
	
		setUp("Sreenatha Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.SreenathaYoga,"100%");
	}
	
	@Test
	public void testSreenathaYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Dhanus),
				newEntry(Planet.Mercury, Rasi.Kanya),
				newEntry(Planet.Sun, Rasi.Simha)
				);
	
		setUp("Sreenatha Yoga");
		
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.SreenathaYoga);
	}
}



