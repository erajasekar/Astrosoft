/**
 * TransitHelper.java
 * Created On 2006, May 4, 2006 6:31:40 PM
 * @author E. Rajasekar
 */

package app.astrosoft.util;

import java.util.Date;

import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;
import swisseph.TCPlanet;
import swisseph.TransitCalculator;
import app.astrosoft.consts.Planet;

public class TransitHelper {
	
	private static final int TRANSIT_FLAG = SweConst.SEFLG_TRANSIT_LONGITUDE | SweConst.SEFLG_SIDEREAL;

	private SwissEph sw;
	
	private TransitCalculator tc;
	
	TransitHelper(Planet planet, SwissEph sw) {
		
		this.sw = sw;
		tc = new TCPlanet(sw,planet.planetNo(),TRANSIT_FLAG,0);
		
		//System.out.println("Roll over-->" + tc.getRollover());
	}
	
	public double getTransit(double position, double startJDUT, boolean backwards){
		tc.setOffset(position);
		//System.out.println("Position " + position);
		return sw.getTransitUT(tc, startJDUT, backwards );
	}
	
	public double getTransit(double position, double startJDUT){
		
		return getTransit(position, startJDUT, false );
	}
	
	public Date getTransitDate(double position, double startJDUT){
		return SweDate.getDate(getTransit(position, startJDUT));
	}
}
