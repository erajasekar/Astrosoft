/**
 * TimeDoubleConvTest.java
 Created On 2006, Oct 26, 2006 2:53:29 PM
 @author E. Rajasekar
 * 
 */

import java.math.BigDecimal;

import app.astrosoft.util.AstroUtil;
import static app.astrosoft.util.AstroUtil.decimal;
import static app.astrosoft.util.AstroUtil.timeFormat;

public class TimeDoubleConvTest {

	public static void main(String[] args) {
		
		double time = decimal(4,36,1);
		System.out.println(time);
		System.out.println(timeFormat(time));
		
		System.out.println(new BigDecimal(4.6).subtract(new BigDecimal(4)));
	}
}
