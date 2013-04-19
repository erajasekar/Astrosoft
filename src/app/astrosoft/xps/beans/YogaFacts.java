/**
 * YogaFacts.java
 * Created On 2007, Nov 12, 2007 2:30:00 PM
 * @author E. Rajasekar
 */

package app.astrosoft.xps.beans;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Logger;

import app.astrosoft.beans.PlanetCharacter;
import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Sex;
import app.astrosoft.core.Horoscope;

public class YogaFacts {
	
	private static final Logger log = Logger.getLogger(YogaFacts.class.getName());

	private static final double POWERFUL_PLANET_THRESHOLD = 75.0;
	
	private static final NumberFormat format  = new DecimalFormat("000");
	
	private PlanetCharacter character;
	
	private Set<Planet> powerfulPlanets; 
	
	private Map<Planet, Double> planetStrength;
	
	private boolean isBirthAtDay;
	
	private Sex personSex;

	public YogaFacts() {
		
	}

	public YogaFacts(Horoscope h) {
		
		PlanetCharacter character = new PlanetCharacter(h.getPlanetaryInfo().getPlanetCharacter());
		setCharacter(character);
		setPlanetStrength(h.getShadBala().getStrengthPer());
		setPowerfulPlanets(planetStrength);
		setBirthAtDay(h.isBirthAtDay());
		setPersonSex(h.getPersonSex());
	}
	
	public void setPlanetStrength(Map<Planet, Double> planetStrength) {
		this.planetStrength = planetStrength;
	}
	
	public void setCharacter(PlanetCharacter character) {
		this.character = character;
	}

	public PlanetCharacter character() {
		return character;
	}

	public void setPowerfulPlanets(Map<Planet, Double> strengthPercent){
		
		powerfulPlanets = EnumSet.noneOf(Planet.class);
		
		for(Entry<Planet,Double> entry : strengthPercent.entrySet()){
			
			if (entry.getValue().doubleValue() >= POWERFUL_PLANET_THRESHOLD){
				powerfulPlanets.add(entry.getKey());
			}
		}
		
		log.fine(powerfulPlanets.toString());
	}
	
	public void setPowerfulPlanets(Set<Planet> powerfulPlanets) {
		this.powerfulPlanets = powerfulPlanets;
	}

	public Set<Planet> powerfulPlanets() {
		return powerfulPlanets;
	}
	
	public boolean isPlanetPowerful(Planet planet){
		return powerfulPlanets.contains(planet);
	}
	
	public boolean isAnyPlanetPowerful(Set<Planet> planets){
		
		for(Planet p : planets){
			if (isPlanetPowerful(p)){
				return true;
			}
		}
		
		return false;
	}

	public void setBirthAtDay(boolean isBirthAtDay) {
		this.isBirthAtDay = isBirthAtDay;
	}

	public boolean isBirthAtDay() {
		
		System.out.println("isBirthAtDay--> " + isBirthAtDay);
		return isBirthAtDay;
	}

	public void setPersonSex(Sex sex) {
		this.personSex = sex;
	}

	public boolean isMale() {
		
		if (personSex != null){
			return personSex.isMale();
		}
		
		return false;
	}
	
	public boolean isFemale() {
		if (personSex != null){
			return personSex.isFemale();
		}
		
		return false;
	}
	
	public String getPlanetStrength(Planet planet){
		
		if (planetStrength == null){
			return "100%";
		}
		return format.format(planetStrength.get(planet)) + "%";
	}
	
}

