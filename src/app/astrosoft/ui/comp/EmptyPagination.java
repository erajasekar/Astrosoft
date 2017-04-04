/**
 * EmptyPagination.java
 * Created On 2007, May 24, 2007 9:22:54 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableDataFactory;
import static app.astrosoft.ui.table.TableDataFactory.emptyTableData;

public class EmptyPagination extends AbstractPagination {

	private static EmptyPagination instance = null;
	@Override
	protected TableData getData(int startIndex, int maxRows) {
		return emptyTableData();
	}

	private EmptyPagination(int pageLength){
		super(pageLength);
		System.out.println("EP " + pageLength);
	}
	
	public static EmptyPagination getInstance(int pageLength){
		
		if (instance == null){
			instance = new EmptyPagination(pageLength);
		}
		
		return instance;
	}
}
