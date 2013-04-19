/**
 * Exporter.java
 * Created On 2007, Jun 12, 2007 1:32:27 PM
 * @author E. Rajasekar
 */

package app.astrosoft.export;

import app.astrosoft.beans.AshtaVargaChartData;
import app.astrosoft.beans.HousePosition;
import app.astrosoft.beans.PlanetChartData;
import app.astrosoft.beans.PlanetaryInfo;
import app.astrosoft.core.Ashtavarga;
import app.astrosoft.core.Compactibility;
import app.astrosoft.core.Horoscope;
import app.astrosoft.core.PanchangList;
import app.astrosoft.core.ShadBala;
import app.astrosoft.core.Vimshottari;

/**
 * This Exporter interface is in role of Visitor interface of visitor pattern.
 * @author Raja
 *
 */
public interface Exporter {

	public void export(PlanetChartData planetChart);

	public void export(Ashtavarga ashtavarga);

	public void export(AshtaVargaChartData ashtavargaChart);

	public void export(ShadBala shadBala);

	public void export(PlanetaryInfo planetaryInfo);

	public void export(HousePosition housePosition);

	public void export(Vimshottari vimshottari);

	public void export(Horoscope horoscope);

	public void export(Compactibility compactibility);
	
	public void export(PanchangList panchangList);

	public void export2Xml(Exportable exportable);
}
