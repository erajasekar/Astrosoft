/**
 * DasaTreeCellRenderer.java
 * Created On 2006, Mar 25, 2006 8:13:00 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.tree;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import app.astrosoft.core.Dasa;

public class DasaTreeCellRenderer implements TreeCellRenderer {
  
DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();

public Component getTreeCellRendererComponent(JTree tree, Object value,
      boolean selected, boolean expanded, boolean leaf, int row,
      boolean hasFocus) {
	  
    renderer.getTreeCellRendererComponent(tree, value, selected, expanded,
        leaf, row, hasFocus);
    
    if (value != null) {
      DefaultMutableTreeNode n = (DefaultMutableTreeNode) value;
      
      Dasa d = (Dasa)(n.getUserObject());
      
      if (!n.isRoot()){
    	  renderer.setToolTipText(d.getStartDate());
      }else{
    	  renderer.setToolTipText("");
      }
      
      if (d.isRunning() && !selected){
    	  
   		  renderer.setForeground(Color.RED);  
   	  }
    }
    return renderer;
  }
} 