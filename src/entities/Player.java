package entities;

import java.awt.image.BufferedImage;

import application.Animation;
import application.Main;
import application.Vector;
import application.Sprite;
import javafx.embed.swing.SwingFXUtils;

public class Player extends application.Entity {
	private static final String src_img = "Hauptfigur_V3.1.png";
	private static final String src_dieanim = "";
	private static final String[] src_sndDie = { "gameover.wav" };
	

	private static final int HP_init = 1;
	private static final int drawingOrder = 0;

	private static final Vector speed_init = new Vector(0, 0);
	private static final Vector size_init = new Vector(1, 1);
	
	private boolean collidable = true;

	private static String src_anim = "AnimationSpriteSheet_strip3x1.png";

	// Images for each animation

	// Gets the upper left images of my sprite sheet
	private static BufferedImage[] anim1 = { 
			Sprite.getSprite(0, 0, src_anim), 
			Sprite.getSprite(2, 0, src_anim) };

	// private static BufferedImage[] walkingRight = {Sprite.getSprite(0, 2),
	// Sprite.getSprite(2, 2)};
	private static BufferedImage[] standartanim = { Sprite.getSprite(1, 0, src_anim) };

	// These are animation states
	private static Animation anim = new Animation(anim1, 12);
	// private static Animation walkRight = new Animation(walkingRight, 10);
	private static Animation standart_anim = new Animation(standartanim, 10);

	// This is the actual animation
	public Animation animation = standart_anim;

	public Player(int x, int y) {
		super(x, y);
		
		animation = anim;
		animation.start();
	}

	@Override
	public void LoadAssets() {
		this.img = Main.game.loader.LoadImage(src_img);
		this.sndDie = Main.game.loader.LoadSound(src_sndDie[Main.game.ran.nextInt(src_sndDie.length)]);
	}
	
	@Override
	public boolean isCollidable() {
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
	
	@Override
	public int getDrawingOrder() {
		return drawingOrder;
	}

	@Override
	public void onAnimate(double time, double dtime) {
		animation.update(time, dtime);
		img = SwingFXUtils.toFXImage(animation.getSprite(), null);
	}

	@Override
	public void move(double dtime) {
		
	}
}
