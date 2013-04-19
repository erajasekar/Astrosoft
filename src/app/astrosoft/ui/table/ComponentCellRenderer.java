/**
 * ComponentCellRenderer.java
 * Created On 2006, Apr 8, 2006 4:48:51 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ComponentCellRenderer implements TableCellRenderer {
	
	Component renderer;

	public ComponentCellRenderer(Component renderer) {
		this.renderer = renderer;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		return renderer;
	}
	
	

}
