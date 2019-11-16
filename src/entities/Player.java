package entities;

import application.Point;

public class Player extends application.Entity{
	private static final String src_sound = "";
	private static final String src_img = "";
	private static final String src_dieanim = "";
	
	private static final Point speed_init = new Point(0, 0);
	private static final int HP_init = 1;
	
	public Player(String src, int x, int y, int width, int height) {
		super(src_img, src_sound, src_dieanim, x, y, width, height, speed_init, HP_init);
	}
}
