/**
 * AstrosoftWindow.java
 * Created On 2005, Nov 12, 2005 2:17:43 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

import app.astrosoft.ui.util.UIConsts;

public class AstrosoftWindow extends JWindow {
	
	private Color windowBgClr = UIConsts.LIGHT_YELLOW;
    private Color windowTextClr = UIConsts.MEROON_RED;
    private Font windowTextFont = new Font( "Tahoma", Font.BOLD, 10);
    
    private WindowLabelModel model;
    private Component renderer;
    
    public AstrosoftWindow(){
    	renderer = new JLabel();
        this.model = new DefaultWindowLabelModel("default");
    }

    public AstrosoftWindow(Component renderer){
    	this.renderer = renderer;
    }
    
    public AstrosoftWindow(WindowLabelModel model) {
     
    	renderer = new JLabel();
        this.model = model;
        setSize(70,20);
    }

    public AstrosoftWindow(WindowLabelModel model, Color textClr, Font textFont, Color bgClr) {
        
    	this(model);
        windowTextClr = textClr;
        windowTextFont = textFont;
        windowBgClr = bgClr;
    }
    
    public AstrosoftWindow(Color textClr) {
        
        windowTextClr = textClr;
    }

    public AstrosoftWindow(Component renderer, Color textClr, Font textFont, Color bgClr) {
        
    	this(renderer);
        windowTextClr = textClr;
        windowTextFont = textFont;
        windowBgClr = bgClr;
    }

    public void setRenderer(Component renderer){
    	this.renderer = renderer;
    }
    
    public void setModel(WindowLabelModel model){
    	renderer = new JLabel();
    	this.model = model;
    	
    }
    
    public void show(Point loc) {

    	
    	JPanel p = new JPanel(  );
        p.setBorder(LineBorder.createGrayLineBorder());
        p.setBackground(windowBgClr);
        renderer.setFont(windowTextFont);
        renderer.setForeground(windowTextClr);
    	
        if (renderer instanceof JLabel){
    		JLabel label = (JLabel)renderer;
    		label.setText(model.getText());
    	}
    	
    	p.add(renderer);
        add(p);
        setLocation( loc );
        setVisible( true );
        pack();
    }
    
    
}
