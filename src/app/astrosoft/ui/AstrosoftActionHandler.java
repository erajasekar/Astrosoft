/**
 * AstrosoftActionHandler.java
 *
 * Created on August 6, 2005, 6:15 PM
 *
 * @author Rajasekar.
 */

package app.astrosoft.ui;

import java.util.Calendar;

import app.astrosoft.pref.AstrosoftPref.Preference;
import app.astrosoft.ui.view.ViewManager.View;

public interface AstrosoftActionHandler
{

    public void newHoroscope();

    public void openHoroscope();

    public void saveHoroscope();

    public void saveCompactibility();

    public void openCompactibility();

    public void editCompactibility();

    public void printHoroscope();

    public void editHoroscope();

    public void exportHoroscope2Pdf();

    public void exportCompactibility2Pdf();

    public void viewChanged(View view);

    public void optionChanged(Preference option, Object value);

    public void optionChanged(Preference option);

    public void showPanchang(Calendar date);
    
    public void setWaitCursor();
    
    public void setDefaultCursor();
    
    public void computeNumeroNumber();
}
