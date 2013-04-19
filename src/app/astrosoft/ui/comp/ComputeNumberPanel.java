/**
 * ComputeNumberPanel.java
 * Created On 2007, Sep 17, 2007 1:16:48 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.SpringLayout;

import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.ui.util.SpringUtilities;

public class ComputeNumberPanel extends NumeroNamePanel{
	
	private static final Dimension labelSize = new Dimension(110,15);

	public ComputeNumberPanel(Dimension size){
		super(null);
		addComponents();
		setPreferredSize(size);
		name.addKeyListener(keyListener);
	}

	@Override
	protected String getTitle() {
		return null;
	}
	
	@Override
	protected void addComponents() {
	
		l_name.setText(DisplayStrings.NAME_STR.toString() + " / " + DisplayStrings.DOB_STR.toString());
		
		setLayout(new SpringLayout());
		
		add(SpringUtilities.makeCompactGrid(1, 2, 5,5,5,5, l_name, name));
		
		add(SpringUtilities.makeCompactGrid(1, 2, 5,5,5,5, l_numeroVal, numeroVal));
		
		add(SpringUtilities.makeCompactGrid(1, 2, 5,5,5,5, l_numeroNum, numeroNum));
		
		SpringUtilities.makeCompactGrid(this, getPanelCount(), 1, 5,5,10,10);
		
		//setBorder(BorderFactory.createEtchedBorder());
		
	}
	
	protected void setSizes(){
		
		l_name.setPreferredSize(labelSize);
		
		l_numeroVal.setPreferredSize(labelSize);
		l_numeroNum.setPreferredSize(labelSize);
		
		//name.setPreferredSize(textSize);
		//numeroVal.setPreferredSize(numSize);
		//numeroNum.setPreferredSize(numSize);
	}
	
	public int getPanelCount() {
		return 3;
	}
}
