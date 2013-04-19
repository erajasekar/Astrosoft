/**
 * Karakas.java
 * Created On 2006, Jan 21, 2006 5:40:11 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

import app.astrosoft.util.Internalization;

public enum Karaka {

	AtmaKaraka,
	AmatyaKaraka,
	BhratruKaraka,
	MatruKaraka,
	PuthraKaraka,
	GnatiKaraka,
	DaraKaraka,
	NoKaraka;
	
	private static Karaka vals[] = values();

	public static Karaka ofIndex(int index) {
		return vals[index % vals.length];
	}
	
	public String toString() {

		return Internalization.getString(this.name());
	}

}
