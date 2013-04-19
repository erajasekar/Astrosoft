/**
 * PlaceChooser.java
 * Created On 2006, Mar 11, 2006 3:35:00 PM
 * @author E. Rajasekar
 */

package app.astrosoft.ui.comp;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import app.astrosoft.beans.Place;
import app.astrosoft.beans.Place.Direction;
import app.astrosoft.beans.Place.Location;
import app.astrosoft.ui.AstroSoft;
import app.astrosoft.ui.util.SpringUtilities;
import app.astrosoft.ui.util.UIConsts;
import app.astrosoft.ui.util.UIUtil;
import app.astrosoft.util.AstrosoftTimeZone;
import app.astrosoft.util.PlaceFinder;
import app.astrosoft.util.Timer;

public class PlaceChooser extends JPanel {

	private JLabel l_timezone = new JLabel("Time Zone");
	private JLabel l_place = new JLabel("Place");
	private JLabel l_latitude = new JLabel("Latitude");
	private JLabel l_longitude = new JLabel("Longitude");
	private JButton searchButton = new JButton();

	private JTextField latitude_deg = new JTextField(4);
	private JTextField latitude_min = new JTextField(4);
	private JTextField longitude_deg = new JTextField(4);
	private JTextField longitude_min = new JTextField(4);
	private JComboBox placeCombo;
	private final JComboBox timeZoneCombo;
	private JComboBox dir_ns;
	private JComboBox dir_ew;
	private String city;
	private String state;
	private String country;
	private boolean showTitle;
	private Dimension size;
	Timer t;

	public PlaceChooser(Dimension size, boolean showTitle) {

		this.showTitle = showTitle;
		timeZoneCombo = new JComboBox(AstrosoftTimeZone.availableTimeZones());
		initComponents();
		addComponents();
		this.size = size;
		setPreferredSize(size);
	}

	private void addComponents() {
		JPanel placeComp = new JPanel(new SpringLayout());

		searchButton.setPreferredSize(UIConsts.BUTTON_ICON_SIZE);
		placeComp.add(placeCombo);
		placeComp.add(searchButton);
		

		//SpringUtilities.makeCompactGrid(placeComp,1,2, 20,20,10,10);

		JPanel latitudeComp = new JPanel(new SpringLayout());

		latitudeComp.add(latitude_deg);
		latitudeComp.add(latitude_min);
		latitudeComp.add(dir_ns);

		JPanel longitudeComp = new JPanel(new SpringLayout());

		longitudeComp.add(longitude_deg);
		longitudeComp.add(longitude_min);
		longitudeComp.add(dir_ew);

		SpringUtilities.makeCompactGrid(placeComp, 1, 2, 5,5,5,5);
		SpringUtilities.makeCompactGrid(latitudeComp, 1, 3, 5,5,10,10);
		SpringUtilities.makeCompactGrid(longitudeComp, 1, 3, 5,5,10,10);

		add(l_place);
		add(placeComp);
		add(l_latitude);
		add(latitudeComp);
		add(l_longitude);
		add(longitudeComp);
		add(l_timezone);
		add(timeZoneCombo);
		
		SpringUtilities.makeCompactGrid(this, 4, 2, 5,5,10,10);

		
	}

	public PlaceChooser() {
		this(new Dimension(350, 200), true);
	}

	private void initComponents() {

		placeCombo = new JComboBox();
		//placeCombo.setSize(new Dimension(60,10));
		//placeCombo.setPreferredSize(new Dimension(100,10));
		placeCombo.setEditable(true);
		
		//t = new Timer();
		//t.print("AT 1");
		
		//t.print("AT 2");

		dir_ew = new JComboBox(Direction.EW().toArray());
		dir_ns = new JComboBox(Direction.NS().toArray());
		
		if (showTitle) {
			setBorder(UIConsts.getTitleBorder("Place"));
		}
		
		setLayout(new SpringLayout());
		//t.print("AT 2.1");
		Place defPlace = AstroSoft.getPreferences().getPlace();
		//t.print("AT 2.2");
		addPlaces(new Place[]{defPlace});
		//placeCombo.addItem("New City");
		
		//t.print("AT 3");
		
		searchButton.setEnabled(false);
		
		ActionListener searchListener = new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				searchClicked();
			}
		};
		searchButton.addActionListener(searchListener);
		Icon searchIcon = UIUtil.createImageIcon("Loc_Search");
		searchButton.setIcon(searchIcon);
		
		//t.print("AT 4");
		
		placeCombo.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
				Object item = e.getItem();
				if (item instanceof Place){
					setSelectedPlace((Place)item);
				}
			}
		});
		
		placeCombo.getEditor().getEditorComponent().addKeyListener(new KeyAdapter(){
			
			@Override
			public void keyReleased(KeyEvent e) {
				String text = ((JTextField)placeCombo.getEditor().getEditorComponent()).getText();
				if (text.length() > 0){
					searchButton.setEnabled(true);
				}else{
					searchButton.setEnabled(false);
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				if (e.getKeyChar() == KeyEvent.VK_ENTER){
					searchClicked();
				}
			}
		});
		
		//t.print("AT 5");
	}

	public Place getSelectedPlace(){
		Location latitude = new Location(latitude_deg.getText(), latitude_min.getText(), (Direction)dir_ns.getSelectedItem());
		Location longitude = new Location(longitude_deg.getText(), longitude_min.getText(), (Direction)dir_ew.getSelectedItem());
		String timeZoneId = ((AstrosoftTimeZone)timeZoneCombo.getSelectedItem()).id();
		Place p;
		
		String[] placeText = placeCombo.getSelectedItem().toString().split(",");
		
		String st = null;
		String coun = null;
		if (placeText.length > 1){
			st = placeText[1];
			if (placeText.length > 2){
				coun = placeText[2];
			}
		}
		p = new Place(placeText[0], st, coun, latitude,longitude,timeZoneId);
	
		return p;
	}
	
	public void setSelectedPlace(final Place p){
		
		//t.print("AT 2.2.3.1");
		
		placeCombo.setSelectedItem(p);
		
		//t.print("AT 2.2.3.2");
		
		city = p.city();
		state = p.state();
		country = p.country();
		
		//t.print("AT 2.2.3.3");
		
		Location latitude = p.latitudeLocation();
		Location longitude = p.longitudeLocation();
		
		//t.print("AT 2.2.3.4");
		
		latitude_deg.setText(String.valueOf(latitude.deg()));
		latitude_min.setText(String.valueOf(latitude.min()));
		dir_ns.setSelectedItem(latitude.dir());
		
		//t.print("AT 2.2.3.5");
		
		longitude_deg.setText(String.valueOf(longitude.deg()));
		longitude_min.setText(String.valueOf(longitude.min()));
		dir_ew.setSelectedItem(longitude.dir());
		
		//t.print("AT 2.2.3.6");
		
		new Thread(new Runnable(){

			public void run() {
				timeZoneCombo.setSelectedItem(p.astrosoftTimeZone());
				System.out.println("done");
			}
			
		}).start();
		
		
		//t.print("AT 2.2.3.7");
	}
	
	private void addPlaces(Place[] places) {
		//t.print("AT 2.2.1");
		addPlaces(Arrays.asList(places));
		//t.print("AT 2.3");
	}

	public void addPlaces(List<Place> places){
		placeCombo.removeAllItems();
		
		//t.print("AT 2.2.2");
		
		for(Place place : places){
			placeCombo.addItem(place);
		}
		//t.print("AT 2.2.3");
		if (places.size() > 0){
			setSelectedPlace(places.get(0));
		}
		//t.print("AT 2.2.4");
	}
	
	private void searchClicked() {
		
		Object query = placeCombo.getSelectedItem();
		
		//System.out.println("query->" + query);
		if (query != null) {
			List<Place> result = PlaceFinder.findPlace(query.toString()); 
			addPlaces(result);
			placeCombo.showPopup();
		}
	}
}

