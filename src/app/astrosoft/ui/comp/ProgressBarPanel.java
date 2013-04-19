/**
 * ProgressBarPanel.java
 * Created On 2007, Sep 4, 2007 12:57:06 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import app.astrosoft.ui.util.UIConsts;

public class ProgressBarPanel extends JPanel {
	
	private static final Logger log = Logger.getLogger(ProgressBarPanel.class.getName());

	private JProgressBar pbar;
	private FutureTask<Object> task;
	private String taskName;
	private ProgressListener listener;
	
	private class Worker extends SwingWorker<Void, Void>{
		
		@Override
        public Void doInBackground() {
			try {
				task.get();
				taskCompleted();
			} catch (Exception e){
				
				log.log(Level.SEVERE, "Exception in " + taskName , e);
			}
			
			return null;
		}
	
	}
	
	public ProgressBarPanel(Dimension size, FutureTask<Object> task, String taskName, ProgressListener listener){
		
		this.task = task;
		this.taskName = taskName;
		this.listener = listener;
		addComponents();
		setPreferredSize(size);
		
		new Worker().execute();
		
	}

	private void addComponents() {
		
		setLayout(new BorderLayout());
		pbar = new JProgressBar();
		pbar.setIndeterminate(true);
		pbar.setStringPainted(true);
		pbar.setString(taskName + "...");
		
		add(BorderLayout.CENTER, pbar);
	}
	
	private void taskCompleted() {
		
		pbar.setString("Completed!");
		pbar.setIndeterminate(false);
		listener.completed();
		
	}
	
}
