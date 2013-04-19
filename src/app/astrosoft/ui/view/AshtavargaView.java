/**
 * AshtavargaView.java
 * Created On 2006, Apr 1, 2006 7:59:50 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.astrosoft.beans.AshtaVargaChartData;
import app.astrosoft.consts.AshtavargaName;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.Language;
import app.astrosoft.consts.TableStyle;
import app.astrosoft.core.Ashtavarga;
import app.astrosoft.ui.comp.AstrosoftTabbedPane;
import app.astrosoft.ui.comp.Chart;
import app.astrosoft.ui.table.AstrosoftTable;
import app.astrosoft.ui.table.AstrosoftTableModel;
import app.astrosoft.ui.util.UIUtil;

public class AshtavargaView extends AstrosoftView {
	
	private static final Dimension tabbedPaneSize = new Dimension(750, 380);
	private static final Dimension viewSize = new Dimension(
			tabbedPaneSize.width, tabbedPaneSize.height + 50);
	
	private static final Dimension chartSize = new Dimension((int)(viewSize.width * 0.32), (int)(viewSize.height * 0.55));
	private static final Dimension gunaSize = new Dimension((int)(viewSize.width * 0.32), (int)(viewSize.height * 0.20));
	
	public AshtavargaView(String title, Ashtavarga ashtavarga, Point loc) {
		
		super(title, viewSize, loc);
		
		AstrosoftTabbedPane tabbedPane = new AstrosoftTabbedPane(tabbedPaneSize);
		Font font = UIUtil.getFont(Font.BOLD, 12,Font.BOLD, 10 );
		//System.out.println(tabbedPane.);
		
		for(AshtavargaName name : AshtavargaName.ashtavargas()){
		
			JPanel panel = new JPanel(new BorderLayout());
			
			JPanel vargaPanel = new JPanel();
			
			Chart ashtavargaChart = new Chart(new AshtaVargaChartData(name, ashtavarga.getAshtavarga(name)), chartSize);
			ashtavargaChart.setTitleFont(font);
			
			Chart trikonaChart = new Chart(new AshtaVargaChartData(AshtavargaName.Trikona, ashtavarga.getTrikona(name)), chartSize);
			trikonaChart.setTitleFont(font);
			
			Chart ekathipathyaChart = new Chart(new AshtaVargaChartData(AshtavargaName.Ekathipathya, ashtavarga.getEkathipathiya(name)), chartSize);
			ekathipathyaChart.setTitleFont(font);
			
			vargaPanel.add(ashtavargaChart);
			vargaPanel.add(trikonaChart);
			vargaPanel.add(ekathipathyaChart);
			
			JPanel gunaPanel = new JPanel();
			
			if (name != AshtavargaName.SarvaAshtavarga){
				AstrosoftTable gunaTable = new AstrosoftTable(new AstrosoftTableModel(ashtavarga.getGunaTable(name)), TableStyle.NONE);
				gunaPanel.add(gunaTable);
				gunaTable.setPreferredSize(gunaSize);
			}
			panel.add(vargaPanel, BorderLayout.CENTER);
			panel.add(gunaPanel, BorderLayout.PAGE_END);
			
			//panel.setBorder(BorderFactory.createEtchedBorder());
			//FIXME: Cannot set tamil font for selected tab. 
			tabbedPane.addTab(name.toString(Language.ENGLISH) , panel);
			
		}
		
		add(tabbedPane, BorderLayout.CENTER);
	}

}
