/**
 * AmsavataraYogaTestCase.java
 Created On 2008, Jan 2, 2008 1:20:30 PM
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
public class AmsavataraYogaTestCase extends YogaAnalysisTestCase {

	
	@Test
	//@Ignore
	public void testAmsavataraYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Venus, Rasi.Kataka),
				newEntry(Planet.Jupiter, Rasi.Makara),
				newEntry(Planet.Saturn, Rasi.Thula));
	
		setUp("Amsavatara Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.AmsavataraYoga,"100%");
	}
	
	@Test
	//@Ignore
	public void testAmsavataraYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Kataka),
				newEntry(Planet.Venus, Rasi.Kataka),
				newEntry(Planet.Jupiter, Rasi.Makara),
				newEntry(Planet.Saturn, Rasi.Thula));
	
		setUp("Amsavatara Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.AmsavataraYoga,"100%");
	}
	
	@Test
	//@Ignore
	public void testAmsavataraYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Venus, Rasi.Mithuna),
				newEntry(Planet.Jupiter, Rasi.Makara),
				newEntry(Planet.Saturn, Rasi.Thula));
	
		setUp("Amsavatara Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.AmsavataraYoga);
	}

	@Test
	//@Ignore
	public void testAmsavataraYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Venus, Rasi.Kataka),
				newEntry(Planet.Jupiter, Rasi.Kumbha),
				newEntry(Planet.Saturn, Rasi.Thula));
	
		setUp("Amsavatara Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.AmsavataraYoga);
	}
	
	@Test
	//@Ignore
	public void testAmsavataraYoga6() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Venus, Rasi.Kataka),
				newEntry(Planet.Jupiter, Rasi.Makara),
				newEntry(Planet.Saturn, Rasi.Vrichika));
	
		setUp("Amsavatara Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.AmsavataraYoga);
	}
}

