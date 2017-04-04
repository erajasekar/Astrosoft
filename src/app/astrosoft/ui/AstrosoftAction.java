/**
 * AstrosoftAction.java
 *
 * Created on August 6, 2005, 6:04 PM
 *
 * @author Rajasekar.
 */

package app.astrosoft.ui;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import app.astrosoft.consts.Command;
import app.astrosoft.consts.Ayanamsa;
import static app.astrosoft.consts.Command.optionMenuItems;
import static app.astrosoft.consts.Command.valueOf;
import app.astrosoft.consts.Varga;
import app.astrosoft.service.NumeroNameService;
import app.astrosoft.test.NumeroNameTest;
import app.astrosoft.ui.view.ViewManager.View;
import app.astrosoft.consts.Language;
import app.astrosoft.pref.AstrosoftPref.Preference;
import static app.astrosoft.pref.AstrosoftPref.Preference.valueOf;
import static app.astrosoft.service.NumeroNameService.exportNames;
import static app.astrosoft.service.NumeroNameService.importNamesFromCSV;
import static app.astrosoft.service.NumeroNameService.importNamesFromList;
import app.astrosoft.ui.util.UIUtil;
import static app.astrosoft.ui.util.UIUtil.createImageIcon;
import static app.astrosoft.ui.view.ViewManager.View.commands;
import static app.astrosoft.ui.view.ViewManager.View.valueOf;
import app.astrosoft.util.AstrosoftFileFilter;
import app.astrosoft.util.FileOps;
import app.astrosoft.util.FileOps.FileDialogMode;
import static app.astrosoft.util.FileOps.openFileDialog;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import static java.lang.System.exit;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

public class AstrosoftAction extends AbstractAction {

	private static final Logger log = getLogger(AstrosoftAction.class.getName());

	AstrosoftActionHandler handler;

	public AstrosoftAction(Command name,AstrosoftActionHandler handler) {
		super(name.action(), createImageIcon(name.action()));
		this.handler = handler;
		putValue(SHORT_DESCRIPTION, name.action());
		putValue(ACTION_COMMAND_KEY, name.name());
		if (name.mnemonic() != -1) {
			putValue(MNEMONIC_KEY, name.mnemonic());
			
		}
		
		if (name.key() != null){
			putValue(ACCELERATOR_KEY, name.key());
		}
	}

	public void actionPerformed(ActionEvent e) {

		Command cmd = valueOf(e.getActionCommand());

		if (cmd.equals(Command.NEW)) {
			handler.newHoroscope();
		} else if (cmd.equals(Command.OPEN)) {
			handler.openHoroscope();
		}
	    else if (cmd.equals(Command.OPEN_COMPACTIBILITY)) {
	    	handler.openCompactibility();
     	}
		else if (cmd.equals(Command.SAVE)) {
			handler.saveHoroscope();
		}

		else if (cmd.equals(Command.SAVE_COMPACTIBILITY)) {
			handler.saveCompactibility();
		} else if (cmd.equals(Command.PRINT)) {
			handler.printHoroscope();
		} else if (cmd.equals(Command.EXIT)) {
			exit(0);
		} else if (cmd.equals(Command.EDIT_CHART)) {
			handler.editHoroscope();
		} else if (cmd.equals(Command.EDIT_COMPACTIBILITY)) {
			handler.editCompactibility();
		}else if (commands().contains(cmd)){
			handler.viewChanged(valueOf(cmd.name()));
		}
		else if (cmd.equals(Command.AY_KM)) {
			handler.optionChanged(Preference.Ayanamsa, Ayanamsa.KRISHNAMURTHI);
		} else if (cmd.equals(Command.AY_LAH)) {
			handler.optionChanged(Preference.Ayanamsa, Ayanamsa.LAHARI);
		} else if (cmd.equals(Command.AY_RAM)) {
			handler.optionChanged(Preference.Ayanamsa, Ayanamsa.RAMAN);
		} else if (cmd.equals(Command.LANG_EN)) {
			handler.optionChanged(Preference.Language, Language.ENGLISH);
		} else if (cmd.equals(Command.LANG_TA)) {
			handler.optionChanged(Preference.Language, Language.TAMIL);
		} else if (optionMenuItems().contains(cmd)) {
			handler.optionChanged(valueOf(cmd.name()));
		} else if (cmd.equals(Command.COMPUTE_NUMBER)){
			log.fine("COMPUTE_NUMBER menu clicked");
			handler.computeNumeroNumber();
			
		} else if(cmd.equals(Command.EXPORT_NAME_DB)){
			
			log.fine("EXPORT_NAME_DB");
			String selectedFile = openFileDialog(null,FileDialogMode.SAVE, AstrosoftFileFilter.ALL_FILES);
			if (selectedFile != null){
				handler.setWaitCursor();
				exportNames(selectedFile);
				handler.setDefaultCursor();
			}
			else{
				log.warning("No file selected");
			}
			
		}else if(cmd.equals(Command.IMPORT_NAME_DB)){
			log.fine("IMPORT_NAME_DB");
			
			String selectedFile = openFileDialog(null,FileDialogMode.OPEN, AstrosoftFileFilter.ALL_FILES);
			
			if (selectedFile != null) {
				handler.setWaitCursor();
				importNamesFromCSV(selectedFile);
				handler.setDefaultCursor();
			}
			else{
				log.warning("No file selected");
			}
			
		}else if(cmd.equals(Command.IMPORT_NAME)){
			log.fine("IMPORT_NAME");
			
			String selectedFile = openFileDialog(null,FileDialogMode.OPEN, AstrosoftFileFilter.ALL_FILES);
			
			if (selectedFile != null) {
				handler.setWaitCursor();
				importNamesFromList(selectedFile);
				handler.setDefaultCursor();
			}
			else{
				log.warning("No file selected");
			}
				
		}else if(cmd.equals(Command.EXPORT_HOROSCOPE_TO_PDF)){
			log.fine("EXPORT_HOROSCOPE_TO_PDF menu clicked");
			handler.exportHoroscope2Pdf();
		}else if(cmd.equals(Command.EXPORT_COMPACTIBILITY_TO_PDF)){
			log.fine("EXPORT_COMPACTIBILITY_TO_PDF menu clicked");
			handler.exportCompactibility2Pdf();
		}
	}
}
