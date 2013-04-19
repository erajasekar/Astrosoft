/**
 * SwissHelper.java
 * Created On 2006, Feb 22, 2006 3:43:29 PM
 * @author E. Rajasekar
 */

package app.astrosoft.util;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import app.astrosoft.beans.HousePosition;
import app.astrosoft.consts.AstroConsts;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.Ayanamsa;
import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.core.Ephemeris.EphData;
import app.astrosoft.ui.AstroSoft;
import swisseph.DblObj;
import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;

public class SwissHelper {

	public static final int HOUSE_SYSTEM = (int)'P';
	public static final int HOUSE_FLAG = SweConst.SEFLG_SIDEREAL;
	
	private SwissEph sw;
	
	private SweDate sweDate;
	
	private EnumMap<Planet, Double> planetPos;
	
	private EnumMap<Planet, Boolean> isReverse;

	public SwissHelper(SweDate sweDate) {
		this();
		this.sweDate = sweDate;
	}
	
	/**
	 * 
	 * @param year
	 * @param month
	 * @param date
	 * @param birthTime - Should be in GMT
	 */
	public SwissHelper(int year, int month, int date, double birthTime) {
		this(new SweDate(year, month, date, birthTime));
	}
	
	public SwissHelper() {
		sw = new SwissEph();
		sw.swe_set_sid_mode(AstroSoft.getPreferences().getAyanamsa().ayaValue(), 0.0, 0.0);
	}

	public void setSweDate(SweDate sweDate) {
		this.sweDate = sweDate;
		calcPlanetaryPosition();
	}
	
	public void setAyanamsa(Ayanamsa ayanamsa) {

		sw.swe_set_sid_mode(ayanamsa.ayaValue(), 0.0, 0.0);
		calcPlanetaryPosition();
	}
	
	public void calcPlanetaryPosition() {
		calcPlanetaryPosition(Planet.allPlanets());
	}

	public void calcPlanetaryPosition(Set<Planet> planets){
		
		planetPos = new EnumMap<Planet, Double>(Planet.class);
		
		isReverse = new EnumMap<Planet, Boolean>(Planet.class);
		
		double[] res = new double[6];
		StringBuffer sbErr = new StringBuffer();
		
		for (Planet planet : planets) {

			int rc = sw.swe_calc_ut(sweDate.getJulDay(), planet.planetNo(),
					AstroConsts.iflag, res, sbErr);
			planetPos.put(planet, res[0]);

			if ((res[3] < 0) && (!Planet.isNode(planet))) {

				isReverse.put(planet, true);
			}
		}

		// No-way to compute ketu pos from swisseph
		Double rahuPos = planetPos.get(Planet.Rahu);
		
		if (rahuPos != null) {
			double ketuPos = ( rahuPos + 180.00) % 360;
			planetPos.put(Planet.Ketu, ketuPos);
		}
		//System.out.println(sbErr);
	}
	
	public HousePosition calcHousePosition(double birthLong, double birthLat) {
		
		double[] ac = new double[10];
		double[] positions = new double[13];

		sw.swe_houses(sweDate.getJulDay(), HOUSE_FLAG, birthLat, birthLong, HOUSE_SYSTEM,
				positions, ac);

		//System.out.println()
		HousePosition housePosition = new HousePosition(positions);
		housePosition.setSiderealTime((double) (ac[2] / 15));
		
		return housePosition;
	}
	
	public Rasi getAscendant(double birthLong, double birthLat){
		double[] ac = new double[10];
		double[] positions = new double[13];

		sw.swe_houses(sweDate.getJulDay(), HOUSE_FLAG, birthLat, birthLong, HOUSE_SYSTEM,
				positions, ac);
		
		return Rasi.ofDeg(positions[1]);
	}
	
	public double getPlanetSpeed(Planet planet){
	
		double[] res = new double[6];
		StringBuffer sbErr = new StringBuffer();
		sw.swe_calc_ut(sweDate.getJulDay(), planet.planetNo(), AstroConsts.iflag, res, sbErr);
		return res[3];
	}
	
	public double getAyanamsa(){
		return sw.swe_get_ayanamsa_ut(sweDate.getJulDay());
		
	}
	
	public EnumMap<Planet, Double> getPlanetaryPosition() {
		
		if(planetPos == null){
			calcPlanetaryPosition();
		}
		return planetPos;
	}
	
	public EnumMap<Planet, Boolean> getPlanetDirection() {
		if(isReverse == null){
			calcPlanetaryPosition();
		}
		return isReverse;
	}
	
	public EnumMap<Planet, Double> getPlanetaryPosition(Set<Planet> planets) {
		
		if(planetPos == null){
			calcPlanetaryPosition(planets);
		}
		return planetPos;
	}

	public EnumMap<Planet, EphData> getEphData(){
		
		calcPlanetaryPosition();
		EnumMap<Planet, EphData> planetEphData = new EnumMap<Planet, EphData>(Planet.class);
		
		for(Planet planet : planetPos.keySet()){

			EphData ephData = new EphData(planetPos.get(planet), isReverse.get(planet));
			planetEphData.put(planet, ephData);
        }
		return planetEphData;
	}
	
	


	public SweDate getSweDate() {
		return sweDate;
	}


	public static double calcNatonnataBalaDeg(SweDate birthSD, double birthTime) {
		
		SwissEph sw = new SwissEph(  );

        //sw.swe_set_ephe_path("d\\:\\\\AstroSoft");
        double et = birthSD.getJulDay(  ) + birthSD.getDeltaT(  );
        DblObj E = new DblObj(  );
        StringBuffer sbErr = new StringBuffer(  );
        int diff = sw.swe_time_equ( et, E, sbErr );
        double EqnOfTime = E.val * 24 * -1;
        double bTimeDeg = ( birthTime + EqnOfTime ) * 15;

        if ( bTimeDeg > 180 ) {
            bTimeDeg = 360 - bTimeDeg;
        }
		return bTimeDeg;
	}
	
	public TransitHelper getTransitHelper(Planet planet){
		return new TransitHelper(planet, sw);
	}
	
	public static void main(String[] args) {
		
		SwissHelper sh = new SwissHelper(1980, 12, 11, (1 + (44.00 / 60.00) - 5.5));
		//SwissHelper sh = new SwissHelper(1960, 8, 10, (5 + (30.00 / 60.00) - 5.5));
		System.out.println(sh.getPlanetaryPosition());
		
		HousePosition housePos = sh.calcHousePosition(77 + (44.00 / 60.00), 11 + (22.00 / 60.00)); 
		System.out.println(housePos);
		
		for(Map.Entry<Planet, Double> e : sh.planetPos.entrySet()){
			System.out.println(e.getKey() + " -> " + housePos.locateHouse(e.getValue()));
		}
		
		System.out.println(Utils.sortMap(sh.getPlanetaryPosition().entrySet(), true));
	}
}
