package app.astrosoft.test;

import java.util.Calendar;

import app.astrosoft.beans.Place;
import app.astrosoft.beans.Place.Location;
import app.astrosoft.beans.Place.Direction;
import app.astrosoft.util.AstroUtil;
import static app.astrosoft.util.AstroUtil.getSunRise;
import static app.astrosoft.util.AstroUtil.getSunSet;
import static java.util.Calendar.getInstance;

public class SunriseSetTest {

	public static void main(String[] args) {
		
		Place tampa = new Place("tampa", "florida", "USA", new Location(27, 57, Direction.NORTH), new Location(82,28,Direction.WEST), "America/New_York");
		//tampa = new Place("erode", "Tamil  Nadu", "India", new Location(11, 22, Direction.NORTH), new Location(77,44,Direction.EAST), "IST");
		
		Calendar today = getInstance();
		
		System.out.println(today);
		System.out.println(today.getTime());
		
		double sunrise = getSunRise(today, tampa);
		double sunset = getSunSet(today, tampa);
		
	    System.out.println(sunrise);
	    System.out.println(sunset);
		
	    System.out.println(today.getTimeZone());
	    

	}
	
}
