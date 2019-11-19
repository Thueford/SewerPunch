package entities;

import application.Entity;
import application.Main;
import application.Vector;
import darstellung.Loader;

public class Garbage extends application.Entity{
	private static final String src_img = "Hindernis_1.2.png";
	private static final String src_dieanim = "";

	private static final String[] src_sndSpawn = { "enemyspawn1.wav" , "enemyspawn2.wav", "enemyspawn3.wav"};
	private static final String[] src_sndDie = { "enemysmash1.wav", "enemysmash2.wav", "enemysmash3.wav", "enemysmash4.wav", "enemysmash5.wav", "enemysmash6.wav", "enemysmash7.wav",};

	private static final int HP_init = 1;
	private static final Vector speed_init = new Vector(0, 0.5);
	private static final Vector size_init = new Vector(1, 1);
	
	private boolean collidable = true;
	private static final int drawingOrder = -5;

	public Garbage(int x, int y) {
		super(x, y);
	}

	@Override
	public void LoadAssets() {
		this.img = Main.game.loader.LoadImage(src_img);
		this.sndSpawn = Main.game.loader.LoadSound(src_sndSpawn[Main.game.ran.nextInt(src_sndSpawn.length)]);
		this.sndDie = Main.game.loader.LoadSound(src_sndDie[Main.game.ran.nextInt(src_sndDie.length)]);
	}
	
	@Override
	public boolean isCollidable() {
		return this.collidable;
	}

	@Override
	public int getInitHP() {
		return HP_init;
	}

	@Override
	public Vector getInitSpeed() {
		return speed_init.copy();
	}

	@Override
	public Vector getInitSize() {
		return size_init.copy();
	}
	
	@Override
	public int getDrawingOrder() {
		return drawingOrder;
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
	public void onUncollide() {
		this.startWalking();
	}

	@Override
	public void onDie() {
		collidable = false;
		Main.game.removeEntity(this);
	}

}
