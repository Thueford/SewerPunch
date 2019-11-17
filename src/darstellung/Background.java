package darstellung;

import application.Entity;
import application.Main;
import application.Vector;
import darstellung.Loader;
import entities.Fist;
import sounds.SoundLoader;

public class Background extends Entity{
	private static final String[] src_img = {"bg1.png", "bg2.png", "bg3.png", "bg4.png",};
	private static final String src_dieanim = "";

	private static final int HP_init = 1;
	private static final Vector speed_init = new Vector(0, 0.5);
	private static final Vector size_init = new Vector(1, 1);

	private static final boolean collidable = false;
	
	public Background(int x, int y) {
		super(x, y);
	}

	@Override
	public void LoadAssets() {
		this.img = Loader.LoadImage(src_img[Main.game.ran.nextInt(src_img.length)]);
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

	@Override
	public void onCollide(Entity e) {
		
	}

	@Override
	public void onUncollide() {
		this.startWalking();
	}

	@Override
	public void onDie() {
		
	}
}
