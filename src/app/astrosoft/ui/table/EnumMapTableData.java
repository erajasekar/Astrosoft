/**
 * EnumMapTableData.java
 * Created On 2006, Feb 10, 2006 7:22:59 PM
 * @author E. Rajasekar
 */
package app.astrosoft.ui.table;

import java.util.EnumMap;
import java.util.Map;

public class EnumMapTableData<K extends Enum<K>, V extends TableRowData> implements TableData<V> {

	Map<K,V> data;
	K []keys;
	
	public EnumMapTableData(EnumMap<K,V> data, Class <K> enumClass) {
		this.data = data;
		keys = enumClass.getEnumConstants();
	}
	
	public V getRow(int index) {
		
		return data.get(keys[index]);
	}

	public int getRowCount() {
		
		return keys.length;
	}

}
