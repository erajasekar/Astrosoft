/**
 * AstrosoftTabbedPane.java
 * Created On 2006, Mar 8, 2006 6:50:43 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class AstrosoftTabbedPane extends JTabbedPane {
	
	public AstrosoftTabbedPane(Dimension paneSize){
		super();
		setPreferredSize(paneSize);
	}
	
	@Override
	public void addTab(String title, Component component) {
		
		JPanel panel = new JPanel();
		panel.add(component);

		/*JPanel panel = new JPanel(new BorderLayout());
		panel.add(component, BorderLayout.CENTER);*/

		panel.setBorder(BorderFactory.createEtchedBorder());
		super.addTab(title, panel);
	}
	
	public void addTab(Enum title, Component component){
		this.add(title.toString(), component);
	}

}
