/**
 * ChatussagaraYogaTestCase.java
 Created On 2007, Nov 1, 2007 9:22:19 PM
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
public class ChatussagaraYogaTestCase extends YogaAnalysisTestCase {

	@Test
	//@Ignore
	public void testChatussagaraYoga1() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Mesha),
				newEntry(Planet.Jupiter, Rasi.Kataka),
				newEntry(Planet.Mercury, Rasi.Thula),
				newEntry(Planet.Saturn, Rasi.Makara),
				newEntry(Planet.Venus, Rasi.Thula)
				);
	
		setUp("Chatussagara Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.ChatussagaraYoga,"100%");
	}
	
	@Test
	//@Ignore
	public void testChatussagaraYoga2() throws Exception{
		
		setUp("Chatussagara Yoga");
		loadFacts(SAMPLE_HOROSCOPE.RAJA);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.ChatussagaraYoga);
	}
	
	
	@Test
	//@Ignore
	public void testChatussagaraYoga4() throws Exception{
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Mesha),
				newEntry(Planet.Jupiter, Rasi.Kataka),
				newEntry(Planet.Mercury, Rasi.Thula),
				newEntry(Planet.Rahu, Rasi.Makara)
				);
	
		setUp("Chatussagara Yoga");
		loadFacts(chart);
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.ChatussagaraYoga);
	}
	
}
