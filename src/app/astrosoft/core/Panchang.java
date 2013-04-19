/**
*
*  Panchang.java
*
* Created on February 24, 2003, 11:54 AM
*
* @author      E. Rajasekar
*
*/
package app.astrosoft.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;

import app.astrosoft.beans.ChartData;
import app.astrosoft.beans.Place;
import app.astrosoft.beans.PlanetChartData;
import app.astrosoft.beans.PlanetaryInfo;
import app.astrosoft.consts.AstroConsts;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.Ayanamsa;
import app.astrosoft.consts.DisplayConsts;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.Karana;
import app.astrosoft.consts.Nakshathra;
import app.astrosoft.consts.Paksha;
import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.Thithi;
import app.astrosoft.consts.Varga;
import app.astrosoft.consts.WeekDay;
import app.astrosoft.consts.Yoga;
import app.astrosoft.export.Exportable;
import app.astrosoft.export.Exporter;
import app.astrosoft.pref.AstrosoftPref;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.table.DefaultColumnMetaData;
import app.astrosoft.ui.table.DefaultTable;
import app.astrosoft.ui.table.MapTableRow;
import app.astrosoft.ui.table.MapTableRowHelper;
import app.astrosoft.ui.table.Table;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableDataFactory;
import app.astrosoft.util.AstroUtil;
import app.astrosoft.util.Mod;
import app.astrosoft.util.SwissHelper;
import app.astrosoft.util.TransitHelper;

import swisseph.*;


public class Panchang {

    private static final AstrosoftPref preferences = AstroSoft.getPreferences();
	private static final double PAN_APPROXIMATION = 0.01;
    
	private SwissHelper swissHelper;
	private Place place;
	private double moon;
	private double sun;
	private double totalSpeed;
    private double diffSpeed;
	private Calendar cal;
	private WeekDay weekday;
	private double sunrise;
	private double sunset;
	private double panTime;
	private double timeZone;
    private PanEvent nakshathra;
    private PanEvent thithi;
    private PanEvent yoga;
    private KaranaEvent karana;
    private Paksha pak;
    
    private EnumMap<Planet, Integer> rasi;
    private EnumMap<Planet, Boolean> dir;
    
    private static DefaultColumnMetaData panchangColumnMetaData;
	
	public static final int AUS_TIME_ROW = 9;

    /** Creates a new instance of Panchang */
    public Panchang(Calendar cal ) {

        this.cal = AstroUtil.getCalendar(cal.getTime());
        calcPanchang();
    }
    
    public Panchang(Date date) {
    	cal = AstroUtil.getCalendar(date); 
    	calcPanchang();
	}

    /*public void setAyanamsa( Ayanamsa ayanamsa ) {

    	sw.swe_set_sid_mode( ayanamsa.ayaValue(), 0.0, 0.0 );      
        calcPanchang(  );

    }
    
    private void setPanCalcTime(double time) {
		
    	refTime = time;
    	panTime = refTime - this.timeZone; //  For 12.00 A.M
    	calcPanchang();
	}*/

    private void calcPlanetaryInfo(  ) {

    	place = preferences.getPlace();
    	
    	timeZone = ((double)cal.getTimeZone().getRawOffset() / AstroConsts.MILLIS_IN_HR) + (cal.get(Calendar.DST_OFFSET) / AstroConsts.MILLIS_IN_HR) ;
    	place.setTimeZone(timeZone);
    	panTime = preferences.getPanCalcTime() - timeZone;
    	
    	//System.out.println("TZ " + timeZone);
    	    	
    	swissHelper = new SwissHelper(new SweDate( cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), panTime ));
    	
    	EnumMap<Planet, Double> planetPosition = swissHelper.getPlanetaryPosition();
    	sun = planetPosition.get(Planet.Sun);
    	moon = planetPosition.get(Planet.Moon);
    	rasi = PlanetaryInfo.positionToRasiNum(planetPosition);
    	rasi.put(Planet.Ascendant, swissHelper.getAscendant(place.longitude(), place.latitude()).ordinal() + 1);
    	
    	dir = swissHelper.getPlanetDirection();
    	
    	
    	double moonSpeed = swissHelper.getPlanetSpeed(Planet.Moon);
    	double sunSpeed = swissHelper.getPlanetSpeed(Planet.Sun);
    	
    	totalSpeed = sunSpeed + moonSpeed;
    	diffSpeed = moonSpeed - sunSpeed;
    	
    }

    private void calcPanchang(  ) {

    	calcPlanetaryInfo(  );
    	
    	TransitHelper transitHelper = swissHelper.getTransitHelper(Planet.Moon);
    	
    	Date nakEndDate = transitHelper.getTransitDate(AstroUtil.nakEndPosition(moon), swissHelper.getSweDate().getJulDay());
    	
    	double nakEndTime = AstroUtil.dateToTimeDouble(nakEndDate);
    	
    	if (((nakEndDate.getTime() - cal.getTimeInMillis()) / AstroConsts.MILLIS_IN_DAY) > 1){
    		nakEndTime = nakEndTime + 24.00;
    	}

    	nakshathra = new PanEvent(Nakshathra.ofDeg(moon).toString(), nakEndTime);
    	
    	thithi = new PanEvent(Thithi.ofDeg( sun, moon ).toString(), findThithiEnd( sun, moon, diffSpeed ));
    	
        yoga = new PanEvent(Yoga.ofDeg( sun , moon ).toString(), findYogaEnd( ( sun + moon ) % 360, totalSpeed ));
        
        karana = calcKarana();

        pak = Paksha.ofDeg( sun, moon );       
        weekday = WeekDay.ofCalendar( cal );
        sunrise = AstroUtil.getSunRise( cal, place );
        sunset = AstroUtil.getSunSet( cal, place );
    }

    private KaranaEvent calcKarana() {
		
    	KaranaEvent karanaEvent;
    	
    	Karana[] karanas = Karana.ofDeg(sun, moon);
		
		double position = getMoonSunDiff(sun, moon);
		double time = 0;
		double firstTime = 0;
		double secondTime = 0;
		
		double firstBal = AstroConsts.karanaLength - ( position % AstroConsts.karanaLength );
		double expected = (position + firstBal) % 180;
		
		//System.out.println("Firstbal " + firstBal  );
		//System.out.println("expected " + expected  );
		
		if (expected % AstroConsts.thithiLength == 0){
			
			firstTime = thithi.getEnding();
			
			double secondBal = firstBal + AstroConsts.karanaLength;
	        time = AstroUtil.endTime( diffSpeed, secondBal );
	        secondTime = time + timeZone + computeCorrection(position + AstroConsts.karanaLength, expected + AstroConsts.karanaLength, time, AstroConsts.karanaLength);
	        
		}else {
			time = AstroUtil.endTime( diffSpeed, firstBal );
	        firstTime = time + timeZone + computeCorrection(position, expected, time, AstroConsts.karanaLength);
	        secondTime = thithi.getEnding();
		}
		
		karanaEvent = new KaranaEvent(karanas[0].toString(),firstTime, karanas[1].toString(), secondTime );
		
		return karanaEvent;
	}

	private double findYogaEnd( double position, double speed ) {

        double bal = AstroConsts.yogaLength - ( position % AstroConsts.yogaLength );
        double time = AstroUtil.endTime( speed, bal );
        return time + timeZone + yogaCorrection(position, position + bal, time);
    }
    
    private double yogaCorrection(double position, double expected, double caltime){
    	
    	double correction = 0.0;
    	 
    	SwissHelper swissHelper = new SwissHelper(new SweDate( cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), caltime));
 	    
 	    EnumMap<Planet, Double> planetPosition = swissHelper.getPlanetaryPosition(EnumSet.of(Planet.Sun, Planet.Moon));
 		
 	    double newmoon = planetPosition.get(Planet.Moon);
 	    double newsun = planetPosition.get(Planet.Sun);
 	    
 	    double newposition = (newsun + newmoon) % 360;
 	    
 	    /*if (newposition > 360.00){
 	    	newposition = 360.00 - newposition;
 	    }*/
    	
 	    double bal = expected - newposition;
 	    
 	    if (bal > AstroConsts.yogaLength){
 	    	bal = 360 - bal;
 	    }
 	    
 	    /*System.out.println("Old Pos" + position);
	    System.out.println("Exp Pos" + expected);
	    System.out.println("Bal " + bal);
	    System.out.println("New Pos" + newposition + "\n");*/
 	   
 	    correction = AstroUtil.endTime(totalSpeed, bal);
 	    
 	    if (Math.abs(bal) > PAN_APPROXIMATION) {
 	    	correction = correction + yogaCorrection(newposition, expected, caltime + correction);
 	    }
    	return correction;
    	 
    }
    
	private double findThithiEnd( double sun, double moon, double speed ) {

        double diff = getMoonSunDiff(sun, moon);

        double bal = AstroConsts.thithiLength - ( diff % AstroConsts.thithiLength );
        double time = AstroUtil.endTime( diffSpeed, bal );
        //System.out.println(sun + " - " + moon + " - " + time + " - " + speed);
        return (timeZone + time + computeCorrection(getMoonSunDiff(sun, moon), diff + bal, time, AstroConsts.thithiLength));
    }
    
	private double computeCorrection(double position, double expected, double caltime, double length ) {
		
	    double correction = 0.0;
	    
	    SwissHelper swissHelper = new SwissHelper(new SweDate( cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), caltime));
	    
	    EnumMap<Planet, Double> planetPosition = swissHelper.getPlanetaryPosition(EnumSet.of(Planet.Sun, Planet.Moon));
		
	    double newmoon = planetPosition.get(Planet.Moon);
	    double newsun = planetPosition.get(Planet.Sun);
	    
	    double newdiff = getMoonSunDiff(newsun, newmoon);
	
	    /*System.out.println( 
	        "Old Pos " + position + "New Diff " + newdiff
	        + AstroUtil.dms( newsun ) + " - " + AstroUtil.dms( newmoon ) );*/
	        
	
	    double newbal = expected - newdiff;
	    //System.out.println("pos " + position + " newdiff " + newdiff + " new bal " + newbal);
	
	    if ( (expected % 180) == 0 && newbal > length  ) {
	
	        newbal = newbal - 180;
	        //System.out.println("new bal > " + newbal);
	    }
	
	    correction = AstroUtil.endTime( diffSpeed, newbal );
	    /*System.out.println( 
	        "Correction : " + correction + " Bal: " + newbal + " Expected : "
	        + expected );*/
	
	    if (Math.abs(newbal) > PAN_APPROXIMATION){
	    	
	    	correction = correction + computeCorrection(getMoonSunDiff(newsun, newmoon), expected , caltime + correction, length);
	    }
	    return correction;
	
	}

    
	/*public void preferenceChange(PreferenceChangeEvent evt) {

		if(evt.getKey().equals(AstrosoftPref.Preference.Ayanamsa.name())){
			setAyanamsa(Enum.valueOf(Ayanamsa.class, evt.getNewValue()));
		}else if (evt.getKey().equals(AstrosoftPref.Preference.PanCalcTime.name())){
			setPanCalcTime(Double.parseDouble(evt.getNewValue()));
		}
	}*/


    public String rahuKala(){
		return weekday.rahuKala();
	}
	
	public String yamaKanda(){
		return weekday.yamaKanda();
	}
	
	public String[] auspiciousTime(){
		return weekday.auspiciousTime();
	}

	public Calendar getDate() {
		
		return cal;
	};
	
	private static DefaultColumnMetaData getPanchangColumnMetaData() {
		
		if (panchangColumnMetaData == null){
			panchangColumnMetaData = new DefaultColumnMetaData(AstrosoftTableColumn.keyvalCols());
			panchangColumnMetaData.localizeColumns();
		}
		return panchangColumnMetaData;
	}
	
	public TableData<MapTableRow> getPanchangTableData() {
		
		List<MapTableRow> rows = new ArrayList<MapTableRow>();
		
		MapTableRowHelper helper = new MapTableRowHelper(getPanchangColumnMetaData());
		
		rows.add(helper.createRow(DisplayStrings.DATE_STR, AstroUtil.formatDate(cal.getTime())));
		rows.add(helper.createRow(DisplayStrings.DAY_STR, weekday));
		rows.add(helper.createRow(DisplayStrings.NAK_STR + " ( " + AstrosoftTableColumn.End.toString() + " ) ", nakshathra));
		rows.add(helper.createRow(DisplayStrings.THITHI_STR + " ( " + AstrosoftTableColumn.End.toString() + " ) ", thithi));
		rows.add(helper.createRow(DisplayStrings.PAKSHA_STR, pak));
		rows.add(helper.createRow(DisplayStrings.YOGA_STR + " ( " + AstrosoftTableColumn.End.toString() + " ) ", yoga));
		rows.add(helper.createRow(DisplayStrings.KARANA_STR + " ( " + AstrosoftTableColumn.End.toString() + " ) ", karana));
		rows.add(helper.createRow(DisplayStrings.SUNRISE_STR, AstroUtil.timeFormat(sunrise)));
		rows.add(helper.createRow(DisplayStrings.SUNSET_STR, AstroUtil.timeFormat(sunset)));
		String auspiciousTime = Arrays.toString(auspiciousTime());
		rows.add(helper.createRow(DisplayStrings.AUS_TIME_STR, auspiciousTime.substring(1, auspiciousTime.length() - 1)));
		rows.add(helper.createRow(DisplayStrings.RAHU_KALA_STR, rahuKala()));
		rows.add(helper.createRow(DisplayStrings.YAMA_KANDA_STR, yamaKanda()));
		
		return TableDataFactory.getTableData(rows);
	}
	
	public Table getPanchangTable() {
		
		return new DefaultTable(getPanchangTableData(), getPanchangColumnMetaData());
		
	}
	
	public ChartData getPlanetChartData(){
		
		return new PlanetChartData(Varga.Rasi, rasi, dir);
	}
	
	public static double getMoonSunDiff(double sun, double moon){
		
		double diff;
		if ( moon > sun ) {

            diff = moon - sun;

        } else {

            diff = ( moon + 360 ) - sun;

        }

        if ( diff > 180.0 ) {

            diff = diff - 180;

        }
        
        return diff;
	}
	
	public WeekDay getWeekday() {
		return weekday;
	}
	
    public static void main( String[] args ) {
    	
    	for(int i = 1; i <= 30 ; i++){
	        Panchang pan = new Panchang( new GregorianCalendar(2006, 9, i, 0, 0));
	
	        System.out.println(pan);
    	}
         // FIXME ArrayOutOfBounds Exception if uncomment
         // System.out.println(DisplayConsts.weekDays[0][pan.weekday] + " Paksha " + DisplayConsts.pakshaNames[0][pan.pak] + " Thithi no "  + (pan.thithi+1) + " " + DisplayConsts.thithiNames[pan.thithi] + " Ends " + AstroUtil.panFormat(pan.thithiEnd, refTime, AstroSoft.ENGLISH) + "  " + pan.thithiEnd + " SunRise/Set" + AstroUtil.timeFormat(pan.sunrise)+"/"+AstroUtil.timeFormat(pan.sunset) );
         // System.out.println(DisplayConsts.yogaNames[pan.yoga] + " Yoga Ends " + AstroUtil.panFormat(pan.yogaEnd, refTime, AstroSoft.ENGLISH) );
    }

    @Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(this.cal.getTime() + "\n");
    	sb.append(this.weekday + "\n");
    	sb.append(this.nakshathra + "\n");
    	sb.append(this.thithi + "\n");
    	sb.append(this.pak + "\n");
    	sb.append(this.yoga + "\n");
    	sb.append(this.karana + "\n");
    	sb.append(AstroUtil.timeFormat(this.sunrise) + " - " + AstroUtil.timeFormat(this.sunset) + "\n");
    	return sb.toString();
    }
    
    private static class PanEvent{
    	
    	private String event;
    	private double ending;
    	
    	public PanEvent(String event, double ending){
    		this.event = event;
    		this.ending = ending;
    	}
    	
    	public double getEnding() {
			return ending;
		}
    	
    	public String toString(){
    		return event + " ( " + AstroUtil.todegmin(ending, ":") + " ) ";
    	}
    }

    private static class KaranaEvent {
    	
    	private PanEvent first;
    	private PanEvent second;
    	
    	public KaranaEvent(String first, double firstEnding, String second, double secondEnding){
    		this.first = new PanEvent(first, firstEnding);
    		this.second = new PanEvent(second, secondEnding);
    	}
    	
    	public String toString() {
    		return first + " , " + second;
    	}
    }

	
	
}
