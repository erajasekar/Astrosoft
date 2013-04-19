/*
 * SortableTableModel.java
 *
 * Created on November 5, 2005, 2:28 PM
 *
 * @author E. Rajasekar.
 */
 
package app.astrosoft.ui.table;

import app.astrosoft.consts.AstrosoftTableColumn;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortableTableModel extends AstrosoftTableModel {
    
    private SortInfo sortInfo;
    private List <AstrosoftTableColumn> sortableColumns;
    
    public SortableTableModel(TableData<? extends TableRowData> rows, ColumnMetaData columnMetaData) {
        super(rows, columnMetaData);
    }
    
    public SortableTableModel(TableData<? extends TableRowData> rows, ColumnMetaData columnMetaData, AstrosoftTableColumn initialSortByCol) {
        this(rows, columnMetaData);
        sortData(initialSortByCol);
    }
    
    public void sortData(AstrosoftTableColumn sortBy){
        
        //Same column clicked again, toggle dir
        if (sortInfo != null && sortInfo.getSortBy() == sortBy){
            sortInfo.toggleDir();
        }else{
            sortInfo = new SortInfo(sortBy);
        }
        sortData();
    }
    
    private void sortData(){
   
        if (sortInfo != null) {
            Comparator<TableRowData> cmp = columnMetaData.getColumnComparator(sortInfo.getSortBy());

            // If descending reverse comparator
            if (sortInfo.getSortDir()){
            	cmp = Collections.reverseOrder(cmp);
            }
            
            rows = TableDataFactory.getSortedTableData(rows, cmp);
            //Collections.sort(rows, cmp);
            
        }
        fireTableDataChanged();
    }
    
    public void updateData(TableData<? extends TableRowData> rows){
        
        this.rows = rows;
        sortData();
    }
    
    public List<AstrosoftTableColumn> getSortableColumns(){
        return columnMetaData.getSortableColumns();
    }
    
    public SortInfo getSortInfo(){
        return sortInfo;
    }
}
