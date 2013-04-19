/**
 * SampleHoroscopes.java
 * Created On 2007, Oct 16, 2007 2:25:30 PM
 * @author E. Rajasekar
 */

package app.astrosoft.test;

import app.astrosoft.core.Horoscope;

public class SampleHoroscopes {

	public static Horoscope getHoroscope(String file) {
		return Horoscope.createFromFile(file);
	}
	
	public static Horoscope getMyHoroscope(){
		return getHoroscope("c:/Astrosoft/samples/raja.ash");
	}
	
	public static Horoscope getElangoHoroscope(){
		return getHoroscope("c:/Astrosoft/samples/elango.ash");
	}
	
	public static Horoscope getMuthuHoroscope(){
		return getHoroscope("c:/Astrosoft/samples/muthu.ash");
	}
	
	public static Horoscope getSubaHoroscope(){
		return getHoroscope("c:/Astrosoft/samples/suba.ash");
	}
	
	public static Horoscope getManiHoroscope(){
		return getHoroscope("c:/Astrosoft/samples/mani.ash");
	}
}
