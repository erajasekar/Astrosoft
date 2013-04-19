/**
 * PlaceFinder.java
 * Created On 2006, Mar 9, 2006 1:49:36 PM
 * @author E. Rajasekar
 */

package app.astrosoft.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import app.astrosoft.beans.Place;

public class PlaceFinder {

	private static final String NAME_NODE = "name";
	private static final String LATITUDE_NODE = "latitude";
	private static final String LOGITUDE_NODE = "longitude";
	private static final String DIR_ATTRIB = "dir";
	
	private static final String XML_SOURCE = "/resources/LatitudeLongitudes.xml";
	private static XPath xpath = XPathFactory.newInstance().newXPath();
	
	public static List<Place> findPlace(String place){
		
		List<Place> placeList = new ArrayList<Place>();
		
		String expression = "//world/country/state/city[name='" + place + "']";
		InputSource inputSource = new InputSource(PlaceFinder.class.getResourceAsStream(XML_SOURCE));
		Node node = null;
		try {
			node = (Node) xpath.evaluate(expression, inputSource, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		if (node != null){
		
			String city = "";
			String state = "";
			String country = "";
			String latitude = "0:0";
			String longitude = "0:0";
			char longDir = 'n';
			char latDir = 'e';
			String timeZoneId = null;
			
			NodeList cityChilds = node.getChildNodes();
			
			for(int i = 0; i < cityChilds.getLength(); i++){
				Node child = cityChilds.item(i);
				
				if (child.getNodeName().equals(NAME_NODE)){
					city = child.getTextContent();
				}else if (child.getNodeName().equals(LOGITUDE_NODE)){
					longitude = child.getTextContent();
					longDir = child.getAttributes().getNamedItem(DIR_ATTRIB).getNodeValue().charAt(0);
				}if (child.getNodeName().equals(LATITUDE_NODE)){
					latitude = child.getTextContent();
					latDir = child.getAttributes().getNamedItem(DIR_ATTRIB).getNodeValue().charAt(0);
				}
			}
			Node stateNode = node.getParentNode();
			state = stateNode.getChildNodes().item(1).getTextContent(); 
			Node countryNode = stateNode.getParentNode();
			country = countryNode.getChildNodes().item(1).getTextContent();
			timeZoneId = countryNode.getChildNodes().item(3).getTextContent();
			Place p = new Place(city, state, country, latitude, latDir, longitude, longDir, timeZoneId);
			placeList.add(p);
			//placeList.add(Place.getDefault());
			//placeList.add(p);
			//placeList.add(p);
		}
		
		return placeList;
	}
	
	public static void main(String[] args) throws XPathExpressionException {
		
		System.out.println(findPlace("Chennai"));
	}
}
