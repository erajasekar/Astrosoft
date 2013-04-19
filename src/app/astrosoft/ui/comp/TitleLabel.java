/*
 * AstrosoftLabel.java
 *
 * Created on October 11, 2005, 8:28 PM
 *
 *@author Rajasekar Elango
 */

package app.astrosoft.ui.comp;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.ui.util.UIUtil;

public class TitleLabel extends JLabel {
  
	private static Color DEF_TEXT_CLR  = UIConsts.ROSE;  
   
	public TitleLabel(String text){
        this(text, 12, DEF_TEXT_CLR);
    }
    
    public TitleLabel(String text, int fontSize) {
    	this(text, fontSize, DEF_TEXT_CLR);
	}
    
    public TitleLabel(String text, int fontSize, Color textColor){
    	super(text);
        this.setForeground(textColor);
        this.setFont(UIUtil.getFont(Font.BOLD, fontSize));
    }
    
    public TitleLabel(Enum str){
    	this(str.toString());
    }
}
