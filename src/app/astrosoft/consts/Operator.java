/**
 * Operator.java
 * Created On 2007, May 10, 2007 4:41:59 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

import java.util.EnumSet;

public enum Operator {
	OR,
	AND;
	
	public static EnumSet<Operator> logical(){
		return EnumSet.of(OR,AND);
	}
	
}
