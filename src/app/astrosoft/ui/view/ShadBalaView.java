/**
 * ShadPanel.java
 *
 * Created on July 12, 2003, 4:02 PM
 * @ author E. Rajasekar.
 */
package app.astrosoft.ui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.text.DecimalFormat;
import java.text.Format;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.Language;
import app.astrosoft.consts.TableStyle;
import app.astrosoft.core.ShadBala;
import app.astrosoft.ui.comp.AstrosoftTabbedPane;
import app.astrosoft.ui.comp.TitledTable;
import app.astrosoft.ui.table.AstrosoftTable;
import app.astrosoft.ui.table.AstrosoftTableModel;
import app.astrosoft.ui.table.SortableTable;
import app.astrosoft.ui.table.SortableTableModel;
import app.astrosoft.ui.util.UIConsts;

public class ShadBalaView extends AstrosoftView {

	ShadBala shadBala;

	private static final Dimension planetBalaTableSize = new Dimension(710, 222);
	private static final Dimension bhavaBalaTableSize = new Dimension(630, 288);
	private static final Dimension tabbedPaneSize = new Dimension(750, 380);
	private static final Dimension panelSize = new Dimension(
			tabbedPaneSize.width, tabbedPaneSize.height + 20);
	
	/*
	 * Creates a new instance of EphPanel
	 * 
	 */
	public ShadBalaView(String title, ShadBala shadBala, Point loc) {

		super(title, panelSize, loc);
		this.shadBala = shadBala;
		JLabel emptyTitle = new JLabel();

		TitledTable shadBalaTablePanel = new TitledTable(emptyTitle, makePlanetBalaTable(),
				planetBalaTableSize);

		TitledTable bhavaBalaTablePanel = new TitledTable(emptyTitle, makeBhavaBalaTable(),
				bhavaBalaTableSize);

		TitledTable sthanaBalaTablePanel = new TitledTable(emptyTitle, makeSthanaBalaTable(),
				planetBalaTableSize);
		
		TitledTable kalaBalaTablePanel = new TitledTable(emptyTitle, makeKalaBalaTable(),
				planetBalaTableSize);

		AstrosoftTabbedPane tabbedPane = new AstrosoftTabbedPane(tabbedPaneSize);

		tabbedPane.addTab(AstrosoftTableColumn.ShadBala.toString(Language.ENGLISH),
				shadBalaTablePanel);
		tabbedPane.addTab(AstrosoftTableColumn.SthanaBala.toString(Language.ENGLISH),
				sthanaBalaTablePanel);
		tabbedPane.addTab(AstrosoftTableColumn.KalaBala.toString(Language.ENGLISH),
				kalaBalaTablePanel);
		tabbedPane.addTab(AstrosoftTableColumn.BhavaBala.toString(Language.ENGLISH),
				bhavaBalaTablePanel);

		add(tabbedPane, BorderLayout.CENTER);
		

		this.setVisible(true);
	}

	private AstrosoftTable makeKalaBalaTable() {
		AstrosoftTable kalaBalaTable = new AstrosoftTable(
				new AstrosoftTableModel(shadBala.getPlanetBalaTableData(),
						shadBala.getKalaBalaColumnMetaData()),
				TableStyle.STANDARD);
		
		kalaBalaTable.setColumnWidth(100, AstrosoftTableColumn.NatonnataBala);
		kalaBalaTable.setColumnColor(AstrosoftTableColumn.KalaBala, Color.RED);
		
		return kalaBalaTable;
	}

	private AstrosoftTable makeSthanaBalaTable() {
		AstrosoftTable sthanaBalaTable = new AstrosoftTable(
				new AstrosoftTableModel(shadBala.getPlanetBalaTableData(),
						shadBala.getSthanaBalaColumnMetaData()),
				TableStyle.STANDARD);
		
		sthanaBalaTable.setColumnWidth(120, AstrosoftTableColumn.OjaYugmarasyamsaBala);
		sthanaBalaTable.setColumnWidth(100, AstrosoftTableColumn.SaptavargajaBala);
		sthanaBalaTable.setColumnColor(AstrosoftTableColumn.SthanaBala, Color.RED);
		
		return sthanaBalaTable;
	}

	private AstrosoftTable makeBhavaBalaTable() {
		SortableTable bhavaBalaTable = new SortableTable(
				new SortableTableModel(shadBala.getBhavaBalaTableData(),
						shadBala.getBhavaBalaColumnMetaData()),
				TableStyle.STANDARD);

		bhavaBalaTable.setColumnWidth(100, AstrosoftTableColumn.BhavaAdhipathiBala);
		bhavaBalaTable.setColumnColor(AstrosoftTableColumn.Rank, UIConsts.DARK_RED);
		bhavaBalaTable.setColumnColor(AstrosoftTableColumn.BhavaBala, Color.RED);
		bhavaBalaTable.setColumnColor(AstrosoftTableColumn.Rupa, Color.RED);
		bhavaBalaTable.setColumnNumberFormat(AstrosoftTableColumn.Rupa, new DecimalFormat("0.0"));
		
		return bhavaBalaTable;
	}

	private AstrosoftTable makePlanetBalaTable() {
		SortableTable planetBalaTable = new SortableTable(
				new SortableTableModel(shadBala.getPlanetBalaTableData(),
						shadBala.getPlanetBalaColumnMetaData()),
				TableStyle.STANDARD);

		planetBalaTable.setColumnColor(AstrosoftTableColumn.Rank, UIConsts.DARK_RED);
		planetBalaTable.setColumnColor(AstrosoftTableColumn.ShadBala, Color.RED);
		planetBalaTable.setColumnColor(AstrosoftTableColumn.BalaPercentage, Color.RED);
		planetBalaTable.setColumnColor(AstrosoftTableColumn.Rupa, Color.RED);
		planetBalaTable.setColumnColor(AstrosoftTableColumn.KashtaBala, Color.BLACK);
		planetBalaTable.setColumnColor(AstrosoftTableColumn.IshtaBala, UIConsts.DARK_GREEN);
		planetBalaTable.setColumnNumberFormat(AstrosoftTableColumn.Rupa, new DecimalFormat("0.0"));
		planetBalaTable.setColumnNumberFormat(AstrosoftTableColumn.ResidentialStrength, new DecimalFormat("00.0"));
		
		return planetBalaTable;
	}

}
