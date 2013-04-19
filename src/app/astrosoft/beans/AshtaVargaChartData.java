/**
 * AshtavargaChartData.java
 * Created On 2006, Mar 31, 2006 5:12:23 PM
 * @author E. Rajasekar
 */

package app.astrosoft.beans;

import java.util.EnumMap;

import app.astrosoft.consts.AshtavargaName;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.Rasi;
import app.astrosoft.core.Ashtavarga;
import app.astrosoft.export.Exportable;
import app.astrosoft.export.Exporter;
import app.astrosoft.ui.table.ColumnMetaData;
import app.astrosoft.ui.table.DefaultColumnMetaData;
import app.astrosoft.ui.table.Table;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableRowData;

public class AshtaVargaChartData extends AbstractChartData implements Exportable{

	private EnumMap<Rasi, Integer> varga;
	
	public AshtaVargaChartData(AshtavargaName name, EnumMap<Rasi, Integer> varga) {
		super();
		
		this.varga = varga;
		chartName = name.toString();
		
		int count = Ashtavarga.getCount(name);
		if ( count != -1){
			chartName = chartName + " ( " +String.valueOf(count) + " ) ";
		}
	}

	
	public Table getChartHouseTable(final Rasi rasi) {
		
		Table ashtavargaTable = new Table(){

			public TableData<TableRowData> getTableData() {
				
				return new TableData<TableRowData>(){

					public TableRowData getRow(final int index){
						
						return new TableRowData(){

							public Object getColumnData(AstrosoftTableColumn col) {
								
								return (index == 1) ? varga.get(rasi) : null;
							}
							
						};
					}

					public int getRowCount() {
						
						return 2;
					}
					
				};
				
			}

			public ColumnMetaData getColumnMetaData() {
				
				return colMetaData;
			}
			
		};
		return ashtavargaTable;
	}


	@Override
	public DefaultColumnMetaData getHouseTableColMetaData() {
		return new DefaultColumnMetaData(AstrosoftTableColumn.C1){
			@Override
			public Class getColumnClass(AstrosoftTableColumn col) {
				
				return Integer.class;
			}
		};
	}
	
	public EnumMap<Rasi, Integer> getVarga() {
		return varga;
	}


	public void doExport(Exporter e) {
		e.export(this);
	}
	
	
}
