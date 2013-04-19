/**
 * MultiLineCellRenderer.java
 * Created On 2006, May 9, 2006 5:30:16 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import app.astrosoft.core.Panchang;
import app.astrosoft.ui.util.UIUtil;

public class MultiLineCellRenderer extends DefaultTableCellRenderer {

	private String LINE_SEP;
	//public int rowHeight = 0;
	
	public MultiLineCellRenderer(){
		this("\n");
	}
	
	public MultiLineCellRenderer(String lineSep){
		super();
		LINE_SEP = lineSep;
	}
	
	@Override
	protected void setValue(Object value) {
		
		if (value instanceof String){
			StringBuilder data = new StringBuilder(UIUtil.htmlTag);
			
			for(String line : ((String) value).split(LINE_SEP)){
				data.append(line);
				data.append(UIUtil.brTag);
				//rowHeight = rowHeight + 17;
			}
			
			data.append(UIUtil.closeHtmlTag);
			super.setValue(data.toString());
			
		}else{
			super.setValue(value);
		}
		
	}
	
 	/*@Override
 	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
 		
 		Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
 				row, column);
 		//table.setRowHeight(Panchang.AUS_TIME_ROW, rowHeight );
 		return renderer;
 	}*/

}
