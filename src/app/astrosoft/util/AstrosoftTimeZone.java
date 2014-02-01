/**
 * AstrosoftTimeZone.java
 * Created On 2006, Mar 10, 2006 2:22:11 PM
 * @author E. Rajasekar
 */

package app.astrosoft.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import app.astrosoft.consts.AstroConsts;
import app.astrosoft.ui.AstroSoft;
import static app.astrosoft.util.AstroUtil.todegmin;
import static java.lang.System.currentTimeMillis;
import static java.util.TimeZone.getAvailableIDs;

public class AstrosoftTimeZone {

	String tzId;
	
	public AstrosoftTimeZone(String timeZoneId) {
		tzId = timeZoneId;
	}
	
	public double offset(){
		return offset(this.tzId);
	}
	
	public String id(){
		return tzId;
	}
	
	public TimeZone getTimeZone(){
		return TimeZone.getTimeZone(tzId);
	}
	
	public static double offset(String tzId){
		TimeZone tz = TimeZone.getTimeZone(tzId);
		return ((double)tz.getOffset(new GregorianCalendar(tz).getTimeInMillis())) / (AstroConsts.MILLIS_IN_HR);
	}
	
	@Override
	public String toString() {
		double offset = offset();
		String sign = offset < 0 ? "-" : "+";
		return tzId + " (GMT " + sign +  todegmin(offset(), ":", true) + ")";
	}
	
	public static AstrosoftTimeZone[] availableTimeZones(){
		String ids[] = getAvailableIDs();
		AstrosoftTimeZone tzs[] = new AstrosoftTimeZone[ids.length];
		
		int i = 0;
		for(String id : ids){
			tzs[i++] = new AstrosoftTimeZone(id);
		}
		return tzs;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		//System.out.println( "Equals tzId -> " + System.currentTimeMillis());
		return ((this == obj) || (tzId.equals(((AstrosoftTimeZone)obj).id())));
	}
	
	@Override
	public int hashCode() {
		System.out.println( "Hashcode tzId -> " + currentTimeMillis());
		return tzId.hashCode();
	}
	
	public static void main(String[] args) {
		
		for(AstrosoftTimeZone tz : availableTimeZones()){
			System.out.println(tz);
		}
	}
}
