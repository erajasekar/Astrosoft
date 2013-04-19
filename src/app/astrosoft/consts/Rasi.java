/**
 * Rasi.java
 * Created On 2005, Oct 20, 2005 8:18:06 PM
 * @author E. Rajasekar
 */
package app.astrosoft.consts;

import static app.astrosoft.consts.AstroConsts.*;
import app.astrosoft.beans.Interval;
import app.astrosoft.ui.util.CallBack;
import app.astrosoft.util.Internalization;
import app.astrosoft.util.Mod;

public enum Rasi {

	Mesha("Ari", Planet.Mars),
	Vrishabha("Tau", Planet.Venus),
	Mithuna("Gem", Planet.Mercury),
	Kataka("Can", Planet.Moon),
	Simha("Leo", Planet.Sun),
	Kanya("Vir", Planet.Mercury),
	Thula("Lib", Planet.Venus),
	Vrichika("Sco", Planet.Mars),
	Dhanus("Sag", Planet.Jupiter),
	Makara("Cap", Planet.Saturn),
	Kumbha("Aqu", Planet.Saturn),
	Meena("Pis", Planet.Jupiter);
	
	private String sym;
	private Planet owner;
	private static Rasi []vals = values();
	//private static DisplayFormat displayFormat = DisplayFormat.FULL_NAME;
	
	Rasi(String sym, Planet owner){
		this.sym = sym;
		this.owner = owner;
	}
	
	public static Rasi ofIndex(int index){
		return vals[index % vals.length];
	}
	
	public static Rasi ofDeg(double deg){
		return ofIndex((int)(deg / rasiLength));
	}
	
	public String sym(){
		return sym;
	}

	public static Interval longitudeForRasi(Rasi rasi){
		
		double rasiStart = rasi.ordinal() * rasiLength;
		double rasiEnd = rasiStart + rasiLength;
		
		Interval rasiInt = new Interval (rasiStart, rasiEnd);
		
		return rasiInt;
	}
	
	/** Returns rasi of absolute pos from current rasi
	 * 
	 * @param pos
	 * @return
	 */
	public Rasi absolute(int pos){
		
		return Rasi.ofIndex(this.ordinal() + pos);
	}

	public Rasi next(){
		return absolute(1);
	}
	
	public Rasi previous(){
		return absolute(-1);
	}
	
	public Rasi[] trines(){
		return new Rasi[]{
				this,
				absolute(4),
				absolute(8)
				};
	}
	
	public Planet owner(){
	
		return owner;
	}
	
	public int bhava(Rasi ascendant){
		Mod m = new Mod(12);
		return (m.sub(this.ordinal(), ascendant.ordinal()) + 1);
	}
	
	public boolean isOddSign(){
		
		//Ordinal starts with 0
		return ( (ordinal() % 2) == 0); 
	}
	
	public boolean isEvenSign(){
		return !isOddSign();
	}
	
	public boolean isMovableSign(){
		return (this == Mesha || this == Kataka || this == Thula || this == Makara);
	}
	
	public boolean isFixedSign(){
		return (this == Vrishabha || this == Simha || this == Vrichika  || this == Kumbha);
	}
	
	/*public static void setDisplayFormat(DisplayFormat formatter){
		DisplayFormat.validateNameFormat(formatter);
		displayFormat = formatter;
	}*/
	
	
	public String toString(){
		
		return Internalization.getString(this.name());
	}
	
	public String toString(Language lang){
		return Internalization.getString(lang, this.name());
	}
	
    public String toString(DisplayFormat format){
		
		return Internalization.getString(format, this.name(), this.sym());
	}
	
	/*public static String[] values(Language language){
		
		final Rasi vals[] = Rasi.values();
		final String result[] = new String[vals.length];
		
		Internalization.useLanguage(language, new CallBack(){

			public void call() {
				int i = 0;
				for(Rasi r : vals){
					result[i++] = r.toString();
				}
				
			}
		});
		return result;
	}*/
}
