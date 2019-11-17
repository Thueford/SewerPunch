package application;

import javafx.scene.image.Image;
import sounds.Sound;

public abstract class Entity extends Rectangle {

	// game vars
	public boolean visible = true, dead = false, collided = false;
	public Vector speed;
	public int HP;

	// assets
	public Image img;
	public Sound sndSpawn, sndDie;

	public Entity(int x, int y) {
		super(x, y, 1, 1);

		this.x = x;
		this.y = y;

		this.speed = getInitSpeed();
		this.HP = getInitHP();

		LoadAssets();
	}

	public abstract int getInitHP();

	public abstract Vector getInitSpeed();

	public abstract Vector getInitSize();
	
	public abstract boolean getCollidable();

	/**
	 * Called once on game load Load all relevat assets
	 */
	public abstract void LoadAssets();

	public Vector getSpeed() {
		return speed;
	}

	public void setSpeed(Vector speed) {
		this.speed = speed;
	}

	public void stopWalking() {
		this.speed.set(0, 0);
	}

	public void startWalking() {
		this.speed.set(this.getInitSpeed());
	}

	public boolean isDead() {
		return this.HP <= 0;
	}

	/**
	 * move entity in current frame
	 * 
	 * @param dtime
	 */
	public void move(long dtime) {
		move(speed.x * dtime, speed.y * dtime);
	}

	/**
	 * apply damage and check 4 death
	 * 
	 * @param damage
	 */
	public void reduceHP(int damage) {
		HP -= damage;
		if (damage <= 0)
			die();
	}

	/**
	 * kill entity
	 */
	public void die() {
		dead = true;
		onDie();
	}

	/**
	 * Called on every game loop
	 */
	public void onAnimate() {
	}

	/**
	 * Called when entity was moved
	 */
	public void onMove() {
	}

	/**
	 * Called when entity collided with another
	 * 
	 * @param e collided entity
	 */
	public void onCollide(Entity e) {
		collided = true;
	}

	/**
	 * Called when collision ended
	 */
	public void onUncollide() {
	}

	/**
	 * triggers an attack
	 */
	public void attack() {
	}

	/**
	 * called when entity died
	 */
	public void onDie() {
	}

}
