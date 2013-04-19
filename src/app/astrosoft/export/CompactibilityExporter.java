/**
 * CompactibilityExporter.java
 * Created On 2007, Jul 5, 2007 12:12:11 PM
 * @author E. Rajasekar
 */

package app.astrosoft.export;

import java.util.logging.Level;
import java.util.logging.Logger;

import app.astrosoft.beans.PlanetChartData;
import app.astrosoft.consts.Ayanamsa;
import app.astrosoft.consts.Language;
import app.astrosoft.consts.Varga;
import app.astrosoft.consts.XmlConsts;
import app.astrosoft.core.Compactibility;
import app.astrosoft.core.Horoscope;
import app.astrosoft.pref.AstrosoftPref;
import app.astrosoft.ui.AstroSoft;

public class CompactibilityExporter extends AbstractExporter {

	private static final Logger log = Logger.getLogger(CompactibilityExporter.class.getName());

	public CompactibilityExporter(String file) {
		super(file);
	}

	public void export(Compactibility compactibility) {

		try {

			//Create document and write horoscope tag
			xmlWriter.add(xmlef.createStartDocument());
			xmlWriter.add(xmlef.createStartElement(XmlConsts.ASTROSOFT_TAG, null,null));
			xmlWriter.add(xmlef.createNamespace("xsi",XmlConsts.XML_NS));
			xmlWriter.add(xmlef.createStartElement(XmlConsts.COMPACTIBILITY_TAG, null,null));
			xmlWriter.add(xmlef.createAttribute(XmlConsts.Title, compactibility.getTitle()));
			xmlWriter.add(xmlef.createAttribute(XmlConsts.hasHoroscope, String.valueOf(compactibility.hasHoroscope())));

			xmlWriter.add(xmlef.createStartElement(XmlConsts.BOY_TAG, null,null));
			exportTableData(compactibility.getBoyInfo(), XmlConsts.HOROSCOPE_INFO_TAG, XmlConsts.INFO_TAG);

			if(compactibility.hasHoroscope()) {
				export(new PlanetChartData(Varga.Rasi, compactibility.getBoyPlanetaryInfo()));
				export(new PlanetChartData(Varga.Navamsa, compactibility.getBoyPlanetaryInfo()));
			}
			xmlWriter.add(xmlef.createEndElement(XmlConsts.BOY_TAG, null));

			xmlWriter.add(xmlef.createStartElement(XmlConsts.GIRL_TAG, null,null));
			exportTableData(compactibility.getGirlInfo(), XmlConsts.HOROSCOPE_INFO_TAG, XmlConsts.INFO_TAG);
			if(compactibility.hasHoroscope()) {
				export(new PlanetChartData(Varga.Rasi, compactibility.getGirlPlanetaryInfo()));
				export(new PlanetChartData(Varga.Navamsa, compactibility.getGirlPlanetaryInfo()));
			}
			xmlWriter.add(xmlef.createEndElement(XmlConsts.GIRL_TAG, null));


			exportTableData(compactibility.getKutaTableData(), Compactibility.getKutaTableColumnMetaData(), XmlConsts.KUTA_ANALYSIS_TAG, XmlConsts.KUTA_MATCH_TAG);

			if (compactibility.hasHoroscope()){
				exportTableData(compactibility.getDoshaTableData(), Compactibility.getDoshaTableColumnMetaData(), XmlConsts.DOSHA_ANALYSIS_TAG, XmlConsts.PLANET_DOSHA_TAG);
			}

			xmlWriter.add(xmlef.createEndElement(XmlConsts.COMPACTIBILITY_TAG, null));
			xmlWriter.add(xmlef.createEndDocument());

			xmlWriter.close();

		}catch(Exception e){
			log.log(Level.SEVERE, "Exception in writing compactibility xml document", e);
		}
	}

	public static void main(String[] args) {


		AstrosoftPref pref = AstroSoft.getPreferences();
		pref.setAyanamsa(Ayanamsa.KRISHNAMURTHI);

		Language currLang = pref.getLanguage();
		pref.setLanguage(Language.ENGLISH);

		Horoscope b = new Horoscope("Elango", 17, 4, 1957, 7, 10,
				77 + (44.00 / 60.00), 11 + (22.00 / 60.00), 5.5, "Erode");

		Horoscope g = new Horoscope("Mani", 10, 8, 1960, 5, 30,
				77 + (44.00 / 60.00), 11 + (22.00 / 60.00), 5.5, "Erode");

		Compactibility c = new Compactibility(b, g);

		//Compactibility c = new Compactibility("a" , "b", Nakshathra.Ashwini, Nakshathra.Bharani, Rasi.Mesha, Rasi.Mesha);

		String file = "c:/astrosoft/resources/export/compactibility.xml";

		CompactibilityExporter compactibilityExporter = new CompactibilityExporter(file);

		compactibilityExporter.export2Xml(c);

		FOPTransformer.exportToPDF(file, "C:/AstroSoft/resources/export/compactibility2pdf.xsl", "C:/AstroSoft/resources/export/compactibility.pdf");

		pref.setLanguage(currLang);
	}
}
