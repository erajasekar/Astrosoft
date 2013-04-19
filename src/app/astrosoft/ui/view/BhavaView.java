/**
 * BhavaPanel.java
 *
 * Created on January 10, 2003, 5:43 PM
 * @author  E. Rajasekar
 */
package app.astrosoft.ui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.border.LineBorder;

import app.astrosoft.beans.HousePosition;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.TableStyle;
import app.astrosoft.ui.comp.TitleLabel;
import app.astrosoft.ui.comp.TitledTable;
import app.astrosoft.ui.table.AstrosoftTable;
import app.astrosoft.ui.table.AstrosoftTableModel;

public class BhavaView extends AstrosoftView {

	HousePosition housePosition;

	/** Creates a new instance of BhavaPanel */
	public BhavaView(String title, HousePosition housePosition) {

		super(title, new Dimension(600, 340));
		//super(new Dimension(400, 100), loc);
		this.housePosition = housePosition;
		
		AstrosoftTable bhavaTable = new AstrosoftTable(new AstrosoftTableModel(
				housePosition.getBhavaTableData(), housePosition
						.getBhavaTableColumnData()), TableStyle.STANDARD);

		bhavaTable.setColumnWidth(40, AstrosoftTableColumn.Bhava);
		
		TitleLabel label = new TitleLabel(DisplayStrings.BHAVA_POS_STR);

		TitledTable tablePanel = new TitledTable(label, bhavaTable, viewSize);
		// TODO: Should make location independant
		add(tablePanel,BorderLayout.CENTER);
		//tablePanel.setBorder(new LineBorder(Color.BLUE));
		this.setVisible(true);
	}
}
