/**
 * TimeDoubleConvTest.java
 Created On 2006, Oct 26, 2006 2:53:29 PM
 @author E. Rajasekar
 * 
 */

import java.math.BigDecimal;

import app.astrosoft.util.AstroUtil;

public class TimeDoubleConvTest {

	public static void main(String[] args) {
		
		double time = AstroUtil.decimal(4,36,1);
		System.out.println(time);
		System.out.println(AstroUtil.timeFormat(time));
		
		System.out.println(new BigDecimal(4.6).subtract(new BigDecimal(4)));
	}
}
