/**
 * YogaAnalysisTestCase.java
 * Created On 2007, Oct 22, 2007 3:51:12 PM
 * @author E. Rajasekar
 */

package app.astrosoft.xps.yoga.test;

import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.getLogger;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.getSession;

import java.util.logging.Logger;

import org.junit.After;

@SuppressWarnings("unchecked")
public abstract class YogaAnalysisTestCase {
	
	protected static final Logger log = Logger.getLogger(YogaAnalysisTestCase.class.getName());
	
	//private static Rasi[] chart1;
	
	//private static Rasi[] chart2;

	/*@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		YogaAnalysisTestHelper.setUpBeforeClass();
	}*/

	/*private static void setUpChart() {
		chart1 = new Rasi[] {Rasi.Mesha, //Sun
							Rasi.Mesha, // Moon
							Rasi.Mesha, // Mars
							Rasi.Vrishabha,// Mercury
							Rasi.Mithuna, // Jupiter
							Rasi.Kataka, // Venus
							Rasi.Kataka, // Saturn
							Rasi.Mithuna, // Rahu
							Rasi.Dhanus, // Kethu
							Rasi.Kanya // Ascendent
		};
		
		chart2 = new Rasi[] {Rasi.Mesha, //Sun
				Rasi.Mesha, // Moon
				Rasi.Vrishabha, // Mars
				Rasi.Vrishabha,// Mercury
				Rasi.Meena, // Jupiter
				Rasi.Kataka, // Venus
				Rasi.Kataka, // Saturn
				Rasi.Mithuna, // Rahu
				Rasi.Dhanus, // Kethu
				Rasi.Kanya // Ascendent
		};
	}*/

	

	
	
	
	
	
	
	
	
	
	

	

	
	/*
	private static PlanetChart constructChart(Varga varga, Rasi ...houses){
		
		EnumMap<Planet, Integer> planetLoc = new EnumMap<Planet, Integer>(Planet.class);
		
		EnumMap<Planet, Rasi> planetHouse = new EnumMap<Planet, Rasi>(Planet.class);
		
		for(int i = 0; i < 9; i ++){
		
			Rasi house = houses[i];
			planetHouse.put(Planet.ofIndex(i), house);
			planetLoc.put(Planet.ofIndex(i), house.ordinal() + 1);
			
		}
		
		planetHouse.put(Planet.Ascendant, houses[9]);
		planetLoc.put(Planet.Ascendant, houses[9].ordinal() + 1);
		
		return new PlanetChart(varga,planetLoc, planetHouse );
	}
	
	private static PlanetChart constructChart(Rasi ...houses){
		return constructChart(Varga.Rasi, houses);
	}
	*/
	
	@After
	public void tearDown() throws Exception {
		
		getLogger().writeToDisk();
        
        System.out.println(getSession().getGlobal("yogas"));
        getSession().dispose();
	}

	
}
