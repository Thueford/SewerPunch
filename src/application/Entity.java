package application;

import javafx.scene.image.Image;
import sounds.Sound;

public abstract class Entity extends Rectangle {

	// game vars
	public boolean visible = true, dead = false, collided = false;
	public Vector speed = new Vector();
	public int HP;

	// assets
	public Image img;
	public Sound sndSpawn, sndDie, sndHit;
	public double deadTimer;

	public Entity(double x, double y) {
		super(x, y, 1, 1);
		
		this.width = getInitSize().x;
		this.height = getInitSize().y;

		this.speed.set(getInitSpeed());
		this.HP = getInitHP();

		LoadAssets();
	}

	public abstract int getInitHP();

	public abstract Vector getInitSpeed();

	public abstract Vector getInitSize();
	
	public abstract boolean isCollidable();
	
	public abstract int getDrawingOrder();

	/**
	 * Called once on game load Load all relevat assets
	 */
	public abstract void LoadAssets();

	public Vector getSpeed() {
		return this.speed;
	}

	public void setSpeed(Vector speed) {
		this.speed.set(speed);
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
	public void move(double dtime) {
		this.add(this.speed.x * dtime, this.speed.y * dtime);
	}

	/**
	 * apply damage and check 4 death
	 * 
	 * @param damage
	 */
	public void reduceHP(int damage) {
		this.HP -= damage;
		if (damage <= 0)
			this.die();
	}

	/**
	 * kill entity
	 */
	public void die() {
		this.dead = true;
		this.onDie();
	}

	/**
	 * Called every time the sprite is drawn
	 */
	public void onAnimate(double time, double dtime) {
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
