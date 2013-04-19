/**
 * Command.java
 *
 * Created on August 04, 2005, 7:16 PM
 *
 * @author  E. Rajasekar
 */
package app.astrosoft.consts;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.EnumSet;

import javax.swing.KeyStroke;

import app.astrosoft.ui.view.ViewManager.View;


public enum Command {

	NEW ("New",KeyEvent.VK_N, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)),
	OPEN ("Open",KeyEvent.VK_O, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK )),
	OPEN_COMPACTIBILITY ("Open Compactibility", KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK)),
	SAVE ("Save",KeyEvent.VK_S,KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK)),
	SAVE_COMPACTIBILITY("Save Compactibility", KeyEvent.VK_C,KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK)),
	PRINT ("Print",KeyEvent.VK_P,KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK)),
	EXIT ("Exit",KeyEvent.VK_X,KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK)),

	EDIT_CHART ("Chart Data",KeyEvent.VK_C),
	EDIT_COMPACTIBILITY ("Compactibility",KeyEvent.VK_M),
	CHART_VIEW (View.CHART_VIEW.displayVal(),KeyEvent.VK_C),
	DASAS_VIEW (View.DASAS_VIEW.displayVal(),KeyEvent.VK_D),
	PLANET_POS_VIEW (View.PLANET_POS_VIEW.displayVal(),KeyEvent.VK_P),
	BHAVA_POS_VIEW (View.BHAVA_POS_VIEW.displayVal(),KeyEvent.VK_B),
	DIV_CHART_VIEW (View.DIV_CHART_VIEW.displayVal(),KeyEvent.VK_I),
	ASHTAVARGA_VIEW (View.ASHTAVARGA_VIEW.displayVal(),KeyEvent.VK_A),
	SHADBALA_VIEW (View.SHADBALA_VIEW.displayVal(),KeyEvent.VK_S),
	YOGA_COMBINATIONS_VIEW (View.YOGA_COMBINATIONS_VIEW.displayVal(),KeyEvent.VK_Y),
	EPHEMERIS_VIEW (View.EPHEMERIS_VIEW.displayVal(),KeyEvent.VK_E),
	PANCHANG_VIEW (View.PANCHANG_VIEW.displayVal(),KeyEvent.VK_G),
	COMPACTIBILITY_VIEW (View.COMPACTIBILITY_VIEW.displayVal(),KeyEvent.VK_M),
	MUHURTHA_VIEW (View.MUHURTHA_VIEW.displayVal()),
	//
	//CMP_COMP ("Marriage Compactibility",KeyEvent.VK_M),
	//CMP_MUHURTHA ("Muhurtha"),

	AY_LAH (Ayanamsa.LAHARI.ayaName()),
	AY_RAM (Ayanamsa.RAMAN.ayaName()),
	AY_KM (Ayanamsa.KRISHNAMURTHI.ayaName()),
	LANG_EN (Language.ENGLISH.name()),
	LANG_TA (Language.TAMIL.name()),
	Place (DisplayStrings.DEF_LOC_STR),
	EphCalcTime(DisplayStrings.EPH_TIME_STR),
	PanCalcTime(DisplayStrings.PAN_TIME_STR),

	//Numerlogy Commands
	FIND_NAME_VIEW(View.FIND_NAME_VIEW.displayVal()),
	COMPUTE_NUMBER(DisplayStrings.COMPUTE_NUMBER_STR),
	EXPORT_NAME_DB(DisplayStrings.EXPORT_NAME_DB_STR),
	IMPORT_NAME_DB(DisplayStrings.IMPORT_NAME_DB_STR),
	IMPORT_NAME(DisplayStrings.IMPORT_NAME_STR),
	EXPORT_HOROSCOPE_TO_PDF(DisplayStrings.EXPORT_HOROSCOPE_TO_PDF_STR),
	EXPORT_COMPACTIBILITY_TO_PDF(DisplayStrings.EXPORT_COMPACTIBILITY_TO_PDF_STR)
	;

	private String action;
	private int mnemonic = -1;
	private KeyStroke key = null;

	private Command(Enum action){
		this(action.toString());
	}

	private Command(String action) {
		this.action = action;
	}

	private Command(String action, int mnemonic) {
		this.action = action;
		this.mnemonic = mnemonic;
	}
	
	private Command(String action, int mnemonic, KeyStroke key) {
		this.action = action;
		this.mnemonic = mnemonic;
		this.key = key;
	}
	
	private Command(String action, KeyStroke key) {
		this.action = action;
		this.key = key;
	}

	public String action(){
		return action;
	}

	public int mnemonic(){
		return mnemonic;
	}
	
	public KeyStroke key(){
		return key;
	}

	public static EnumSet<Command> ayanamsaOptions(){
		return EnumSet.range(AY_LAH, AY_KM);
	}

	public static EnumSet<Command> langOptions(){
		return EnumSet.range(LANG_EN, LANG_TA);
	}

	public static EnumSet<Command> fileMenuItems(){
		return EnumSet.range(NEW, EXIT);
	}

	public static EnumSet<Command> editMenuItems(){
		return EnumSet.range(EDIT_CHART, EDIT_COMPACTIBILITY);
	}

	public static EnumSet<Command> viewMenuItems(){
		return EnumSet.range(CHART_VIEW, PANCHANG_VIEW);
	}

	public static EnumSet<Command> computeMenuItems(){
		return EnumSet.range(COMPACTIBILITY_VIEW, MUHURTHA_VIEW);
	}

	public static EnumSet<Command> optionMenuItems(){
		return EnumSet.range(Place, PanCalcTime);
	}

	public static EnumSet<Command> numeroMenuItems(){
		return EnumSet.range(FIND_NAME_VIEW, IMPORT_NAME);
	}

	public static EnumSet<Command> exportMenuItems(){
		return EnumSet.range(EXPORT_HOROSCOPE_TO_PDF, EXPORT_COMPACTIBILITY_TO_PDF);
	}

	public static EnumSet<Command> toolBarItems(){
		return EnumSet.of(Command.NEW ,
				Command.OPEN ,
				Command.SAVE ,
				Command.PRINT ,
				Command.DIV_CHART_VIEW ,
				Command.PLANET_POS_VIEW ,
				Command.BHAVA_POS_VIEW ,
				Command.DASAS_VIEW,
				Command.CHART_VIEW ,
				Command.ASHTAVARGA_VIEW ,
				Command.SHADBALA_VIEW ,
				Command.EPHEMERIS_VIEW ,
				Command.PANCHANG_VIEW ,
				Command.COMPACTIBILITY_VIEW);
	}
}
