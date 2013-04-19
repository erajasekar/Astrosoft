/**
 * NumeroNamePagination.java
 * Created On 2007, May 23, 2007 6:07:09 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import app.astrosoft.consts.Operator;
import app.astrosoft.persistence.NumerologicalName;
import app.astrosoft.service.NumeroNameService;
import app.astrosoft.ui.table.TableData;

public class NumeroNamePagination extends AbstractPagination<NumerologicalName> {

	private String name;
	private String numeroVal;
	String numeroNum;
	private Operator op1;
	private Operator op2;
	private boolean findAll;
	
	public NumeroNamePagination(String name, String numeroVal, String numeroNum, Operator op1, Operator op2, int pageLen){
		
		super(pageLen);
		this.name = name;
		this.numeroVal = numeroVal;
		this.numeroNum = numeroNum;
		this.op1 = op1;
		this.op2 = op2;
		findAll = false;
	}
	
	public NumeroNamePagination(int pageLen){
		super(pageLen);
		findAll = true;
	}
	
	
	@Override
	protected TableData<NumerologicalName> getData(int startIndex, int maxRows) {
	
		if (findAll){
			return NumeroNameService.findAllNames(startIndex,maxRows);
		}else {
			return NumeroNameService.findDynamic(name, numeroVal, numeroNum, op1, op2, startIndex, maxRows);
		}
	}

}
