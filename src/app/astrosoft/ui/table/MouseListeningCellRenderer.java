/**
 * MouseListeningCellRenderer.java
 * Created On 2005, Nov 11, 2005 8:19:56 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

import java.awt.Component;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/** Table Cell Renderer Implementation that listenes
 *  for mouse events
 */
public class MouseListeningCellRenderer implements TableCellRenderer {
	private TableCellRenderer tableCellRenderer;
	private MouseListener listener;

	public MouseListeningCellRenderer(TableCellRenderer tableCellRenderer, MouseListener listener) {
		this.tableCellRenderer = tableCellRenderer;
		this.listener = listener;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		Component c = tableCellRenderer.getTableCellRendererComponent(table,
				value, isSelected, hasFocus, row, column);
		c.addMouseListener(listener);
		return c;
	}
}
