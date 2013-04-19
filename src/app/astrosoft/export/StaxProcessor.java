/**
 * StaxProcessor.java
 * Created On 2007, Jun 9, 2007 8:32:23 PM
 * @author E. Rajasekar
 */

package app.astrosoft.export;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;

import app.astrosoft.consts.XmlConsts;

public class StaxProcessor {
	
	private static final Logger log = Logger.getLogger(StaxProcessor.class.getName());
	
	private static final XMLEventFactory xmlef = XMLEventFactory.newInstance();


	public static void main(String[] args) {
		
		try {
			
			XMLOutputFactory factory = XMLOutputFactory.newInstance();
			
			Writer out = new BufferedWriter(new FileWriter("c:/astrosoft/export/astrosoft.xml"));
			
			XMLEventWriter xmlWriter = factory.createXMLEventWriter(out);
			
			xmlWriter.add(xmlef.createStartDocument());
			
			xmlWriter.add(xmlef.createStartElement(XmlConsts.ASTROSOFT_TAG, null,null));
			
			xmlWriter.add(xmlef.createNamespace("http://www.w3.org/2001/XMLSchema-instance"));
			
			xmlWriter.add(xmlef.createStartElement(XmlConsts.HOROSCOPE_TAG, null,null));
			
			xmlWriter.add(xmlef.createStartElement(XmlConsts.CHART_TAG, null,null));
			
			xmlWriter.add(xmlef.createAttribute("name", "Rasi"));
			
			xmlWriter.add(xmlef.createStartElement(XmlConsts.HOUSE_TAG, null,null));
			
			xmlWriter.add(xmlef.createAttribute("no", "1"));
			
			xmlWriter.add(xmlef.createStartElement(XmlConsts.PLANET_TAG, null,null));
			xmlWriter.add(xmlef.createCharacters("Su"));
			xmlWriter.add(xmlef.createEndElement(XmlConsts.PLANET_TAG, null));
			xmlWriter.add(xmlef.createStartElement(XmlConsts.PLANET_TAG, null,null));
			xmlWriter.add(xmlef.createCharacters("Mo"));
			xmlWriter.add(xmlef.createEndElement(XmlConsts.PLANET_TAG, null));
			xmlWriter.add(xmlef.createStartElement(XmlConsts.PLANET_TAG, null,null));
			xmlWriter.add(xmlef.createCharacters("Ma"));
			xmlWriter.add(xmlef.createEndElement(XmlConsts.PLANET_TAG, null));
			
			xmlWriter.add(xmlef.createEndElement(XmlConsts.HOUSE_TAG, null));
			xmlWriter.add(xmlef.createEndElement(XmlConsts.CHART_TAG, null));
			xmlWriter.add(xmlef.createEndElement(XmlConsts.HOROSCOPE_TAG, null));
			
			xmlWriter.add(xmlef.createEndDocument());
			
			out.flush();
			out.close();

			
			
			
		}catch(Exception e){
			log.log(Level.SEVERE, "Exception in writing xml document", e);
		}
			

	}
	
	public static XMLEventFactory getXmlEventFactory() {
		return xmlef;
	}
}
