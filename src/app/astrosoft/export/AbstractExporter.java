/**
 * AbstractExporter.java
 * Created On 2007, Jul 5, 2007 12:10:51 PM
 * @author E. Rajasekar
 */

package app.astrosoft.export;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.Format;
import java.util.EnumMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.events.Attribute;

import app.astrosoft.beans.AshtaVargaChartData;
import app.astrosoft.beans.Degree;
import app.astrosoft.beans.HousePosition;
import app.astrosoft.beans.PlanetChartData;
import app.astrosoft.beans.PlanetaryInfo;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.Language;
import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.XmlConsts;
import app.astrosoft.core.Ashtavarga;
import app.astrosoft.core.Compactibility;
import app.astrosoft.core.Horoscope;
import app.astrosoft.core.PanchangList;
import app.astrosoft.core.ShadBala;
import app.astrosoft.core.Vimshottari;
import app.astrosoft.pref.AstrosoftPref;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.table.ColumnMetaData;
import app.astrosoft.ui.table.Table;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableRowData;
import app.astrosoft.util.AstroUtil;

/**
 * This is abstract Exporter class to implement common behaviour between all Exporter classes.
 * All non-common operations throws UnsupportedOperationException and concrete subclasses
 * should provide implementation if it really supports that operation.
 *
 * @author Raja
 *
 */
public abstract class AbstractExporter implements Exporter{

	private static final Logger log = Logger.getLogger(AbstractExporter.class.getName());

	protected static final XMLOutputFactory factory = XMLOutputFactory.newInstance();

	protected static final XMLEventFactory xmlef = XMLEventFactory.newInstance();

	protected static final Format formatter = new java.text.DecimalFormat("000.00");

	protected XMLEventWriter xmlWriter;

	public AbstractExporter(String file) {

		try {
			xmlWriter = factory.createXMLEventWriter(new BufferedWriter(new FileWriter(file)));
		}catch(Exception e){
			log.log(Level.SEVERE, "Exception in creating xml event writer ", e);
		}
	}

	public void export2Xml(Exportable exportable){

		//Switch to English to write all data in English
		AstrosoftPref pref = AstroSoft.getPreferences();
		Language currLang = pref.getLanguage();
		pref.setLanguage(Language.ENGLISH);

		exportable.doExport(this);

		// Switch back to current language setting
		pref.setLanguage(currLang);

	}

	/**
	 * Use this method if table data needs to be represented as key-value pair
	 * @param data
	 * @param title
	 */
	public <E extends TableRowData> void exportTableData(TableData<E> data, QName tableTitle, QName rowTitle) {

		try {

			xmlWriter.add(xmlef.createStartElement(tableTitle, null,null));

			for(int i = 0; i < data.getRowCount(); i++){

				TableRowData row = data.getRow(i);

				xmlWriter.add(xmlef.createStartElement(rowTitle, null,null));
				xmlWriter.add(xmlef.createAttribute(XmlConsts.Name, row.getColumnData(AstrosoftTableColumn.Key).toString()));
				xmlWriter.add(xmlef.createCharacters(row.getColumnData(AstrosoftTableColumn.Value).toString()));
				xmlWriter.add(xmlef.createEndElement(rowTitle,null));

			}


			xmlWriter.add(xmlef.createEndElement(tableTitle, null));

		}catch(Exception e){
			log.log(Level.SEVERE, "Exception in writing horosocope xml document", e);
		}
	}

	public <E extends TableRowData> void exportTableData(Table table , QName tableTitle, QName rowTitle) {

		exportTableData(table.getTableData(), table.getColumnMetaData(), tableTitle, rowTitle);
	}

	public <E extends TableRowData> void exportTableData(TableData<E> data, ColumnMetaData colMetaData , QName tableTitle, QName rowTitle) {
		exportTableData(data,colMetaData,tableTitle, null, rowTitle);
	}

	/**
	 * Use this method for regular table
	 * @param data
	 * @param title
	 */
	public <E extends TableRowData> void exportTableData(TableData<E> data, ColumnMetaData colMetaData , QName tableTitle, Attribute titleAttribute, QName rowTitle) {

		try {

			xmlWriter.add(xmlef.createStartElement(tableTitle, null,null));

			if (titleAttribute != null){
				xmlWriter.add(titleAttribute);
			}

			for(int i = 0; i < data.getRowCount(); i++){

				TableRowData row = data.getRow(i);

				xmlWriter.add(xmlef.createStartElement(rowTitle, null,null));


				for (AstrosoftTableColumn col : colMetaData.getVisibleColumns()) {

					QName colTag = new QName(XmlConsts.XML_NS, col.name());
					xmlWriter.add(xmlef.createStartElement(colTag, null, null));
					Object value = row.getColumnData(col);
					if (value != null ) {

						xmlWriter.add(xmlef.createCharacters(formatTableValue(colMetaData.getColumnClass(col), value)));
					}
					xmlWriter.add(xmlef.createEndElement(colTag,null));
				}
				xmlWriter.add(xmlef.createEndElement(rowTitle,null));
			}


			xmlWriter.add(xmlef.createEndElement(tableTitle, null));

		}catch(Exception e){
			log.log(Level.SEVERE, "Exception in writing horosocope xml document", e);
		}
	}

	private static String formatTableValue(Class columnClass , Object value){

		String strVal;

		if (columnClass == Degree.class){

			strVal = AstroUtil.dms(Double.valueOf(value.toString()));

		} else if (value instanceof Double){

			strVal = formatter.format(value);
		}
		else{

			strVal = value.toString();
		}

		return strVal;

	}

	public void export(PlanetChartData planetChart) {

		try {

			xmlWriter.add(xmlef.createStartElement(XmlConsts.CHART_TAG, null,null));

			xmlWriter.add(xmlef.createAttribute(XmlConsts.Name, planetChart.getChartName()));

			for(Rasi rasi:Rasi.values()){

				xmlWriter.add(xmlef.createStartElement(XmlConsts.HOUSE_TAG, null,null));

				xmlWriter.add(xmlef.createAttribute(XmlConsts.Number, String.valueOf(rasi.ordinal()+1)));

				EnumMap<Rasi, List<Planet>> planetsInRasi = planetChart.getPlanetsInRasi();

				if (planetsInRasi.keySet().contains(rasi)){

					for(Planet planet : planetsInRasi.get(rasi)){

						xmlWriter.add(xmlef.createStartElement(XmlConsts.PLANET_TAG, null,null));
						xmlWriter.add(xmlef.createCharacters(planet.sym()));
						xmlWriter.add(xmlef.createEndElement(XmlConsts.PLANET_TAG, null));
					}
				}

				xmlWriter.add(xmlef.createEndElement(XmlConsts.HOUSE_TAG, null));

			}

			xmlWriter.add(xmlef.createEndElement(XmlConsts.CHART_TAG, null));

		}catch(Exception e){
			log.log(Level.SEVERE, "Exception in writing chart xml ", e);
		}

	}

	public void export(Ashtavarga ashtavarga) {
		throw new UnsupportedOperationException("Operation export(ashtavarga) is not supported");

	}

	public void export(AshtaVargaChartData ashtavargaChart) {
		throw new UnsupportedOperationException("Operation export(ashtavargaChart) is not supported");

	}

	public void export(ShadBala shadBala) {
		throw new UnsupportedOperationException("Operation export(shadBala) is not supported");

	}

	public void export(PlanetaryInfo planetaryInfo) {
		throw new UnsupportedOperationException("Operation export(planetaryInfo) is not supported");

	}

	public void export(HousePosition housePosition) {
		throw new UnsupportedOperationException("Operation export(housePosition) is not supported");

	}

	public void export(Vimshottari vimshottari) {
		throw new UnsupportedOperationException("Operation export(vimshottari) is not supported");

	}

	public void export(Horoscope horoscope) {
		throw new UnsupportedOperationException("Operation export(horoscope) is not supported");
	}

	public void export(Compactibility compactibility) {
		throw new UnsupportedOperationException("Operation export(compactibility) is not supported");

	}
	
	public void export(PanchangList panchangList) {
		throw new UnsupportedOperationException("Operation export(panchangList) is not supported");

	}

}
