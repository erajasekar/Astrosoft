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
import static app.astrosoft.consts.Language.values;
import app.astrosoft.consts.Nakshathra;
import app.astrosoft.consts.Paksha;
import app.astrosoft.consts.Rasi;
import app.astrosoft.consts.Thithi;
import app.astrosoft.pref.AstrosoftPref;
import app.astrosoft.ui.AstroSoft;
import static app.astrosoft.ui.AstroSoft.getPreferences;
import static java.util.ResourceBundle.getBundle;
import static java.util.logging.Logger.getLogger;

public class Internalization  {

	private static final Logger log = getLogger(Internalization.class.getName());
	
	public static String bundleName = "resources.AstrosoftBundle";

	private static Map<Language,ResourceBundle> bundles;

	//private static Language language;

	private static Map<String, String> unsupportedMsgs = new HashMap<>();

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
		
		bundles = new EnumMap<>(Language.class);
		
		for (Language l : values()){
			
			ResourceBundle bundle = getBundle(bundleName, new Locale(l.isoCode()));
			bundles.put(l,bundle);
		}
	}
	private static void addUnSupportedMsgs() {
		unsupportedMsgs
				.put(DisplayStrings.NAKPADA_STR.name(), "�섺��� ����");
		unsupportedMsgs.put(DisplayStrings.NAK_STR.name(), "�섺���");
		unsupportedMsgs.put(DisplayStrings.THITHI_PAK_STR.name(), "����/���");
		unsupportedMsgs.put(DisplayStrings.SARVAASHTAVARGA_STR.name(),
				"��š������");
		unsupportedMsgs.put(DisplayStrings.ASHTAVARGA_STR.name(), "�������");
		unsupportedMsgs.put(DisplayStrings.LONGITUDE_STR.name(), "�섡���");
		unsupportedMsgs.put(DisplayStrings.SID_TIME_STR.name(),
				"�섺�� �����");
		unsupportedMsgs.put(DisplayStrings.SUNRISE_SET_STR.name(),
				"�â� ����/����");
		unsupportedMsgs.put(DisplayStrings.AYANAMSA_STR.name(), "��ɡ���");
		unsupportedMsgs.put(DisplayStrings.THITHI_PAK_STR.name(), "����/���");
		unsupportedMsgs.put(DisplayStrings.PAKSHA_STR.name(), "���");
		unsupportedMsgs.put(DisplayStrings.SUNSET_STR.name(), "�â� ����");
		unsupportedMsgs.put(DisplayStrings.PLACE_STR.name(), "���");

		unsupportedMsgs.put(DisplayStrings.DOSHA_STR.name(), "�����");
		unsupportedMsgs.put(Rasi.Mesha.name(), "����");
		unsupportedMsgs.put(Rasi.Vrishabha.name(), "â���");
		unsupportedMsgs.put(Rasi.Vrishabha.sym(), "â�");
		unsupportedMsgs.put(Nakshathra.Ashwini.name(), "��Ţɢ");
		unsupportedMsgs.put(Nakshathra.Mrigasira.name(), "��ո��â��");
		unsupportedMsgs.put(Nakshathra.Hastam.name(), "����");
		unsupportedMsgs.put(Kuta.Vasya.name(), "Ŋ��");
		unsupportedMsgs.put(Kuta.StreeDeergha.name(), "���â�����");
		unsupportedMsgs.put(Varga.Hora.name(), "�����");
		unsupportedMsgs.put(AshtavargaName.AshtaVarga.name(), "�������");
		unsupportedMsgs.put(AshtavargaName.SarvaAshtavarga.name(), "��š������");
		
		unsupportedMsgs.put(AstrosoftTableColumn.JaiminiKaraka.name(), "�����ɢ ��ø�");
		unsupportedMsgs.put(AstrosoftTableColumn.ResidentialStrength.name(), "�Ä��������");
		unsupportedMsgs.put(AstrosoftTableColumn.SthanaBala.name(), "����");
		unsupportedMsgs.put(AstrosoftTableColumn.ChestaBala.name(), "����");
		unsupportedMsgs.put(AstrosoftTableColumn.IshtaBala.name(), "���");
		unsupportedMsgs.put(AstrosoftTableColumn.KashtaBala.name(), "���");
		unsupportedMsgs.put(AstrosoftTableColumn.OjaYugmarasyamsaBala.name(), "�����Ä���");
		unsupportedMsgs.put(AstrosoftTableColumn.SaptavargajaBala.name(), "������");
		unsupportedMsgs.put(AstrosoftTableColumn.BhavaDrishtiBala.name(), "��� ��Չ�");
		unsupportedMsgs.put(AstrosoftTableColumn.HoraBala.name(), "����");
		unsupportedMsgs.put(AstrosoftTableColumn.PakshaBala.name(), "��");
		unsupportedMsgs.put(AstrosoftTableColumn.Beeja.name(), "����");
		unsupportedMsgs.put(AstrosoftTableColumn.Kshetra.name(), "�������");

		unsupportedMsgs.put(Paksha.Krishna.name(), "��Չ�");
		unsupportedMsgs.put(Thithi.Sashti.name(), "���");
		unsupportedMsgs.put(Thithi.Ashtami.name(), "�����");
		unsupportedMsgs.put(Kuta.Rajju.name(), "È�");

		// TODO: Create enum and refer
		
		unsupportedMsgs.put("Vishakambha", "Ţ�����");
		unsupportedMsgs.put("Ayushman", "�ԉ���");
		unsupportedMsgs.put("Harshana", "�����");
		unsupportedMsgs.put("Vajra", "ň��");
		unsupportedMsgs.put("Shastiamsa", "���¡���");
		unsupportedMsgs.put("Shashtamsa", "��������");
		unsupportedMsgs.put("Ashtamsa", "�������");
		
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
		return  getString(getPreferences().getLanguage(), key);
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
