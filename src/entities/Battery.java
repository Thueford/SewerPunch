package entities;

import application.Entity;
import application.Vector;
import javafx.scene.image.Image;

public class Battery extends Entity{
	
	private Image[] images = new Image[] {};

	public Battery(int x, int y, double fill) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getInitHP() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector getInitSpeed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector getInitSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCollidable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getDrawingOrder() {
		// TODO Auto-generated method stub
		return -99;
	}

	@Override
	public void LoadAssets() {
		
		
		
	}

}
