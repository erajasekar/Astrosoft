/**
 * XpsUtil.java
 * Created On 2007, Oct 15, 2007 10:11:49 PM
 * @author E. Rajasekar
 */

package app.astrosoft.xps;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XpsUtil {
	
	private static final Logger log = Logger.getLogger(XpsUtil.class.getName());

	private static String RULE_PATH="/resources/rules/";
	
	//private static String RULE_PATH="c:/AstroSoft/resources/rules/";
		
	public static InputStream getRuleAsStream(String ruleFile){
		
		/*try {
			InputStream is = new FileInputStream(new File(RULE_PATH + ruleFile));
			return is;
		}catch (Exception e) {
			log.log(Level.SEVERE, "Exception in reading file " , e);
			return null;
		}*/
		return XpsUtil.class.getResourceAsStream( RULE_PATH + ruleFile);
	}
}
