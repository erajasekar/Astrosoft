/**
 * VimDasaView.java
 * Created On 2006, Mar 24, 2006 7:31:43 PM
 * @author E. Rajasekar
 */
package app.astrosoft.ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.TableStyle;
import app.astrosoft.core.Dasa;
import app.astrosoft.core.Vimshottari;
import app.astrosoft.ui.comp.TitleLabel;
import app.astrosoft.ui.comp.TitledTable;
import app.astrosoft.ui.table.AstrosoftTable;
import app.astrosoft.ui.table.AstrosoftTableModel;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.tree.DasaTreeCellRenderer;
import app.astrosoft.ui.tree.DasaTreeListener;
import app.astrosoft.ui.tree.DasaTreeSelectionHandler;
import app.astrosoft.ui.util.UIUtil;

public class VimDasaView extends AstrosoftView implements DasaTreeSelectionHandler{

	private Vimshottari v;
	private AstrosoftTableModel dasaTableModel;
	private AstrosoftTable dasaTable;
	private TitleLabel dasaTitle;
	
	private static final Dimension viewSize = new Dimension(580, 400);
	
	private static final Dimension treeSize = new Dimension((int)(viewSize.width * 0.30), viewSize.height);
	
	private static final Dimension tableSize = new Dimension((int)(viewSize.width * 0.60), (int) (viewSize.height * 0.56));
	
	
	public VimDasaView(String title, Vimshottari v) {
		
		super(title, viewSize);
		this.v = v;
		Font treeFont = UIUtil.getFont("Tahoma", Font.PLAIN, 11);
		Font tableFont = UIUtil.getFont(Font.BOLD, 12);
		
		JTree dasaTree = new JTree(v.getDasaTreeModel());
		dasaTree.setFont(treeFont);
		
		dasaTree.getSelectionModel().setSelectionMode
         (TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		dasaTree.setCellRenderer(new DasaTreeCellRenderer());
		ToolTipManager.sharedInstance().registerComponent(dasaTree);
		
		JScrollPane treePane = new JScrollPane(dasaTree);
		treePane.setPreferredSize(treeSize);
		
		dasaTableModel = new AstrosoftTableModel(
				v.getVimDasaTableData(), Vimshottari.getVimDasaTableColumnMetaData());
		dasaTable = new AstrosoftTable(dasaTableModel, TableStyle.SCROLL_SINGLE_ROW_SELECT);
		
		dasaTable.getTableHeader().setFont(tableFont);
		dasaTable.getSelectionModel().setLeadSelectionIndex(-1);

		dasaTitle = new TitleLabel(DisplayStrings.VIM_DASA_STR);

		JPanel dasaPanel = new JPanel();
		dasaPanel.add(new TitledTable(dasaTitle, dasaTable, tableSize));
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePane, dasaPanel);
		
		//splitPane.setBackground(Color.WHITE);
		treePane.setBorder(BorderFactory.createEtchedBorder());
		dasaPanel.setBorder(BorderFactory.createEmptyBorder());
		splitPane.setBorder(BorderFactory.createEtchedBorder());
		
		add(splitPane,BorderLayout.CENTER);
		
		this.setVisible(true);
		
		//Make Current Dasa as Selected
		TreePath currentDasaPath = v.getCurrentDasaPath();
		dasaTree.setSelectionPath(currentDasaPath);
		
		DefaultMutableTreeNode currentDasaNode = (DefaultMutableTreeNode) dasaTree.getLastSelectedPathComponent();
		
		nodeSelected(currentDasaNode, currentDasaPath);
		
		dasaTree.addTreeSelectionListener(new DasaTreeListener(this));
	}
	
	
	public void nodeSelected(DefaultMutableTreeNode node, TreePath path) {
		
		Dasa dasa = (Dasa) node.getUserObject();
		
		boolean isRoot = node.isRoot();
		
		if (node.isLeaf()){
			updateDasaTable(dasa.getParent(), isRoot);
		}else{
			updateDasaTable(dasa, isRoot);
		}
		
		if (!isRoot && node.isLeaf()){
			setRowSelected(node.getParent(), node);
		}
		
		String title;
		
		if (isRoot){
			title = DisplayStrings.VIM_DASA_STR.toString();
		}else{
			title = path.getPathComponent(1).toString() + " " + DisplayStrings.DASA_STR.toString();
		}
		
		dasaTitle.setText(title);
	}
	
	private void setRowSelected(TreeNode parent, TreeNode node){
		int index = parent.getIndex(node);
		dasaTable.getSelectionModel().setLeadSelectionIndex(index);
	}
	
	private void updateDasaTable(Dasa dasa, boolean isRoot){
	
		TableData<Dasa> data = null;
		
		//if Root node is selected, display root dasa.
		if(isRoot || dasa == null){
			data = v.getVimDasaTableData();
		}else{
			data = v.getVimDasaTableData(dasa);
		}
		
		if (data.getRowCount() > 0){
			dasaTableModel.updateData(data);
		}
	}
}
