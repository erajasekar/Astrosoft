/**
 * MuhurthaPanel.java
 * Created On 2005, Oct 22, 2005 7:08:58 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import app.astrosoft.beans.Interval;
import app.astrosoft.beans.MuhurthaBean;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.DisplayFormat;
import app.astrosoft.consts.TableStyle;
import app.astrosoft.core.Muhurtha;
import app.astrosoft.ui.AstrosoftActionHandler;
import app.astrosoft.ui.table.AstrosoftTable;
import app.astrosoft.ui.table.AstrosoftTableModel;
import app.astrosoft.ui.table.Cell;
import app.astrosoft.ui.table.SortableTable;
import app.astrosoft.ui.table.SortableTableModel;
import app.astrosoft.ui.window.DefaultWindowLabelModel;
import app.astrosoft.ui.window.TablePopupWindowModel;
import app.astrosoft.ui.window.WindowLabelModel;


public class MuhurthaPanel extends JPanel {
    
    private static final int VISIBLE_TABLE_ROWS = 15;
    private Muhurtha muhurtha;
    private Point loc = new Point(20,30);
    private Dimension tableSize = new Dimension(700, 350);
    private AstrosoftActionHandler handler;
    SortableTableModel model;
    SortableTable mTable;
    JButton okButton;
    JButton panButton;
    
    public MuhurthaPanel(Muhurtha muhurtha, AstrosoftActionHandler handler){
        this.muhurtha = muhurtha;
        this.handler = handler;
        muhurtha.calcMuhurtha();
        model = new SortableTableModel(muhurtha.getNextTransitPeriods(),MuhurthaBean.getColumnMetaData() , AstrosoftTableColumn.Period);
        mTable = new SortableTable(model, TableStyle.SCROLL_SINGLE_ROW_SELECT);
       
        okButton = new JButton("More");
        okButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                moreClicked();
            }
        });
        panButton = new JButton("Panchang");
        panButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                panchangClicked();
            }
        });
        
        TablePopupWindowModel popupModel =  new TablePopupWindowModel(){
        
        	DefaultWindowLabelModel windowModel = new DefaultWindowLabelModel();
        	public WindowLabelModel getModel(Cell cell) {
        		
        		int colIndex;
        		
        		switch(cell.col){
        		
        			case Nakshathra:  
        				colIndex = model.getColumnIndex(AstrosoftTableColumn.Longitude);
        				break;
        			case Period:
        				colIndex = model.getColumnIndex(AstrosoftTableColumn.PeriodPopup);
        				break;
        			default:
        				colIndex = model.getColumnIndex(cell.col);
        				break;
        		}
        		windowModel.setText(model.getValueAt(cell.row,colIndex).toString());
        		
        		return windowModel;
        	}
        	
        };
        
        mTable.addColumnPopupWindow(AstrosoftTableColumn.Period, popupModel);
        mTable.addColumnPopupWindow(AstrosoftTableColumn.Nakshathra, popupModel);
        setLayout(new BorderLayout());
        //mTable.setShowHorizontalLines( true );
        showPanel();
    }
    
    public void setLoc(Point loc){
        this.loc = loc;
    }
    
    public void setTableSize(Dimension size){
        this.tableSize = size;
    }
    
    public void showPanel(){
        setBounds(loc.x,loc.y, tableSize.width + loc.x + 50, tableSize.height + loc.y + 40);
        showTable();
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(panButton);
        add(buttonPanel, BorderLayout.PAGE_END);
    }
    
    private void showTable() {
        mTable.setPreferredScrollableViewportSize(tableSize);
        JScrollPane scrollPane = new JScrollPane(mTable);
        
        JPanel p = new JPanel();
        
        p.add(mTable.getTableHeader());
        p.add(scrollPane);
        
        add(p, BorderLayout.CENTER);
        
        if (mTable.getModel().getRowCount() <= VISIBLE_TABLE_ROWS) {
            mTable.setBorder(new LineBorder(AstrosoftTable.tableBorderClr));
            scrollPane.setBorder(new LineBorder(this.getBackground()));
            mTable.getTableHeader().setBorder(new LineBorder(AstrosoftTable.tableBorderClr));
        }
        //scrollPane.setViewportBorder(new LineBorder(Color.WHITE));
    }
    
    private void moreClicked(){
        
        model.updateData(muhurtha.getNextTransitPeriods());
    }
    
    private void panchangClicked(){
        int selectedRow = mTable.getSelectedRow();
        if (selectedRow != -1) {
            AstrosoftTableModel model = (AstrosoftTableModel)mTable.getModel();
            Calendar cal = new GregorianCalendar();
			try {
				cal = Interval.parseDate((String)model.getValueAt(selectedRow,model.getColumnIndex(AstrosoftTableColumn.Period)), DisplayFormat.DATE);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            handler.showPanchang(cal);
        }
    }
}
