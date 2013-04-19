/**
 * BirthInputDialog.java
 * Created On 2006, May 27, 2006 2:12:20 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.dlg;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import app.astrosoft.beans.BirthData;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.comp.BirthDataPanel;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.util.Timer;

public class BirthInputDialog extends AstrosoftDialog {

	private static final Dimension dlgSize = new Dimension(400,400);
	
	private JButton okButton = new JButton(DisplayStrings.OK_STR.toString());
	
	public BirthInputDialog(AstroSoft parent, BirthData birthData){
		super( parent, "Enter Birth Details", dlgSize );
		
		initComponents(birthData);
		setVisible(true);
	}
	
	public BirthInputDialog(AstroSoft parent){
		this(parent, null);
	}

	private void initComponents(BirthData birthData) {
	
		dlgPanel.setLayout(new FlowLayout());
		
		Timer t = new Timer();
		final BirthDataPanel birthDataPanel = new BirthDataPanel(new Dimension(dlgSize.width - 50, dlgSize.height - 80), DisplayStrings.BIRTH_DATA_STR.toString());
		t.print("bdp -> ");
		dlgPanel.add(birthDataPanel);
		
		if(birthData != null){
			birthDataPanel.setBirthData(birthData);
		}
		
		dlgPanel.add(okButton);
		
		add(dlgPanel);
		
		okButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				parent.computeHoroscope(birthDataPanel.getBirthData());
				closeDialog();
			}
			
		});
		
		setBackground(UIConsts.THEME_CLR);
	}
}
