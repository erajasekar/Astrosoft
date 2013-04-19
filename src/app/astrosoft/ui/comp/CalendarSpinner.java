/**
 * MonthChooser.java
 * Created On 2006, Apr 10, 2006 4:47:04 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.util.SpringUtilities;

public class CalendarSpinner extends CalendarChooser {

	public static final String FMT_MONTH_YEAR =  "MMM yyyy";
	
	public static final String FMT_DATE =  "dd MMM yyyy";
	
	public static final String FMT_YEAR =  "yyyy";
	
	public static final String FMT_MONTH =  "MMM";
	
	public static final String FMT_TIME =  "hh : mm : ss a";
	
	private static final Dimension spinnerSize = new Dimension(80, 20);
	
	private JSpinner spinner;

	private JSpinner.DateEditor editor;
	
	private String dateFormat;
	
	private JPanel chooserPanel;
	
	public CalendarSpinner(String dateFormat){
		super();
		this.dateFormat = dateFormat;
	}
	
	public JPanel getChooser(){
		
		createSpinner();
		chooserPanel = new JPanel(new BorderLayout());
		chooserPanel.add(spinner, BorderLayout.CENTER);
		
		return chooserPanel;
	}
	
	public JPanel getChooser(String label){
		createSpinner();
		
		chooserPanel = new JPanel(new SpringLayout());
		
		JLabel l = new JLabel(label);
		chooserPanel.add(l);
		chooserPanel.add(spinner);
		
		SpringUtilities.makeCompactGrid(chooserPanel, 1, 2, 5,5,10,10);
		
		return chooserPanel;
	}
	
	private void createSpinner(){
		
		SpinnerModel model = new SpinnerDateModel();
		
		spinner = new JSpinner(model);
		spinner.setPreferredSize(spinnerSize);
		
		editor = new JSpinner.DateEditor(spinner, dateFormat);
		spinner.setEditor(editor);
		
		spinner.addChangeListener(new ChangeListener(){

			public void stateChanged(ChangeEvent e) {
				selectionChanged(e);
			}
			
		});
		
	}
	
	@Override
	public void setSelectedDate(Date date){
		spinner.setValue(date);
	}
	
	public void setForeground(Color fgClr){
		editor.getTextField().setForeground(fgClr);
	}
	
	public void setBackground(Color bgClr){
		editor.getTextField().setBackground(bgClr);
		chooserPanel.setBackground(bgClr);
	}

	public void setDateFormat(String format){
		
		Color bgClr = editor.getTextField().getBackground();
		Color fgClr = editor.getTextField().getForeground();
		
		dateFormat = format;
		editor = new JSpinner.DateEditor(spinner, dateFormat);
		
		editor.getTextField().setForeground(fgClr);
		editor.getTextField().setBackground(bgClr);
		
		spinner.setEditor(editor);
	}

	@Override
	public Date getSelectedDate() {
		
		return (Date) spinner.getValue();
	}
}
