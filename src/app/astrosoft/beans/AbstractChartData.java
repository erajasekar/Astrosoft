/**
 * AbstractChartData.java
 * Created On 2006, Mar 31, 2006 5:00:38 PM
 * @author E. Rajasekar
 */

package app.astrosoft.beans;


import java.util.EnumSet;
import java.util.Set;

import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.Rasi;
import app.astrosoft.ui.table.DefaultColumnMetaData;
import app.astrosoft.util.Utils;

public abstract class AbstractChartData implements ChartData {

	protected String chartName;

	protected DefaultColumnMetaData colMetaData;

	protected Rasi ascendant;

	public AbstractChartData(String chartName) {
		this();
		this.chartName = chartName;
	}

	public AbstractChartData() {
		colMetaData = getHouseTableColMetaData();
		colMetaData.localizeColumns();
	}

	public String getChartName() {
		return chartName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		//sb.append(planetsInRasi);

		sb.append("\n");

		for(Rasi r : Rasi.values()){
			sb.append(r + " : \n");
			sb.append(Utils.printTableData(getChartHouseTable(r)));
			sb.append("\n");
		}

		return sb.toString();
	}

	public Set<Rasi> getHouses() {

		return EnumSet.allOf(Rasi.class);
	}

	public Rasi getAscendant() {
		return ascendant;
	}

	public abstract DefaultColumnMetaData getHouseTableColMetaData();
}
