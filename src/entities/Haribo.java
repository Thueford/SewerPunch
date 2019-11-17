package entities;

import java.awt.image.BufferedImage;

import application.Animation;

/**
 * Die Klasse der mutierten Haribo-Bären, unserem Tier mit 'h' und dem einfachsten Gegner im Spiel
 */

import application.Entity;
import application.Main;
import application.Sprite;
import application.Vector;
import javafx.embed.swing.SwingFXUtils;

public class Haribo extends application.Entity {

	private static final String src_img = "Gegner_Feuer_V3.2.png";
	private static final String src_dieanim = "Haribo_death_Sprite_strip5x1.png";
	private static final String src_anim = "Gegner_Feuer_Spritesheet_strip10x1.png";

	private static final String[] src_sndSpawn = { "enemyspawn1.wav" , "enemyspawn2.wav", "enemyspawn3.wav"};
	private static final String[] src_sndDie = { "enemysmash1.wav", "enemysmash2.wav", "enemysmash3.wav", "enemysmash4.wav", "enemysmash5.wav", "enemysmash6.wav", "enemysmash7.wav"};

	private static final int HP_init = 1;
	private static final Vector speed_init = new Vector(0, 2);
	private static final Vector size_init = new Vector(1, 1);
	
	private boolean collidable = true;
	private static final int drawingOrder = 0;
	
	private double anim_speed = 0.10;
	
	//Animation
	private static BufferedImage[] anim1 = { 
			Sprite.getSprite(1, 0, src_anim), 
			Sprite.getSprite(2, 0, src_anim),
			Sprite.getSprite(3, 0, src_anim),
			Sprite.getSprite(4, 0, src_anim),
			Sprite.getSprite(5, 0, src_anim),
			Sprite.getSprite(6, 0, src_anim),
			Sprite.getSprite(7, 0, src_anim),
			Sprite.getSprite(8, 0, src_anim),
			Sprite.getSprite(9, 0, src_anim)};
	
	private static BufferedImage[] anim_die = { 
			Sprite.getSprite(0, 0, src_dieanim), 
			Sprite.getSprite(1, 0, src_dieanim),
			Sprite.getSprite(2, 0, src_dieanim),
			Sprite.getSprite(3, 0, src_dieanim),
			Sprite.getSprite(4, 0, src_dieanim)};

	private BufferedImage[] standartanim = { Sprite.getSprite(0, 0, src_anim) };
	// These are animation states
	private Animation anim = new Animation(anim1, anim_speed);
	private Animation standart_anim = new Animation(standartanim, 1);
	private Animation die_anim = new Animation(anim_die, anim_speed);
	// This is the actual animation
	public Animation animation = standart_anim;
	

	public Haribo(int x, int y) {
		super(x, y);
		
		this.animation = anim;
		this.animation.start();
		
		this.speed.y = Main.game.ran.nextDouble()*3 + 2;
	}

	@Override
	public void LoadAssets() {
		this.img = Main.game.loader.LoadImage(src_img);
		this.sndSpawn = Main.game.loader.LoadSound(src_sndSpawn[Main.game.ran.nextInt(src_sndSpawn.length)]);
		this.sndDie = Main.game.loader.LoadSound(src_sndDie[Main.game.ran.nextInt(src_sndDie.length)]);
	}

	@Override
	public boolean isCollidable() {
		return this.collidable;
	}

	@Override
	public int getInitHP() {
		return HP_init;
	}

	@Override
	public Vector getInitSpeed() {
		return speed_init.copy();
	}

	@Override
	public Vector getInitSize() {
		return size_init.copy();
	}
	
	@Override
	public void onMove() {
		super.onMove();

		if(this.x > 10 || this.y > 10)
			Main.game.removeEntity(this);
	}
	
	@Override
	public int getDrawingOrder() {
		return drawingOrder;
	}
	
	public void onAnimate(double time, double dtime) {
		//animation.restart();
		this.animation.update(time, dtime);
		this.img = SwingFXUtils.toFXImage(animation.getSprite(), null);
	}

	@Override
	public void onCollide(Entity e) {
		super.onCollide(e);
		if (e instanceof FistL) {
			this.stopWalking();
			this.speed.x = e.speed.x;
			this.speed.y = 0;
			reduceHP(1);
		}
	}

	@Override
	public void onUncollide() {
		this.startWalking();
	}

	@Override
	public void onDie() {
		animation = die_anim;
	}
}
