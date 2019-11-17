package entities;

import application.Entity;
import application.Main;
import application.Vector;
import javafx.scene.image.Image;

public class Battery extends Entity{
	
	private static final String[] src_img = {"battery/Batterie_V3f0.png", "battery/Batterie_V3f1.png", "battery/Batterie_V3f2.png", "battery/Batterie_V3f3.png", "battery/Batterie_V3f4.png", "battery/Batterie_V3f5.png", 
											 "battery/Batterie_V3f6.png", "battery/Batterie_V3f7.png", "battery/Batterie_V3f8.png", "battery/Batterie_V3f9.png", "battery/Batterie_V3f10.png",
											 "battery/Batterie_V3f11.png", "battery/Batterie_V3f12.png", "battery/Batterie_V3f13.png", "battery/Batterie_V3f14.png", "battery/Batterie_V3f15.png"};
	private Image[] images = new Image[] {};
	private double fill;
	private static final double bots = 50;
	
	private static final int HP_init = 9999999;
	private static final int drawingOrder = -99;

	private static final Vector speed_init = new Vector(0, 0);
	private static final Vector size_init = new Vector(1, 1);

	public Battery(int x, int y, double fill) {
		super(x, y);
		this.LoadImages();
		this.fill = fill;
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
	
	private void LoadImages() {
		for(int i = 0; i<src_img.length-1; i++) {
			images[i] = Main.game.loader.LoadImage(src_img[i]);
		}
	}
	public void update() {
		this.img = Main.game.loader.LoadImage(src_img[(int) (this.fill/src_img.length)]);
	}

}
