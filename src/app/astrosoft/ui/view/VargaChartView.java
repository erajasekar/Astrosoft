/**
 * VargaChartView.java
 * Created On 2006, Apr 1, 2006 3:37:02 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.astrosoft.beans.PlanetChartData;
import app.astrosoft.beans.PlanetaryInfo;
import app.astrosoft.consts.Varga;
import app.astrosoft.ui.comp.Chart;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.ui.util.UIUtil;

public class VargaChartView extends AstrosoftView {

	private static final Dimension viewSize = new Dimension(650, 400);
	
	private static final Dimension vargaSize = new Dimension((int)(viewSize.width * 0.20), viewSize.height);
	
	private static final Dimension chartSize = new Dimension((int)(viewSize.width * 0.70), (int) (viewSize.height * 0.65));
	
	private JPanel chartPanel;
	
	private PlanetaryInfo planetaryInfo;
	
	public VargaChartView(String title, PlanetaryInfo planetaryInfo) {
		
		super(title, viewSize);
		this.planetaryInfo = planetaryInfo;
		
		JPanel vargaPanel = new JPanel();
		
		final JList vargaList = new JList(Varga.values());
		
		vargaList.setFont(UIUtil.getFont("Tahoma", Font.PLAIN, 11));
		
		vargaList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vargaList.setSelectedIndex(0);
		
		vargaPanel.add(vargaList);
		
		vargaPanel.setPreferredSize(vargaSize);
		
		chartPanel = new JPanel(new BorderLayout());
		
		chartPanel.add(new Chart(new PlanetChartData(Varga.Bhava, planetaryInfo), chartSize), BorderLayout.CENTER);
		
		final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, vargaPanel, chartPanel);
		
		vargaPanel.setBorder(BorderFactory.createEtchedBorder());
		splitPane.setBorder(BorderFactory.createEtchedBorder());
		chartPanel.setBorder(BorderFactory.createEmptyBorder());
		
		vargaList.addListSelectionListener(new ListSelectionListener(){

			public void valueChanged(ListSelectionEvent e) {
				splitPane.remove(chartPanel);
				vargaChanged((Varga)vargaList.getSelectedValue());
				splitPane.add(chartPanel);
			}
		});
		
		add(splitPane,BorderLayout.CENTER);
	}
	
	private void vargaChanged(Varga varga) {
		
		chartPanel.removeAll();
		chartPanel.add(new Chart(new PlanetChartData(varga, planetaryInfo), chartSize), BorderLayout.CENTER);
	}
}
