/**
 * XmlConsts.java
 * Created On 2007, Jun 10, 2007 11:47:18 PM
 * @author E. Rajasekar
 *
 */

package app.astrosoft.consts;

import javax.xml.namespace.QName;


public interface XmlConsts {

	public static final String DateTime = "DateTime";
	public static final String Sex = "Sex";
	public static final String Place = "Place";
	public static final String State = "State";
	public static final String Country = "Country";
	public static final String City = "City";
	public static final String Longitude = "Longitude";
	public static final String Latitude = "Latitude";
	public static final String TimeZone = "TimeZone";
	public static final String dir = "dir";
	public static final String Compactibility = "Compactibility";
	public static final String Rasi = "Rasi";
	public static final String Nakshathra = "Nakshathra";
	public static final String hasHoroscope = "hasHoroscope";
	public static final String BirthData = "BirthData";
	public static final String Name = "Name";
	public static final String BoyData = "BoyData";
	public static final String GirlData = "GirlData";
	public static final String XML_NS = "http://www.w3.org/2001/XMLSchema-instance";
	public static final String ASHTAVARGA_XML_NS = "Ashtavarga";
	public static final String Number = "No";
	public static final String Title = "Title";
	public static final String Dasa = "Dasa";
	public static final String Period = "Period";
	public static final String Location = "Location";
	public static final String Definition = "Definition";
	public static final String Results = "Results";

	public static final QName ASTROSOFT_TAG = new QName(XmlConsts.XML_NS, "Astrosoft");

	public static final QName HOROSCOPE_TAG = new QName(XML_NS, "Horoscope");
	public static final QName VARGA_CHART_TAG = new QName(XML_NS, "Varga-Chart");
	public static final QName CHART_TAG = new QName(XML_NS, "Chart");
	public static final QName HOUSE_TAG = new QName(XML_NS, "House");
	public static final QName ASHTVARGA_CHART_TAG = new QName(XML_NS, "Chart","av");
	public static final QName ASHTVARGA_HOUSE_TAG = new QName(XML_NS, "House", "av");
	public static final QName PLANET_TAG = new QName(XML_NS, "Planet");
	public static final QName PANCHANG_TAG = new QName(XML_NS, "Panchang");
	public static final QName MONTH_TAG = new QName(XML_NS, "Month");
	public static final QName PANCHANG_INFO_TAG = new QName(XML_NS, "Panchang-Information");

	public static final QName HOROSCOPE_INFO_TAG = new QName(XML_NS, "Horoscope-Information");
	public static final QName INFO_TAG = new QName(XML_NS, "Information");
	public static final QName PLANETARY_POS_TAG = new QName(XML_NS, "Planetary-Position");
	public static final QName PLANET_POS_TAG = new QName(XML_NS, "Planet-Position");
	public static final QName BHAVA_POSITIONS_TAG = new QName(XML_NS, "Bhava-Positions");
	public static final QName BHAVA_POS_TAG = new QName(XML_NS, "Bhava-Position");
	public static final QName ASHTAVARGAS_TAG = new QName(XML_NS, "Ashtavargas");
	public static final QName ASHTAVARGA_TAG = new QName(XML_NS, "Ashtavarga");
	public static final QName GUNAHARAS_TAG = new QName(XML_NS, "Gunaharas");
	public static final QName GUNAHARA_TAG = new QName(XML_NS, "Gunahara");
	public static final QName SHADBALAS_TAG = new QName(XML_NS, "Shadbalas");
	public static final QName SHADBALA = new QName(XML_NS, "Shadbala");
	public static final QName STHANABALA_TAG = new QName(XML_NS, "Sthanabala");
	public static final QName KALABALA_TAG = new QName(XML_NS, "Kalabala");
	public static final QName BHAVABALA_TAG = new QName(XML_NS, "Bhavabala");
	public static final QName BALA_TAG = new QName(XML_NS, "Bala");
	public static final QName VIM_DASA_TAG = new QName(XML_NS, "Vimshottari-Dasas");
	public static final QName MAJOR_DASA_TAG = new QName(XML_NS, "Major-Dasa");
	public static final QName SUB_DASA_TAG = new QName(XML_NS, "Sub-Dasa");
	public static final QName ANTHARA_DASA_TAG = new QName(XML_NS, "Anthara-Dasa");
	public static final QName DASA_TAG = new QName(XML_NS, "Dasa");
	public static final QName COMPACTIBILITY_TAG = new QName(XML_NS, Compactibility);
	public static final QName BOY_TAG = new QName(XML_NS, "Boy");
	public static final QName GIRL_TAG = new QName(XML_NS, "Girl");
	public static final QName BOY_INFO_TAG = new QName(XML_NS, "Boy-Information");
	public static final QName GIRL_INFO_TAG = new QName(XML_NS, "Girl-Information");
	public static final QName KUTA_ANALYSIS_TAG = new QName(XML_NS, "Kuta-Analysis");
	public static final QName KUTA_MATCH_TAG = new QName(XML_NS, "Kuta-Match");
	public static final QName DOSHA_ANALYSIS_TAG = new QName(XML_NS, "Dosha-Analysis");
	public static final QName PLANET_DOSHA_TAG = new QName(XML_NS, "Planet-Dosha");


	public static final QName TABLE_DATA_TAG = new QName(XML_NS, "Table-Data");
	public static final QName ROW_TAG = new QName(XML_NS, "row");
	public static final QName COLUMN_TAG = new QName(XML_NS, "column");
	


}
