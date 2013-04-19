/*** HelloWorldExample.java
	 Created On 2007, Oct 15, 2007 6:54:02 PM
	 @author E. Rajasekar
**/


package app.astrosoft.xps;


import java.io.InputStreamReader;
import java.io.Reader;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.audit.WorkingMemoryFileLogger;
import org.drools.common.DefaultAgenda;
import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageBuilderConfiguration;
import org.drools.event.DebugAgendaEventListener;
import org.drools.event.DebugWorkingMemoryEventListener;
import org.drools.event.DefaultAgendaEventListener;
import org.drools.event.DefaultWorkingMemoryEventListener;
import org.drools.rule.Package;
import org.drools.rule.builder.dialect.java.JavaDialectConfiguration;

import app.astrosoft.consts.Planet;
import app.astrosoft.xps.beans.PlanetLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;;

/**
 * This is a sample file to launch a rule package from a rule source file.
 */
public class HelloWorldExample {

	
    public static final void main(final String[] args) throws Exception {
    	
    	//read in the source
        final Reader source = new InputStreamReader( HelloWorldExample.class.getResourceAsStream( "/resources/rules/test.drl" ) );

        Properties properties = new Properties();
        properties.setProperty( "drools.dialect.java.languageLevel",
                                "1.5" );
        
        PackageBuilderConfiguration cfg = new PackageBuilderConfiguration( properties );
        
        JavaDialectConfiguration javaConf = (JavaDialectConfiguration) cfg.getDialectConfiguration( "java" );
        System.out.println(javaConf.getJavaLanguageLevel());
        
        final PackageBuilder builder = new PackageBuilder(cfg);

        
        //this wil parse and compile in one step
        builder.addPackageFromDrl( source );
        
        // Check the builder for errors
        if ( builder.hasErrors() ) {
            System.out.println( builder.getErrors().toString() );
            throw new RuntimeException( "Unable to compile \"HelloWorld.drl\".");
        }

        //get the compiled package (which is serializable)
        final Package pkg = builder.getPackage();

        //add the package to a rulebase (deploy the rule package).
        final RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );

        final StatefulSession session = ruleBase.newStatefulSession();
        
        session.addEventListener( new DebugAgendaEventListener() );
        session.addEventListener( new DebugWorkingMemoryEventListener() );
        
        final WorkingMemoryFileLogger logger = new WorkingMemoryFileLogger( session );
        logger.setFileName( "C:/Astrosoft/helloworld" );        

        /*final Message message = new Message();
        message.setMessage( "Hello World" );
        message.setStatus( Message.STATUSVALS.HELLO );
        session.insert( message );*/
        
        List<String> l = new ArrayList<String>();
        
        l.add("a");
        l.add("b");
        l.add("c");
        
        session.insert(l);
        
        session.insert(Planet.Sun);
        
        /*Map<Planet, Integer> m = new EnumMap<Planet, Integer>(Planet.class);
        
        Map<Planet,Integer> m = new HashMap<Planet,Integer>();
        m.put(Planet.Sun, 1);
        m.put(Planet.Moon, 2);
        m.put(Planet.Mars, 3);
        
        session.insert(m);*/
        
        PlanetLocation p1 = new PlanetLocation(Planet.Sun, 1);
        PlanetLocation p2 = new PlanetLocation(Planet.Moon, 2);
        PlanetLocation p3 = new PlanetLocation(Planet.Mars, 3);
        
        session.insert(p1);
        session.insert(p2);
        session.insert(p3);
        
        
        session.fireAllRules();
        
        logger.writeToDisk();
        
        session.dispose();
    }

    public static class Message {
        //public static final int HELLO   = 0;
        //public static final int GOODBYE = 1;
        
        public static enum STATUSVALS {HELLO,GOODBYE};

        private String          message;

        private STATUSVALS             status;

        public Message() {

        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(final String message) {
            this.message = message;
        }

        public STATUSVALS getStatus() {
            return this.status;
        }

        public void setStatus(final STATUSVALS status) {
            this.status = status;
        }
        
        @Override
        public String toString() {
        	
        	return "[ " + message + " , " + status + " ]";
        }
    }

}

