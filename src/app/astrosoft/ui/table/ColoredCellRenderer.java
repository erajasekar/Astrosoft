/**
 * ColoredRowRenderer.java
 * Created On 2006, Feb 18, 2006 3:48:24 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

import java.awt.Component;
import java.awt.Color;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import app.astrosoft.consts.AstrosoftTableColumn;

public class ColoredCellRenderer implements TableCellRenderer {

	private TableCellRenderer tableCellRenderer;
	private Cell cell;
	private List<AstrosoftTableColumn> cols;
	private Color bgClr;
	private Color fgClr;
	
	public ColoredCellRenderer(TableCellRenderer tableCellRenderer, Cell cell, List<AstrosoftTableColumn> cols, Color fgClr, Color bgClr) {
		this.tableCellRenderer = tableCellRenderer;
		this.cell = cell;
		this.cols = cols;
		this.fgClr = fgClr;
		this.bgClr = bgClr;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		Component c = tableCellRenderer.getTableCellRendererComponent(table,
				value, isSelected, hasFocus, row, column);
		
		if (this.cell.equals(new Cell(row + 1, cols.get(column)))) {
			
			if (c instanceof JLabel){
				JLabel l = new JLabel(((JLabel)c).getText());
				if (bgClr != null){
					l.setBackground(bgClr);
				}
				
				if (fgClr != null){
					l.setForeground(fgClr);
				}
				
				l.setFont(c.getFont());
				l.setHorizontalAlignment(((JLabel)c).getHorizontalAlignment());
				return l;
			}
		}else{
			//c.setForeground(defFgClr);
			//c.setBackground(defBgClr);
			c.setForeground(table.getForeground());
			c.setBackground(table.getBackground());
		}
		
		return c;
	}

}
