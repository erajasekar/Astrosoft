/**
 * AstrosoftPref.java
 * Created On 2005, Nov 18, 2005 7:06:42 PM
 * @author E. Rajasekar
 */
package app.astrosoft.pref;


import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import app.astrosoft.beans.Place;
import static app.astrosoft.beans.Place.getDefault;
import app.astrosoft.consts.Ayanamsa;
import static app.astrosoft.consts.Ayanamsa.getDefault;
import app.astrosoft.consts.Language;
import static app.astrosoft.consts.Language.getDefault;
import app.astrosoft.util.AstroUtil;
import static app.astrosoft.util.AstroUtil.dateToTimeDouble;
import static java.lang.Enum.valueOf;
import static java.lang.System.getProperty;
import static java.util.logging.Logger.getLogger;
import static java.util.prefs.Preferences.systemNodeForPackage;

public class AstrosoftPref {

	private static final Logger log = getLogger(AstrosoftPref.class.getName());

	public static enum Preference {

		Language, PanCalcTime, EphCalcTime, Ayanamsa, Place, City, State, Country, Longitude, Latitude, TimeZone, IsInitialized,
		AcrobatExe, AstrosoftFilesDir;
	}

	private static double defaultEphCalcTime = 0.0; // 12.00 AM

	private static double defaultPanCalcTime = 6.00; // 12.00 AM

	private static boolean defaultIsInitialized = false;
	
	public static String defaultAcrobatExecutable = "";
	
	private static String defaultAstrosoftFilesDir = getProperty("user.home") + File.separator;

	Preferences root = systemNodeForPackage(AstrosoftPref.class);

	public AstrosoftPref() {

		if (getProperty("os.name").toLowerCase().indexOf("windows") >= 0) {
			defaultAstrosoftFilesDir = defaultAstrosoftFilesDir + File.separator + "My Documents" + File.separator;
		}
		if (root.getBoolean(Preference.IsInitialized.name(),
				defaultIsInitialized) == false) {
			log.info("Initializing Astrosoft Preferences ");
			setDefaults();
		}
	}

	public void setLanguage(Language language) {
		root.put(Preference.Language.name(), language.name());
		flush();
	}

	public void setIsInitialized(boolean initialized) {
		root.putBoolean(Preference.IsInitialized.name(), initialized);
		flush();
	}

	public void setPanCalcTime(double time) {
		root.putDouble(Preference.PanCalcTime.name(), time);
		flush();
	}

	public void setEphCalcTime(double time) {
		root.putDouble(Preference.EphCalcTime.name(), time);
		flush();
	}

	public void setAyanamsa(Ayanamsa ayanamsa) {
		root.put(Preference.Ayanamsa.name(), ayanamsa.name());
		flush();
	}

	public void setPlace(Place place) {
		root.node(Preference.Place.name()).put(Preference.City.name(),
				place.city());
		root.node(Preference.Place.name()).put(Preference.State.name(),
				place.state());
		root.node(Preference.Place.name()).put(Preference.Country.name(),
				place.country());
		root.node(Preference.Place.name()).putDouble(
				Preference.Longitude.name(), place.longitude());
		root.node(Preference.Place.name()).putDouble(
				Preference.Latitude.name(), place.latitude());
		root.node(Preference.Place.name()).put(
				Preference.TimeZone.name(), place.astrosoftTimeZone().id());
		flush();

	}

	public void setAcrobatExecutable(String acrobatExecutable){
		root.put(Preference.AcrobatExe.name(),acrobatExecutable);
		flush();
	}
	
	public void setAstrosoftFilesDir(String dir){
		root.put(Preference.AstrosoftFilesDir.name(),dir);
		flush();
	}
	
	private void setDefaults() {

		setLanguage(getDefault());
		setIsInitialized(true);
		setPanCalcTime(defaultPanCalcTime);
		setEphCalcTime(defaultEphCalcTime);
		setAyanamsa(getDefault());
		setPlace(getDefault());
		setAcrobatExecutable(defaultAcrobatExecutable);
		setAstrosoftFilesDir(defaultAstrosoftFilesDir);
		
	}

	public  void flush(){
		try {
			root.flush();
		} catch (BackingStoreException e) {
			log.log(Level.SEVERE, "Exception in flusing preferences " ,e);
		}
	}
	
	public Ayanamsa getAyanamsa(){
		return valueOf(Ayanamsa.class,root.get(Preference.Ayanamsa.name(), getDefault().name()));
	}

	public Language getLanguage() {

		return valueOf(Language.class,root.get(Preference.Language.name(), getDefault().name()));
	}

	public double getEphCalcTime(){
		return root.getDouble(Preference.EphCalcTime.name(), defaultEphCalcTime);
	}

	public double getPanCalcTime(){
		return root.getDouble(Preference.PanCalcTime.name(), defaultPanCalcTime);
	}

	public Place getPlace() {

		Place defaultPlace = getDefault();

		Preferences placeNode = root.node("Place");

		String city = placeNode.get(Preference.City.name(),defaultPlace.city());
		String state = placeNode.get(Preference.State.name(),defaultPlace.state());
		String country = placeNode.get(Preference.Country.name(),defaultPlace.country());
		double latitude = placeNode.getDouble(Preference.Latitude.name(),defaultPlace.latitude());
		double longitude = placeNode.getDouble(Preference.Longitude.name(),defaultPlace.longitude());
		String timeZone = placeNode.get(Preference.TimeZone.name(),defaultPlace.astrosoftTimeZone().id());

		return new Place(city,state,country, latitude, longitude, timeZone);
	}
	
	public String getAcrobatExecutable(){
		return root.get(Preference.AcrobatExe.name(), defaultAcrobatExecutable);
	}
	
	public String getAstrosoftFilesDir(){
		return root.get(Preference.AstrosoftFilesDir.name(), defaultAstrosoftFilesDir);
	}

	private void clearAll() {
		try {
			root.clear();

			for (String child : root.childrenNames()) {
				root.node(child).removeNode();
			}

		} catch (BackingStoreException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {

		AstrosoftPref pref = new AstrosoftPref();
		//pref.clearAll();
		pref.root.exportSubtree(System.out);
	}

	public void preferenceChanged(Preference preference, Object value) {

		//System.out.println(preference + " changed to " + value);

		switch (preference) {

		case Language:
			setLanguage((Language) value);
			break;
		case Ayanamsa:
			setAyanamsa((Ayanamsa) value);
			break;

		case PanCalcTime:

			setPanCalcTime(dateToTimeDouble((Date)value));
			break;
		case EphCalcTime:

			setEphCalcTime(dateToTimeDouble((Date)value));
			break;
		case Place:
			setPlace((Place) value);
			break;

		}
		//FIXME Remove
		/*try {
			root.exportSubtree(System.out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BackingStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		try {
			root.flush();
		} catch (BackingStoreException e) {
			log.log(Level.SEVERE, "Exception in flusing preferences " ,e);
		}

	}

	public void addPreferenceChangeListener(PreferenceChangeListener prefChangeListener){
		root.addPreferenceChangeListener(prefChangeListener);
	}



}
