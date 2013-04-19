/**
 * Dasa.java
 * Created On 2006, Mar 22, 2006 6:17:51 PM
 * @author E. Rajasekar
 */

package app.astrosoft.core;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import app.astrosoft.consts.Planet;
import app.astrosoft.ui.table.TableRowData;
import app.astrosoft.util.AstroUtil;

public interface Dasa extends TableRowData {

	public static double today = AstroUtil.dateToDecimalYear(new GregorianCalendar());
	
	public Dasa getParent();
	
	public double getStart();
	
	public double getEnd();
	
	public double getPeriod();
	
	public boolean isRunning();
	
	public int getLevel();
	
	public String printTree();

	public Dasa getCurrent();
	
	public Iterable<Dasa> subDasas();
	
	public String fullDasa();

	public DefaultMutableTreeNode createTreeNode();
	
	public String getStartDate();	
	
	public String getEndDate();
}
