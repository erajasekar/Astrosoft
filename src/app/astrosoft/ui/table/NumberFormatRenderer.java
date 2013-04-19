/**
 * NumberFormatRenderer.java
 * Created On 2006, Mar 11, 2006 2:31:13 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

import java.awt.Component;
import java.text.Format;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class NumberFormatRenderer implements TableCellRenderer {

	private TableCellRenderer tableCellRenderer;
	Format formatter = new java.text.DecimalFormat("000.00");
	
	public NumberFormatRenderer(TableCellRenderer tableCellRenderer, Format formatter) {
		this.tableCellRenderer = tableCellRenderer;
		this.formatter = formatter;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		Component c = tableCellRenderer.getTableCellRendererComponent(table,
				value, isSelected, hasFocus, row, column);
		
		if (c instanceof JLabel){
			JLabel l = (JLabel) c;
			l.setHorizontalAlignment(JLabel.CENTER);
			if (value instanceof Double) {
				l.setText(formatter.format(value));
			}else if (value != null){
				l.setText(value.toString());
			}
		}
		return c;
	}

}
