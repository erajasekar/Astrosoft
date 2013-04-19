/**
 * TableRowData.java
 * Created On 2005, Oct 27, 2005 8:27:36 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

import app.astrosoft.consts.AstrosoftTableColumn;

public interface TableRowData {

	public Object getColumnData(AstrosoftTableColumn col);
}
