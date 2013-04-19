/**
 * PlanetCharacter.java
 * Created On 2007, Oct 22, 2007 1:23:45 PM
 * @author E. Rajasekar
 */

package app.astrosoft.beans;

import java.util.Map;
import java.util.Set;

import app.astrosoft.consts.Planet;

public class PlanetCharacter {

	private Map<Planet, Boolean> planetCharacter;
	
	public PlanetCharacter(Map<Planet, Boolean> planetCharacter){
		this.planetCharacter = planetCharacter;
	}
	
	public boolean isBenefic(Planet planet){
		if (planetCharacter.containsKey(planet)){
			return planetCharacter.get(planet);
		}
		
		if(planet.isAsc()) {
			return true;
		}
		throw new  IllegalArgumentException("isBenefic() is not applicable for " +  planet.name());
	}
	
	public boolean isMalefic(Planet planet){
		if (planetCharacter.containsKey(planet)){
			return (!planetCharacter.get(planet));
		}
		
		if(planet.isAsc()){
			return true;
		}
		throw new  IllegalArgumentException("isMalefic() is not applicable for " +  planet.name());
	}
	
	public boolean isAllBenefics(Set<Planet> planets){
		
		if (planets.isEmpty())
			return false;
		
		for(Planet p : planets){
			if (!isBenefic(p)){
				return false;
			}
		}
		return true;
	}
	
	public boolean isAnyBenefics(Set<Planet> planets){
		
		if (planets.isEmpty())
			return false;
		
		for(Planet p : planets){
			if (isBenefic(p)){
				return true;
			}
		}
		return false;
	}
	
	public boolean isAllMalefics(Set<Planet> planets){
		
		if (planets.isEmpty())
			return false;
		
		for(Planet p : planets){
			if (!isMalefic(p)){
				return false;
			}
		}
		return true;
	}
	
	public boolean isAnyMalefics(Set<Planet> planets){
		
		if (planets.isEmpty())
			return false;
		
		for(Planet p : planets){
			if (isMalefic(p)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return planetCharacter.toString();
	}
}
