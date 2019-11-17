package entities;

import application.Entity;
import application.Main;
import application.Vector;
import darstellung.Loader;
import sounds.SoundLoader;

public class Fist extends application.Entity {

	private static final String src_img = "./res/haribo.jpeg";
	private static final String src_dieanim = "";

	private static final String[] src_sndSpawn = { "./res/tone.wav" };
	private static final String[] src_sndDie = { "./res/tone.wav" };

	private static final int HP_init = 100;
	private static final Vector speed_init = new Vector(4, 0);
	private static final Vector size_init = new Vector(1, 1);
	
	private static final boolean collidable = true;

	public Fist(int x, int y) {
		super(x, y);
		decrease_bots(x+1);
	}

	@Override
	public void LoadAssets() {
		this.img = Loader.LoadImage(src_img);
		this.sndSpawn = SoundLoader.LoadSound(src_sndSpawn[Main.game.ran.nextInt(src_sndSpawn.length)]);
		this.sndDie = SoundLoader.LoadSound(src_sndSpawn[Main.game.ran.nextInt(src_sndDie.length)]);
	}
	
	@Override
	public boolean getCollidable() {
		return collidable;
	}

	@Override
	public int getInitHP() {
		return HP_init;
	}

	@Override
	public Vector getInitSpeed() {
		return speed_init;
	}

	@Override
	public Vector getInitSize() {
		return size_init;
	}
	
	@Override
	public void move(long dtime) {	
		super.move(dtime);
		
	}

	@Override
	public void onCollide(Entity e) {
		if (e instanceof Haribo)
			e.reduceHP(1);
	}
	public void decrease_bots(int anz) {
		Main.game.bots -= anz;
	}

	@Override
	public void onUncollide() {

	}
}
