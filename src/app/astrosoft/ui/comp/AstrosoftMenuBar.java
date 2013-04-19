/*
 * AstrosoftMenuBar.java
 *
 * Created on August 6, 2005, 7:51 PM
 *
 * @author E. Rajasekar.
 */

package app.astrosoft.ui.comp;

import java.awt.Color;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import app.astrosoft.consts.Command;
import app.astrosoft.consts.Ayanamsa;
import app.astrosoft.pref.AstrosoftPref;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.AstrosoftActionManager;

public class AstrosoftMenuBar extends JMenuBar
{
	
	List<JMenu> menus;
	JMenu[] viewSubMenus;
	JMenu[] optionSubMenus;
	JMenuItem[] fileMenuItems;
	JMenuItem[] editMenuItems;
	JMenuItem[] viewMenuItems;
	JMenuItem[] computeMenuItems;
	JMenuItem[] optionMenuItems;
	JRadioButtonMenuItem[] ayaMenuItems;
    JRadioButtonMenuItem[] langMenuItems;
 	
    AstrosoftActionManager actionMgr;
   
    public AstrosoftMenuBar(AstrosoftActionManager actionMgr) {
        
    	this.actionMgr = actionMgr;
    	
    	menus = new ArrayList<JMenu>();

    	// File Menu
    	JMenu menu = new JMenu( "File" );
    	
    	EnumSet<Command> fileItems = Command.fileMenuItems();
    	fileMenuItems = new JMenuItem[fileItems.size()];

    	int i = 0;
    	for(Command fileItem : fileItems){
    		fileMenuItems[i++] = new JMenuItem( actionMgr.getAction( fileItem ));
    	}
    	
    	menus.add(menu);
    	addMenuItems(menu, fileMenuItems);
    	
    	// Edit Menu
    	EnumSet<Command> editItems = Command.editMenuItems();
        menu = new JMenu( "Edit " );
        editMenuItems = new JMenuItem[editItems.size()];
    	
        i = 0;
    	for(Command editItem : editItems){
    		editMenuItems[i++] = new JMenuItem( actionMgr.getAction( editItem ));
    	}
    	
        addMenuItems(menu, editMenuItems);
        
        menus.add(menu);
        
        //AstrosoftView Menu
        menu = new JMenu( "View" );
        viewSubMenus = new JMenu[1];
        
        viewMenuItems = new JMenuItem[10];
        
        viewMenuItems[0] = new JMenuItem( actionMgr.getAction( Command.CHART_VIEW));
        addMenuItems(menu,viewMenuItems,0,0);
        
        viewMenuItems[1] = new JMenuItem( actionMgr.getAction( Command.DASAS_VIEW )) ;
        viewMenuItems[2] = new JMenuItem( actionMgr.getAction( Command.PLANET_POS_VIEW )) ;
        viewMenuItems[3] = new JMenuItem( actionMgr.getAction( Command.BHAVA_POS_VIEW)) ;
        viewMenuItems[4] = new JMenuItem( actionMgr.getAction( Command.DIV_CHART_VIEW ) ) ;
        viewMenuItems[5] = new JMenuItem( actionMgr.getAction( Command.ASHTAVARGA_VIEW )) ;
        viewMenuItems[6] = new JMenuItem( actionMgr.getAction( Command.SHADBALA_VIEW ) );
        viewMenuItems[7] = new JMenuItem( actionMgr.getAction( Command.YOGA_COMBINATIONS_VIEW ) );
        addMenuItems(menu,viewMenuItems,1,7 );
        menu.add(new JSeparator(SwingConstants.HORIZONTAL));
        viewMenuItems[8] = new JMenuItem( actionMgr.getAction( Command.EPHEMERIS_VIEW ) );
        viewMenuItems[9] = new JMenuItem( actionMgr.getAction( Command.PANCHANG_VIEW ) );
        addMenuItems(menu,viewMenuItems,8,9 );
        
        menus.add(menu);
        
        // Compute Menu
        menu = new JMenu( "Compute" );

        EnumSet<Command> compItems = Command.computeMenuItems();
        computeMenuItems = new JMenuItem[compItems.size()];
        
        i = 0;
        for(Command compItem : compItems){
        	computeMenuItems[i++] = new JMenuItem( actionMgr.getAction( compItem ) );	
        }
        
        addMenuItems(menu, computeMenuItems);
        
        menus.add(menu);

        constructNumerologyMenu();
        //constructExportMenu();
        
        // Option Menu
        menu = new JMenu( "Options" );

        optionSubMenus = new JMenu[2];
        
        // Option Ayanamsa Sub Menu
        optionSubMenus[0] = new JMenu( "Ayanamsa " );

        ButtonGroup ayaBtnGrp = new ButtonGroup(  );
        
        EnumSet<Command> ayaOptions = Command.ayanamsaOptions();
        ayaMenuItems = new JRadioButtonMenuItem[ayaOptions.size()];

        i = 0;
        for ( Command ayanamsa : ayaOptions ) {

            ayaMenuItems[i] =
                new JRadioButtonMenuItem( actionMgr.getAction( ayanamsa) );
            optionSubMenus[0].add( ayaMenuItems[i] );
            ayaBtnGrp.add( ayaMenuItems[i] );
            i++;
        }
        
        //Option Language Sub Menu
        optionSubMenus[1] = new JMenu( "Language " );

        ButtonGroup langBtnGrp = new ButtonGroup(  );

        EnumSet<Command> langOptions = Command.langOptions();
        langMenuItems = new JRadioButtonMenuItem[langOptions.size()];
        i = 0;
        for ( Command lang : Command.langOptions() ) {

        	langMenuItems[i] = new JRadioButtonMenuItem( actionMgr.getAction( lang) );
            optionSubMenus[1].add( langMenuItems[i] );
            langBtnGrp.add( langMenuItems[i] );
            i++;
        }

        
        addSubMenus(menu, optionSubMenus);
        
        //Rest of option menuitems
        EnumSet<Command> optionItems = Command.optionMenuItems();
        JMenuItem []optionMenuItems = new JMenuItem[optionItems.size()];
        i = 0;
        for(Command optionItem : optionItems){
        	optionMenuItems[i++] = new JMenuItem( actionMgr.getAction( optionItem ));
        }
        
        addMenuItems(menu, optionMenuItems);
        
        menus.add(menu);

        AstrosoftPref preferences = AstroSoft.getPreferences();
        ayaMenuItems[preferences.getAyanamsa().ordinal()].setSelected( true );
        langMenuItems[preferences.getLanguage().ordinal()].setSelected( true );
        
        addAllMenus();
        
    }
    
	private void addMenuItems(JMenu menu, JMenuItem[] items){
    	for(JMenuItem item:items){
    		menu.add(item);
    		item.setIcon(null);
    	}
    }
    
    private void addMenuItems(JMenu menu, JMenuItem[] items, int startIndex, int endIndex){
    	for(int i = startIndex; i <= endIndex; i++){
    		menu.add(items[i]);
    		items[i].setIcon(null);
    	}
    }
    
    private void addSubMenus(JMenu menu, JMenu[] subMenus){
    	for(JMenuItem subMenu:subMenus){
    		menu.add(subMenu);
    	}
    }
    
    private void addAllMenus(){
    	for(JMenu menu:menus){
    		add(menu);
    	}
    }
    
    private void constructNumerologyMenu(){
    	
    	JMenu menu = new JMenu(" Numerology ");
    	
    	EnumSet<Command> numeroItems = Command.numeroMenuItems();
    	JMenuItem[] numeroMenuItems = new JMenuItem[numeroItems.size()];

    	int i = 0;
    	for(Command numeroItem : numeroItems){
    		numeroMenuItems[i++] = new JMenuItem( actionMgr.getAction( numeroItem ));
    	}
    	
    	addMenuItems(menu, numeroMenuItems);
    	
    	menus.add(menu);
    }
    
    private void constructExportMenu() {
    	
    	JMenu menu = new JMenu(" Export ");
    	
    	EnumSet<Command> exporItems = Command.exportMenuItems();
    	JMenuItem[] exportMenuItems = new JMenuItem[exporItems.size()];

    	int i = 0;
    	for(Command exporMenuItem : exporItems){
    		exportMenuItems[i++] = new JMenuItem( actionMgr.getAction( exporMenuItem ));
    	}
    	
    	addMenuItems(menu, exportMenuItems);
    	
    	menus.add(menu);
		
	}

}
