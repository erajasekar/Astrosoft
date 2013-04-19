/*
 * SortInfo.java
 *
 * Created on November 5, 2005, 2:55 PM
 *
 * @author E. Rajasekar.
 */

package app.astrosoft.ui.table;

import app.astrosoft.consts.AstrosoftTableColumn;

public class SortInfo {

    private AstrosoftTableColumn sortBy;
    
    private boolean isDesc = false;
    
    public SortInfo(AstrosoftTableColumn col) {
        sortBy = col;
    }
    
    public SortInfo(AstrosoftTableColumn col, boolean iDesc) {
        this(col);
        this.isDesc = isDesc;
    }
    
    public AstrosoftTableColumn getSortBy(){
        return sortBy;
    }
    
    public boolean getSortDir(){
        return isDesc;
    }
     
    public void setSortBy(AstrosoftTableColumn col){
        sortBy = col;
    }
    
    public void toggleDir(){
        isDesc = isDesc ^ true;
    }
    
    public String toString() {
        return "Sorted by " + sortBy + " isDesc " + isDesc;
    }
}
