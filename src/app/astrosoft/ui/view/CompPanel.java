/**
 * CompPanel.java
 *
 * Created on July 3, 2004, 2:35 PM
 *
 * @author E. Rajasekar
 */
package app.astrosoft.ui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import app.astrosoft.beans.PlanetChartData;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.TableStyle;
import app.astrosoft.consts.Varga;
import app.astrosoft.core.Compactibility;
import app.astrosoft.ui.comp.Chart;
import app.astrosoft.ui.comp.TitleLabel;
import app.astrosoft.ui.table.AstrosoftTable;
import app.astrosoft.ui.table.AstrosoftTableModel;
import app.astrosoft.ui.table.Cell;
import app.astrosoft.ui.table.NumberCellRenderer;
import app.astrosoft.ui.table.SortableTable;
import app.astrosoft.ui.table.SortableTableModel;
import app.astrosoft.ui.window.AstrosoftWindow;
import app.astrosoft.ui.window.SphutaLabelModel;
import app.astrosoft.ui.window.TablePopupWindowModel;
import app.astrosoft.ui.window.WindowLabelModel;

public class CompPanel extends javax.swing.JPanel {

	Compactibility c;

	private JLabel ltitle;

	private final int BSTART_X = 20;

	private final int GSTART_X = 410;

	private final int START_Y = 20;

	private final int YINC = 20;

	private final int XINC = 100;

	private int ypos = START_Y;

	public CompPanel(Compactibility c) {

		this.c = c;
		ltitle = new TitleLabel(DisplayStrings.MRAG_COMP_STR);
		setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
		displayInfo();
		displayKutas();
		if (c.hasHoroscope()){
			displayDoshas();
			displayCharts();
		}
	}

	

	private void displayInfo() {

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
		
		this.add(ltitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(
				230, ypos, -1, -1));
		
		ypos = ypos + YINC;
                
		this.add(boyTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(
				BSTART_X, ypos, -1, -1));

		this.add(girlTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(
				GSTART_X, ypos, -1, -1));
		
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

	private void displayKutas() {

		SortableTableModel kutaModel = new SortableTableModel(c
				.getKutaTableData(), Compactibility.getKutaTableColumnMetaData());
		SortableTable kutaTable = new SortableTable(kutaModel, TableStyle.STANDARD);
		kutaTable.setColumnWidth(100, AstrosoftTableColumn.Kuta);
		kutaTable.setRowColor(13, Color.RED);

		ypos = ypos + 6 * YINC;
		this.add(kutaTable.getTableHeader(),
				new org.netbeans.lib.awtextra.AbsoluteConstraints(BSTART_X, ypos,
						-1, -1));
		ypos = ypos + 23;
		this.add(kutaTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(
				BSTART_X, ypos, -1, -1));

	}

	public void displayDoshas() {

		ypos = ypos - 23;
		final AstrosoftTable doshaTable = new AstrosoftTable( new AstrosoftTableModel(c
				.getDoshaTableData(), Compactibility.getDoshaTableColumnMetaData()), TableStyle.STANDARD);
		
		doshaTable.setRowColor(5, Color.RED);
		
		doshaTable.setColumnNumberFormat(AstrosoftTableColumn.Boy, new java.text.DecimalFormat("00.00"));
		doshaTable.setColumnNumberFormat(AstrosoftTableColumn.Girl, new java.text.DecimalFormat("00.00"));
		
		this.add(doshaTable.getTableHeader(),
				new org.netbeans.lib.awtextra.AbsoluteConstraints(320, ypos,
						-1, -1));
		ypos = ypos + 23;
		this.add(doshaTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(
				320, ypos, -1, -1));
		
	}
	
	private void displayCharts() {
		
		ypos = ypos + 320;
		
		Dimension chartSize = new Dimension(360,320);
		
		Chart bRasiChart = new Chart(new PlanetChartData(Varga.Rasi, c.getBoyPlanetaryInfo()), chartSize);

		Chart bNavamsaChart = new Chart(new PlanetChartData(Varga.Navamsa, c.getBoyPlanetaryInfo()), chartSize);
		
		Chart gRasiChart = new Chart(new PlanetChartData(Varga.Rasi, c.getGirlPlanetaryInfo()), chartSize);

		Chart gNavamsaChart = new Chart(new PlanetChartData(Varga.Navamsa, c.getGirlPlanetaryInfo()), chartSize);
		
		JPanel panel = new JPanel(new GridLayout(2,2));
		
		panel.add(bRasiChart);
		panel.add(gRasiChart);
		
		panel.add(bNavamsaChart);
		panel.add(gNavamsaChart);
		
		this.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(
				50, ypos, -1, -1));

	}

}
