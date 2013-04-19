/**
 * DisplayFormat.java
 * Created On 2005, Dec 22, 2005 6:13:55 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

import java.util.EnumSet;

public enum DisplayFormat {
	FULL_NAME, SYMBOL, DEG, DATE, DATE_TIME;
	
	public static EnumSet intervalFormats(){
		return EnumSet.of(DEG, DATE, DATE_TIME);
	}
	
	public static void validateNameFormat(DisplayFormat format){
		
		if (!(format == FULL_NAME || format == SYMBOL)){
			throw new IllegalArgumentException("Invalid Name Display Format");
		}
	}
};
