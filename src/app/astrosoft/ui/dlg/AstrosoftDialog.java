/*
 * AstrosoftDialog.java
 *
 * Created on October 30, 2005, 8:08 PM
 *
 * @author unknown
 */

package  app.astrosoft.ui.dlg;

import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.ui.util.UIUtil;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public abstract class AstrosoftDialog extends javax.swing.JDialog {
    
    protected AstroSoft parent;
    protected JPanel dlgPanel;
    
    protected  ActionListener closeListener = new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			
			closeDialog();
		}
    	
    };
    
    /** Creates a new instance of AstrosoftDialog */
    public AstrosoftDialog(AstroSoft parent, String title, Dimension size) {
        
        super( parent, title, false );
        this.parent = parent;
        dlgPanel = new JPanel(new AbsoluteLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        /*java.awt.Dimension screenSize =
            java.awt.Toolkit.getDefaultToolkit(  ).getScreenSize(  );*/
        
        java.awt.Dimension screenSize = AstroSoft.getScreenSize();

        setBounds( 
            ( screenSize.width - size.width ) / 2, ( screenSize.height - size.height ) / 2, size.width,
            size.height );    
        this.setResizable(false);
    }
    
    public void setBackground(Color bgColor){
    	super.setBackground(bgColor);
        UIUtil.setPanelBackground(dlgPanel, bgColor);
    }
    
    // Sets background to theme color
    public void setBackground(){
    	setBackground(UIConsts.THEME_CLR);
    }
    
    public void closeDialog(){
        setVisible(false);
        dispose();
    }
    
    public void resetSize(Dimension size){
    	
    	java.awt.Dimension screenSize = AstroSoft.getScreenSize();

        setBounds( 
            ( screenSize.width - size.width ) / 2, ( screenSize.height - size.height ) / 2, size.width,
            size.height ); 
    	
    }
}
