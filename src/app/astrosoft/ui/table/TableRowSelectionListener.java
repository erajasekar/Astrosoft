/**
 * TableRowSelectionListener.java
 * Created On 2007, May 15, 2007 5:06:20 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

public interface TableRowSelectionListener<E extends TableRowData> {

	public void selectionChanged(TableData<E> data);
}
