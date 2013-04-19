/**
 * PlanetPanel.java
 *
 * Created on January 10, 2003, 3:45 PM
 * @author  E. Rajasekar
 */
package app.astrosoft.ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import app.astrosoft.beans.PlanetaryInfo;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.DisplayFormat;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.TableStyle;
import app.astrosoft.ui.comp.TitleLabel;
import app.astrosoft.ui.comp.TitledTable;
import app.astrosoft.ui.table.AstrosoftTable;
import app.astrosoft.ui.table.AstrosoftTableModel;
import app.astrosoft.ui.table.ColumnMetaData;
import app.astrosoft.ui.table.PlanetCellRenderer;


public class PlanetView extends AstrosoftView {

	PlanetaryInfo planetaryInfo;

	/** Creates a new instance of PlanetPanel 
     */
    public PlanetView(String title, PlanetaryInfo planetaryInfo) {

    	super(title, new Dimension(600, 295));
    	this.planetaryInfo = planetaryInfo;

    	ColumnMetaData colMetaData = planetaryInfo.getPlanateryInfoColumnMetaData();
    	AstrosoftTable planetTable = new AstrosoftTable(new AstrosoftTableModel(
				planetaryInfo.getPlanateryInfoTableData(), colMetaData), TableStyle.STANDARD);

    	
    	planetTable.setCellRenderer(new PlanetCellRenderer(planetTable.getCellRenderer(AstrosoftTableColumn.Planet), DisplayFormat.FULL_NAME, true, true, planetaryInfo.getPlanetDirection()),AstrosoftTableColumn.Planet );
		TitleLabel label = new TitleLabel(DisplayStrings.PLANET_POS_STR);

		TitledTable tablePanel = new TitledTable(label, planetTable, viewSize);
		
		add(tablePanel,BorderLayout.CENTER);
		
		this.setVisible(true);

    }
}
