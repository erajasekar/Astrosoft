/**
 * MuhurthaHelper.java
 * Created On 2005, Oct 15, 2005 5:09:03 PM
 * @author Rajasekar E.
 */

package app.astrosoft.util;

import java.util.EnumMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.astrosoft.beans.Interval;
import app.astrosoft.beans.MuhurthaBean;
import app.astrosoft.consts.MuhurthaRank;
import app.astrosoft.consts.Planet;


/** 
 * Helper class for computing muhurtha.
 */
public class MuhurthaHelper {
	
	private static final Logger log = Logger.getLogger(MuhurthaHelper.class.getName());
	
	private EnumMap <MuhurthaRank, List<Interval>>  tLongitudes;
	
	private TransitHelper transitHelper;
	
	public MuhurthaHelper(EnumMap <MuhurthaRank, List<Interval>>longs){
		tLongitudes = longs;
		transitHelper = new SwissHelper().getTransitHelper(Planet.Moon);
	}
	
	public void setTransitLongitudes(EnumMap <MuhurthaRank, List<Interval>> longs) {
		tLongitudes = longs;
	}
	
    //TODO: Make this to run in seperate thread.
	public List<MuhurthaBean> getTransists(Interval period) {

		List<MuhurthaBean> mTransists = new java.util.ArrayList<MuhurthaBean>();
		
		for(MuhurthaRank r : tLongitudes.keySet()) {
			
			for(Interval tLongitude : tLongitudes.get(r)) {
			
				double startJT = period.getStart();
				double endJT = period.getEnd();
				
				//System.out.println("S -> " + SweDate.getDate(startJT));
				//System.out.println("E -> "+ SweDate.getDate(endJT));
				
				while (startJT <= endJT) {
					
					//System.out.println("Period " + new Interval(startJT,endJT));
					//System.out.println("startJT " + startJT);
					//System.out.println("endJT " + endJT);
					
					
					try {
					/*tc.setOffset(tLongitude.getStart());
					double transitStart = sw.getTransitUT(tc, startJT, false);
					tc.setOffset(tLongitude.getEnd());
					double transitEnd = sw.getTransitUT(tc, transitStart, false);*/
					
					//System.out.println("tLongitude.getStart() " + tLongitude);
						
					double transitStart = transitHelper.getTransit(tLongitude.getStart(), startJT);
					double transitEnd = transitHelper.getTransit(tLongitude.getEnd(), transitStart);
					
					//System.out.println("transitStart " + transitStart + " - " + tLongitude.getStart());
					//System.out.println("transitEnd " + transitEnd+ " - " + tLongitude.getEnd());
                                
					if (transitStart <= endJT ) {
						MuhurthaBean mTransit = new MuhurthaBean(new Interval(transitStart, transitEnd),tLongitude , r);
						mTransists.add(mTransit);
					}
					
					startJT = transitEnd ;//+ 0.1;
					}catch(swisseph.SwissephException ex){
						log.log(Level.SEVERE, "Exception in calculating transists " , ex);
					};
					
				}
				
			}
		}
		
		
		return mTransists;
	}

}
