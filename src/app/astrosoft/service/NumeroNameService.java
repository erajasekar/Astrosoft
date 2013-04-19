/**
 * NumeroNameService.java
 * Created On 2007, May 3, 2007 10:06:39 PM
 * @author E. Rajasekar
 */

package app.astrosoft.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import app.astrosoft.consts.Operator;
import app.astrosoft.persistence.JPAUtil;
import app.astrosoft.persistence.NumerologicalName;
import app.astrosoft.ui.comp.NumeroNamePagination;
import app.astrosoft.ui.dlg.OptionDialog;
import app.astrosoft.ui.table.ColumnMetaData;
import app.astrosoft.ui.table.Table;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableDataFactory;
import app.astrosoft.ui.table.TableRowData;


public class NumeroNameService {
	
	private static final Logger log = Logger.getLogger("app.astrosoft.service");
	
	private static final int exportPageLen = 25;

	public static void addName(String name){
		
		addName(new NumerologicalName(name));
	}
	
	public static void addName(NumerologicalName numeroName){
		
		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
	
			em.persist(numeroName);
			tx.commit();
			log.log(Level.INFO, "Added Name " + numeroName);
		
		}catch(EntityExistsException e){
			
			log.warning("Name : " + numeroName.getName() + " already exists");
			
		}finally {
			em.close();
		}
	}
	
	public static void deleteNames(List<NumerologicalName> names){
		
		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		for(NumerologicalName name : names){
			NumerologicalName entity = em.find(NumerologicalName.class, name.getNameId());
			em.remove(entity);
			log.log(Level.INFO, "Removed Name " + entity);
		}
		
		tx.commit();
		em.close();
	}
	
	public static List queryAllNames(int startIndex, int maxRows){
		
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		
		Query q = em.createNamedQuery(NumerologicalName.FIND_ALL_NAMES);
		
		q.setFirstResult(startIndex);
		q.setMaxResults(maxRows);
		
		List names = q.getResultList();
		em.close();
		
		return names;
	}
	
	public static TableData<NumerologicalName> findAllNames(int startIndex, int maxRows){
		
		return TableDataFactory.getTableData(queryAllNames(startIndex, maxRows));
		
	}
	
	public static List queryDynamic(String name, String numeroVal, String numeroNum, Operator op1, Operator op2, int startIndex, int maxRows){
		
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		
		Query q = em.createQuery(NumerologicalName.SELECT_NAME_SQL + getSqlWhere(name, numeroVal, numeroNum, op1, op2) + NumerologicalName.ORDER_BY);
		
		q.setFirstResult(startIndex);
		q.setMaxResults(maxRows);
		List names = q.getResultList();
		
		em.close();
		
		return names;
	}
	
	public static TableData<NumerologicalName> findDynamic(String name, String numeroVal, String numeroNum, Operator op1, Operator op2, int startIndex, int maxRows){
		
		return TableDataFactory.getTableData(queryDynamic(name, numeroVal, numeroNum, op1,op2, startIndex, maxRows));
	}
	
	private static String getSqlWhere(String name, String numeroVal, String numeroNum, Operator op1, Operator op2){
	
		StringBuilder sb = new StringBuilder();
		
		if (name != null && name.trim().length() > 0) {
			sb.append("name like '" + name.toUpperCase() + "%' " );
		}
		if (numeroVal != null && numeroVal.trim().length() > 0) {
			
			if (sb.length() > 0) {
				sb.append(op1);
			}
			sb.append(" numeroVal = " + numeroVal + " " );
		}
		if (numeroNum != null && numeroNum.trim().length() > 0) {
			
			if (sb.length() > 0) {
				sb.append(op2);
			}
			sb.append(" numeroNum = " + numeroNum + " " );
		}
		
		if (sb.length() > 0){
			sb.insert(0, "where ");
		}
		
		log.fine("SQL Where " + sb);
		return sb.toString();
	}
	
	public static void exportNames(String file){
		
		if (file == null){
			return;
		}
			
		ColumnMetaData colData = NumerologicalName.getColumnMetaData();
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			for(TableData<NumerologicalName> data : new NumeroNamePagination(exportPageLen)){
				writer.write(TableDataFactory.toCSV(data,colData));
			}
		
			writer.close();
		} catch (IOException e) {
			log.log(Level.SEVERE,"Exception in exporting name ", e);
		}
		OptionDialog.showDialog("Exported Successfully.", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private static void importNames(String file, boolean isCSV){
		
		if (file == null){
			return;
		}
		
		BufferedReader reader = null;
		EntityManager em = null;
		
		try {
			
			reader = new BufferedReader(new FileReader(file));
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			EntityTransaction tx = em.getTransaction();
			
			String name;
			
			while( (name = reader.readLine()) != null){
				
				tx.begin();
				if (isCSV) {
					name = name.split(",")[0];
				}
				
				try {
					em.persist(new NumerologicalName(name));
					log.fine("Added name " + name);
					tx.commit();
				}catch(EntityExistsException e){
					log.warning(name + " already exists");
					tx.rollback();
				}
			}
		}catch (IOException e) {
			log.log(Level.SEVERE,"Exception in importing  name ", e);
		}finally {
			
			try {
				reader.close();
			}catch (IOException e) {
				log.log(Level.SEVERE,"Exception in importing  name ", e);
			}
				
			em.close();
		}
		
		OptionDialog.showDialog("Imported Successfully.", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void importNamesFromCSV(String file){
	
		importNames(file, true);
	}
	
	public static void importNamesFromList(String file){
		
		importNames(file,false);
	}
	
	/*private static class NameTable implements Table {

		List<? extends TableRowData> rows;
		
		public NameTable(List<? extends TableRowData> rows ) {
			this.rows = rows;
		}
		public TableData<? extends TableRowData> getTableData() {
			return TableDataFactory.getTableData(rows);
		}

		public ColumnMetaData getColumnMetaData() {
			return NumerologicalName.getColumnMetaData();
		}
		
		@Override
		public String toString() {
			
			return getTableData().toString();
		}
		
	};*/
}
