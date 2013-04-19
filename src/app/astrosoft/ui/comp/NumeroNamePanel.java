/**
 * NumeroNamePanel.java
 * Created On 2007, May 15, 2007 1:28:02 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.ui.util.SpringUtilities;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.util.AstroUtil;

public abstract class NumeroNamePanel extends JPanel {

	private static final Color numColor = Color.RED;
	
	protected static final Dimension textSize = new Dimension(50,17);
	protected static final Dimension numSize = new Dimension(20,17);
	
	protected JLabel l_blank = new JLabel(" ");
	protected JLabel l_name = new JLabel(DisplayStrings.NAME_STR.toString());// + "                        ");
	protected JLabel l_numeroVal = new JLabel(DisplayStrings.NUMERO_VALUE_STR.toString());
	protected JLabel l_numeroNum = new JLabel(DisplayStrings.NUMERO_NUMBER_STR.toString());
	
	
	protected JTextField name = new JTextField(); 
	protected JTextField numeroVal = new JTextField(); 
	protected JTextField numeroNum = new JTextField();
	
	protected JButton button;
	
	protected KeyListener keyListener = new KeyAdapter(){
		
		@Override
		public void keyReleased(KeyEvent e) {
			
			int val = AstroUtil.computeNumeroVal(name.getText());
			numeroVal.setText(val == 0 ? "" : String.valueOf(val));
			int num = AstroUtil.toNumeroNum(val);
			numeroNum.setText(val == 0 ? "" : String.valueOf(num));
		}
	};

	
	public NumeroNamePanel(final ActionListener l){
	
		setSizes();
		numeroNum.setForeground(numColor);
		numeroVal.setForeground(numColor);
		
		String title = getTitle();
		
		if(title != null){
			
			button = new JButton(title);
			
			if (l != null) {
				button.addActionListener(new ActionListener(){
		
					public void actionPerformed(ActionEvent e) {
						l.actionPerformed(e);
					}
					
				});
			}
		}
		
	}
	
	protected void setSizes(){
		
		l_name.setPreferredSize(textSize);
		name.setPreferredSize(textSize);
		l_numeroVal.setPreferredSize(numSize);
		l_numeroNum.setPreferredSize(numSize);
		numeroVal.setPreferredSize(numSize);
		numeroNum.setPreferredSize(numSize);
	}

	protected void addComponents() {

		setLayout(new SpringLayout());
		
		
		add(SpringUtilities.makeCompactGrid(2, 1, 0,0,4,4, l_name, name));
		
		addOperatorPanel1();
		
		add(SpringUtilities.makeCompactGrid(2, 1, 0,0,4,4, l_numeroVal, numeroVal));
	
		addOperatorPanel2();
		
		add(SpringUtilities.makeCompactGrid(2, 1, 0,0,4,4, l_numeroNum, numeroNum));
		
		add(SpringUtilities.makeCompactGrid(2, 1, 0,0,4,4, l_blank, button));
		
		SpringUtilities.makeCompactGrid(this, 1, getPanelCount(), 5,5,10,10);
		
		setBorder(BorderFactory.createEtchedBorder());
		//setBorder(UIConsts.getTitleBorder(""));
		
	}

	protected void addOperatorPanel1() {
	}
	
	protected void addOperatorPanel2() {
	}

	public int getPanelCount() {
		return 4;
	}
	protected abstract String getTitle();
	
	public String getName(){
		return name.getText();
	}
	
	public String getNumeroValue(){
		return numeroVal.getText();
	}
	
	public String getNumeroNumber(){
		return numeroNum.getText();
	}
	
	public void resetValues(){
		name.setText("");
		numeroVal.setText("");
		numeroNum.setText("");
	}
	
	public void setValues(String n, int v, int num){
		
		name.setText(n);
		numeroVal.setText(String.valueOf(v));
		numeroNum.setText(String.valueOf(num));
	}
}
