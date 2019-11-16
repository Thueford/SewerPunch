package entities;
/**
 * Die Klasse der mutierten Haribo-Bären, unserem Tier mit 'h' und dem einfachsten Gegner im Spiel
 */

import application.Point;

public class Haribo extends application.Entity{
	private static final String src_sound = "./res/tone.wav";
	private static final String src_img = "./res/haribo.jpeg";
	private static final String src_dieanim = "";
	
	private static final Point speed_init = new Point(0, 2);
	private static final int HP_init = 1;
	private static final Point size_init = new Point(1,1);
	
	public Haribo(int x, int y) {
		super(src_img, src_sound, src_dieanim, x, y);
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
