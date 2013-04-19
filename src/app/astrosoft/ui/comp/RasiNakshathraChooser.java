/*
 * RasiNakshathraChooser.java
 *
 * Created on November 2, 2005, 8:27 PM
 *
 * @author E. Rajasekar.
 */

package app.astrosoft.ui.comp;

import app.astrosoft.consts.DisplayConsts;
import app.astrosoft.consts.DisplayFormat;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.Language;
import app.astrosoft.consts.Nakshathra;
import app.astrosoft.consts.Rasi;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.util.CallBack;
import app.astrosoft.ui.util.LocationGenerator;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.ui.util.UIUtil;
import app.astrosoft.util.Internalization;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;


public class RasiNakshathraChooser extends JPanel {

    // Defaults overriden by constructor
    private Dimension compSize;
    private String title;
     
    private Point origin;
    private Dimension labelSize;
    private Dimension comboSize;
    private int hgap;
    private int vgap;
    
    private JComboBox rasiCombo;
    private JComboBox nakCombo;
    
    private Font font = UIUtil.getFont(Font.BOLD, 10);
    
    public RasiNakshathraChooser() {
        this(null, new Dimension(300,100));
    }
    
    public RasiNakshathraChooser(String title){
        this(title, new Dimension(300,100));
    }
    
    public RasiNakshathraChooser(Dimension compSize){
        this(null, compSize);
    }
    
    public RasiNakshathraChooser(String title, Dimension compSize){
    	this.title = title;
        this.compSize = compSize;
        initComponents();
        setPreferredSize(compSize);
    }
     
    public void initComponents() {
        setLayout(new AbsoluteLayout());
        rasiCombo =  new JComboBox(Rasi.values());
    	nakCombo =  new JComboBox();
    	
    	populateNakCombo(Rasi.Mesha);
        rasiCombo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                populateNakCombo((Rasi)rasiCombo.getSelectedItem());
            }
        });
        
        rasiCombo.setFont(font);
        nakCombo.setFont(font);
        showPanel();
    }
    
    private void showPanel(){
        
        origin = new Point((compSize.width * 10)/100, (compSize.height * 20)/100);
        hgap = 0;
        vgap = ((compSize.height * 60)/100) - 40;
        labelSize = new Dimension((compSize.width * 80 * 40)/10000, 20);
        comboSize = new Dimension((compSize.width * 80 * 60)/10000, 20);
        LocationGenerator locGen = new LocationGenerator(origin, labelSize.width + hgap,labelSize.height + vgap);
        
        JLabel lRasiStr = new JLabel(DisplayStrings.RASI_STR.toString(Language.ENGLISH));
        JLabel lNakStr = new JLabel(DisplayStrings.NAK_STR.toString(Language.ENGLISH));
        //lRasiStr.setFont(font);
        //lNakStr.setFont(font);
        
        add(lRasiStr, new AbsoluteConstraints(locGen.getNextRow(), labelSize));
        add(rasiCombo, new AbsoluteConstraints(locGen.getNextColumn(), comboSize));
        add(lNakStr, new AbsoluteConstraints(locGen.getNextRow(), labelSize));
        add(nakCombo, new AbsoluteConstraints(locGen.getNextColumn(),comboSize));
        
        if (title != null){
            setBorder(UIConsts.getTitleBorder(title));
        }
        setVisible(true);
    }
    
    private void populateNakCombo(Rasi rasi){
        nakCombo.removeAllItems();
        Set<Nakshathra> naks = Nakshathra.ofRasi(rasi);
        for(Nakshathra nak : naks){
            nakCombo.addItem(nak);
        }
    }
    
    public Rasi getSelectedRasi(){
        return (Rasi)rasiCombo.getSelectedItem();
    }
    
    public Nakshathra getSelectedNakshathra(){
        return (Nakshathra)nakCombo.getSelectedItem();
    }
    
    public void setSelectedRasiNakshthra(Rasi rasi, Nakshathra nak){
    	rasiCombo.setSelectedItem(rasi);
    	nakCombo.setSelectedItem(nak);
    }
}


