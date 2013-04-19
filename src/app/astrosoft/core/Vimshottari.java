/**
 * Vimshottari.java
 * Created On 2006, Mar 22, 2006 6:42:45 PM
 * @author E. Rajasekar
 */

package app.astrosoft.core;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import app.astrosoft.consts.AstroConsts;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.DisplayFormat;
import app.astrosoft.consts.Planet;
import app.astrosoft.core.VimDasa.DasaIterator;
import app.astrosoft.export.Exportable;
import app.astrosoft.export.Exporter;
import app.astrosoft.ui.table.DefaultColumnMetaData;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableDataFactory;
import app.astrosoft.ui.table.TableRowData;
import app.astrosoft.util.AstroUtil;

public class Vimshottari implements Exportable {

	public static final int MAX_LEVEL = 3;

	public static Format dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	
	private Planet startLord;
	
	private double balance;
	
	private EnumMap<Planet, Dasa> dasa;
	
	private Dasa currentDasa;
	
	private DefaultMutableTreeNode root;
	
	private static DefaultColumnMetaData vimDasaTableColumnMetaData;
	
	public Vimshottari(double moonLongitude, Calendar bday) {
		
		startLord = getDasaLord(moonLongitude);
		
		balance = computeBalance(moonLongitude);
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(bday.getTime());
		double birth = AstroUtil.dateToDecimalYear(cal);
		
		double start = ( birth + balance ) - startLord.dasaPeriod();
		dasa = VimDasa.generateSubDasas(startLord, null, start, 0);
		currentDasa = getCurrent();
	}

	private Planet getDasaLord(double lon) {

		double i = 0;

		for (i = 0; i <= 26; i++) {

			if (((i * (AstroConsts.nakLength )) <= lon)
					&& (lon < ((i + 1) * (AstroConsts.nakLength )))) {

				break;
			}

		}

		return Planet.ofDasaNo((int) (i + 7));

	}
	
	private double computeBalance(double moonLongitude) {

        double balance = moonLongitude % AstroConsts.nakLength;

        balance = AstroConsts.nakLength - balance;
        balance = balance * 60;

        return ( balance * startLord.dasaPeriod() ) / 800;

    }
	
	public EnumMap<Planet, Dasa> getDasa() {
		return dasa;
	}
	
	public String getBalance() {
		
		StringBuilder sb = new StringBuilder("Bal of ");
		sb.append(startLord.toString(DisplayFormat.SYMBOL));
		sb.append(" Dasa ");
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(AstroUtil.decimalYearToDate(balance));
		
		int year = balance < 1 ? 0 : cal.get(Calendar.YEAR);
		sb.append(year + "y ");
		sb.append(cal.get(Calendar.MONTH) + "m ");
		sb.append(cal.get(Calendar.DATE) + "d ");
		
		return sb.toString();
	}
	
	public Dasa getCurrent(){
		
		Dasa current = null;
		for(Dasa d : dasas()){
			if(d.isRunning()){
				current = d.getCurrent();
			}
		}
		return current;
	}
	
	public Iterable<Dasa> dasas() {
		return new VimDasa.DasaIterable(startLord, dasa);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		for(Dasa d : dasas()){
			sb.append(d.printTree());
		}
			
		return sb.toString();
	}

	
	public DefaultMutableTreeNode getRoot() {
		
		if (root == null){
			createTreeNode();
		}
		return root;
	}
	
	public void createTreeNode(){
		
		root = new DefaultMutableTreeNode(new VimDasa());
		for(Dasa d : dasas()){
			root.add(d.createTreeNode());
		}
		
		/*System.out.println(printDasaTree());
		System.out.println(getCurrentDasaPath());*/
	}
	
	public DefaultTreeModel getDasaTreeModel(){
		createTreeNode();
		return new DefaultTreeModel(root);
	}
	
	public TreePath getCurrentDasaPath(){
	
		TreePath path = null;
		Enumeration e = root.breadthFirstEnumeration();
		
		while(e.hasMoreElements()){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
			if (currentDasa == node.getUserObject()){
				path = new TreePath(node.getPath());
			}
		}
		
		return path;
		
	}
	
	public String printDasaTree(){
		
		StringBuilder sb = new StringBuilder();
		Enumeration e = root.breadthFirstEnumeration();
		
		while(e.hasMoreElements()){
			sb.append(e.nextElement() + " , ");
		}
		return sb.toString();
	}
	
	public TableData<Dasa> getVimDasaTableData(Dasa dasa){
		
		return TableDataFactory.getTableData(dasa.subDasas());
	}
	
	public TableData<Dasa> getVimDasaTableData() {
		
		return TableDataFactory.getTableData(dasas());
	}
	
	public static DefaultColumnMetaData getVimDasaTableColumnMetaData() {
		
		if (vimDasaTableColumnMetaData == null){
			vimDasaTableColumnMetaData = new DefaultColumnMetaData(AstrosoftTableColumn.Dasa, AstrosoftTableColumn.Start, AstrosoftTableColumn.End);
			vimDasaTableColumnMetaData.localizeColumns(AstrosoftTableColumn.Dasa);
		}
		return vimDasaTableColumnMetaData;
	}
	
	public Planet getStartLord() {
		return startLord;
	}
	
	public static void main(String[] args) {

		Vimshottari v = new Vimshottari(300 + ( 59.00 / 60.00 ), new GregorianCalendar(1980, Calendar.DECEMBER, 11));
		//v.getDasa().get(Planet.Rahu).getSubDasas();
		//v.getDasa().get(Planet.Venus).getSubDasas().get(Planet.Venus).getSubDasas();
		//System.out.println(v);
		//System.out.println("Current:-> " + v.getCurrent());
		
		EnumMap<Planet, Dasa> dasa = v.getDasa();
		
		for(Planet p : Planet.dasaLords(v.getStartLord())){
			
			Dasa d = dasa.get(p);
			for(Dasa sb : d.subDasas()) {
				System.out.println(TableDataFactory.toCSV(v.getVimDasaTableData(sb),getVimDasaTableColumnMetaData()));
				System.out.println("**********************************************************");
			}
			System.out.println("---------------------------------------------------------");
		}
		
		//System.out.println(TableDataFactory.toCSV(v.getVimDasaTableData(),getVimDasaTableColumnMetaData()));
		
		//System.out.println(v.printDasaTree());
		
	}

	public void doExport(Exporter e) {
		e.export(this);
	}

	
}
