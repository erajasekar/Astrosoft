/**
 * MapTableRow.java
 * Created On 2006, Feb 10, 2006 6:20:44 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

import java.util.HashMap;
import java.util.Map;

import app.astrosoft.consts.AstrosoftTableColumn;

public class MapTableRow implements TableRowData {

	Map<AstrosoftTableColumn, Object> row;
	
	public MapTableRow() {
		row = new HashMap<AstrosoftTableColumn, Object>();
	}
	
	public MapTableRow(Map<AstrosoftTableColumn, Object> row){
		this.row = row;
	}
	
	public void addColumn(AstrosoftTableColumn key, Object value){
		this.row.put(key, value);
	}
	
	public Object getColumnData(AstrosoftTableColumn col) {
	
		return row.get(col);
	}
	
	@Override
	public String toString() {
		
		return row.toString();
	}
}
