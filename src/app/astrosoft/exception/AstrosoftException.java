/**
 * AstrosoftException.java
 * Created On 2007, Sep 3, 2007 10:31:29 PM
 * @author E. Rajasekar
 */

package app.astrosoft.exception;

public class AstrosoftException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AstrosoftException(String msg, Throwable cause){
		super(msg,cause);
		
	}

	public AstrosoftException(String msg) {
		super(msg);
	}
	
}
