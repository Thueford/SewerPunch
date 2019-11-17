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
	private static final String src_dieanim = "";

	private static final String[] src_sndSpawn = { "enemyspawn1.wav" , "enemyspawn2.wav", "enemyspawn3.wav"};
	private static final String[] src_sndDie = { "enemysmash1.wav", "enemysmash2.wav", "enemysmash3.wav", "enemysmash4.wav", "enemysmash5.wav", "enemysmash6.wav", "enemysmash7.wav"};

	private static final int HP_init = 1;
	private static final Vector speed_init = new Vector(0, 2);
	private static final Vector size_init = new Vector(1, 1);
	
	private static final boolean collidable = true;
	
	private int anim_speed = 8;
	
	//Animation
	private static String src_anim = "Gegner_Feuer_Spritesheet.png";
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

	private BufferedImage[] standartanim = { Sprite.getSprite(0, 0, src_anim) };
	// These are animation states
	private Animation anim = new Animation(anim1, anim_speed);
	private Animation standart_anim = new Animation(standartanim, 10);
	// This is the actual animation
	public Animation animation = standart_anim;
	

	public Haribo(int x, int y) {
		super(x, y);
		
		animation = anim;
		animation.start();
	}

	@Override
	public void LoadAssets() {
		this.img = Main.game.loader.LoadImage(src_img);
		this.sndSpawn = Main.game.loader.LoadSound(src_sndSpawn[Main.game.ran.nextInt(src_sndSpawn.length)]);
		this.sndDie = Main.game.loader.LoadSound(src_sndDie[Main.game.ran.nextInt(src_sndDie.length)]);
	}

	@Override
	public boolean getCollidable() {
		return collidable;
	}

	@Override
	public int getInitHP() {
		return HP_init;
	}

	@Override
	public Vector getInitSpeed() {
		return speed_init;
	}

	@Override
	public Vector getInitSize() {
		return size_init;
	}
	
	public void onAnimate() {
		//animation.restart();
		animation.update();
		img = SwingFXUtils.toFXImage(animation.getSprite(), null);
	}

	@Override
	public void onCollide(Entity e) {
		super.onCollide(e);
		if (e instanceof Fist) {
			stopWalking();
			reduceHP(1);
		}
	}

	@Override
	public void onUncollide() {
		this.startWalking();
	}

	@Override
	public void onDie() {
	}
}
