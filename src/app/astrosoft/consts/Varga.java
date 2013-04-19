/**
 * Charts.java
 * Created On 2006, Feb 4, 2006 2:57:02 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

import app.astrosoft.util.Internalization;

public enum Varga {
	
	Bhava(0),
	Rasi(1),
	
	Hora(2),
	Drekkana(3),
	
	Chaturtamsa(4),
	Navamsa(9),
	
	Saptamsa(7),
	Dasamsa(10),
	
	Dwadasamsa(12),
	Shodasamsa(16),
	
	Vimshamsa(20),
	ChaturVimshamsa(24),
	
	
	
	Bhamsa(27),
	Trimshamsa(30),
	
	Khavedamsa(40),
	Akshvedamsa(45),
	
	Shastiamsa(60),
	Panchamsa(5),
	
	Shashtamsa(6),
	Ashtamsa(8),
	
	Ekadamsa(11);
	
	
	private Varga(int div) {
		division = div;
	}
	
	private int division;
	
	private static Varga vals[] = values();

	public int division() {
		return division;
	}
	
	public static Varga ofIndex(int index) {
		return vals[index % vals.length];
	}
	
	public String toString() {

		return Internalization.getString(this.name());
	}
}
