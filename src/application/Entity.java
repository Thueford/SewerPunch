package application;

import darstellung.Loader;
import javafx.scene.image.Image;
import sounds.Sound;
import sounds.SoundLoader;

public abstract class Entity extends Rectangle {
	
	// game vars
	public Point speed;
	public int HP;

	// assets
	public final Image img;
	public Sound sndSpawn, sndDie;

	public Entity(int x, int y) {
		super(x, y, 1, 1);

		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		this.speed = getInitSpeed();
		this.HP = getInitHP();
		LoadAssets();
		
		this.img = Loader.LoadImage(src_img);
		this.sndSpawn = SoundLoader.LoadSound(sndSpawn);
	}

	public abstract int getInitHP();
	public abstract Point getInitSpeed();
	public abstract Point getInitSize();
	public abstract void LoadAssets();
	
	public Point getSpeed() {
		return speed;
	}
	
	public void setSpeed(Point speed) {
		this.speed = speed;
	}

	public boolean isDead() {
		return this.HP <= 0;
	}

	//Entitäten töten
	public void die() {
		onDie();
	}

	public abstract void spawn();
	public abstract void move();
	public abstract void attack();
	
	public abstract void onDie();
	public abstract void onCollide();
}
