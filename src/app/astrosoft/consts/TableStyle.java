/**
 * TableStyle.java
 * Created On 2006, Feb 18, 2006 5:42:11 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

import java.util.EnumSet;

public enum TableStyle {

	NONE(EnumSet.of(Style.NO_BORDER, Style.NO_VER_GRID,Style.NO_SELECTION , Style.NO_DRAGING)),
	STANDARD(EnumSet.of(Style.NO_SELECTION , Style.NO_DRAGING, Style.NO_BORDER)),
	SINGLE_ROW_SELECT(EnumSet.of(Style.SINGLE_ROW_SELECTION , Style.NO_DRAGING)),
	SCROLL_SINGLE_ROW_SELECT(EnumSet.of(Style.NO_BORDER, Style.SINGLE_ROW_SELECTION, Style.NO_DRAGING)),
	SCROLL(EnumSet.of(Style.NO_BORDER, Style.NO_DRAGING)),
	GRID(EnumSet.of(Style.NO_SELECTION , Style.HOR_LINE, Style.NO_DRAGING)),
	MULTI_ROW_GRID(EnumSet.of(Style.MULTI_ROW_SELECTION, Style.HOR_LINE, Style.NO_BORDER));
	
	;
	
	private EnumSet<Style> styles;
	
	public enum Style {
		NO_BORDER, 
		NO_VER_GRID, 
		NO_SELECTION,
		NO_DRAGING,
		HOR_LINE,
		SINGLE_ROW_SELECTION,
		MULTI_ROW_SELECTION;
		};
	
	private TableStyle(){
	}
	
	private TableStyle(EnumSet<Style> styles){
		this.styles = styles;
	}
	
	public EnumSet<Style> styles(){
		
		return styles;
	}
}
