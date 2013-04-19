/**
 * PanchangView.java
 * Created On 2006, May 8, 2006 6:27:10 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.ScrollPane;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIDefaults;
import javax.swing.table.TableCellRenderer;

import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.TableStyle;
import app.astrosoft.core.Panchang;
import app.astrosoft.ui.comp.CalendarChooser;
import app.astrosoft.ui.comp.Chart;
import app.astrosoft.ui.comp.DateListener;
import app.astrosoft.ui.table.AstrosoftTable;
import app.astrosoft.ui.table.AstrosoftTableModel;
import app.astrosoft.ui.table.MultiLineCellRenderer;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.ui.util.UIUtil;

public class PanchangView extends AstrosoftView{

	private static final Dimension viewSize = new Dimension(750, 450);
	//private static final Dimension panTableSize = new Dimension(350, 400);
	private static final Dimension chartSize = new Dimension(350, 300);
	
	private static final int KeyColWidth = 130;
	private static final int ValueColWidth = 250;
	private static final int RowHeight = 30;
	private static final int AusRowHeight = 100;
	
	private static final Dimension headerSize = new Dimension(KeyColWidth + ValueColWidth , RowHeight);
	
	private CalendarChooser dateChooser;
	
	private Panchang pan;
	private AstrosoftTableModel panTableModel;
	private AstrosoftTable panTable;
	
	public PanchangView(Panchang pan, Point loc) {
		
		super(viewSize, loc);
		this.pan = pan;
		
		final JPanel panPanel = new JPanel(new BorderLayout());
		
		panPanel.add(createPanTable(), BorderLayout.WEST);
		
		final JPanel chartPanel = createChartPanel();
		panPanel.add(chartPanel, BorderLayout.EAST);
		
		dateChooser.addDateListener(new DateListener(){

			public void dateChanged(Date date) {
				
				Panchang newPan = new Panchang(date);
				panTableModel.updateData(newPan.getPanchangTableData());
				
				panTable.setRowHeight(Panchang.AUS_TIME_ROW, AusRowHeight);
				
				panPanel.remove(chartPanel);
				chartPanel.removeAll();
				chartPanel.add(new Chart(newPan.getPlanetChartData(), chartSize));
				panPanel.add(chartPanel, BorderLayout.EAST);
			}
			
		});
		add(panPanel);
		
	}
	
	private JPanel createChartPanel(){
		Chart panChart = new Chart(pan.getPlanetChartData(), chartSize);
		JPanel chartPanel = new JPanel();
		chartPanel.add(panChart);
		return chartPanel;
	}
	
	private JPanel createPanTable(){
		
		panTableModel = new AstrosoftTableModel(pan.getPanchangTable());
		panTable = new AstrosoftTable(panTableModel, TableStyle.GRID){
			
			TableCellRenderer multiLineRenderer = new MultiLineCellRenderer(",");
			//@Override
			public TableCellRenderer getCellRenderer(int row, int col) {
				
				if (row == Panchang.AUS_TIME_ROW && col == 1){
					//return new MultiLineCellRenderer(",");
					return multiLineRenderer;
				}
				return super.getCellRenderer(row, col);
			}
		};
		
		
		
		panTable.setColumnWidth(ValueColWidth, AstrosoftTableColumn.Value);
		panTable.setColumnWidth(KeyColWidth, AstrosoftTableColumn.Key);
		panTable.setRowHeight(RowHeight);
		panTable.setRowHeight(Panchang.AUS_TIME_ROW, AusRowHeight);
		
		JPanel tablePanel = new JPanel(new BorderLayout());
		
		JPanel tableBodyPanel = new JPanel();
		tableBodyPanel.add(panTable);
		
		tablePanel.add(createPanTableHeader(), BorderLayout.PAGE_START);
		tablePanel.add(tableBodyPanel, BorderLayout.CENTER);
		
		//tablePanel.setBorder(BorderFactory.createEtchedBorder());
		
		//tableBodyPanel.setPreferredSize(panTable.getPreferredSize());
		//tablePanel.setPreferredSize(panTable.getPreferredSize());
		return tablePanel;
	}
	
	private JPanel createPanTableHeader(){
		
		dateChooser = CalendarChooser.getDateChooser();
		JPanel dateChooserPanel = dateChooser.getChooser();
	
		JPanel headerPanel = new JPanel(new BorderLayout());
		JLabel label = new JLabel(DisplayStrings.DATE_STR.toString());
		label.setFont(UIUtil.getFont());
		
		headerPanel.add(label, BorderLayout.WEST);
		headerPanel.add(dateChooserPanel, BorderLayout.EAST);
		
		headerPanel.setBorder(BorderFactory.createEtchedBorder());
		
		label.setPreferredSize(new Dimension(KeyColWidth , RowHeight));
		
		dateChooserPanel.setPreferredSize(new Dimension(ValueColWidth , RowHeight));
		headerPanel.setPreferredSize(headerSize);
		
		headerPanel.setBackground(UIConsts.TABLE_HEADER_BACKGROUND);
		label.setForeground(UIConsts.TABLE_HEADER_FOREGROUND);
		
		dateChooser.setBackground(UIConsts.TABLE_HEADER_BACKGROUND);
		dateChooser.setForeground(UIConsts.TABLE_HEADER_FOREGROUND);
		
		dateChooser.setSelectedDate(pan.getDate().getTime());
		JPanel outerPanel = new JPanel();
		outerPanel.add(headerPanel);
		
		return outerPanel;

	}
}
