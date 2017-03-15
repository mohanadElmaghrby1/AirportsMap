package airport;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PGraphics;

/**
 * a class to represent airport marker on map 
 * @author mohanad
 *
 */
public class AirportMarker extends SimplePointMarker {
	
	/**
	 * 
	 * @param airport : airport to  represent
	 */
	public AirportMarker(Feature airport ){
		super(((PointFeature) airport).getLocation() , airport.getProperties());
	}

	
	/**
	 * this mathod to control the marker design and color 
	 */
	@Override
	public void draw(PGraphics pg, float x, float y) {
		pg.fill(250,0,0);
		pg.ellipse(x, y, 5, 5);

	}
	
	public void showTitle (PGraphics pg , float x , float y ){
		
	}
}
