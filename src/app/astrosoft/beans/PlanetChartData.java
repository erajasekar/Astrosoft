/**
 * ChartData.java
 * Created On 2006, Mar 30, 2006 7:03:52 PM
 * @author E. Rajasekar
 */

package app.astrosoft.beans;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.attribute.standard.Chromaticity;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.events.XMLEvent;

import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.DisplayFormat;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.Varga;
import app.astrosoft.consts.XmlConsts;
import app.astrosoft.core.Horoscope;
import app.astrosoft.export.HoroscopeExporter;
import app.astrosoft.export.StaxProcessor;
import app.astrosoft.export.Exportable;
import app.astrosoft.export.Exporter;
import app.astrosoft.ui.table.Cell;
import app.astrosoft.ui.table.ColumnMetaData;
import app.astrosoft.ui.table.DefaultColumnMetaData;
import app.astrosoft.ui.table.Table;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableRowData;
import app.astrosoft.util.Utils;

public class PlanetChartData extends AbstractChartData implements Exportable {

	private static final Logger log = Logger.getLogger(PlanetChartData.class.getName());
	
	private EnumMap<Planet, Boolean> planetDir;
	
	private EnumMap<Rasi, List<Planet>> planetsInRasi;
	
	private Varga varga;
	
	public PlanetChartData(Varga varga, PlanetaryInfo planetaryInfo) {
		
		this(varga,	planetaryInfo.getDivChart(varga),planetaryInfo.getPlanetDirection());
	}
	
	public PlanetChartData(Varga varga, EnumMap<Planet, Integer> planetPos, EnumMap<Planet, Boolean> planetDir){
		super(varga.toString());
		this.varga = varga; 
		this.planetDir = planetDir;
		this.ascendant = Rasi.ofIndex(planetPos.get(Planet.Ascendant) - 1);
		planetsInRasi = calcPlanetsInRasi(planetPos);
	}
	
	public static EnumMap<Rasi, List<Planet>> calcPlanetsInRasi(EnumMap<Planet, Integer> planetPosition){
		
		EnumMap<Rasi, List<Planet>> planetsInRasi = new EnumMap<Rasi, List<Planet>>(Rasi.class);
		
		List<Planet> planetList = null;
		
		for (Planet p : Planet.planetsAsc()) {
			Rasi r = Rasi.ofIndex(planetPosition.get(p) - 1);
			
			if (planetsInRasi.containsKey(r)){
				planetList = planetsInRasi.get(r);
				planetList.add(p);
			}else{
				planetList = new ArrayList<Planet>();
				planetList.add(p);
				planetsInRasi.put(r, planetList);
			}	
		}
		
		return planetsInRasi;
	}
	
	public Set<Rasi> getHouses() {
		
		return planetsInRasi.keySet();
	}
	
	

	public Map<Cell, Planet> getCells(List<Planet> planets){
		
		Map<Cell, Planet> cells = new HashMap<Cell, Planet>();
		
		if(planets == null || planets.size() == 0){
			return cells;
		}
		
		
		switch(planets.size()){
			
			case 1: cells.put(new Cell(1,AstrosoftTableColumn.C2), planets.get(0));
					break;
					
			case 2: cells.put(new Cell(0,AstrosoftTableColumn.C1), planets.get(0));
					cells.put(new Cell(2,AstrosoftTableColumn.C3), planets.get(1));
					break;
					
			case 3: cells.put(new Cell(0,AstrosoftTableColumn.C2), planets.get(0));
					cells.put(new Cell(2,AstrosoftTableColumn.C1), planets.get(1));
					cells.put(new Cell(2,AstrosoftTableColumn.C3), planets.get(2));
					break;
					
			case 4: cells.put(new Cell(0,AstrosoftTableColumn.C1), planets.get(0));
					cells.put(new Cell(0,AstrosoftTableColumn.C3), planets.get(1));
					cells.put(new Cell(2,AstrosoftTableColumn.C1), planets.get(2));
					cells.put(new Cell(2,AstrosoftTableColumn.C3), planets.get(3));
					break;
					
			case 5: cells.put(new Cell(0,AstrosoftTableColumn.C1), planets.get(0));
					cells.put(new Cell(0,AstrosoftTableColumn.C3), planets.get(1));
					cells.put(new Cell(2,AstrosoftTableColumn.C1), planets.get(2));
					cells.put(new Cell(2,AstrosoftTableColumn.C3), planets.get(3));
					cells.put(new Cell(1,AstrosoftTableColumn.C2), planets.get(4));
					break;
					
			case 6: cells.put(new Cell(0,AstrosoftTableColumn.C1), planets.get(0));
					cells.put(new Cell(0,AstrosoftTableColumn.C3), planets.get(1));
					cells.put(new Cell(2,AstrosoftTableColumn.C1), planets.get(2));
					cells.put(new Cell(2,AstrosoftTableColumn.C3), planets.get(3));
					cells.put(new Cell(0,AstrosoftTableColumn.C2), planets.get(4));
					cells.put(new Cell(2,AstrosoftTableColumn.C2), planets.get(5));
					break;
					
			case 7: cells.put(new Cell(0,AstrosoftTableColumn.C1), planets.get(0));
					cells.put(new Cell(0,AstrosoftTableColumn.C3), planets.get(1));
					cells.put(new Cell(2,AstrosoftTableColumn.C1), planets.get(2));
					cells.put(new Cell(2,AstrosoftTableColumn.C3), planets.get(3));
					cells.put(new Cell(0,AstrosoftTableColumn.C2), planets.get(4));
					cells.put(new Cell(2,AstrosoftTableColumn.C2), planets.get(5));
					cells.put(new Cell(1,AstrosoftTableColumn.C2), planets.get(6));
					break;
					
			case 8: cells.put(new Cell(0,AstrosoftTableColumn.C1), planets.get(0));
					cells.put(new Cell(0,AstrosoftTableColumn.C3), planets.get(1));
					cells.put(new Cell(2,AstrosoftTableColumn.C1), planets.get(2));
					cells.put(new Cell(2,AstrosoftTableColumn.C3), planets.get(3));
					cells.put(new Cell(0,AstrosoftTableColumn.C2), planets.get(4));
					cells.put(new Cell(2,AstrosoftTableColumn.C2), planets.get(5));
					cells.put(new Cell(1,AstrosoftTableColumn.C1), planets.get(6));
					cells.put(new Cell(1,AstrosoftTableColumn.C3), planets.get(7));
					break;
					
			case 9: 
			default:cells.put(new Cell(0,AstrosoftTableColumn.C1), planets.get(0));
					cells.put(new Cell(0,AstrosoftTableColumn.C3), planets.get(1));
					cells.put(new Cell(0,AstrosoftTableColumn.C2), planets.get(4));
					cells.put(new Cell(2,AstrosoftTableColumn.C1), planets.get(2));
					cells.put(new Cell(2,AstrosoftTableColumn.C3), planets.get(3));
					cells.put(new Cell(2,AstrosoftTableColumn.C2), planets.get(5));
					cells.put(new Cell(1,AstrosoftTableColumn.C1), planets.get(6));
					cells.put(new Cell(1,AstrosoftTableColumn.C2), planets.get(7));
					cells.put(new Cell(1,AstrosoftTableColumn.C3), planets.get(8));
					break;
		}
		
		return cells;
		
	}
	
	/* (non-Javadoc)
	 * @see app.astrosoft.beans.ChartData#getChartHouseTable(app.astrosoft.consts.Rasi)
	 */
	public Table getChartHouseTable(Rasi rasi){
		return new ChartHouseTable(rasi);
	}
	
	public EnumMap<Rasi, List<Planet>> getPlanetsInRasi() {
		return planetsInRasi;
	}
	

	@Override
	public DefaultColumnMetaData getHouseTableColMetaData() {
		return new DefaultColumnMetaData(AstrosoftTableColumn.chartHouseCols()){
			@Override
			public Class getColumnClass(AstrosoftTableColumn col) {
				
				return Planet.class;
			}
		};
		
	}
	
	public boolean highlightRetrogrades(){
		if (varga == Varga.Rasi || varga == Varga.Navamsa || varga == Varga.Bhava){
			return true;
		}else{
			return false;
		}
	}

	public EnumMap<Planet, Boolean> getPlanetDir() {
		return planetDir;
	}
	
	private class ChartHouseTable implements Table{
		
		private Map<Cell, Planet> cells;

		public ChartHouseTable(Rasi rasi) {
			
			List<Planet> planets = planetsInRasi.get(rasi);
			cells = getCells(planets);
		}
		public TableData<ChartHouseRow> getTableData() {
			
			return new ChartHouseData(cells);
		}

		public ColumnMetaData getColumnMetaData() {
			return colMetaData;
		}
		
	}
	
	private class ChartHouseData implements TableData<ChartHouseRow>{

		private Map<Cell, Planet> cells;
		
		
		public ChartHouseData(Map<Cell, Planet> cells) {
			this.cells = cells;
			
		}
		public ChartHouseRow getRow(int index) {
			
			return new ChartHouseRow(index, cells);
		}

		public int getRowCount() {
			
			return 3;
		}
		
	}
	
	private class ChartHouseRow implements TableRowData{

		private Map<Cell, Planet> cells;
		private int row;
		
		
		public ChartHouseRow(int row, Map<Cell, Planet> cells) {
			this.cells = cells;
			this.row = row;
			
		}
		
		public Object getColumnData(AstrosoftTableColumn col) {
			
			Planet data = null;
			Cell cell = new Cell(row, col);
			
			if (cells.containsKey(cell)){
				
				data = cells.get(cell);
			}
			
			return data;
		}
		
	}
	
	public void doExport(Exporter e){
		
			e.export(this);
	}
	
	public static void main(String[] args) {
		Horoscope h = new Horoscope("Raja" , 11, 12, 1980, 1, 44, Place.getDefault());
		
		EnumMap<Planet, Integer> pos = new EnumMap<Planet, Integer>(Planet.class);
		
		pos.put(Planet.Sun, 1);
    	pos.put(Planet.Moon, 1);
    	pos.put(Planet.Mars, 1);
    	pos.put(Planet.Mercury, 1);
    	pos.put(Planet.Jupiter, 1);
    	pos.put(Planet.Venus, 1);
    	pos.put(Planet.Saturn, 1);
    	pos.put(Planet.Rahu, 1);
    	pos.put(Planet.Ketu, 1);
    	pos.put(Planet.Ascendant, 1);
    	
		/*for (Planet p : Planet.planetsAsc()) {
			planetPos.put(p, 1);
		}*/
		//PlanetChartData cd = new PlanetChartData("Rasi" , h.getDivChart(Varga.Rasi), h.getPlanetDirection(), DisplayFormat.FULL_NAME);
    	
		
    	PlanetChartData cd = new PlanetChartData(Varga.Rasi , h.getPlanetaryInfo());
		
		
		//System.out.println(cd);
	}
	
}
