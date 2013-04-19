/**
 * NakshathraPada.java
 * Created On 2006, Jan 8, 2006 7:33:17 PM
 * @author E. Rajasekar
 */

package app.astrosoft.beans;

import app.astrosoft.consts.AstroConsts;
import app.astrosoft.consts.Nakshathra;
import app.astrosoft.consts.Rasi;

public class NakshathraPada {

	private Nakshathra nak;

	int pada;
	
	public NakshathraPada(Nakshathra nak, int pada) {
		this.nak = nak;
		this.pada = pada;
	}
	
	public NakshathraPada(double deg){
		this.nak = Nakshathra.ofDeg(deg);
		pada = ((int) (deg / AstroConsts.padaLength) % 4) + 1;
	}
	
	public Nakshathra getNak() {
		return nak;
	}
	
	public int getPada() {
		return pada;
	}
	
	@Override
	public String toString() {
		
		String str = nak.toString() + " ~ " + pada;
		return str;
	}
	
	//FIXME
	public static void main(String[] args) {
		NakshathraPada np = new NakshathraPada(359);
		System.out.println(np);
	}
}
