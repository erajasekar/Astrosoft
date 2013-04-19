/**
 * Alphabet.java
 * Created On 2007, May 3, 2007 10:14:15 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

public enum Alphabet {

	A(1),I(1),J(1),Q(1),Y(1),
	B(2),K(2),R(2),
	C(3),G(3),L(3),S(3),
	D(4),M(4),T(4),
	E(5),H(5),N(5),X(5),
	U(6),V(6),W(6),
	O(7),Z(7),
	F(8),P(8)
	;
	
	private int numeroVal;
	
	private Alphabet(int val){
		numeroVal = val;
	}
	
	public static Alphabet ofChar(char c){
		return Enum.valueOf(Alphabet.class, String.valueOf(Character.toUpperCase(c)));
	}
	
	public int getNumeroVal() {
		return numeroVal;
	}
	
	public static int numeroValOf(char c){
		return Alphabet.ofChar(c).numeroVal;
	}
	
	
}
