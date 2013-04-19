/**
 * LocalizedCellRenderer.java
 * Created On 2006, Feb 17, 2006 8:49:28 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import app.astrosoft.consts.Roman;
import app.astrosoft.ui.util.UIUtil;

public class LocalizedCellRenderer implements TableCellRenderer {

	private TableCellRenderer tableCellRenderer;
	private Font font;
	
	public LocalizedCellRenderer(TableCellRenderer tableCellRenderer, Font font) {
		this.tableCellRenderer = tableCellRenderer;
		this.font = font;
	}
	
	public LocalizedCellRenderer(TableCellRenderer tableCellRenderer) {
		this.tableCellRenderer = tableCellRenderer;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		Component c = tableCellRenderer.getTableCellRendererComponent(table,
				value, isSelected, hasFocus, row, column);
		
		if (! (value instanceof Number || value instanceof Roman) ){
			c.setFont(UIUtil.getFont(font.getFontName(), font.getStyle(), font.getSize(), 14));
		}
		
		return c;
	}
}
