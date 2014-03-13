/**
 * Internalization.java
 * Created On 2005, Dec 21, 2005 3:13:24 PM
 * @author E. Rajasekar
 */

package app.astrosoft.util;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;

import app.astrosoft.consts.AshtavargaName;
import app.astrosoft.consts.AstrosoftTableColumn;
import app.astrosoft.consts.Ayanamsa;
import app.astrosoft.consts.Varga;
import app.astrosoft.consts.DisplayFormat;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.Kuta;
import app.astrosoft.consts.Language;
import app.astrosoft.consts.Nakshathra;
import app.astrosoft.consts.Paksha;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.Thithi;
import app.astrosoft.pref.AstrosoftPref;
import app.astrosoft.ui.AstroSoft;

public class Internalization  {

	private static final Logger log = Logger.getLogger(Internalization.class.getName());
	
	public static String bundleName = "resources.AstrosoftBundle";

	private static Map<Language,ResourceBundle> bundles;

	//private static Language language;

	private static Map<String, String> unsupportedMsgs = new HashMap<String, String>();

	static {
		//setPreferedLanguate();
		addUnSupportedMsgs();
		loadBundles();
		/*AstroSoft.getPreferences().addPreferenceChangeListener(
				new PreferenceChangeListener() {

					public void preferenceChange(PreferenceChangeEvent evt) {
						if (evt.getKey().equals(
								AstrosoftPref.Preference.Language.name())) {
							setLanguage(Enum.valueOf(Language.class, evt
									.getNewValue()));
						}
					}
				});*/
	}

	private static void loadBundles(){
		
		bundles = new EnumMap<Language,ResourceBundle>(Language.class);
		
		for (Language l : Language.values()){
			
			ResourceBundle bundle = ResourceBundle.getBundle(bundleName, new Locale(l.isoCode()));
			bundles.put(l,bundle);
		}
	}
	private static void addUnSupportedMsgs() {
		unsupportedMsgs
				.put(DisplayStrings.NAKPADA_STR.name(), "¿ìºò¾¢Ãõ À¡¾õ");
		unsupportedMsgs.put(DisplayStrings.NAK_STR.name(), "¿ìºò¾¢Ãõ");
		unsupportedMsgs.put(DisplayStrings.THITHI_PAK_STR.name(), "¾¢¾¢/Àìõ");
		unsupportedMsgs.put(DisplayStrings.SARVAASHTAVARGA_STR.name(),
				"º÷Å¡¼Å÷¸õ");
		unsupportedMsgs.put(DisplayStrings.ASHTAVARGA_STR.name(), "«¼Å÷¸õ");
		unsupportedMsgs.put(DisplayStrings.LONGITUDE_STR.name(), "«ì¡õºõ");
		unsupportedMsgs.put(DisplayStrings.SID_TIME_STR.name(),
				"¿ìºò¾¢Ã §¡¨Ã");
		unsupportedMsgs.put(DisplayStrings.SUNRISE_SET_STR.name(),
				"ÝÃ¢Â ¯¾Âõ/«¾õ");
		unsupportedMsgs.put(DisplayStrings.AYANAMSA_STR.name(), "«ÂÉ¡õõ");
		unsupportedMsgs.put(DisplayStrings.THITHI_PAK_STR.name(), "¾¢¾¢/Àìõ");
		unsupportedMsgs.put(DisplayStrings.PAKSHA_STR.name(), "Àìõ");
		unsupportedMsgs.put(DisplayStrings.SUNSET_STR.name(), "ÝÃ¢Â «¾õ");
		unsupportedMsgs.put(DisplayStrings.PLACE_STR.name(), "þ¼õ");

		unsupportedMsgs.put(DisplayStrings.DOSHA_STR.name(), "§¾¡õ");
		unsupportedMsgs.put(Rasi.Mesha.name(), "§Áõ");
		unsupportedMsgs.put(Rasi.Vrishabha.name(), "Ã¢Àõ");
		unsupportedMsgs.put(Rasi.Vrishabha.sym(), "Ã¢");
		unsupportedMsgs.put(Nakshathra.Ashwini.name(), "«Å¢É¢");
		unsupportedMsgs.put(Nakshathra.Mrigasira.name(), "Á¢Õ¸¢Ã¢¼õ");
		unsupportedMsgs.put(Nakshathra.Hastam.name(), "¾õ");
		unsupportedMsgs.put(Kuta.Vasya.name(), "ÅÂõ");
		unsupportedMsgs.put(Kuta.StreeDeergha.name(), "¾¢Ã¢¾£÷¸õ");
		unsupportedMsgs.put(Varga.Hora.name(), "§¡¨Ã");
		unsupportedMsgs.put(AshtavargaName.AshtaVarga.name(), "«¼Å÷¸õ");
		unsupportedMsgs.put(AshtavargaName.SarvaAshtavarga.name(), "º÷Å¡¼Å÷¸õ");
		
		unsupportedMsgs.put(AstrosoftTableColumn.JaiminiKaraka.name(), "¦öÁ¢É¢ ¸¡Ã¸õ");
		unsupportedMsgs.put(AstrosoftTableColumn.ResidentialStrength.name(), "¦Ã¢¦¼ýº¢Âø");
		unsupportedMsgs.put(AstrosoftTableColumn.SthanaBala.name(), "¾¡½");
		unsupportedMsgs.put(AstrosoftTableColumn.ChestaBala.name(), "§º¼");
		unsupportedMsgs.put(AstrosoftTableColumn.IshtaBala.name(), "þ¼");
		unsupportedMsgs.put(AstrosoftTableColumn.KashtaBala.name(), "¸¼");
		unsupportedMsgs.put(AstrosoftTableColumn.OjaYugmarasyamsaBala.name(), "µÔìÁÃ¡õº");
		unsupportedMsgs.put(AstrosoftTableColumn.SaptavargajaBala.name(), "ºô¾Å÷");
		unsupportedMsgs.put(AstrosoftTableColumn.BhavaDrishtiBala.name(), "À¡Å ¾¢ÕÊ");
		unsupportedMsgs.put(AstrosoftTableColumn.HoraBala.name(), "§¡Ã");
		unsupportedMsgs.put(AstrosoftTableColumn.PakshaBala.name(), "Àì");
		unsupportedMsgs.put(AstrosoftTableColumn.Beeja.name(), "À£õ");
		unsupportedMsgs.put(AstrosoftTableColumn.Kshetra.name(), "§¸¾¢Ãõ");

		unsupportedMsgs.put(Paksha.Krishna.name(), "¸¢Õ½");
		unsupportedMsgs.put(Thithi.Sashti.name(), "ºÊ");
		unsupportedMsgs.put(Thithi.Ashtami.name(), "«¼Á¢");
		unsupportedMsgs.put(Kuta.Rajju.name(), "Ã×");

		// TODO: Create enum and refer
		
		unsupportedMsgs.put("Vishakambha", "Å¢¸õÀõ");
		unsupportedMsgs.put("Ayushman", "¬ÔÁ¡ý");
		unsupportedMsgs.put("Harshana", "÷½õ");
		unsupportedMsgs.put("Vajra", "ÅÃõ");
		unsupportedMsgs.put("Shastiamsa", "ºÊÂ¡õºõ");
		unsupportedMsgs.put("Shashtamsa", "º¡¼¡õºõ");
		unsupportedMsgs.put("Ashtamsa", "«¼¡õºõ");
		
	}

	/*private static void setPreferedLanguate() {
		setLanguage(AstroSoft.getPreferences().getLanguage());
	}

	public static void setLanguage(Language language) {
		
		log.info("Language changed to " + language);
		Locale locale = new Locale(language.isoCode());
		bundle = ResourceBundle.getBundle(bundleName, locale);
		
	}*/

	/*public static void useLanguage(Language language, CallBack caller) {
		setLanguage(language);
		caller.call();
		setPreferedLanguate();
	}*/

	public static String getString(Language language, String key) {
		String msg = null;
		
		ResourceBundle bundle = bundles.get(language);
		log.finer("Bundle : " + bundle.getLocale() + " Key : " + key);
		boolean langNotEnglish = !language.isEnglish();

		if (langNotEnglish) {
			msg = unsupportedMsgs.get(key);

			if (msg != null) {
				return msg;
			}
		}

		try {
			msg = bundle.getString(key);
		} catch (java.util.MissingResourceException e) {

			// If key not found in current resource bundle,
			// get it from English language's bundle.
			if (langNotEnglish) {
				msg = bundles.get(Language.ENGLISH).getString(key);
			} else {
				throw e;
			}
		}
		return msg;
	}

	public static String getString(String key) {
		/*String str = "";
		setLanguage(language);
		str = getString(key);
		setPreferedLanguate();*/
		
		//setLanguage(language);
		return  getString(AstroSoft.getPreferences().getLanguage(), key);
		//setPreferedLanguate(Ast);
		//return str;
	}

	public static String getString(Language language, Enum string){
		return getString(language, string.name());
	}
	
	public static String getString(DisplayFormat format, String... args) {

		switch (format) {
			case FULL_NAME:
			default:
				return getString(args[0]);
	
			case SYMBOL:
				return getString(args[1]);
		}
	}
}
