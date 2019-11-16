package entities;

import application.Entity;
import application.Main;
import application.Point;
import darstellung.Loader;
import sounds.SoundLoader;

public class Fist extends application.Entity {

	private static final String src_img = "./res/haribo.jpeg";
	private static final String src_dieanim = "";

	private static final String[] src_sndSpawn = { "./res/tone.wav" };
	private static final String[] src_sndDie = { "./res/tone.wav" };

	private static final int HP_init = 100;
	private static final Point speed_init = new Point(4, 0);
	private static final Point size_init = new Point(1, 1);

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
	public int getInitHP() {
		// TODO Auto-generated method stub
		return HP_init;
	}

	@Override
	public Point getInitSpeed() {
		// TODO Auto-generated method stub
		return speed_init;
	}

	@Override
	public Point getInitSize() {
		// TODO Auto-generated method stub
		return size_init;
	}
	
	@Override
	public void move(long dtime) {	
		super.move(dtime);
		
	}

	@Override
	public void onCollide(Entity e) {
		// TODO Auto-generated method stub
		if (e instanceof Player)
			e.reduceHP(1);
	}
	public void decrease_bots(int anz) {
		Main.game.bots -= anz;
	}

	@Override
	public void onUncollide() {

	}
}
