/*
 * AstrosoftTable.java
 *
 * Created on November 4, 2005, 9:09 PM
 *
 * @author E. Rajasekar
 */

package app.astrosoft.ui.table;

import app.astrosoft.beans.Degree;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.Language;
import app.astrosoft.consts.Planet;
import app.astrosoft.consts.Roman;
import app.astrosoft.consts.TableStyle;
import app.astrosoft.core.Ephemeris.EphData;
import app.astrosoft.ui.util.UIUtil;
import app.astrosoft.ui.window.AstrosoftWindow;
import app.astrosoft.ui.window.TablePopupWindowModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Format;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;

public class AstrosoftTable extends JTable {
    
    public static final Color tableBorderClr =  Color.GRAY;// gridLineClr;
    
    private static final int rowHeight = 22; //includes margin
    private static final int rowMargin = 2;
    private Font headerFont = UIUtil.getFont(Language.ENGLISH, Font.BOLD, 12);
    private Font tableFont = new Font("Verdana", Font.PLAIN, 11);
    
    TableStyle tableStyle;
    
    public AstrosoftTable(TableModel model, TableStyle tableStyle) {
        super(model);
        this.tableStyle = tableStyle;
        setTableProperties();
    }
    
    private void setTableProperties(){
        
        setHeaderProperties();
        setRowHeight(rowHeight);
        setRowMargin(rowMargin);
       
        setRowSelectionAllowed(false);
        setShowHorizontalLines( false );
        setFont(tableFont);
        
        //setBorder(new LineBorder(tableBorderClr));
        //setGridColor(gridLineClr);
        //doLayout();
        setStyles();
        setDefaultRenderer(Number.class, new NumberCellRenderer());
        setDefaultRenderer(Roman.class, new NumberCellRenderer());
        setDefaultRenderer(Degree.class, new DegreeCellRenderer());
        //setDefaultRenderer(Integer.class, new NumberCellRenderer());
        //setDefaultRenderer(Double.class, new NumberCellRenderer());
        setDefaultRenderer(EphData.class, new EphemerisCellRenderer());
        localizeColumns();
    }
    
    private void setHeaderProperties(){
        JTableHeader header = getTableHeader();
        header.setFont(headerFont);
        header.setPreferredSize(new Dimension(getPreferredSize().width, rowHeight));
        //header.setBorder(new LineBorder(tableBorderClr));
    }
    
    public void addBorders(){
    	//TODO: Find a way to remove bottom border
    	getTableHeader().setBorder(new LineBorder(tableBorderClr));
    	setBorder(new LineBorder(tableBorderClr));
    }
    
    public void addBorder(){
    	setBorder(new LineBorder(tableBorderClr));
    }
    
    /*public void setPreferredHeight() {
    
    	int wd = getColumnModel().getTotalColumnWidth();
    	int ht = getModel().getRowCount() * rowHeight;	
    	
    	super.setPreferredSize(new Dimension(wd, ht));
    }*/
    
    public void setColumnWidth(int wd, AstrosoftTableColumn... cols){
    	
    	for(AstrosoftTableColumn col : cols){
    		int colIndex = ((AstrosoftTableModel) getModel()).getColumnIndex(col);
        	getColumnModel().getColumn(colIndex).setPreferredWidth(wd);
    	}
    	setHeaderProperties();
    }
    
    
    public void addCellMouseListener(final Cell cell, final TableCellPopupListener listener){
        
        MouseListener mouseListener = new MouseAdapter(){

        	Cell source;
        	public void mousePressed(MouseEvent e) {
        		
        		source = UIUtil.getSourceTableCell(e); 
        		
        		if (!source.isAny() && cell.equals(source) ){
        			listener.showPopup(source, e.getPoint());
        		}
        	}
        	
        	public void mouseReleased(MouseEvent e) {
        		
        		source = UIUtil.getSourceTableCell(e);
        		
        		if (!source.isAny() && cell.equals(source) ){
        			listener.hidePopup(source, e.getPoint());
        		}
        	}
        };
        this.addMouseListener(mouseListener); 
          
    }
    
    public void addCellPopupWindow(Cell cell, final TablePopupWindowModel model){
    
    	TableCellPopupListener cellListener = new TableCellPopupListener(){
        
    		AstrosoftWindow window;
    		public void showPopup(Cell cell, Point loc){
            
    			window = new AstrosoftWindow();
    			Point tableLoc = getLocationOnScreen();
                window.setModel(model.getModel(cell));
                loc.translate(tableLoc.x + 10,tableLoc.y - 10);
                window.show(loc);
            }
            public void hidePopup(Cell cell, Point loc){
                window.dispose();
                repaint();
            }
			
    	};
    	addCellMouseListener(cell, cellListener);
    }
    
    public void addColumnPopupWindow(AstrosoftTableColumn col, final TablePopupWindowModel model){
    	addCellPopupWindow(new Cell(col), model);
    }
    
    private void localizeColumns() {
    	
    	AstrosoftTableModel model = (AstrosoftTableModel) getModel();
    	ColumnMetaData colMetaData = model.getColumnMetaData();
    	
    	for(AstrosoftTableColumn col : colMetaData.getLocaleColumns()){
    		
    		TableColumn tc = getColumnModel().getColumn(model.getColumnIndex(col));
    		tc.setCellRenderer(new LocalizedCellRenderer(getCellRenderer(col), tableFont));
    		tc.setHeaderRenderer(new LocalizedCellRenderer(getHeaderRenderer(col), headerFont));
    	}
	}
    
    public TableCellRenderer getCellRenderer(AstrosoftTableColumn col){
    	
    	AstrosoftTableModel model = (AstrosoftTableModel) getModel();
    	TableCellRenderer defRenderer = getColumnModel().getColumn(model.getColumnIndex(col)).getCellRenderer();
    	
    	if (defRenderer == null){
    		defRenderer = getDefaultRenderer(model.getColumnMetaData().getColumnClass(col));
    	}
    	return defRenderer;
    }
    
    protected TableCellRenderer getHeaderRenderer(AstrosoftTableColumn col){
    	
    	AstrosoftTableModel model = (AstrosoftTableModel) getModel();
    	TableCellRenderer defRenderer = getColumnModel().getColumn(model.getColumnIndex(col)).getHeaderRenderer();
    	
    	if (defRenderer == null){
    		//defRenderer = getDefaultRenderer(model.getColumnMetaData().getColumnClass(col));
    		defRenderer = getTableHeader().getDefaultRenderer();
    	}
    	return defRenderer;
    }
    
    public void setCellColor(Cell cell, Color fgClr, Color bgClr){
    
    	AstrosoftTableModel model = (AstrosoftTableModel) getModel();
    	ColumnMetaData colMetaData = model.getColumnMetaData();
    	
    	if (cell.isAnyCol()){
    	
    		for(AstrosoftTableColumn col : colMetaData.getVisibleColumns()){
        		
        		TableColumn tc = getColumnModel().getColumn(model.getColumnIndex(col));
        		tc.setCellRenderer(new ColoredCellRenderer(getCellRenderer(col), cell, colMetaData.getVisibleColumns(), fgClr, bgClr));
        		
        	}
    	}else{
    		TableColumn tc = getColumnModel().getColumn(model.getColumnIndex(cell.col));
    		tc.setCellRenderer(new ColoredCellRenderer(getCellRenderer(cell.col), cell, colMetaData.getVisibleColumns(), fgClr, bgClr));
    	}
    }
    
    public void setRowColor(int row, Color fgClr, Color bgClr){
    	setCellColor(new Cell(row, Cell.ANY_COL), fgClr, bgClr );
    }

    public void setRowColor(int row, Color fgClr){
    	setRowColor(row, fgClr, null);
    }
    
    public void setColumnColor(AstrosoftTableColumn col, Color fgClr, Color bgClr){
    	setCellColor(new Cell(Cell.ANY_ROW, col), fgClr, bgClr );
    }

    public void setColumnColor(AstrosoftTableColumn col, Color fgClr){
    	setColumnColor(col, fgClr, null);
    }
    
    public void setColumnNumberFormat(AstrosoftTableColumn col, Format fmt){
    	
    	TableColumn tc = getColumnModel().getColumn(((AstrosoftTableModel) getModel()).getColumnIndex(col));
		tc.setCellRenderer(new NumberFormatRenderer(getCellRenderer(col), fmt));
    }
    
    public void setCellRenderer(TableCellRenderer renderer, AstrosoftTableColumn col){
    	TableColumn tc = getColumnModel().getColumn(((AstrosoftTableModel) getModel()).getColumnIndex(col));
		tc.setCellRenderer(renderer);
    }
    
    public void setHeaderRenderer(TableCellRenderer renderer, AstrosoftTableColumn col){
    	TableColumn tc = getColumnModel().getColumn(((AstrosoftTableModel) getModel()).getColumnIndex(col));
		tc.setHeaderRenderer(renderer);
    }
    
    private void setStyles(){
    	
    	EnumSet<TableStyle.Style> styles = tableStyle.styles();
    	
    	if (!styles.contains(TableStyle.Style.NO_BORDER)){
    		addBorders();
    	}
    	if (styles.contains(TableStyle.Style.NO_VER_GRID)){
    		setShowVerticalLines( false );
    	}else{
    		setShowVerticalLines( true );
    	}
    	if (styles.contains(TableStyle.Style.NO_SELECTION)){
    		setRowSelectionAllowed(false);
    	}
    	
    	if (styles.contains(TableStyle.Style.HOR_LINE)){
    		setShowHorizontalLines( true );
    	}
    	
    	if (styles.contains(TableStyle.Style.SINGLE_ROW_SELECTION)){
    		setRowSelectionAllowed(true);
    		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	    //setRowSelectionInterval(0,0);
    	}
    	if (styles.contains(TableStyle.Style.MULTI_ROW_SELECTION)){
    		setRowSelectionAllowed(true);
    		setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    	    //setRowSelectionInterval(0,0);
    	}
    	if (styles.contains(TableStyle.Style.NO_DRAGING	)){
    		getTableHeader().setReorderingAllowed(false);
    	}
    	
    }
    
    @Override
    public TableCellRenderer getCellRenderer(int arg0, int arg1) {
    	TableCellRenderer c = super.getCellRenderer(arg0, arg1);
    //	System.out.println("[" + arg0 + " , " + arg1 + "] ->" + c.getClass());
    	
    	return c;
    }
    
    public <E extends TableRowData> void addRowSelectionListener(final TableRowSelectionListener<E> l){
    	
    	ListSelectionModel listSelectionModel = getSelectionModel();
    	listSelectionModel.addListSelectionListener(
                new ListSelectionListener(){

					public void valueChanged(ListSelectionEvent e) {
						
						TableData<E> data = getSelectedData();
						l.selectionChanged(data);
					}
                	
                });
    	
    	setSelectionModel(listSelectionModel);
    }
    
    public <E extends TableRowData>TableData<E> getSelectedData(){
    	
    	ListSelectionModel lsm = getSelectionModel();
    	
    	List<Integer> indexes = new ArrayList<Integer>();
    	
    	int start = lsm.getMinSelectionIndex();
    	int end = lsm.getMaxSelectionIndex();
    	
    	if (start >= 0){
    		for(int i = start; i <= end; i++){
    			if (lsm.isSelectedIndex(i)){
    				indexes.add(i);
    			}
    		}
    	}
		
		return ((AstrosoftTableModel) getModel()).getData(indexes);
    }
}
