/**
 * Ayanamsa.java
 * Created On 2005, Oct 1, 2005 6:25:13 PM
 * @author relango
 */
package app.astrosoft.consts;

import swisseph.SweConst;

public enum Ayanamsa {

	LAHARI("Lahari", SweConst.SE_SIDM_LAHIRI), RAMAN("Raman", SweConst.SE_SIDM_RAMAN), KRISHNAMURTHI("KrishnaMurthi", SweConst.SE_SIDM_KRISHNAMURTI);
	
	private String ayaName;
	
	private int ayaValue;
	
	private Ayanamsa(String ayaName, int ayaValue){
		
		this.ayaName = ayaName;
		this.ayaValue = ayaValue;
	}
	
	public String ayaName(){
		return ayaName;
	}
	
	public String toString(){
		return ayaName;
	}
	
	public int ayaValue(){
		return ayaValue;
	}
	
	public static Ayanamsa getDefault(){
		return LAHARI;
	}
	
	/*public static void ofName(String name){
		
	}*/
}
