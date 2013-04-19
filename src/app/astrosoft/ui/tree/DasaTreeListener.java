/**
 * DasaTreeListener.java
 * Created On 2006, Mar 25, 2006 4:30:43 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.tree;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import app.astrosoft.core.Dasa;
import app.astrosoft.core.Vimshottari;
import app.astrosoft.core.VimDasa;
import app.astrosoft.ui.table.AstrosoftTableModel;
import app.astrosoft.ui.table.TableData;
import app.astrosoft.ui.table.TableDataFactory;

public class DasaTreeListener implements TreeSelectionListener {

	private DasaTreeSelectionHandler handler;
	
	public DasaTreeListener(DasaTreeSelectionHandler handler) {
		this.handler= handler;
	}
	
	public void valueChanged(TreeSelectionEvent e) {
		JTree tree = (JTree) e.getSource();

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		
		if (node != null){
		
			boolean isRoot = node.isRoot();
			Dasa dasa = (Dasa) node.getUserObject();
			
			//Add sub dasas only if not present already.
			if (!isRoot && node.isLeaf()){
				
				
				for(Dasa d : dasa.subDasas()){
					node.add(new DefaultMutableTreeNode(d, true));
				}
				DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
				model.reload(node);
			}
			handler.nodeSelected(node, e.getNewLeadSelectionPath());
				
		}
		//tree.collapsePath(e.getOldLeadSelectionPath());
	}
	
}
