/**
 * YogaFinder.java
 * Created On 2007, Nov 15, 2007 4:10:53 PM
 * @author E. Rajasekar
 */

package app.astrosoft.xps.yoga;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.event.DebugAgendaEventListener;
import org.drools.event.DebugWorkingMemoryEventListener;
import org.drools.rule.Package;

import app.astrosoft.consts.Varga;
import app.astrosoft.core.Horoscope;
import app.astrosoft.exception.AstrosoftException;
import app.astrosoft.test.SampleHoroscopes;
import app.astrosoft.util.Timer;
import app.astrosoft.xps.XpsUtil;
import app.astrosoft.xps.beans.PlanetChart;
import app.astrosoft.xps.beans.YogaFacts;

public class YogaFinder {
	
	private static final Logger log = Logger.getLogger(YogaFinder.class.getName());

	private static String RULE_FILE = "YogaCombinations.dslr";
	private static String DSL_FILE = "YogaCombinations.dsl";
	
	private static String PACKAGE_FILE = "YogaCombinations.pkg";
	
	private RuleBase ruleBase;
	
	private static YogaFinder  instance = null;
	
	private YogaFinder() {
		
		try {
			
			Timer t = new Timer();
			//final Package pkg = buildPackage();
			
			final Package pkg = deserializePackage();
			ruleBase = RuleBaseFactory.newRuleBase();
	        ruleBase.addPackage( pkg );
	        
	        t.print("Time taken for creating rule base ");
	        
		}catch (Exception e) {
			log.log(Level.SEVERE, "Exception in initializing rules " ,e);
			throw new AstrosoftException("Exception in initializing rules " ,e);
		}
		
	}
	
	public static YogaFinder getInstance(){
		
		if (instance == null){
			instance = new YogaFinder();
		}
		return instance;
	}
	
	public YogaResults findYogas(Horoscope h){
		
		Timer t = new Timer();
		 
		final StatefulSession session = ruleBase.newStatefulSession();
	    
		if (log.isLoggable(Level.FINE)){
			registerListener(session);
		}
		
		//final WorkingMemoryFileLogger logger = new WorkingMemoryFileLogger( session );
        
        loadFacts(h,session);
        
        session.fireAllRules();
        
        //logger.writeToDisk();
        
        YogaResults yogas = (YogaResults) session.getGlobal("yogas");
        session.dispose();
	       
        t.print("FindYoga Execution time for " + h.getPersonName());
	    
	    return yogas;
	}
	
	private static void loadFacts(Horoscope h, StatefulSession session) {
		
		YogaResults yogas = new YogaResults();
		session.setGlobal("yogas", yogas);
		
		YogaFacts facts = new YogaFacts(h);
		
		session.setGlobal("$facts", facts);
		
		PlanetChart chart = new PlanetChart(Varga.Rasi, h.getPlanetaryInfo());
		
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

		
		builder.addPackageFromDrl( rule,dsl );
		
		// Check the builder for errors
        if ( builder.hasErrors() ) {
            log.severe("Compilation Errors in rule : " + builder.getErrors().toString() );
            throw new AstrosoftException( "Compilation Errors in " + RULE_FILE);
        }

        return builder.getPackage();
	}
	
	public void serializePackage(){
		
		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream(PACKAGE_FILE));
			os.writeObject(buildPackage());
		} catch (Exception e) {
			log.log(Level.SEVERE,"Exception in Serializing Package");
		}
		
	}
	
	private static Package deserializePackage(){
		
		Package pkg = null;
		
		try {

			ObjectInputStream in = new ObjectInputStream(XpsUtil.getRuleAsStream(PACKAGE_FILE));
			pkg = (Package) in.readObject();
		}catch(Exception e){
			log.log(Level.SEVERE,"Exception in Serializing Package");
			throw new AstrosoftException("Exception in Serializing Package", e);
		}
		return pkg;
	}
	
	public static void main(String[] args) {
		
		//YogaFinder finder = YogaFinder.getInstance();
		
		//finder.serializePackage();
		
		//YogaResults yogas = finder.findYogas(SampleHoroscopes.getMyHoroscope());
		//System.out.println("Yogas Found --> " + yogas);
		
		
		//Horoscope h = SampleHoroscopes.getMyHoroscope();
		
		//System.out.println(h);
		//System.out.println("Yogas Found --> " + h.getYogaCombinations());

		// Ignore first construction 
		Horoscope h = SampleHoroscopes.getMyHoroscope();
		
		Timer t = new Timer();
		h = SampleHoroscopes.getMyHoroscope();
		t.print("Default Horoscope construction time ");
		
		t.reset();
		h = SampleHoroscopes.getMyHoroscope();
		h.calculateAll();
		t.print("Full Horoscope construction time ");
		
		
	}
	
}

