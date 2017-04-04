/*
 * DefaultColumnMetaData.java
 *
 * Created on November 5, 2005, 2:04 PM
 *
 * @author E. Rajasekar.
 */

package app.astrosoft.ui.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import app.astrosoft.consts.AstrosoftTableColumn;
import static java.util.Arrays.asList;

public class DefaultColumnMetaData implements ColumnMetaData {
    
    protected List<AstrosoftTableColumn> columns = new ArrayList<>();
    
    protected List<AstrosoftTableColumn> sortableColumns = new ArrayList<>();
    
    protected List<AstrosoftTableColumn> localeColumns = new ArrayList<>();
    
    protected int hiddenCols = 0;
    
    public DefaultColumnMetaData() {
	}
    
    public DefaultColumnMetaData(AstrosoftTableColumn... cols) {
		setColumns(cols);
	}
    
    public DefaultColumnMetaData(Set<AstrosoftTableColumn> cols) {
    	columns.addAll(cols);
	}
    
    public DefaultColumnMetaData(List<AstrosoftTableColumn> cols) {
    	columns.addAll(cols);
	}
    
    public List<AstrosoftTableColumn> getColumns(){
        return columns;
    }
    
    public List<AstrosoftTableColumn> getSortableColumns(){
        return sortableColumns;
    }
	
    public Class getColumnClass(AstrosoftTableColumn col){
        return String.class;
    }
	
    public Comparator getColumnComparator(AstrosoftTableColumn col){
         return null;
    }
    
    public int getHiddenColumnCount() {
    	return hiddenCols;
    }
    
    public void setColumns(AstrosoftTableColumn... cols){
        columns = asList(cols);
    }
    
    public void addColumns(List<AstrosoftTableColumn> cols) {
    	columns.addAll(cols);
	}
    
    public void setSortableColumns(AstrosoftTableColumn... cols){
        sortableColumns = asList(cols);
    }
    
    public void setHiddenColumnCount(int count){
    	hiddenCols = count;
    }

    public void localizeColumns(AstrosoftTableColumn... cols) {
		localeColumns = asList(cols);
	}
    
    public void localizeColumns() {
		localeColumns = getVisibleColumns();
	}
    
	public List<AstrosoftTableColumn> getLocaleColumns() {
		
		return localeColumns;
	}

	public List<AstrosoftTableColumn> getVisibleColumns() {
		
		return columns.subList(0,columns.size() - hiddenCols);
	}
	
	@Override
	public String toString() {
		
		return columns.toString();
	}
}
