/**
 * XpsTest.java
 * Created On 2007, Oct 16, 2007 2:13:32 PM
 * @author E. Rajasekar
 */

package app.astrosoft.xps.yoga.test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import app.astrosoft.consts.Varga;
import app.astrosoft.core.Horoscope;
import app.astrosoft.test.SampleHoroscopes;
import app.astrosoft.util.Timer;
import app.astrosoft.xps.XpsUtil;
import app.astrosoft.xps.beans.PlanetChart;
import app.astrosoft.xps.beans.YogaFacts;
import app.astrosoft.xps.yoga.YogaResults;
import static app.astrosoft.consts.Varga.*;

public class YogasTest {
	
	private static String RULE_FILE = "YogaCombinations.dslr";
	//private static String RULE_FILE = "astrosoft.drl";
	private static String DSL_FILE = "YogaCombinations.dsl";

	public static final void main(final String[] args) throws Exception {
	    
		final Package pkg = buildPackage();
		
		final RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );
        
        /*for (Rule r :pkg.getRules() ){
        	System.out.println(r.getLhs());
        }*/
       
        System.out.println("Raja -->" + findYogas(ruleBase, SampleHoroscopes.getMyHoroscope()));
        
        System.out.println("Elango -->" + findYogas(ruleBase, SampleHoroscopes.getElangoHoroscope()));
        
        System.out.println("Mani -->" + findYogas(ruleBase, SampleHoroscopes.getManiHoroscope()));
        
        System.out.println("Muthu -->" + findYogas(ruleBase, SampleHoroscopes.getMuthuHoroscope()));
        
        System.out.println("Suba -->" + findYogas(ruleBase, SampleHoroscopes.getSubaHoroscope()));
	}
	
	public static YogaResults findYogas(RuleBase ruleBase, Horoscope h){
		
		Timer t = new Timer();
		 
		final StatefulSession session = ruleBase.newStatefulSession();
	        
        registerListener(session);
        final WorkingMemoryFileLogger logger = new WorkingMemoryFileLogger( session );
        logger.setFileName( "C:/Astrosoft/helloworld" ); 
        
        
        loadFacts(h,session);
        
        session.fireAllRules();
        
        logger.writeToDisk();
        
        YogaResults yogas = (YogaResults) session.getGlobal("yogas");
        session.dispose();
	       
	    t.print("FindYoga Execution time for " + h.getPersonName());
	    
	    return yogas;
	         
	}

	private static void loadFacts(Horoscope h, StatefulSession session) {
		
		YogaResults yogas = new YogaResults();
		session.setGlobal("yogas", yogas);
		
		PlanetCharacter character = new PlanetCharacter(h.getPlanetaryInfo().getPlanetCharacter());
		
		YogaFacts facts = new YogaFacts();
		
		facts.setCharacter(character);
		
		facts.setPowerfulPlanets(h.getShadBala().getStrengthPer());
		
		session.setGlobal("$facts", facts);
		
		//session.insert(yogas);
		PlanetChart chart = new PlanetChart(Varga.Rasi, h.getPlanetaryInfo());
		
		/*Map<Planet,Integer> m = new HashMap<Planet,Integer>();
        m.put(Planet.Sun, 3);
        m.put(Planet.Moon, 2);
        m.put(Planet.Mars, 3);
        
        session.insert(m);*/
		session.insert(chart);
		
		
	}

	private static void registerListener(StatefulSession session) {
		session.addEventListener( new DebugAgendaEventListener() );
        session.addEventListener( new DebugWorkingMemoryEventListener() );
        
	}

	private static Package buildPackage() throws DroolsParserException, IOException {
		
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
}
