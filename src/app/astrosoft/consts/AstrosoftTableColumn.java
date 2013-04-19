/**
 * AstrosoftTableColumn.java
 * Created On 2005, Oct 27, 2005 8:18:10 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

import java.util.EnumSet;

import app.astrosoft.util.Internalization;

public enum AstrosoftTableColumn {

	Period,
	PeriodPopup,
	Longitude {
		public String toString() {
			return DisplayStrings.LONGITUDE_STR.toString();
		}
	},
	Nakshathra {
		public String toString() {
			return DisplayStrings.NAK_STR.toString();
		}
	},
	Rasi {
		public String toString() {
			return DisplayStrings.RASI_STR.toString();
		}
	},
	Rank,
	Kuta,
	KutaGained,
	MaxKuta,

	Total {
		public String toString() {
			return DisplayStrings.TOTAL_STR.toString();
		}
	},
	Key,
	Value,
	Beeja,
	Kshetra,
	Dosha{
		public String toString() {
			return DisplayStrings.DOSHA_STR.toString();
		}
	},
	Boy {
		public String toString() {
			return DisplayStrings.BOY_STR.toString();
		}
	},
	Girl {
		public String toString() {
			return DisplayStrings.GIRL_STR.toString();
		}
	},
	Sun ,
	Moon ,
	Mars ,
	Mercury ,
	Jupiter,
	Venus ,
	Saturn ,
	Rahu,
	Ketu,
	Ascendant,
	Bhava,
	House,
	No,
	Start,
	Mid,
	End,
	Length,
	Planet {
		public String toString() {
			return DisplayStrings.PLANET_STR.toString();
		}
	},
	NakshathraPada {
		public String toString() {
			return DisplayStrings.NAKPADA_STR.toString();
		}
	},
	JaiminiKaraka,
	ShadBala,
	BhavaBala,
	Bala,
	ResidentialStrength,
	SthanaBala,
	KalaBala,
	DigBala,
	DrikBala,
	ChestaBala,
	NaisargikaBala,
	OchchaBala,
	SaptavargajaBala,
	OjaYugmarasyamsaBala,
	KendraBala,
	DrekkanaBala,
	AbdaBala,
	MasaBala,
	VaraBala,
	HoraBala,
	PakshaBala,
	TribhagaBala,
	NatonnataBala,
	AyanaBala,
	YuddhaBala,
	BhavaAdhipathiBala,
	BhavaDigBala,
	BhavaDrishtiBala,
	IshtaBala,
	KashtaBala,
	BalaPercentage,
	Rupa,
	Dasa {
		public String toString() {
			return DisplayStrings.DASA_STR.toString();
		}
	},
	C1,
	C2,
	C3,
	Date {
		public String toString() {
			return DisplayStrings.DATE_STR.toString();
		}
	},
	Month,
	Name {
		public String toString() {
			return DisplayStrings.NAME_STR.toString();
		}
	},
	
	NumeroValue {
		public String toString() {
			return DisplayStrings.NUMERO_VALUE_STR.toString();
		}
	},
	
	NumeroNumber {
		public String toString() {
			return DisplayStrings.NUMERO_NUMBER_STR.toString();
		}
	},
	;

	/*
	 * public static Planet ofPlanet(AstrosoftTableColumn col){ return
	 * Planet.valueOf(Planet.class, col.name()); }
	 */

	public <T extends Enum<T>> T toEnum(Class<T> e) {
		return T.valueOf(e, this.name());
	}

	//TODO: create EnumSet for other table cols as well.
	public static EnumSet<AstrosoftTableColumn> chartHouseCols(){
		return EnumSet.range(C1,C3);
	}
	
//	TODO: create EnumSet for other table cols as well.
	public static EnumSet<AstrosoftTableColumn> keyvalCols(){
		return EnumSet.of(Key,Value);
	}
	
	public String toString() {

		String value;
		try{
			value = Internalization.getString(this.name());
		}catch (java.util.MissingResourceException e){
			value = this.name();
		}
		
		return value;
	}
	public String toString(Language lang) {

		return Internalization.getString(lang, this.name());
	}

	public static void main(String[] args) {
		AstrosoftTableColumn col = AstrosoftTableColumn.Sun;

		System.out.println(House);
		System.out.println(Bhava);
		
	}
}
