/**
 * Thithi.java
 * Created On 2006, Jan 21, 2006 5:25:46 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

public enum Thithi {

	Prathamai,
	Dvithiyai,
	Thrithiyai,
	Chathruthi,
	Panchami,
	Sashti,
	Sapthami,
	Ashtami,
	Navami,
	Dasami,
	Ekaadasi,
	Dvaadasi,
	Thrayodasi,
	Chathurdasi,
	Pournami,
	Amavasya;
	
	private static Thithi vals[] = values();

	public static Thithi ofIndex(int index) {
		return vals[index % vals.length];
	}
	
	public static Thithi ofDeg(double sun, double moon ) {

        Thithi thithi;
        Paksha pak = Paksha.Shukla;
        double diff;

        if ( moon > sun ) {
            diff = moon - sun;
        } else {
            diff = ( moon + 360 ) - sun;
        }

        if ( diff > 180.0 ) {
            pak = Paksha.Krishna;
            diff = diff - 180;
        }

        thithi = ofIndex(( int ) ( diff / 12 ));

        //For Krishna Paksha 15th thithi is Ammavasya
        if ( ( thithi == Thithi.Pournami ) && ( pak == Paksha.Krishna ) ) { 
            thithi = Thithi.Amavasya;
        }
        return thithi;
    }

}
