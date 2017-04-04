/*
 * UIUtil.java
 *
 * Created on November 5, 2005, 7:34 PM
 *
 * @author E. Rajasekar.
 */

package app.astrosoft.ui.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import app.astrosoft.consts.Language;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.table.AstrosoftTableModel;
import app.astrosoft.ui.table.Cell;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

public class UIUtil {

	// public static final String fontTag = " <font face='" +
	// getFont().getFamily() + "' color='#00468C'> ";
	public static final String closeFontTag = "</font> ";

	public static final String htmlTag = "<html> ";

	public static final String closeHtmlTag = "</html> ";

	public static final String brTag = "<br> ";

	public static final ClassLoader classLoader = UIUtil.class.getClassLoader();

	public static final String imagePath = "/resources/images/";

	/** Returns an ImageIcon, or null if the path was invalid. */
	public static ImageIcon createImageIcon(String imageName) {

		String image = imagePath + imageName.trim() + ".gif";

		URL imgURL = UIUtil.class.getResource(image);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		}
		return null;

	}

	/** Event source cell of JTable based on mouse position * */
	public static Cell getSourceTableCell(MouseEvent e) {

		JTable table = (JTable) e.getSource();
		AstrosoftTableModel model = (AstrosoftTableModel) table.getModel();

		int row = table.rowAtPoint(e.getPoint());
		int col = table.convertColumnIndexToModel(table.columnAtPoint(e
				.getPoint()));

		return new Cell(row, model.getColumn(col));
	}

	/** Returns font depending on prefered language option. * */
	public static Font getFont(int style, int size) {
		Language language = AstroSoft.getPreferences().getLanguage();
		Font font = new Font(language.font(), style, size);
		return font;
	}

	/*
	 * public static Font getFont(int size){ return getFont(Font.BOLD, size); }
	 */

	public static Font getFont() {
		return getFont(Font.BOLD, 12);
	}

	/**
	 * If prefered language is tamil returns tamil font, else returns font
	 * passed as argument
	 * 
	 * @param f
	 * @return
	 */
	/*
	 * public static Font getFont(String name){ return getFont(name, Font.BOLD,
	 * 12); }
	 */

	/**
	 * If prefered language is tamil returns tamil font, else returns font
	 * passed as argument
	 * 
	 * @param f
	 * @return
	 */
	public static Font getFont(String name, int style, int size) {
		Language language = AstroSoft.getPreferences().getLanguage();
		if (language == Language.TAMIL) {
			return new Font(language.font(), style, size);
		}
		return new Font(name, style, size);
	}

	/**
	 * If prefered language is tamil returns tamil font, else returns font
	 * passed as argument
	 * 
	 * @param f
	 * @return
	 */
	public static Font getFont(String name, int style, int en_size, int ta_size) {
		Language language = AstroSoft.getPreferences().getLanguage();
		if (language == Language.TAMIL) {
			return new Font(language.font(), style, ta_size);
		}
		return new Font(name, style, en_size);
	}

	public static Font getFont(int en_style, int en_size, int ta_style,
			int ta_size) {
		Language language = AstroSoft.getPreferences().getLanguage();
		if (language == Language.TAMIL) {
			return new Font(language.font(), ta_style, ta_size);
		}
		return new Font(language.font(), en_style, en_size);
	}

	/*
	 * public static Font getFont(Font font) {
	 * 
	 * return getFont(font.getName(), font.getStyle(), font.getSize()); }
	 */

	public static Font getFont(Language lang, int style, int size) {
		return new Font(lang.font(), style, size);
	}

	public static void setPanelBackground(JComponent panel, Color color) {
		if (panel!=null){
			panel.setBackground(color);
			for (Component c : panel.getComponents()) {
				if (c instanceof JPanel) {
					setPanelBackground((JPanel) c, color);
				}
			}
		}	
	}

	public static String getFontTag() {
		return " <font face='" + getFont().getFamily() + "' color='#00468C'> ";
	}

	public static void applyOptionPaneBackground(JOptionPane optionPane,
			Color color) {
		optionPane.setBackground(color);
		for (Iterator i = getComponents(optionPane).iterator(); i.hasNext();) {
			Component comp = (Component) i.next();
			//if (comp instanceof JPanel) {
				comp.setBackground(color);
			//}
		}
	}

	public final static Collection getComponents(Container container) {
		Collection components = new Vector();
		Component[] comp = container.getComponents();
		for (int i = 0, n = comp.length; i < n; i++) {
			components.add(comp[i]);
			if (comp[i] instanceof Container) {
				components.addAll(getComponents((Container) comp[i]));
			}
		}
		return components;
	}
	
	public static void setWindowLocation(Window window, Component parentComponent) {
		
		Point comboLocation =
			parentComponent.getLocationOnScreen(  );
        Dimension size = parentComponent.getSize(  );
        Dimension windowSize = window.getSize(  );
        Dimension screenSize =
            Toolkit.getDefaultToolkit(  ).getScreenSize(  );

        if ( 
            ( ( comboLocation.x
                - ( windowSize.width - size.width ) ) <= 0 )
                && ( ( comboLocation.y + size.height
                + windowSize.height ) >= screenSize.height ) ) {
            window.setLocation( 
                0, comboLocation.y - windowSize.height );
        } else if ( 
            ( comboLocation.x
                - ( windowSize.width - size.width ) ) <= 0 ) {
            window.setLocation( 
                0, comboLocation.y + size.height );
        } else if ( 
            ( comboLocation.y + size.height
                + windowSize.height ) >= screenSize.height ) {
            window.setLocation( 
                comboLocation.x
                - ( windowSize.width - size.width ),
                comboLocation.y - windowSize.height );
        } else {
            window.setLocation( 
                comboLocation.x
                - ( windowSize.width - size.width ),
                comboLocation.y + size.height );
        }
	}
}
