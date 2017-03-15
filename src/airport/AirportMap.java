package airport;

import java.awt.TextField;
import java.util.*;

import javax.swing.JOptionPane;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.*;


/**
 * program to visualise the all world airports on the map and searching for a specific
 * city for having airport or no 
 * @author mohanad
 *
 */
public class AirportMap extends PApplet{
	
	String CityToSearch= "" ;
	
	// the map
	UnfoldingMap map ;
	
	// the list of all airports
	private List<Marker> airportList ;
	
	//the routes of the world 
	public List<Marker> routeList ;
	
	TextField textField = new TextField("Text", 16);

	
	@Override
	public void setup() {
		//setting size of window 
		size(850 ,650 , OPENGL);
		
		 add(textField);
		
		//setting map and default events 
		map = new UnfoldingMap(this, 100, 80, 700, 550, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this , map );
		// zoom map while
		map.zoomToLevel(2);
		
		// get features from airport data
		List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");
				
		// create hash map of airpoert with key  integer and value location 
		HashMap<Integer , Location> airports = new HashMap<Integer, Location>();
		
		//create array list of marker
		airportList= new ArrayList<Marker>();
		
		//create list of marker
		for (PointFeature feature : features){
			
			AirportMarker m = new AirportMarker(feature);
			m.setRadius(5);
			
			airportList.add(m);
			airports.put(Integer.parseInt(feature.getId()), m.getLocation());
			
			
		}
		
		background(0);
		map.addMarkers(airportList);
		

	}
	
	@Override
	public void draw() {
		map.draw();
		addkey();
		fill(0 ,0 ,0 );
		textSize(15);
		textField.setLocation(0, 0);
		textField.setVisible(true);
		text(CityToSearch, 190 , 40);
	}

	@Override
	public void keyTyped() {
		if (key >= 'A' || key <= 'Z' || key>='a' || key<='z')
			CityToSearch+=key;
		else CityToSearch=CityToSearch.substring(0,CityToSearch.length()-2);
	}
	
	@Override
	public void mouseClicked() {
		if (mouseX > 300 && mouseX < 370 && mouseY > 20 && mouseY <50){
			searchForAirport(CityToSearch);
		}
	}
	
	private void addkey(){
		
		fill(250 , 0 ,0);
		rect(300, 20, 70, 30);
		
		fill(0 , 0 , 0);
		textSize(16);
		text("search", 310, 40 );
		
		fill(250 ,250 ,250 );
		rect(180, 20, 110, 30);
	}
	
	/**
	 * search for city which contain airport or no 
	 * @param city
	 */
	private void searchForAirport (String city ){
		boolean found =false;
		System.out.println("searching for "+city);

		
//		for (Marker m : airportList){
//			
//			System.out.println(" list ="+m.getStringProperty("city"));
//			
//		}
//		
		
		for (Marker m : airportList ){
			//System.out.println("cityes = "+m.getStringProperty("city"));	
			String citys=m.getStringProperty("city").replaceAll("\"", "");
			
			if (citys.equalsIgnoreCase(city))
			{
				found=true;
				System.out.println(" okay this city have airport ");
				
				hideMarker(m);
				return;
			}
			
		}
		
		System.out.println("sorry this city have no airport ");
				
	}
	
	private void hideMarker (Marker city){
		for (Marker mhide : airportList ){
			if (!city.equals(mhide))
				mhide.setHidden(true);
		}
		
		map.addMarker((Marker) airportList);
	}

}
