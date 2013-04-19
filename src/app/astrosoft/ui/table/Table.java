/**
 * Table.java
 * Created On 2006, Mar 18, 2006 3:59:07 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

public interface Table {

	public TableData<? extends TableRowData> getTableData();
	
	public ColumnMetaData getColumnMetaData();
}
