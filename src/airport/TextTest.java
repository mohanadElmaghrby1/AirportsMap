package airport;

import java.awt.TextField;

import processing.core.PApplet;

public class TextTest extends PApplet{

	TextField txt = new TextField("mohanad");
	
	@Override
	public void setup() {
		size(600,400);
		add(txt);
		
	}
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
		txt.setLocation(0, 0);
	}
}
