/**
 * Utils.java
 * Created On 2006, Feb 25, 2006 6:19:01 PM
 * @author E. Rajasekar
 */

package app.astrosoft.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.ui.table.ColumnMetaData;
import app.astrosoft.ui.table.Table;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableRowData;

public class Utils {

	public static <K,V extends Comparable<V>> List<ComparableEntry<K,V>> sortMap(Set<Map.Entry<K,V>> entrySet, boolean isDesc, Comparator<ComparableEntry> comparator){
		
		List<ComparableEntry<K,V>> entries = new ArrayList<ComparableEntry<K,V>>();
		
		for (Map.Entry<K,V> entry : entrySet) {
			entries.add(new ComparableEntry<K,V>(entry));
		}
	
		return sortEntryList(entries, isDesc, comparator);
		
	}
	
	public static <K,V extends Comparable<V>> List<ComparableEntry<K,V>> sortMap(Set<Map.Entry<K,V>> entrySet){
		return sortMap(entrySet,false,null);
	}
	
	public static <K,V extends Comparable<V>> List<ComparableEntry<K,V>> sortMap(Set<Map.Entry<K,V>> entrySet, boolean isDesc){
		return sortMap(entrySet,isDesc,null);
	}
	
	public static <K,V extends Comparable<V>> List<ComparableEntry<K,V>> sortEntryList(List<ComparableEntry<K,V>> entries, boolean isDesc, Comparator<ComparableEntry> comparator){

		if (comparator == null){
			if (isDesc){
				Collections.sort(entries, Collections.reverseOrder());
			}else{
				Collections.sort(entries);
			}
		}else{
			Collections.sort(entries, comparator);
		}
		
		return entries;
	}
	
	public static <K,V extends Comparable<V>> List<ComparableEntry<K,V>> sortEntryList(List<ComparableEntry<K,V>> entries, boolean isDesc){
		return sortEntryList(entries, isDesc, null);
	}
	
	public static String printTableData(TableData<? extends TableRowData> data, ColumnMetaData colMetaData){
		StringBuffer sb = new StringBuffer();
		
		for(int row = 0; row < data.getRowCount(); row++){
			for(AstrosoftTableColumn col : colMetaData.getColumns()){
				sb.append(((TableRowData)data.getRow(row)).getColumnData(col) + "\t");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public static String printTableData(Table table){
		return printTableData(table.getTableData(), table.getColumnMetaData());
	}
}
