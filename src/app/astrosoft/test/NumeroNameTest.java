/**
 * NumeroNameTest.java
 * Created On 2007, May 3, 2007 3:19:07 PM
 * @author E. Rajasekar
 */

package app.astrosoft.test;

import java.io.File;
import java.util.logging.Logger;
import java.util.logging.Level;

import app.astrosoft.consts.Operator;
import app.astrosoft.persistence.JPAUtil;
import app.astrosoft.persistence.NumerologicalName;
import app.astrosoft.service.NumeroNameService;
import app.astrosoft.ui.comp.NumeroNamePagination;
import app.astrosoft.ui.comp.Pagination;

public class NumeroNameTest {
	
	static {
		//System.setProperty("java.util.logging.config.file", "./resources/logging.properties");
	}
	
	private static final Logger log = Logger.getLogger(NumeroNameTest.class.getName());
	
	public static void main(String[] args) {
		
		//System.out.println(System.getProperties());
		
		File f = new File("c:/astrosoft/samples/raja.ash");
		
		System.out.println(f.getParentFile().getAbsolutePath());
		
		/*if (args.length > 0) {
			//NumeroNameService.addName(args[0]);
		}else {
			NumeroNameService.addName("Raja");
			NumeroNameService.addName("Rajasekar");
			NumeroNameService.addName("Mani");
			NumeroNameService.addName("Elango");
		}*/
		
		//Pagination<NumerologicalName> p = new NumeroNamePagination("r", "", "", Operator.OR, Operator.OR, 3);
		
		//NumeroNameService.exportNames("c:/astrosoft/names.csv");
		
		//NumeroNameService.importNamesFromCSV("c:/n.txt");
		
		
		/*log.info("=======\n" + p.getPreviousPage() + "=========");
		
		log.info("=======\n" + p.getNextPage() + "=========");
		
		log.info("=======\n" + p.getPreviousPage() + "=========");
		
		log.info("=======\n" + p.getPreviousPage() + "=========");*/
		
		/*log.info("=======\n" + p.getPage(3) + "=========");
		log.info("=======\n" + p.getNextPage() + "=========");
		log.info("=======\n" + p.getNextPage() + "=========");
		log.info("=======\n" + p.getNextPage() + "=========");
		log.info("=======\n" + p.getNextPage() + "=========");
		log.info("=======\n" + p.getTotalPages() + "=========");*/
		
		
		//System.out.println(NumeroNameService.findDynamic("aj","      ","5",Operator.AND, Operator.AND));
		
		//System.out.println(NumeroNameService.findDynamic(5));
		//System.out.println(NumeroNameService.findDynamic(0));
		
		//JPAUtil.closeEntityManagerFactory();
		
		/*System.out.println(log.getLevel());
		log.info("sdfsdfsfd");
		log.config("this is config");
		log.severe("this is severe");
		log.fine("this is fine");
		log.log(Level.SEVERE, "will throw exp", new Exception("sdfsdfsdf"));*/
	}
	
}
