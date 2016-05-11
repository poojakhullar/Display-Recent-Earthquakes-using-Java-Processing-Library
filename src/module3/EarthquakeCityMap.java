package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MarkerManager;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.MarkerFactory;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);
	    
			
	    // The List you will populate with new SimplePointMarkers
	  
	    
	    
	    

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	   
	    List<Marker> markers = new ArrayList<Marker>();
	    int yellow = color(255, 255, 0);
        int red = color(255, 0, 0);
        int blue = color(0, 0, 255); 
	    //PointFeature feature = null;
	    SimplePointMarker mark = null;
	    for(PointFeature f1 : earthquakes){
	    	Location l1 = f1.getLocation();
	    	mark = createMarker(f1);  // an object of simplepointmarker can be equal to createMarkermethod 
	    	mark.setRadius(10.0f);
	    	
	    	Object magObj = f1.getProperty("magnitude");
	    	float mag = Float.parseFloat(magObj.toString());
	    	if(mag >= 5.0f){
	    		mark.setColor(red);
	    	}
	    	else if(mag >= 4.0f){
	    		mark.setColor(yellow);
	    	}
	    	else{
	    		mark.setColor(blue);
	    	}
	    	
			   markers.add(mark); // markers list can add simple point marker object
	    	   
	    	
	    	
	    }
	    
	    
	    
	    
	    
	    System.out.println(markers);
		   map.addMarkers(markers);
		   
	    
	    
	    // These print statements show you (1) all of the relevant properties 
	    // in the features, and (2) how to get one property and use it
	    if (earthquakes.size() > 0) {
	    	PointFeature f = earthquakes.get(0);
	    	System.out.println(f.getLocation());
	    	System.out.println(f.getProperties());
	    	Object magObj = f.getProperty("magnitude");
	    	float mag = Float.parseFloat(magObj.toString());
	    	System.out.println(mag);
	    	
	    	
	    	// PointFeatures also have a getLocation method
	    }
	    
	   
	    
	    // Here is an example of how to use Processing's color method to generate 
	    // an int that represents the color yellow.  
	    
	    
	    //TODO: Add code here as appropriate
	}
		
	// A suggested helper method that takes in an earthquake feature and 
	// returns a SimplePointMarker for that earthquake
	// TODO: Implement this method and call it from setUp, if it helps
	public SimplePointMarker createMarker(PointFeature feature)
	{
		//List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
		// finish implementing and use this method, if it helps.
		//Location l1 = null;
		//for(PointFeature f1 : earthquakes){
	    	//l1 = f1.getLocation();
	    	//System.out.println(l1);
	    	
	  //}
		
		
		
		return new SimplePointMarker(feature.location);
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	// TODO: Implement this method to draw the key
	private void addKey() 
	{	
		// Remember you can use Processing's graphics methods here
		fill(255, 250, 240); //color white
        rect(25, 50, 150, 250); // (x location of upper left corner, y location of upper left corner, width, height)

        fill(0); //needed for text to appear, sets the color to fill shapes, takes in an int rgb value
        textAlign(LEFT, CENTER);
        textSize(12);
        text("Earthquake Key", 50, 75); //heading of key, takes (string, float x, and float y)

        fill(color(255, 0, 0)); //red
        ellipse(50, 125, 15, 15); //(x coordinate, y coordinate, width, height)   )
        fill(color(255, 255, 0)); //yellow 
        ellipse(50, 175, 10, 10);
        fill(color(0, 0, 255));
        ellipse(50, 225, 5, 5);

        fill(0, 0, 0);
        text("5.0+ Magnitude", 75, 125);
        text("4.0+ Magnitude", 75, 175); // same y coordinate but different x so it could appear right beside marker
        text("Below 4.0", 75, 225);
    
	
	}
}
