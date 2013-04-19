/**
 * ViewContainer.java
 * Created On 2006, Mar 27, 2006 7:29:37 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.view;

import javax.swing.JPanel;

import app.astrosoft.ui.view.ViewManager.View;

public interface ViewContainer {

	public void addView(JPanel view);
	
	public JPanel createView(View view);
	
}
