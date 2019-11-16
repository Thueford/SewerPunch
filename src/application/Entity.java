package application;

import darstellung.Loader;
import Sounds.Sound;
import Sounds.SoundLoader;
import javafx.scene.image.Image;

public abstract class Entity extends Rectangle {
	
	public final String src_img;
	public final Image img;
	public final Sound sound;

	public Point speed;
	public int HP;
	
	public Entity(String src_img, String src_sound, String src_dieanim, int x, int y) {
		super(x, y, 1, 1);
		
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		this.speed = getInitSpeed();
		this.HP = getInitHP();
		
		this.src_img = src_img;
		this.img = Loader.LoadImage(src_img);
		this.sound = SoundLoader.LoadSound(src_sound);
	}

	public abstract int getInitHP();
	public abstract Point getInitSpeed();
	public abstract Point getInitSize();
	
	public static double getSpeed() {
		return speed;
	}
	public static void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean isDead() {
		return this.HP <= 0;
	}

	//Entitäten töten
	public void die() {
		onDie();
	}
	
	public abstract void move();
	public abstract void attack();
	
	public abstract void onDie();
	public abstract void onCollide();
}
