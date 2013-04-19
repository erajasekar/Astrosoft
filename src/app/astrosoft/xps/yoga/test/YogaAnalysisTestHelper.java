/**
 * YogaAnalysisTestHelper.java
 * Created On 2007, Oct 27, 2007 7:56:59 PM
 * @author E. Rajasekar
 */
package app.astrosoft.xps.yoga.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.audit.WorkingMemoryFileLogger;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.event.DebugAgendaEventListener;
import org.drools.event.DebugWorkingMemoryEventListener;
import org.drools.rule.Package;
import org.drools.rule.Rule;

import app.astrosoft.beans.PlanetCharacter;
import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.Varga;
import app.astrosoft.consts.YogaCombination;
import app.astrosoft.core.Horoscope;
import app.astrosoft.test.SampleHoroscopes;
import app.astrosoft.xps.XpsUtil;
import app.astrosoft.xps.beans.PlanetChart;
import app.astrosoft.xps.beans.YogaFacts;
import app.astrosoft.xps.yoga.YogaResults;
import static app.astrosoft.util.CollectionUtil.newEnumMap;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestCase.*;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.YOGAS;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.buildPackage;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.getLogger;
import static app.astrosoft.xps.yoga.test.YogaAnalysisTestHelper.getSession;

public class YogaAnalysisTestHelper {
	
	public static enum SAMPLE_HOROSCOPE {RAJA,SUBA};
	
	private static PlanetCharacter character1;
	
	private static PlanetCharacter character2;
	
	private static String RULE_FILE = "YogaCombinations.dslr";
	//private static String RULE_FILE = "astrosoft.drl";
	private static String DSL_FILE = "YogaCombinations.dsl";
	
	public static final String YOGAS = "yogas";
	
	private static RuleBase ruleBase;
	
	private static StatefulSession session;
	
	private static Package pkg;
	
	private static WorkingMemoryFileLogger logger;
	
	private static Map<SAMPLE_HOROSCOPE, Horoscope> samples;
	
	public static Package buildPackage() throws DroolsParserException, IOException {
		
		final Reader rule = new InputStreamReader( XpsUtil.getRuleAsStream(RULE_FILE));
		final Reader dsl = new InputStreamReader( XpsUtil.getRuleAsStream(DSL_FILE));
		
		final PackageBuilder builder = new PackageBuilder();
	
		
		//builder.addPackageFromDrl( rule );
		
		builder.addPackageFromDrl( rule,dsl );
		
		// Check the builder for errors
	    if ( builder.hasErrors() ) {
	        System.out.println( builder.getErrors().toString() );
	        throw new RuntimeException( "Compilation Errors in " + RULE_FILE);
	    }
	
	    return builder.getPackage();
	    
	}
	
	public static void setUpBeforeClass() throws Exception {
		
		System.out.println("setUpBeforeClass()");
		
		pkg = buildPackage();
		
		ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );
        
        setUpHoroscope();
        //setUpChart();
        setUpCharacter();
	}
	
	private static void setUpCharacter() {
		
		Map<Planet, Boolean> c1 = new EnumMap<Planet, Boolean>(Planet.class);
		Map<Planet, Boolean> c2 = new EnumMap<Planet, Boolean>(Planet.class);
		
		for(Planet p : Planet.subaPlanets()){
			c1.put(p, true);
			c2.put(p, true);
		}
		for(Planet p : Planet.papaPlanets()){
			c1.put(p, false);
			c2.put(p, false);
		}
		
		c1.put(Planet.Moon, true);
		c2.put(Planet.Moon, false);
		
		character1 = new PlanetCharacter(c1);
		character2 = new PlanetCharacter(c2);
		
	}

	private static void setUpHoroscope() {
		
		samples = new EnumMap<SAMPLE_HOROSCOPE, Horoscope>(SAMPLE_HOROSCOPE.class);
		
		Horoscope h = SampleHoroscopes.getMyHoroscope();
		samples.put(SAMPLE_HOROSCOPE.RAJA, h);
		
		samples.put(SAMPLE_HOROSCOPE.SUBA, SampleHoroscopes.getSubaHoroscope());
	}
	
	public static void setUp(String ruleName) throws Exception {
		
		filterRule(ruleName);
		printRules();
		
		session = ruleBase.newStatefulSession();
        registerListener(session);
        logger = new WorkingMemoryFileLogger( session );
        logger.setFileName( "C:/Astrosoft/yogaanalysis" ); 
        
        YogaResults yogas = new YogaResults();
		session.setGlobal(YOGAS, yogas);
		
		
		
	}
	
	/*public static void setUp(String ruleName) throws Exception {
		
		setUp(ruleName,character1);
	}*/
	
	private static void registerListener(StatefulSession session) {
		session.addEventListener( new DebugAgendaEventListener() );
        session.addEventListener( new DebugWorkingMemoryEventListener() );
	}

	public static void filterRule(String ruleName) throws Exception{
		
		pkg = buildPackage();
		
		Rule[] rules = pkg.getRules();
		
		for(Rule rule:rules ){
			if (!rule.getName().equals(ruleName)){
				//System.out.println("Removing " + rule.getName());
				pkg.removeRule(pkg.getRule(rule.getName()));
				//ruleBase.removeRule(pkg.getName(), rule.getName());
			}
		}
		
		ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );
	}

	public static void testYogaPresent(YogaCombination yoga, String efficacy){
		
		YogaResults yogas = (YogaResults) session.getGlobal(YOGAS);
		assertTrue(yogas.getYogas().contains(new YogaResults.Result(yoga,efficacy)));
	}
	
	public static void testYogaPresent(YogaCombination yoga){
		testYogaPresent(yoga,"100%");
	}
			
	public static void testYogaNotPresent(YogaCombination yoga){
		
		YogaResults yogas = (YogaResults) session.getGlobal(YOGAS);
		assertFalse(yogas.hasYogaCombination(yoga));
	}

	public static PlanetChart constructChart(Varga varga, Map.Entry<Planet, Rasi> ...entries){
		
		Map<Planet,Rasi> planetHouses = newEnumMap(Planet.class, entries);
		return new PlanetChart(varga,planetHouses);
	}
	
	public static PlanetChart constructChart(Map.Entry<Planet, Rasi> ...entries){
		return constructChart(Varga.Rasi,entries);
	}
	
	public static void loadFacts(SAMPLE_HOROSCOPE sample){
		
		Horoscope h = samples.get(sample);
		
		YogaFacts facts = new YogaFacts(h);
		
		session.setGlobal("$facts", facts);
		
		PlanetChart chart = new PlanetChart(Varga.Rasi, h.getPlanetaryInfo());
		
		session.insert(chart);
		
		
	}
	
	public static void loadFacts(PlanetChart chart, PlanetCharacter character, Set<Planet> powerfulPlanets){
		
		YogaFacts facts = new YogaFacts();
		
		facts.setCharacter(character);
		
		//if (powerfulPlanets != null) {
			facts.setPowerfulPlanets(powerfulPlanets);
		//}
		
		session.setGlobal("$facts", facts);
		
		session.insert(chart);
	}
	
	public static void loadFacts(PlanetChart chart){
		loadFacts(chart,character1,null);
	}
	
	public static void loadFacts(PlanetChart chart,PlanetCharacter character){
		loadFacts(chart,character,null);
	}
	
	public static void loadFacts(PlanetChart chart,Set<Planet> powerfulPlanets){
		loadFacts(chart,character1,powerfulPlanets);
	}
	
	public static void loadFacts(PlanetChart chart, YogaFacts facts){
		session.setGlobal("$facts", facts);
		session.insert(chart);
	}
	
	public static void tearDown() throws Exception {
		
		logger.writeToDisk();
        
        System.out.println(getSession().getGlobal("yogas"));
        session.dispose();
	}
	
	public static void printRules(){
		
		System.out.println("Rules: ");
		for(Rule rule:ruleBase.getPackage(pkg.getName()).getRules() ){
			
			//rule.getLhs().;
			System.out.println(rule.getName());
			//System.out.println(rule.getLhs().getChildren());
		}
	}
	
	public static StatefulSession getSession() {
		return session;
	}
	
	public static WorkingMemoryFileLogger getLogger() {
		return logger;
	}
	
	public static PlanetCharacter getCharacter1() {
		return character1;
	}
	
	public static PlanetCharacter getCharacter2() {
		return character2;
	}
	
}
