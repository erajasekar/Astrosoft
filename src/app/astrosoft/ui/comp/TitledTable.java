/**
 * TitledTable.java
 * Created On 2006, Feb 25, 2006 3:25:48 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import app.astrosoft.ui.table.AstrosoftTable;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.ui.util.UIUtil;

public class TitledTable extends JPanel {
	
	private static final Color bgClr = Color.WHITE;
	private static final int titleHeight = 40;
	private JScrollPane scrollPane;
	private AstrosoftTable table;
	private Border scrollPaneBorder;
	
	public TitledTable() {
		setLayout(new BorderLayout());
	}
	
	public TitledTable(JLabel title, AstrosoftTable table, Dimension tableSize, JPanel footer){
		
		this();
		this.table = table;
		JPanel labelPanel = new JPanel();
		//JPanel tablePanel = new JPanel();
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(tableSize);
		
		/*tablePanel.setLayout(new BorderLayout());
		
		tablePanel.setPreferredSize(tableSize);
		tablePanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		tablePanel.add(table, BorderLayout.PAGE_END);*/
		
		if (title != null){
			labelPanel.add(title);
			labelPanel.setPreferredSize(new Dimension(tableSize.width, titleHeight));
			add(labelPanel, BorderLayout.PAGE_START);	
		}
		
		add(scrollPane, BorderLayout.CENTER);
		
		if (footer != null){
			
			
			footer.setBackground(UIConsts.THEME_CLR);
			footer.setBorder(BorderFactory.createMatteBorder(
	                0, 1, 1, 1, Color.GRAY));
			add(footer, BorderLayout.PAGE_END);
		}
		scrollPaneBorder = scrollPane.getBorder();
		//setBorder(UIConsts.getTitleBorder("abc"));
		
	}
	
	public TitledTable(AstrosoftTable table, Dimension tableSize){
		this(null, table, tableSize, null);
	}
	
	public TitledTable(JLabel title , AstrosoftTable table, Dimension tableSize) {
		this(title,table,tableSize,null);
	}

	public void setScrollPaneSize(Dimension size){
		scrollPane.setPreferredSize(size);
	}
	
	public void removeScrollPaneBorder(){
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		table.addBorder();
	}
	
	public void addScrollPaneBorder(){
		scrollPane.setBorder(scrollPaneBorder);
	}
}
