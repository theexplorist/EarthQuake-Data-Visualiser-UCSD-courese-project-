package guimodule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class LifeExpectancy extends PApplet {

	
	private UnfoldingMap map;
	Map<String, Float> lifeExpByCountry;
	List<Feature> countries;
	List<Marker> countryMarkers;
	public void setup(){
		
		size(400, 400, OPENGL);
		map = new UnfoldingMap(this, width/8, height/8, 6*width/8, 6*height/8, new OpenStreetMap.OpenStreetMapProvider());
		lifeExpByCountry = loadLifeExpectancy("LifeExpectancyWorldBankModule3.csv");
		MapUtils.createDefaultEventDispatcher(this, map);

				countries = GeoJSONReader.loadData(this, "countries.geo.json");
				countryMarkers = MapUtils.createSimpleMarkers(countries);
				map.addMarkers(countryMarkers);
				//System.out.println(countryMarkers);
				shadeWorld();
		
	}
	
	
	public void draw(){
		map.draw();
	}
	
	private Map<String, Float> loadLifeExpectancy(String file){
		
		Map<String, Float> map = new HashMap<String, Float>();
		String[] rows = loadStrings(file);
		for(String row : rows){
			String[] columns = row.split(",");
			if(columns.length == 6 && !columns[5].equals("..")){
				map.put(columns[4], Float.parseFloat(columns[5]));
			}
		}return map;
	}
	
	private  void shadeWorld(){
		for(Marker marker : countryMarkers){
			String countryId = marker.getId();
			System.out.println(countryId);
			if(lifeExpByCountry.containsKey(countryId)){
				float life = lifeExpByCountry.get(countryId);
				int colorl = (int)map(life, 40, 90, 10, 255);
				marker.setColor(color(255 - colorl, 100, colorl));
			}else{
				marker.setColor(color(150, 150, 150));
			}
		}
	}
}
