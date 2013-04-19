/**
 * Roman.java
 * Created On 2006, Feb 25, 2006 3:04:07 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

public enum Roman {
	I,
	II,
	III,
	IV,
	V,
	VI,
	VII,
	VIII,
	IX,
	X,
	XI,
	XII;
	
	public static Roman of(int number){
		return values()[number - 1];
	}
}
