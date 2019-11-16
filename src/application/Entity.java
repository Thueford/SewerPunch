package application;

import darstellung.Loader;
import javafx.scene.image.Image;
import sounds.Sound;
import sounds.SoundLoader;

public abstract class Entity extends Rectangle {
	
	// game vars
	public boolean visible = true;
	public Point speed;
	public int HP;

	// assets
	public Image img;
	public Sound sndSpawn, sndDie;

	public Entity(int x, int y) {
		super(x, y, 1, 1);

		this.x = x;
		this.y = y;
		
		Main.game.entities.add(this);
		
		this.width = width;
		this.height = height;
		this.speed = getInitSpeed();
		this.HP = getInitHP();
		
		LoadAssets();
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
	
	public void stopEntity() {
		this.speed.x = 0;
		this.speed.y = 0;
	}
	public void startEntity() {
		this.speed.x = this.getInitSpeed().x;
		this.speed.y = this.getInitSpeed().y;
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
	public abstract void onUncollide();
}
