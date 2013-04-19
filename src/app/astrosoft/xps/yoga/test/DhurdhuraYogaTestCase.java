/**
 * DhurdhuraYogaTestCase.java
 Created On 2007, Nov 1, 2007 9:29:51 PM
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
import app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.SAMPLE_HOROSCOPE;

@SuppressWarnings("unchecked")
public class DhurdhuraYogaTestCase extends YogaAnalysisTestCase {

	
	@Test
	//@Ignore
	public void testDhurdhuraYoga1() throws Exception{
			
			PlanetChart chart = constructChart(
					newEntry(Planet.Ascendant, Rasi.Mesha),
					newEntry(Planet.Moon, Rasi.Dhanus),
					newEntry(Planet.Mars, Rasi.Makara),
					newEntry(Planet.Mercury, Rasi.Vrichika));
		
			setUp("Dhurdhura Yoga");
			loadFacts(chart);
			getSession().fireAllRules();
			testYogaPresent(YogaCombination.DhurdhuraYoga);
		}
		
		@Test
		//@Ignore
		public void testDhurdhuraYoga2() throws Exception{
			
			setUp("Dhurdhura Yoga");
			loadFacts(SAMPLE_HOROSCOPE.RAJA);
			getSession().fireAllRules();
			testYogaNotPresent(YogaCombination.DhurdhuraYoga);
		}
		
}

