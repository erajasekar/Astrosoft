/**
 * DasaTreeSelectionHandler.java
 * Created On 2006, Mar 25, 2006 7:01:35 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public interface DasaTreeSelectionHandler {

	public void nodeSelected(DefaultMutableTreeNode node, TreePath path);
}
