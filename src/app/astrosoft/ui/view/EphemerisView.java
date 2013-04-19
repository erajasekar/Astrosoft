/**
 * EphemerisView.java
 * Created On 2006, Apr 6, 2006 3:33:23 PM
 * @author E. Rajasekar
 */
package app.astrosoft.ui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.table.JTableHeader;

import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.Language;
import app.astrosoft.consts.TableStyle;
import app.astrosoft.core.Ephemeris;
import app.astrosoft.core.Ephemeris.Mode;
import app.astrosoft.pref.AstrosoftPref;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.comp.CalendarChooser;
import app.astrosoft.ui.comp.CalendarSpinner;
import app.astrosoft.ui.comp.DateListener;
import app.astrosoft.ui.comp.TitleLabel;
import app.astrosoft.ui.comp.TitledTable;
import app.astrosoft.ui.table.AstrosoftTable;
import app.astrosoft.ui.table.AstrosoftTableModel;
import app.astrosoft.ui.table.Table;
import app.astrosoft.ui.util.UIUtil;
import app.astrosoft.util.AstroUtil;

public class EphemerisView extends AstrosoftView {

	private static final Dimension viewSize = new Dimension(750, 450);
	private static final Dimension dailyTableSize = new Dimension(viewSize.width, viewSize.height);
	private static final Dimension monthyTableSize = new Dimension(viewSize.width, 288);
	
	private AstrosoftTable ephTable;
	private AstrosoftTableModel tableModel;
	private TitledTable tablePanel;
	
	private CalendarChooser monthChooser;
	
	private Calendar ephDate;
	
	private Mode ephMode;
	
	public EphemerisView(Ephemeris eph, Point loc) {
			
			super(viewSize, loc);
			
			ephMode = eph.getMode();
			ephDate = eph.getDate();
			
			tableModel = new AstrosoftTableModel(eph.getEphemerisTable());
			ephTable = new AstrosoftTable(tableModel, TableStyle.SCROLL_SINGLE_ROW_SELECT);
			
			ephTable.setColumnWidth(60, AstrosoftTableColumn.Sun, AstrosoftTableColumn.Moon, AstrosoftTableColumn.Rahu, AstrosoftTableColumn.Ketu);
			
			monthChooser = CalendarChooser.getMonthChooser();
			
			tablePanel = new TitledTable(ephTable, dailyTableSize);
		
			JTableHeader header = ephTable.getTableHeader();
			tablePanel.add(createEphSelector(header.getBackground(), header.getForeground()), BorderLayout.PAGE_START);
			
			add(tablePanel,BorderLayout.PAGE_START);
			
			monthChooser.addDateListener(new DateListener(){

				public void dateChanged(Date date) {
					ephDate = AstroUtil.getCalendar(date); 
					updateEphTable();
				}
				
			});
	}
	
	private void updateEphTable(){
	
		Table table = new Ephemeris(ephDate, ephMode).getEphemerisTable();
		
		tableModel.updateColumns(table.getColumnMetaData());
		tableModel.updateData(table.getTableData());
	}
	
	private JPanel createEphModeSelector(Color bgClr, Color fgClr){
		JPanel panel = new JPanel();
		
		ActionListener modeListener = new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				ephMode = Mode.valueOf(e.getActionCommand());
				
				if (ephMode.isMonthly()){
					
					tablePanel.setScrollPaneSize(monthyTableSize);
					((CalendarSpinner) monthChooser).setDateFormat(CalendarSpinner.FMT_YEAR);
				}else{
					 tablePanel.setScrollPaneSize(dailyTableSize);
					 int row = ephTable.getSelectedRow();
					 if (row != -1){
						 ephDate.set(Calendar.MONTH, row);
					 }
					 
					 monthChooser.setSelectedDate(ephDate.getTime());
					((CalendarSpinner) monthChooser).setDateFormat(CalendarSpinner.FMT_MONTH_YEAR);
				}
				updateEphTable();
				
			}
			
		};
		ButtonGroup bg = new ButtonGroup();
		
		for(Mode m : Mode.values()){
			JRadioButton button = new JRadioButton(m.toString());
			bg.add(button);
			button.setActionCommand(m.name());
			button.addActionListener(modeListener);
			panel.add(button);
			button.setBackground(bgClr);
			button.setForeground(fgClr);
			
			if ( m == ephMode) {
				button.setSelected(true);
			}
		}
		
		panel.setBackground(bgClr);
		
		return panel;
	}
	
	private JPanel createEphTitle(Color bgClr, Color fgClr){
		
		JPanel panel = new JPanel();
		AstrosoftPref preferences = AstroSoft.getPreferences();
		StringBuilder text = new StringBuilder(DisplayStrings.EPHEMERIS_STR.toString());
		text.append(" For ");
		text.append(preferences.getPlace().city());
		text.append(" at ");
		text.append(AstroUtil.timeFormat(preferences.getEphCalcTime()));
		
		JLabel title = new TitleLabel(text.toString());
		panel.add(title);
		
		title.setFont(UIUtil.getFont(Language.ENGLISH, Font.BOLD, 12));
		title.setForeground(fgClr);
		panel.setBackground(bgClr);
		
		return panel;
	}
	
	private JPanel createEphSelector(Color bgClr, Color fgClr){
		
		JPanel ephSelector = new JPanel(new BorderLayout());
		
		JPanel chooser = monthChooser.getChooser();
		
		monthChooser.setBackground(bgClr);
		monthChooser.setForeground(fgClr);
		
		ephSelector.setBackground(bgClr);
		
		ephSelector.add(chooser, BorderLayout.WEST);
		ephSelector.add(createEphTitle(bgClr, fgClr), BorderLayout.CENTER);
		ephSelector.add(createEphModeSelector(bgClr, fgClr), BorderLayout.EAST);
		
		ephSelector.setBorder(BorderFactory.createEtchedBorder());
		return ephSelector;
	}
}
