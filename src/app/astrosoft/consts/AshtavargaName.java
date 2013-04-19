/**
 * AshtavargaReduction.java
 * Created On 2006, Feb 4, 2006 3:48:48 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

import java.util.EnumSet;

import app.astrosoft.util.Internalization;

public enum AshtavargaName {
	
	Sun,
	Moon,
	Mars,
	Mercury,
	Jupiter,
	Venus,
	Saturn,
	SarvaAshtavarga,
	AshtaVarga, Trikona , Ekathipathya;
	
	public static EnumSet<AshtavargaName> ashtavargas(){
		return EnumSet.range(Sun, SarvaAshtavarga);
	}
	
	public static AshtavargaName ofPlanet(Planet p){
		return valueOf(p.name());
	}
	
	public String toString(Language lang) {

		return Internalization.getString(lang, this.name());
	}
	
	@Override
	public String toString() {
		
		return Internalization.getString(this.name());
	}
}
