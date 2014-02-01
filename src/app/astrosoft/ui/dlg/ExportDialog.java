/**
 * ExportDialog.java
 * Created On 2007, Sep 4, 2007 1:07:44 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.dlg;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.FutureTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import app.astrosoft.exception.AstrosoftException;
import app.astrosoft.ui.AstroSoft;
import static app.astrosoft.ui.AstroSoft.getPreferences;
import app.astrosoft.ui.comp.FileChooserPanel;
import app.astrosoft.ui.comp.ProgressBarPanel;
import app.astrosoft.ui.comp.ProgressListener;
import static app.astrosoft.ui.dlg.OptionDialog.showDialog;
import app.astrosoft.ui.util.SpringUtilities;
import static app.astrosoft.ui.util.SpringUtilities.makeCompactGrid;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.util.FileOps;
import static app.astrosoft.util.FileOps.openDocument;

public class ExportDialog extends AstrosoftDialog{
	
	private static final Dimension dlgSize = new Dimension(400,260);
	
	private static final Dimension acrobatSize = new Dimension(dlgSize.width - 100,50);
	private static final Dimension progressSize = new Dimension(dlgSize.width - 100,15);

	private FutureTask<Object> task;
	
	private JButton open = new JButton("Open");
	private JButton close = new JButton("Close");
	
	private String outputFile;

	private FileChooserPanel acrobatPanel;
	
	public ExportDialog(AstroSoft parent, String title, FutureTask<Object> task, String outputFile){
		super(parent, title, dlgSize);
		this.task = task;
		this.outputFile = outputFile;
		addComponents();
		setVisible(true);
		
	}
	
	private void addComponents(){
		
		dlgPanel.setLayout(new SpringLayout());
		
		ProgressListener listener = this::exportCompleted;
		
		ProgressBarPanel pbarPanel = new ProgressBarPanel(progressSize, task, "Exporting", listener);
		dlgPanel.add(pbarPanel);
		
		acrobatPanel = new FileChooserPanel(acrobatSize,getPreferences().getAcrobatExecutable(), "Acrobat Executable", FileOps.FileDialogMode.OPEN);
		dlgPanel.add(acrobatPanel);
		
		JPanel p = new JPanel();
		
		p.add(open);
		p.add(close);
		
		dlgPanel.add(p);
		open.setEnabled(false);
		close.setEnabled(false);
		
		makeCompactGrid(dlgPanel, 3, 1, 30,30,30,30);
		
		add(dlgPanel);
			
	    setBackground(UIConsts.THEME_CLR);
	    
	    addListeners();
	}

	private void addListeners() {
		open.addActionListener((ActionEvent e) -> {
                    openClicked();
        });
		
		close.addActionListener((ActionEvent e) -> {
                    dispose();
        });
		
	}

	protected void exportCompleted() {
		
		open.setEnabled(true);
		close.setEnabled(true);
	}

	protected void openClicked() {
		
		try {
			getPreferences().setAcrobatExecutable(acrobatPanel.getFilePath());
			openDocument(outputFile);
		}catch(AstrosoftException e){
			
			showDialog(e.getMessage(),JOptionPane.ERROR_MESSAGE);
			//JOptionPane.showMessageDialog (this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
}
