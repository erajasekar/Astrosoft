/**
 * Horoscope.java
 *
 * Created on December 14, 2002, 12:17 PM
 * @author  E. Rajasekar
 */
package app.astrosoft.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;

import org.w3c.dom.Document;

import swisseph.SweDate;
import app.astrosoft.beans.BirthData;
import app.astrosoft.beans.HousePosition;
import app.astrosoft.beans.NakshathraPada;
import app.astrosoft.beans.Place;
import app.astrosoft.beans.PlanetaryInfo;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.Ayanamsa;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.Paksha;
import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.Sex;
import app.astrosoft.consts.Thithi;
import app.astrosoft.consts.Varga;
import app.astrosoft.consts.Yoga;
import app.astrosoft.export.Exportable;
import app.astrosoft.export.Exporter;
import app.astrosoft.export.XMLHelper;
import app.astrosoft.pref.AstrosoftPref;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.table.DefaultColumnMetaData;
import app.astrosoft.ui.table.DefaultTable;
import app.astrosoft.ui.table.MapTableRow;
import app.astrosoft.ui.table.MapTableRowHelper;
import app.astrosoft.ui.table.Table;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableDataFactory;
import app.astrosoft.util.AstroUtil;
import app.astrosoft.util.SwissHelper;
import app.astrosoft.xps.yoga.YogaFinder;
import app.astrosoft.xps.yoga.YogaResults;

public class Horoscope implements  PreferenceChangeListener , Exportable {

	private static final Logger log = Logger.getLogger(Horoscope.class.getName());

	private BirthData birthData;

	private SwissHelper swissHelper;

	private double ayanamsa;

	private double sunrise;

	private double sunset;

	private HousePosition housePosition;

	private PlanetaryInfo planetaryInfo;

	private Vimshottari vimDasa;

	private Ashtavarga ashtaVarga;

	private Thithi thithi;

	private Paksha pak;

	private Yoga yoga;

	private ShadBala shadBala;

	private TableData<MapTableRow> infoTableData;

	private static DefaultColumnMetaData infoTableColumnMetaData;

	private String title;
	
	private boolean isBirthAtDay;
	
	private YogaResults yogaCombinations;

	/** Creates a new instance of Horoscope */
	public Horoscope(String name, int date, int month, int year, int hr,
			int min, Place place) {

		this(new BirthData(name, date, month, year, hr, min, 0, place));
	}

	public Horoscope(String name, int date, int month, int year, int hr,
			int min, double longi, double lati, double tz, String place) {
		this(name, date, month, year, hr, min, new Place(place, lati, longi, tz));
	}

	public Horoscope(BirthData birthData){

		this.birthData = birthData;
		swissHelper = new SwissHelper(birthData.birthSD());

		AstroSoft.getPreferences().addPreferenceChangeListener(this);

		// sw.swe_set_ephe_path( "d\\:\\\\AstroSoft" );
		calculate();
	}

	/**
	 * Constructs Horoscope from file. Returns null if file formatter is invalid.
	 *
	 * @param file
	 * @return Horoscope
	 */
	public static Horoscope createFromFile(String file) {

		Horoscope h = null;

		try{
			//h = valueOfXMLNode(XMLHelper.parseXML(file).getChildNodes().item(0));
			h = new Horoscope(BirthData.valueOfXMLNode(XMLHelper.parseXML(file).getChildNodes().item(0)));
		}catch(NullPointerException e){
			System.out.println(e);
		}
		if (h != null){
			return h;
		}

		//TODO: Fall through to read old file format. remove it once all files are converted
		try {

			if (file == null) {

				return null;
			}

			File f = new File(file);
			FileInputStream fis = new FileInputStream(f);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String name = br.readLine();
			int date = Integer.parseInt(br.readLine());
			int month = Integer.parseInt(br.readLine());
			int year = Integer.parseInt(br.readLine());
			int hr = Integer.parseInt(br.readLine());
			int min = Integer.parseInt(br.readLine());
			double longi = Double.parseDouble(br.readLine());
			double lati = Double.parseDouble(br.readLine());
			double tz = Double.parseDouble(br.readLine());
			String city = br.readLine();
			Place place = new Place(city, lati, longi, tz);

			fis.close();
			h = new Horoscope(name, date, month, year, hr, min, place);

			//Save as new format.

			h.saveToFile(file);

		} catch (Exception e) {

			return null;

		}
		return h;
	}

	/*public static Horoscope valueOfXMLNode(Node horoscopeNode){

		return new Horoscope(BirthData.valueOfXMLNode(horoscopeNode.getChildNodes().item(0)));
	}*/

	/*public Element toXMLElement(Document doc, String elementName){

		Element horElement = doc.createElement(elementName);
		horElement.appendChild(birthData.toXMLElement(doc));
		return horElement;
	}*/

	public void saveToFile(String fileName) {

		Document doc = XMLHelper.createDOM();

		//doc.appendChild(toXMLElement(doc, StringConsts.Horoscope.toString()));
		doc.appendChild(birthData.toXMLElement(doc));

		XMLHelper.saveXML(doc, fileName);

		}

	public void calculate() {

		calcPlanetaryInfo();
		//calcDashaBhukthis();
		//calcAshtaVarga();
		calcDetails();

		//TODO: Remove it after all testing
		//calcShadBala();
		//Reset lazy objects to null to get values updated
        infoTableData = null;
	}
	
	public void calculateAll() {

		calculate();
		calcDashaBhukthis();
		calcAshtaVarga();
		calcShadBala();
		
	}

	public void setAyanamsa(Ayanamsa ayanamsa) {

		swissHelper.setAyanamsa(ayanamsa);
		calculateAll();
	}

	private void calcSunRiseSet() {

		sunrise = AstroUtil.getSunRise(birthData.year(), birthData.month(), birthData.date(),
				birthData.longitude(), birthData.latitude(), birthData.timeZone());
		sunset = AstroUtil.getSunSet(birthData.year(), birthData.month(), birthData.date(),
				birthData.longitude(), birthData.latitude(), birthData.timeZone());
		
		if ((birthData.birthTime() > sunrise)
				&& (birthData.birthTime() < sunset)) {
			isBirthAtDay = true;
		} else {
			isBirthAtDay = false;
		}
	}

	public void calcHousePositions() {

		housePosition = swissHelper.calcHousePosition(birthData.longitude(), birthData.latitude());
	}

	public void calcPlanetaryInfo() {

		calcHousePositions();
		EnumMap<Planet, Double> planetaryPosition = swissHelper.getPlanetaryPosition();
		planetaryInfo = new PlanetaryInfo(planetaryPosition, swissHelper.getPlanetDirection(), housePosition);

		ayanamsa = swissHelper.getAyanamsa();
	}


	public void calcDashaBhukthis() {

		vimDasa = new Vimshottari(getPlanetaryPosition(Planet.Moon), birthData.birthDay());

		/*setVimDasha(v.getDashaString());
		setCurrentDasha(v.getCurrentDasha());
		setBirthDasaLord(v.getBirthDasaLord());
		setDasaBal(v.getDasaBal());*/
	}

	public void calcAshtaVarga() {

		ashtaVarga= new Ashtavarga(getDivChart(Varga.Rasi));
	}

	private void calcDetails() {

		calcSunRiseSet();

		thithi = Thithi.ofDeg(getPlanetaryPosition(Planet.Sun), getPlanetaryPosition(Planet.Moon));
		pak = Paksha.ofDeg(getPlanetaryPosition(Planet.Sun), getPlanetaryPosition(Planet.Moon));
		yoga = Yoga.ofDeg(getPlanetaryPosition(Planet.Sun), getPlanetaryPosition(Planet.Moon));
	}

	private void calcShadBala() {

		if (birthData.year() < 1900){
			log.warning("Year should be less than 1900");

		}else{
			shadBala = new ShadBala(planetaryInfo, housePosition, birthData, ayanamsa, sunrise, sunset, getPaksha());
		}

	}

	public void preferenceChange(PreferenceChangeEvent evt) {

		/*if(evt.getKey().equals(AstrosoftPref.Preference.Ayanamsa.name())){
			setAyanamsa(Enum.valueOf(Ayanamsa.class, evt.getNewValue()));
		}else */
		if(evt.getKey().equals(AstrosoftPref.Preference.Language.name())){
			this.languageChanged();
		}
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		int i = 0;

		sb.append("\n");
		//sb.append("cp " + getCurrentDasa());
		sb.append("\n");
		sb.append("Bhava\n");

		sb.append(housePosition);

		sb.append("\n");
		sb.append("Planetary Info\n");
		sb.append(planetaryInfo);
		sb.append("\n");
		sb.append("Thithi " + thithi
				+ " Paksha " + pak);
		sb.append("\n");

		sb.append("AshtaVarga \n");
		sb.append(ashtaVarga);
		sb.append("\n");
		sb.append("ShadBala \n");
		sb.append(shadBala);
		sb.append("\n");
		sb.append("Dasas\n");
		int j = 0;
        int k = 0;

        /*for ( i = 0; i < 9; i++ ) {

            for ( j = 0; j < 9; j++ ) {

                for ( k = 0; k < 9; k++ )
                	sb.append( getVimDasha()[i][j][k]  + "\t");

                sb.append("\n");
            }
            sb.append("\n");

        }*/
        sb.append(vimDasa);
        sb.append("\n");
		return sb.toString();

	}

	public HousePosition getHousePosition() {
		return housePosition;
	}

	public BirthData getBirthData() {
		return birthData;
	}

	public SweDate getBirthSD() {
		return birthData.birthSD();
	}

	public String getPersonName(){
		return birthData.name();
	}

	public Rasi getAscendant(){
		return housePosition.getAscendant();
	}

	public Rasi getRasi() {
		return planetaryInfo.getPlanetRasi().get(Planet.Moon);
	}

	public NakshathraPada getNakshathra() {
		return planetaryInfo.getPlanetNakshathra().get(Planet.Moon);
	}

	public PlanetaryInfo getPlanetaryInfo() {
		return planetaryInfo;
	}

	public EnumMap<Planet, Double> getPlanetaryPosition(){
		return planetaryInfo.getPlanetPosition();
	}

	public Double getPlanetaryPosition(Planet planet){
		return planetaryInfo.getPlanetPosition(planet);
	}

	public EnumMap<Planet, Integer> getPlanetaryLocation(){
		return planetaryInfo.getPlanetLocation();
	}

	public int getPlanetaryLocation(Planet p) {
		return planetaryInfo.getPlanetLocation().get(p);
	}


	public EnumMap<Planet, Rasi> getPlanetRasis() {
		return planetaryInfo.getPlanetRasi();
	}

	public EnumMap<Planet, Boolean> getPlanetDirection() {
		return planetaryInfo.getPlanetDirection();
	}

	public EnumMap<Varga, EnumMap<Planet, Integer>> getDivChart() {
		return planetaryInfo.getDivChart();
	}

	public EnumMap<Planet, Integer> getDivChart(Varga varga) {
		return planetaryInfo.getDivChart(varga);
	}

	public Thithi getThithi() {
		return thithi;
	}

	public Paksha getPaksha() {
		return pak;
	}

	public Yoga getYoga() {
		return yoga;
	}

	public double getAyanamsa() {
		return ayanamsa;
	}

	public double getSunrise() {
		return sunrise;
	}

	public double getSunset() {
		return sunset;
	}

	public boolean isBirthAtDay() {
		return isBirthAtDay;
	}
	
	public Sex getPersonSex(){
		return birthData.sex();
	}
	
	public YogaResults getYogaCombinations() {
		
		if (yogaCombinations == null){
			
			yogaCombinations = YogaFinder.getInstance().findYogas(this);
		}
		return yogaCombinations;
	}
	
	public ShadBala getShadBala() {

		if (shadBala == null){

			calcShadBala();
		}
		return shadBala;
	}

	public Ashtavarga getAshtaVarga() {
		
		if (ashtaVarga == null){
			calcAshtaVarga();
		}
		return ashtaVarga;
	}

	
	/**
	 * @return Returns the dasaBal.
	 */
	public String getDasaBal() {
		return getVimshottariDasa().getBalance();
	}

	/**
	 * @param vimDasa The vimDasa to set.

	private void setVimDasha(String[][][] vimDasa) {
		this.vimDasha = vimDasa;
	}*/

	/**
	 * @return Returns the vimDasa.
	 */
	public Vimshottari getVimshottariDasa() {
		
		if (vimDasa == null){
			calcDashaBhukthis();
		}
		return vimDasa;
	}

	/**
	 * @return Returns the currentDasha.
	 */
	public String getCurrentDasa() {

		VimDasa current = (VimDasa)getVimshottariDasa().getCurrent();
		if (current != null){
			return current.fullDasa();
		}else{
			return "No";
		}
	}

	public TableData<MapTableRow> getInfoTableData() {

		if(infoTableData == null){

			List<MapTableRow> rows = new ArrayList<MapTableRow>();

			MapTableRowHelper helper = new MapTableRowHelper(getInfoTableColumnMetaData());

			rows.add(helper.createRow(DisplayStrings.NAME_STR, birthData.name()));
			rows.add(helper.createRow(DisplayStrings.DOB_STR, birthData.birthDayString()));
			rows.add(helper.createRow(DisplayStrings.TOB_STR, AstroUtil.timeFormat(birthData.birthTime(),true)));
			rows.add(helper.createRow(DisplayStrings.PLACE_STR, birthData.place()));
			rows.add(helper.createRow(DisplayStrings.SID_TIME_STR, AstroUtil.dms(this.getHousePosition().getSiderealTime())));
			rows.add(helper.createRow(DisplayStrings.SUNRISE_SET_STR, AstroUtil.timeFormat(getSunrise()) + "/" + AstroUtil.timeFormat(getSunset())));
			rows.add(helper.createRow(DisplayStrings.NAK_STR, getNakshathra()));
			rows.add(helper.createRow(DisplayStrings.RASI_STR, getRasi()));

			rows.add(helper.createRow(DisplayStrings.LANGA_STR, getAscendant()));
			rows.add(helper.createRow(DisplayStrings.YOGA_STR, getYoga()));
			//rows.add(helper.createRow(DisplayStrings.RASI_STR, getRasi()));
			rows.add(helper.createRow(DisplayStrings.THITHI_STR, getThithi()));

			rows.add(helper.createRow(DisplayStrings.AYANAMSA_STR, AstroUtil.dms(getAyanamsa())));
			rows.add(helper.createRow(DisplayStrings.CURR_PD_STR, getCurrentDasa()));
			rows.add(helper.createRow(DisplayStrings.DASA_STR, getDasaBal()));

			infoTableData = TableDataFactory.getTableData(rows);
		}
		return infoTableData;
	}

	public static DefaultColumnMetaData getInfoTableColumnMetaData() {

		if(infoTableColumnMetaData == null){
			infoTableColumnMetaData = new DefaultColumnMetaData(AstrosoftTableColumn.Key, AstrosoftTableColumn.Value);
			infoTableColumnMetaData.localizeColumns();
		}
		return infoTableColumnMetaData;
	}

	public Table getHoroscopeInfo(){
		return new DefaultTable(getInfoTableData(), getInfoTableColumnMetaData());
	}

	public String getTitle() {

		if(title == null){
			StringBuilder sb = new StringBuilder("Horoscope of ");
			sb.append(birthData.name());
			sb.append(" born on ");
			sb.append(birthData.birthDayString());
			sb.append(" " + AstroUtil.timeFormat(birthData.birthTime()));
			sb.append(" at ");
			sb.append(birthData.place());
			title = sb.toString();
		}
		return title;
	}

	public static void main(String[] args) {

	/*	Horoscope h = new Horoscope("Raja", 11, 12, 1980, 1, 44,
				77 + (44.00 / 60.00), 11 + (22.00 / 60.00), 5.5, "Erode");
		h.setAyanamsa(Ayanamsa.LAHARI);
		h.calculateAll();

		System.out.println(h);
		// Horoscope h = new
		// Horoscope("Suba",31,3,1988,18,10,77+(44.00/60.00),11+(22.00/60.00),5.5,"Erode");
		// Horoscope h = new
		// Horoscope("Viji",7,8,1982,11,45,77+(44.00/60.00),11+(22.00/60.00),5.5,"Erode");
		/*
		 * Horoscope h = new Horoscope( "BV", 16, 10, 1918, 14, 26, 77 + ( 34.00 /
		 * 60.00 ), 13 + ( 0.00 / 60.00 ), 5.5, "Banglore" );
		 */





	/*	int i = 0;

		for (i = 0; i < 10; i++) {

			System.out.println(Planet.ofIndex(i) + " --> "
					+ AstroUtil.dms(h.planetPositions[i]) + " Dir -->"
					+ h.planetDir[i]);

		}

		System.out.println("cp " + h.currentDasha);
		System.out.println("Bhava");

		for (i = 0; i <= 12; i++) {

			System.out.println(i + " --> " + AstroUtil.dms(h.housePositions[i])
					+ " sandi " + AstroUtil.dms(h.bhavaSandhis[i]));

		}

		/*
		 * for(i = 0; i < VargaCharts.noOfCharts; i++){
		 * System.out.println(VargaCharts.chartNames[i]+"\n");

		for (int j = 0; j < 10; j++) {

			System.out.println(Planet.ofIndex(j) + " --> "
					+ Rasi.ofIndex(h.charts[0][j] - 1) + " - "
					+ h.charts[0][j]);

		}

		// }*/
		/*System.out.println("Thithi " + h.thithi
				+ " Paksha " + h.pak);

		for (i = 0; i < 7; i++)
			System.out.println(i + " --> " + h.karakas[i] + "<--> "
					+ Karaka.ofIndex(h.getKaraka( i )).toString() + " <--> "
					+ AstroUtil.dms(h.planetPositions[h.karakas[i]] % 30));*/

		runTests();
	}

	/*
	public void preferenceChange(PreferenceChangeEvent evt) {

		if(evt.getKey().equals(AstrosoftPref.Preference.Ayanamsa.name())){
			setAyanamsa(Enum.valueOf(Ayanamsa.class, evt.getNewValue()));
		}else if(evt.getKey().equals(AstrosoftPref.Preference.Language.name())){
			log.info("Language option changed");
			infoTableData = null;
			calcDashaBhukthis();
		}
	}*/

	public void languageChanged(){
		log.entering("Horoscope" , "languageChanged()");
		infoTableData = null;
		calcDashaBhukthis();
	}

	public static void runTests(){

		AstroSoft.getPreferences().setAyanamsa(Ayanamsa.LAHARI);
		Horoscope h = new Horoscope("Raja", 11, 12, 1980, 1, 44,
				77 + (44.00 / 60.00), 11 + (22.00 / 60.00), 5.5, "Erode");
		h.calculateAll();

		System.out.println("----------------------Raja----------------------");
		System.out.println(h);

		Horoscope b = new Horoscope("Elango", 17, 4, 1957, 7, 10,
				77 + (44.00 / 60.00), 11 + (22.00 / 60.00), 5.5, "Erode");

		b.calculateAll();
		System.out.println("----------------------Elango----------------------");
		System.out.println(b);

		Horoscope g = new Horoscope("Mani", 10, 8, 1960, 5, 30,
				77 + (44.00 / 60.00), 11 + (22.00 / 60.00), 5.5, "Erode");
		g.calculateAll();
		System.out.println("----------------------Mani----------------------");
		System.out.println(g);

		Horoscope s = new Horoscope("Suba",31,3,1988,18,10,77+(44.00/60.00),11+(22.00/60.00),5.5,"Erode");
		s.calculateAll();
		System.out.println("----------------------Suba----------------------");
		System.out.println(s);
		h = new Horoscope("Viji",7,8,1982,11,45,77+(44.00/60.00),11+(22.00/60.00),5.5,"Erode");
		h.calculateAll();
		System.out.println("----------------------Viji----------------------");
		System.out.println(h);
	}

	public void doExport(Exporter e) {
		e.export(this);
	}

	public String createDocumentName() {

		return AstroSoft.getPreferences().getAstrosoftFilesDir() + birthData.name() + "_Horoscope.pdf";
	}


}
