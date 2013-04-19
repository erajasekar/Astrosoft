/**
 * Month.java
 * Created On 2006, Jan 7, 2006 8:29:54 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

public enum Month {

	January("Jan", 31),
	Febraury("Feb", 28),
	March("Mar", 31),
	April("Apr", 30),
	May("May", 31),
	June("Jun", 30),
	July("Jul", 31),
	August("Aug", 31),
	September("Sep", 30),
	October("Oct", 31),
    November("Nov", 30),
    December("Dec", 31);
	
	private int length;
	private String sym;
	
	private static Month []vals = values();
	
	private Month(String sym, int noOfDays){
		this.sym = sym;
		this.length = noOfDays;
	}
	
	public static Month ofIndex(int index){
		return vals[index % vals.length];
	}
	
	public int length(){
		return length;
	}
	
	public String toString(DisplayFormat fmt) {
		
		switch(fmt){
			case FULL_NAME: return name();
			case SYMBOL: return sym;
		}
		throw new IllegalArgumentException("Invalid Format " + fmt);
	}
	
	@Override
	public String toString() {
		
		return toString(DisplayFormat.SYMBOL);
	}
}
