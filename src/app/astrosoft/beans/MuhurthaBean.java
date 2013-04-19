/**
 * MuhurthaBean.java
 * Created On 2005, Oct 15, 2005 7:24:41 PM
 * @author E. Rajasekar
 */
package app.astrosoft.beans;

import java.util.Comparator;

import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.DisplayFormat;
import app.astrosoft.consts.MuhurthaRank;
import app.astrosoft.consts.Nakshathra;
import app.astrosoft.consts.Rasi;
import app.astrosoft.ui.table.ColumnMetaData;
import app.astrosoft.ui.table.DefaultColumnMetaData;
import app.astrosoft.ui.table.TableRowData;

/**
 * Class holds Muhurtha Transit Information
 */
public class MuhurthaBean implements TableRowData {

	private Interval period;

	private Interval longitude;

	private MuhurthaRank rank;

	private Nakshathra nak;

	private Rasi rasi;

	public MuhurthaBean(Interval period, Interval longitudes, MuhurthaRank rank) {

		this.period = period;
		this.longitude = longitudes;
		this.rank = rank;

		nak = Nakshathra.ofDeg(longitudes.getStart());

		rasi = Rasi.ofDeg(longitudes.getStart());
	}

	public Interval getPeriod() {
		return period;
	}

	public Interval getLongitude() {
		return longitude;
	}

	public MuhurthaRank getRank() {
		return rank;
	}

	public Nakshathra getNakshathra() {
		return nak;
	}

	public Rasi getRasi() {
		return rasi;
	}

	public String toString() {

		StringBuffer sb = new StringBuffer(period.toString(DisplayFormat.DATE_TIME) + " -> " + longitude.toString(DisplayFormat.DEG));
		sb.append(" -> ");
		sb.append("[ " + rasi + " , ");
		sb.append(nak + " ] ");
		sb.append(rank + "\n");
		return sb.toString();
	}

	public static Comparator<TableRowData> getComparator(
			final AstrosoftTableColumn col) {

		Comparator<TableRowData> cmp = new Comparator<TableRowData>() {

			Comparable c1 = null;

			Comparable c2 = null;

			public int compare(TableRowData o1, TableRowData o2) {

				switch (col) {

				case Period:

					c1 = (Comparable) ((MuhurthaBean) o1).period;
					c2 = (Comparable) ((MuhurthaBean) o2).period;

					break;

				case Rank:

					c1 = ((MuhurthaBean) o1).rank;
					c2 = ((MuhurthaBean) o2).rank;
					break;

				}
				return c1.compareTo(c2);
			}

		};

		return cmp;

	}

	public Object getColumnData(AstrosoftTableColumn col) {

		switch (col) {

		case Period:
			return getPeriod().toString(DisplayFormat.DATE);
		case Longitude:
			return getLongitude().toString(DisplayFormat.DEG);
		case Rank:
			return getRank();
		case Nakshathra:
			return getNakshathra();
		case Rasi:
			return getRasi();
		case PeriodPopup:
			return getPeriod().toString(DisplayFormat.DATE_TIME);
		}
		return null;
	}

	public static ColumnMetaData getColumnMetaData() {
		return columnMetaData;
	}

	private static ColumnMetaData columnMetaData = new DefaultColumnMetaData() {

		{
			// All hidden cols should be last.
			super.setColumns(AstrosoftTableColumn.Period,
					AstrosoftTableColumn.Nakshathra, AstrosoftTableColumn.Rasi,
					AstrosoftTableColumn.Rank, AstrosoftTableColumn.Longitude,
					AstrosoftTableColumn.PeriodPopup);
			super.setSortableColumns(AstrosoftTableColumn.Period,
					AstrosoftTableColumn.Rank);
			super.setHiddenColumnCount(2);
			super.localizeColumns(AstrosoftTableColumn.Period, AstrosoftTableColumn.Nakshathra, AstrosoftTableColumn.Rasi, AstrosoftTableColumn.Rank);
		}

		public Comparator<TableRowData> getColumnComparator(
				AstrosoftTableColumn col) {
			return getComparator(col);
		}
	};
}
