/**
 * PopupListener.java
 * Created On 2007, May 22, 2007 2:22:26 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

public class PopupListener extends MouseAdapter {
    JPopupMenu popup;

    public PopupListener(JPopupMenu popupMenu) {
        popup = popupMenu;
    }

    public void mousePressed(MouseEvent e) {
        showPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
    	 showPopup(e);
    }

    private void showPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popup.show(e.getComponent(),
                       e.getX(), e.getY());
        }
    }
}

