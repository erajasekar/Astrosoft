/**
 * TableCellPopupListener.java
 * Created On 2005, Nov 12, 2005 1:15:09 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

import java.awt.Point;

public interface TableCellPopupListener {
	
	public void showPopup(Cell cell, Point loc);
	
	public void hidePopup(Cell cell, Point loc);

}
