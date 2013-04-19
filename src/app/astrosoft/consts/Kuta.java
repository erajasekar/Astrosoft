/**
 * Kuta.java
 * Created On 2006, Jan 21, 2006 6:14:08 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

import app.astrosoft.util.Internalization;

public enum Kuta {
	Dina(3),
	Gana(6),
	Mahendra(1),
	StreeDeergha(1),
	Yoni(4),
	Rasi(7),
	RasiAdhipathi(5),
	Vasya(2),
	Rajju(1),
	Vedha(1),
	Varna(1),
	Nadi(8);
	
	private int maxVal;
	
	private Kuta(int maxVal){
		this.maxVal = maxVal;
	}
	
	public int maxValue(){
		return maxVal;
	}

	public static int totalValue(){
		return 40;
	}
	
	public String toString(){
		
		return Internalization.getString(this.name());
	}
}
