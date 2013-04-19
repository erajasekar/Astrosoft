/**
 * VargaChartPanel.java
 * Created On 2007, Dec 6, 2007 1:36:39 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import app.astrosoft.beans.PlanetChartData;
import app.astrosoft.beans.PlanetaryInfo;
import app.astrosoft.consts.Varga;
import app.astrosoft.ui.util.UIUtil;

public class VargaChartPanel extends JPanel {

	private PlanetaryInfo planetaryInfo;
	
	private Dimension panelSize;
	
	//private Dimension chartSize;
	
	private Dimension comboSize = new Dimension(200,20);
	
	private JComboBox vargaCombo;

	private Chart chart;
	
	public VargaChartPanel(PlanetaryInfo planetaryInfo, Dimension panelSize) {
		this.planetaryInfo = planetaryInfo;
		this.panelSize = panelSize;
		
		vargaCombo = new JComboBox(Varga.values());
		
		vargaCombo.setFont(UIUtil.getFont("Tahoma", Font.PLAIN, 11));
		
		vargaCombo.setSelectedItem(Varga.Rasi);
		vargaCombo.setPreferredSize(comboSize);
		
		vargaCombo.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				vargaChanged((Varga)vargaCombo.getSelectedItem());
			}
			
		});
		setLayout(new BorderLayout());
		
		//chartSize = panelSize;
		//chartSize = new Dimension((int)(panelSize.width * 0.95), (int) (panelSize.height * 0.95));
		
		vargaChanged(Varga.Rasi);
		
		JPanel p = new JPanel();
		p.add(vargaCombo);
		add(p,BorderLayout.PAGE_START);
		add(new JPanel(),BorderLayout.PAGE_END);
		
		//setPreferredSize(panelSize);
	}
	
	private void vargaChanged(Varga varga){
		
		if (chart!= null) {
			chart.updateChartData(new PlanetChartData(varga, planetaryInfo));
		}else{
			chart = new Chart(new PlanetChartData(varga, planetaryInfo), panelSize);
			add(chart,BorderLayout.CENTER);
		}
	}
}

