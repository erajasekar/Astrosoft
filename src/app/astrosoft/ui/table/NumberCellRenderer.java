/**
 * NumberCellRenderer.java
 * Created On 2006, Feb 20, 2006 6:28:52 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;


import java.text.Format;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class NumberCellRenderer extends DefaultTableCellRenderer {
 
	Format formatter = new java.text.DecimalFormat("000.00");
	
	public NumberCellRenderer() {
		super();
		setHorizontalAlignment(JLabel.CENTER);
	}
	
	public NumberCellRenderer(Format formatter) {
		this();
		this.formatter = formatter;
	}
	
	@Override
	protected void setValue(Object value) {
		
		if (value instanceof Double) {
			setValue(formatter.format(value));
		}else{
			super.setValue(value);
		}
	}
}
