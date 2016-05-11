package module3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.*;

public class LifeExpectancy extends PApplet{
	UnfoldingMap map;
	Map<String, Float> LifeExpectancyByCountry;
	List<Feature> countries;
	List<Marker> countryMarkers;
	
	public void setup(){
		size(800,600,OPENGL);
		
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map); // initialize default events such as keyboard interaction
		// method which will read the values and populate inside map datatype
		LifeExpectancyByCountry = loadLifeExpectancyFROMCSV("data/LifeExpectancyWorldBank.csv");
	    // use helper methods to import the data from file
		countries = GeoJSONReader.loadData(this, "data/cou.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);  //gets the data from the file and creates markers for it
		map.addMarkers(countryMarkers);
		shadeCountries();
		
	}
	
	public void draw(){
		map.draw();
		
	}

	
	private void shadeCountries() {
		// TODO Auto-generated method stub
		
		//get the countryid from the marker and if the country id is same to that in lifeexpectancy then get life expectancy
		for (Marker marker : countryMarkers) {
			String countryId = marker.getId();
			
			if(LifeExpectancyByCountry.containsKey(countryId)){
				float lifeExp = LifeExpectancyByCountry.get(countryId);
				//transfer the expectancy to color
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
				marker.setColor(color(255-colorLevel, 100, colorLevel));
				
				}
			else{
				// shade will be grey when we dont have data corresponding to a country
				marker.setColor(color(150,150,150));// shade will be grey when we dont have data corressponding to a country
			}
				
		}
		
	}

	private Map<String, Float> loadLifeExpectancyFROMCSV(String string) {
		// TODO Auto-generated method stub
		Map<String, Float> lifeExp = new HashMap<String, Float>();
		String[] rows = loadStrings("data/LifeExpectancyWorldBank.csv");
		for (String row : rows){
			String[] columns = row.split(",");
			
				float value = Float.parseFloat(columns[5]);// converting string type value to float
				lifeExp.put(columns[4], value);
		
		}
		
		// in order to talk about location with its properties we need to look into feature class in unfolding
		
		return lifeExp;
	}

	
}
