/**
 * NameSearchPanel.java
 * Created On 2007, May 11, 2007 2:32:05 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.astrosoft.consts.Operator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.ui.util.SpringUtilities;
import app.astrosoft.ui.util.UIConsts;

public class NameSearchPanel extends NumeroNamePanel {

	private static final Dimension comboSize = new Dimension(1,17);
	
	private JComboBox op1 = new JComboBox(Operator.logical().toArray());
	private JComboBox op2 = new JComboBox(Operator.logical().toArray());
	
	public NameSearchPanel(final ActionListener l){
		
		super(l);
		l_blank.setPreferredSize(comboSize);
		op1.setPreferredSize(comboSize);
		op2.setPreferredSize(comboSize);
		addComponents();
	}

	protected void addOperatorPanel1() {
		add(SpringUtilities.makeCompactGrid(2, 1, 0,0,4,4, l_blank, op1));
	}
	
	protected void addOperatorPanel2() {
		add(SpringUtilities.makeCompactGrid(2, 1, 0,0,4,4, l_blank, op2));
	}

	public int getPanelCount() {
		return 6;
	}

	
	public Operator getOperator1(){
		return (Operator) op1.getSelectedItem();
	}
	
	public Operator getOperator2(){
		return (Operator) op2.getSelectedItem();
	}

	@Override
	protected String getTitle() {
		return DisplayStrings.SEARCH_STR.toString();
	}
}
