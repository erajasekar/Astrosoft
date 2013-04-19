/**
 * CompactibilityView.java
 * Created On 2006, Apr 8, 2006 1:06:44 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import app.astrosoft.beans.PlanetChartData;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.Language;
import app.astrosoft.consts.TableStyle;
import app.astrosoft.consts.Varga;
import app.astrosoft.core.Ashtavarga;
import app.astrosoft.core.Compactibility;
import app.astrosoft.ui.comp.AstrosoftTabbedPane;
import app.astrosoft.ui.comp.Chart;
import app.astrosoft.ui.comp.TitledTable;
import app.astrosoft.ui.table.AstrosoftTable;
import app.astrosoft.ui.table.AstrosoftTableModel;
import app.astrosoft.ui.table.Cell;
import app.astrosoft.ui.table.SortableTable;
import app.astrosoft.ui.table.SortableTableModel;
import app.astrosoft.ui.util.UIUtil;
import app.astrosoft.ui.window.SphutaLabelModel;
import app.astrosoft.ui.window.TablePopupWindowModel;
import app.astrosoft.ui.window.WindowLabelModel;

public class CompactibilityView extends AstrosoftView {

	private static final Dimension tabbedPaneSize = new Dimension(750, 380);
	private static final Dimension viewSize = new Dimension(
			tabbedPaneSize.width, tabbedPaneSize.height + 20);
	
	private static final Dimension kutaTableSize = new Dimension(240,310);
	//private static final Dimension infoTableSize = new Dimension(40,300);
	private static final Dimension doshaTableSize = new Dimension(250,134);
	private static final Dimension chartSize = new Dimension(340,270);
	
	private Compactibility c;
	public CompactibilityView(String title, Compactibility c, Point loc) {
		
		super(title, viewSize, loc);
		this.c = c;
		AstrosoftTabbedPane tabbedPane = new AstrosoftTabbedPane(tabbedPaneSize);
		//Font font = UIUtil.getFont();
		//tabbedPane.setFont(font);
		
		tabbedPane.addTab(DisplayStrings.KUTA_STR.toString(Language.ENGLISH), createKutaPanel());
		
		if (c.hasHoroscope()){
			tabbedPane.addTab(DisplayStrings.DOSHA_STR.toString(Language.ENGLISH), createDoshaPanel());
			tabbedPane.addTab(DisplayStrings.BOY_HOR_STR, createBoyChart());
			tabbedPane.addTab(DisplayStrings.GIRL_HOR_STR, createGirlChart());
		}
		add(tabbedPane, BorderLayout.CENTER);
	}
	
	private JPanel createKutaPanel() {
		
		JPanel kutaPanel = new JPanel(new GridLayout(1,3, 10, 10));
		
		SortableTableModel kutaModel = new SortableTableModel(c
				.getKutaTableData(), Compactibility.getKutaTableColumnMetaData());
		SortableTable kutaTable = new SortableTable(kutaModel, TableStyle.SCROLL);
		kutaTable.setColumnWidth(100, AstrosoftTableColumn.Kuta);
		kutaTable.setRowColor(13, Color.RED);
		
		AstrosoftTableModel boyModel = new AstrosoftTableModel(c
				.getBoyInfo(), Compactibility.getInfoTableColumnMetaData());
		
		AstrosoftTableModel girlModel = new AstrosoftTableModel(c
				.getGirlInfo(), Compactibility.getInfoTableColumnMetaData());
		
		AstrosoftTable boyTable = new AstrosoftTable(boyModel, TableStyle.NONE);
		
		AstrosoftTable girlTable = new AstrosoftTable(girlModel, TableStyle.NONE);
		
		boyTable.setColumnWidth(100, AstrosoftTableColumn.Key);
		girlTable.setColumnWidth(100, AstrosoftTableColumn.Key);
		
		if (c.hasHoroscope()) {
			
			addPopups(boyTable, boyModel, girlTable, girlModel);
		}
		
		TitledTable tablePanel = new TitledTable(kutaTable, kutaTableSize);
		//tablePanel.setPreferredSize(new Dimension(kutaTableSize.width , kutaTableSize.height));
		
		//JScrollPane scrollPane = new JScrollPane(kutaTable); 
		/*kutaPanel.add(boyTable, BorderLayout.WEST);
		kutaPanel.add(scrollPane, BorderLayout.CENTER);
		kutaPanel.add(girlTable, BorderLayout.EAST);*/
		kutaPanel.add(boyTable);
		kutaPanel.add(tablePanel);
		kutaPanel.add(girlTable);
		
		kutaTable.setPreferredScrollableViewportSize(kutaTableSize);
		
		//boyTable.setPreferredSize(infoTableSize);
		//girlTable.setPreferredSize(infoTableSize);
		
		return kutaPanel;
	}

		
	private void addPopups(AstrosoftTable boyTable, final AstrosoftTableModel boyModel, AstrosoftTable girlTable, final AstrosoftTableModel girlModel){
		
		TablePopupWindowModel beejaPopup =  new TablePopupWindowModel(){
            
        	SphutaLabelModel sphutaModel = new SphutaLabelModel();
        	public WindowLabelModel getModel(Cell cell) {
        		
        		int colIndex = boyModel.getColumnIndex(AstrosoftTableColumn.Beeja);
        		
        		sphutaModel.setSphuta((Double)boyModel.getValueAt(cell.row,colIndex));
        		return sphutaModel;
        	}
        	
        };
        
        boyTable.addCellPopupWindow(new Cell(4), beejaPopup);
        
        TablePopupWindowModel kshetraPopup =  new TablePopupWindowModel(){
            
        	SphutaLabelModel sphutaModel = new SphutaLabelModel();
        	public WindowLabelModel getModel(Cell cell) {
        		
        		int colIndex = girlModel.getColumnIndex(AstrosoftTableColumn.Kshetra);
        		
        		sphutaModel.setSphuta((Double)(girlModel.getValueAt(cell.row,colIndex)));
        		return sphutaModel;
        	}
        	
        };
		
        girlTable.addCellPopupWindow(new Cell(4), kshetraPopup);
	}
	
	private JPanel createDoshaPanel(){
		
		final AstrosoftTable doshaTable = new AstrosoftTable( new AstrosoftTableModel(c
				.getDoshaTableData(), Compactibility.getDoshaTableColumnMetaData()), TableStyle.STANDARD);
		
		doshaTable.setRowColor(5, Color.RED);
		
		doshaTable.setColumnNumberFormat(AstrosoftTableColumn.Boy, new java.text.DecimalFormat("00.00"));
		doshaTable.setColumnNumberFormat(AstrosoftTableColumn.Girl, new java.text.DecimalFormat("00.00"));
		
		return new TitledTable(doshaTable, doshaTableSize);
	}
	
	private JPanel createBoyChart(){
		
		Chart bRasiChart = new Chart(new PlanetChartData(Varga.Rasi, c.getBoyPlanetaryInfo()), chartSize);

		Chart bNavamsaChart = new Chart(new PlanetChartData(Varga.Navamsa, c.getBoyPlanetaryInfo()), chartSize);
		
		JPanel panel = new JPanel(new GridLayout(1,2));
		
		panel.add(bRasiChart);
		
		panel.add(bNavamsaChart);
		
		panel.setPreferredSize(new Dimension(chartSize.width * 2, chartSize.height + 70));
		
		return panel;
	}
	
	private JPanel createGirlChart(){
		
		Chart gRasiChart = new Chart(new PlanetChartData(Varga.Rasi, c.getGirlPlanetaryInfo()), chartSize);

		Chart gNavamsaChart = new Chart(new PlanetChartData(Varga.Navamsa, c.getGirlPlanetaryInfo()), chartSize);
		
		JPanel panel = new JPanel(new GridLayout(1,2));
		
		panel.add(gRasiChart);
		
		panel.add(gNavamsaChart);
		
		panel.setPreferredSize(new Dimension(chartSize.width * 2, chartSize.height + 70));
		
		return panel;
	}
}
