/**
 * PagingPanel.java
 * Created On 2007, May 24, 2007 5:23:14 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.ui.table.AstrosoftTableModel;
import app.astrosoft.ui.table.TableRowData;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.ui.util.UIUtil;

public class PagingPanel<E extends TableRowData> extends JPanel {

	private static Dimension btnSize = new Dimension(20,20);
	private static Dimension comboSize = new Dimension(50,20);
	
	private AstrosoftTableModel tableModel;
	
	private Pagination<E> pagination;
	
	private JButton prevBtn = new JButton();
	private JButton nextBtn = new JButton();
	
	private int [] pages = new int[]{5,10,15,20,25};
	
	private JComboBox pageCombo = new JComboBox();
	private JComboBox showCombo = new JComboBox();
	
	private JLabel pageLabel = new JLabel(DisplayStrings.PAGE_STR.toString());
	private JLabel showLabel = new JLabel(DisplayStrings.SHOW_STR.toString());
	
	public PagingPanel(AstrosoftTableModel tableModel, Pagination<E> pagination ){
		this.tableModel = tableModel;
		this.pagination = pagination;
		addComponents();
	}

	private void addComponents() {
		
		setBackground(UIConsts.THEME_CLR);
		
		for(int page:pages){
			showCombo.addItem(page);
		}
		
		prevBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				previousButtonClicked();
				
			}
			
		});
		
		nextBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				nextButtonClicked();
				
			}
			
		});
		
		pageCombo.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
				pageChanged((Integer)e.getItem());
				
			}
			
		});
		
		showCombo.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
				pageLengthChanged((Integer)e.getItem());
				
			}
			
		});
		
		showCombo.setSelectedItem(pagination.getPageLength());
		
		prevBtn.setIcon(UIUtil.createImageIcon("prev"));
		nextBtn.setIcon(UIUtil.createImageIcon("next"));
		
		nextBtn.setBorder(BorderFactory.createEtchedBorder());
		prevBtn.setBorder(BorderFactory.createEtchedBorder());
		
		nextBtn.setPreferredSize(btnSize);
		prevBtn.setPreferredSize(btnSize);
		pageCombo.setPreferredSize(comboSize);
		//showCombo.setPreferredSize(comboSize);
		
		add(showLabel);
		add(showCombo);
		add(prevBtn);
		add(pageLabel);
		add(pageCombo);
		add(nextBtn);
		nextButtonClicked();
		prevBtn.setEnabled(false);
	}

	protected void pageLengthChanged(Integer pageLength) {
		pagination.setPageLength(pageLength);
		nextButtonClicked();
		prevBtn.setEnabled(false);
		
	}

	protected void pageChanged(int pageNo) {
		tableModel.updateData(pagination.getPage(pageNo));
		adjustControls();
	}

	private void nextButtonClicked() {
		tableModel.updateData(pagination.getNextPage());
		
		if (pageCombo.getItemCount() < pagination.getTotalPages()){
			pageCombo.addItem(pagination.getTotalPages());
		}
		
		pageCombo.setSelectedItem(pagination.getCurrentPage());
		adjustControls();
	}

	private void previousButtonClicked() {
		
		tableModel.updateData(pagination.getPreviousPage());
		pageCombo.setSelectedItem(pagination.getCurrentPage());
		adjustControls();
	}
	
	public void setPagination(Pagination<E> pagination) {
		this.pagination = pagination;
		nextButtonClicked();
	}
	
	private void adjustControls(){
		if (pagination.isFirstPage()){
			prevBtn.setEnabled(false);
		}
		else {
			prevBtn.setEnabled(true);
		}
		if (pagination.isLastPage()){
			nextBtn.setEnabled(false);
		}
		else {
			nextBtn.setEnabled(true);
		}
	}
	
}
