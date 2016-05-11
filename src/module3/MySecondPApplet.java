package module3;

import processing.core.PApplet;
import processing.core.PImage;

public class MySecondPApplet extends PApplet{
	
	private String url = "http://www.missionbeachonline.com/images/mission-beach-thing-to-do.jpg";
	private PImage backgroundImage;
	
	
	public void setup(){
		
		size(200,200);
		
		
		
		backgroundImage = loadImage(url, "png");
		
	}
	
	public void draw(){
		
		backgroundImage.resize(0, height); //height is the instance variable in PImage ie. the image will change height corresponding to the canvas
		
		image(backgroundImage, 0, 0); // DISPLAY IMAGE
		int[] color = SunColorSec(second());
		fill(color[0],color[1],color[2]);
		//fill(255,209,0);
		ellipse(width/4, height/5, width/5, height/5);
		
	}

	private int[] SunColorSec(int second) {
		// TODO Auto-generated method stub
		int[] rgb = new int[3];
		float difffrom30 = Math.abs(30-second);
		float ratio = difffrom30/30;
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		
		return rgb;
		
		
		
	}

}
