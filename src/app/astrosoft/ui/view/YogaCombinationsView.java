/**
 * YogaCombinationsView.java
 * Created On 2007, Nov 16, 2007 3:21:40 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.astrosoft.beans.PlanetChartData;
import app.astrosoft.beans.PlanetaryInfo;
import app.astrosoft.consts.Varga;
import app.astrosoft.consts.YogaCombination;
import app.astrosoft.ui.comp.Chart;
import app.astrosoft.ui.comp.VargaChartPanel;
import app.astrosoft.ui.util.UIUtil;
import app.astrosoft.xps.yoga.YogaResults;

public class YogaCombinationsView extends AstrosoftView{
	
	private static final Logger log = Logger.getLogger(YogaCombinationsView.class.getName());
	

	private static final Dimension viewSize = new Dimension(790, 470);
	
	private static final Dimension yogaSize = new Dimension((int)(viewSize.width * 0.20), viewSize.height);
	
	private static final Dimension chartSize = new Dimension((int)(viewSize.width * 0.50), (int) (viewSize.height * 0.60));
	
	private static final Point viewLoc = new Point(0,0);
	
	private JPanel chartPanel;
	
	private PlanetaryInfo planetaryInfo;

	private YogaResults yogaResults;

	private JEditorPane editorPane;

	private JList yogaList;
	
	public YogaCombinationsView(String title, YogaResults yogaResults, PlanetaryInfo planetaryInfo) {
		
		super(viewSize, viewLoc);
		this.planetaryInfo = planetaryInfo;
		this.yogaResults = yogaResults;
		
		JPanel yogaPanel = new JPanel();
		
		yogaList = new JList(yogaResults.getYogas().toArray());
		yogaList.setFont(UIUtil.getFont("Tahoma", Font.PLAIN, 11));
		
		yogaList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		yogaList.setSelectedIndex(0);
		
		yogaPanel.add(yogaList);
		
		yogaPanel.setPreferredSize(yogaSize);	
		
		
		final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, yogaPanel, createResultPane());
		
		yogaPanel.setBorder(BorderFactory.createEtchedBorder());
		splitPane.setBorder(BorderFactory.createEmptyBorder());
		
		yogaList.addListSelectionListener(new ListSelectionListener(){

			public void valueChanged(ListSelectionEvent e) {
				//splitPane.remove(chartPanel);
				yogaChanged((YogaResults.Result)yogaList.getSelectedValue());
				//splitPane.add(chartPanel);
			}
		});
		
		add(splitPane,BorderLayout.CENTER);
	}
	
	private void yogaChanged(YogaResults.Result selectedYoga) {
		
		editorPane.setText(selectedYoga.getYoga().getHtmlString());
		
		/*try {
			editorPane.setPage("file://C:\\Documents and Settings\\Raja\\Desktop\\AstroScrap.java.html");
		} catch (IOException e) {
			log.log(Level.SEVERE, "Exception in creation yoga detail html" , e);

		}*/
	}

	private JSplitPane createResultPane(){
		
		chartPanel = new JPanel(new BorderLayout());
		
		//chartPanel.add(new Chart(new PlanetChartData(Varga.Rasi, planetaryInfo), chartSize), BorderLayout.CENTER);
		
		chartPanel.add(new VargaChartPanel(planetaryInfo, chartSize), BorderLayout.CENTER);
		
		final JSplitPane yogaResultPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, chartPanel, createYogaDetailPane());
		
		chartPanel.setBorder(BorderFactory.createEtchedBorder());
		yogaResultPane.setBorder(BorderFactory.createEmptyBorder());
		
		return yogaResultPane;
	}
	
	private Component createYogaDetailPane(){
		
		//JPanel yogaDetail= new JPanel();
		
		editorPane = new JEditorPane();
		
		editorPane.setContentType("text/html");
		editorPane.setEditable(false);
				
		yogaChanged((YogaResults.Result)yogaList.getSelectedValue()); 
		
		editorPane.setBorder(BorderFactory.createEtchedBorder());
		
		return editorPane;
		
		
	}
}

