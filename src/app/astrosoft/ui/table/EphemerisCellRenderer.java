/**
 * EphemerisCellRenderer.java
 * Created On 2006, Apr 7, 2006 6:23:50 PM
 * @author E. Rajasekar
 */
 package app.astrosoft.ui.table;

 import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import app.astrosoft.consts.DisplayFormat;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.core.Panchang;
import app.astrosoft.core.Ephemeris.EphData;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.ui.util.UIUtil;
import static app.astrosoft.ui.util.UIUtil.getFontTag;
import app.astrosoft.util.AstroUtil;
import static app.astrosoft.util.AstroUtil.todegmin;

 public class EphemerisCellRenderer extends DefaultTableCellRenderer {
 	
	//private static final String fontClr = Integer.toHexString(UIConsts.DARK_GREEN.getRed()) + Integer.toHexString(UIConsts.DARK_GREEN.getGreen()) + Integer.toHexString(UIConsts.DARK_GREEN.getBlue());
	
	public EphemerisCellRenderer() {
 		super();
 		//Reset if language option gets changed.
 		//fontTag = " <font face='" + UIUtil.getFont().getFamily() + "' color='#00468C'>";
 		//setHorizontalAlignment(JLabel.CENTER);
 	}

 	@Override
 	protected void setValue(Object value) {
 		
 		if (value instanceof EphData) {
 			EphData ephValue = (EphData) value;
 			StringBuilder data = new StringBuilder(UIUtil.htmlTag);
 			
 			String house = getFontTag() + ephValue.getHouse().toString(DisplayFormat.SYMBOL) + UIUtil.closeFontTag ;
 			
 			//System.out.println(house);
 			//data.append(ephValue.)
 			data.append(todegmin(ephValue.getPosition(), house));
 			
 			if (ephValue.isReverse()){
 				
 				//data.append("&nbsp;");
 				data.append(getFontTag());
 				data.append(DisplayStrings.RETRO_SYM);
 				data.append(UIUtil.closeFontTag);
 			}
 			data.append(UIUtil.closeHtmlTag);
 			super.setValue(data.toString());
 		}else{
 			super.setValue(value);
 		}
 	}
 	
 }