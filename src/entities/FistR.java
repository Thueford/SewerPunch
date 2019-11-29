package entities;

import application.Fistmanagement;
import application.Main;
import helper.Loader;
import helper.Vector;

public class FistR extends entities.Entity {

	private static final String src_img = "Hand_V3.2.png";
	private static final String src_dieanim = "";

	private static final String[] src_sndSpawn = { "faustangriff1.wav", "faustangriff2.wav", "faustangriff3.wav", };
	private static final String[] src_sndDie = { "faustdie.wav" };
	private static final String[] src_sndWeg = { "faustweg1.wav", "faustweg2.wav", "faustweg3.wav" };

	private static final int HP_init = 100;
	private static final Vector speed_init = new Vector(-50, 0);
	private static final Vector size_init = new Vector(10, 1);

	private static final int drawingOrder = 0;
	// variables for fist movement control
	private int range;

	private boolean collidable = true;

	public FistR(int x, int y, int range) {
		super(x, y);
		System.out.println("Fist right");

		this.range = range;
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
		return collidable;
	}

	@Override
	public void LoadAssets() {
		this.img = Loader.LoadImage(src_img);
		this.sndSpawn = Loader.LoadSound(src_sndSpawn[Main.game.ran.nextInt(src_sndSpawn.length)]);
		this.sndDie = Loader.LoadSound(src_sndDie[Main.game.ran.nextInt(src_sndDie.length)]);
		this.sndWeg = Loader.LoadSound(src_sndWeg[Main.game.ran.nextInt(src_sndWeg.length)]);
	}

	@Override
	public void move(double dtime) {
		// if on target tile 'range', dont move, unless speed is inverted
		if (this.getX() >= range && this.getSpeed().x > 0) {
			if (Main.game.bots.getRes() < conResCost * dtime) {
				this.die();
			} else Main.game.bots.reduceRes((conResCost * dtime));
			return;
		}
		if (this.getX() > 10)
			this.onDie();
		super.move(dtime);

	}

//	@Override
//	public void onCollide(Entity e) {
//		if (e instanceof Haribo) {
//			e.reduceHP(1);
//			System.out.println("Collision!");
//		}
//	}	

	@Override
	public void onCollide(Entity e) {
		if (e instanceof Haribo) {
			e.reduceHP(1);
			System.out.println("Collision!");
		}
	}

	@Override
	public void onDie() {
		Fistmanagement.fists[(int) this.y - 5] = null;
		Fistmanagement.occupied[(int) this.y - 5] = false;
		Main.game.removeEntity(this);
	}
}
