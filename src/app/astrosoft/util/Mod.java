/**
 * Mod.java
 * Created On 2006, Feb 24, 2006 12:17:57 PM
 * @author E. Rajasekar
 */

package app.astrosoft.util;

public class Mod {

	int mod;
	
	public Mod(int mod) {
		this.mod = mod;
	}
	
	public int add(int n1, int n2){
		return ((n1 + n2) % mod);
	}
	
	public int sub(int n1, int n2){
		int res = n1 - n2;
		if (res <= 0){
			res = (res % mod) + mod;
		}
		return (res % mod);
	}
	
	public double add(double n1, double n2){
		return ((n1 + n2) % mod);
	}
	
	public double sub(double n1, double n2){
		double res = n1 - n2;
		if (res < 0){
			res = (res % mod) + mod;
		}
		return (res % mod);
	}
	
	public int correct(int n){
		if (n < 0){
			return (n % mod) + mod;
		}else{
			return n % mod;
		}
	}
	
	public double correct(double n){
		if (n < 0){
			return (n % mod) + mod;
		}else{
			return n % mod;
		}
	}
}
