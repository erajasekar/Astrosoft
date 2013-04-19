/**
 * AbstractPagination.java
 * Created On 2007, May 23, 2007 5:45:44 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.util.Iterator;
import java.util.logging.Logger;

import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableDataFactory;
import app.astrosoft.ui.table.TableRowData;
import app.astrosoft.ui.view.FindNameView;

public abstract class AbstractPagination<E extends TableRowData> implements Pagination<E>{
	
	private static final Logger log = Logger.getLogger(AbstractPagination.class.getName());

	protected int currentPage;
	protected int pageLength;
	protected int totalPages;
	protected boolean isFirstPage;
	protected boolean isLastPage;
	protected Iterator<TableData<E>> pageIterator;
	
	public AbstractPagination(int pageLength) {
		
		if (pageLength <= 0 ){
			throw new IllegalArgumentException("Invalid Page Length " + pageLength);
		}
		this.pageLength = pageLength;
		initialize();
	}
	
	private void initialize() {
		isFirstPage = true;
		isLastPage = false;
		currentPage = 0;
		totalPages = 0;
		pageIterator = null;
		
	}

	public AbstractPagination() {
		this(10);
	}
	
	/*public TableData<E> getFirstPage() {
		
		currentPage = 1;
		return getPage(currentPage);
	}*/

	public TableData<E> getNextPage() {

		if (isLastPage){
			currentPage = totalPages + 1;
			log.fine("You are in last page ");
			return TableDataFactory.emptyTableData();
		}
		
		currentPage++;
		totalPages = currentPage;
		return getPage();
	}

	public TableData<E> getPreviousPage() {
		
		//currentPage--;
		if (isFirstPage){
			currentPage = 0;
			log.fine("You are in first page ");
			return TableDataFactory.emptyTableData();
		}
		currentPage--;
		return getPage();
	}

	/*public TableData<E> getLastPage() {
		
		currentPage = totalPages;
		return getPage(currentPage);
	}*/

	public TableData<E> getPage(int pageNum){
		
		currentPage = pageNum;
		return getPage();
	}

	private TableData<E> getPage(){
		
		log.info("Showing Page ( " + currentPage + " )");
		
		TableData<E> data = getData(( (currentPage - 1) * pageLength)  , pageLength);
		
		if (currentPage == 1){
			isFirstPage = true;
		}else {
			isFirstPage = false;
		}
		if (data.getRowCount() < pageLength){
			isLastPage = true;
		}else{
			isLastPage = false;
		}
		return data;
	}

	protected abstract TableData<E> getData(int startIndex, int maxRows);
	
	public void setPageLength(int pageLength){
		this.pageLength = pageLength;
		initialize();
	}
	
	public Iterator<TableData<E>> iterator() {

		if (pageIterator == null){
			
			pageIterator = new Iterator<TableData<E>>(){

				public boolean hasNext() {
					return (!isLastPage);
				}

				public TableData<E> next() {
					return getNextPage();
				}

				public void remove() {
					throw new UnsupportedOperationException("Remove not supported for pagination");
					
				}
				
			};
		}
		return pageIterator;
	}
	public boolean isFirstPage() {
		return isFirstPage;
	}
	
	public boolean isLastPage() {
		return isLastPage;
	}
	
	public int getTotalPages() {
		return totalPages;
	}

	public int getCurrentPage() {
		
		return currentPage;
	}
	
	public int getPageLength() {
		return pageLength;
	}
}
