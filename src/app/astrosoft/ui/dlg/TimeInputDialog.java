/**
 * TimeInputDialog.java
 * Created On 2006, Apr 11, 2006 6:29:09 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.dlg;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.comp.CalendarChooser;
import app.astrosoft.ui.comp.DateListener;
import app.astrosoft.ui.comp.PlaceChooser;
import app.astrosoft.ui.util.SpringUtilities;
import app.astrosoft.ui.util.UIConsts;

public class TimeInputDialog extends AstrosoftDialog {

	private static final Dimension dlgSize = new Dimension(300,180);
	private static final Dimension panelSize = new Dimension((int)(dlgSize.width * 0.80), (int)(dlgSize.height * 0.70));
	
	private JButton okButton = new JButton("Ok");
	private JButton cancelButton = new JButton("Cancel");
	
	private CalendarChooser timeChooser;
	
	public TimeInputDialog( Enum title, AstroSoft parent, Date initialValue, final DateListener listener ) {
	     super( parent, title.toString(), dlgSize );
	     initComponents();
	     timeChooser.setSelectedDate(initialValue);
	     okButton.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					listener.dateChanged(timeChooser.getSelectedDate());
					System.out.println("dlg -> " + timeChooser.getSelectedDate());				
					closeDialog();
				}
			});
	     setVisible(true);
	}
	
	private void initComponents(){
		
		 timeChooser = CalendarChooser.getTimeChooser();
	     dlgPanel.setLayout(new FlowLayout());
	     dlgPanel.add(createTimeChooser());
	     
	     cancelButton.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					closeDialog();
				}
			});
	     
	     
	     add(dlgPanel);
	     setBackground(UIConsts.THEME_CLR);
	}

	private JPanel createTimeChooser() {
		
		//JPanel panel = new JPanel();
		
		JPanel timePanel = new JPanel(new SpringLayout());
		
		JPanel chooser = timeChooser.getChooser(getTitle());
		timePanel.add(chooser);
		
		JPanel buttonPanel = new JPanel();
		
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		
		timePanel.add(buttonPanel);
		
		SpringUtilities.makeCompactGrid(timePanel, 2, 1, 5,5,10,10);
		
		timePanel.setBorder(UIConsts.getTitleBorder(getTitle()));
		
		timePanel.setPreferredSize(panelSize);
		
		//timeChooser.setBackground(UIConsts.THEME_CLR);
		//panel.add(timePanel);
		
		
		return timePanel;
	}
}
