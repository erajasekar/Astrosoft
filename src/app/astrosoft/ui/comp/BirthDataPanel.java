/**
 * BirthDataPanel.java
 * Created On 2006, May 27, 2006 2:19:01 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingWorker;
import javax.swing.UIDefaults;

import app.astrosoft.beans.BirthData;
import app.astrosoft.beans.Place;
import app.astrosoft.consts.Command;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.Language;
import app.astrosoft.export.XMLHelper;
import app.astrosoft.ui.cal.JCalendarCombo;
import app.astrosoft.ui.util.SpringUtilities;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.ui.util.UIUtil;
import app.astrosoft.util.AstroUtil;
import app.astrosoft.util.AstrosoftFileFilter;
import app.astrosoft.util.FileOps;
import app.astrosoft.util.Timer;

public class BirthDataPanel extends JPanel {
	
	private static final Logger log = Logger.getLogger(BirthDataPanel.class.getName());
	
	private JLabel l_name = new JLabel(DisplayStrings.NAME_STR.toString(Language.ENGLISH));
	private JLabel l_date = new JLabel(DisplayStrings.DATE_STR.toString(Language.ENGLISH));
	
	private JTextField name = new JTextField();
	private JButton openButton = new JButton(UIUtil.createImageIcon(Command.OPEN.name()));
	
	private CalendarChooser timeChooser;
	private JCalendarCombo jCalendarCombo;
	private PlaceChooser placeChooser;
	
	private Dimension panelSize;
	private String title;
	
	private Worker w = new Worker();
	
	private class Worker extends SwingWorker<PlaceChooser, Void>{
		
		@Override
        public PlaceChooser doInBackground() {
			try {
				//return new PlaceChooser(new Dimension(panelSize.width - 60, panelSize.height - (int)(panelSize.height * 0.50)), false);
				PlaceChooser pc = new PlaceChooser(new Dimension(panelSize.width - 60, panelSize.height - (int)(panelSize.height * 0.50)), false);
				
				return pc;
			} catch (Exception e){
				
				log.log(Level.SEVERE, "Exception in constructing BirthDataPanel " , e);
			}
			
			return null;
		}
	
		@Override
		protected void done() {
			
		}
	}
	
	public BirthDataPanel(Dimension size, String title){
		
		this.panelSize = size;
		this.title = title;
		
		w.execute();
		
		openButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				browseHoroscope();
			}
			
		});
		
		addComponents();
		setPreferredSize(panelSize);
	}

	private void addComponents() {
		
		setLayout(new SpringLayout());
		
		timeChooser = CalendarChooser.getTimeChooser();
		
		
		jCalendarCombo = new JCalendarCombo( 0, false, 1801, 2099 );
		
		
		JPanel birthPanel = timeChooser.getChooser(DisplayStrings.BIRTH_TIME_STR.toString());
		
		JPanel namePanel = new JPanel(new SpringLayout());
		
		namePanel.add(name);
		namePanel.add(openButton);
		openButton.setPreferredSize(UIConsts.BUTTON_ICON_SIZE);
		
		SpringUtilities.makeCompactGrid(namePanel, 1, 2, 0,0,1,1);
		
		birthPanel.add(l_name, 0);
		birthPanel.add(namePanel, 1);
		
		birthPanel.add(l_date, 2);
		
		birthPanel.add(jCalendarCombo, 3);
		
		
		SpringUtilities.makeCompactGrid(birthPanel, 3, 2, 5,5,10,15);
		
		add(birthPanel);
		
		//placeChooser = new PlaceChooser(new Dimension(panelSize.width - 60, panelSize.height - (int)(panelSize.height * 0.50)), false);
		
		try {
			placeChooser = w.get();
		}catch(Exception e){
			log.log(Level.SEVERE, "Exception in constructing BirthDataPanel " , e);
		}
		
		
		add(placeChooser);
		
		SpringUtilities.makeCompactGrid(this, 2, 1, 5,5,0,0);
		
		setBorder(UIConsts.getTitleBorder(title));
	}
	
	public String getPersonName() {
		return name.getText();
	}
	
	public Place getBirthPlace(){
		return placeChooser.getSelectedPlace();
	}
	
	public Calendar getBirthTime(){
		
		Calendar birthTime = AstroUtil.getCalendar(timeChooser.getSelectedDate());
		
		Calendar birthCal = new GregorianCalendar(
				Integer.parseInt(jCalendarCombo.getSelectedYear()), 
				Integer.parseInt(jCalendarCombo.getSelectedMonth()) - 1,
				Integer.parseInt(jCalendarCombo.getSelectedDay()),
		 		birthTime.get(Calendar.HOUR_OF_DAY),birthTime.get(Calendar.MINUTE),
				birthTime.get(Calendar.SECOND) );
		
		return birthCal;
		
	}
	
	public BirthData getBirthData(){
		return new BirthData(getPersonName(), getBirthTime(), getBirthPlace());
	}
	
	public void setPersonName(String personName){
		name.setText(personName);
	}
	
	public void setBirthPlace(Place place){
		placeChooser.setSelectedPlace(place);
	}
	
	public void setBirthTime(Calendar cal){
		
		timeChooser.setSelectedDate(cal.getTime());
		jCalendarCombo.setSelectedDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE));
	}
	
	public void setBirthData(BirthData birthData){
		setPersonName(birthData.name());
		setBirthTime(birthData.birthDay());
		setBirthPlace(birthData.getBirthPlace());
	}
	
	private void browseHoroscope(){
		
		String file = FileOps.openFileDialog(this, FileOps.FileDialogMode.OPEN, AstrosoftFileFilter.HOROSCOPE_EXTN);
		if (file != null) {
			setBirthData(BirthData.valueOfXMLNode(XMLHelper.parseXML(file).getChildNodes().item(0)));
		}
	}
}
