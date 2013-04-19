/**
 * CompactibilityInputDialog.java
 * Created On 2006, Jun 3, 2006 3:26:28 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.dlg;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import app.astrosoft.beans.BirthData;
import app.astrosoft.consts.DisplayStrings;
import app.astrosoft.consts.Language;
import app.astrosoft.consts.Nakshathra;
import app.astrosoft.consts.Rasi;
import app.astrosoft.core.Horoscope;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.comp.BirthDataPanel;
import app.astrosoft.ui.comp.RasiNakshathraChooser;
import app.astrosoft.ui.util.SpringUtilities;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.ui.util.UIUtil;

public class CompactibilityInputDialog extends AstrosoftDialog {

	private static final Dimension dlgSize = new Dimension(660,420);
	private static final Dimension panelSize = new Dimension(dlgSize.width - 10 , dlgSize.height );

	private static final Dimension couplePanelSize = new Dimension(((int)panelSize.width / 2)  , panelSize.height - 90);


	private JTabbedPane tabbedPane;

	private BirthDataPanel boyDataPanel;
	private BirthDataPanel girlDataPanel;

	private RasiNakshathraChooser bRasiNakChooser;

	private RasiNakshathraChooser gRasiNakChooser;

	private JButton horOkButton = new JButton(DisplayStrings.OK_STR.toString());
	private JButton starOkButton = new JButton(DisplayStrings.OK_STR.toString());

	private JTextField boyName = new JTextField(14);
	private JTextField girlName = new JTextField(14);

	/*private boolean hasBirthData = false;
	private boolean hasStarData = false;

	private String bName;
	private String gName;

	private Rasi boyRasi;
	private Rasi girlRasi;

	private Nakshathra boyNak;
	private Nakshathra girlNak;

	private BirthData boyBirthData;
	private BirthData girlBirthData;


	private CompactibilityInputDialog(AstroSoft parent, boolean hasBirthData, boolean hasStarData){
		super( parent, "Enter Birth Details", dlgSize );
		this.hasBirthData= hasBirthData;
		this.hasStarData = hasStarData;
		initComponents();
		addComponents();
		setVisible(true);

	}*/

	public CompactibilityInputDialog(AstroSoft parent){
		super( parent, "Enter Birth Details", dlgSize );
		initComponents();
		addComponents();
		setVisible(true);
	}

	public CompactibilityInputDialog(AstroSoft parent, String boyName, String girlName, Rasi boyRasi, Rasi girlRasi, Nakshathra boyNak, Nakshathra girlNak){
		this(parent);

		this.boyName.setText(boyName);
		this.girlName.setText(girlName);

		bRasiNakChooser.setSelectedRasiNakshthra(boyRasi, boyNak);
		gRasiNakChooser.setSelectedRasiNakshthra(girlRasi, girlNak);
		tabbedPane.setSelectedIndex(1);
	}

	public CompactibilityInputDialog(AstroSoft parent, BirthData boyBirthData, BirthData girlBirthData){
		this(parent);
		boyDataPanel.setBirthData(boyBirthData);
		girlDataPanel.setBirthData(girlBirthData);

	}

	private void initComponents() {

		tabbedPane = new JTabbedPane();


		horOkButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {

				Horoscope boyHoroscope = new Horoscope(boyDataPanel.getBirthData());
				Horoscope girlHoroscope = new Horoscope(girlDataPanel.getBirthData());
				parent.displayCompactibility(boyHoroscope, girlHoroscope);
				closeDialog();
			}

		});

		starOkButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {

				Rasi bRasi = bRasiNakChooser.getSelectedRasi();
		        Rasi gRasi = gRasiNakChooser.getSelectedRasi();

		        Nakshathra bNak = bRasiNakChooser.getSelectedNakshathra();
		        Nakshathra gNak = gRasiNakChooser.getSelectedNakshathra();

		        parent.displayCompactibility(boyName.getText(), girlName.getText(), bRasi, gRasi, bNak, gNak);
		        closeDialog();
			}
		});
	}

	private void addComponents() {

		dlgPanel.setLayout(new FlowLayout());

		tabbedPane.add(DisplayStrings.HOROSCOPE_STR.toString(), createHoroscopePanel());
		tabbedPane.add(DisplayStrings.STAR_STR.toString(), createStarPanel());

		dlgPanel.add(tabbedPane);
		//dlgPanel.add(okButton);
		add(dlgPanel);
		setBackground(UIConsts.THEME_CLR);
	}

	private JPanel createHoroscopePanel(){

		JPanel p = new JPanel(new BorderLayout());

		boyDataPanel = new BirthDataPanel(couplePanelSize, DisplayStrings.BOY_DATA_STR.toString());
		girlDataPanel = new BirthDataPanel(couplePanelSize, DisplayStrings.GIRL_DATA_STR.toString());

		p.add(boyDataPanel, BorderLayout.WEST);
		p.add(girlDataPanel, BorderLayout.EAST);

		//boyDataPanel.setPreferredSize(couplePanelSize);
		//girlDataPanel.setPreferredSize(couplePanelSize);

		JPanel okButtonPanel = new JPanel();
		okButtonPanel.add(horOkButton);
		p.add(okButtonPanel, BorderLayout.PAGE_END);

		UIUtil.setPanelBackground(p, UIConsts.THEME_CLR);
		return p;
	}

	private JPanel createStarPanel(){

		JPanel p = new JPanel(new BorderLayout());
		bRasiNakChooser = new RasiNakshathraChooser(new Dimension(couplePanelSize.width - 12 ,couplePanelSize.height -150));
		gRasiNakChooser = new RasiNakshathraChooser(new Dimension(couplePanelSize.width - 12,couplePanelSize.height - 150));


		JPanel boyPanel = new JPanel(new BorderLayout());
		JPanel girlPanel = new JPanel(new BorderLayout());

		JPanel bNamePanel = new JPanel();
		JPanel gNamePanel = new JPanel();


		bNamePanel.add(new JLabel("  " + DisplayStrings.NAME_STR.toString(Language.ENGLISH) + "                      "));
		bNamePanel.add(boyName);

		boyPanel.add(bNamePanel, BorderLayout.PAGE_START);
		boyPanel.add(bRasiNakChooser, BorderLayout.CENTER);


		gNamePanel.add(new JLabel("  " + DisplayStrings.NAME_STR.toString(Language.ENGLISH)+ "                      "));
		gNamePanel.add(girlName);

		//SpringUtilities.makeGrid(bNamePanel, 1, 2, 5,5,0,0);
		//SpringUtilities.makeGrid(gNamePanel, 1, 2, 5,5,0,0);

		girlPanel.add(gNamePanel, BorderLayout.PAGE_START);
		girlPanel.add(gRasiNakChooser, BorderLayout.CENTER);

		boyPanel.setBorder(UIConsts.getTitleBorder(DisplayStrings.BOY_DATA_STR));
		girlPanel.setBorder(UIConsts.getTitleBorder(DisplayStrings.GIRL_DATA_STR));

		p.add(boyPanel, BorderLayout.WEST);
		p.add(girlPanel, BorderLayout.EAST);

		JPanel okButtonPanel = new JPanel();
		okButtonPanel.add(starOkButton);
		p.add(okButtonPanel, BorderLayout.PAGE_END);
		UIUtil.setPanelBackground(p, UIConsts.THEME_CLR);
		return p;
	}

}




