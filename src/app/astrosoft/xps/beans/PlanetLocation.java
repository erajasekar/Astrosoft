/**
 * PlanetPosition.java
 * Created On 2007, Oct 16, 2007 1:46:28 PM
 * @author E. Rajasekar
 */

package app.astrosoft.xps.beans;

import app.astrosoft.consts.Planet;

public class PlanetLocation {

	private Planet planet;
	private int location;
	
	public PlanetLocation(Planet planet, int location) {
		this.planet = planet;
		this.location = location;
	}
	
	public Planet getPlanet() {
		return planet;
	}
	
	public int getLocation() {
		return location;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(planet.name());
		sb.append("(");
		sb.append(location);
		sb.append(") ");
		return sb.toString();
	}
	
}
