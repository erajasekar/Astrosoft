/**
 * DefaultTable.java
 * Created On 2006, Mar 18, 2006 4:01:57 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

public class DefaultTable implements Table {

	private TableData<? extends TableRowData> tableData;
	private ColumnMetaData columnMetaData;
	
	public DefaultTable(TableData<? extends TableRowData> tableData, ColumnMetaData columnMetaData) {
	
		this.tableData = tableData;
		this.columnMetaData = columnMetaData;
	}
	public TableData<? extends TableRowData> getTableData() {
		
		return tableData;
	}

	public ColumnMetaData getColumnMetaData() {
		
		return columnMetaData;
	}

}
