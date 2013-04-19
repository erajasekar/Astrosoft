/**
 * MahabhagyaYogaTestCase.java
 * Created On 2007, Nov 14, 2007 5:44:57 PM
 * @author E. Rajasekar
 */

package app.astrosoft.xps.yoga.test;

import static app.astrosoft.util.CollectionUtil.newEntry;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.*;

import org.junit.Test;

import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.Sex;
import app.astrosoft.consts.YogaCombination;
import app.astrosoft.xps.beans.PlanetChart;
import app.astrosoft.xps.beans.YogaFacts;
import app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.SAMPLE_HOROSCOPE;

@SuppressWarnings("unchecked")

public class MahabhagyaYogaTestCase  extends YogaAnalysisTestCase{

	@Test
	//@Ignore
	public void testMahabhagyaYoga1() throws Exception{
		
		setUp("Mahabhagya Yoga");
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Sun, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Thula));
		
		YogaFacts facts = new YogaFacts();
		
		facts.setBirthAtDay(true);
		facts.setPersonSex(Sex.Male);
		
		loadFacts(chart,facts);
		
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.MahabhagyaYoga);
	}

	@Test

	public void testMahabhagyaYoga2() throws Exception{
		
		setUp("Mahabhagya Yoga");
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Sun, Rasi.Vrishabha),
				newEntry(Planet.Moon, Rasi.Thula));
		
		YogaFacts facts = new YogaFacts();
		
		facts.setBirthAtDay(true);
		facts.setPersonSex(Sex.Male);
		
		loadFacts(chart,facts);
		
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.MahabhagyaYoga);
	}
	
	@Test
	public void testMahabhagyaYoga4() throws Exception{
		
		setUp("Mahabhagya Yoga");
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Sun, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Thula));
		
		YogaFacts facts = new YogaFacts();
		
		facts.setBirthAtDay(true);
		facts.setPersonSex(Sex.Female);
		
		loadFacts(chart,facts);
		
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.MahabhagyaYoga);
	}
	
	@Test
	public void testMahabhagyaYoga6() throws Exception{
		
		setUp("Mahabhagya Yoga");
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Sun, Rasi.Mesha),
				newEntry(Planet.Moon, Rasi.Thula));
		
		YogaFacts facts = new YogaFacts();
		
		facts.setBirthAtDay(false);
		facts.setPersonSex(Sex.Male);
		
		loadFacts(chart,facts);
		
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.MahabhagyaYoga);
	}
	
	@Test
	//@Ignore
	public void testMahabhagyaYoga3() throws Exception{
		
		setUp("Mahabhagya Yoga");
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Vrishabha),
				newEntry(Planet.Sun, Rasi.Kataka),
				newEntry(Planet.Moon, Rasi.Kanya));
		
		YogaFacts facts = new YogaFacts();
		
		facts.setBirthAtDay(false);
		facts.setPersonSex(Sex.Female);
		
		loadFacts(chart,facts);
		
		getSession().fireAllRules();
		testYogaPresent(YogaCombination.MahabhagyaYoga);
	}
	
	@Test
	public void testMahabhagyaYoga8() throws Exception{
		
		setUp("Mahabhagya Yoga");
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Mesha),
				newEntry(Planet.Sun, Rasi.Vrichika),
				newEntry(Planet.Moon, Rasi.Makara));
		
		YogaFacts facts = new YogaFacts();
		
		facts.setBirthAtDay(false);
		facts.setPersonSex(Sex.Female);
		
		loadFacts(chart,facts);
		
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.MahabhagyaYoga);
	}
	
	@Test
	public void testMahabhagyaYoga10() throws Exception{
		
		setUp("Mahabhagya Yoga");
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Meena),
				newEntry(Planet.Sun, Rasi.Vrichika),
				newEntry(Planet.Moon, Rasi.Makara));
		
		YogaFacts facts = new YogaFacts();
		
		facts.setBirthAtDay(false);
		facts.setPersonSex(Sex.Male);
		
		loadFacts(chart,facts);
		
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.MahabhagyaYoga);
	}
	
	@Test
	public void testMahabhagyaYoga12() throws Exception{
		
		setUp("Mahabhagya Yoga");
		
		PlanetChart chart = constructChart(
				newEntry(Planet.Ascendant, Rasi.Meena),
				newEntry(Planet.Sun, Rasi.Vrichika),
				newEntry(Planet.Moon, Rasi.Makara));
		
		YogaFacts facts = new YogaFacts();
		
		facts.setBirthAtDay(true);
		facts.setPersonSex(Sex.Female);
		
		loadFacts(chart,facts);
		
		getSession().fireAllRules();
		testYogaNotPresent(YogaCombination.MahabhagyaYoga);
	}
}

