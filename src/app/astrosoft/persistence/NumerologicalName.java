/**
 * NumerologicalName.java
 * Created On 2007, May 3, 2007 1:18:26 PM
 * @author E. Rajasekar
 */

package app.astrosoft.persistence;

import java.io.Serializable;

import javax.persistence.*;

import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.ui.table.ColumnMetaData;
import app.astrosoft.ui.table.DefaultColumnMetaData;
import app.astrosoft.ui.table.TableRowData;
import app.astrosoft.util.AstroUtil;

@NamedQueries({
	@NamedQuery(
		name = NumerologicalName.FIND_ALL_NAMES,
		query = NumerologicalName.SELECT_NAME_SQL + NumerologicalName.ORDER_BY
		)
	})

@Entity(name = "NumerologicalName")
@Table(name = "NUMERO_NAME")
public class NumerologicalName implements Serializable, TableRowData {

	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL_NAMES = "findAllNames";
	public static final String SELECT_NAME_SQL = "select n from NumerologicalName n ";
	public static final String ORDER_BY = " order by name ";
	
	@Id @GeneratedValue 
	@Column(name = "NAME_ID")
	private Long nameId;
	
	@Column(name = "NAME" , unique=true)
	private String name;
	
	@Column(name = "NUMERO_VALUE")
	private int numeroVal = -1;
	
	@Column(name = "NUMERO_NUMBER")
	private int numeroNum = -1;
	
	private NumerologicalName() {
		
	}
	
	public NumerologicalName(String name) {
		this.setName(name);
		setNumeroVal(AstroUtil.computeNumeroVal(name));
	}

	public void setNameId(Long nameId) {
		this.nameId = nameId;
	}

	public Long getNameId() {
		return nameId;
	}

	public void setName(String name) {
		this.name = name.toUpperCase();
	}

	public String getName() {
		return name;
	}

	private void setNumeroVal(int numerologicalValue) {
		this.numeroVal = numerologicalValue;
		this.numeroNum = AstroUtil.toNumeroNum(numerologicalValue);
	}

	public int getNumeroVal() {
		return numeroVal;
	}

	public int getNumeroNum() {
		return numeroNum;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(name);
		sb.append(" : ");
		sb.append(numeroVal);
		sb.append(" : ");
		sb.append(numeroNum);
		sb.append("\n");
		return sb.toString();
	}
	
	public Object getColumnData(AstrosoftTableColumn col) {
		
		switch(col) {
			case Name: 
				return getName();
		
			case NumeroValue:
				return getNumeroVal();
				
			case NumeroNumber:
				return getNumeroNum();
		}
		return null;
	}
	
	private static ColumnMetaData columnMetaData = new DefaultColumnMetaData() {
		{
			super.setColumns(AstrosoftTableColumn.Name,
							 AstrosoftTableColumn.NumeroValue,
							 AstrosoftTableColumn.NumeroNumber);
			localizeColumns();
		}
		
	};
	
	public static ColumnMetaData getColumnMetaData() {
		return columnMetaData;
	}
}
