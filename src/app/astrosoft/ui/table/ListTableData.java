/**
 * ListTableData.java
 * Created On 2006, Feb 8, 2006 7:36:26 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.Collections;
import static java.util.Collections.sort;
import java.util.Comparator;
import java.util.List;

public class ListTableData<E extends TableRowData> implements TableData<E> {

	List<E> rows;
	
	public ListTableData() {
	}
	
	public ListTableData(List<E> rows) {
		this.rows = rows;
	}
	
	public ListTableData(E[] rows) {
		this.rows = asList(rows);
	}
	
	public ListTableData(TableData<E> data) {
		rows = new ArrayList<>(data.getRowCount());
		for(int i = 0; i < data.getRowCount(); i++){
			rows.add(data.getRow(i));
		}
	}
	
	public ListTableData(TableData<E> data, Comparator<TableRowData> cmp) {
		this(data);
		sort(rows, cmp);
	}
	
	public E getRow(int index) {
		
		return rows.get(index);
	}

	public int getRowCount() {
		
		return rows.size();
	}
	
	public List<E> getAllRows(){
		return rows;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < getRowCount(); i++) {
			sb.append(getRow(i).toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
