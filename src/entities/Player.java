package entities;

import application.Game;
import application.Main;
import display.MainMenu;
import helper.Animation;
import helper.Loader;
import helper.Sprite;
import helper.Vector;
import javafx.embed.swing.SwingFXUtils;

import java.awt.image.BufferedImage;

public class Player extends entities.Entity {
	private static final String src_img = "Hauptfigur_V3.1.png";
	private static final String src_dieanim = "";
	private static final String[] src_sndDie = { "gameover.wav" };

	private static final int HP_init = 1;
	private static final int drawingOrder = 0;

	private static final Vector speed_init = new Vector(0, 0);
	private static final Vector size_init = new Vector(1, 1);

	private static String src_anim = "AnimationSpriteSheet_strip3x1.png";

	// Gets the upper left images of my sprite sheet
	private static BufferedImage[] anim1 = { Sprite.getSprite(0, 0, src_anim), Sprite.getSprite(2, 0, src_anim) };

	// Images for each animation

	// private static BufferedImage[] walkingRight = {Sprite.getSprite(0, 2),
	// Sprite.getSprite(2, 2)};
	private static BufferedImage[] standartanim = { Sprite.getSprite(1, 0, src_anim) };

	// These are animation states
	private static Animation anim = new Animation(anim1, 0.3);

	// private static Animation walkRight = new Animation(walkingRight, 10);
	private static Animation standart_anim = new Animation(standartanim, 1);
	private boolean collidable = true;

	// This is the actual animation
	public Animation animation;

	public Player(int x, int y) {
		super(x, y);

		animation = anim;
		animation.start();
	}

	@Override
	public int getDrawingOrder() {
		return drawingOrder;
	}

	@Override
	public int getInitHP() {
		return HP_init;
	}

	@Override
	public Vector getInitSize() {
		return size_init.copy();
	}

	@Override
	public Vector getInitSpeed() {
		return speed_init.copy();
	}

	@Override
	public boolean isCollidable() {
		return collidable;
	}

	@Override
	public void LoadAssets() {
		this.img = Loader.LoadImage(src_img);
		this.sndDie = Loader.LoadSound(src_sndDie[Main.game.ran.nextInt(src_sndDie.length)]);
	}

	@Override
	public void move(double dtime) {
	}

	@Override
	public void onAnimate(double time, double dtime) {
		animation.update(time, dtime);
		img = SwingFXUtils.toFXImage(animation.getSprite(), null);
	}

	@Override
	public void onCollide(Entity e) {
//		super.onCollide(e);
		if (e instanceof Haribo || e instanceof Garbage) {
			this.stopWalking();
			reduceHP(1);
			Main.game.loop.terminate();
		}
	}

	@Override
	public void onDie()
	{
		Main.game.Over();
	}
}
