/**
 * Karna.java
 * Created On 2006, Jan 21, 2006 5:30:29 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

import app.astrosoft.util.Internalization;
import app.astrosoft.util.Mod;

public enum Karana {

	Bava,
	Balava,
	Kaulava,
	Taitula,
	Garija,
	Vanija,
	Visti,
	Sakuna,
	Chatushpada, 
	Naga,
	Kimstughna;
	
	private static double CHATHURDASI_SECOND_HALF = 342.00;
		
	private static Karana vals[] = values();
	
	public static Karana ofIndex(int index) {
		return vals[index % vals.length];
	}
	
	public static Karana ofDeg(double deg){
		
		Karana karana = null;
		
		if (deg > CHATHURDASI_SECOND_HALF && deg <= CHATHURDASI_SECOND_HALF + 6.0) {
			karana = Sakuna;
		}else if (deg > CHATHURDASI_SECOND_HALF + 6.0 && deg <= CHATHURDASI_SECOND_HALF + 12.0) {
			karana = Chatushpada;
		}else if (deg > CHATHURDASI_SECOND_HALF + 12.0 && deg <= CHATHURDASI_SECOND_HALF + 18.0) {
			karana = Naga;
		} else if (deg > 0.0 && deg < 6.0){
			karana = Kimstughna;
		}else {
			
			/*if (deg > 180){
				deg = deg - 180;
			}*/
			karana = ofIndex(((int)(deg - 6.0) / 6) % 7);
		}
		
		return karana;
	}
	
	public static Karana[] ofDeg(double sun, double moon){
		
		Mod mod = new Mod(360);
		double deg = mod.sub(moon, sun);
		return new Karana[] {ofDeg(deg), ofDeg(mod.add(deg,6))};
	}
	
	public String toString() {

		return Internalization.getString(this.name());
	}
}
