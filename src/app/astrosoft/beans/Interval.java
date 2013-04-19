/**
 * Interval.java
 * Created On 2005, Oct 15, 2005 3:23:14 PM
 * @author relango
 * 
 */

package app.astrosoft.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import app.astrosoft.consts.DisplayFormat;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.util.AstroUtil;
import swisseph.SweDate;

public class Interval implements Comparable {

	private static DateFormat dateFormater = new SimpleDateFormat(
			"EEE dd MMM yyyy ");

	private static DateFormat dateTimeFormatter = new SimpleDateFormat(
			"dd MMM HH:mm:ss z");
	
	static {
		//dateTimeFormatter.setTimeZone(AstroSoft.getPreferences().getPlace().astrosoftTimeZone().getTimeZone());
	}

	private double start;

	private double end;

	private Interval tLongitude;

	public Interval(double start, double end) throws IllegalArgumentException {

		if (start > end) {
			// throw new IllegalArgumentException("Start is greater than End" +
			// SweDate.getDate(start) + "-" + SweDate.getDate(end) );
			throw new IllegalArgumentException("Start is greater than End");
		}
		this.start = start;
		this.end = end;
		//System.out.println(this);
	}

	/**
	 * @return Returns the start.
	 */
	public double getStart() {
		return start;
	}

	/**
	 * @return Returns the end.
	 */
	public double getEnd() {
		return end;
	}

	public static Interval intersection(Interval i1, Interval i2) {

		
		double start = Math.max(i1.start, i2.start);
		double end =  Math.min(i1.end, i2.end);
			
		if (end > start){
			
			//System.out.println("< " + i1.start + "," + i1.end + " > < " + i2.start + "," + i2.end + " > ");
			//System.out.println(start + "<>" + end);
			return new Interval(start,end);
		}
		return null;
	}

	public static List<Interval> intersection(List<Interval> l1,
			List<Interval> l2) {

		List<Interval> result = new ArrayList<Interval>();

		for (Interval i1 : l1) {
			for (Interval i2 : l2) {
				Interval x = intersection(i1, i2);
				if (x != null)
					result.add(x);
			}
		}

		return result;

	}
	
	public static List<Interval> common(List<Interval> l1,
			List<Interval> l2) {

		List<Interval> result = new ArrayList<Interval>();

		for (Interval i1 : l1) {
			for (Interval i2 : l2) {
				if(i1.equals(i2)){
					result.add(i1);
				}
			}
		}

		return result;

	}
	
	@Override
	public String toString() {
		
		return toString(DisplayFormat.DEG);// + " <--> " + toString(DisplayFormat.DATE_TIME);
	}

	public String toString(DisplayFormat format) {

		if (!DisplayFormat.intervalFormats().contains(format)){
			throw new IllegalArgumentException("Invalid Interval Display Format");
		}
		
		String str = "";
		switch (format) {

			case DEG:
				str = AstroUtil.dms(start) + " - " + AstroUtil.dms(end);
				break;
			case DATE:
				str = dateFormater.format(SweDate.getDate(start)) + " - "
						+ dateFormater.format(SweDate.getDate(end));
				break;
			case DATE_TIME:
				str = dateTimeFormatter.format(SweDate.getDate(start)) + " - "
						+ dateTimeFormatter.format(SweDate.getDate(end));
				break;
			}
		
		return str;
	}

	public static Calendar parseDate(String dateStr, DisplayFormat format) throws ParseException {

		Calendar cal = new GregorianCalendar();
        
		switch (format) {

		case DATE:
			cal.setTime(dateFormater.parse(dateStr));
			return cal;

		case DATE_TIME:
			cal.setTime(dateTimeFormatter.parse(dateStr));
			return cal;

		default:
			throw new IllegalArgumentException("Invalid formatter " + format);
		}

		
	}
	
	@Override
	public boolean equals(Object o) {

		Interval i = (Interval) o;
		return (this.start == i.start && this.end == i.end);
	}

	public int compareTo(Object o) {

		Interval i = (Interval) o;

		if (this.start < i.start) {
			return -1;
		} else if (this.start > i.start) {
			return 1;
		}
		return 0;
	}
}
