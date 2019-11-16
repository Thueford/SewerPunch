package application;

import darstellung.Loader;
import javafx.scene.image.Image;
import sounds.Sound;
import sounds.SoundLoader;

public abstract class Entity extends Rectangle {

	// game vars
	public boolean visible = true, dead = false;
	public Point speed;
	public int HP;

	// assets
	public Image img;
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
	
	public void stopWalking() {
		this.speed.set(0,0);
	}
	
	public void startWalking() {
		this.speed.set(this.getInitSpeed());
	}

	public boolean isDead() {
		return this.HP <= 0;
	}

	/**
	 * move entity in current frame
	 * @param dtime
	 */
	public void move(long dtime) {
		move(speed.x * dtime, speed.y * dtime);
	}
	
	/**
	 * apply damage and check 4 death
	 * @param damage
	 */
	public void reduceHP(int damage) {
		HP -= damage;
		if(damage <= 0) die();
	}

	/**
	 * kill entity
	 */
	public void die() {
		dead = true;
		onDie();
	}
	
	/**
	 * Called when entity was moved
	 */
	public void onMove() {}
	/**
	 * Called when entity collided with another
	 * @param e collided entity
	 */
	public void onCollide(Entity e) {}
	/**
	 * Called when collision ended
	 */
	public void onUncollide() {}
	/**
	 * triggers an attack
	 */
	public void attack() {}
	/**
	 * called when entity died
	 */
	public void onDie() {}

}
