/**
 * AbsLayoutPanel.java
 * Created On 2006, Mar 29, 2006 5:09:23 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;

import app.astrosoft.ui.AstroSoft;

public class AbsLayoutPanel extends JPanel{

	public AbsLayoutPanel(JComponent ...comps) {
		
		super();
		setLayout(null);
		Dimension screenSize = AstroSoft.getScreenSize();
		setBounds(0,0, screenSize.width, screenSize.height);
		
		for(JComponent c : comps){
			this.add(c);
		}
	}
	
}
