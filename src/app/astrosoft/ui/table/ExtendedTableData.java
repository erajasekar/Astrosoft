/**
 * ExtendedTableData.java
 * Created On 2006, Feb 10, 2006 9:05:57 PM
 * @author E. Rajasekar
 */
package app.astrosoft.ui.table;

public class ExtendedTableData<E extends TableRowData> implements TableData<E> {

	TableData<E> t1;

	TableData<E> t2;

	int t1Size;

	public ExtendedTableData(TableData<E> rows, TableData<E> extraRows ) {
		this.t1 = rows;
		this.t2 = extraRows;
		t1Size = rows.getRowCount();
	}
	
	public E getRow(int index) {

		if (index < t1.getRowCount()) {
			return t1.getRow(index);
		} else {
			return t2.getRow(index - t1Size);
		}

	}

	public int getRowCount() {

		return t1Size + t2.getRowCount();
	}

}
