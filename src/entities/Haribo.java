package entities;

/**
 * Die Klasse der mutierten Haribo-Bären, unserem Tier mit 'h' und dem einfachsten Gegner im Spiel
 */

import application.Entity;
import application.Main;
import application.Vector;
import darstellung.Loader;
import sounds.SoundLoader;

public class Haribo extends application.Entity {

	private static final String src_img = "Gegner_Feuer_V3.2.png";
	private static final String src_dieanim = "";

	private static final String[] src_sndSpawn = { "enemyspawn1.wav" , "enemyspawn2.wav", "enemyspawn3.wav"};
	private static final String[] src_sndDie = { "enemysmash1.wav", "enemysmash2.wav", "enemysmash3.wav", "enemysmash4.wav", "enemysmash5.wav", "enemysmash6.wav", "enemysmash7.wav",};

	private static final int HP_init = 1;
	private static final Vector speed_init = new Vector(0, 2);
	private static final Vector size_init = new Vector(1, 1);
	
	private static final boolean collidable = true;

	public Haribo(int x, int y) {
		super(x, y);
	}

	@Override
	public void LoadAssets() {
		this.img = Loader.LoadImage(src_img);
		this.sndSpawn = SoundLoader.LoadSound(src_sndSpawn[Main.game.ran.nextInt(src_sndSpawn.length)]);
		this.sndDie = SoundLoader.LoadSound(src_sndDie[Main.game.ran.nextInt(src_sndDie.length)]);
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
	public void onCollide(Entity e) {
		super.onCollide(e);
		if (e instanceof Fist) {
			stopWalking();
			reduceHP(1);
		}
	}

	@Override
	public void onUncollide() {
		this.startWalking();
	}

	@Override
	public void onDie() {
		
	}
}
