package entities;

import helper.Rectangle;
import helper.Sound;
import helper.Vector;
import javafx.scene.image.Image;

/**
 * @author afeilke1
 *
 */
public abstract class Entity extends Rectangle {

	// game vars
	public boolean visible = true, dead = false, collided = false;
	public Vector speed = new Vector();
	public int HP;

	// assets
	public Image img;
	public Sound sndSpawn, sndDie, sndHit, sndWeg;
	public double deadTimer;

	public Entity(double x, double y) {
		super(x, y, 1, 1);

		this.width = getInitSize().x;
		this.height = getInitSize().y;

		this.speed.set(getInitSpeed());
		this.HP = getInitHP();

		LoadAssets();
	}

	/**
	 * triggers an attack
	 */
	public void attack() {
	}

	/**
	 * kill entity
	 */
	public void die() {
		this.dead = true;
		this.onDie();
	}

	public abstract int getDrawingOrder();

	public abstract int getInitHP();

	public abstract Vector getInitSize();

	public abstract Vector getInitSpeed();

	public Vector getSpeed() {
		return this.speed;
	}

	public abstract boolean isCollidable();

	public boolean isDead() {
		return this.HP <= 0;
	}

	/**
	 * Called once on game load Load all relevat assets
	 */

	public abstract void LoadAssets();

	/**
	 * move entity in current frame
	 * 
	 * @param dtime
	 */
	public void move(double dtime) {
		this.add(this.speed.x * dtime, this.speed.y * dtime);
	}

	/**
	 * Called every time the sprite is drawn
	 */
	public void onAnimate(double time, double dtime) {
	}

	/**
	 * Called when entity collided with another
	 * 
	 * @param e collided entity
	 */
	public void onCollide(Entity e) {
	}

	/**
	 * called when entity died
	 */
	public void onDie() {
	}

	/**
	 * Called when entity was moved
	 */
	public void onMove() {
	}

	/**
	 * Called when collision ended
	 */
	public void onUncollide() {
	}

	/**
	 * apply damage and check 4 death
	 * 
	 * @param damage
	 */
	public void reduceHP(int damage) {
		this.HP -= damage;
		if (HP <= 0)
			this.die();
	}

	public void setSpeed(Vector speed) {
		this.speed.set(speed);
	}

	public void startWalking() {
		this.speed.set(this.getInitSpeed());
	}

	public void stopWalking() {
		this.speed.set(0, 0);
	}

}
