/**
 * ComputeNumberDialog.java
 * Created On 2007, Sep 16, 2007 6:54:02 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.dlg;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentListener;

import javax.swing.JButton;

import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.comp.ComputeNumberPanel;
import app.astrosoft.ui.comp.NameAddPanel;
import app.astrosoft.ui.util.UIConsts;

public class ComputeNumberDialog extends AstrosoftDialog {

	private static final Dimension dlgSize = new Dimension(400,200);
	private static final Dimension panelSize = new Dimension((int)(dlgSize.width * 0.90), (int)(dlgSize.height * 0.65));
	
	public ComputeNumberDialog(AstroSoft parent){
		super(parent,DisplayStrings.COMPUTE_NUMBER_STR.toString(),dlgSize);
		addComponents();
		setVisible(true);
	}

	private void addComponents() {
		
		dlgPanel.setLayout(new FlowLayout());
		dlgPanel.add(new ComputeNumberPanel(panelSize));
		
		JButton close = new JButton("Close");
		close.addActionListener(closeListener);
		
		dlgPanel.add(close);
		
		add(dlgPanel);
		setBackground(UIConsts.THEME_CLR);
		
	}
	
	
	
}
