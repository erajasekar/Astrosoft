/**
 * XMLHelper.java
 * Created On 2006, May 18, 2006 3:18:37 PM
 * @author E. Rajasekar
 */

package app.astrosoft.export;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLHelper {

	private static final Logger log = Logger.getLogger(XMLHelper.class.getName());

	public static Document createDOM() {

		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();

			return db.newDocument();
		} catch (ParserConfigurationException pce) {
			log.log(Level.SEVERE, "Exception in creating document ", pce);
			return null;
		}
	}

	public static Element addRootElement(Document doc, Object name) {
		Element rootElement = doc.createElement(name.toString());
		doc.appendChild(rootElement);
		return rootElement;
	}

	public static Element addElement(Document doc, Element parent, Object name,
			String value) {

		Element element = doc.createElement(name.toString());
		parent.appendChild(element);
		if (value != null) {
			element.appendChild(doc.createTextNode(value));
		}
		return element;
	}

	public static Element addElement(Document doc, Element parent, Object name){
		return addElement(doc, parent, name, null);
	}

	public static void addAttribute(Element parent, Object name, Object value) {
		if (value == null) {
			parent.setAttribute(name.toString(), "");
		} else {
			parent.setAttribute(name.toString(), value.toString());
		}
	}

	public static Map<String, String> getChildElements(Node parent){
		Map<String, String> elements = new HashMap<String, String>();

		NodeList children = parent.getChildNodes();

		for(int i = 0; i < children.getLength(); i++){
			Node child = children.item(i);
			elements.put(child.getNodeName(), child.getTextContent());
		}

		return elements;
	}

	public static Node getChildNode(Node parent, String nodeName){
		
		Node node = null;
		
		NodeList children = parent.getChildNodes();

		for(int i = 0; i < children.getLength(); i++){
			Node child = children.item(i);
			if (child.getNodeName().equals(nodeName)){
				node = child;
				break;
			}
		}

		return node;
	}
	
	public static Document parseXML(String fileName){

		Document document = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ();

        // Do not generate comment nodes.
        factory.setIgnoringComments (true);
        // Eliminate whitespace in element content.
        factory.setIgnoringElementContentWhitespace (true);
        // Validate the document against its XSD.
        factory.setValidating (false);
        // Respect name space
        factory.setNamespaceAware (true);

        try
        {

           document = factory.newDocumentBuilder ().parse (new File(fileName));

        }catch(ParserConfigurationException e){
        	log.log(Level.SEVERE, "Exception in parsing XML " ,e);
        }catch(IOException e){
        	log.log(Level.SEVERE, "Exception in parsing XML " ,e);
        }catch (SAXException e) {
        	log.log(Level.SEVERE, "Exception in parsing XML " ,e);
		}

        return document;
	}

    // This method writes a DOM document to a file
    public static void saveXML(Document doc, String fileName) {
        try {
            // Prepare the DOM document for writing
            Source source = new DOMSource(doc);

            // Prepare the output file
            File file = new File(fileName);
            Result result = new StreamResult(file);

            // Write the DOM document to the file
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(source, result);
        } catch (Exception e) {
        	log.log(Level.SEVERE, "Exception in creating document ", e);
        }
    }

}
