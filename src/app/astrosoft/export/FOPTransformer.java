/**
 * FOPUtil.java
 * Created On 2007, Jun 7, 2007 2:39:42 PM
 * @author E. Rajasekar
 */

package app.astrosoft.export;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;


public class FOPTransformer {
	
	private static final Logger log = Logger.getLogger(FOPTransformer.class.getName());

	private static FopFactory fopFactory = FopFactory.newInstance();

	public static void exportToPDF(String xmlFile, String xslFile, String pdfFile){
		
		OutputStream out = null;
		
		log.info("Template File + " + xslFile);
		
		try {
		  
		  out = new BufferedOutputStream(new FileOutputStream(new File(pdfFile)));
		  
		  //	Construct fop with desired output format
		  Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

		  TransformerFactory factory = TransformerFactory.newInstance();
		  Transformer transformer = factory.newTransformer(new StreamSource(xslFile)); // identity transformer
		           
		  // Setup input and output for XSLT transformation 
		 
		  Source src = new StreamSource(new File(xmlFile));

		  // Resulting SAX events (the generated FO) must be piped through to FOP
		  Result res = new SAXResult(fop.getDefaultHandler());
		            
		  // Start XSLT transformation and FOP processing
		  transformer.transform(src, res);
		  
		 

		} catch(Exception e){
			
			log.log(Level.SEVERE,"Exception Generating PDF using FOP ", e);
			
		}finally {
		  
		  try {
			out.close();
		  } catch (IOException e) {
			log.log(Level.SEVERE,"Exception in closing output file", e);
		  }
		}
	}
	
	public static void test(){
		exportToPDF("C:/AstroSoft/export/astrosoft.xml", "C:/AstroSoft/export/horoscope2pdf.xsl", "C:/AstroSoft/export/astrosoft.pdf");
	}
	
	public static void main(String[] args) {
		test();
	}
}
