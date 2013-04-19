/*
 * AstrosoftToolBar.java
 *
 * Created on Septembar 24, 2005, 3:23 PM
 *
 * @author E. Rajasekar.
 */

package app.astrosoft.ui.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import app.astrosoft.consts.Command;
import app.astrosoft.core.Horoscope;
import app.astrosoft.ui.AstrosoftActionManager;

public class AstrosoftToolBar extends JToolBar
{
	
	List<JButton> buttons ;
	
	public AstrosoftToolBar(AstrosoftActionManager actionMgr){
		this(Color.WHITE, actionMgr);
	}
	
    public AstrosoftToolBar(Color mclr, AstrosoftActionManager actionMgr) {
        
    	
    	buttons = new ArrayList<JButton>();
    	
    	for(Command toolBarItem : Command.toolBarItems()){
    		buttons.add(new JButton(actionMgr.getAction(toolBarItem)));
    	}
    	
        for (JButton b:buttons){

        	add(b);
        	String cmd = b.getActionCommand();
        	if (cmd.equals(Command.PRINT) || cmd.equals(Command.SHADBALA_VIEW)  || cmd.equals(Command.PANCHANG_VIEW)){
        		//addSeparator();
        		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        		
        		// FIXME size of separator
            	//separator.setPreferredSize(new Dimension(1,20));
            	/*separator.setSize(new Dimension(100,100));
            	separator.setMaximumSize(new Dimension(100,100));
            	separator.setMinimumSize(new Dimension(100,100));
            	separator.setVisible(true);
            	separator.setBounds(0,0,20,20);*/
                add(separator);
        		
        	}
        	b.setText("");
        }
    }
}
