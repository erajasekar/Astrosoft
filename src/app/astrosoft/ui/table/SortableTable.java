/*
 * SortableTable.java
 *
 * Created on November 5, 2005, 2:30 PM
 *
 * @author E. Rajasekar.
 */

package app.astrosoft.ui.table;

import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.TableStyle;
import app.astrosoft.ui.util.UIUtil;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class SortableTable extends AstrosoftTable {
    
    private static final String IMAGE_ARROW_UP = "arrow_up";
    private static final String IMAGE_ARROW_DOWN = "arrow_down";
    
    public SortableTable(SortableTableModel model, TableStyle tableStyle) {
        super(model, tableStyle);
        JTableHeader header = getTableHeader();
        header.addMouseListener(new MouseAdapter(){
            
            public void mouseClicked(MouseEvent e){
                tableHeaderClicked(e);
            }
        });
        
        setSortableHeaderRender();
        //header.setDefaultRenderer(new SortableHeaderRenderer(header.getDefaultRenderer()));
    }
    
    

	private void tableHeaderClicked(MouseEvent e){
        JTableHeader h = (JTableHeader) e.getSource();
        TableColumnModel columnModel = h.getColumnModel();
        
        //Columns may be dragged and re-ordered
        int viewColumn = columnModel.getColumnIndexAtX(e.getX());
        //int colClicked = columnModel.getColumn(viewColumn).getModelIndex();
        int colClicked = convertColumnIndexToModel(viewColumn);
        sortData(colClicked);
    }
    
    private void sortData(int colIndex){
        SortableTableModel model = (SortableTableModel )getModel();
        
        AstrosoftTableColumn colClicked = model.getColumn(colIndex);
        
        if (model.getSortableColumns().contains(colClicked)) {
            model.sortData(colClicked);
            getTableHeader().repaint();
        }
    }
    
    public ImageIcon getSortImageIcon(){
        SortableTableModel model = (SortableTableModel )getModel();
        String imageName = model.getSortInfo().getSortDir()? IMAGE_ARROW_DOWN : IMAGE_ARROW_UP;
        return UIUtil.createImageIcon(imageName);
    }
    
    private void setSortableHeaderRender() {
    	
    	AstrosoftTableModel model = (AstrosoftTableModel) getModel();
    	ColumnMetaData colMetaData = model.getColumnMetaData();
    	
    	for(AstrosoftTableColumn col : colMetaData.getVisibleColumns()){
    		
    		TableColumn tc = getColumnModel().getColumn(model.getColumnIndex(col));
    		tc.setHeaderRenderer(new SortableHeaderRenderer(getHeaderRenderer(col)));
    	}
		
	}
}