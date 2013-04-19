/**
 * AstrosoftTableModel.java
 * Created On 2005, Oct 28, 2005 6:22:53 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import app.astrosoft.consts.AstrosoftTableColumn;
import java.util.Collections;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableRowData;


public class AstrosoftTableModel extends AbstractTableModel {

	protected TableData<? extends TableRowData> rows;

	protected ColumnMetaData columnMetaData;

	private List<AstrosoftTableColumn> columns;

	public AstrosoftTableModel(TableData<? extends TableRowData> rows,
			ColumnMetaData columnMetaData) {
		this.rows = rows;
		this.columnMetaData = columnMetaData;
		this.columns = columnMetaData.getColumns();
	}

	public AstrosoftTableModel(Table table){
		this(table.getTableData(), table.getColumnMetaData());
	}
	
	public int getRowCount() {

		return rows.getRowCount();
	}

	public String getColumnName(int col) {
		return columns.get(col).toString();
	}

	public int getColumnCount() {

		return columns.size() - columnMetaData.getHiddenColumnCount();
	}

	public Class<?> getColumnClass(int col) {
		return columnMetaData.getColumnClass(columns.get(col));
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public Object getValueAt(int row, int col) {

		TableRowData columnData = rows.getRow(row);
		Object data = columnData.getColumnData(columns.get(col));
		return data;
	}

	public void updateData(TableData<? extends TableRowData> rows) {
		this.rows = rows;
		this.fireTableDataChanged();
	}

	public void updateColumns(ColumnMetaData columnMetaData){
		this.columnMetaData = columnMetaData;
		this.columns = columnMetaData.getColumns();
		this.fireTableStructureChanged();
	}
	
	public int getColumnIndex(AstrosoftTableColumn col) {
		
		return columns.indexOf(col);
	}

	public AstrosoftTableColumn getColumn(int col) {
		
		AstrosoftTableColumn column = null;
		try{
			column = columns.get(col);
		}catch(ArrayIndexOutOfBoundsException e){}
		return column;
	}

	public ColumnMetaData getColumnMetaData() {
		return columnMetaData;
	}
	
	public <E extends TableRowData>TableData<E> getData(List <Integer> indexes){
		
		List <E> data = new ArrayList<E>();
		for(Integer i : indexes){
			data.add((E)rows.getRow(i));
		}
		
		return TableDataFactory.getTableData(data);
	}
}
