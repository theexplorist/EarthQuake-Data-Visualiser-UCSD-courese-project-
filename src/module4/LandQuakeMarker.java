package module4;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

/** Implements a visual marker for land earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public class LandQuakeMarker extends EarthquakeMarker {
	

	public LandQuakeMarker(PointFeature quake) {
		
		// calling EarthquakeMarker constructor
		super(quake);
		
		// setting field in earthquake marker
		isOnLand = true;
	}


	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		//System.out.println("OFF");
		// Draw a centered circle for land quakes
		// DO NOT set the fill color here.  That will be set in the EarthquakeMarker
		// class to indicate the depth of the earthquake.
		// Simply draw a centered circle.
		//System.out.println(getAge());
		if(getAge().equals("Past Day")){
			
			pg.pushStyle();
			//System.out.println("**");
		
			 pg.fill(200, 200, 0, 100);
			pg.ellipse(x, y, 4*radius, 3*radius);
			pg.fill(255, 100);
			pg.ellipse(x, y , 2*radius, 2*radius);
			pg.fill(0, 0, 0);
			pg.line(x-5, y-5, x+5, y+5); 

			pg.line(x-5, y+5, x+5, y-5);
			pg.popStyle();
		}else
		pg.ellipse(x, y, 2*radius, 2*radius);
		
		// HINT: Notice the radius variable in the EarthquakeMarker class
		// and how it is set in the EarthquakeMarker constructor
		
		// TODO: Implement this method
		
		
	}
	

	// Get the country the earthquake is in
	public String getCountry() {
		return (String) getProperty("country");
	}



		
}