/**
 * Pagination.java
 * Created On 2007, May 23, 2007 5:41:58 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.util.Iterator;

import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableRowData;

public interface Pagination<E extends TableRowData> extends Iterable<TableData<E>> {

	
	//public TableData<E> getFirstPage();
	
	public TableData<E> getNextPage();

	public TableData<E> getPreviousPage();
	
	//public TableData<E> getLastPage();
	
	public TableData<E> getPage(int pageNum);
	
	public boolean isFirstPage();
	
	public boolean isLastPage();
	
	public int getTotalPages();
	
	public int getCurrentPage();
	
	public void setPageLength(int pageLength);
	
	public int getPageLength();
	
	public Iterator<TableData<E>> iterator();
}
