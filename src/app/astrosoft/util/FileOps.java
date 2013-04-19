/**
 * FileOps.java
 *
 * Created on December 28, 2002, 10:44 AM
 *
 * @author  E. Rajasekar
 */
package app.astrosoft.util;

import java.awt.Color;
import java.awt.Component;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import app.astrosoft.exception.AstrosoftException;
import app.astrosoft.pref.AstrosoftPref;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.dlg.OptionDialog;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.ui.util.UIUtil;


public class FileOps {
	
	private static final Logger log = Logger.getLogger(FileOps.class.getName());

	public static enum FileDialogMode {OPEN, SAVE};
    
	
	/** Open's file dialog and returns selected file.
     * 
     * @param as parent
     * @param mode FileDialogMode - open, save
     * @return Selected file as String
     */
    public static String openFileDialog( Component parent, FileDialogMode mode, AstrosoftFileFilter astrosoftFileFilter ) {

    	AstrosoftPref pref = AstroSoft.getPreferences();
    	
    	String initialFile = pref.getAstrosoftFilesDir();
    	
        JFileChooser fd = new JFileChooser( initialFile );
        UIUtil.setPanelBackground(fd, UIConsts.THEME_CLR);
        fd.setOpaque(true);
        
        fd.setFileFilter( astrosoftFileFilter );
        
        String selectedFile = null;
        if (mode == FileDialogMode.OPEN){
        	selectedFile = showFileOpenDialog(parent, fd, astrosoftFileFilter);
        }else if (mode == FileDialogMode.SAVE) {
        	selectedFile = showFileSaveDialog(parent, fd, astrosoftFileFilter);
        }

        if (selectedFile != null) {
        	pref.setAstrosoftFilesDir(fd.getSelectedFile().getParentFile().getAbsolutePath() + File.separator);
        }
        
        return selectedFile;
    }
    
    private static String showFileSaveDialog(Component parent, JFileChooser fd, AstrosoftFileFilter fileFilter){
    	
    	while (true) {
    		
    		int returnVal = fd.showSaveDialog(parent);
        	
        	if (returnVal == JFileChooser.APPROVE_OPTION){
        		
        		String selectedFile = getSelectedFile(fd, fileFilter);

                if (new File(selectedFile).exists()){
                	int option = OptionDialog.showDialog(selectedFile + " already exists. \n Do you want to overwrite it ? " , JOptionPane.QUESTION_MESSAGE);
                	if (option == JOptionPane.YES_OPTION){
                		return selectedFile;
                	}
                }else{
                	return selectedFile;
                }
        	//Cancel clicked break out	
        	}else{
        		break;
        	}
    	}
    	
    	return null;
    	
    }
    
    private static String showFileOpenDialog(Component parent, JFileChooser fd, AstrosoftFileFilter fileFilter){
    	
    	while(true){
    		
    		int returnVal = fd.showOpenDialog(parent);
        	
        	if (returnVal == JFileChooser.APPROVE_OPTION){
        		
        		String selectedFile = getSelectedFile(fd, fileFilter);
        		
        		if ( !(new File(selectedFile).exists()) ){
        			OptionDialog.showDialog(selectedFile + " does not exists. \n Please choose valid file. " , JOptionPane.ERROR_MESSAGE);
        		}
        		else {
        			return selectedFile;
        		}
        	}
        	//Cancel clicked break out
	    	else{
	    		break;
	    	}
    	}
    	return null;
    }

    private static String getSelectedFile(JFileChooser fd, AstrosoftFileFilter fileFilter){
    	
    	String selectedFile = ( fd.getSelectedFile(  ) ).getAbsolutePath(  );
        
        
        String fileExtn = fileFilter.getExtension();
        if ( !fileExtn.equals("*") && !selectedFile.endsWith(fileExtn)){
        	selectedFile = selectedFile + fileExtn;
		}
        
        return selectedFile;
    }
    public static int getFromFile( String filename, int row, int col ) {

        int value = 0;

        try {

            //File f = new File( getResourceURI("/resources/" + filename) );
            //FileInputStream fis = new FileInputStream( f );
            BufferedReader br =
                new BufferedReader( new InputStreamReader( FileOps.class.getResourceAsStream("/resources/" + filename) ) );
            int lineno = 1;

            while ( lineno++ < row )
                br.readLine(  );

            //String []values = br.readLine().split(",");
            String[] values = new String[30];
            java.util.StringTokenizer st =
                new java.util.StringTokenizer( br.readLine(  ), "," );
            int i = 0;

            while ( st.hasMoreTokens(  ) ) {

                values[i++] = st.nextToken(  );

            }

            value = Integer.parseInt( values[col - 1] );
            //fis.close(  );

        } catch ( IOException e ) {

            e.printStackTrace(  );

        } 
        return value;

    }
    
    public static void openDocument(String file) {
    	
    	Process p = null;
    	
    	String acrobatExecutable = AstroSoft.getPreferences().getAcrobatExecutable();
    	
    	if (acrobatExecutable == AstrosoftPref.defaultAcrobatExecutable){
    		throw new AstrosoftException("Acrobat Executable not initialized");
    	}
    	
    	String cmd[] = {acrobatExecutable ,file };
    	
    	//String cmd[] = { "open" ,file };
		try {
		     p = Runtime.getRuntime().exec(cmd);
		} catch (Exception e) {
			 log.log(Level.SEVERE, "Error executing command " + cmd[0] , e);
		     throw new AstrosoftException("Could not run acrobat, reason : " + e.getMessage());
		}
    	  
    	/*StringBuilder sb = new StringBuilder();  
    	BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
    	
    	try {
	    	String line = br.readLine();
	    	while(line != null){
	    		sb.append(line);
	    		line = br.readLine();
	    	}
    	}catch(IOException e){
    		log.log(Level.SEVERE, "Error in reading command  " + cmd[0] + " output ", e);
    	}finally{
    		
    		try {
    			br.close();
    		}catch(IOException ie){
    			log.log(Level.SEVERE, "Exception in closing command output " , ie);
    		}
    	}*/
    	
   
    }
    
    public static void main(String[] args) {
		/*String url = "RasiKuta.txt";
		
		System.out.println(FileOps.getFromFile(url,1,1));
		*/
    	
    	//System.out.println(openDocument("C:/Astrosoft/samples/astrosoft.pdf"));
    	
	}

    
}


