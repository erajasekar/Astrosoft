/**
 * MonthSpinner.java
 * Created On 2006, Apr 8, 2006 4:58:52 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;

public abstract class CalendarChooser {

	private List <DateListener> listeners;
	
	//private Date selectedDate;
	
	public static CalendarSpinner getMonthChooser(){
		return new CalendarSpinner(CalendarSpinner.FMT_MONTH_YEAR);
	}
	
	public static CalendarSpinner getTimeChooser(){
		return new CalendarSpinner(CalendarSpinner.FMT_TIME);
	}
	
	public static CalendarSpinner getDateChooser(){
		return new CalendarSpinner(CalendarSpinner.FMT_DATE);
	}
	
	protected CalendarChooser() {
		listeners = new ArrayList<DateListener>();
	}
	
	public void addDateListener(DateListener listener){
		listeners.add(listener);
	}
	
	public void removeDateListener(DateListener listener){
		listeners.remove(listener);
	}
	
	protected void selectionChanged(ChangeEvent e) {
		
		//NOTE: Assumes every chooser is spinner, which may change
		//in future
		JSpinner spinner = (JSpinner) e.getSource();
		//selectedDate = (Date)spinner.getValue();
		notifyListeners((Date)spinner.getValue());
	}
	
	private void notifyListeners(Date selectedDate) {
		for(DateListener l : listeners){
			l.dateChanged(selectedDate);
		}
	}
	
	public abstract Date getSelectedDate(); 
	
	public abstract void setSelectedDate(Date date); 
	
	public abstract JPanel getChooser();
	
	public abstract JPanel getChooser(String label);
	
	public abstract void setForeground(Color fgClr);
	
	public abstract void setBackground(Color bgClr);
}
