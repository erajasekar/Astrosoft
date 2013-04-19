/**
 * DegreeCellRenderer.java
 * Created On 2006, Feb 25, 2006 2:49:23 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

import app.astrosoft.util.AstroUtil;

public class DegreeCellRenderer extends DefaultTableCellRenderer {
	
	public DegreeCellRenderer() {
		super();
		setHorizontalAlignment(JLabel.CENTER);
		setFont(new Font("Verdana", Font.PLAIN, 10));
	}

	@Override
	protected void setValue(Object value) {
		
		if (value instanceof Double) {
			setValue(AstroUtil.dms((Double)value));
		}else{
			super.setValue(value);
		}
	}
}
