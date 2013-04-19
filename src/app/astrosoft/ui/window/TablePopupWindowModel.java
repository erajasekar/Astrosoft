/**
 * TablePopupWindowModel.java
 * Created On 2005, Nov 12, 2005 4:13:33 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.window;

import app.astrosoft.ui.table.Cell;

public interface TablePopupWindowModel {

	public WindowLabelModel getModel(Cell cell);
}
