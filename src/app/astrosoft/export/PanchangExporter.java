/**
 * PanchangExporter.java
 * Created On 2007, Sep 14, 2007 3:31:06 PM
 * @author E. Rajasekar
 */

package app.astrosoft.export;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLStreamException;

import app.astrosoft.consts.Month;
import app.astrosoft.consts.XmlConsts;
import app.astrosoft.core.Panchang;
import app.astrosoft.core.PanchangList;
import app.astrosoft.ui.AstroSoft;

public class PanchangExporter extends AbstractExporter{
	
	private static final Logger log = Logger.getLogger(HoroscopeExporter.class.getName());
	
	private static SimpleDateFormat df = new SimpleDateFormat("MMM yyyy");
	
	public PanchangExporter(String file) {
		
		super(file);
	}
	
	public void export(PanchangList panchangList){
		
		try {
			
			xmlWriter.add(xmlef.createStartDocument());
			xmlWriter.add(xmlef.createStartElement(XmlConsts.ASTROSOFT_TAG, null,null));
			xmlWriter.add(xmlef.createNamespace("xsi",XmlConsts.XML_NS));
			xmlWriter.add(xmlef.createStartElement(XmlConsts.PANCHANG_TAG, null,null));
			xmlWriter.add(xmlef.createAttribute(XmlConsts.Location, AstroSoft.getPreferences().getPlace().toString())); 
			
			for(Panchang p:panchangList){
				export(p);
			}
			
//			End panchang tag and document
			xmlWriter.add(xmlef.createEndElement(XmlConsts.PANCHANG_INFO_TAG, null));
			xmlWriter.add(xmlef.createEndDocument());
			
			xmlWriter.close();
			
		}catch(Exception e){
			log.log(Level.SEVERE, "Exception in writing panchang xml document", e);
		}
		
	}

	private void export(Panchang p) throws XMLStreamException {
		
		Calendar cal = p.getDate();
		if (cal.get(Calendar.DATE) == 1){
			xmlWriter.add(xmlef.createStartElement(XmlConsts.MONTH_TAG, null,null));
			xmlWriter.add(xmlef.createAttribute(XmlConsts.Title, "Panchang for " + df.format(p.getDate().getTime())));
		}
		exportTableData(p.getPanchangTableData(),XmlConsts.PANCHANG_INFO_TAG,XmlConsts.INFO_TAG);
		if (cal.get(Calendar.DATE) == cal.getActualMaximum(Calendar.DATE)){
			xmlWriter.add(xmlef.createEndElement(XmlConsts.MONTH_TAG, null));
		}
	}
	
	public static void main(String[] args) {
		
		PanchangList pl = new PanchangList(2007,Calendar.SEPTEMBER);
		
		String file = "c:/astrosoft/resources/export/panchang.xml";
		PanchangExporter pe = new PanchangExporter(file);
		
		pe.export2Xml(pl);
		
		FOPTransformer.exportToPDF(file, "C:/AstroSoft/resources/export/panchang2pdf.xsl", "C:/AstroSoft/resources/export/panchang.pdf");
	}
}
