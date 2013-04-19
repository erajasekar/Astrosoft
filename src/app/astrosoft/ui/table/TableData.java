/**
 * TableData.java
 * Created On 2006, Feb 8, 2006 7:19:37 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

public interface TableData<E extends TableRowData> {

	public E getRow(int index);
	
	public int getRowCount();
}
