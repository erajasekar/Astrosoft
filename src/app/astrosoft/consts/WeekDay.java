/**
 * WeekDay.java
 * Created On 2006, Jan 7, 2006 8:11:43 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

import java.util.Calendar;

import app.astrosoft.util.Internalization;

public enum WeekDay {

	Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday;
	
	private static final String[] rahukala =
    {
         "04:30 PM - 06:00 PM", "07:30 AM - 09:00 AM", "03:00 PM - 4:30 PM",
         "12:00 PM - 01:30 PM", "01:30 PM - 03:00 PM", "10:30 AM - 12:00 PM",
         "09:00 AM - 10:30 AM"
     
    };
	
	private static final String[] yamakanda =
    {
        "12:00 PM - 1:30 PM", "10:30 AM - 12:00 AM", "09:00 AM - 10:30 AM",
        "07:30 AM - 09:00 AM", "06:00 AM - 07:30 AM", "03:00 PM - 04:30 PM",
        "01:30 PM - 03:00 PM"
    
    };
    
	private static final String[][] auspiciousTime = 
	{
	    {"07.30 - 10.00 am", "02.00 - 04.30 pm", "09.00 pm - 12.00 am"},
	    {"06.00 - 07.00 am ", "12.00 - 02.00 pm", "06.00 - 09.00 pm , 10.00 - 11.00 pm"},
	    {"10.30 - 11.00 am", "12.00 - 01.00 pm , 04.30 - 06.00 pm ", "07.00 - 08.00 pm"},
	    {"09.00 - 10.00 am", "01.30 - 03.00 pm , 04.00 - 05.00 pm", "07.00 - 10.00 pm , 11.00 pm - 12.00 am"},
	    {"09.00 - 10.30 am", "01.00 - 01.30 pm , 04.30 - 06.00 pm", "06.00 - 07.00 pm , 08.00 - 09.00 pm"},        
	    {"06.00 - 09.00  am", "01.00 - 01.30 pm , 05.00 - 06.00 pm", "08.00 - 9.00 pm , 10.30 - 11.00 pm"},
	    {"07.00 - 07.30 am , 10.30 - 12.00 pm", "12.00 - 01.00 pm , 05.00 - 06.00 pm", "06.00 - 07.30 pm , 09.00 - 10.00 pm"}        
	};
	
	private static WeekDay vals[] = values();
	
	public static WeekDay ofIndex(int index) {
		return vals[index % vals.length];
	}
	
	public static WeekDay ofDay( int yr, int mon, int date ) {

        return ofCalendar(new java.util.GregorianCalendar( yr, mon - 1, date ));
    }
	
	public static WeekDay ofCalendar(Calendar cal){
		return WeekDay.ofIndex( cal.get( java.util.Calendar.DAY_OF_WEEK ) - 1 );
	}
	
	public String rahuKala(){
		return rahukala[ordinal()];
	}
	
	public String yamaKanda(){
		return yamakanda[ordinal()];
	}
	
	public String[] auspiciousTime(){
		return auspiciousTime[ordinal()];
	}
	
	public String sym(){
		return this.name().substring(0,3);
	}
	
	public String toString() {

		return Internalization.getString(this.name());
	}
}

