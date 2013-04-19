/**
 * MapTableRowHelper.java
 * Created On 2006, Feb 13, 2006 3:38:59 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

import java.util.Arrays;
import java.util.List;

import app.astrosoft.consts.AstrosoftTableColumn;

public class MapTableRowHelper{
	List <AstrosoftTableColumn> keys;
	
	public MapTableRowHelper(AstrosoftTableColumn... cols) {
		keys = Arrays.asList(cols);
	}
	
	public MapTableRowHelper(ColumnMetaData colMetaData) {
		keys = colMetaData.getColumns();
	}
	
	public MapTableRow createRow(Object...vals){
		
		if (keys.size() < vals.length) {
			throw new IllegalArgumentException("Lengh of keys and values does not match");
		}
		MapTableRow row = new MapTableRow();
		
		for (int i = 0; i < vals.length; i++) {
			row.addColumn(keys.get(i),vals[i]);
			
		}
		return row;
	}
}