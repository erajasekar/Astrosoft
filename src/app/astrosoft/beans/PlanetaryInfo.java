/**
 * PlanetaryInfo.java
 * Created On 2006, Feb 18, 2006 8:47:04 PM
 * @author E. Rajasekar
 */

package app.astrosoft.beans;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import app.astrosoft.beans.HousePosition.Bhava;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.Paksha;
import app.astrosoft.consts.Varga;
import app.astrosoft.consts.Karaka;
import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.core.VargaCharts;
import app.astrosoft.export.Exportable;
import app.astrosoft.export.Exporter;
import app.astrosoft.ui.table.DefaultColumnMetaData;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableRowData;
import app.astrosoft.util.AstroUtil;
import app.astrosoft.util.ComparableEntry;
import app.astrosoft.util.Mod;
import app.astrosoft.util.Utils;

public class PlanetaryInfo implements Exportable{

	private static final Logger log = Logger.getLogger(PlanetaryInfo.class.getName());
	
	/*
	 * public static enum Info{ Position, Direction, Rasi, Location,
	 * BhavaLocation, Nakshathra, Karaka; }
	 */
	private EnumMap<Planet, Double> planetPosition;

	private EnumMap<Planet, Double> planetRasiPosition;

	private EnumMap<Planet, Boolean> planetDirection;

	private EnumMap<Planet, Rasi> planetRasi;

	private EnumMap<Planet, Integer> planetLocation;

	private EnumMap<Planet, Bhava> planetBhava;

	private EnumMap<Planet, NakshathraPada> planetNakshathra;

	private EnumMap<Planet, Karaka> planetKaraka;

	private EnumMap<Varga,EnumMap<Planet,Integer>> divChart;
	
	// True means benefic... false means malefic
	private Map<Planet, Boolean> planetCharacter;

	private TableData<PlanetaryInfoRow> planateryInfoTableData;

	private DefaultColumnMetaData planateryInfoColumnMetaData;
	
	private Mod mod = new Mod(12);

	public PlanetaryInfo(EnumMap<Planet, Double> planetPosition,
			EnumMap<Planet, Boolean> planetDirection, HousePosition housePosition) {
		
		this.planetPosition = planetPosition;
		this.planetDirection = planetDirection;
		planetPosition.put(Planet.Ascendant, housePosition
				.getAscendantPosition());

		VargaCharts vc = new VargaCharts(planetPosition, housePosition);

		divChart = vc.getAllCharts();

		planetRasi = new EnumMap<Planet, Rasi>(Planet.class);

		planetNakshathra = new EnumMap<Planet, NakshathraPada>(Planet.class);

		planetBhava = new EnumMap<Planet, Bhava>(Planet.class);

		planetLocation = new EnumMap<Planet, Integer>(Planet.class);

		planetRasiPosition = new EnumMap<Planet, Double>(Planet.class);

		planetKaraka = new EnumMap<Planet, Karaka>(Planet.class);

		List<ComparableEntry<Planet, Double>> rasiPositions = new ArrayList<ComparableEntry<Planet, Double>>();

		int lagna = housePosition.getAscendant().ordinal() + 1;

		for (Planet p : Planet.planetsAsc()) {

			planetRasi.put(p, Rasi.ofIndex(divChart.get(Varga.Rasi).get(p) - 1));
			planetNakshathra.put(p, new NakshathraPada(planetPosition.get(p)));
			planetLocation.put(p, mod.sub(divChart.get(Varga.Rasi).get(p), lagna) + 1);
			planetBhava.put(p, housePosition.getBhava(mod.sub(divChart.get(Varga.Bhava).get(p), lagna) + 1));
			planetRasiPosition.put(p, planetPosition.get(p) % 30);
			if (!p.isNode() && !p.isAsc()) {
				rasiPositions.add(new ComparableEntry<Planet, Double>(p,
						planetRasiPosition.get(p)));
			}
		}

		rasiPositions = Utils.sortEntryList(rasiPositions, true);

		int i = 0;
		for (ComparableEntry<Planet, Double> entry : rasiPositions) {

			planetKaraka.put(entry.getKey(), Karaka.ofIndex(i++));
		}
		
		calcPlanetCharacter();

	}

	

	/**
	 * @return Returns the planetPosition.
	 */
	public EnumMap<Planet, Double> getPlanetPosition() {
		return planetPosition;
	}

	/**
	 * @return Returns the planetDirection.
	 */
	public EnumMap<Planet, Boolean> getPlanetDirection() {
		return planetDirection;
	}

	/**
	 * @return Returns the planetRasi.
	 */
	public EnumMap<Planet, Rasi> getPlanetRasi() {
		return planetRasi;
	}
	
	/**
	 * @return Returns the planetRasi.
	 */
	public EnumMap<Planet, Rasi> getPlanetRasi(Varga varga) {
		
		if (varga.equals(Varga.Rasi)){
			return planetRasi;
		}
		EnumMap<Planet, Rasi> planetInRasi = new EnumMap<Planet, Rasi>(Planet.class);
		
		for (Planet p : Planet.planetsAsc()) {
			
			planetRasi.put(p, Rasi.ofIndex(divChart.get(varga).get(p) - 1));
		}
		
		return planetInRasi;
	}

	/**
	 * @return Returns the planetLocation.
	 */
	public EnumMap<Planet, Integer> getPlanetLocation() {
		return planetLocation;
	}
	
	public EnumMap<Planet, Integer> getPlanetLocation(Varga varga) {
		
		if (varga.equals(Varga.Rasi)){
			return planetLocation;
		}
		
		int lagna = divChart.get(varga).get(Planet.Ascendant);
		
		log.fine("Lagna "  + lagna);
		
		EnumMap<Planet, Integer> location = new EnumMap<Planet, Integer>(Planet.class);
		
		for (Planet p : Planet.planetsAsc()) {
			
			location.put(p, mod.sub(divChart.get(varga).get(p), lagna) + 1);
		}
		
		return location;
	}

	/**
	 * @return Returns the planetBhava.
	 */
	public EnumMap<Planet, Bhava> getPlanetBhava() {
		return planetBhava;
	}

	/**
	 * @param planetNakshathra
	 *            The planetNakshathra to set.
	 */
	public void setPlanetNakshathra(EnumMap<Planet, NakshathraPada> nakshathara) {
		this.planetNakshathra = nakshathara;
	}

	/**
	 * @return Returns the planetNakshathra.
	 */
	public EnumMap<Planet, NakshathraPada> getPlanetNakshathra() {
		return planetNakshathra;
	}

	/**
	 * @return Returns the planetKaraka.
	 */
	public EnumMap<Planet, Karaka> getPlanetKaraka() {
		return planetKaraka;
	}

	public static EnumMap<Planet, Integer> positionToRasiNum(EnumMap<Planet, Double> position){
		
		EnumMap<Planet, Integer> planetRasiNum = new EnumMap<Planet, Integer>(Planet.class);
		
		for(Entry<Planet, Double> e : position.entrySet()){
			planetRasiNum.put(e.getKey(), (int)(e.getValue() / 30) + 1);
		}
		
		return planetRasiNum;
	}
	
	private void calcPlanetCharacter() {
		
		planetCharacter = new EnumMap<Planet, Boolean>(Planet.class);
		for(Planet p : Planet.subaPlanets()){
			planetCharacter.put(p, true);
		}
		for(Planet p : Planet.papaPlanets()){
			planetCharacter.put(p, false);
		}
		
		Paksha pak = Paksha.ofDeg(getPlanetPosition(Planet.Sun),getPlanetPosition(Planet.Moon));
		planetCharacter.put(Planet.Moon, pak.isShukla());
		
		//System.out.println(planetCharacter);
	}
	
	public Double getPlanetPosition(Planet planet) {

		return planetPosition.get(planet);
	}

	/**
	 * @return Returns the planetRasiPosition.
	 */
	public EnumMap<Planet, Double> getPlanetRasiPosition() {
		return planetRasiPosition;
	}

	public Double getPlanetRasiPosition(Planet planet) {
		return planetRasiPosition.get(planet);
	}

	public EnumMap<Varga, EnumMap<Planet, Integer>> getDivChart() {
		return divChart;
	}

	public EnumMap<Planet, Integer> getDivChart(Varga varga) {
		return divChart.get(varga);
	}
	
	public Map<Planet, Boolean> getPlanetCharacter() {
		return planetCharacter;
	}

	public TableData<PlanetaryInfoRow> getPlanateryInfoTableData() {

		if (planateryInfoTableData == null) {
			planateryInfoTableData = new PlanetaryInfoData();
		}
		return planateryInfoTableData;
	}

	public DefaultColumnMetaData getPlanateryInfoColumnMetaData() {

		if (planateryInfoColumnMetaData == null) {
			planateryInfoColumnMetaData = new DefaultColumnMetaData(
					AstrosoftTableColumn.Planet,
					AstrosoftTableColumn.Longitude, AstrosoftTableColumn.Rasi,
					AstrosoftTableColumn.NakshathraPada,
					AstrosoftTableColumn.JaiminiKaraka) {

				@Override
				public Class getColumnClass(AstrosoftTableColumn col) {

					switch(col){
						case Longitude: return Degree.class;
					}
					return super.getColumnClass(col);
				}
				
			};
			planateryInfoColumnMetaData.localizeColumns();
			
		}
		return planateryInfoColumnMetaData;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		for (Planet p : Planet.planetsAsc()) {
			sb.append(p + "\t" + AstroUtil.dms(planetPosition.get(p)) + "\t"
					+ planetRasiPosition.get(p) + "\t");
			if (planetKaraka.containsKey(p)) {
				sb.append(planetKaraka.get(p));
			}
			sb.append("\n");
		}

		sb.append("\n");
		sb.append("Charts\n" + VargaCharts.toString(divChart));
		sb.append("\n");
		return sb.toString();
	}

	private class PlanetaryInfoRow implements TableRowData {

		Planet row;

		public PlanetaryInfoRow(Planet row) {
			this.row = row;
		}

		public Object getColumnData(AstrosoftTableColumn col) {

			switch (col) {
				case Planet :
					return row;
				case Longitude :
					return planetPosition.get(row);
				case Rasi :
					return planetRasi.get(row);
				case NakshathraPada :
					return planetNakshathra.get(row);
				case JaiminiKaraka :
					return planetKaraka.get(row);
			}
			return null;
		}
	}

	private class PlanetaryInfoData implements TableData<PlanetaryInfoRow> {

		public PlanetaryInfoRow getRow(int index) {

			return new PlanetaryInfoRow(Planet.values()[index]);
		}

		public int getRowCount() {

			return 10;
		}

	}

	public void doExport(Exporter e) {
		e.export(this);
	}
}
