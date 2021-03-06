package entities;

import application.Fistmanagement;
import application.Main;
import helper.Loader;
import helper.Vector;
import javafx.scene.image.Image;

public class Battery extends Entity {

	private static final String[] src_img = { "battery/Batterie_V3f0_.png", "battery/Batterie_V3f1_.png",
			"battery/Batterie_V3f2_.png", "battery/Batterie_V3f3_.png", "battery/Batterie_V3f4_.png",
			"battery/Batterie_V3f5_.png", "battery/Batterie_V3f6_.png", "battery/Batterie_V3f7_.png",
			"battery/Batterie_V3f8_.png", "battery/Batterie_V3f9_.png", "battery/Batterie_V3f10_.png",
			"battery/Batterie_V3f11_.png", "battery/Batterie_V3f12_.png", "battery/Batterie_V3f13_.png",
			"battery/Batterie_V3f14_.png", "battery/Batterie_V3f15_.png" };
	private static final double bots = 50;
	private static final int HP_init = 9999999;
	private static final int drawingOrder = 99;

	private static final Vector speed_init = new Vector(0, 0);
	private static final Vector size_init = new Vector(1, 1);

	private Image[] images = new Image[src_img.length + 10];
	private double fill;

	public Battery(double x, double y, double fill) {
		super(x, y);
		this.LoadImages();
		this.fill = fill;
	}

	@Override
	public int getDrawingOrder() {
		return drawingOrder;
	}

	@Override
	public int getInitHP() {
		return HP_init;
	}

	@Override
	public Vector getInitSize() {
		return size_init;
	}

	@Override
	public Vector getInitSpeed() {
		return speed_init;
	}

	@Override
	public boolean isCollidable() {
		return false;
	}

	@Override
	public void LoadAssets() {

	}

	private void LoadImages() {
		for (int i = 0; i < src_img.length; i++) {
			images[i] = Loader.LoadImage(src_img[i]);
		}
	}

	@Override
	public void move(double dtime) {
		update();
	}

	@Override
	public void onAnimate(double time, double dtime) {
		super.onAnimate(time, dtime);
	}

	public void update() {
		try {
			this.img = Loader.LoadImage(
					src_img[(int) ((src_img.length - 1) * Main.game.bots.getRes() / Fistmanagement.resource_max)]);
		} catch (ArrayIndexOutOfBoundsException e) {
			// System.err.println(e.getMessage());
		}
	}

}
