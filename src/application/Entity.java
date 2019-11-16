package application;

import darstellung.Loader;
import javafx.scene.image.Image;
import sounds.Sound;
import sounds.SoundLoader;

public class Entity extends Rectangle {
	
	public final String src_img;
	public final Image img;
	public final Sound sound;
	
	private static double speed;
	private static int HP;
	
	public Entity(String src_img, String src_sound, String src_dieanim, int x, int y, int width, int height, double speed, int HP) {
		super(x, y, width, height);
		
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.HP = HP;
		
		this.src_img = src_img;
		this.img = Loader.LoadImage(src_img);
		this.sound = SoundLoader.LoadSound(src_sound);
	}
	
	public static double getSpeed() {
		return speed;
	}
	public static void setSpeed(double speed) {
		Entity.speed = speed;
	}

	public boolean isDead() {
		return this.HP<=0;
	}

	//Entitäten töten
	public void die() {
		
	}
}
