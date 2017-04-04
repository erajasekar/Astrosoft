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
import static app.astrosoft.persistence.NumerologicalName.getColumnMetaData;
import app.astrosoft.service.NumeroNameService;
import static app.astrosoft.service.NumeroNameService.addName;
import static app.astrosoft.service.NumeroNameService.deleteNames;
import app.astrosoft.test.NumeroNameTest;
import app.astrosoft.ui.comp.AstrosoftTabbedPane;
import app.astrosoft.ui.comp.EmptyPagination;
import static app.astrosoft.ui.comp.EmptyPagination.getInstance;
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
import static app.astrosoft.ui.table.TableDataFactory.emptyTableData;
import app.astrosoft.ui.table.TableRowSelectionListener;
import app.astrosoft.ui.util.UIConsts;
import static java.awt.Cursor.getPredefinedCursor;
import static java.util.logging.Logger.getLogger;

public class FindNameView extends AstrosoftView {

	private static final Logger log = getLogger(FindNameView.class.getName());
	
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
	
	private TableData<? extends TableRowData> data = emptyTableData();
	
	AstrosoftTableModel nameModel;

	public FindNameView() {
		super(DisplayStrings.FIND_NAME_STR.toString(), size, loc);
		
		searchPanel = new NameSearchPanel((ActionEvent e) -> {
                    searchButtonClicked();
        });
		
		addPanel = new NameAddPanel((ActionEvent e) -> {
                    addButtonClicked();
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
		
		nameModel = new AstrosoftTableModel(data, getColumnMetaData());
		nameTable = new AstrosoftTable(nameModel, TableStyle.MULTI_ROW_GRID);
		
		tablePanel = new TitledTable(null, nameTable, tableSize, createFooter());
		add(tablePanel, BorderLayout.CENTER);
		
		nameTable.addRowSelectionListener((TableData<NumerologicalName> data1) -> {
            if (data1.getRowCount() > 0) {
                NumerologicalName firstRow = data1.getRow(0);
                addPanel.setValues(firstRow.getName(), firstRow.getNumeroVal(), firstRow.getNumeroNum());
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
		
		deleteButton.addActionListener((ActionEvent e) -> {
                    log.fine("Delete Button Clicked");
            TableData <NumerologicalName> selected = nameTable.getSelectedData();
            List <NumerologicalName> names = new ArrayList<>();
            for(int i = 0 ; i < selected.getRowCount(); i++){
                names.add(selected.getRow(i));
            }
            deleteNames(names);
            searchButtonClicked();
        });
		
		
		return footer;
		
	}
	private void createPagingPanel(JPanel footer) {
		
		Pagination<NumerologicalName> pagination = getInstance(pageLength);
		pagingPanel = new PagingPanel<>(nameModel, pagination);
		
		footer.add(pagingPanel, BorderLayout.EAST);
	}

	private void searchButtonClicked() {
		log.fine("Name Search Button Clicked");
		setCursor(getPredefinedCursor(Cursor.WAIT_CURSOR));
		tablePanel.setVisible(true);
		pagingPanel.setPagination(new NumeroNamePagination(searchPanel.getName(), searchPanel.getNumeroValue(), searchPanel.getNumeroNumber(), searchPanel.getOperator1(), searchPanel.getOperator2(),pageLength));
		setCursor(getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	private void addButtonClicked() {
		log.fine("Name Add Button Clicked");
		addName(addPanel.getName());
		addPanel.resetValues();
		searchButtonClicked();
	}

}
