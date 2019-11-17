package entities;

import application.Entity;
import application.Main;
import application.Vector;

public class FistR extends application.Entity {

	private static final String src_img = "Hand_V3.2.png";
	private static final String src_dieanim = "";

	private static final String[] src_sndSpawn = { "faustangriff1.wav", "faustangriff2.wav", "faustangriff3.wav",};
	private static final String[] src_sndDie = { "faustdie.wav" };
	private static final String[] src_sndweg = { "faustweg1.wav", "faustweg2.wav", "faustweg3.wav" };

	private static final int HP_init = 100;
	private static final Vector speed_init = new Vector(-4, 0);
	private static final Vector size_init = new Vector(1, 1);
	
	private boolean collidable = true;
	private static final int drawingOrder = 0;
	
	//variables for fist movement control
	private static int range;

	public FistR(int x, int y, int range) {
		super(x, y);
		System.out.println("Fist right");

		this.range = range;
	}

	@Override
	public void LoadAssets() {
		this.img = Main.game.loader.LoadImage(src_img);
		this.sndSpawn = Main.game.loader.LoadSound(src_sndSpawn[Main.game.ran.nextInt(src_sndSpawn.length)]);
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
	public void move(double dtime) {
		//if on target tile 'range', dont move, unless speed is inverted
		if(this.getX() <= range && this.getSpeed().x < 0) return;
		if(this.getX() > 10) Main.game.removeEntity(this);
		super.move(dtime);
		
	}

	@Override
	public void onCollide(Entity e) {
		if (e instanceof Haribo)
			e.reduceHP(1);
	}
	
	@Override
	public void onUncollide() {

	}
	
	@Override
	public void onDie() {
		
	}
}
