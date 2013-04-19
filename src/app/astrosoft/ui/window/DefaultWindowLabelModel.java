/**
 * DefaultWindowLabelModel.java
 * Created On 2005, Nov 12, 2005 2:45:57 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.window;

public class DefaultWindowLabelModel implements WindowLabelModel {

	private String text;
	
	public DefaultWindowLabelModel(){
		text = null;
	}
	
	public DefaultWindowLabelModel(String text){
		this.text = text;
	}
	public String getText() {
		
		return text;
	}
	
	public void setText(String text){
		this.text = text;
	}
}
