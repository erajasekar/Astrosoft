/**
 * PlaceOptionDialog.java
 * Created On 2006, Mar 11, 2006 3:13:01 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.dlg;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.comp.PlaceChooser;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.ui.util.UIUtil;
import app.astrosoft.util.Timer;

public class PlaceOptionDialog extends AstrosoftDialog {

	private static final Dimension dlgSize = new Dimension(400,280);
	private JButton okButton = new JButton(DisplayStrings.OK_STR.toString());
	private PlaceChooser placeChooser;
	//private Timer t = new Timer();
	
	public PlaceOptionDialog( AstroSoft parent ) {
		 
	     super( parent, "Set Default Location", dlgSize );
	     //t.printAndReset();
	     
	     initComponents();
	     //t.printAndReset();
	     okButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				okClicked();
			}
	     });
	     //t.printAndReset();
	     setVisible(true);
	     //t.printAndReset();
	}

	private void initComponents() {
		
		dlgPanel.setLayout(new FlowLayout());
		//t.printAndReset();
		//dlgPanel.add(new JLabel(), BorderLayout.CENTER);
		//dlgPanel.add(new PlaceChooser(), BorderLayout.CENTER);
		placeChooser = new PlaceChooser(new Dimension(dlgSize.width - 50, dlgSize.height - 80), true);
		//t.printAndReset();
		//System.out.println("3" + System.currentTimeMillis());
		dlgPanel.add(placeChooser);
		//t.printAndReset();
		dlgPanel.add(okButton);
		//t.printAndReset();
		add(dlgPanel);
		//t.printAndReset();
		
		setBackground(UIConsts.THEME_CLR);
		//t.printAndReset();
		
	}
	
	private void okClicked() {
		
		try{
		AstroSoft.getPreferences().setPlace(placeChooser.getSelectedPlace());
		closeDialog();
		}catch(Exception e){
			OptionDialog.showDialog("Place Enter Valid Latitude/Longitude ", JOptionPane.ERROR_MESSAGE);
		}
	}
}
