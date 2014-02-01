/**
 * SphutaLabelModel.java
 * Created On 2005, Nov 12, 2005 2:53:01 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.window;

import app.astrosoft.util.AstroUtil;
import static app.astrosoft.util.AstroUtil.dms;

public class SphutaLabelModel extends DefaultWindowLabelModel {

	public SphutaLabelModel(){
		super();
	}
	
	public SphutaLabelModel(double deg){
		super(dms(deg));
	}
	
	public void setSphuta(double deg){
		super.setText(dms(deg));
	}
}
