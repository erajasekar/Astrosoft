/**
 * Planet.java
 * Created On 2005, Oct 20, 2005 8:18:06 PM
 * @author E. Rajasekar
 */
package app.astrosoft.consts;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import swisseph.SweConst;
import app.astrosoft.util.Internalization;

public enum Planet {
	
	Sun("Su", SweConst.SE_SUN, 6.0, 0),
	Moon("Mo", SweConst.SE_MOON, 10.0, 1),
	Mars("Ma", SweConst.SE_MARS, 7.0, 2),
	Mercury("Me", SweConst.SE_MERCURY, 17.0, 6),
	Jupiter("Ju", SweConst.SE_JUPITER, 16.0, 4),
	Venus("Ve", SweConst.SE_VENUS, 20.0, 8),
	Saturn("Sa", SweConst.SE_SATURN, 19.0, 5),
	Rahu("Ra", SweConst.SE_TRUE_NODE, 18.0, 3),
	Ketu("Ke", SweConst.SE_MEAN_APOG, 7.0, 7),
	Ascendant("Asc"),
	Ra_Ke("Ra/Ke"),
	
	//Primarily used in rules
	NullPlanet;
	
	public static enum Character {PAPA, SUBA, UNKNOWN};
	private String sym;
	private int swissPlanetNo;
	private double dasaPeriod = -1;
	private int dasaNo = -1;
	
	private static Planet []vals = values();
	//private static DisplayFormat displayFormat = DisplayFormat.FULL_NAME;
	private static Planet[] orderByDasa = {Sun, Moon, Mars, Rahu, Jupiter, Saturn, Mercury, Ketu, Venus};
		
	Planet(String sym, int planetNo, double period, int dasaNo){
		this.sym = sym;
		this.swissPlanetNo = planetNo;
		this.dasaPeriod = period;
		this.dasaNo = dasaNo;
	}
	
	Planet(String sym){
		this.sym = sym;
	}
	
	Planet(){
	}
	
	public static Planet ofIndex(int index){
		return vals[index % 9];
	}
	
	public String sym(){
		return sym;
	}
	
	public int planetNo(){
		return swissPlanetNo;
	}
	
	public double dasaPeriod(){
		if (dasaPeriod == -1){
			throw new UnsupportedOperationException();
		}
		return dasaPeriod;
	}
	
	public static Set<Planet> allPlanets(){
		return EnumSet.range(Sun, Ketu);
	}
	
	public static Set<Planet> majorPlanets(){
		return EnumSet.range(Sun, Saturn);
	}
	
	public static Set<Planet> majorPlanetsAsc(){
		Set<Planet> planets = majorPlanets();
		planets.add(Ascendant);
		return planets;
	}
	
	public static Set<Planet> planetsAsc(){
		return EnumSet.range(Sun, Ascendant);
	}
	
	public static boolean isNode(Planet p){
		return (p == Rahu || p == Ketu);
	}
	
	public int dasaNo(){
		if (dasaNo == -1){
			throw new UnsupportedOperationException();
		}
		return dasaNo;
	}
	
	public static Planet ofDasaNo(int dasaNo){
		return orderByDasa[dasaNo % 9];
	}
	
	public Planet nextDasaPlanet() {
		return nextDasaPlanet(1);
	}
	
	public Planet nextDasaPlanet(int i){
		return Planet.ofDasaNo(this.dasaNo + i);
	}
	
	public Planet nextPlanet(){
		return Planet.ofIndex(this.ordinal() + 1);
	}
	
	public static Set<Planet> doshaPlanets(){
		return EnumSet.of(Mars, Sun, Saturn, Ra_Ke);
	}
	
	public Rasi moolaTrikonaRasi(){
		switch(this){
			case Sun: return Rasi.Mesha;
			case Moon: return Rasi.Vrishabha;
			case Mars: return Rasi.Mesha;
			case Mercury: return Rasi.Kanya;
			case Jupiter: return Rasi.Dhanus;
			case Venus: return Rasi.Thula;
			case Saturn: return Rasi.Kumbha;
		}
		throw new IllegalArgumentException("moolaTrikonaRasi() is invalid for planet " + name());
	}
	
	public boolean isMoolaTrikona(Rasi house){
		return house == moolaTrikonaRasi();
	}
	
	public boolean isOwnerOf(Rasi house){
		
		return (this.equals(house.owner()));
	}
	
	public boolean isExaltedRasi(Rasi house){
		return house.equals(exaltedRasi());
	}
	
	public Rasi exaltedRasi(){
		switch(this){
			case Sun: return Rasi.Mesha;
			case Moon: return Rasi.Vrishabha;
			case Mars: return Rasi.Makara;
			case Mercury: return Rasi.Kanya;
			case Jupiter: return Rasi.Kataka;
			case Venus: return Rasi.Meena;
			case Saturn: return Rasi.Thula;
		}
		throw new IllegalArgumentException("exaltedRasi() is invalid for planet " + name());
	}
	
	public boolean isDebilitatedRasi(Rasi house){
		return house.equals(debilitatedRasi());
	}
	
	public Rasi debilitatedRasi(){
		switch(this){
			case Sun: return Rasi.Thula;
			case Moon: return Rasi.Vrichika;
			case Mars: return Rasi.Kataka;
			case Mercury: return Rasi.Meena;
			case Jupiter: return Rasi.Makara;
			case Venus: return Rasi.Kanya;
			case Saturn: return Rasi.Mesha;
		}
		throw new IllegalArgumentException("debilitatedRasi() is invalid for planet " + name());
	}
	
	public boolean isAspectingLocation(int location){
		
		switch(this){
			case Sun:
			case Moon:
			case Mercury:
			case Venus:
			case Rahu:
			case Ketu:
						return (location == 7);
						
			case Mars: return (location == 4 || location == 8 || location == 7);
			case Jupiter: return (location == 5 || location == 9 || location == 7);
			case Saturn: return (location == 3 || location == 10 || location == 7);
		}
		
		throw new IllegalArgumentException("isAspectingLocation() is invalid for planet " + name());
	}
	
	public Character character(){
		if (papaPlanets().contains(this)){
			return Character.PAPA;
		}else if(subaPlanets().contains(this)){
			return Character.SUBA;
		}else{
			return Character.UNKNOWN;
		}
	}
	
	public static Set<Planet> papaPlanets(){
		return EnumSet.of(Sun, Mars, Saturn, Rahu, Ketu);
	}
	
	public static Set<Planet> subaPlanets(){
		return EnumSet.of(Jupiter, Venus, Mercury);
	}
	
	/** Determines if this planet is natural benefic */
	//TODO: check if moon/mercury ok..
	public boolean isBenefic(){
		
		Character character = character();
		
		if (character == Character.UNKNOWN){
			throw new  IllegalArgumentException("isBenefic() is not applicable for " +  name());
		}
		return (character == Character.SUBA);
	}
	
	/** Determines if this planet is natural malefic */
	public boolean isMalefic(){
		Character character = character();
		
		if (character == Character.UNKNOWN){
			throw new  IllegalArgumentException("isMalefic() is not applicable for " +  name());
		}
		return (character == Character.PAPA);
	}
	/*
	public static boolean isAllBenefics(Set<Planet> planets){
		
		if (planets.isEmpty())
			return false;
		
		for(Planet p : planets){
			if (!p.isBenefic()){
				return false;
			}
		}
		return true;
	}
	
	public static boolean isAnyBenefics(Set<Planet> planets){
		
		if (planets.isEmpty())
			return false;
		
		for(Planet p : planets){
			if (p.isBenefic()){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isAllMalefics(Set<Planet> planets){
		
		if (planets.isEmpty())
			return false;
		
		for(Planet p : planets){
			if (!p.isMalefic()){
				return false;
			}
		}
		return true;
	}
	
	public static boolean isAnyMalefics(Set<Planet> planets){
		
		if (planets.isEmpty())
			return false;
		
		for(Planet p : planets){
			if (p.isMalefic()){
				return true;
			}
		}
		return false;
	}
	*/
	public boolean isAsc(){
		return (this == Ascendant);
	}
	
	public boolean isNode() {
		
		return isNode(this);
	}
	
	public int ashtavargaNo(){
		if (this == Ascendant){
			return 7;
		}else if (isNode(this) || this == Ra_Ke){
			return -1;
		}else{
			return ordinal();
		}
	}
	
	public static List<AstrosoftTableColumn> toTableColumn(Set<Planet> planets){
		
		List<AstrosoftTableColumn> cols = new ArrayList<AstrosoftTableColumn>();
		
		for(Planet p : planets){
			cols.add(AstrosoftTableColumn.valueOf(p.name()));
		}
		
		return cols;
	}
	
	/*public static void setDisplayFormat(DisplayFormat formatter){
		DisplayFormat.validateNameFormat(formatter);
		displayFormat = formatter;
	}*/
	
	/*public void useDisplayFormat(DisplayFormat formatter, CallBack caller){
		setDisplayFormat(formatter);
		caller.call();
		formatter = DisplayFormat.FULL_NAME; 
	}*/
	
	public String toString(){
		
		return Internalization.getString(this.name());
	}
	
	public String toString(DisplayFormat format){
		
		return Internalization.getString(format, this.name(), this.sym());
	}
	
	public static Iterator<Planet> dasaIterator(Planet startWith){
		return new DasaIterator(startWith);
	}
	
	public static Iterable<Planet> dasaLords(final Planet startWith){
		return new Iterable<Planet>(){
			
			public Iterator<Planet> iterator() {
				
				return new DasaIterator(startWith) ;
			}
			
		};
	}
	
	private static class DasaIterator implements Iterator<Planet>{

		Planet current = null;
		//Planet start;
		short count = 0;
		
		public DasaIterator(Planet startWith){
			//start = startWith;
			current = startWith;
		}
		
		public boolean hasNext() {
			//return current != start;
			return count != 9;
		}

		public Planet next() {
			
			//Planet p = (current != null) ? current : start;
			Planet p = current;
			current = p.nextDasaPlanet();
			count ++;
			return p;
		}

		public void remove() {
			throw new UnsupportedOperationException("remove is not supported");
			
		}
	}
	
     public static void main(String[] args) {
    	 for(Planet p : Planet.dasaLords(Planet.Saturn)){
    		 System.out.println(p);
    	 }
	 }
}
