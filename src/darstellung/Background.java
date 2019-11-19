package darstellung;

import application.Main;
import entities.Entity;
import helper.Vector;

public class Background extends Entity {
	private static final String[] src_img = { "bg1.png", "bg2.png", "bg3.png", "bg4.png" };
	private static final String src_dieanim = "";

	private static final int HP_init = 1;
	private static final int drawingOrder = -100;
	private static final Vector speed_init = new Vector(0, 0.5);
	private static final Vector size_init = new Vector(13, 12);

	private static final boolean collidable = false;

	public Background(int x, int y) {
		super(x, y);
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
		return size_init.copy();
	}

	@Override
	public Vector getInitSpeed() {
		return speed_init.copy();
	}

	@Override
	public boolean isCollidable() {
		return collidable;
	}

	@Override
	public void LoadAssets() {
		this.img = Main.game.loader.LoadImage(src_img[Main.game.ran.nextInt(src_img.length)]);
	}

	@Override
	public void onAnimate(double time, double dtime) {
		super.onAnimate(time, dtime);
		if (this.y > 10) {
			this.y = -10;
			this.LoadAssets();
		}
	}

	@Override
	public void onCollide(Entity e) {

	}

	@Override
	public void onDie() {

	}

	@Override
	public void onUncollide() {
		this.startWalking();
	}
}
