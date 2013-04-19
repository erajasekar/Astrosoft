/**
 * LakshmiYogaTestCase.java
 Created On 2007, Dec 28, 2007 5:08:25 PM
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
import app.astrosoft.test.SampleHoroscopes;
import app.astrosoft.xps.beans.PlanetChart;
import app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.SAMPLE_HOROSCOPE;

@SuppressWarnings("unchecked")
public class LakshmiYogaTestCase extends YogaAnalysisTestCase {
	
	@Test
	@Ignore
	public void testLakshmiYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mithuna),
				newEntry(Planet.Saturn, Rasi.Kumbha)
				);
	
		setUp("Lakshmi Yoga");
		
		Set<Planet> powerful = EnumSet.of(Planet.Mercury);
		
		loadFacts(chart, powerful);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.LakshmiYoga,"100%");
	}
	
	@Test
	@Ignore
	public void testLakshmiYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mithuna),
				newEntry(Planet.Saturn, Rasi.Thula)
				);
	
		setUp("Lakshmi Yoga");
		
		Set<Planet> powerful = EnumSet.of(Planet.Mercury);
		
		loadFacts(chart, powerful);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.LakshmiYoga,"100%");
	}

	@Test
	@Ignore
	public void testLakshmiYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mithuna),
				newEntry(Planet.Saturn, Rasi.Makara)
				);
	
		setUp("Lakshmi Yoga");
		
		Set<Planet> powerful = EnumSet.of(Planet.Mercury);
		
		loadFacts(chart, powerful);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.LakshmiYoga);
	}
	
	@Test
	@Ignore
	public void testLakshmiYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Vrishabha),
				newEntry(Planet.Saturn, Rasi.Thula)
				);
	
		setUp("Lakshmi Yoga");
		
		Set<Planet> powerful = EnumSet.of(Planet.Venus);
		
		loadFacts(chart, powerful);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.LakshmiYoga);
	}
	
	@Test
	@Ignore
	public void testLakshmiYoga6() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mithuna),
				newEntry(Planet.Saturn, Rasi.Kumbha)
				);
	
		setUp("Lakshmi Yoga");
		
		Set<Planet> powerful = EnumSet.of(Planet.Venus);
		
		loadFacts(chart, powerful);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.LakshmiYoga);
	}
	
	@Test
	//@Ignore
	public void testLakshmiYoga7() throws Exception{
		
		setUp("Lakshmi Yoga");
		
		loadFacts(SAMPLE_HOROSCOPE.SUBA);
		
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.LakshmiYoga,"088%");
	}
}

