/**
 * FindNameView.java
 * Created On 2007, May 11, 2007 3:11:55 PM
 * @author E. Rajasekar
 */
package app.astrosoft.ui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.Language;
import app.astrosoft.consts.TableStyle;
import app.astrosoft.persistence.NumerologicalName;
import app.astrosoft.service.NumeroNameService;
import app.astrosoft.test.NumeroNameTest;
import app.astrosoft.ui.comp.AstrosoftTabbedPane;
import app.astrosoft.ui.comp.EmptyPagination;
import app.astrosoft.ui.comp.NameAddPanel;
import app.astrosoft.ui.comp.NameSearchPanel;
import app.astrosoft.ui.comp.NumeroNamePagination;
import app.astrosoft.ui.comp.Pagination;
import app.astrosoft.ui.comp.PagingPanel;
import app.astrosoft.ui.comp.PopupListener;
import app.astrosoft.ui.comp.TitledTable;
import app.astrosoft.ui.table.AstrosoftTable;
import app.astrosoft.ui.table.AstrosoftTableModel;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableRowData;
import app.astrosoft.ui.table.TableDataFactory;
import app.astrosoft.ui.table.TableRowSelectionListener;
import app.astrosoft.ui.util.UIConsts;

public class FindNameView extends AstrosoftView {

	private static final Logger log = Logger.getLogger(FindNameView.class.getName());
	
	private static final Dimension size = new Dimension(700, 436);
	
	private static final Dimension tableSize = size;
	
	private static final Point loc = new Point(50, 10);
	private NameSearchPanel searchPanel;
	private NameAddPanel addPanel;
	private AstrosoftTable nameTable;
	private JButton deleteButton;
	private JPanel tablePanel;
	private PagingPanel<NumerologicalName> pagingPanel;
	private int pageLength = 15;
	
	private TableData<? extends TableRowData> data = TableDataFactory.emptyTableData();
	
	AstrosoftTableModel nameModel;

	public FindNameView() {
		super(DisplayStrings.FIND_NAME_STR.toString(), size, loc);
		
		searchPanel = new NameSearchPanel(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				searchButtonClicked();
				
			}
		});
		
		addPanel = new NameAddPanel(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				addButtonClicked();
				
			}
		});
		
		constructTabbedPane();
		constructNameTable();
		
	}

	
	private void constructTabbedPane() {
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		tabbedPane.addTab(DisplayStrings.SEARCH_STR.toString() , searchPanel);
		tabbedPane.addTab(DisplayStrings.ADD_STR.toString() , addPanel);
		
		add(tabbedPane, BorderLayout.PAGE_START);
	}

	private void constructNameTable() {
		
		nameModel = new AstrosoftTableModel(data, NumerologicalName.getColumnMetaData());
		nameTable = new AstrosoftTable(nameModel, TableStyle.MULTI_ROW_GRID);
		
		tablePanel = new TitledTable(null, nameTable, tableSize, createFooter());
		add(tablePanel, BorderLayout.CENTER);
		
		nameTable.addRowSelectionListener(new TableRowSelectionListener<NumerologicalName>(){

			public void selectionChanged(TableData<NumerologicalName> data) {

				if (data.getRowCount() > 0){ 
					NumerologicalName firstRow = data.getRow(0);
					addPanel.setValues(firstRow.getName(), firstRow.getNumeroVal(), firstRow.getNumeroNum());
				}
			}
			
		});
		
		tablePanel.setVisible(false);
		
	}

	private JPanel createFooter() {
		
		
		deleteButton = new JButton(DisplayStrings.DELETE_STR.toString());
		
		JPanel footer = new JPanel(new BorderLayout());
		
		JPanel p = new JPanel();
		p.setBackground(UIConsts.THEME_CLR);
		p.add(deleteButton);
		footer.add(p, BorderLayout.CENTER);
		createPagingPanel(footer);
		
		//deleteButton.setEnabled(false);
		
		deleteButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				log.fine("Delete Button Clicked");
				
				TableData <NumerologicalName> selected = nameTable.getSelectedData();
				List <NumerologicalName> names = new ArrayList<NumerologicalName>();
				
				for(int i = 0 ; i < selected.getRowCount(); i++){
					names.add(selected.getRow(i));
				}
				
				NumeroNameService.deleteNames(names);
				searchButtonClicked();
			}
			
		});
		
		
		return footer;
		
	}
	private void createPagingPanel(JPanel footer) {
		
		Pagination<NumerologicalName> pagination = EmptyPagination.getInstance(pageLength);
		pagingPanel = new PagingPanel<NumerologicalName>(nameModel, pagination);
		
		footer.add(pagingPanel, BorderLayout.EAST);
	}

	private void searchButtonClicked() {
		log.fine("Name Search Button Clicked");
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		tablePanel.setVisible(true);
		pagingPanel.setPagination(new NumeroNamePagination(searchPanel.getName(), searchPanel.getNumeroValue(), searchPanel.getNumeroNumber(), searchPanel.getOperator1(), searchPanel.getOperator2(),pageLength));
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	private void addButtonClicked() {
		log.fine("Name Add Button Clicked");
		NumeroNameService.addName(addPanel.getName());
		addPanel.resetValues();
		searchButtonClicked();
	}

}
