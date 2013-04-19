/**
 * MuhurthaTest.java
 Created On 2007, Nov 6, 2007 4:51:24 PM
 @author E. Rajasekar
 */

package app.astrosoft.test;

import java.util.GregorianCalendar;

import app.astrosoft.beans.Interval;
import app.astrosoft.beans.MuhurthaBean;
import app.astrosoft.consts.Nakshathra;
import app.astrosoft.consts.Rasi;
import app.astrosoft.core.Muhurtha;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableRowData;

public class MuhurthaTest {

	public static void main(String[] args) {
		
		Muhurtha m = new Muhurtha(new GregorianCalendar(2005, 11,01), Rasi.Mesha, Nakshathra.Bharani, true, true);
		m.calcMuhurtha();
		
		
		TableData<? extends TableRowData> nextTransitPeriods = m.getNextTransitPeriods();
		
		/*for(int i = 0; i < nextTransitPeriods.getRowCount(); i++){
			
			MuhurthaBean bean = (MuhurthaBean)nextTransitPeriods.getRow(i);
			
			Interval longs = bean.getLongitude();
			
			Interval period = bean.getPeriod();
			
			//System.out.println(longs + " --- " + period);
			
		}*/
		System.out.println(nextTransitPeriods);
		
		m.printFavLongitudes();
	}
}

