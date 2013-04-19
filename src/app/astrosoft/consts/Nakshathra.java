/**
 * Nakshathra.java
 * Created On 2005, Oct 20, 2005 8:02:06 PM
 * @author E. Rajasekar
 */
package app.astrosoft.consts;

import static app.astrosoft.consts.AstroConsts.*;

import java.util.EnumSet;

import app.astrosoft.beans.Interval;
import app.astrosoft.util.Internalization;

public enum Nakshathra {

	Ashwini, Bharani, Krithika, Rohini, Mrigasira, Thiruvaadhirai, Punarpoosam, Poosam, Ayilyam, Magam, Pooram, Uththiram, Hastam, Chittirai, Swathi, Vishakham, Anusham, Kettai, Moolam, Pooradam, Uththiradam, Thiruvonam, Avittam, Shathayam, Poorattadhi, Uttarattadhi, Revathi;
	private static Nakshathra vals[] = values();

	public static Nakshathra ofIndex(int index) {
		return vals[index % vals.length];
	}

	public static Nakshathra ofDeg(double deg) {
		return ofIndex((int) (deg / nakLength));
	}

	public static Interval degFor(Nakshathra nak) {

		double nakStart = nak.ordinal() * nakLength;
		//double nakEnd = nakStart + nakLength;
		double nakEnd = (nak.ordinal() + 1) * nakLength;

		Interval nakInt = new Interval(nakStart, nakEnd);

		return nakInt;
	}

	/**
	 * Returns nakshathra of absolute pos from current nakshathra
	 * 
	 * @param pos
	 * @return
	 */
	public Nakshathra absolute(int pos) {

		return Nakshathra.ofIndex(this.ordinal() + pos);
	}

	public static EnumSet<Nakshathra> ofRasi(Rasi rasi) {
		int nakIndex = (rasi.ordinal() / 4) + (rasi.ordinal() * 2);
		return EnumSet.range(Nakshathra.ofIndex(nakIndex), Nakshathra
				.ofIndex(nakIndex + 2));
	}

	public static EnumSet<Nakshathra> muhurtaNakshathras(MuhurthaRank rank) {

		EnumSet<Nakshathra> naks = null;

		switch (rank) {

		case VeryGood:
			naks = EnumSet.of(Nakshathra.Ashwini, Nakshathra.Rohini,
					Nakshathra.Mrigasira, Nakshathra.Poosam,
					Nakshathra.Uththiram, Nakshathra.Hastam, Nakshathra.Swathi,
					Nakshathra.Anusham, Nakshathra.Uththiradam,
					Nakshathra.Thiruvonam, Nakshathra.Uttarattadhi,
					Nakshathra.Revathi);

			break;
		case Good:
			naks = EnumSet.of(Nakshathra.Thiruvaadhirai,
					Nakshathra.Punarpoosam, Nakshathra.Magam,
					Nakshathra.Chittirai, Nakshathra.Moolam,
					Nakshathra.Avittam, Nakshathra.Shathayam);
			break;
		}

		return naks;
	}

	public String toString() {

		return Internalization.getString(this.name());
	}
}
