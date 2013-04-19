/**
 * Chart.java
 * Created On 2006, Mar 31, 2006 4:56:17 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultSingleSelectionModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ToolTipManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import app.astrosoft.beans.ChartData;
import app.astrosoft.beans.PlanetChartData;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.Language;
import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.TableStyle;
import app.astrosoft.ui.table.AstrosoftTable;
import app.astrosoft.ui.table.AstrosoftTableModel;
import app.astrosoft.ui.table.PlanetCellRenderer;
import app.astrosoft.ui.table.Table;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.ui.window.AstrosoftWindow;
import app.astrosoft.ui.window.DefaultWindowLabelModel;
import app.astrosoft.ui.window.WindowLabelModel;

public class Chart extends JPanel{

	private ChartData chartData;
	
	private List<JPanel> housePanel;
	
	private Dimension houseSize;
	
	private Dimension tableSize;
	
	private int rowHeight;
	
	private JLabel title;
	
	public Chart(ChartData chartData, Dimension chartSize) {
		
		this.chartData = chartData;
		
		houseSize = new Dimension(chartSize.width / 4, chartSize.height / 4);
		
		tableSize = new Dimension((int)(houseSize.width * 0.80), (int)(houseSize.height * 0.80));
		
		rowHeight = tableSize.height / 3;
		
		//Thirteen-th fills center
		housePanel = new ArrayList<JPanel>();
		
		title = new TitleLabel(chartData.getChartName());
		
		setLayout(new GridBagLayout());
		//setPreferredSize(new Dimension(chartSize.width + (3 * 4), chartSize.height + (3 * 4)));
		setPreferredSize(chartSize);
		addHouseTables();
		setVisible(true);
	}
	
	private void addHouseTables() {
		
		Set<Rasi> dataHouses = chartData.getHouses();
		boolean isPlanetChart = (chartData instanceof PlanetChartData);
		int houseNo;
		String toolTip;
		ToolTipManager toolTipManager =  ToolTipManager.sharedInstance();
		Rasi ascendant = chartData.getAscendant();
		
		for(Rasi house : Rasi.values()){
			
			houseNo = house.ordinal();
			
			JPanel panel = new JPanel();
			
			if(dataHouses.contains(house)){

				Table table = chartData.getChartHouseTable(house);
				AstrosoftTable houseTable = new AstrosoftTable(new AstrosoftTableModel(table), TableStyle.NONE);
				panel.add(houseTable, getConstrains(houseNo));

				//houseTable.setDefaultRenderer(Planet.class, new PlanetCellRenderer());
				houseTable.setRowHeight(rowHeight);
				houseTable.setPreferredSize(tableSize);
				houseTable.setBackground(UIConsts.getChartBackground());
	
				if (isPlanetChart){
					decoratePlanetChart(table, houseTable);
					toolTip = house.toString(Language.ENGLISH) + ": " + house.bhava(ascendant);
					houseTable.setToolTipText(toolTip);
					toolTipManager.registerComponent(houseTable);
				}
				//houseTable.addMouseListener(new ChartHouseMouseListener());
			}
			panel.setPreferredSize(houseSize);
			panel.setBackground(UIConsts.getChartBackground());
			
			panel.setBorder(UIConsts.getChartBorder());
			add(panel, getConstrains(houseNo));
			
			if (isPlanetChart){
				toolTip = house.toString(Language.ENGLISH) + ": " + house.bhava(ascendant);
				panel.setToolTipText(toolTip);
				toolTipManager.registerComponent(panel);
			}
			
			housePanel.add(houseNo, panel);
			
			//panel.addMouseListener(new ChartHouseMouseListener());
		}
		
		// Add ChartName at Center
		houseNo = 12;
		JPanel panel = new JPanel();
		
		panel.add(title);
		add(panel, getConstrains(houseNo));
	}

	public void setTitleFont(Font font){
		title.setFont(font);
	}
	
	private void decoratePlanetChart(Table table, AstrosoftTable houseTable) {
		
		PlanetChartData planetChartData = (PlanetChartData) chartData;
		
		boolean highlightRetrogrades = planetChartData.highlightRetrogrades();
		EnumMap<Planet, Boolean> planetDir = planetChartData.getPlanetDir();
		
		for(AstrosoftTableColumn col : table.getColumnMetaData().getVisibleColumns()){
			houseTable.setCellRenderer(new PlanetCellRenderer(houseTable.getCellRenderer(col), highlightRetrogrades, false, planetDir ), col);
		}
		
		createPopups();
	}

	private void createPopups() {
		JPopupMenu popup = new JPopupMenu();
		popup.add("Rajasekar");
		//popup.setSelectionModel();
		
		MouseListener popupListener = new PopupListener(popup);
		this.addMouseListener(popupListener);
	}

	private class ChartHouseMouseListener extends MouseAdapter{
		
		AstrosoftWindow window;
		
		//Container previous_source;
		
		Container source;
		
		public void mouseEntered(MouseEvent e) {
			
			source = (Container) e.getSource();
			
			if (source instanceof JTable){
				source = ((JTable)source).getParent();
			}
			
			//if (previous_source != null && previous_source == source){
			//	return;
			//}
			
			Rasi ascendant = chartData.getAscendant();
			
			if (ascendant != null){
				
				int bhava = Rasi.ofIndex(housePanel.indexOf(source)).bhava(ascendant);
				
				WindowLabelModel windowModel = new DefaultWindowLabelModel(String.valueOf(bhava));
				
				window = new AstrosoftWindow(windowModel);
				window.setPreferredSize(new Dimension(30,20));
    			Point houseLoc = source.getLocationOnScreen();
    			Point loc = e.getPoint();
                
                loc.translate(houseLoc.x + 10,houseLoc.y - 10);
                window.show(loc);
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			
			if (window != null){
				window.dispose();
			}
			//previous_source = source;
		}
	}
	private static GridBagConstraints getConstrains(int house){

		GridBagConstraints c = new GridBagConstraints();

		switch(house){
			case 0: c.gridx = 1;
					c.gridy = 0;
					break;
			case 1: c.gridx = 2;
					c.gridy = 0;
					break;
			case 2: c.gridx = 3;
					c.gridy = 0;
					break;
			case 3: c.gridx = 3;
					c.gridy = 1;
					break;
			case 4: c.gridx = 3;
					c.gridy = 2;
					break;
			case 5: c.gridx = 3;
					c.gridy = 3;
					break;
			case 6: c.gridx = 2;
					c.gridy = 3;
					break;
					
			case 7: c.gridx = 1;
					c.gridy = 3;
					break;
			case 8: c.gridx = 0;
					c.gridy = 3;
					break;
			case 9: c.gridx = 0;
					c.gridy = 2;
					break;
			case 10: c.gridx = 0;
					 c.gridy = 1;
					break;
			case 11: c.gridx = 0;
					 c.gridy = 0;
					 break;
			
					 //Center Panel
			case 12: c.gridx = 1;
					 c.gridy = 1;
					 c.gridwidth = 2;
					 c.gridheight = 2;
					 break;
			default : throw new IllegalArgumentException("house > 12"); 
		}
		
		/*c.ipadx = 1;
		c.ipady = 1;
		c.insets = new Insets(1,1,1,1);*/
		return c;
	}

	public void updateChartData(ChartData chartData){
		
		this.chartData = chartData;
		title.setText(chartData.getChartName());
		//title = new TitleLabel(chartData.getChartName());
		this.removeAll();
		addHouseTables();
		
	}
}
