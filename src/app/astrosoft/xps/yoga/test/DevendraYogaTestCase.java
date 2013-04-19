/**
 * DevendraYogaTestCase.java
 Created On 2008, Jan 2, 2008 7:47:19 PM
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
import app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.SAMPLE_HOROSCOPE;

@SuppressWarnings("unchecked")
public class DevendraYogaTestCase  extends YogaAnalysisTestCase{

	
	@Test
	//@Ignore
	public void testDevendraYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Vrishabha),
				newEntry(Planet.Venus, Rasi.Meena),
				newEntry(Planet.Jupiter, Rasi.Vrishabha),
				newEntry(Planet.Mercury, Rasi.Kumbha),
				newEntry(Planet.Saturn, Rasi.Mithuna));
		
		setUp("Devendra Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.DevendraYoga);
	}
	
	@Test
	//@Ignore
	public void testDevendraYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Vrichika),
				newEntry(Planet.Mars, Rasi.Kanya),
				newEntry(Planet.Mercury, Rasi.Vrichika),
				newEntry(Planet.Jupiter, Rasi.Simha),
				newEntry(Planet.Sun, Rasi.Dhanus));
		
		setUp("Devendra Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.DevendraYoga);
	}
	
	@Test
	//@Ignore
	public void testDevendraYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mithuna),
				newEntry(Planet.Mars, Rasi.Kanya),
				newEntry(Planet.Mercury, Rasi.Vrichika),
				newEntry(Planet.Jupiter, Rasi.Simha),
				newEntry(Planet.Sun, Rasi.Dhanus));
		
		setUp("Devendra Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.DevendraYoga);
	}
	
	@Test
	//@Ignore
	public void testDevendraYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Vrishabha),
				newEntry(Planet.Venus, Rasi.Mesha),
				newEntry(Planet.Jupiter, Rasi.Vrishabha),
				newEntry(Planet.Mercury, Rasi.Kumbha),
				newEntry(Planet.Saturn, Rasi.Mithuna));
		
		setUp("Devendra Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.DevendraYoga);
	}
	
	@Test
	//@Ignore
	public void testDevendraYoga6() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Vrishabha),
				newEntry(Planet.Venus, Rasi.Meena),
				newEntry(Planet.Jupiter, Rasi.Kataka),
				newEntry(Planet.Mercury, Rasi.Kumbha),
				newEntry(Planet.Saturn, Rasi.Mithuna));
		
		setUp("Devendra Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.DevendraYoga);
	}
	
	@Test
	//@Ignore
	public void testDevendraYoga8() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Vrishabha),
				newEntry(Planet.Venus, Rasi.Meena),
				newEntry(Planet.Jupiter, Rasi.Vrishabha),
				newEntry(Planet.Mercury, Rasi.Meena),
				newEntry(Planet.Saturn, Rasi.Mithuna));
		
		setUp("Devendra Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.DevendraYoga);
	}

}

