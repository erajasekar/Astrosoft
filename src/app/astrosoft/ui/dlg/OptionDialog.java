/**
 * OptionDialog.java
 *
 * Created on December 27, 2002, 4:51 PM
 * @author  E. Rajasekar
 */
package app.astrosoft.ui.dlg;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.ui.util.UIUtil;
import app.astrosoft.util.Utils;


public class OptionDialog  {

	private static final long serialVersionUID = 1L;
	
	public static int showDialog(String message, int messageType){

		int optionType = JOptionPane.DEFAULT_OPTION;
		String title = null;
		
		if (messageType == JOptionPane.ERROR_MESSAGE){
			title = "Error ";
			optionType = JOptionPane.DEFAULT_OPTION;
		}else if (messageType == JOptionPane.QUESTION_MESSAGE){
			title = "Confirm ";
			optionType = JOptionPane.YES_NO_OPTION;
		}
		else if (messageType == JOptionPane.INFORMATION_MESSAGE){
			title = "Information ";
			optionType = JOptionPane.DEFAULT_OPTION;
		}
		
		JOptionPane pane = new JOptionPane(message, messageType, optionType);
		
		JDialog dialog = pane.createDialog(pane, title);
		
		UIUtil.applyOptionPaneBackground(pane,UIConsts.OPTIONPANE_BACKGROUND);
		
		dialog.setVisible(true);
		
		Object selectedValue = pane.getValue();
	    if(selectedValue instanceof Integer) {
	    	return ((Integer)selectedValue).intValue();
		}
	    return JOptionPane.CLOSED_OPTION;
	}
	
}
