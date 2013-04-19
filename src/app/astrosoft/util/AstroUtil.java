package app.astrosoft.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import swisseph.SweDate;
import app.astrosoft.beans.Place;
import app.astrosoft.consts.Alphabet;
import app.astrosoft.consts.AstroConsts;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.Language;
import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.ui.AstroSoft;

import com.web_tomorrow.utils.suntimes.SunTimes;
import com.web_tomorrow.utils.suntimes.Time;


public class AstroUtil {

	private static final Logger log = Logger.getLogger(AstroUtil.class.getName());
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d yyyy");
	private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM d yyyy, hh:mm:ss a");
	
    public static String dms( double val ) {

        String result = new String(  );

        if ( val < 0 ) {

            val = Math.abs( val );
            result = result + "-";

        }

        int[] deg_min_sec = int_dms(val);

        result =
            result + threeDigit( deg_min_sec[0] ) + " : " + twoDigit( deg_min_sec[1] ) + " : "
            + twoDigit( deg_min_sec[2] );

        return result;

    }
    
    public static double decimal( int deg, int min, int sec ) {

    	double temp = ( ( ( deg * 60 ) + min ) * 60 ) + sec;
        double res = ( double ) ( temp / 3600 );

        //System.out.println("res " + res);
        return res;

    }
    
    public static double toDouble(String degmmsec){
    	return toDouble(degmmsec, ":");
    }
    
    public static double toDouble(String degmmsec , String dlm){
    	String dms[] = degmmsec.split(dlm);
    	if (dms.length == 2){
    		return decimal(dms[0], dms[1], "0");
    	}else if(dms.length == 3){
    		return decimal(dms[0], dms[1], dms[2]);
    	}else{
    		throw new IllegalArgumentException("Invalid Format deg:mm:sec " + degmmsec);
    	}
    }

    private static double decimal(String deg, String min, String sec) {
		
		return decimal(Integer.parseInt(deg), Integer.parseInt(min), Integer.parseInt(sec));
	}

	public static String todegmin( double val, String dlm, boolean ignoreSign ) {

        String result = new String();
        
        if (!ignoreSign && val < 0){
            result = result + "-";
        }
        
        int deg_min_sec[] = int_dms(val);

        result = result + twoDigit( deg_min_sec[0] ) + dlm + twoDigit( deg_min_sec[1] );// + dlm + twoDigit( deg_min_sec[2] );

        return result;
    }
	
	public static String todegmin( double val, String dlm){
		return todegmin(val, dlm, false);
	}

	public static String todegminsec( double val, String dlm, boolean ignoreSign ) {

        String result = new String();
        
        if (!ignoreSign && val < 0){
            result = result + "-";
        }
        
        int deg_min_sec[] = int_dms(val);

        result = result + twoDigit( deg_min_sec[0] ) + dlm + twoDigit( deg_min_sec[1] ) + dlm + twoDigit( deg_min_sec[2] );

        return result;
    }
	
	public static String todegminsec( double val, String dlm){
		return todegminsec(val, dlm, false);
	}
	
    public static String twoDigit( int val ) {

        String res = new String(  );
        if ( val < 10 ) {
            res = "0" + String.valueOf( val );
        } else {
            res = String.valueOf( val );
        }

        return res;

    }
    
    public static String threeDigit( int val ) {

        String res = new String(  );
        if ( val < 10 ) {
            res = "00" + String.valueOf( val );
        }else if ( val < 100 ) {
            res = "0" + String.valueOf( val );
        } else {
            res = String.valueOf( val );
        }

        return res;
    }
    
    public static double endTime( double speed, double bal ) {

        double time = ( 24.00 * ( bal ) ) / speed;

        return time;

    }

    public static String panFormat( double val ) {

        /*String result = new String(  );
        String ampm = new String(  );
        
        double refTime = AstroSoft.getPreferences().getPanCalcTime();

        if(val < 12.00)
            ampm = "AM";
        else if(val > 12.00 && val < 24.00){
            ampm = "PM";
            val = val - 12.00;
            }
        else if(val > 24.00 && val < ref){
            /*ampm = "AM";
            result = "(Next Day)";
        }
        if ( val > ( refTime + 24.00) ) {

            result = DisplayStrings.FULL_DAY_STR.toString();
            return result;
        }
        
        int deg_min_sec[] = int_dms(val);
        
        // FIXME Set time's font based on lang

        if (AstroSoft.getPreferences().getLanguage() == Language.ENGLISH) {
            result = DisplayStrings.TILL_STR.toString() + " " + twoDigit( deg_min_sec[0] ) + ":" + twoDigit( deg_min_sec[1] ) + ampm ;
        }else{
            result = twoDigit( deg_min_sec[0] ) + ":" + twoDigit( deg_min_sec[1] ) + ampm + " " + DisplayStrings.TILL_STR.toString();
        }
        return result;*/
    	
    	return todegmin(val, ":");

    }

    /*public static int getWeekDay( int yr, int mon, int date ) {

        java.util.GregorianCalendar cal =
            new java.util.GregorianCalendar( yr, mon - 1, date );

        return ( cal.get( java.util.Calendar.DAY_OF_WEEK ) - 1 );

    }*/

    public static double getSunRise( 
        int yr, int mon, int date, double lg, double lt, double tz ) {

        double sunrise = 0.0;
        try {

            Time t1 =
                SunTimes.getSunriseTimeUTC( 
                    yr, mon, date, lg, lt, SunTimes.ZENITH );

            sunrise =
                AstroUtil.decimal( 
                    t1.getHour(  ), t1.getMinute(  ), t1.getSecond(  ) ) + tz;

        } catch ( Exception e ) {

            e.printStackTrace(  );

        }

        Mod mod24 = new Mod(24);
        return ( mod24.correct(sunrise) );

    }

    public static double getSunSet( 
        int yr, int mon, int date, double lg, double lt, double tz ) {

        double sunset = 0.0;

        try {

            Time t2 =
                SunTimes.getSunsetTimeUTC( 
                    yr, mon, date, lg, lt, SunTimes.ZENITH );
            
            sunset =
                AstroUtil.decimal( 
                    t2.getHour(  ), t2.getMinute(  ), t2.getSecond(  ) ) + tz;

        } catch ( Exception e ) {

            e.printStackTrace(  );

        }

        Mod mod24 = new Mod(24);
        return ( mod24.correct(sunset) );

    }
    
    public static double getSunRise(Calendar cal, Place place){
    	return getSunRise(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE), place.longitude(), place.latitude(), place.timeZone());
    }
    
    //TODO: check if month - 1 should be used.?
    public static double getSunSet(Calendar cal, Place place){
    	return getSunSet(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE), place.longitude(), place.latitude(), place.timeZone());
    }

    public static String timeFormat( double val, boolean showSecs ) {

        String result = new String(  );
        String ampm = new String(  );

        if (val == 0){
        	val = 12.00;
        	ampm = "AM";
        }else if ( val < 12.00 ) {
            ampm = "AM";
        } else if ( val > 12.00 ) {

            ampm = "PM";
            val = val - 12.00;

        }

        if (showSecs) {
        	result = todegminsec(val, ":");
        }else{
        	result = todegmin(val, ":");
        }

        result = result + " " + ampm;

        return result;

    }

    public static String timeFormat( double val ) {
    	return timeFormat(val, false);
    }
    
    /*public static int getThithi( double sun, double moon ) {

        int thithi;
        int pak = 0;
        double diff;

        if ( moon > sun ) {
            diff = moon - sun;
        } else {
            diff = ( moon + 360 ) - sun;
        }

        if ( diff > 180.0 ) {

            pak = 1;
            diff = diff - 180;

        }

        thithi = ( int ) ( diff / 12 );

        if ( ( thithi == 14 ) && ( pak == 1 ) ) { // For Krishna Paksha 15th thithi is Ammavasya
            thithi = 15;
        }

        return thithi;

    }

    public static int getPaksha( double sun, double moon ) {

        int pak = 0;
        double diff;

        if ( moon > sun ) {
            diff = moon - sun;
        } else {
            diff = ( moon + 360 ) - sun;
        }

        if ( diff > 180.0 ) {
            pak = 1;
        }

        return pak;

    }*/

        //FIXME
    /*public static Interval longitudeForRasiNak(int rasi, int nak){
		
		double rasiStart = rasi * rasiLength;
		double rasiEnd = rasiStart + rasiLength;
		
		double nakStart = nak * nakLength;
		double nakEnd = nakStart + nakLength;
		
		Interval rasiInt = new Interval (rasiStart, rasiEnd);
		Interval nakInt = new Interval (nakStart, nakEnd);
		
		return rasiInt.intersection(nakInt);
	}*/
    
    public static double incJulDate(double jd, int days, int month, int year) {
    
    	GregorianCalendar cal = new GregorianCalendar();
    	cal.setTime(SweDate.getDate(jd));
    	cal.add(Calendar.YEAR, year);
    	cal.add(Calendar.MONTH, month);
    	cal.add(Calendar.DATE, days);
    	SweDate sd = new SweDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE), AstroUtil.decimal(cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND)));
    	return sd.getJulDay();
    }
    
    public static int[] int_dms(double value){
    
    	int deg = ( int ) value;
        double mindob = ( value - deg ) * 60;
        int min = ( int ) ( mindob );
        int sec = ( int ) ( ( mindob - min ) * 60 );
        //System.out.println("mindob " + mindob);
        //System.out.println(deg + " : " + min + " : " + sec);
        
        return new int[]{deg,min,sec};
    }
    
    public static String formatDate(Date date){
    	return dateFormat.format(date);
    }
    
    public static String formatDateTime(Date date){
    	return dateTimeFormat.format(date);
    }
    
    public static Date parseDateTime(String dateStr){
    	try {
			return dateTimeFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    //  converts yy/mm/dd into decimal years
    public static double dateToDecimalYear(GregorianCalendar cal){

       int days = cal.get( Calendar.DAY_OF_YEAR );
       double year = cal.get(Calendar.YEAR);
       double dayFrac;

       if ( cal.isLeapYear((int)year) ) {
           dayFrac = ( double ) days / 366;

       } else {
           dayFrac = ( double ) days / 365;
       }

       return year + dayFrac;
   }
    
   public static Date decimalYearToDate(double year){
	   
	   int yr = (int) year;
	   double days;
	   GregorianCalendar cal = new GregorianCalendar(yr, Calendar.JANUARY, 1);
	   if ( cal.isLeapYear(yr)) {
           days = (year - yr) * 366;

       } else {
    	   days = (year - yr) * 365;
       }
	   cal.add(Calendar.DATE, (int)days);//Math.ceil(days));
	   return cal.getTime();
   }

   public static Calendar getCalendar(Date date){
	   //Calendar cal = new GregorianCalendar(AstroSoft.getPreferences().getPlace().astrosoftTimeZone().getTimeZone());
	   Calendar cal = new GregorianCalendar();
	   cal.setTime(date);
	   return cal;
   }
   
   /**
    * Use this method only if returned calendar object can change, otherwise use Astrosoft.today
    * @return
    */
   public static Calendar getCalendar(){
	   return getCalendar(AstroSoft.today.getTime());
   }
   
   public static Date doubleTimeToDate(double time){
	   int []hr_min = int_dms(time);
	   Calendar cal = getCalendar();
	   cal.set(Calendar.HOUR_OF_DAY, hr_min[0]);
	   cal.set(Calendar.MINUTE, hr_min[1]);
	   cal.set(Calendar.SECOND, hr_min[2]);
	   return cal.getTime();
   }
   
   public static double dateToTimeDouble(Date date){
	   Calendar cal = AstroUtil.getCalendar(date);
	   return AstroUtil.decimal(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
   }
   
   /*public static Date doubleTimeToDateCurrentTZ(double time){
	   int []hr_min = int_dms(time);
	   Calendar cal = new GregorianCalendar();
	   cal.set(Calendar.HOUR_OF_DAY, hr_min[0]);
	   cal.set(Calendar.MINUTE, hr_min[1]);
	   cal.set(Calendar.SECOND, hr_min[2]);
	   return cal.getTime();
   }
   
   
   public static double dateToTimeDoubleCurrentTZ(Date date){
	   Calendar cal = new GregorianCalendar();
	   cal.setTime(date);
	   return AstroUtil.decimal(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
   }*/
   
   public static double nakEndPosition(double moon){
   		double bal = AstroConsts.nakLength - (moon % AstroConsts.nakLength);
   		return moon + bal;
   }
   
   public static int computeNumeroVal(String text){
	   
	   char []chars = text.toCharArray();
	   int val = 0;
	   
	   for(char c : chars){
		   
		   if (Character.isLetter(c)){
			   val = val + Alphabet.numeroValOf(c);
		   }else if (Character.isDigit(c)){
			   val = val + Integer.valueOf(Character.toString(c));
		   }
	   }
	   
	   return val;
   }
   
   public static int toNumeroNum(int numeroVal){
	   
	   int num = numeroVal % 9;
	   
	   if (num == 0){
		   num = 9;
	   }
	   
	   return num;
   }
   
   public static Map<Rasi, Set<Planet>> calcPlanetInHouses(Map<Planet,Rasi> planetHouses){
	
	   Map <Rasi, Set<Planet>> planetInHouses = new EnumMap<Rasi, Set<Planet>>(Rasi.class);
	   
	   for(Planet p : planetHouses.keySet()){
	   
		   Rasi h = planetHouses.get(p);
		   
		   if (planetInHouses.containsKey(h)){
			   planetInHouses.get(h).add(p);
		   }else{
			   planetInHouses.put(h, EnumSet.of(p));
		   }
	   }
	   
	   // Initialize empty set for empty houses
	   for (Rasi house : Rasi.values()){
		   
		   if (!planetInHouses.containsKey(house)) {
			   planetInHouses.put(house, EnumSet.noneOf(Planet.class));
		   }
	   }
	   
	   return planetInHouses;
   }
   
   public static Map<Planet,Integer> calcPlanetLocation(Map<Planet,Rasi> planetHouses){
	
	    if (!planetHouses.containsKey(Planet.Ascendant)){
	    	throw new IllegalArgumentException("planetHouses is missing Ascendant");
	    }
	    
	    Rasi lagna = planetHouses.get(Planet.Ascendant);
		
		log.fine("Lagna "  + lagna);
		
		EnumMap<Planet, Integer> location = new EnumMap<Planet, Integer>(Planet.class);
		
		for (Planet p : planetHouses.keySet()) {
			
			location.put(p, planetHouses.get(p).bhava(lagna));
		}
		
		return location;
   }
   
}
