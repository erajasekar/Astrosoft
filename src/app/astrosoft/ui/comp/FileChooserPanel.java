/**
 * FileChooserPanel.java
 * Created On 2007, Sep 4, 2007 3:48:40 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import app.astrosoft.pref.AstrosoftPref;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.util.SpringUtilities;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.util.AstrosoftFileFilter;
import app.astrosoft.util.FileOps;

public class FileChooserPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField filePath = new JTextField();
	private JButton browse = new JButton("Browse");
	
	private String defaultPath;
	private String title;
	private FileOps.FileDialogMode mode;
	private AstrosoftFileFilter fileFilter; 
	
	
	public FileChooserPanel(Dimension size, String defaultPath, String title, FileOps.FileDialogMode mode, AstrosoftFileFilter fileFilter){
		
		this.defaultPath = defaultPath;
		this.title = title;
		this.mode = mode;
		this.fileFilter = fileFilter;
		setLayout(new SpringLayout());
		setPreferredSize(size);
		addComponents();
	}
	
	
	public FileChooserPanel(Dimension size, String defaultPath, String title, FileOps.FileDialogMode mode){
		this(size,defaultPath,title,mode,  AstrosoftFileFilter.ALL_FILES);
	}

	private void addComponents() {
		
		if (defaultPath != null) {
			filePath.setText(defaultPath);
		}
		
		add(filePath);
		add(browse);
		SpringUtilities.makeCompactGrid(this, 1, 2, 5,5,5,5);
		
		browse.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				String selectedFile = FileOps.openFileDialog(FileChooserPanel.this, mode, fileFilter);
				
				if (selectedFile != null) {
					filePath.setText(selectedFile);
				}
			}
			
		});
		setBorder(UIConsts.getTitleBorder(title));
	}
	
	public String getFilePath() {
		return filePath.getText();
	}
	
	public void setFilePath(String path){
		filePath.setText(path);
	}

}
