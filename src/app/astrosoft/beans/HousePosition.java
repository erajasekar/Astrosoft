/**
 * HousePosition.java
 * Created On 2006, Feb 24, 2006 1:35:42 PM
 * @author E. Rajasekar
 */

package app.astrosoft.beans;

import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.Roman;
import app.astrosoft.export.Exportable;
import app.astrosoft.export.Exporter;
import app.astrosoft.ui.table.ColumnMetaData;
import app.astrosoft.ui.table.DefaultColumnMetaData;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableDataFactory;
import app.astrosoft.ui.table.TableRowData;
import app.astrosoft.util.AstroUtil;
import app.astrosoft.util.Mod;

public class HousePosition implements Exportable {

	public class Bhava implements TableRowData {

		Rasi house;
		
		int bhava;

		double start;

		double mid;

		double end;

		double length;

		public Bhava(int bhava, double start, double mid, double end) {
			this.bhava = bhava;
			this.start = start;
			this.mid = mid;
			this.end = end;
			this.length = mod.sub(end, start);
			house = Rasi.ofDeg(mid);
		}

		public int bhava() {
			return bhava;
		}
		
		public Rasi house(){
			return house;
		}

		public double start() {
			return start;
		}

		public double mid() {
			return mid;
		}

		public double end() {
			return end;
		}

		public double length() {
			return length;
		}

		public Object getColumnData(AstrosoftTableColumn col) {

			switch (col) {
				case House :
					return Roman.of(bhava());
				case Bhava:
					return house;
				case Start :
					return start();
				case Mid :
					return mid();
				case End :
					return end();
				case Length :
					return length();
				default :
					return null;
			}
		}

		@Override
		public String toString() {

			StringBuilder sb = new StringBuilder();

			sb.append(bhava + "\t");
			sb.append(AstroUtil.dms(start) + "\t");
			sb.append(AstroUtil.dms(mid) + "\t");
			sb.append(AstroUtil.dms(end) + "\t");
			sb.append(AstroUtil.dms(length) + "\n");
			return sb.toString();
		}
	}

	private Mod mod = new Mod(360);

	private Bhava[] housePositions = new Bhava[12];

	private double ascendant;

	private double siderealTime;

	private TableData<Bhava> bhavaTableData;

	private DefaultColumnMetaData bhavaTableColumnData;

	public HousePosition(double positions[]) {

		double start = mod.add(positions[12], (mod.sub(positions[1],
				positions[12]) / 2));
		double end = 0.0;

		ascendant = positions[1];

		// postion starts from 1
		for (int i = 1; i < positions.length - 1; i++) {
			end = mod.add(positions[i], (mod
					.sub(positions[i + 1], positions[i]) / 2));
			housePositions[i - 1] = new Bhava(i, start, positions[i], end);
			start = end;
		}
		end = mod
				.add(positions[12], (mod.sub(positions[1], positions[12]) / 2));
		housePositions[11] = new Bhava(12, start, positions[12], end);

		// System.out.println(verifyLength());
	}

	public Rasi locateHouse(double deg) {

		int house = -1;
		// FIXME: See if we can get it from somewhere
		Rasi lagna = Rasi.ofDeg(ascendant);
		double start = housePositions[0].start();
		double end = 0;
		// System.out.println("deg: " + deg + " start: " + start);
		if (deg < start) {
			deg = deg + 360;
		}

		for (Bhava b : housePositions) {
			// System.out.println(b);
			end = start + b.length();
			// System.out.println("< " + start + " , " + end + " >");
			if (deg <= end && deg >= start) {
				house = b.bhava() - 1;
				break;
			} else {
				start = end;
			}
		}
		// System.out.println("lagna: " + lagna + " house: " + house);
		return lagna.absolute(house);
	}

	public Bhava[] getHousePositions() {
		return housePositions;
	}

	/** Note starts with 1 not 0 **/
	public Bhava getBhava(int i){
		return housePositions[i - 1];
	}
	
	public double getAscendantPosition() {
		return ascendant;
	}
	
	public Rasi getAscendant(){
		return Rasi.ofDeg(ascendant);
	}

	/**
	 * @param siderealTime
	 *            The siderealTime to set.
	 */
	public void setSiderealTime(double siderealTime) {
		this.siderealTime = siderealTime;
	}

	/**
	 * @return Returns the siderealTime.
	 */
	public double getSiderealTime() {
		return siderealTime;
	}

	public TableData<Bhava> getBhavaTableData() {

		if (bhavaTableData == null) {
			bhavaTableData = TableDataFactory.getTableData(housePositions);
		}
		return bhavaTableData;
	}

	public ColumnMetaData getBhavaTableColumnData() {

		if (bhavaTableColumnData == null) {
			bhavaTableColumnData = new DefaultColumnMetaData(
					AstrosoftTableColumn.House, AstrosoftTableColumn.Bhava,
					AstrosoftTableColumn.Start,AstrosoftTableColumn.Mid,
					AstrosoftTableColumn.End,AstrosoftTableColumn.Length) {

				@Override
				public Class getColumnClass(AstrosoftTableColumn col) {

					switch (col) {
						case Start :
						case Mid :
						case End :
						case Length :
							return Degree.class;
						case House:
							return Roman.class;
					}
					return super.getColumnClass(col);
				}
			};
			bhavaTableColumnData.localizeColumns();

		}

		return bhavaTableColumnData;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("Ascendant: " + AstroUtil.dms(ascendant) + "\n");
		sb.append("Sidereal Time: " + AstroUtil.dms(siderealTime) + "\n");

		for (Bhava b : housePositions) {
			sb.append(b + "\n");
		}

		return sb.toString();
	}

	private boolean verifyLength() {

		double length = 0.0;
		for (Bhava b : housePositions) {
			length = length + b.length();
		}

		return (length == 360.00);
	}

	public void doExport(Exporter e) {
		e.export(this);
	}
}
