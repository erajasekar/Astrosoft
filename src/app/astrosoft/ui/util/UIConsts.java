/**
 * UIConsts.java
 * Created On 2006, Mar 15, 2006 3:14:06 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.Map;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import app.astrosoft.ui.AstroSoft;

public class UIConsts {

	public static final Color LIGHT_YELLOW = new Color(255, 255, 235);
	public static final Color YELLOWISH_WHITE = new Color(255, 255, 252);
	
	public static final Color GRAY_WHITE = new Color(238,238,238);
	public static final Color LIGHT_BLUE = new Color(0, 0 , 204);
	public static final Color DARK_BLUE = new Color(0, 0 , 140);
	public static final Color GREENISH_BLUE = new Color( 0, 70, 140 );
	public static final Color SKY_BLUE = new Color( 210, 228, 238 );
	
	public static final Color DARK_GREEN = new Color(0, 90, 0);
	public static final Color LEAF_GREEN = new Color(75, 148,75);
	public static final Color PEARL_GREEN = new Color(236, 233,216);
	public static Color DARK_PEARL_GREEN = new Color(218,216,200);
	
	public static final Color DARK_RED = new Color(128, 0, 0);
	public static final Color MEROON_RED = new Color( 128, 0, 80 );
	public static final Color ROSE = new Color(200, 0, 130);
	
	public static final Color TABLE_HEADER_BACKGROUND = GRAY_WHITE;
	public static final Color TABLE_HEADER_FOREGROUND = GREENISH_BLUE;
	
	public static final Color CAL_COMBO_SEL_CLR = Color.RED;
	
	public static final Color CAL_COMBO_BACKGROUND = LIGHT_YELLOW;
	
	public static final Color THEME_CLR = PEARL_GREEN;
	//public static final Color THEME_CLR = PEARL_GREEN;
	
	public static final Color OPTIONPANE_BACKGROUND = new Color(222,222,222);
	
	
	public static final Dimension BUTTON_ICON_SIZE = new Dimension(20,20);
	
	public static String getLookAndFeel(){
		return "com.digitprop.tonic.TonicLookAndFeel";
	}
	
	public static Map getUIDefaults() {
		Properties UIProps = new Properties();
		
		
		//UIProps.put("Label.font", new Font("Arial", Font.PLAIN,12 ));
		
		UIProps.put("Button.focus", Color.BLACK);
		UIProps.put("Button.background", THEME_CLR);
		UIProps.put("Button.rollover", true);
		//UIProps.put("Button.border", new LineBorder(THEME_CLR));

		UIProps.put("Panel.background", Color.WHITE);
		//UIProps.put("Panel.background", YELLOWISH_WHITE);
		UIProps.put("CheckBox.background", Color.WHITE);
		UIProps.put("Viewport.background", Color.WHITE);
		UIProps.put("ComboBox.background", Color.WHITE);
		
		//Tabbed Pane Settings
		
		UIProps.put("TabbedPane.background", Color.WHITE);
		UIProps.put("TabbedPane.selected", THEME_CLR);
		UIProps.put("TabbedPane.contentBorderInsets", new Insets(0,0,0,0));
		UIProps.put("TabbedPane.tabRunOverlay", 5);
		
		
		//Menu Bar Settings
		UIProps.put("MenuBar.background", THEME_CLR);
		UIProps.put("MenuItem.background", THEME_CLR);
		UIProps.put("Menu.background", THEME_CLR);
		UIProps.put("Menu.selectionBackground", LEAF_GREEN);
		UIProps.put("MenuItem.selectionBackground", LEAF_GREEN);
		UIProps.put("ToolBar.background", THEME_CLR);
		UIProps.put("RadioButtonMenuItem.background", THEME_CLR);
		UIProps.put("RadioButtonMenuItem.selectionBackground", LEAF_GREEN);
		UIProps.put("PopupMenu.border", new LineBorder(LEAF_GREEN));
		
		//UIProps.put("ScrollBar.background", MENU_CLR);
		
		
		//Table Settings
		//UIProps.put("Table.background", Color.WHITE);
		UIProps.put("Table.foreground", LIGHT_BLUE);
		UIProps.put("Table.selectionBackground", GRAY_WHITE);
		UIProps.put("Table.selectionForeground", GREENISH_BLUE);
		//UIProps.put("Table.gridColor", new Color( 0, 128, 192));
		UIProps.put("TableHeader.background", TABLE_HEADER_BACKGROUND);
		UIProps.put("TableHeader.foreground", TABLE_HEADER_FOREGROUND);
		
		//Tree Settings
		
		UIProps.put("Tree.selectionBackground", LEAF_GREEN);
		UIProps.put("Tree.selectionBorderColor", LEAF_GREEN);
		
		UIProps.put("SplitPane.background", Color.WHITE);
		
		UIProps.put("List.selectionBackground", LEAF_GREEN);
		UIProps.put("List.selectionBorderColor", LEAF_GREEN);
		
		//JOptionPane Properties
		//UIProps.put("OptionPane.background", Color.WHITE);
		//UIProps.put("OptionPane.foreground", Color.BLACK);
		
		
		//UIProps.put("window", GREENISH_BLUE);
		return UIProps;
	}

	public static Border getTitleBorder(String title){
		TitledBorder border = new TitledBorder(new EtchedBorder(), title);
		border.setTitleColor(UIConsts.DARK_GREEN);
		return border;
	}
	
	public static Border getTitleBorder(Enum title){
		return getTitleBorder(title.toString());
	}
	
	public static Color getChartBackground(){
		return YELLOWISH_WHITE;
	}
	
	public static Border getChartBorder(){
		//return BorderFactory.createEtchedBorder(EtchedBorder.RAISED, ROSE, Color.WHITE);
		return BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.ORANGE, Color.WHITE);
		//return BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, YELLOWISH_WHITE.brighter(), YELLOWISH_WHITE.darker());
		//new LineBorder(Color.GRAY, 1, true);
		//BorderFactory.createBevelBorder(BevelBorder.RAISED);
	}
}


