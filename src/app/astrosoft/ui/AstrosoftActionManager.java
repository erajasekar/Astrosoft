/*
 * AstrosoftActionManager.java
 *
 * Created on August 6, 2005, 6:42 PM
 *
* @author Rajasekar.
 */

package app.astrosoft.ui;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.KeyEvent;
import javax.swing.Action;

import app.astrosoft.consts.Command;
import app.astrosoft.ui.util.UIUtil;

public class AstrosoftActionManager
{
    EnumMap<Command,Action> asActions;
    
    public AstrosoftActionManager(AstrosoftActionHandler handler) {
        
        asActions = new EnumMap<Command,Action>(Command.class);
        createAstrosoftActions(handler);
    }
    
    private void createAstrosoftActions(AstrosoftActionHandler handler) {
        
    	for(Command cmd: Command.values()){
    		asActions.put(cmd, new AstrosoftAction(cmd, handler));
    	}
    }
    
    
    
    public Action getAction(Command key) {
        
       return (Action) asActions.get(key);
    }

    public void enableActions(EnumSet<Command> keys, boolean enable) {
        
        for(Command key : keys ) {
            
            getAction(key).setEnabled(enable);
        }
    }
    
    public void enableAction(Command key, boolean enable){
    	getAction(key).setEnabled(enable);
    }
    
    
}
