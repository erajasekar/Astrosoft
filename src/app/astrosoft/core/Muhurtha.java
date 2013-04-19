/**
 * Muhurtha.java
 * Created On 2005, Oct 15, 2005 4:02:12 PM
 * @author Rajasekar E.
 */

package app.astrosoft.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.List;

import swisseph.SweDate;
import app.astrosoft.beans.Interval;
import app.astrosoft.beans.MuhurthaBean;
import app.astrosoft.consts.DisplayFormat;
import app.astrosoft.consts.MuhurthaRank;
import app.astrosoft.consts.Nakshathra;
import app.astrosoft.consts.Rasi;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableDataFactory;
import app.astrosoft.ui.table.TableRowData;
import app.astrosoft.util.AstroUtil;
import app.astrosoft.util.MuhurthaHelper;

/** Class for muhurtha calculations */

public class Muhurtha {

	private double mDate;

	private double startJulDay;

	private double endJulDay;

	private Rasi mRasi;

	private Nakshathra mNak;

	// Default value 2 months
	private int incPeriod = 2;

	private boolean filterByRasi = false;

	private boolean filterByMuhurthaNaks = false;

	private EnumMap <MuhurthaRank, List<Interval>> favLogitudes;

	private List<Interval> transitPeriods;

	public Muhurtha(Calendar date, Rasi rasi, Nakshathra nak){

		//TODO Remove if muthurtha should start from today
		date.add(Calendar.DATE, -2);
		double muhurthaTime = AstroUtil.decimal(date.get(Calendar.HOUR), date.get(Calendar.MINUTE), date.get(Calendar.SECOND));// - AstroSoft.getPreferences().getPlace().timeZone();
		SweDate sd = new SweDate(date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, date.get(Calendar.DATE), muhurthaTime);

		mDate = sd.getJulDay();
		startJulDay = mDate;

		mRasi = rasi;
		mNak = nak;
	}

	public Muhurtha(Calendar date, Rasi rasi, Nakshathra nak, boolean filterByRasi, boolean filterByMuhurthaNaks){

		this(date, rasi, nak);
		this.filterByRasi = filterByRasi;
		this.filterByMuhurthaNaks = filterByMuhurthaNaks;
	}

   public Muhurtha(Calendar date, Rasi rasi, Nakshathra nak, boolean filterByRasi, boolean filterByMuhurthaNaks, int incPeriod){

		this(date, rasi, nak, filterByRasi,filterByMuhurthaNaks);
        this.incPeriod = incPeriod;
	}

	public void setIncPeriod(int months) {

		incPeriod = months;
	}

	public void calcMuhurtha(){

		calcFavLongitudes();
		//getNextTransitPeriods();
		//getNextTransitPeriods();
	}

	private List<Interval> calcFavNakLongitudes() {

		List<Interval> favNakLongitudes =  new java.util.ArrayList <Interval> ();

		int [] favNakPos = {1, 3, 5, 7, 8, 10, 12, 14, 16, 17, 19, 21, 23, 25, 26};

		for(int i: favNakPos) {

			favNakLongitudes.add(Nakshathra.degFor(mNak.absolute(i)));
		}

		//System.out.println("Fav Nak Longitudes: " + favNakLongitudes);

		return favNakLongitudes;
	}

	private List<Interval> calcFavRasiLongitudes() {

		List<Interval> favRasiLongitudes =  new java.util.ArrayList <Interval> ();

		int [] favRasiPos = {0,2,6,9,10};

		for(int i: favRasiPos) {

			favRasiLongitudes.add(Rasi.longitudeForRasi(mRasi.absolute(i)));
		}

		//System.out.println("Fav Rasi Longitudes: " + favRasiLongitudes);

		return favRasiLongitudes;
	}

	private List<Interval> calcFavMuhurthaLongitudes(MuhurthaRank rank) {

		List<Interval> favMuhurthaLongitudes =  new java.util.ArrayList <Interval> ();

		for(Nakshathra nak : Nakshathra.muhurtaNakshathras(rank)) {
			favMuhurthaLongitudes.add(Nakshathra.degFor(nak));
		}

		//System.out.println("Fav Muhurtha Longitudes with rank: " + rank + " - " + favMuhurthaLongitudes);

		return favMuhurthaLongitudes;
	}

	private void calcFavLongitudes(){

		favLogitudes = new EnumMap<MuhurthaRank, List<Interval>> (MuhurthaRank.class);

		List<Interval> favNakLongitudes = calcFavNakLongitudes();
		favLogitudes.put(MuhurthaRank.VeryGood, favNakLongitudes);

		if (filterByRasi){
			favLogitudes.put(MuhurthaRank.VeryGood, Interval.intersection(favNakLongitudes, calcFavRasiLongitudes()));
		}

		//System.out.println("Fav Longitudes after rasi filter ->" + favLogitudes);
        List<Interval> rasiFilteredfavNakLongitudes =  favLogitudes.get(MuhurthaRank.VeryGood);
        
		if (filterByMuhurthaNaks){

			for(MuhurthaRank r :  MuhurthaRank.values()){

				List<Interval> favMuhurthaLongitudes = calcFavMuhurthaLongitudes(r);
				favLogitudes.put(r,
								Interval.intersection(rasiFilteredfavNakLongitudes,
								favMuhurthaLongitudes));
			}
		}

		//System.out.println("Fav Longs: " + favLogitudes);
	}


	public TableData<? extends TableRowData> getNextTransitPeriods() {

		MuhurthaHelper muhurthaHelper = new MuhurthaHelper(favLogitudes);

		endJulDay = AstroUtil.incJulDate(startJulDay, 0, incPeriod, 0);

		Interval period = new Interval(startJulDay, endJulDay);
		List <MuhurthaBean>muhurthaList = muhurthaHelper.getTransists(period);
		//System.out.println(muhurthaList);

		//Collections.sort(muhurthaList, MuhurthaBean.getComparator(AstrosoftTableColumn.Period), false);

		//System.out.println(muhurthaList);

		//Collections.sort(muhurthaList, MuhurthaBean.getComparator(AstrosoftTableColumn.Rank));

		//System.out.println(muhurthaList);
		startJulDay = endJulDay;

		return TableDataFactory.getTableData(muhurthaList);
	}

	public void printFavLongitudes(){
		
		for(MuhurthaRank r: favLogitudes.keySet()){
			
			System.out.println("************** " + r + " ****************");
			for(Interval i : favLogitudes.get(r)){
				System.out.println(i.toString(DisplayFormat.DEG));
			}
			System.out.println("*****************************************");
		}
		
		
	}
	public static void main(String[] args) {

		//GregorianCalendar today = new GregorianCalendar(2005, 11,01);
		//Muhurtha m = new Muhurtha(AstroSoft.today, Rasi.Mesha, Nakshathra.Mrigasira, false, true);
		Muhurtha m = new Muhurtha(new GregorianCalendar(2005, 11,01), Rasi.Vrishabha, Nakshathra.Rohini, false, true);
		m.calcMuhurtha();
		m.printFavLongitudes();
		System.out.println(m.getNextTransitPeriods());
		
		/*EnumMap <MuhurthaRank, List<Interval>>  longs = new EnumMap(MuhurthaRank.class);
		List<Interval> l = new ArrayList<Interval>();
		
		l.add(new Interval((30.0/2.25),(30.0/2.25) ));
		
		longs.put(MuhurthaRank.VeryGood, l);
		
		MuhurthaHelper helper = new MuhurthaHelper(longs);
		
		m.endJulDay = AstroUtil.incJulDate(m.startJulDay, 0, m.incPeriod, 0);

		Interval period = new Interval(m.startJulDay, m.endJulDay);
		
		System.out.println(helper.getTransists(period));*/
		}
}
