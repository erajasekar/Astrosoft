/**
 * VasumathiYogaTestCase.java
 Created On 2007, Nov 1, 2007 10:51:35 PM
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
public class VasumathiYogaTestCase extends YogaAnalysisTestCase {

	@Test
	//@Ignore
	public void testVasumathiYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Mithuna),
				newEntry(Planet.Venus, Rasi.Simha),
				newEntry(Planet.Jupiter, Rasi.Meena));
	
		setUp("Vasumathi Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.VasumathiYoga, "75%");
	}
	
	
	@Test
	//@Ignore
	public void testVasumathiYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Mithuna),
				newEntry(Planet.Venus, Rasi.Thula)
				);
	
		setUp("Vasumathi Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.VasumathiYoga);
	}
	
	@Test
	//@Ignore
	public void testVasumathiYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mithuna),
				newEntry(Planet.Moon, Rasi.Vrichika),
				newEntry(Planet.Venus, Rasi.Simha),
				newEntry(Planet.Jupiter, Rasi.Meena));
	
		setUp("Vasumathi Yoga");
		loadFacts(chart,YogaAnalysisTestHelper.getCharacter2());
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.VasumathiYoga, "100%");
	}
}

