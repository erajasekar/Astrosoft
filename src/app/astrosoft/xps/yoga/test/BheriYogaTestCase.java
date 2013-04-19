/**BheriYogaTestCase.java
 Created On 2007, Dec 24, 2007 5:16:31 PM
 @author E. Rajasekar
 * 
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
public class BheriYogaTestCase extends YogaAnalysisTestCase {
	
	@Test
	//@Ignore
	public void testBheriYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mars, Rasi.Mesha),
				newEntry(Planet.Jupiter, Rasi.Kataka),
				newEntry(Planet.Venus, Rasi.Makara)
				);
	
		setUp("Bheri Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.BheriYoga,"100%");
	}
	
	@Test
	//@Ignore
	public void testBheriYoga3() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mars, Rasi.Meena),
				newEntry(Planet.Jupiter, Rasi.Meena),
				newEntry(Planet.Venus, Rasi.Dhanus)
				);
	
		setUp("Bheri Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.BheriYoga,"100%");
	}
	
	@Test
	//@Ignore
	public void testBheriYoga2() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mars, Rasi.Mesha),
				newEntry(Planet.Venus, Rasi.Kataka),
				newEntry(Planet.Jupiter, Rasi.Makara)
				);
	
		setUp("Bheri Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.BheriYoga);
	}
	
	@Test
	//@Ignore
	public void testBheriYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Mars, Rasi.Vrishabha),
				newEntry(Planet.Jupiter, Rasi.Kataka),
				newEntry(Planet.Venus, Rasi.Makara)
				);
	
		setUp("Bheri Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.BheriYoga);
	}
}
