/**
 * NameAddPanel.java
 * Created On 2007, May 15, 2007 3:15:14 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.util.AstroUtil;

public class NameAddPanel extends NumeroNamePanel {
	
	public NameAddPanel(final ActionListener l){
		
		super(l);
		addComponents();
		name.addKeyListener(keyListener);
	}

	@Override
	protected String getTitle() {
		
		return DisplayStrings.ADD_STR.toString();
	}

}
