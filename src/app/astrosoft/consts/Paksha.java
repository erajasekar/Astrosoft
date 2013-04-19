/**
 * Paksha.java
 * Created On 2006, Jan 21, 2006 5:58:34 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

import app.astrosoft.util.Internalization;

public enum Paksha {
	Shukla, Krishna;

	public static Paksha ofDeg(double sun, double moon) {

		Paksha pak = Shukla;
		double diff;

		if (moon > sun) {
			diff = moon - sun;
		} else {
			diff = (moon + 360) - sun;
		}

		if (diff > 180.0) {
			pak = Krishna;
		}

		return pak;

	}
	
	public boolean isShukla(){
	    return this == Shukla;	
	}

	public String toString() {

		return Internalization.getString(this.name());
	}

}
