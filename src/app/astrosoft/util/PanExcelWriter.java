package app.astrosoft.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import app.astrosoft.beans.Place;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.XmlConsts;
import app.astrosoft.core.Panchang;
import app.astrosoft.core.PanchangList;
import app.astrosoft.pref.AstrosoftPref;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.table.MapTableRow;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableRowData;

import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;



public class PanExcelWriter {

	private static String PARENT_DIR = "c:/astrosoft/docs";
	
	private static DateFormat monFormat = new SimpleDateFormat("MMM_yyyy");
	
	private static DateFormat dateFormat = new SimpleDateFormat("dd EEE");
	
	//private WritableSheet sheet;
	
	public static void main(String[] args) throws Exception {

		AstrosoftPref pref = AstroSoft.getPreferences();
		pref.setPlace(Place.getDefault());
		
		new PanExcelWriter(2009);
	
	}
	
	public PanExcelWriter(int year) throws Exception {
		
		WritableWorkbook workbook = null;

		
		PanchangList pl = new PanchangList(year);
		
		WritableSheet sheet= null;
		
		int sheetNo = 0;
		
		for(Panchang p:pl){
			
			Calendar cal = p.getDate();
			
			if (cal.get(Calendar.DATE) == 1) {
				
				workbook = Workbook.createWorkbook(new File(PARENT_DIR, "pan_" + monFormat.format(cal.getTime()) + ".xls" )); 
				sheetNo = 0;
			}
			
			sheet = workbook.createSheet(dateFormat.format(cal.getTime()), sheetNo++);
			
			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 30);
			
			TableData<MapTableRow> data = p.getPanchangTableData();
			
			
			for(int i = 0; i < data.getRowCount(); i++){

				TableRowData row = data.getRow(i);
				
				String key = row.getColumnData(AstrosoftTableColumn.Key).toString();
				if (!key.equals("Auspicious Time") && !key.equals("Rahu Kala") && !key.equals("Yama Kanda") ){
			
					if (key.indexOf("End") >= 0){
						key = key.substring(0, key.length()-9);
					}
					writeRow(sheet, i, key, row.getColumnData(AstrosoftTableColumn.Value).toString());
				}
			}
		
			
			if (cal.get(Calendar.DATE) == cal.getActualMaximum(Calendar.DATE)){
				workbook.write(); 
				workbook.close(); 
			}
		}
		
		
	}
	
	private static void writeRow(WritableSheet sheet, int row, String param, String value) throws RowsExceededException, WriteException{
		Label label = new Label(0, row, param); 
		sheet.addCell(label);
		
		WritableCellFormat cf = new WritableCellFormat(new WritableFont(WritableFont.createFont("Tahoma"),8));
	
//		cf.setShrinkToFit(true);
		
		label.setCellFormat(cf);
		
		label = new Label(1, row, value); 
		label.setCellFormat(cf);
		sheet.addCell(label); 
	}
}
