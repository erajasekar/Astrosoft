/**
 * TablePanel.java
 * Created On 2006, Mar 2, 2006 3:55:32 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

import app.astrosoft.ui.comp.TitleLabel;

public abstract class AstrosoftView extends JPanel {
	
	protected Dimension viewSize;
	private int headerHeight = 50;
	private static Point defaultLoc =  new Point(100, 20);
	private static Font titleFont =  new Font("Verdana", Font.BOLD, 16);
	
	public AstrosoftView(String title, Dimension viewSize) {
		this(title, viewSize, defaultLoc);
	}
	
	public AstrosoftView(String title, Dimension viewSize, Point loc){
		this(viewSize, loc);
		JPanel header = new JPanel();
		JLabel label = new TitleLabel(title);
		label.setFont(titleFont);
		header.add(label);
		header.setPreferredSize(new Dimension(viewSize.width, headerHeight));
		add(header, BorderLayout.PAGE_START);
	}
	
	public AstrosoftView(Dimension viewSize, Point loc) {
		this.viewSize = viewSize;
		Dimension panelSize = new Dimension(viewSize.width, viewSize.height + 40 );
		setLayout(new BorderLayout());
		setLocation(loc);
		setSize(panelSize);
		setPreferredSize(panelSize);
	}
	
	/*public AstrosoftView(Dimension viewSize, Point loc) {
		this(viewSize, loc);
		JPanel header = new JPanel();
		header.add(table);
		header.setPreferredSize(new Dimension(viewSize.width, headerHeight));
		add(header, BorderLayout.PAGE_START);
	}*/
}
