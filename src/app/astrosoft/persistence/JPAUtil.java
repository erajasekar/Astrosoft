/**
 * JPAUtil.java
 * Created On 2007, May 3, 2007 9:55:54 PM
 * @author E. Rajasekar
 */

package app.astrosoft.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class JPAUtil {
	
	private static final Logger log = Logger.getLogger(JPAUtil.class.getName());
	
	public static final String PERSISTENT_UNIT_NAME = "astrosoft";

	private static EntityManagerFactory emf = null;
	
	static {
		
		/*Ejb3Configuration cfg = new Ejb3Configuration().configure("/META-INF/persistence.xml");
		Configuration hbmcfg = cfg.getHibernateConfiguration();
		SchemaExport schemaExport = new SchemaExport(hbmcfg);
		schemaExport.create(true, true);*/
	}
	
	public static synchronized EntityManagerFactory getEntityManagerFactory() {
		
		if (emf == null || !emf.isOpen()){
			
			emf = Persistence.createEntityManagerFactory("astrosoft");
			
			if (!checkIfSchemaExists(emf)){
				
				log.warning("DB Schema is not found, create new schema");
				
				//TODO: This is hibernate specific, should be changed for other databases.
				Map configOverrides = new HashMap();
				configOverrides.put("hibernate.hbm2ddl.auto", "create");
				emf = Persistence.createEntityManagerFactory("astrosoft", configOverrides);
			}
		}
		
		return emf;
	}
	
	public static synchronized void closeEntityManagerFactory(){
		if (emf != null){
			emf.close();
		}
	}
	
	private static boolean checkIfSchemaExists(EntityManagerFactory emf){
		
		boolean exists = true;
		
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createNamedQuery(NumerologicalName.FIND_ALL_NAMES);
		
		q.setFirstResult(0);
		q.setMaxResults(1);
		
		try {
			List names = q.getResultList();
		}catch(Exception e){
			
			log.log(Level.SEVERE, "Exception in querying names, Exception: " , e);
			
			if (e.getCause().getCause().getMessage().indexOf("Table not found") >= 0){
				exists = false;
			}
		}finally {
		
			em.close();
		}
		return exists;
	}
}
