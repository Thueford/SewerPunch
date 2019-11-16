package entities;
/**
 * Die Klasse der mutierten Haribo-Bären, unserem Tier mit 'h' und dem einfachsten Gegner im Spiel
 */

import java.util.Random;

import application.Point;
import darstellung.Loader;
import sounds.SoundLoader;

public class Haribo extends application.Entity{
	private static final String src_img = "./res/haribo.jpeg";
	private static final String src_dieanim = "";
	
	private static final String[] src_sndSpawn = {"./res/tone.wav"};	
	private static final String[] src_sndDie = {"./res/tone.wav"};
	
	private static final Point speed_init = new Point(0, 2);
	private static final int HP_init = 1;
	private static final Point size_init = new Point(1,1);
	
	
	public Haribo(int x, int y) {
		super(x, y);
	}

	@Override
	public void LoadAssets() {
		this.img = Loader.LoadImage(src_img);
		Random x = new Random();
		this.sndSpawn = SoundLoader.LoadSound(src_sndSpawn[x.nextInt(src_sndSpawn.length)]);
		this.sndDie = SoundLoader.LoadSound(src_sndSpawn[x.nextInt(src_sndDie.length)]);
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
	public void move() {
		// TODO Auto-generated method stub
		this.move(this.x + speed.x, this.y + speed.y);
	}

	@Override
	public void attack() {
		//macht nix, Haribos haben keine Attacken
		
	}

	@Override
	public void onDie() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCollide() {
		// TODO Auto-generated method stub
		this.stopEntity();
	}
	
	@Override
	public void onUncollide() {
		this.startEntity();
	}
	
	
}
