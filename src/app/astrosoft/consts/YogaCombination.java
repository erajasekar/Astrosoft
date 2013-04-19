/**
 * YogaCombination.java
 * Created On 2007, Oct 22, 2007 2:51:25 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

import java.util.EnumSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import app.astrosoft.export.XMLHelper;
import app.astrosoft.html.Attribute;
import app.astrosoft.html.Tag;

public enum YogaCombination {

	AmalaYoga,
	AnaphaYoga,
	SunaphaYoga, 
	GajakesariYoga,
	KemadrumaYoga,
	ChandraManglaYoga,
	DhurdhuraYoga,
	AdhiYoga,
	ChatussagaraYoga,
	VasumathiYoga,
	RajalakshanaYoga,
	SakataYoga,
	SubhaVesiYoga,
	PapaVesiYoga,
	SubhaVasiYoga,
	PapaVasiYoga,
	ObhayachariYoga,
	HamsaYoga,
	MalavyaYoga,
	SasaYoga,
	RuchakaYoga,
	BhadraYoga,
	KusumaYoga,
	JayaYoga,
	SivaYoga,
	ChapaYoga,
	KahalaYoga,
	LakshmiYoga,
	MahabhagyaYoga,
	BudhaAdityaYoga,
	SankhaYoga,
	BheriYoga,
	SreenathaYoga,
	GajaYoga,
	AmsavataraYoga,
	DevendraYoga,
	MakutaYoga,
	VidyutYoga,
	IndraYoga,
	RaviYoga,
	GoYoga,
	ThrilochanaYoga;
	
	private static Set<YogaCombination> negativeYogas = EnumSet.of(SakataYoga,KemadrumaYoga,PapaVesiYoga);
	
	private static final Logger log = Logger.getLogger(YogaCombination.class.getName());
	
	private static final String XML_SOURCE = "/resources/YogaCombinations.xml";
	private static XPath xpath = XPathFactory.newInstance().newXPath();
	
	private static final Attribute titleColorAttr = new Attribute("color", "#0000FF");
	
	public String getDefinition(){
		
		return getXmlNode(XmlConsts.Definition).getTextContent();
	}
	
	public String getResults(){
		
		return getXmlNode(XmlConsts.Results).getTextContent();
	}

	private Node getXmlNode(String nodeName){
		
		String expression = "//Astrosoft/YogaCombinations/YogaCombination[@Name='"+ name() + "']";
		
		InputSource inputSource = new InputSource(YogaCombination.class.getResourceAsStream(XML_SOURCE));
		Node node = null;
		try {
			node = (Node) xpath.evaluate(expression, inputSource, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			log.log(Level.SEVERE, "Exception in queryin xml", e);
		}
		if (node != null) {
			return XMLHelper.getChildNode(node, nodeName);
		}else{
			throw new IllegalArgumentException(nodeName + "is not found in XML");
		}
		
	}
	
	public boolean isPosition(){
		return !negativeYogas.contains(this);
	}
	
	public boolean isNegative(){
		return negativeYogas.contains(this);
	}
	
	@SuppressWarnings("unchecked")
	public String getHtmlString(){
		
		Tag html = new Tag("html");
		
		Tag head = new Tag("head");
		head.add(generateStyleTag());
		html.add(head);
		
		Tag body = new Tag("body");
		
		String definition = getDefinition();
		String results = getResults();
		
		Tag p1 = new Tag("p");
		
		p1.add(generateTitleTag("Definition:-"));
		p1.add(definition);
		
		Tag p2 = new Tag("p");
		
		p2.add(generateTitleTag("Results:-"));
		p2.add(results);
		
		body.add(p1);
		body.add(p2);
		html.add(body);
		
		System.out.println(html);
		return html.toString();
	}
	
	@SuppressWarnings("unchecked")
	private static Tag generateStyleTag(){
		
		Tag style = new Tag("style");
		
		style.addAttribute(new Attribute("type" , "text/css"));
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("body { \n"); 
		sb.append("    		font-family: Tahoma; \n"); 
		sb.append("    		font-size: 12pt; \n"); 
		sb.append("    		color:#000000; \n"); 
		sb.append("    		background:#FFFFFC; \n"); 
		sb.append("    	 } \n");
		
		style.add(sb.toString());
		
		return style;
		
	}
	
	@SuppressWarnings("unchecked")
	private static Tag generateTitleTag(String title){
		
		Tag tf = new Tag("font");
		tf.addAttribute(titleColorAttr);
		
		Tag b = new Tag("b");
		b.add(title);
		
		tf.add(b);
	
		return tf;
	}
	
	public static void main(String[] args) {
		System.out.println(AmalaYoga.getResults());
	}
}
