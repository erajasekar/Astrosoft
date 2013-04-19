/**
 * Language.java
 * Created On 2005, Nov 19, 2005 2:08:14 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

public enum Language {
	ENGLISH("en", "Arial"),TAMIL("ta", "TSCArial");
	
	private String isoLangCode;
	private String fontName;
	
	private Language(String code, String fontName){
		this.isoLangCode = code;
		this.fontName = fontName;
	}
	public static Language getDefault(){
		return ENGLISH;
	}
	
	public String isoCode(){
		return isoLangCode;
	}
	
	public String font(){
		return fontName;
	}
	
	public boolean isEnglish(){
		return (this == Language.ENGLISH);
	}
	
	public boolean isTamil(){
		return (this == Language.TAMIL);
	}
}
