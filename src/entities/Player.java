package entities;

import java.awt.image.BufferedImage;

import application.Animation;
import application.Point;
import application.Sprite;
import darstellung.Loader;
import javafx.embed.swing.SwingFXUtils;
import sounds.SoundLoader;

public class Player extends application.Entity {
	private static final String src_img = "";
	private static final String src_dieanim = "";
	
	private static final String src_sndSpawn = "";
	private static final String src_sndDie = "";
	
	private static final Point speed_init = new Point(0, 0);
	private static final int HP_init = 1;
	private static final Point size_init = new Point(1,1);
	
	private static String src_anim = "AnimationSpriteSheet";
	
	// Images for each animation
	
	private static BufferedImage[] anim1 = {
			Sprite.getSprite(0, 1, src_anim), 
			Sprite.getSprite(2, 1, src_anim)}; // Gets the upper left images of my sprite sheet
	//private static BufferedImage[] walkingRight = {Sprite.getSprite(0, 2), Sprite.getSprite(2, 2)};
	private static BufferedImage[] standartanim = {Sprite.getSprite(1, 0, src_anim)};
	
	// These are animation states
	private static Animation anim = new Animation(anim1, 12);
	//private static Animation walkRight = new Animation(walkingRight, 10);
	private static Animation standart_anim = new Animation(standartanim, 10);
	
	@Override
	public void onAnimate() {
		// TODO Auto-generated method stub
		animation.update();
		img = SwingFXUtils.toFXImage(animation.getSprite(), null);
	}

	// This is the actual animation
	public Animation animation = standart_anim;
	
	public Player(int x, int y) {
		super(x, y);
	}

	@Override
	public void LoadAssets() {
		//this.img = Loader.LoadImage(src_img);
		//this.sndSpawn = SoundLoader.LoadSound(src_sndSpawn);
		//this.sndDie = SoundLoader.LoadSound(src_sndDie);
	}
	
	@Override
	public int getInitHP() {
		// TODO Auto-generated method stub
		return HP_init;
	}

	@Override
	public Point getInitSpeed() {
		// TODO Auto-generated method stub
		return speed_init;
	}

	@Override
	public Point getInitSize() {
		// TODO Auto-generated method stub
		return size_init;
	}

	@Override
	public void move(long dtime) {
		//while (anim_counter<100) {
		animation = anim;
		animation.start();
		//System.out.println(walkLeft);
		//}
		
	    
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDie() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCollide() {
		// TODO Auto-generated method stub
		
	}

}
