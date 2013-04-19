/*
 * MuhurthaInput.java
 *
 * Created on October 30, 2005, 6:40 PM
 *
 * @author E. Rajasekar
 */

package app.astrosoft.ui.dlg;

import app.astrosoft.consts.DisplayConsts;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.Language;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.core.Muhurtha;
import app.astrosoft.ui.comp.RasiNakshathraChooser;
import app.astrosoft.ui.util.LocationGenerator;
import app.astrosoft.ui.util.UIConsts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;


public class MuhurthaInput extends AstrosoftDialog {
    
    private static final Dimension dlgSize = new Dimension(300,350);
    private static final Dimension nakChooserSize = new Dimension(dlgSize.width - 60,100);
    private static final Point origin = new Point (30,30);
    private static final Rectangle nakPanelLoc = new Rectangle(new Point(30,30), nakChooserSize);
    private static final Rectangle filterPanelLoc = new Rectangle(nakPanelLoc.x, nakPanelLoc.y + nakPanelLoc.height + 20, nakPanelLoc.width, nakPanelLoc.height);
    
    private JCheckBox chandraFilter;
    private JCheckBox nakFilter;
    private JButton okButton;
    private RasiNakshathraChooser nakPanel;
    
    /** Creates a new instance of MuhurthaInput */
    public MuhurthaInput(AstroSoft parent) {
        
        super( parent, "Muhurtha For", dlgSize);
        initComponents();
    }
    
    public void initComponents(){
        
        //Init controls
        nakPanel = new RasiNakshathraChooser(DisplayStrings.NAK_STR.toString(Language.ENGLISH), nakChooserSize);
        JPanel filterPanel = new JPanel(new AbsoluteLayout());
        chandraFilter = new JCheckBox(DisplayStrings.FILTER_BY_CHANDRA_STR.toString(), true);
        nakFilter = new JCheckBox(DisplayStrings.FILTER_BY_MUHURTHA_STR.toString());
        okButton = new JButton("Ok");
        filterPanel.setBorder(UIConsts.getTitleBorder(DisplayStrings.FILTER_STR.toString()));
        okButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                okButtonClicked();
            }
        });
        
        //Create filter panel
        LocationGenerator locGen = new LocationGenerator(origin, 0, 20);
        filterPanel.add(chandraFilter, new AbsoluteConstraints(locGen.getNextRow(), new Dimension(200,20)));
        filterPanel.add(nakFilter, new AbsoluteConstraints(locGen.getNextRow(), new Dimension(200,20)));
        
        //Add nak chooser and filter panel
        dlgPanel.add(nakPanel, new AbsoluteConstraints(nakPanelLoc.getLocation(), nakPanelLoc.getSize()));
        dlgPanel.add(filterPanel, new AbsoluteConstraints(filterPanelLoc.getLocation(), filterPanelLoc.getSize()));
        
        // Add button
        Dimension okButSize = new Dimension(60, 20);
        dlgPanel.add(okButton, new AbsoluteConstraints(new Point((dlgSize.width - okButSize.width) / 2 ,270), okButSize));
        
        // Add outer panel
        add(dlgPanel);
        setBackground(UIConsts.THEME_CLR);
        setVisible(true);
    }
    
    private void okButtonClicked(){
        Muhurtha m = new Muhurtha(AstroSoft.today, nakPanel.getSelectedRasi(), nakPanel.getSelectedNakshathra(), chandraFilter.isSelected(), nakFilter.isSelected(), 2);
        parent.displayMuhurtha(m);
        closeDialog();
    }
    
}

