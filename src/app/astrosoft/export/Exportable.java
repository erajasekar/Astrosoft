/**
 * Exportable.java
 * Created On 2007, Jun 12, 2007 2:31:11 PM
 * @author E. Rajasekar
 */

package app.astrosoft.export;

/**
 * This Exportable interface is in role of Vistable interface in visitor pattern.
 * Each class that needs to be exported implements this interface.
 *  
 * @author Raja
 *
 */
public interface Exportable {

	public void doExport(Exporter e);
}
