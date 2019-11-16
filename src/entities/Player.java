package entities;

import java.awt.image.BufferedImage;

import application.Animation;
import application.Point;
import application.Sprite;
import darstellung.Loader;
import sounds.SoundLoader;

public class Player extends application.Entity {
	private static final String src_img = "";
	private static final String src_dieanim = "";
	
	private static final String src_sndSpawn = "";
	private static final String src_sndDie = "";
	
	private static final Point speed_init = new Point(0, 0);
	private static final int HP_init = 1;
	private static final Point size_init = new Point(1,1);
	
	// Images for each animation
	private BufferedImage[] walkingLeft = {Sprite.getSprite(0, 1), Sprite.getSprite(2, 1)}; // Gets the upper left images of my sprite sheet
	private BufferedImage[] walkingRight = {Sprite.getSprite(0, 2), Sprite.getSprite(2, 2)};
	private BufferedImage[] standing = {Sprite.getSprite(1, 0)};

	// These are animation states
	private Animation walkLeft = new Animation(walkingLeft, 10);
	private Animation walkRight = new Animation(walkingRight, 10);
	private Animation standing1 = new Animation(standing, 10);

	// This is the actual animation
	public Animation animation = standing1;
	
	public Player(String src, int x, int y, int width, int height) {
		super(x, y);
		
	}

	@Override
	public void LoadAssets() {
		this.img = Loader.LoadImage(src_img);
		this.sndSpawn = SoundLoader.LoadSound(src_sndSpawn);
		this.sndDie = SoundLoader.LoadSound(src_sndDie);
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
	public void move() {
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
