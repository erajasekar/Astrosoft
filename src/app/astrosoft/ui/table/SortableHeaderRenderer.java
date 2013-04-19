/** SortableHeaderRenderer.java
 *
 *  Created On 2005, Nov 11, 2005 8:07:06 PM
 *
 *  @author E. Rajasekar
 */

package app.astrosoft.ui.table;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import app.astrosoft.consts.AstrosoftTableColumn;

public class SortableHeaderRenderer implements TableCellRenderer {
	private TableCellRenderer tableCellRenderer;

	public SortableHeaderRenderer(TableCellRenderer tableCellRenderer) {
		this.tableCellRenderer = tableCellRenderer;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component c = tableCellRenderer.getTableCellRendererComponent(table,
				value, isSelected, hasFocus, row, column);

		SortableTableModel model = (SortableTableModel) table.getModel();
		AstrosoftTableColumn modelColumn = model.getColumn(table
				.convertColumnIndexToModel(column));

		SortInfo sortInfo = model.getSortInfo();
		if (sortInfo != null && sortInfo.getSortBy() == modelColumn) {
			if (c instanceof JLabel) {
				JLabel l = (JLabel) c;
				l.setHorizontalTextPosition(JLabel.LEFT);
				l.setIcon(((SortableTable) table).getSortImageIcon());
				return l;
			}
		} else {
			((JLabel) c).setIcon(null);
		}
		return c;
	}
}
