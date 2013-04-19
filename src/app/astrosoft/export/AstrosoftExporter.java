/**
 * AstrosoftExporter.java
 * Created On 2007, Aug 31, 2007 3:18:16 PM
 * @author E. Rajasekar
 */

package app.astrosoft.export;

import java.util.concurrent.FutureTask;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

public class AstrosoftExporter {

	public static enum Type {

		Horosocope {
			@Override
			public Exporter getExporter(String xmlFile) {

				return new HoroscopeExporter(xmlFile);
			}

			@Override
			public String getTemplate() {

				return Type.class.getResource("/resources/export/horoscope2pdf.xsl").toString();
			}
		},
		Compactibility{
			@Override
			public Exporter getExporter(String xmlFile) {

				return new CompactibilityExporter(xmlFile);
			}

			@Override
			public String getTemplate() {

				return Type.class.getResource("/resources/export/compactibility2pdf.xsl").toString();
			}
		},
		Panchang {
			@Override
			public Exporter getExporter(String xmlFile) {

				return new PanchangExporter(xmlFile);
			}

			@Override
			public String getTemplate() {

				return Type.class.getResource("/resources/export/panchang2pdf.xsl").toString();
			}
		};

		public abstract Exporter getExporter(String xmlFile);

		public abstract String getTemplate();


	};

	private static final Logger log = Logger.getLogger(AstrosoftExporter.class.getName());

	public static FutureTask<Object> export2Pdf(final Type exporterType, final Exportable exportable, final String pdfFile){

		Runnable r = new Runnable () {
			
			public void run() {
				String xmlFile = pdfFile.replaceAll(".pdf|.PDF",".xml");
				export2Xml(exporterType,exportable,xmlFile);
		
				log.info("XML File: " + xmlFile);
				log.info("PDF File: " + pdfFile);
		
				FOPTransformer.exportToPDF(xmlFile, exporterType.getTemplate(), pdfFile);
			}
		};
		
		FutureTask<Object> task = new FutureTask<Object>(r,null);
		
		new Thread(task).start();
		
		return task;
	}

	public static void export2Xml(Type exporterType, Exportable exportable, String xmlFile){

		exporterType.getExporter(xmlFile).export2Xml(exportable);

	}

}
