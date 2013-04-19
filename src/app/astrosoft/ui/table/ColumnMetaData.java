/**
 * ColumnMetaData.java
 * Created On 2005, Oct 28, 2005 5:59:41 PM
 * @author E. Rajasekar
 */
package app.astrosoft.ui.table;

import java.util.Comparator;
import java.util.List;

import app.astrosoft.consts.AstrosoftTableColumn;

public interface ColumnMetaData {

	public List<AstrosoftTableColumn> getColumns();
	
	public List<AstrosoftTableColumn> getVisibleColumns();

	public List<AstrosoftTableColumn> getSortableColumns();
	
	public List<AstrosoftTableColumn> getLocaleColumns();
	
	public int getHiddenColumnCount();

	public Class getColumnClass(AstrosoftTableColumn col);

	public Comparator<TableRowData> getColumnComparator(AstrosoftTableColumn col);
}
