package module3;

import processing.core.*;


public class MyDisplay extends PApplet {
	
	private String url = "http://www.missionbeachonline.com/images/mission-beach-thing-to-do.jpg";
	private PImage backgroundImage;
	
	
	
public void setup(){
		
		size(400,400);
		
		
		background(200,200,200);
		
	}
	
	
	public void draw(){
		
		//backgroundImage.resize(0, height); //height is the instance variable in PImage ie. the image will change height corresponding to the canvas
		
		//image(backgroundImage, 0, 0);
		fill(255,209,0);
		ellipse(200, 200, 390, 390);
		fill(0,0,0);
		ellipse(120, 130, 50, 70);
		ellipse(280, 130, 50, 70);
		arc(200, 280, 75, 75, 0, PI);
		
		
	}

}
