/**
 * PlanetCellRenderer.java
 * Created On 2006, Apr 1, 2006 1:23:07 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

import java.awt.Color;
import java.awt.Component;
import java.util.EnumMap;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import app.astrosoft.consts.DisplayFormat;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.Planet;
import app.astrosoft.ui.util.UIConsts;

public class PlanetCellRenderer implements TableCellRenderer {

	private TableCellRenderer tableCellRenderer;
	private boolean highlightRetrogrades;
	private boolean expandRetrogrades;
	private DisplayFormat planetFormat;
	private EnumMap<Planet, Boolean> planetDir;
	
	public PlanetCellRenderer(TableCellRenderer tableCellRenderer, DisplayFormat planetFormat, boolean highlightRetrogrades, boolean expandRetrogrades, EnumMap<Planet, Boolean> planetDir) {
		
		this.tableCellRenderer = tableCellRenderer;
		this.highlightRetrogrades = highlightRetrogrades;
		this.expandRetrogrades = expandRetrogrades;
		this.planetFormat = planetFormat;
		this.planetDir = planetDir;
	}
	
	public PlanetCellRenderer(TableCellRenderer tableCellRenderer) {
		this(tableCellRenderer, DisplayFormat.SYMBOL, false, false, null);
	}

	public PlanetCellRenderer(TableCellRenderer tableCellRenderer, boolean highlightRetrogrades, boolean expandRetrogrades, EnumMap<Planet, Boolean> planetDir) {
		this(tableCellRenderer, DisplayFormat.SYMBOL, highlightRetrogrades, expandRetrogrades, planetDir);
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		Component c = tableCellRenderer.getTableCellRendererComponent(table,
				value, isSelected, hasFocus, row, column);
		
		if (value != null && value instanceof Planet && c instanceof JLabel){
			
			JLabel l = new JLabel(((JLabel)c).getText());
			Planet p = (Planet)value;
			
			l.setFont(c.getFont());
			l.setText(p.toString(planetFormat));
			
			Color fgClr = null;
			
			if (p.isAsc()){
				fgClr = Color.RED;
			}
			
			if(p == Planet.Moon){
				fgClr = Color.MAGENTA;
			}
			
			if (highlightRetrogrades){
				if (planetDir.containsKey(p) && planetDir.get(p)){
					fgClr = UIConsts.GREENISH_BLUE;
					
					if(expandRetrogrades){
						l.setText(p.toString(planetFormat) + " (" + DisplayStrings.RETRO_SYM + ")");
					}
				}
			}
			
			if (fgClr != null){
				l.setForeground(fgClr);
			}else{
				l.setForeground(table.getForeground());
			}
			return l;
		}
		return c;
	}
	
}
