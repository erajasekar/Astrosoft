/**
 * ViewManager.java
 * Created On 2006, Mar 27, 2006 7:32:36 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.view;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import app.astrosoft.consts.Command;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.Language;
import app.astrosoft.ui.AstroSoft;

public class ViewManager {

	public static enum View{
		CHART_VIEW("Chart"),
		DASAS_VIEW(DisplayStrings.DASA_STR.toString(Language.ENGLISH)),
		PLANET_POS_VIEW(DisplayStrings.PLANET_POS_STR.toString(Language.ENGLISH)),
		BHAVA_POS_VIEW(DisplayStrings.BHAVA_POS_STR.toString(Language.ENGLISH)),
		DIV_CHART_VIEW(DisplayStrings.DIV_CHARTS_STR.toString(Language.ENGLISH)),
		ASHTAVARGA_VIEW(DisplayStrings.ASHTAVARGA_STR.toString(Language.ENGLISH)),
		SHADBALA_VIEW(DisplayStrings.SHADBALA_STR.toString(Language.ENGLISH)),
		PANCHANG_VIEW("Panchang"),
		EPHEMERIS_VIEW("Ephemeris"),
		COMPACTIBILITY_VIEW("Marriage Compactibility"),
		MUHURTHA_VIEW("Muhurtha"),
		FIND_NAME_VIEW(DisplayStrings.FIND_NAME_STR.toString(Language.ENGLISH)),
		YOGA_COMBINATIONS_VIEW(DisplayStrings.YOGA_COMBINATIONS_STR.toString(Language.ENGLISH))
		;

		private String displayVal;

		private View(String displayVal) {
			this.displayVal = displayVal;
		}

		public String displayVal() {

			return displayVal;
		}

		@Override
		public String toString() {

			return displayVal;
		}

		public static Set<Command> commands(){

			Set<Command> cmds = new HashSet<Command>();
			for(View v : values()){
				cmds.add(Command.valueOf(v.name()));
			}
			return cmds;
		}
	};


	private ViewContainer container;

	public ViewManager(ViewContainer container) {
		this.container = container;
	}

	public void showView(View view) {
		JPanel viewPanel = container.createView(view);
		container.addView(viewPanel);
	}

}
