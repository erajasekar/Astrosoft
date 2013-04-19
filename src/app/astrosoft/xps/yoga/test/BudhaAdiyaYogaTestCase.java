/**
 * BudhaAdiyaYogaTestCase.java
 Created On 2007, Nov 1, 2007 6:15:59 PM
 @author E. Rajasekar
 */

package app.astrosoft.xps.yoga.test;

import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.getSession;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.loadFacts;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.setUp;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.testYogaPresent;

import org.junit.Ignore;
import org.junit.Test;

import app.astrosoft.consts.YogaCombination;
import app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.SAMPLE_HOROSCOPE;

public class BudhaAdiyaYogaTestCase {

	@Test
	//@Ignore
	public void testBudhaAdiyaYoga() throws Exception{
		
		setUp("Budha-Aditya Yoga");
		loadFacts(SAMPLE_HOROSCOPE.RAJA);
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.BudhaAdityaYoga);
	}
}
