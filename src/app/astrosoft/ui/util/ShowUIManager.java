/**
 * ShowUIManager.java
 Created On 2007, May 22, 2007 10:48:50 PM
 @author E. Rajasekar
 */

package app.astrosoft.ui.util;

import javax.swing.*;

import java.util.*;
 
public class ShowUIManager {
 
    public static void main(String[] args) {
    	
    	try {
			UIManager.setLookAndFeel(UIConsts.getLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        UIDefaults defaults = UIManager.getDefaults();
        System.out.println("Count Item = " + defaults.size());
        String[ ] colName = {"Key", "Value"};
        String[ ][ ] rowData = new String[ defaults.size() ][ 2 ];
        int i = 0;
        for(Enumeration e = defaults.keys(); e.hasMoreElements(); ){
            String key = e.nextElement().toString();
            //if (key.indexOf("Dialog") >= 0){
            	i++;
            	rowData[ i ] [ 0 ] = key;
                rowData[ i ] [ 1 ] = ""+defaults.get(key);
                System.out.println(rowData[i][0]+" ,, "+rowData[i][1]);
            //}
            
        }
        JFrame f = new JFrame("UIDefaults Key-Value sheet");
        JTable t = new JTable(rowData, colName);
        f.setContentPane(new JScrollPane(t));
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}

