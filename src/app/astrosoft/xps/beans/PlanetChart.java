/**
 * PlanetChart.java
 * Created On 2007, Oct 16, 2007 1:57:28 PM
 * @author E. Rajasekar
 */

package app.astrosoft.xps.beans;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import app.astrosoft.beans.PlanetChartData;
import app.astrosoft.beans.PlanetaryInfo;
import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.Varga;
import app.astrosoft.util.AstroUtil;
import static app.astrosoft.util.CollectionUtil.*;
import app.astrosoft.util.Mod;

public class PlanetChart {
	
	private static final Logger log = Logger.getLogger(PlanetChart.class.getName());

	private String varga;
	
	//private List<PlanetLocation> planetLocations;
	
	private Map<Planet, Integer> planetLocations;
	
	private Map<Planet, Rasi> planetHouses; 
	
	private Map<Rasi,Set<Planet>> planetsInHouse;
	
	private Mod mod12 = new Mod(12);
	
	public PlanetChart(Varga varga, PlanetaryInfo planetaryInfo){
		
		/*this.varga = varga;
		this.planetLocations = planetaryInfo.getPlanetLocation(varga);
		this.planetHouses = planetaryInfo.getPlanetRasi(varga);
		*/
		this(varga,  planetaryInfo.getPlanetLocation(varga), planetaryInfo.getPlanetRasi(varga));
	}

	public PlanetChart(Varga varga, Map<Planet, Integer> planetLocations, Map<Planet, Rasi> planetHouses){
	
		this.varga = varga.name();
		this.planetLocations = planetLocations;
		this.planetHouses =planetHouses;
		this.planetsInHouse = AstroUtil.calcPlanetInHouses(planetHouses);
	}
	
	public PlanetChart(Varga varga, Map<Planet, Rasi> planetHouses ){
		
		this(varga, AstroUtil.calcPlanetLocation(planetHouses), planetHouses);
	}
	
	/*private void addPlanetLocations(EnumMap<Planet, Integer> planetPos) {
		
		for (Planet p : planetPos.keySet()){
			planetLocations.add(new PlanetLocation(p,planetPos.get(p)));
		}
	}*/
	
	public Map<Planet, Integer> getPlanetLocations() {
		return planetLocations;
	}
	
	public int getPlanetLocation(Planet planet){
		return planetLocations.get(planet);
	}
	
	public Rasi getPlanetHouse(Planet planet) {
		return planetHouses.get(planet);
	}
	
	public String getVarga() {
		return varga;
	}
	
	public boolean isPlanetAt(Planet planet, int location){
		return (getPlanetLocation(planet) == location);
	}
	
	public boolean isPlanetsAt(Set<Planet> planets, int location){
		
		for(Planet p : planets){
			if (!isPlanetAt(p,location)){
				return false;
			}
		}
		return true;
	}
	
	public boolean isPlanetAt(Planet planet, Rasi rasi){
		return (getPlanetHouse(planet).equals(rasi));
	}
	
	public boolean isPlanetsAt(Set<Planet> planets, Rasi rasi){
		
		for(Planet p : planets){
			if (!isPlanetAt(p,rasi)){
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isPlanetWith(Planet planet1, Planet planet2){
		return (getPlanetHouse(planet1).equals(getPlanetHouse(planet2)));
	}
	
	public boolean isPlanetWithAll(Planet planet, Set<Planet> planets){
		
		for(Planet p : planets){
			if (!isPlanetWith(planet,p)){
				return false;
			}
		}
		return true;
	}
	
	public boolean isPlanetWithAny(Planet planet, Set<Planet> planets){
		
		for(Planet p : planets){
			if (isPlanetWith(planet,p)){
				return true;
			}
		}
		return false;
	}
	
	public boolean isPlanetInOwnHouse(Planet planet){
		return planet.isOwnerOf(getPlanetHouse(planet));
	}
	
	public boolean isPlanetExalted(Planet planet){
		return planet.isExaltedRasi(getPlanetHouse(planet));
	}
	
	public boolean isPlanetDebilitated(Planet planet){
		return planet.isDebilitatedRasi(getPlanetHouse(planet));
	}
	
	public boolean isPlanetInMoolaTrikona(Planet planet){
		return planet.isMoolaTrikona(getPlanetHouse(planet));
	}
	
	public boolean isPlanetInOwnOrExaltedHouse(Planet planet){
		return (isPlanetInOwnHouse(planet) ||isPlanetExalted(planet) );
	}
	public boolean isPlanetInKendra(Planet planet){
		
		//System.out.println(getPlanetLocation(planet) + " = " + isKendraLocation(getPlanetLocation(planet)));
		return isKendraLocation(getPlanetLocation(planet));
	}
	
	public boolean isPlanetInTrine(Planet planet){
		
		//System.out.println(getPlanetLocation(planet) + " = " + isTrineLocation(getPlanetLocation(planet)));
		return isTrineLocation(getPlanetLocation(planet));
	}
	
	public boolean isPlanetInKendraTo(Planet planet1, Planet planet2){
		int distance = mod12.sub(getPlanetLocation(planet1) , getPlanetLocation(planet2)) + 1;
		
		return isKendraLocation(distance);
	}
	
	public boolean isPlanetInTrineTo(Planet planet1, Planet planet2){
		int distance = mod12.sub(getPlanetLocation(planet1) , getPlanetLocation(planet2)) + 1;
		return isTrineLocation(distance);
	}
	
	public boolean isPlanetInKendraOrTrine(Planet planet){
		return (isPlanetInKendra(planet) || isPlanetInTrine(planet));
	}
	
	public boolean isPlanetInKendraOrTrineTo(Planet planet1, Planet planet2){
		return (isPlanetInKendraTo(planet1,planet2) || isPlanetInTrineTo(planet1,planet2));
	}
	
	public boolean isPlanetAspectedBy(Planet aspected, Planet aspecting){
		
		int distance =  mod12.sub(getPlanetLocation(aspected) , getPlanetLocation(aspecting)) + 1;
		return aspecting.isAspectingLocation(distance);
	}
	
	public boolean isHouseAspectedBy(Rasi aspected, Planet aspecting){
		
		int distance =  mod12.sub(aspected.ordinal() , getPlanetHouse(aspecting).ordinal()) + 1;
		return aspecting.isAspectingLocation(distance);
	}

	public Set<Planet> planetsAt(Planet from, int location){
		
		Rasi fromHouse = getPlanetHouse(from);
		Rasi atHouse = fromHouse.absolute(location - 1);
		
		log.fine("From house " + fromHouse);
		log.fine("To house " + atHouse);
		return planetsInHouse.get(atHouse);
	}
	
	/** Location : 1 - 12 **/
	public Planet lordOf(int location){
		Rasi asc = getPlanetHouse(Planet.Ascendant);
		return asc.absolute(location - 1).owner();
	}
	
	public Planet lordOf(String location){
		return lordOf(Integer.parseInt(location));
	}
	
	/** Location : 1 - 12 **/
	public Rasi houseOf(int location){
		Rasi asc = getPlanetHouse(Planet.Ascendant);
		return asc.absolute(location - 1);
	}
	
	public Rasi houseOf(String location){
		return houseOf(Integer.parseInt(location));
	}
	
	public boolean isPlanetStronglyDisposed(Planet planet){
		return ( isPlanetExalted(planet) ||
				 isPlanetInOwnHouse(planet) ||
				 (isPlanetInKendraOrTrine(planet) && !isPlanetDebilitated(planet)));
	}
	
	public static boolean isKendraLocation(int location){
		return (location == 1 || location == 4 || location == 7 || location == 10);
	}
	
	public static boolean isTrineLocation(int location){
		return (location == 1 || location == 5 || location == 9);
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(varga);
		sb.append(" => ");
		sb.append(planetLocations);
		sb.append(" => ");
		sb.append(planetHouses);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		Map.Entry<Planet, Rasi> entry1 = newEntry(Planet.Sun, Rasi.Mesha);
		Map.Entry<Planet, Rasi> entry2 = newEntry(Planet.Moon, Rasi.Vrichika);
		Map.Entry<Planet, Rasi> entry3 = newEntry(Planet.Ascendant, Rasi.Kataka);
		
		Map<Planet,Rasi> planetHouses = newEnumMap(Planet.class,newEntry(Planet.Sun, Rasi.Mesha),newEntry(Planet.Ascendant, Rasi.Kanya));
		
		PlanetChart pc = new PlanetChart(Varga.Rasi, planetHouses);
		
		System.out.println(pc);
		
		/**Map.Entry<Planet, Rasi> entries[] = { CollectionUtil.newEntry(Planet.Sun, Rasi.Mesha), 
				CollectionUtil.newEntry(Planet.Moon, Rasi.Vrichika),
				CollectionUtil.newEntry(Planet.Ascendant, Rasi.kataka)
		};*/
	}
}
