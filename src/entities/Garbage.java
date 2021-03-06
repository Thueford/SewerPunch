package entities;

import application.Main;
import helper.Loader;
import helper.Vector;

public class Garbage extends entities.Entity {
	private static final String src_img = "Hindernis_1.2.png";
	private static final String src_dieanim = "";

	private static final String[] src_sndSpawn = { "enemyspawn1.wav", "enemyspawn2.wav", "enemyspawn3.wav" };
	private static final String[] src_sndDie = { "enemysmash1.wav", "enemysmash2.wav", "enemysmash3.wav",
			"enemysmash4.wav", "enemysmash5.wav", "enemysmash6.wav", "enemysmash7.wav", };

	private static final int HP_init = 1;
	private static final Vector speed_init = new Vector(0, 0.5);
	private static final Vector size_init = new Vector(1, 1);

	private static final int drawingOrder = -5;
	private boolean collidable = true;

	public Garbage(int x, int y) {
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
		return this.collidable;
	}

	@Override
	public void LoadAssets() {
		this.img = Loader.LoadImage(src_img);
		this.sndSpawn = Loader.LoadSound(src_sndSpawn[Main.game.ran.nextInt(src_sndSpawn.length)]);
		this.sndDie = Loader.LoadSound(src_sndDie[Main.game.ran.nextInt(src_sndDie.length)]);
	}

	@Override
	public void onCollide(Entity e) {
		super.onCollide(e);
		if (e instanceof FistL || e instanceof FistR) {
			this.stopWalking();
			this.reduceHP(1);
		}
	}

	@Override
	public void onDie() {
		collidable = false;
		Main.game.removeEntity(this);
	}

	@Override
	public void onUncollide() {
		this.startWalking();
	}

}
