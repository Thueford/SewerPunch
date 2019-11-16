package entities;

public class Haribo extends application.Entity{
	
	private static final String src_sound = "";
	private static final String src_img = "";
	
	private static final double speed_init = 2;
	private static final int HP_init = 1;
	
	public Haribo(String src, int x, int y, int width, int height) {
		super(src_img, src_sound, x, y, width, height, speed_init, HP_init);
	}
}
