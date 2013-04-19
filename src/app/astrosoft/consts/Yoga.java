/**
 * Yoga.java
 * Created On 2006, Jan 21, 2006 5:33:46 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

import static app.astrosoft.consts.AstroConsts.nakLength;

public enum Yoga {

	Vishakambha,
	Priti,
	Ayushman,
	Saubhagya,
	Sobhana,
	Atiganda,
	Sukarma,
	Dhriti,
	Sula,
	Ganda,
	Vriddhi,
	Dhruva,
	Vyaghat,
	Harshana,
	Vajra,
	Siddhi,
	Vyatipatha,
	Variyan,
	Parigha,
	Siva,
	Siddha,
	Sadhya,
	Subha,
	Sukla,
	Brahma,
	Indra,
	Vaidhriti;
	
	private static Yoga vals[] = values();
	
	public static Yoga ofIndex(int index) {
		return vals[index % vals.length];
	}

	public static Yoga ofDeg(double sun, double moon) {
		return ofIndex((int) ((sun + moon) / nakLength));
	}

}
