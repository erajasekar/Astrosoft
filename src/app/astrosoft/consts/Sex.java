/**
 * Sex.java
 Created On 2007, Nov 12, 2007 5:02:47 PM
 @author E. Rajasekar
 */

package app.astrosoft.consts;

public enum Sex {
	Male("M"),
	Female("F");
	
	private String sym;

	private Sex(String sym){
		this.sym = sym;
	}
	
	public boolean isMale(){
		return this == Male;
	}
	
	public boolean isFemale(){
		return this == Female;
	}
}

