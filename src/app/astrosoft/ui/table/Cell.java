/**
 * Cell.java
 * Created On 2005, Nov 12, 2005 1:32:10 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

import app.astrosoft.consts.AstrosoftTableColumn;

public class Cell {

	public static final int ANY_ROW = -1;
	public static final AstrosoftTableColumn ANY_COL = null;
	public int row;
	public AstrosoftTableColumn col;
	
	public Cell(int row, AstrosoftTableColumn col){
		this.row = row;
		this.col = col;
	}
	
	public Cell(AstrosoftTableColumn col) {
		this(ANY_ROW, col);
	}
	
	public Cell(int row) {
		this(row, ANY_COL);
	}
	
	public boolean isAny(){
		return ( (row == ANY_ROW) || (col == ANY_COL));
	}
	
	public boolean isAnyRow(){
		return (row == ANY_ROW);
	}
	
	public boolean isAnyCol(){
		return (col == ANY_COL);
	}
	
	@Override
	public String toString() {
		
		return "[ " + row + " , " + col + " ]";
	}
	
	@Override
	public boolean equals(Object obj) {
		
		boolean isEqual = false;
		Cell other = (Cell) obj;
		
		if (!this.isAny() && !other.isAny()){
			isEqual =  (this.row == other.row) && (this.col == other.col);
		}else if(this.isAnyRow() || other.isAnyRow()){
			isEqual = (this.col == other.col);
		}else if(this.isAnyCol() || other.isAnyCol()){
			isEqual = (this.row == other.row);
		}
		
		/*System.out.println(this);
		System.out.println(obj);
		System.out.println(isEqual);*/
		return isEqual;
	}
	
	@Override
	public int hashCode() {

		int hash = 17;
		hash = hash + 31 * row;
		hash = hash + 31 * col.hashCode();
		
		return hash;
	}
}
